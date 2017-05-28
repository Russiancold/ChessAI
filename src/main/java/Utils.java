import java.awt.*;

/**
 * Created by allen on 26.05.2017.
 */
public final class Utils {
    public static void tilesInit(Graphics g, Field field) {
        Color color = Color.white;
        Piece piece = null;
        Piece.Color pieceColor;
        Tile tile;
        for(int i = 0; i < 8; i++) {
            if(color == Color.gray) {
                color = Color.white;
            } else { color = Color.gray;}
            for(int j = 0; j < 8; j++) {
                field.addTile(tile = new Tile(new Position(i, j), field, color, g));
                switch (j) {
                    case 0: {
                        pieceColor = Piece.Color.BLACK;
                        switch (i) {
                            case 0: piece = new Piece(new Position(i, j), pieceColor, Piece.Type.ROOK, g);
                            break;
                            case 1: piece = new Piece(new Position(i, j), pieceColor, Piece.Type.KNIGHT, g);
                            break;
                            case 2: piece = new Piece(new Position(i, j), pieceColor, Piece.Type.BISHOP, g);
                            break;
                            case 3: piece = new Piece(new Position(i, j), pieceColor, Piece.Type.KING, g);
                            break;
                            case 4: piece = new Piece(new Position(i, j), pieceColor, Piece.Type.QUEEN, g);
                            break;
                            case 5: piece = new Piece(new Position(i, j), pieceColor, Piece.Type.BISHOP, g);
                            break;
                            case 6: piece = new Piece(new Position(i, j), pieceColor, Piece.Type.KNIGHT, g);
                            break;
                            case 7: piece = new Piece(new Position(i, j), pieceColor, Piece.Type.ROOK, g);
                            break;
                        }
                        break;
                    }
                    case 1: {
                        pieceColor = Piece.Color.BLACK;
                        piece = new Piece(new Position(i, j), pieceColor, Piece.Type.PAWN, g);
                        break;
                    }
                    case 7: {
                        pieceColor = Piece.Color.WHITE;
                        switch (i) {
                            case 0: piece = new Piece(new Position(i, j), pieceColor, Piece.Type.ROOK, g);
                            break;
                            case 1: piece = new Piece(new Position(i, j), pieceColor, Piece.Type.KNIGHT, g);
                            break;
                            case 2: piece = new Piece(new Position(i, j), pieceColor, Piece.Type.BISHOP, g);
                            break;
                            case 3: piece = new Piece(new Position(i, j), pieceColor, Piece.Type.KING, g);
                            break;
                            case 4: piece = new Piece(new Position(i, j), pieceColor, Piece.Type.QUEEN, g);
                            break;
                            case 5: piece = new Piece(new Position(i, j), pieceColor, Piece.Type.BISHOP, g);
                            break;
                            case 6: piece = new Piece(new Position(i, j), pieceColor, Piece.Type.KNIGHT, g);
                            break;
                            case 7: piece = new Piece(new Position(i, j), pieceColor, Piece.Type.ROOK, g);
                            break;
                        }
                        break;
                    }
                    case 6: {
                        pieceColor = Piece.Color.WHITE;
                        piece = new Piece(new Position(i, j), pieceColor, Piece.Type.PAWN, g);
                        break;
                    }
                    default: new Piece(new Position(i, j), Piece.Color.NIL, Piece.Type.NIL, g);
                }
                if(color == Color.gray) {
                    color = Color.white;
                } else { color = Color.gray;}
                tile.setPiece(piece);
            }
        }
    }
}
