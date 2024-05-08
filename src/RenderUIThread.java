import javax.swing.*;

public class RenderUIThread extends Thread {
    JComponent jcomponent;
    RenderUIThread(JComponent jcomponent) {
        this.jcomponent = jcomponent;
    }
    public void run(){
        while(GameManager.gameStart) {
            if(!GameManager.gamePause) {
                jcomponent.repaint();
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