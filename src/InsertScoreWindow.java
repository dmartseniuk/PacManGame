import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;

public class InsertScoreWindow extends JFrame {
    JLabel labelName;
    JTextField txtName;
    JButton buttonContinue;
    InsertScoreWindow() {

        // Define labels
        labelName = new JLabel("Your score(" + GameManager.points + ") | Enter your name:");
        labelName.setForeground(Color.yellow);

        // Define text fields
        txtName = new JTextField();
        txtName.setPreferredSize(new Dimension(100, 50));
        txtName.setBackground(Color.YELLOW);

        // Setting the frame
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        add(labelName, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        add(txtName, c);

        buttonContinue = new JButton();
        try {
            buttonContinue.setIcon(new ImageIcon(ImageIO.read(new File("buttons/ButtonContinue.png"))));
        } catch (IOException e) {
            buttonContinue.setText("Continue");
        }
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 3;

        ActionListener a = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameManager.highScores.add(new Score(txtName.getText(), GameManager.points));
                Collections.sort(GameManager.highScores, (s1, s2) -> { return s1.getScore() > s2.getScore() ? -1 : s1.getScore() < s2.getScore() ? 1 : 0; });
                Score.saveScoresToFile();
                dispose();
                SwingUtilities.invokeLater(() -> new HighScoresWindow());
            }
        };
        buttonContinue.addActionListener(a);
        add(buttonContinue, c);

        getContentPane().setBackground(Color.BLACK);
        setMinimumSize(new Dimension(400, 300));
        setSize(400, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
