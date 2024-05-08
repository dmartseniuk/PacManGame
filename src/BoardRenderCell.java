import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class BoardRenderCell extends JComponent {

    protected int width, height;
    protected Color color;

    BoardRenderCell() {
        this.color = Color.YELLOW;
    }
    BoardRenderCell(Color color) {
        this.color = color;
    }

    public void animate() {};

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
        repaint();
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0,0,width,height);
    }
}