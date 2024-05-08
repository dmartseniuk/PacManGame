import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MenuWindow extends JFrame {
    MenuWindow() {

        // Setting the frame
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        // Define buttons
        JButton buttonNewGame, buttonHighScores, buttonExit;

        buttonNewGame = new JButton();
        try {
            buttonNewGame.setIcon(new ImageIcon(ImageIO.read(new File("buttons/NewGameButton.png"))));
        } catch (IOException e) {
            buttonNewGame.setText("New Game");
        }
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        add(buttonNewGame, c);

        buttonHighScores = new JButton();
        try {
            buttonHighScores.setIcon(new ImageIcon(ImageIO.read(new File("buttons/HighScoresButton.png"))));
        } catch (IOException e) {
            buttonHighScores.setText("High Scores");
        }
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        add(buttonHighScores, c);

        buttonExit = new JButton();
        try {
            buttonExit.setIcon(new ImageIcon(ImageIO.read(new File("buttons/ExitButton.png"))));
        } catch (IOException e) {
            buttonExit.setText("Exit");
        }
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        add(buttonExit, c);

        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SwingUtilities.invokeLater(() -> new SettingsWindow());
            }
        };
        ActionListener redirectHighScores = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SwingUtilities.invokeLater(() -> new HighScoresWindow());
            }
        };
        ActionListener exitAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                System.exit(0);
            }
        };
        buttonNewGame.addActionListener(a);
        buttonHighScores.addActionListener(redirectHighScores);
        buttonExit.addActionListener(exitAction);

        getContentPane().setBackground(Color.BLACK);
        setMinimumSize(new Dimension(400, 300));
        setSize(400, 300);
        setVisible(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
