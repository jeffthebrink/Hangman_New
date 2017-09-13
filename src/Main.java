import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    static List<String> wordList = new ArrayList<>();
    private static Random random = new Random();

    public static String readAndReturnWord() {
        File file = new File("words.txt");
        Scanner scanner = null;

        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            System.exit(0);
        }

        scanner.useDelimiter("\\Z");
        while (scanner.hasNext()) {
            String contents = scanner.next();
            wordList = Arrays.asList(contents.split("\\s*, \\s*"));
        }
        scanner.close();

        int index = random.nextInt(wordList.size());
        String randomWord = wordList.get(index);
        return randomWord;
    }

    public static void displayGallows(String mysteryWord) {
        int wordLength = mysteryWord.length();

        int counter = 0;

        while (counter < wordLength) {
            System.out.print("_ ");
            counter++;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("Hello and welcome to the hangman game!");
        System.out.println();

        String mysteryWord = readAndReturnWord();

        System.out.println();
        System.out.println("The mystery word has " + mysteryWord.length() + " letters in it.");

        displayGallows(mysteryWord);

        System.out.println();
        System.out.println("Enter a letter for your first guess.");


    }
}
