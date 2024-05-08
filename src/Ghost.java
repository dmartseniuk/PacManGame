public class Ghost extends Character {
    Ghost(int initialPositionX, int initialPositionY, BoardRenderCell renderCell) {
        super(initialPositionX, initialPositionY, renderCell);
        // set random direction for ghost
        setRandomDirection();
    }

    @Override
    public void move(int x, int y) {
        if(GameManager.isFieldAccessible(x, y)) {
            positionX = x;
            positionY = y;
        }
        else {
            setRandomDirection();
        }
    }
    public void setRandomDirection() {
        int newDirection = (int) ((Math.random() * (4)) + 1);
        while(newDirection == this.direction) {
            newDirection = (int) ((Math.random() * (4)) + 1);
        }
        this.direction = newDirection;
    }
}
