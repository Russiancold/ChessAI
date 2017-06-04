import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by allen on 25.05.2017.
 */

public class Game extends JFrame implements Scrollable{
    private static final int CANVAS_WIDTH  = 960;
    private static final int CANVAS_HEIGHT = 960;
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
        canvas.setPreferredSize(new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT));
        Utils.tilesInit(field);

        Container cp = getContentPane();
        cp.add(canvas);

        setPreferredSize(new Dimension(640, 640));

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setTitle("Chess");
        setVisible(true);
        MouseListener mouse = new MouseListener(){

            @Override
            public void mouseClicked(MouseEvent e){
                Point point = MouseInfo.getPointerInfo().getLocation();
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

        this.addMouseListener(mouse);
    }

    @Override
    public Dimension getPreferredScrollableViewportSize() {
        Dimension viewportSize = new Dimension(VISIBLE_ROW_COUNT * TILE_SIZE, VISIBLE_ROW_COUNT * TILE_SIZE);
        return viewportSize;
    }

    @Override
    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 1;
    }

    @Override
    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
        return TILE_SIZE;
    }

    @Override
    public boolean getScrollableTracksViewportWidth() {
        return false;
    }

    @Override
    public boolean getScrollableTracksViewportHeight() {
        return false;
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

    public void changeMovingSide() {
        if(movingSide == Piece.Color.WHITE) {
            movingSide = Piece.Color.BLACK;
        } else {
            movingSide = Piece.Color.WHITE;
        }
    }

    // The entry main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Game();
        });
    }
}
