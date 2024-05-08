public class UpgradesSpawningThread extends Thread {
    public void run() {
        while(GameManager.gameStart) {
            if(!GameManager.gamePause) {
                for (int i = 0; i < GameManager.ghosts.length; i++) {
                    // Drop upgrade 25% chance
                    if ((int) ((Math.random() * (5 - 1)) + 1) == 1) {
                        GameManager.spawnUpgrade(GameManager.ghosts[i].getPositionX(), GameManager.ghosts[i].getPositionY());
                    }
                }
            }
            try {
                Thread.sleep(5000);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}