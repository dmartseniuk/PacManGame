import java.io.*;
import java.util.ArrayList;

public class Score implements Serializable {
    private final String name;
    private final int score;

    Score(String name, int score) {
        this.score = score;
        this.name = name;
    }

    public String toString(){
        return this.name + ": " + this.score;
    }

    public int getScore() {
        return this.score;
    }

    public static String[] getScoresStringList() {
        String[] result = new String[GameManager.highScores.size()];
        int index = 0;
        for (Score s : GameManager.highScores) {
            result[index] = s.toString();
            index++;
        }
        return result;
    }

//    public static void saveScoresToFile() {
//        try {
//            File scoresFile = new File("scores.txt");
//            scoresFile.createNewFile();
//            FileOutputStream fileOutputStream = new FileOutputStream(scoresFile, false);
//            ObjectOutputStream objectOutputStream
//                        = new ObjectOutputStream(fileOutputStream);
//
//                objectOutputStream.writeObject(GameManager.highScores);
//
//            objectOutputStream.flush();
//            objectOutputStream.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public static void saveScoresToFile() {
        try {
            File scoresFile = new File("scores.txt");
            scoresFile.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(scoresFile, false);
            ObjectOutputStream objectOutputStream
                        = new ObjectOutputStream(fileOutputStream);

                objectOutputStream.writeObject(GameManager.highScores);

            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void readScoresFromFile() {
        try {
            File scoresFile = new File("scores.txt");
            scoresFile.createNewFile();
            FileInputStream fileInputStream = new FileInputStream(scoresFile);
            ObjectInputStream objectInputStream
                    = new ObjectInputStream(fileInputStream);
                GameManager.highScores = (ArrayList<Score>)objectInputStream.readObject();

                objectInputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("No saved scores found");
        }catch (IOException e) {
            System.out.println("Error reading scores");
        }
         catch (ClassNotFoundException e) {
             System.out.println(e.getMessage());
        }

}
}
