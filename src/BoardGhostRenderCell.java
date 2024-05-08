import java.awt.*;
public class BoardGhostRenderCell extends BoardRenderCell {
    int mouthTop, mouthBottom;
    int eyesVariationX = 1;
    int eyesVariationY = 1;

    BoardGhostRenderCell(Color color) {
        super(color);
    }

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
        // Ghost body
        g.setColor(this.color);
        g.fillOval(0,0,width,height);
        g.setColor(Color.BLACK);
        g.fillRect(0, height/2+10, width, height/2);
        g.setColor(this.color);
        int[] x = {0,        0,      width/4,  width/2, width-width/4, width, width};
        int[] y = {height/2, height, height/2, height, height/2,        height, height/2};
        g.fillPolygon(x, y, 7);

        // Ghost eyes
        g.setColor(Color.WHITE);
        g.fillOval(width/6,height/4,width/3,height/3);
        g.fillOval(width/2,height/4,width/3,height/3);
        g.setColor(Color.BLACK);
        g.fillOval(width/6 + width/10 +this.eyesVariationX,height/4+(height/20)+this.eyesVariationY,width/5,height/5);
        g.fillOval(width/2 + width/10 +this.eyesVariationX,height/4+(height/20)+this.eyesVariationY,width/5,height/5);
    }

    @Override
    public void animate() {
        this.eyesVariationX = (int) ((Math.random() * (4 + 4)) - 4);
        this.eyesVariationY = (int) ((Math.random() * (4 + 4)) - 4);
    }
}

