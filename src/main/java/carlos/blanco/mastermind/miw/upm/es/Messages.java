package carlos.blanco.mastermind.miw.upm.es;

public class Messages {

    public static void printGameOverStatement(String gameCode) {
        System.out.println(String.format("Out of guesses.  Game over.  Code is %s", gameCode));
    }

    public static void printGuessHelpStatement(Integer codeLength) {
        System.out.println(String.format("Your guess must be a %s digit number with no duplicate digits.", codeLength));
    }

    public static void printGuessResultStatement(Integer digitMatches, Integer LocationMatches) {
        System.out.println(String.format("%s correct digits.  %s correct locations.\n", digitMatches, LocationMatches));
    }

    public static void printUsageMessage() {
        System.out.println("Usage:  java MastermindGame <codeLength>");
        System.out.println("Valid code lengths are numbers from 3 to 6");
    }

    public static void printWelcomeMessage(Integer codeLength, Integer maxGuesses) {
        System.out.println("Welcome to Mastermind.");
        System.out.println(
                String.format("Your secret code is %s digits in length.  You have %s guesses.  Good luck!\n", codeLength, maxGuesses));
    }

    public static void printWinningStatement(Integer numberOfGuesses, String gameCode) {
        System.out.println(String.format("You win!!  %s guesses.  Code is: %s", numberOfGuesses, gameCode));
    }
}
