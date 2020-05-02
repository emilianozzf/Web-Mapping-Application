/**
 * A class for palindrome operations.
 */
public class Palindrome {

  /**
   * Given a word, returns a deque where the characters appear in the same order as in the word.
   *
   * @param word - the given word.
   * @return a deque where the characters appear in the same order as in the word.
   */
  public Deque<Character> wordToDeque(String word) {
    Deque<Character> res = new LinkedListDeque<>();
    for (int i = 0; i < word.length(); i += 1) {
      res.addLast(word.charAt(i));
    }
    return res;
  }

  /**
   * Returns true if the given word is a palindrome, and false otherwise. ‘A’ and ‘a’ should not be
   * considered equal.
   *
   * @param word - the given word.
   * @return true if the given word is a palindrome, and false otherwise.
   */
  public boolean isPalindrome(String word) {
    Deque<Character> wordDeque = wordToDeque(word);
    return isPalindromeRecursive(wordDeque);
  }

  private boolean isPalindromeRecursive(Deque<Character> wordDeque) {
    if (wordDeque.size() <= 1) {
      return true;
    }

    if (wordDeque.removeFirst() == wordDeque.removeLast()) {
      return isPalindromeRecursive(wordDeque);
    }
    return false;
  }

  /**
   * Returns true if the given word is a palindrome according to the given character comparator, and
   * false otherwise.
   *
   * @param word - the given word.
   * @param cc   - the given character comparator.
   * @return true if the given word is a palindrome, and false otherwise.
   */
  public boolean isPalindrome(String word, CharacterComparator cc) {
    Deque<Character> wordDeque = wordToDeque(word);
    return isPalindromeRecursiveOffByOne(wordDeque, cc);
  }

  private boolean isPalindromeRecursiveOffByOne(Deque<Character> wordDeque,
      CharacterComparator cc) {
    if (wordDeque.size() <= 1) {
      return true;
    }

    if (cc.equalChars(wordDeque.removeFirst(), wordDeque.removeLast())) {
      return isPalindromeRecursiveOffByOne(wordDeque, cc);
    }
    return false;
  }
}