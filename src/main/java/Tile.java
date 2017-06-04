import com.sun.org.apache.regexp.internal.RE;
import javafx.geometry.Pos;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Created by allen on 26.05.2017.
 */
public class Tile {
    private Position position;
    private Piece piece;
    private Color color;

    public void removePiece() {
        piece = new Piece(Piece.Color.NIL, Piece.Type.NIL);
    }

    public void jump() {
        piece.setPawnJumped(true);
    }

    public void addPiece(Piece piece) {
        this.piece = piece;
    }

    public Color getDefaultColor() {
        return defaultColor;
    }

    private Color defaultColor;

    public Tile(Position position, Field field, Color color) {
        this.position = position;
        this.piece = piece;
        this.color = color;
        this.defaultColor = color;
        field.addTile(this);
    }

    public boolean isEmpty() {
        return piece.getPieceType() == Piece.Type.NIL;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Position getPosition() {
        return position;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }

    public ArrayList<Tile> getAvailableTiles(Field field) {
        switch (piece.getPieceType()) {
            case KING: return getKingTiles(field);
            case QUEEN: return getQueenTiles(field);
            case KNIGHT: return getKnightTiles(field);
            case PAWN: return getPawnTiles(field);
            case BISHOP: return getBishopTiles(field);
            case ROOK: return getRookTiles(field);
        }
        return null;
    }

    public ArrayList<Tile> getPawnTiles(Field field) {
        ArrayList<Tile> availableTiles = new ArrayList<>();
        int x = position.getX();
        int y = position.getY();
        if (piece.getType() == Piece.Color.BLACK) {
            if(!field.getByPosition(x + 1, y + 1).isEmpty()) {
                if(field.getByPosition(x + 1, y + 1).getPiece().getType() == Piece.Color.WHITE) {
                    availableTiles.add(field.getByPosition(x + 1, y + 1));
                    field.getByPosition(x + 1, y + 1).setColor(Color.RED.brighter());
                }
            }
            if(!field.getByPosition(x - 1, y + 1).isEmpty()) {
                if(field.getByPosition(x - 1, y + 1).getPiece().getType() == Piece.Color.WHITE) {
                    availableTiles.add(field.getByPosition(x - 1, y + 1));
                    field.getByPosition(x - 1, y + 1).setColor(Color.RED.brighter());
                }
            }
            if(field.getByPosition(new Position(x, y + 1)).getPiece().getPieceType() == Piece.Type.NIL) {
                availableTiles.add(field.getByPosition(new Position(x, y + 1)));
                field.getByPosition(x, y + 1).setColor(Color.GREEN.brighter());
                if(!piece.isPawnJumped() && field.getByPosition(new Position(x, y + 2))
                        .getPiece().getPieceType() == Piece.Type.NIL) {
                    availableTiles.add(field.getByPosition(new Position(x, y + 2)));
                    field.getByPosition(x, y + 2).setColor(Color.GREEN.brighter());
                }
            }
        } else {
            if(!field.getByPosition(x + 1, y - 1).isEmpty()) {
                if(field.getByPosition(x + 1, y - 1).getPiece().getType() == Piece.Color.BLACK) {
                    availableTiles.add(field.getByPosition(x + 1, y - 1));
                    field.getByPosition(x + 1, y - 1).setColor(Color.RED.brighter());
                }
            }
            if(!field.getByPosition(x - 1, y - 1).isEmpty()) {
                if(field.getByPosition(x - 1, y - 1).getPiece().getType() == Piece.Color.BLACK) {
                    availableTiles.add(field.getByPosition(x - 1, y - 1));
                    field.getByPosition(x - 1, y - 1).setColor(Color.RED.brighter());
                }
            }
            if(field.getByPosition(new Position(x, y - 1)).getPiece().getPieceType() == Piece.Type.NIL) {
                availableTiles.add(field.getByPosition(new Position(x, y - 1)));
                field.getByPosition(x, y - 1).setColor(Color.GREEN.brighter());
                if(!piece.isPawnJumped() && field.getByPosition(new Position(x, y - 2))
                        .getPiece().getPieceType() == Piece.Type.NIL) {
                    availableTiles.add(field.getByPosition(new Position(x, y - 2)));
                    field.getByPosition(x, y - 2).setColor(Color.GREEN.brighter());
                }
            }
        }
        return availableTiles;
    }

    public ArrayList<Tile> getRookTiles(Field field) {
        return getLines(field);
    }

    public ArrayList<Tile> getQueenTiles(Field field) {
        ArrayList<Tile> availableTiles = getLines(field);
        availableTiles.addAll(getDiags(field));
        return availableTiles;
    }

    public ArrayList<Tile> getBishopTiles(Field field) {
        return getDiags(field);
    }

    public ArrayList<Tile> getKnightTiles(Field field) {
        int x = position.getX();
        int y = position.getY();
        ArrayList<Tile> buffTiles = new ArrayList<>();
        ArrayList<Tile> availableTiles = new ArrayList<>();
        buffTiles.add(field.getByPosition(new Position(x + 2, y + 1)));
        buffTiles.add(field.getByPosition(new Position(x + 2, y - 1)));
        buffTiles.add(field.getByPosition(new Position(x - 2, y + 1)));
        buffTiles.add(field.getByPosition(new Position(x - 2, y - 1)));
        buffTiles.add(field.getByPosition(new Position(x + 1, y + 2)));
        buffTiles.add(field.getByPosition(new Position(x - 1, y + 2)));
        buffTiles.add(field.getByPosition(new Position(x + 1, y - 2)));
        buffTiles.add(field.getByPosition(new Position(x - 1, y - 2)));
        for(Tile i : buffTiles) {
            if(i.getPiece().getPieceType() != Piece.Type.NIL) {
                if(i.getPiece().getType() != piece.getType()) {
                    availableTiles.add(i);
                    i.setColor(Color.RED.brighter());
                }
            } else {
                availableTiles.add(i);
                i.setColor(Color.GREEN.brighter());
            }
        }
        return availableTiles;
    }

    public ArrayList<Tile> getKingTiles(Field field) {
        ArrayList<Tile> availableTiles = new ArrayList<>();
        int x = position.getX();
        int y = position.getY();
        if(field.getByPosition(new Position(x + 1, y)).getPiece().getPieceType() != Piece.Type.NIL) {
            if(field.getByPosition(new Position(x + 1, y)).getPiece().getType() != piece.getType()) {
                availableTiles.add(field.getByPosition(new Position(x + 1, y)));
                field.getByPosition(new Position(x + 1, y)).setColor(Color.RED);
            }
        } else {
            availableTiles.add(field.getByPosition(new Position(x + 1, y)));
            field.getByPosition(new Position(x + 1, y)).setColor(Color.GREEN);
        }
        if(field.getByPosition(new Position(x, y - 1)).getPiece().getPieceType() != Piece.Type.NIL) {
            if(field.getByPosition(new Position(x, y - 1)).getPiece().getType() != piece.getType()) {
                availableTiles.add(field.getByPosition(new Position(x, y - 1)));
                field.getByPosition(new Position(x, y - 1)).setColor(Color.RED);
            }
        } else {
            availableTiles.add(field.getByPosition(new Position(x, y - 1)));
            field.getByPosition(new Position(x, y - 1)).setColor(Color.GREEN);
        }
        if(field.getByPosition(new Position(x, y + 1)).getPiece().getPieceType() != Piece.Type.NIL) {
            if(field.getByPosition(new Position(x, y + 1)).getPiece().getType() != piece.getType()) {
                availableTiles.add(field.getByPosition(new Position(x, y + 1)));
                field.getByPosition(new Position(x, y + 1)).setColor(Color.RED);
            }
        } else {
            availableTiles.add(field.getByPosition(new Position(x, y + 1)));
            field.getByPosition(new Position(x, y + 1)).setColor(Color.GREEN);
        }
        if(field.getByPosition(new Position(x - 1, y)).getPiece().getPieceType() != Piece.Type.NIL) {
            if(field.getByPosition(new Position(x - 1, y)).getPiece().getType() != piece.getType()) {
                availableTiles.add(field.getByPosition(new Position(x - 1, y)));
                field.getByPosition(new Position(x - 1, y)).setColor(Color.RED);
            }
        } else {
            availableTiles.add(field.getByPosition(new Position(x - 1, y)));
            field.getByPosition(new Position(x - 1, y)).setColor(Color.GREEN);
        }
        return availableTiles;
    }

    public ArrayList<Tile> getDiags(Field field) {
        ArrayList<Tile> availableTiles = new ArrayList<>();
        int i = this.getPosition().getX();
        int j = this.getPosition().getY();
        boolean isEmpty = true;
        while (i < 11 && j < 11 && isEmpty) {
            i++;
            j++;
            Tile diagTile = field.getByPosition(new Position(i, j));
            if (diagTile.getPiece().getPieceType() != Piece.Type.NIL) {
                isEmpty = false;
                if (diagTile.getPiece().getType() != this.getPiece().getType()) {
                    diagTile.setColor(Color.RED.brighter());
                    availableTiles.add(diagTile);
                }
            } else {
                diagTile.setColor(Color.GREEN.brighter());
                availableTiles.add(diagTile);
            }
        }
        i = this.getPosition().getX();
        j = this.getPosition().getY();
        isEmpty = true;
        while (j < 11 && i > 0 && isEmpty) {
            i--;
            j++;
            Tile diagTile = field.getByPosition(new Position(i, j));
            if (diagTile.getPiece().getPieceType() != Piece.Type.NIL) {
                isEmpty = false;
                if (diagTile.getPiece().getType() != this.getPiece().getType()) {
                    diagTile.setColor(Color.RED.brighter());
                    availableTiles.add(diagTile);
                }
            } else {
                diagTile.setColor(Color.GREEN.brighter());
                availableTiles.add(diagTile);
            }
        }
        i = this.getPosition().getX();
        j = this.getPosition().getY();
        isEmpty = true;
        while (i < 11 && j > 0 && isEmpty) {
            i++;
            j--;
            Tile diagTile = field.getByPosition(new Position(i, j));
            if (diagTile.getPiece().getPieceType() != Piece.Type.NIL) {
                isEmpty = false;
                if (diagTile.getPiece().getType() != this.getPiece().getType()) {
                    diagTile.setColor(Color.RED.brighter());
                    availableTiles.add(diagTile);
                }
            } else {
                diagTile.setColor(Color.GREEN.brighter());
                availableTiles.add(diagTile);
            }
        }
        i = this.getPosition().getX();
        j = this.getPosition().getY();
        isEmpty = true;
        while (i > 0 && j > 0 && isEmpty) {
            i--;
            j--;
            Tile diagTile = field.getByPosition(new Position(i, j));
            if (diagTile.getPiece().getPieceType() != Piece.Type.NIL) {
                isEmpty = false;
                if (diagTile.getPiece().getType() != this.getPiece().getType()) {
                    diagTile.setColor(Color.RED.brighter());
                    availableTiles.add(diagTile);
                }
            } else {
                diagTile.setColor(Color.GREEN.brighter());
                availableTiles.add(diagTile);
            }
        }
        return availableTiles;
    }

    public ArrayList<Tile> getLines(Field field) {
        ArrayList<Tile> availableTiles = new ArrayList<>();
        int i = this.getPosition().getX();
        int j = this.getPosition().getY();
        boolean isEmpty = true;
        while (i > 0 && isEmpty) {
            i--;
            Tile diagTile = field.getByPosition(new Position(i, j));
            if (diagTile.getPiece().getPieceType() != Piece.Type.NIL) {
                isEmpty = false;
                if (diagTile.getPiece().getType() != this.getPiece().getType()) {
                    diagTile.setColor(Color.RED.brighter());
                    availableTiles.add(diagTile);
                }
            } else {
                diagTile.setColor(Color.GREEN.brighter());
                availableTiles.add(diagTile);
            }
        }
        i = this.getPosition().getX();
        j = this.getPosition().getY();
        isEmpty = true;
        while (i < 11 && isEmpty) {
            i++;
            Tile diagTile = field.getByPosition(new Position(i, j));
            if (diagTile.getPiece().getPieceType() != Piece.Type.NIL) {
                isEmpty = false;
                if (diagTile.getPiece().getType() != this.getPiece().getType()) {
                    diagTile.setColor(Color.RED.brighter());
                    availableTiles.add(diagTile);
                }
            } else {
                diagTile.setColor(Color.GREEN.brighter());
                availableTiles.add(diagTile);
            }
        }
        i = this.getPosition().getX();
        j = this.getPosition().getY();
        isEmpty = true;
        while (j > 0 && isEmpty) {
            j--;
            Tile diagTile = field.getByPosition(new Position(i, j));
            if (diagTile.getPiece().getPieceType() != Piece.Type.NIL) {
                isEmpty = false;
                if (diagTile.getPiece().getType() != this.getPiece().getType()) {
                    diagTile.setColor(Color.RED.brighter());
                    availableTiles.add(diagTile);
                }
            } else {
                diagTile.setColor(Color.GREEN.brighter());
                availableTiles.add(diagTile);
            }
        }
        i = this.getPosition().getX();
        j = this.getPosition().getY();
        isEmpty = true;
        while (j < 11 && isEmpty) {
            j++;
            Tile diagTile = field.getByPosition(new Position(i, j));
            if (diagTile.getPiece().getPieceType() != Piece.Type.NIL) {
                isEmpty = false;
                if (diagTile.getPiece().getType() != this.getPiece().getType()) {
                    diagTile.setColor(Color.RED.brighter());
                    availableTiles.add(diagTile);
                }
            } else {
                diagTile.setColor(Color.GREEN.brighter());
                availableTiles.add(diagTile);
            }
        }
        return availableTiles;
    }
}
