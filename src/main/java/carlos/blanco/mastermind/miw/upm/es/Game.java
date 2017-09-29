package carlos.blanco.mastermind.miw.upm.es;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game {

    private static final Combinations defaultGuess = Combinations.generateAPattern("pyyp");

    private static String[] messages = {"Game is over... - ", "You should have taken the red pill.", "YOU ARE DOING IT WRONG!",
            "Thank you, now keep enjoying your crappy Windows :D", "Not feeling lucky any more punk?", "it's over!"};

    private static Scanner scanner;

    private static boolean continueGame() {
        @SuppressWarnings("resource")
        Scanner scan = new Scanner(System.in);
        System.out.println("Would you like to play again?\n(Write 'y' to play again, anything else to not play again)");
        return scan.nextLine().toLowerCase().equals("y");
    }

    public static Combinations getActualPatternFromUser() {
        System.out.println("\n\n\nNEW GAME!:\n");
        boolean validPattern = false;
        String input = "";
        while (!validPattern) {
            System.out.println("Please enter a four peg pattern choosing from these colors:\n"
                    + "Colors: R=red, Y=yellow, G=green, B=blue, O=orange and P=purple \n" + "(e.g. gypb)");
            scanner = new Scanner(System.in);

            input = scanner.nextLine();
            input = input.toLowerCase();
            validPattern = validatePattern(input);
        }
        return Combinations.generateAPattern(input);
    }

    private static Combinations getNextPossibleGuess(ArrayList<Combinations> possiblePatterns) {
        Random r = new Random();
        // System.out.println("\n\nDEBUG: SIZE OF POSSIBLE PATTERNS: " + possiblePatterns.size() + "\n\n");
        int guess = r.nextInt(possiblePatterns.size());
        return possiblePatterns.get(guess);
    }

    public static void main(String[] args) {
        boolean play = true;
        while (play) {
            Combinations actualPattern = getActualPatternFromUser();
            startGuessing(actualPattern);
            play = continueGame();
        }
        System.out.println("\n" + sassyEndMessage());
        System.out.println("See ya next time!");
    }

    private static String sassyEndMessage() {

        Random r = new Random();
        int msgIndex = r.nextInt(messages.length);
        return messages[msgIndex];

    }

    private static void startGuessing(Combinations actualPattern) {

        Combinations currentGuess = defaultGuess;
        ArrayList<Combinations> possiblePatterns = Combinations.allPossiblePossibilities;
        int totalGuesses = 1;

        Score currentScore = Combinations.comparePatterns(currentGuess, actualPattern);

        while (true) {
            System.out.println("\n\nActual Pattern: " + actualPattern);
            System.out.println("Trying this guess: " + currentGuess);
            if (currentScore.getCorrectPositionAndColor() == 4) {
                System.out.println(currentScore);
                System.out.println("I win, in " + totalGuesses + " guesses.\n\n");
                break;
            } else {
                System.out.println(currentScore);
                System.out.println("I didn't win, yet :), will try again.");
            }

            possiblePatterns = Combinations.getNextPossibleGuesses(currentGuess, currentScore, possiblePatterns);
            currentGuess = getNextPossibleGuess(possiblePatterns);
            currentScore = Combinations.comparePatterns(currentGuess, actualPattern);
            totalGuesses++;
        }
    }

    private static boolean validatePattern(String input) {
        for (int counter = 0; counter < 4; counter++) {
            char currentCharacter = input.charAt(counter);
            if (Colors.generate(currentCharacter) == null) {
                System.out.println("Pattern given is not allowed, please read instructions carefully...RETARD!.\n");
                return false;
            }
        }
        return true;
    }

}
