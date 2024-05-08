import javax.swing.*;

public class StatPanelThread extends Thread {
    JLabel pointsLabel, livesLabel;
    StatPanelThread(JLabel pointsLabel, JLabel livesLabel) {
        this.pointsLabel = pointsLabel;
        this.livesLabel = livesLabel;
    }
    public void run(){
        while(GameManager.gameStart) {
            if(!GameManager.gamePause) {
                this.pointsLabel.setText("Points: " + GameManager.points);
                this.livesLabel.setText("Lives: " + GameManager.pacman.getLives());
            }
            try {
                Thread.sleep(100);
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}