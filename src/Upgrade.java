import java.util.function.Consumer;

public class Upgrade extends GameEntity {
    final private Consumer onCollect;
    Upgrade(int initialPositionX, int initialPositionY, BoardRenderCell renderCell, Consumer onCollect) {
        super(initialPositionX, initialPositionY, renderCell);
        this.onCollect = onCollect;
    }

    public void callOnCollect() {
        onCollect.accept(null);
    }
}
