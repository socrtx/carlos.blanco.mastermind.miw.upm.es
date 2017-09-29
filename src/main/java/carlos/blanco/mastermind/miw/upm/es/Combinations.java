package carlos.blanco.mastermind.miw.upm.es;

import java.util.ArrayList;
import java.util.HashMap;

public class Combinations {

    public static ArrayList<Colors> possibleColors;

    public static ArrayList<Combinations> allPossiblePossibilities;

    static {
        Combinations.possibleColors = new ArrayList<Colors>();
        possibleColors.add(Colors.RED);
        possibleColors.add(Colors.GREEN);
        possibleColors.add(Colors.BLUE);
        possibleColors.add(Colors.YELLOW);
        possibleColors.add(Colors.PURPLE);
        possibleColors.add(Colors.ORANGE);

        allPossiblePossibilities = generateAllPossibilities();
    }

    public static Score comparePatterns(Combinations guess, Combinations pattern) {
        ArrayList<Integer> positionOfNonBlackFlags = new ArrayList<Integer>();
        int blackFlags = 0;
        int whiteFlags = 0;

        ArrayList<Colors> guessColors = guess.getColors();
        ArrayList<Colors> patternColors = pattern.getColors();
        HashMap<Colors, Integer> colorsMap = getEmptyColorsMap();

        for (int counter = 0; counter < 4; counter++) {
            if (guessColors.get(counter).getCharacter() == patternColors.get(counter).getCharacter()) {
                ++blackFlags;
            } else {
                positionOfNonBlackFlags.add(counter);
            }
        }

        int currentValue = 0;
        Colors currentColor = null;
        for (Integer pos : positionOfNonBlackFlags) {
            currentColor = patternColors.get(pos);
            currentValue = colorsMap.get(currentColor) + 1;
            colorsMap.put(currentColor, currentValue);
        }

        for (Integer pos : positionOfNonBlackFlags) {
            currentColor = guessColors.get(pos);

            for (Colors color : patternColors) {
                if (currentColor.getCharacter() == color.getCharacter()) {
                    if (colorsMap.get(currentColor) > 0) {
                        whiteFlags++;
                        currentValue = colorsMap.get(currentColor) - 1;
                        colorsMap.put(currentColor, currentValue);
                        break;
                    }
                }
            }
        }

        return new Score(blackFlags, whiteFlags);

    }

    private static ArrayList<Combinations> generateAllPossibilities() {
        ArrayList<Combinations> allPosibilities = new ArrayList<Combinations>();
        for (int counter = 0; counter < 1296; counter++) {
            allPosibilities.add(Combinations.generateAPattern(counter));
        }
        return allPosibilities;

    }

    public static Combinations generateAPattern(int num) {
        Colors colorPos4 = Colors.generate((num / 1) % 6);
        Colors colorPos3 = Colors.generate((num / 6) % 6);
        Colors colorPos2 = Colors.generate((num / 36) % 6);
        Colors colorPos1 = Colors.generate((num / 216) % 6);

        return new Combinations(colorPos1, colorPos2, colorPos3, colorPos4);
    }

    public static Combinations generateAPattern(String input) {
        Colors colorPos4 = Colors.generate(input.charAt(3));
        Colors colorPos3 = Colors.generate(input.charAt(2));
        Colors colorPos2 = Colors.generate(input.charAt(1));
        Colors colorPos1 = Colors.generate(input.charAt(0));

        return new Combinations(colorPos1, colorPos2, colorPos3, colorPos4);
    }

    private static HashMap<Colors, Integer> getEmptyColorsMap() {
        HashMap<Colors, Integer> temp = new HashMap<Colors, Integer>();
        temp.put(Colors.BLUE, 0);
        temp.put(Colors.RED, 0);
        temp.put(Colors.YELLOW, 0);
        temp.put(Colors.PURPLE, 0);
        temp.put(Colors.GREEN, 0);
        temp.put(Colors.ORANGE, 0);
        return temp;

    }

    public static ArrayList<Combinations> getNextPossibleGuesses(Combinations currentGuess, Score score,
            ArrayList<Combinations> possiblePatterns) {
        ArrayList<Combinations> matchingScores = new ArrayList<Combinations>();
        for (Combinations pattern : possiblePatterns) {
            if (Combinations.comparePatterns(currentGuess, pattern).toString().equals(score.toString())) {
                matchingScores.add(pattern);
            }
        }

        // matchingScores.remove(matchingScores.indexOf(currentGuess));
        return matchingScores;
    }

    private Colors colorPos1;

    private Colors colorPos2;

    private Colors colorPos3;

    private Colors colorPos4;

    public Combinations(Colors colorPos1, Colors colorPos2, Colors colorPos3, Colors colorPos4) {
        super();
        this.colorPos1 = colorPos1;
        this.colorPos2 = colorPos2;
        this.colorPos3 = colorPos3;
        this.colorPos4 = colorPos4;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Combinations other = (Combinations) obj;
        if (colorPos1 == null) {
            if (other.colorPos1 != null) {
                return false;
            }
        } else if (!colorPos1.equals(other.colorPos1)) {
            return false;
        }
        if (colorPos2 == null) {
            if (other.colorPos2 != null) {
                return false;
            }
        } else if (!colorPos2.equals(other.colorPos2)) {
            return false;
        }
        if (colorPos3 == null) {
            if (other.colorPos3 != null) {
                return false;
            }
        } else if (!colorPos3.equals(other.colorPos3)) {
            return false;
        }
        if (colorPos4 == null) {
            if (other.colorPos4 != null) {
                return false;
            }
        } else if (!colorPos4.equals(other.colorPos4)) {
            return false;
        }
        return true;
    }

    public ArrayList<Colors> getColors() {
        ArrayList<Colors> colors = new ArrayList<Colors>();
        colors.add(this.colorPos1);
        colors.add(this.colorPos2);
        colors.add(this.colorPos3);
        colors.add(this.colorPos4);

        return colors;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((colorPos1 == null) ? 0 : colorPos1.hashCode());
        result = (prime * result) + ((colorPos2 == null) ? 0 : colorPos2.hashCode());
        result = (prime * result) + ((colorPos3 == null) ? 0 : colorPos3.hashCode());
        result = (prime * result) + ((colorPos4 == null) ? 0 : colorPos4.hashCode());
        return result;
    }

    public String toSimpleString() {
        return "" + this.colorPos1.getCharacter() + this.colorPos2.getCharacter() + this.colorPos3.getCharacter()
                + this.colorPos4.getCharacter();
    }

    public String toString() {
        return "(" + this.colorPos1.toString() + "," + this.colorPos2.toString() + "," + this.colorPos3.toString() + ","
                + this.colorPos4.toString() + ")";
    }

}
