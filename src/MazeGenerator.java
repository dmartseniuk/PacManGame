public class MazeGenerator {
    public static void initBoard(BoardCell[][] arr) {
        for(int i = 0; i<arr.length; i++) {
            for(int j = 0; j<arr[i].length; j++) {
                arr[i][j] = new BoardCell(false, false);
            }
        }
    }
    public static void fillCoins(BoardCell[][] arr) {
        for(int i = 0; i<arr.length; i++) {
            for(int j = 0; j<arr[i].length; j++) {
                if(!arr[i][j].isWall) {
                    arr[i][j].isCoin = true;
                    GameManager.coinsOnBoard++;
                }
            }
        }
    }
    public static void generateMaze(BoardCell[][] arr, int columns, int rows) {
        MazeGenerator.initBoard(arr);

        int minLength = columns <= rows ? columns : rows;

        int isGhostSpace = ((columns-10)%4==0 || (rows-10)%4==0) ? 2 : 0;


        // Fill vertical walls
        for (int j = 0; j < minLength/2-isGhostSpace; j = j + 2) {
            int gates = 0;
            for(int i = j; i < rows - j; i++) {
                int rand = (int)(Math.random() * 3) + 1;
                boolean isWall = true;

                if(rand == 1 && j > 0) {
                    isWall = false;
                    if(i!=j && i != rows - j - 1) {
                        gates++;
                    }
                }
                // Right wall
                arr[i][columns - 1 - j].isWall = isWall;
                // Left wall
                arr[rows-1-i][j].isWall = isWall;

                if(j > 0 && gates < 1 && i == rows-j-1) {
                    i = j;
                }
            }
        }

        // Fill horizontal walls
        for (int j = 0; j < minLength/2-isGhostSpace; j = j + 2) {
            int gates = 0;
            for(int i = j; i < columns-j; i++) {
                int rand = (int)(Math.random() * 3) + 1;
                boolean isWall = true;

                if(rand == 1 && j > 0) {
                    isWall = false;
                    if(i!=j && i != columns - j - 1) {
                        gates++;
                    }
                }
                // Top wall
                arr[j][i].isWall = isWall;
                // Bottom wall
                arr[rows - 1 - j][i].isWall = isWall;
                if(j > 0 && gates < 1 && i == columns-j-1) {
                    i = j;
                }
            }
        }
        MazeGenerator.fillCoins(arr);
    }
}
