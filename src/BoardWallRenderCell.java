import java.awt.*;

public class BoardWallRenderCell extends BoardRenderCell{

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        g.fillRect(0,0,width,height);
    }
}
