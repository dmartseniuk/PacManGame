public class GhostsThread extends Thread {
    public void run() {
        while(GameManager.gameStart) {
            if(!GameManager.gamePause) {
                for (int i = 0; i < GameManager.ghosts.length; i++) {
                    GameManager.ghosts[i].moveWithDirection();
                    // Switch direction 10% chance
                    if ((int) ((Math.random() * (10 - 1)) + 1) == 1) {
                        GameManager.ghosts[i].setRandomDirection();
                    }
                }
            }
            try {
                Thread.sleep(200);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
