public class GameEntity {
    protected int positionX, positionY, direction = 1;
    // direction 1 - North, 2 - East, 3 - South, 4 - West
    protected BoardRenderCell renderCell;

    GameEntity(int initialPositionX, int initialPositionY, BoardRenderCell renderCell) {
        this.positionX = initialPositionX;
        this.positionY = initialPositionY;
        this.renderCell = renderCell;
    }

    public int getPositionX() {
        return this.positionX;
    }

    public int getPositionY() {
        return  this.positionY;
    }

    public void move(int x, int y) {
        if(GameManager.isFieldAccessible(x, y)) {
            positionX = x;
            positionY = y;
        }
    }
    public BoardRenderCell getRenderCell() {
        return this.renderCell;
    }
}
