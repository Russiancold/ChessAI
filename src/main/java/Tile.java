import java.awt.*;
import java.awt.event.*;

/**
 * Created by allen on 26.05.2017.
 */
public class Tile implements java.awt.event.MouseListener{
    private Position position;
    private Piece piece;
    private Field field;

    public Tile(Position position, Field field, Color color, Graphics g) {
        this.position = position;
        this.field = field;
        g.setColor(color);
        g.fillRect(position.getX() * 80, position.getY() * 80, 80, 80);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(piece.getPieceType() == Piece.Type.NIL){
            return;
        } else {
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
