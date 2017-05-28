import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by allen on 25.05.2017.
 */

public class Game extends JFrame {
    private static final int CANVAS_WIDTH  = 640;
    private static final int CANVAS_HEIGHT = 640;

    private Field field = new Field();

    private DrawCanvas canvas;

    public Field getField() {
        return field;
    }

    public Game() {
        field = new Field();
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
            Utils.tilesInit(g, field);
            Utils.piecesInit(g);
        }
    }

    // The entry main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Game();
        });
    }
}
