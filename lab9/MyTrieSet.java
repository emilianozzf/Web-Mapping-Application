import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A Tire-based implementation of the TrieSet61B interface, which represents a basic trie.
 */
public class MyTrieSet implements TrieSet61B {

  private Node root;
  private int size;

  /**
   * A private helper class Node for MyTrieSet.
   */
  private static class Node {

    private char c;
    private boolean isKey;
    private Map<Character, Node> map;

    /**
     * Constructor for class Node.
     *
     * @param c     - the given character.
     * @param isKey - the boolean indicating whether the node is a key.
     */
    public Node(char c, boolean isKey) {
      this.c = c;
      this.isKey = isKey;
      this.map = new HashMap<>();
    }
  }

  public MyTrieSet() {
    this.root = new Node('\0', false);
    this.size = 0;
  }

  /**
   * Clears all items out of Trie.
   */
  @Override
  public void clear() {
    root = null;
    size =0;
  }

  /**
   * Returns true if the Trie contains KEY, false otherwise.
   *
   * @param key - the given key.
   * @return true if the Trie contains KEY, false otherwise.
   */
  @Override
  public boolean contains(String key) {
    if (key == null || key.length() < 1) {
      return false;
    }

    Node cur = root;
    for (int i = 0, n = key.length(); i < n; i++) {
      char c = key.charAt(i);
      if (cur == null || !cur.map.containsKey(c)) {
        return false;
      }
      cur = cur.map.get(c);
    }
    return cur.isKey;
  }

  /**
   * Inserts string KEY into Trie.
   *
   * @param key - the given key.
   */
  @Override
  public void add(String key) {
    if (key == null || key.length() < 1) {
      return;
    }
    Node cur = root;
    for (int i = 0, n = key.length(); i < n; i++) {
      char c = key.charAt(i);
      if (!cur.map.containsKey(c)) {
        cur.map.put(c, new Node(c, false));
      }
      cur = cur.map.get(c);
    }
    cur.isKey = true;
  }

  /**
   * Returns a list of all words that start with PREFIX.
   *
   * @param prefix - the given prefix.
   * @return a list of all words that start with PREFIX.
   */
  @Override
  public List<String> keysWithPrefix(String prefix) {
    List<String> res = new ArrayList<>();

    if (prefix == null || prefix.length() < 1) {
      return res;
    }

    Node cur = root;
    for (int i = 0, n = prefix.length(); i < n; i++) {
      char c = prefix.charAt(i);
      if (cur == null || !cur.map.containsKey(c)) {
        return res;
      }
      cur = cur.map.get(c);
    }

    dfs(res, cur, prefix);
    return res;
  }

  private void dfs(List<String> res, Node node, String stringToBuild) {
    if (node.isKey) {
      res.add(stringToBuild);
    }

    for (char c : node.map.keySet()) {
      dfs(res, node.map.get(c), stringToBuild + c);
    }
  }

  /**
   * Returns the longest prefix of KEY that exists in the Trie.
   * <p>
   * Not required for Lab 9. If you don't implement this, throw an UnsupportedOperationException.
   *
   * @param key - the given key.
   * @return the longest prefix of KEY that exists in the Trie
   */
  @Override
  public String longestPrefixOf(String key) {
    throw new UnsupportedOperationException();
  }
}
