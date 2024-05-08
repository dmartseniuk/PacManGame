import javax.swing.*;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        Score.readScoresFromFile();
        SwingUtilities.invokeLater(() -> new MenuWindow());
    }
}