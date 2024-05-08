import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class HighScoresWindow  extends JFrame {
    HighScoresWindow() {

        // Setting the frame
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Define buttons
        JButton buttonExit;
        JList scoresList;

        buttonExit = new JButton();
        try {
            buttonExit.setIcon(new ImageIcon(ImageIO.read(new File("buttons/MainMenuButton.png"))));
        } catch (IOException e) {
            buttonExit.setText("Main Menu");
        }
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        add(buttonExit, c);

        String scores[] = Score.getScoresStringList();

        scoresList = new JList(scores);
        scoresList.setBackground(Color.YELLOW);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        add(scoresList, c);

        ActionListener redirectMenu = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SwingUtilities.invokeLater(() -> new MenuWindow());
            }
        };
        buttonExit.addActionListener(redirectMenu);

        getContentPane().setBackground(Color.BLACK);
        setMinimumSize(new Dimension(400, 300));
        setSize(400, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
