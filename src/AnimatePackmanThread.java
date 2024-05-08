public class AnimatePackmanThread extends Thread {
    public void run() {
        while(GameManager.gameStart) {
            if(!GameManager.gamePause) {
                GameManager.pacman.getRenderCell().animate();
            }
            try {
                Thread.sleep(GameManager.pacman.getSpeed()/2);
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
