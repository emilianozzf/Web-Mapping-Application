/**
 * A class for off-by-N comparators.
 */
public class OffByN implements CharacterComparator {
    private int n;

    /**
     * Constructor for OffByN.
     *
     * @param N - the given N.
     */
    public OffByN(int N) {
        n = N;
    }

    /**
     * Returns true if characters are equal by the rules of the implementing class.
     *
     * @param x - the character x.
     * @param y - the character y.
     */
    @Override
    public boolean equalChars(char x, char y) {
        return (x - y == n) || (y - x == n);
    }
}