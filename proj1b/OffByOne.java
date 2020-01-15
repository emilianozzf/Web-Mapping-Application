public class OffByOne implements CharacterComparator {
    /** Returns true if characters are different by exactly one. */
    @Override
    public boolean equalChars(char x, char y) {
        if ((x - y == 1) || (y - x == 1)) {
            return true;
        }
        return false;
    }
}