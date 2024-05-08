public class PacmanThread  extends Thread {
    public void run() {
        while(GameManager.gameStart) {
            if(!GameManager.gamePause) {
                GameManager.pacman.moveWithDirection();
                GameManager.checkCollisions();
            }
            try {
                Thread.sleep(GameManager.pacman.getSpeed());
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
