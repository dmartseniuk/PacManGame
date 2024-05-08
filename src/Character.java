import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Character extends GameEntity {
    Character(int initialPositionX, int initialPositionY, BoardRenderCell renderCell) {
        super(initialPositionX, initialPositionY, renderCell);
    }

    public void setDirection(int direction) {
        if(direction > 4)
            this.direction = 4;
        if(direction<1)
            this.direction = 1;
        this.direction = direction;
    }

    public void moveWithDirection() {
        int newY = this.getPositionY();
        int newX = this.getPositionX();
        switch (this.direction) {
            case 1:
                newY--;
                break;
            case 2:
                newX--;
                break;
            case 3:
                newY++;
                break;
            case 4:
                newX++;
                break;
            default:
                break;
        }
        move(newX, newY);
    }
}
