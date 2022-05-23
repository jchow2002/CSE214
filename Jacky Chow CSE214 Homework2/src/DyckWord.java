
/**
 * @author Ritwik Banerjee
 */
public class DyckWord {

    private final String word;

    public DyckWord(String word) {
        if (isDyckWord(word))
            this.word = word;
        else
            throw new IllegalArgumentException(String.format("%s is not a valid Dyck word.", word));
    }

    private static boolean isDyckWord(String word) {
        int counter = 0;
        if (word == null)
            return false;
        //for (char c : word.toCharArray()) {
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            //Operator op = Operator.of(c);
            if (c == Operator.LEFT_PARENTHESIS.getSymbol())
                counter++;
            else if (c == Operator.RIGHT_PARENTHESIS.getSymbol()) {
                if (counter <= 0) return false;
                else counter--;
            }
        }
        return counter == 0;
    }

    public String getWord() {
        return word;
    }

    public static void main(String[] args) {

        System.out.println(isDyckWord("(()))"));
    }
}
