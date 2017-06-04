import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by allen on 25.05.2017.
 */

public class Game extends JFrame{
    private static final int PREFERED_ROW_COUNT = 12;
    private static final int VISIBLE_ROW_COUNT = 8;
    private static final int TILE_SIZE = 80;

    private Field field;

    private DrawCanvas canvas;

    public Field getField() {
        return field;
    }

    private boolean isMoving = false;
    private Piece movingPiece;
    private Tile moveStart;
    private Piece.Color movingSide = Piece.Color.WHITE;
    private ArrayList<Tile> availableTiles = new ArrayList<>();

    public Game(){
        field = new Field();
        canvas = new DrawCanvas();
        JScrollPane scrollPaneCanvas = new JScrollPane(canvas);
        scrollPaneCanvas.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPaneCanvas.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        setLayout(new BorderLayout());
        canvas.setPreferredSize(new Dimension(PREFERED_ROW_COUNT * TILE_SIZE, PREFERED_ROW_COUNT * TILE_SIZE));
        Utils.tilesInit(field);

        Container cp = getContentPane();
        cp.add(scrollPaneCanvas);
        int x = 2 * TILE_SIZE;
        Point pt = new Point(x, x);
        scrollPaneCanvas.getViewport().setViewPosition(pt);

        setPreferredSize(new Dimension(VISIBLE_ROW_COUNT * TILE_SIZE + 16, VISIBLE_ROW_COUNT * TILE_SIZE + 42));

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setTitle("Chess");
        setLocationRelativeTo(null);
        setVisible(true);
        MouseListener mouse = new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent e){
                Point point = new Point(e.getX(), e.getY());
                Position position = Utils.pointToPosition(point);
                if(!isMoving && !field.getByPosition(position).isEmpty()
                        && field.getByPosition(position).getPiece().getType() == movingSide) {
                    isMoving = true;
                    movingPiece = field.getByPosition(position).getPiece();
                    moveStart = field.getByPosition(position);
                    availableTiles = field.getByPosition(position).getAvailableTiles(field);
                    repaint();
                    return;
                }
                if(isMoving) {
                    if(availableTiles.contains(field.getByPosition(position))) {
                            moveStart.removePiece();
                            field.getByPosition(position).addPiece(movingPiece);
                            for (Tile i : availableTiles) {
                                isMoving = false;
                                i.setColor(i.getDefaultColor());
                            }
                            changeMovingSide();
                            field.getByPosition(position).jump();
                            repaint();
                            return;
                    } else {
                        isMoving = false;
                        for (Tile i : availableTiles) {
                            i.setColor(i.getDefaultColor());
                        }
                        if(field.getByPosition(position).getPiece().getType() == movingSide){
                            isMoving = true;
                            movingPiece = field.getByPosition(position).getPiece();
                            moveStart = field.getByPosition(position);
                            availableTiles = field.getByPosition(position).getAvailableTiles(field);
                        }
                        repaint();
                        return;
                    }
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

        canvas.addMouseListener(mouse);
    }

    private class DrawCanvas extends JPanel implements Scrollable{
        @Override
        public void paintComponent(Graphics g) {
            for(Tile i : field.getField()) {
                g.setColor(i.getColor());
                g.fillRect(i.getPosition().getX() * TILE_SIZE, i.getPosition().getY() * TILE_SIZE, TILE_SIZE, TILE_SIZE);

                if(i.getPiece().getPieceType() != Piece.Type.NIL) {
                    Image img = null;
                    try {
                        img = i.getPiece().getImage();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    g.drawImage(img, i.getPosition().getX() * TILE_SIZE, i.getPosition().getY() * TILE_SIZE, null);
                }
            }
        }

        @Override
        public Dimension getPreferredScrollableViewportSize() {
            Dimension viewportSize = new Dimension(PREFERED_ROW_COUNT * TILE_SIZE, PREFERED_ROW_COUNT * TILE_SIZE);
            return viewportSize;
        }

        @Override
        public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation,
                                               int direction) {
            return TILE_SIZE;
        }

        @Override
        public boolean getScrollableTracksViewportHeight() {
            return false;
        }

        @Override
        public boolean getScrollableTracksViewportWidth() {
            return false;
        }

        @Override
        public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation,
                                              int direction) {
            return 1;
        }

    }

    public void changeMovingSide() {
        if(movingSide == Piece.Color.WHITE) {
            movingSide = Piece.Color.BLACK;
        } else {
            movingSide = Piece.Color.WHITE;
        }
        field.incCounter();
    }

    // The entry main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Game();
        });
    }
}
