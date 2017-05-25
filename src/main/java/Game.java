import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by allen on 25.05.2017.
 */
/** Custom Drawing Code Template */
// A Swing application extends javax.swing.JFrame
public class Game extends JFrame {
    // Define constants
    public static final int CANVAS_WIDTH  = 640;
    public static final int CANVAS_HEIGHT = 640;

    // Declare an instance of the drawing canvas,
    // which is an inner class called DrawCanvas extending javax.swing.JPanel.
    private DrawCanvas canvas;

    // Constructor to set up the GUI components and event handlers
    public Game() {
        canvas = new DrawCanvas();    // Construct the drawing canvas
        canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));

        // Set the Drawing JPanel as the JFrame's content-pane
        Container cp = getContentPane();
        cp.add(canvas);
        // or "setContentPane(canvas);"

        setDefaultCloseOperation(EXIT_ON_CLOSE);   // Handle the CLOSE button
        pack();              // Either pack() the components; or setSize()
        setTitle("......");  // "super" JFrame sets the title
        setVisible(true);    // "super" JFrame show
        setTitle("Chess");
    }

    /**
     * Define inner class DrawCanvas, which is a JPanel used for custom drawing.
     */
    private class DrawCanvas extends JPanel {
        // Override paintComponent to perform your own painting
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);     // paint parent's background
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
            for(int i = 0; i < 8; i++) {
                new Piece(new Position(i, 1), Piece.Color.BLACK, "Pawn", g);
                new Piece(new Position(i, 6), Piece.Color.WHITE, "Pawn", g);
            }
        }
    }

    // The entry main method
    public static void main(String[] args) {
        // Run the GUI codes on the Event-Dispatching thread for thread safety
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Game(); // Let the constructor do the job
            }
        });
    }
}
