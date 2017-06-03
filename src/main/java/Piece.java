import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by allen on 25.05.2017.
 */
public class Piece {
    private String URL = "C:\\Users\\allen\\Documents\\Chess\\src\\main\\resources\\img\\";

    public Color getType() {
        return type;
    }

    public boolean isPawnJumped() {
        return pawnJumped;
    }

    private boolean pawnJumped = false;
    private Color type;
    private Type pieceType;

    public Piece(Position position, Color color, Type piece){
        this.pieceType = piece;
        this.type = color;

        if(color.equals(Color.WHITE)) {
            this.URL = this.URL + "w";
        } else {
            this.URL = URL + "b";
        }

        switch (piece){
            case BISHOP: this.URL = this.URL + "B.png";
                    break;
            case ROOK: this.URL = this.URL + "R.png";
                break;
            case QUEEN: this.URL = this.URL + "Q.png";
                break;
            case KING: this.URL = this.URL + "K.png";
                break;
            case PAWN: this.URL = this.URL + "P.png";
                break;
            case KNIGHT: this.URL = this.URL + "N.png";
                break;
            default: this.URL = "null";
        }
    }

    public enum Color {
        NIL,
        WHITE,
        BLACK
    }

    public enum Type {
        NIL,
        PAWN,
        ROOK,
        KING,
        QUEEN,
        KNIGHT,
        BISHOP
    }



    public Type getPieceType() {
        return pieceType;
    }

    public Image getImage() throws IOException {
        Image img = ImageIO.read(new File(URL));
        return img;
    }
}
