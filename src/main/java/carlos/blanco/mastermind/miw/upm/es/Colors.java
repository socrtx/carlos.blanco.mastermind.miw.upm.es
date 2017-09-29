package carlos.blanco.mastermind.miw.upm.es;

public enum Colors {
    RED(0, 'r'), GREEN(1, 'g'), BLUE(2, 'b'), YELLOW(3, 'y'), PURPLE(4, 'p'), ORANGE(5, 'o');

    public static Colors generate(char character) {
        for (Colors color : Colors.values()) {
            if (color.getCharacter() == character) {
                return color;
            }
        }
        return null;
    }

    public static Colors generate(int number) {
        for (Colors color : Colors.values()) {
            if (color.getNumber() == number) {
                return color;
            }
        }
        return null;
    }

    private int number;

    private char character;

    Colors(int number, char character) {
        this.number = number;
        this.character = character;
    }

    public char getCharacter() {
        return character;
    }

    public int getNumber() {
        return number;
    }

    public String toString() {
        return this.name();
    }

}
