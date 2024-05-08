import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameWindow extends JFrame {
    GameWindow() {
        // Define game variables
        GameManager.setupGame();
        GameManager.gameWindow = this;

        // Setup key listeners
        GameManager.pacman.setControlComponent(this);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.isShiftDown() && e.isControlDown() && e.getKeyCode() == 81)
                    GameManager.gamePause = !GameManager.gamePause;
            }
        });


        BoardTable j = new BoardTable(new BoardTableModel(GameManager.board));
        j.setBackground(Color.BLACK);
        j.setCellSelectionEnabled(false);
        j.setFocusable(false);
        j.setShowGrid(false);
        j.setRowMargin(0);
        j.setIntercellSpacing(new Dimension(0, 0));
        j.setSize(new Dimension(GameManager.rows*20, GameManager.columns*20));

        j.addComponentListener(new ComponentAdapter(){

            @Override
            public void componentResized(ComponentEvent e){
                //Get new JTable component size
                Dimension size = j.getSize();
                int cellSize;
                //Check if height or width is the limiting factor and set cell size accordingly
                if (size.height / GameManager.rows > size.width / GameManager.columns){
                    cellSize = size.width / GameManager.columns;
                }
                else{
                    cellSize = size.height / GameManager.rows;
                }
                //Set new row height to our new size
                j.setRowHeight(cellSize);
                //Set new column width to our new size
                for (int i = 0; i < j.getColumnCount(); i++){
                    j.getColumnModel().getColumn(i).setMaxWidth(cellSize);
                    j.getColumnModel().getColumn(i).setMinWidth(cellSize);
                }
            }
        });

        // Defining UI components
        // Define stat panel
        JPanel statPanel = new JPanel();
        statPanel.setBackground(Color.YELLOW);

        // Define labels
        JLabel livesLabel = new JLabel("Lives: " + GameManager.pacman.getLives());
        JLabel pointsLabel = new JLabel("Points: " + GameManager.points);

        statPanel.add(livesLabel);
        statPanel.add(pointsLabel);

        // Setting the frame
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        add(statPanel, c);

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.weighty=1;
        c.weightx=1;
        add(j, c);

        getContentPane().setBackground(Color.BLACK);

        setSize(new Dimension(GameManager.columns*30, GameManager.rows*30));

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        RenderUIThread renderUIThread = new RenderUIThread(j);
        StatPanelThread updateStatPanelThread = new StatPanelThread(livesLabel, pointsLabel);

        renderUIThread.start();
        updateStatPanelThread.start();
    }
}