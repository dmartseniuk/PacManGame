import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Pacman extends Character{
    private int lives;
    private int speed = 150; // Move ms sleep (less = faster)
    Pacman(int initialPositionX, int initialPositionY, BoardRenderCell renderCell) {
        super(initialPositionX, initialPositionY, renderCell);
        this.lives = 3;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getLives() {
        return this.lives;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return this.speed;
    }

    @Override
    public void moveWithDirection() {
        int newY = this.getPositionY();
        int newX = this.getPositionX();
        switch (this.direction) {
            case 1 -> newY--;
            case 2 -> newX--;
            case 3 -> newY++;
            case 4 -> newX++;
            default -> {
            }
        }
        move(newX, newY);
        GameManager.consumeCoin(newX, newY);
    }

    public void setControlComponent(JFrame jFrame) {
        jFrame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP -> direction = 1;
                    case KeyEvent.VK_DOWN -> direction = 3;
                    case KeyEvent.VK_LEFT -> direction = 2;
                    case KeyEvent.VK_RIGHT -> direction = 4;
                    default -> {
                    }
                }
            }
        });
    }

}
