import java.util.List;

/**
 * A TrieSet61B interface.
 */
public interface TrieSet61B {

  /**
   * Clears all items out of Trie.
   */
  void clear();

  /**
   * Returns true if the Trie contains KEY, false otherwise.
   *
   * @param key - the given key.
   * @return true if the Trie contains KEY, false otherwise.
   */
  boolean contains(String key);

  /**
   * Inserts string KEY into Trie.
   *
   * @param key - the given key.
   */
  void add(String key);

  /**
   * Returns a list of all words that start with PREFIX.
   *
   * @param prefix - the given prefix.
   * @return a list of all words that start with PREFIX.
   */
  List<String> keysWithPrefix(String prefix);

  /**
   * Returns the longest prefix of KEY that exists in the Trie.
   * <p>
   * Not required for Lab 9. If you don't implement this, throw an UnsupportedOperationException.
   *
   * @param key - the given key.
   * @return the longest prefix of KEY that exists in the Trie
   */
  String longestPrefixOf(String key);
}
