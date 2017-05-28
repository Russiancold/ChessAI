import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by allen on 26.05.2017.
 */
public class Field {
    private ArrayList<Tile> field = new ArrayList<>();

    public void removePiece(Piece piece) {
        field.remove(piece);
    }

    public void addTile(Tile tile) {
        field.add(tile);
    }
}
