import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    static List<String> wordList = new ArrayList<>();
    private static Random random = new Random();
    static Integer guessCount = 0;
    static Integer correctGuess = 0;

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

    public static String makeAGuess() {
        Scanner scannerChar = new Scanner(System.in);
        System.out.println("Enter a letter.");
        String guessChar = scannerChar.next();

        if (guessChar.length() > 1) {
            System.out.println("You may only enter one letter at a time!");
        }

        return guessChar;
    }

    public static void main(String[] args) {
        System.out.println("Hello and welcome to the hangman game!");
        System.out.println();

        String mysteryWord = readAndReturnWord();

        System.out.println();
        System.out.println("The mystery word has " + mysteryWord.length() + " letters in it.");

        displayGallows(mysteryWord);

        System.out.println();

        while (true) {

            String guessWord = makeAGuess();

            if (mysteryWord.contains(guessWord)) {
                System.out.println("That letter is in the mystery word!");
                correctGuess++;
                if (correctGuess == mysteryWord.length()){
                    System.out.println("Congrats! You've won the game!");
                    System.exit(1);
                }
            } else {
                System.out.println("Sorry, that letter is not in the mystery word.");
                guessCount++;
                if (guessCount > (mysteryWord.length() + 2)) {
                    System.out.println("You've run out of guesses! Game over!");
                    System.exit(1);
                }
            }
        }

    }
}
