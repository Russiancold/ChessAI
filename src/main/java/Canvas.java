import javax.swing.*;

/**
 * Created by allen on 28.05.2017.
 */
public class Canvas extends java.awt.Canvas{
    JFrame frame;
    public Canvas() {
        frame = new JFrame("Chess");
        frame.setSize(640, 640);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
