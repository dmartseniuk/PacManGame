public class AnimateGhostsThread extends Thread {
    public void run(){
        while(GameManager.gameStart) {
            if(!GameManager.gamePause) {
                for(int i = 0; i<GameManager.ghosts.length; i++) {
                    GameManager.ghosts[i].getRenderCell().animate();
                }
            }

            try {
                Thread.sleep(200);
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}