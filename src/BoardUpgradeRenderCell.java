import java.awt.*;

public class BoardUpgradeRenderCell extends BoardRenderCell {

    BoardUpgradeRenderCell(Color color) {
        super(color);
    }

    @Override
    public void setSize(int width, int height) {
        super.setSize(width, height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0,0,width,height);
        g.setColor(this.color);
        g.fillOval(width/2-width/4,height/2-height/4,width/2,height/2);
    }
}