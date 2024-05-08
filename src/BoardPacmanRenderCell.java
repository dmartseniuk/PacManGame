import java.awt.*;

public class BoardPacmanRenderCell extends BoardRenderCell {
    int mouthTop, mouthBottom;
    boolean opening = false;

    @Override
    public void setSize(int width, int height) {
        if(width!=this.width || height!=this.height) {
            this.mouthTop = 0;
            this.mouthBottom = height;
        }
        super.setSize(width, height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0,0,width,height);
        g.setColor(this.color);
        g.fillOval(0,0,width,height);
        g.setColor(Color.BLACK);
        int[] x = {width/2, width, width};
        int[] y = {height/2, this.mouthTop, this.mouthBottom};
        g.fillPolygon(x, y, 3);
    }

    @Override
    public void animate() {
        if(this.mouthTop > 0 && !this.opening) {
            this.mouthTop -= 5;
            this.mouthBottom += 5;
        }
        else if(this.mouthTop < this.height && this.opening) {
            this.mouthTop +=5;
            this.mouthBottom -=5;
        }
        else {
            this.opening = !this.opening;
        }

    }
}
