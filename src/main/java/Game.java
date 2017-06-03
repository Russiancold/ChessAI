import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

/**
 * Created by allen on 25.05.2017.
 */

public class Game extends JFrame {
    private static final int CANVAS_WIDTH  = 960;
    private static final int CANVAS_HEIGHT = 960;

    private Field field;

    private DrawCanvas canvas;

    public Field getField() {
        return field;
    }

    public Game(){
        field = new Field();
        canvas = new DrawCanvas();
        canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        Utils.tilesInit(field);

        Container cp = getContentPane();
        cp.add(canvas);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setTitle("Chess");
        setVisible(true);
        MouseListener mouse = new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent e){
                Point point = MouseInfo.getPointerInfo().getLocation();
                Position position = Utils.pointToPosition(point);
                if(field.getByPosition(position).getPiece().getPieceType() != Piece.Type.NIL) {
                    field.getByPosition(position).getAvailableTiles(field);
                    repaint();
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
        };

        this.addMouseListener(mouse);
    }

    private class DrawCanvas extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            for(Tile i : field.getField()) {
                g.setColor(i.getColor());
                g.fillRect(i.getPosition().getX() * 80, i.getPosition().getY() * 80, 80, 80);

                if(i.getPiece().getPieceType() != Piece.Type.NIL) {
                    Image img = null;
                    try {
                        img = i.getPiece().getImage();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    g.drawImage(img, i.getPosition().getX() * 80, i.getPosition().getY() * 80, null);
                }
            }
        }
    }

    // The entry main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Game();
        });
    }
}
