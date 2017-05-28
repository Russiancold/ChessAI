import java.awt.*;

/**
 * Created by allen on 26.05.2017.
 */
public final class Utils {
    public static void tilesInit(Graphics g, Field field) {
        Color color = Color.white;
        for(int i = 0; i < 8; i++) {
            if(color == Color.gray) {
                color = Color.white;
            } else { color = Color.gray;}
            for(int j = 0; j < 8; j++) {
                field.addTile(new Tile(new Position(i, j), field, color, g));
                if(color == Color.gray) {
                    color = Color.white;
                } else { color = Color.gray;}
            }
        }
    }

    public static void piecesInit(Graphics g){
        for(int i = 0; i < 8; i++) {
            new Piece(new Position(i, 1), Piece.Color.BLACK, Piece.Type.PAWN, g);
            new Piece(new Position(i, 6), Piece.Color.WHITE, Piece.Type.PAWN, g);
        }
        for(int i = 0; i < 5; i++) {
            switch (i) {
                case 0: {
                    new Piece(new Position(0, 0), Piece.Color.BLACK, Piece.Type.ROOK, g);
                    new Piece(new Position(7, 0), Piece.Color.BLACK, Piece.Type.ROOK, g);
                    new Piece(new Position(0, 7), Piece.Color.WHITE, Piece.Type.ROOK, g);
                    new Piece(new Position(7, 7), Piece.Color.WHITE, Piece.Type.ROOK, g);
                    break;
                }
                case 1: {
                    new Piece(new Position(1, 0), Piece.Color.BLACK, Piece.Type.KNIGHT, g);
                    new Piece(new Position(6, 0), Piece.Color.BLACK, Piece.Type.KNIGHT, g);
                    new Piece(new Position(1, 7), Piece.Color.WHITE, Piece.Type.KNIGHT, g);
                    new Piece(new Position(6, 7), Piece.Color.WHITE, Piece.Type.KNIGHT, g);
                    break;
                }
                case 2: {
                    new Piece(new Position(2, 0), Piece.Color.BLACK, Piece.Type.BISHOP, g);
                    new Piece(new Position(5, 0), Piece.Color.BLACK, Piece.Type.BISHOP, g);
                    new Piece(new Position(2, 7), Piece.Color.WHITE, Piece.Type.BISHOP, g);
                    new Piece(new Position(5, 7), Piece.Color.WHITE, Piece.Type.BISHOP, g);
                    break;
                }
                case 3: {
                    new Piece(new Position(3, 0), Piece.Color.BLACK, Piece.Type.KING, g);
                    new Piece(new Position(3, 7), Piece.Color.WHITE, Piece.Type.KING, g);
                    break;
                }
                case 4: {
                    new Piece(new Position(4, 0), Piece.Color.BLACK, Piece.Type.QUEEN, g);
                    new Piece(new Position(4, 7), Piece.Color.WHITE, Piece.Type.QUEEN, g);
                    break;
                }
            }
        }
    }
}
