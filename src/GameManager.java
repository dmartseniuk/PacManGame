import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameManager {
    public static Pacman pacman;
    public static Ghost[] ghosts;
    public static List<Upgrade> upgrades = new ArrayList<>();
    public static int ghostsSpeed = 200;
    public static int points = 0;
    public static int coinsOnBoard = 0;
    public static BoardCell[][] board;
    public static int rows = 25, columns = 25;
    public static List<Score> highScores = new ArrayList<Score>();
    public static JFrame gameWindow;

    public static BoardWallRenderCell wallCell;
    public static BoardCoinRenderCell coinCell;
    public static BoardRenderCell emptyCell;

    public static boolean gameStart = true;
    public static boolean gamePause = false;

    // Utils functions
    synchronized public static boolean isFieldAccessible(int x, int y) {
        return !board[y][x].isWall;
    }
    public static void consumeCoin(int x, int y) {
        if(board[y][x].isCoin) {
            board[y][x].isCoin = false;
            points = points + 1;
            if(points == coinsOnBoard) {
                gameOver();
            }
        }
    }

    synchronized public static void spawnUpgrade(int x, int y) {
        Upgrade[] upgradeTypes = new Upgrade[] {
                new Upgrade(x,y,new BoardUpgradeRenderCell(Color.RED), i -> {
                    pacman.setSpeed((int)Math.floor(pacman.getSpeed()*0.75));
                    pacman.getRenderCell().setColor(Color.RED);
                }),
                new Upgrade(x,y,new BoardUpgradeRenderCell(Color.BLUE), i -> {
                    ghostsSpeed = (int)Math.floor(ghostsSpeed*1.1);
                    pacman.getRenderCell().setColor(Color.BLUE);
                }),
                new Upgrade(x,y,new BoardUpgradeRenderCell(Color.GREEN), i -> {
                    pacman.setLives(pacman.getLives() + 1);
                    pacman.getRenderCell().setColor(Color.GREEN);
                }),
                new Upgrade(x,y,new BoardUpgradeRenderCell(Color.MAGENTA), i -> {
                    points += 100;
                    pacman.getRenderCell().setColor(Color.MAGENTA);
                }),
                new Upgrade(x,y,new BoardUpgradeRenderCell(Color.PINK), i -> {
                    spawnGhosts();
                    pacman.getRenderCell().setColor(Color.PINK);
                })
        };


        int rnd = new Random().nextInt(upgradeTypes.length);
        upgrades.add(upgradeTypes[rnd]);
    }

    public static void spawnGhosts() {
        Ghost ghost1 = new Ghost(rows-2, columns-2, new BoardGhostRenderCell(Color.RED));
        Ghost ghost2 = new Ghost(rows-3, columns-2, new BoardGhostRenderCell(Color.PINK));
        Ghost ghost3 = new Ghost(rows-4, columns-2, new BoardGhostRenderCell(Color.ORANGE));
        Ghost ghost4 = new Ghost(rows-5, columns-2, new BoardGhostRenderCell(Color.CYAN));
        ghosts = new Ghost[]{ghost1,ghost2,ghost3, ghost4};

        int ghostIndex = ghosts.length-1;
        for(int i = rows/2; i>=0; i--) {
            for(int j = columns/2; j>=0; j--) {
                if(isFieldAccessible(j,i)) {
                    ghosts[ghostIndex].move(j,i);
                    ghostIndex--;
                }
                if(ghostIndex<0)
                    return;
            }
        }
    }

    synchronized public static void checkCollisions() {
        try {
            for (Ghost ghost : ghosts) {
                if (pacman.getPositionX() == ghost.getPositionX() && pacman.getPositionY() == ghost.getPositionY()) {
                    if (1 == ghost.getPositionX() && 1 == ghost.getPositionY())
                        pacman.move(1, rows - 2);
                    else
                        pacman.move(1, 1);

                    pacman.setLives(pacman.getLives() - 1);
                    if (pacman.getLives() < 1) {
                        gameOver();
                    }
                }
            }
            List<Upgrade> newUpgrades = new ArrayList<>();
            for (Upgrade upgrade : GameManager.upgrades) {
                if (pacman.getPositionX() == upgrade.getPositionX() && pacman.getPositionY() == upgrade.getPositionY()) {
                    upgrade.callOnCollect();
                    continue;
                }
                newUpgrades.add(upgrade);
            }
            upgrades = newUpgrades;
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void gameOver() {
        gamePause = true;
        gameStart = false;
        SwingUtilities.invokeLater(() -> new InsertScoreWindow());
        gameWindow.dispose();
    }

    public static void setupGame() {
        pacman = new Pacman(1,1, new BoardPacmanRenderCell());
        board = new BoardCell[rows][columns];
        wallCell = new BoardWallRenderCell();
        coinCell = new BoardCoinRenderCell();
        emptyCell = new BoardRenderCell();

        MazeGenerator.generateMaze(board, columns, rows);

//        ghosts = new Ghost[]{};
        spawnGhosts();

        // Start game threads
        AnimatePackmanThread animatePackmanThread = new AnimatePackmanThread();
        AnimateGhostsThread animateGhostsThread = new AnimateGhostsThread();
        GhostsThread ghostsThread = new GhostsThread();
        PacmanThread pacmanThread = new PacmanThread();
        UpgradesSpawningThread upgradesSpawningThread = new UpgradesSpawningThread();

        animatePackmanThread.start();
        animateGhostsThread.start();
        ghostsThread.start();
        pacmanThread.start();
        upgradesSpawningThread.start();
    }
}
