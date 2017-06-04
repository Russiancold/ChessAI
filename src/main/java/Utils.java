import java.awt.*;
import java.awt.geom.Arc2D;

/**
 * Created by allen on 26.05.2017.
 */
public final class Utils {
    public static void tilesInit(Field field) {
        Color color = Color.gray;
        Piece piece = null;
        Piece.Color pieceColor;
        Tile tile;
        for(int i = 0; i < 12; i++) {
            if(color == Color.gray) {
                color = Color.white;
            } else { color = Color.gray;}
            for(int j = 0; j < 12; j++) {
                tile = new Tile(new Position(i, j), field, color);
                switch (j) {
                    case 2: {
                        pieceColor = Piece.Color.BLACK;
                        switch (i) {
                            case 0: piece = new Piece(Piece.Color.NIL, Piece.Type.NIL);
                                break;
                            case 1: piece = new Piece(Piece.Color.NIL, Piece.Type.NIL);
                                break;
                            case 10: piece = new Piece(Piece.Color.NIL, Piece.Type.NIL);
                                break;
                            case 11: piece = new Piece(Piece.Color.NIL, Piece.Type.NIL);
                                break;
                            case 2: piece = new Piece(pieceColor, Piece.Type.ROOK);
                            break;
                            case 3: piece = new Piece(pieceColor, Piece.Type.KNIGHT);
                            break;
                            case 4: piece = new Piece(pieceColor, Piece.Type.BISHOP);
                            break;
                            case 5: piece = new Piece(pieceColor, Piece.Type.QUEEN);
                            break;
                            case 6: piece = new Piece(pieceColor, Piece.Type.KING);
                            break;
                            case 7: piece = new Piece(pieceColor, Piece.Type.BISHOP);
                            break;
                            case 8: piece = new Piece(pieceColor, Piece.Type.KNIGHT);
                            break;
                            case 9: piece = new Piece(pieceColor, Piece.Type.ROOK);
                            break;
                        }
                        break;
                    }
                    case 3: {
                        if(i != 0 && i != 1 && i != 10 && i != 11) {
                            pieceColor = Piece.Color.BLACK;
                            piece = new Piece(pieceColor, Piece.Type.PAWN);
                        }
                        break;
                    }
                    case 9: {
                        pieceColor = Piece.Color.WHITE;
                        switch (i) {
                            case 0: piece = new Piece(Piece.Color.NIL, Piece.Type.NIL);
                                break;
                            case 1: piece = new Piece(Piece.Color.NIL, Piece.Type.NIL);
                                break;
                            case 10: piece = new Piece(Piece.Color.NIL, Piece.Type.NIL);
                                break;
                            case 11: piece = new Piece(Piece.Color.NIL, Piece.Type.NIL);
                                break;
                            case 2: piece = new Piece(pieceColor, Piece.Type.ROOK);
                            break;
                            case 3: piece = new Piece(pieceColor, Piece.Type.KNIGHT);
                            break;
                            case 4: piece = new Piece(pieceColor, Piece.Type.BISHOP);
                            break;
                            case 5: piece = new Piece(pieceColor, Piece.Type.QUEEN);
                            break;
                            case 6: piece = new Piece(pieceColor, Piece.Type.KING);
                            break;
                            case 7: piece = new Piece(pieceColor, Piece.Type.BISHOP);
                            break;
                            case 8: piece = new Piece(pieceColor, Piece.Type.KNIGHT);
                            break;
                            case 9: piece = new Piece(pieceColor, Piece.Type.ROOK);
                            break;
                        }
                        break;
                    }
                    case 8: {
                        if(i != 0 && i != 1 && i != 10 && i != 11) {
                            pieceColor = Piece.Color.WHITE;
                            piece = new Piece(pieceColor, Piece.Type.PAWN);
                        }
                        break;
                    }
                    case 4: piece = new Piece(Piece.Color.NIL, Piece.Type.NIL);
                        break;
                    case 5: piece = new Piece(Piece.Color.NIL, Piece.Type.NIL);
                        break;
                    case 6: piece = new Piece(Piece.Color.NIL, Piece.Type.NIL);
                        break;
                    case 7: piece = new Piece(Piece.Color.NIL, Piece.Type.NIL);
                        break;
                    case 0: piece = new Piece(Piece.Color.NIL, Piece.Type.NIL);
                        break;
                    case 1: piece = new Piece(Piece.Color.NIL, Piece.Type.NIL);
                        break;
                    case 10: piece = new Piece(Piece.Color.NIL, Piece.Type.NIL);
                        break;
                    case 11: piece = new Piece(Piece.Color.NIL, Piece.Type.NIL);
                        break;
                }
                if(color == Color.gray) {
                    color = Color.white;
                } else { color = Color.gray;}
                tile.setPiece(piece);
            }
        }
    }

    public static Position pointToPosition(Point mouse) {
        return new Position(((int) mouse.getX() - 10) / 80, ((int) mouse.getY() - 30) / 80);
    }
}
