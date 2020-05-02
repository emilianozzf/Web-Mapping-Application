/**
 * A class for off-by-1 comparators.
 */
public class OffByOne implements CharacterComparator {

    /** Returns true if characters are different by exactly one.
     *
     * @param x - the character x.
     * @param y - the character y.
     */
    @Override
    public boolean equalChars(char x, char y) {
        return (x - y == 1) || (y - x == 1);
    }
}