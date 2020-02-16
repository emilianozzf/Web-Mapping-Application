import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
  // root of BSTMap
  private Node root;

  private class Node {
    // sorted by key
    private K key;
    // associated data
    private V val;
    // left and right subtrees
    private Node left;
    private Node right;
    // number of nodes in subtree
    private int size;

    public Node(K key, V val, int size) {
      this.key = key;
      this.val = val;
      this.size = size;
    }
  }

  /**
   * Initializes an empty symbol table.
   */
  public BSTMap() {
  }

  /**
   * Removes all of the mappings from this map.
   */
  @Override
  public void clear() {
    root = null;
  }

  /**
   * Returns true if this map contains a mapping for the specified key.
   */
  @Override
  public boolean containsKey(K key) {
    if (key == null) throw new IllegalArgumentException("argument to contains() is null");
    return get(key) != null;
  }

  /**
   * Returns the value to which the specified key is mapped, or null if this map contains no mapping
   * for the key.
   */
  @Override
  public V get(K key) {
    return get(root, key);
  }

  private V get(Node x, K key) {
    if (key == null) throw new IllegalArgumentException("calls get() with a null key");
    if (x == null) return null;

    int cmp = key.compareTo(x.key);
    if      (cmp < 0) return get(x.left, key);
    else if (cmp > 0) return get(x.right, key);
    else              return x.val;
  }

  /**
   * Returns the number of key-value mappings in this map.
   */
  @Override
  public int size() {
    return size(root);
  }

  private int size(Node x) {
    if (x == null) return 0;
    else return x.size;
  }

  /**
   * Associates the specified value with the specified key in this map.
   */
  @Override
  public void put(K key, V value) {
    if (key == null) throw new IllegalArgumentException("calls put() with a null key");
    root = put(root, key, value);
  }

  private Node put(Node x, K key, V val) {
    if (x == null) return new Node(key, val, 1);
    int cmp = key.compareTo(x.key);
    if      (cmp < 0) x.left  = put(x.left,  key, val);
    else if (cmp > 0) x.right = put(x.right, key, val);
    else              x.val = val;
    x.size = 1 + size(x.left) + size(x.right);
    return x;
  }

  /**
   * Returns a Set view of the keys contained in this map.
   */
  @Override
  public Set<K> keySet() {
    Set<K> keySet = new HashSet<>();
    keySet(root, keySet);
    return keySet;
  }

  private void keySet(Node x, Set<K> keySet) {
    if (x == null) {
      return;
    }
    keySet.add(x.key);
    keySet(x.left, keySet);
    keySet(x.right, keySet);
  }

  /**
   * Prints out your BSTMap in order of increasing Key.
   */
  public void printInOrder(){
    printInOrder(root);
  }

  private void printInOrder(Node x) {
    if (x == null) {
      return;
    }
    printInOrder(x.left);
    System.out.print(x.key + ": " + x.val + " ");
    printInOrder(x.right);
  }

  /**
   * Removes the mapping for the specified key from this map if present. Not required for Lab 8. If
   * you don't implement this, throw an UnsupportedOperationException.
   *
   * @param key
   */
  @Override
  public V remove(K key) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  /**
   * Removes the entry for the specified key only if it is currently mapped to the specified value.
   * Not required for Lab 8. If you don't implement this, throw an UnsupportedOperationException.
   *
   * @param key
   * @param value
   */
  @Override
  public V remove(K key, V value) throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }

  /**
   * Returns an iterator over elements of type {@code T}.
   *
   * @return an Iterator.
   */
  @Override
  public Iterator<K> iterator() throws UnsupportedOperationException {
    throw new UnsupportedOperationException();
  }
}
