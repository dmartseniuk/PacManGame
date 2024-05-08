import java.awt.*;

public class BoardCoinRenderCell extends BoardRenderCell{
    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0,0,width,height);
        g.setColor(Color.YELLOW);
        g.fillRect(width/2-width/8,height/2-height/8,width/4,height/4);
    }
}
