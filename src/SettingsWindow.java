import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class SettingsWindow extends JFrame {
    private static int columnsNumber, rowsNumber;
    JLabel labelColumns, labelRows, labelX;
    JTextField txtColumns, txtRows;
    JButton buttonContinue;
    SettingsWindow() {

        // Define labels
        labelColumns = new JLabel("Columns: ");
        labelColumns.setForeground(Color.yellow);
        labelRows = new JLabel("Rows: ");
        labelRows.setForeground(Color.yellow);
        labelX = new JLabel("X");
        labelX.setForeground(Color.yellow);

        // Define text fields
        txtColumns = new JTextField();
        txtRows = new JTextField();
        txtColumns.setPreferredSize(new Dimension(100, 50));
        txtRows.setPreferredSize(new Dimension(100, 50));
        txtRows.setBackground(Color.YELLOW);
        txtColumns.setBackground(Color.YELLOW);

        // Setting the frame
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        add(labelColumns, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 0;
        add(labelRows, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        add(txtColumns, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        add(labelX, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 1;
        add(txtRows, c);

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
                try {
                    columnsNumber = Integer.parseInt(txtColumns.getText());
                    rowsNumber = Integer.parseInt(txtRows.getText());
                } catch (NumberFormatException n) {
                    JOptionPane.showMessageDialog(null, "Enter the number", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (columnsNumber < 10 || columnsNumber > 100) {
                    JOptionPane.showMessageDialog(null, "The number of columns should be between 10 and 100. Please enter the correct number!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (rowsNumber < 10 || rowsNumber > 100) {
                    JOptionPane.showMessageDialog(null, "The number of rows should be between 10 and 100. Please enter the correct number!", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (txtColumns.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Enter number of columns (10-100)", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (txtRows.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Enter number of rows (10-100)", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (txtColumns.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Enter number of columns (10-100)", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                dispose();
                GameManager.rows = rowsNumber;
                GameManager.columns = columnsNumber;
                SwingUtilities.invokeLater(() -> new GameWindow());
            }
        };
        buttonContinue.addActionListener(a);
        add(buttonContinue, c);

        getContentPane().setBackground(Color.BLACK);
        setMinimumSize(new Dimension(400, 200));
        setSize(400, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setLocationRelativeTo(null);
    }
}
