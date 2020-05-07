import java.util.Set;

/**
 * Your implementation BSTMap should implement this interface. To do so, append "implements
 * Map61B<K,V>" to the end of your "public class..." declaration, though you can use other formal
 * type parameters if you'd like.
 */
public interface Map61B<K, V> extends Iterable<K> {

  /**
   * Removes all of the mappings from this map.
   */
  void clear();

  /**
   * Returns true if this map contains a mapping for the specified key.
   *
   * @param key - the specified key.
   * @return true if this map contains a mapping for the specified key.
   * @throws IllegalArgumentException if called with a null key.
   */
  boolean containsKey(K key) throws IllegalArgumentException;

  /**
   * Returns the value to which the specified key is mapped, or null if this map contains no mapping
   * for the key.
   *
   * @param key - the specified key.
   * @return the value to which the specified key is mapped, or null if this map contains no mapping
   * for the key.
   * @throws IllegalArgumentException if called with a null key.
   */
  V get(K key) throws IllegalArgumentException;

  /**
   * Returns the number of key-value mappings in this map.
   *
   * @return the number of key-value mappings in this map.
   */
  int size();

  /**
   * Associates the specified value with the specified key in this map.
   *
   * @param key   - the specified key.
   * @param value - the specified value.
   * @throws IllegalArgumentException if called with a null key.
   */
  void put(K key, V value) throws IllegalArgumentException;

  /**
   * Returns a Set view of the keys contained in this map.
   *
   * @return a Set view of the keys contained in this map.
   */
  Set<K> keySet();

  /**
   * Removes the mapping for the specified key from this map if present. Not required for Lab 8. If
   * you don't implement this, throw an UnsupportedOperationException.
   */
  V remove(K key);

  /**
   * Removes the entry for the specified key only if it is currently mapped to the specified value.
   * Not required for Lab 8. If you don't implement this, throw an UnsupportedOperationException.
   */
  V remove(K key, V value);
}
