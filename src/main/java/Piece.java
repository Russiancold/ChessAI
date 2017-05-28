import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by allen on 25.05.2017.
 */
public class Piece {
    private String URL = "C:\\Users\\allen\\Documents\\Chess\\src\\main\\resources\\img\\";

    private Position position;
    private Color type;
    private Type pieceType;
    private Field field;

    public Piece(Position position, Color color, Type piece, Graphics g){
        this.position = position;
        this.field = field;

        if(color.equals(Color.WHITE)) {
            this.type = Color.WHITE;
            this.URL = this.URL + "w";
        } else {
            this.type = Color.BLACK;
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
        }

        Image img = null;

        try {
            img = this.getImage();
        } catch (IOException e) {
            e.printStackTrace();
        }

        g.drawImage(img, position.getX() * 80, position.getY() * 80, null);
    }

    public enum Color {
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
