import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by allen on 26.05.2017.
 */
public class Field {
    private ArrayList<Tile> field = new ArrayList<>();

    public ArrayList<Tile> getField() {
        return field;
    }

    public Tile getByPosition(Position position){
        for(Tile i : field) {
            if(i.getPosition().equals(position)) {
                return i;
            }
        }
        return null;
    }

    public Tile getByPosition(int x, int y){
        Position position = new Position(x, y);
        for(Tile i : field) {
            if(i.getPosition().equals(position)) {
                return i;
            }
        }
        return null;
    }

    public void addTile(Tile tile) {
        field.add(tile);
    }
}
