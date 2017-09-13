import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

    static List<String> wordList = new ArrayList<>();
    private static Random random = new Random();
    static Integer guessCount = 0;
    static Integer correctGuess = 0;
    private static ArrayList<String> guessArray = new ArrayList<>();


    private static String readAndReturnWord() {
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

    private static void displayGallows(String mysteryWord) {
        int wordLength = mysteryWord.length();

        int counter = 0;

        while (counter < wordLength) {
            System.out.print("_ ");
            counter++;
        }
        System.out.println();
    }

    private static String makeAGuess() {
        Scanner scannerChar = new Scanner(System.in);
        System.out.println("Enter a letter.");
        String guessChar = scannerChar.next();

        if (guessChar.length() > 1) {
            System.out.println("You may only enter one letter at a time!");
        }

        return guessChar;
    }

    private static void updateDisplay() {
        System.out.println("updated display goes here...");
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the hangman game!");
        System.out.println();

        String mysteryWord = readAndReturnWord();

        System.out.println();

        displayGallows(mysteryWord);

        System.out.println();

        while (true) {

            String guessLetter = makeAGuess();

            if (mysteryWord.contains(guessLetter) && !guessArray.contains(guessLetter)) {
                System.out.println("That letter is in the mystery word!");
                guessArray.add(guessLetter);
                correctGuess++;
                updateDisplay();
                if (correctGuess == mysteryWord.length()) {
                    System.out.println("Congrats! You've won the game!");
                    System.exit(1);
                }
            } else {
                System.out.println("Sorry, that letter is not in the mystery word or has already been guessed.");
                guessArray.add(guessLetter);
                guessCount++;
                if (guessCount > (mysteryWord.length() + 2)) {
                    System.out.println("You've run out of guesses! Game over!");
                    System.exit(1);
                }
            }
        }
    }
}
