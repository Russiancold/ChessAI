import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by allen on 26.05.2017.
 */
public class Field extends JFrame{
    private static final int CANVAS_WIDTH  = 640;
    private static final int CANVAS_HEIGHT = 640;

    private ArrayList<Tile> field = new ArrayList<>();

    public void remove(Piece piece) {
        field.remove(piece);
    }

    DrawCanvas canvas;

    public Field() {
        canvas = new DrawCanvas();
        canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));

        Container cp = getContentPane();
        cp.add(canvas);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setTitle("Chess");
        setVisible(true);
    }

    private class DrawCanvas extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            g.setColor(Color.gray);
            for(int i = 0; i < 8; i++) {
                if(g.getColor() == Color.gray) {
                    g.setColor(Color.white);
                } else { g.setColor(Color.gray);}
                for(int j = 0; j < 8; j++) {
                    g.fillRect(i * 80, j * 80, 80, 80);
                    if(g.getColor() == Color.gray) {
                        g.setColor(Color.white);
                    } else { g.setColor(Color.gray);}
                }
            }
            Utils.piecesInit(g);
        }
    }

    public void addTile(Tile tile) {
        field.add(tile);
    }
}
