import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

/**
 * MyHashMap that implements Map61B.
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

  /**
   * Entry node for MyHashMap.
   */
  private static class Entry<K, V> {

    private K key;
    private V value;
    private Entry next;

    /**
     * Constructor for Entry node.
     *
     * @param key   - the specified key.
     * @param value - the specified value.
     * @param next  - the next entry node.
     */
    private Entry(K key, V value, Entry next) {
      this.key = key;
      this.value = value;
      this.next = next;
    }
  }

  private int numOfBuckets;
  private double loadFactor;
  private Entry<K, V>[] buckets;
  private int size;
  private HashSet<K> keySet;

  /**
   * MyHashMap should initially have a number of buckets equal to initialSize and a load factor
   * equal to loadFactor.
   * <p>
   * If initialSize and loadFactor aren’t given, you should set defaults initialSize = 16 and
   * loadFactor = 0.75 (as Java’s built-in HashMap does).
   */
  public MyHashMap() {
    this(16);
  }

  /**
   * MyHashMap should initially have a number of buckets equal to initialSize and a load factor
   * equal to loadFactor.
   * <p>
   * If initialSize and loadFactor aren’t given, you should set defaults initialSize = 16 and
   * loadFactor = 0.75 (as Java’s built-in HashMap does).
   *
   * @param initialSize - the specified initial number of buckets.
   */
  public MyHashMap(int initialSize) {
    this(initialSize, 0.75);
  }

  /**
   * MyHashMap should initially have a number of buckets equal to initialSize and a load factor
   * equal to loadFactor.
   * <p>
   * If initialSize and loadFactor aren’t given, you should set defaults initialSize = 16 and
   * loadFactor = 0.75 (as Java’s built-in HashMap does).
   *
   * @param initialSize - the specified initial number of buckets.
   * @param loadFactor  - the specified loadFactor
   */
  public MyHashMap(int initialSize, double loadFactor) {
    this.numOfBuckets = initialSize;
    this.loadFactor = loadFactor;
    this.buckets = new Entry[this.numOfBuckets];
    this.size = 0;
    this.keySet = new HashSet<>();
  }

  /**
   * Removes all of the mappings from this map.
   */
  @Override
  public void clear() {
    this.buckets = new Entry[this.numOfBuckets];
    this.size = 0;
    this.keySet = new HashSet<>();
  }

  /**
   * Returns true if this map contains a mapping for the specified key.
   *
   * @param key - the specified key.
   * @return true if this map contains a mapping for the specified key.
   */
  @Override
  public boolean containsKey(K key) {
    return get(key) != null;
  }

  /**
   * Returns the value to which the specified key is mapped, or null if this map contains no mapping
   * for the key.
   *
   * @param key - the specified key.
   * @return the value to which the specified key is mapped, or null if this map contains no mapping
   * for the key.
   */
  @Override
  public V get(K key) {
    int i = hash(key);
    for (Entry<K, V> e = buckets[i]; e != null; e = e.next) {
      if (e.key.equals(key)) {
        return e.value;
      }
    }
    return null;
  }

  /**
   * Private helper hash function.
   *
   * @param key - the specified key.
   * @return valid index of the key.
   */
  private int hash(K key) {
    return (key.hashCode() & 0x7fffffff) % numOfBuckets;
  }

  /**
   * Returns the number of key-value mappings in this map.
   *
   * @return the number of key-value mappings in this map.
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * Associates the specified value with the specified key in this map. If the map previously
   * contained a mapping for the key, the old value is replaced.
   *
   * @param key   - the specified key.
   * @param value - the mapped value.
   */
  @Override
  public void put(K key, V value) {
    if (size >= numOfBuckets * loadFactor) {
      resize(2 * numOfBuckets);
    }

    int i = hash(key);
    if (!containsKey(key)) {
      Entry<K, V> newEntry = new Entry<>(key, value, buckets[i]);
      buckets[i] = newEntry;
      size += 1;
      keySet.add(key);
    } else {
      for (Entry<K, V> e = buckets[i]; e != null; e = e.next) {
        if (e.key.equals(key)) {
          e.value = value;
        }
      }
    }
  }

  private void resize(int chains) {
    MyHashMap<K, V> temp = new MyHashMap<>(chains, this.loadFactor);

    for (int i = 0; i < numOfBuckets; i++) {
      for (Entry<K, V> e = buckets[i]; e != null; e = e.next) {
        temp.put(e.key, e.value);
      }
    }

    this.numOfBuckets = temp.numOfBuckets;
    this.loadFactor = temp.loadFactor;
    this.buckets = temp.buckets;
    this.size = temp.size;
    this.keySet = temp.keySet;
  }

  /**
   * Returns a Set view of the keys contained in this map.
   *
   * @return a Set view of the keys contained in this map.
   */
  @Override
  public Set<K> keySet() {
    return keySet;
  }

  /**
   * Removes the mapping for the specified key from this map if present. Not required for Lab 8. If
   * you don't implement this, throw an UnsupportedOperationException.
   */
  @Override
  public V remove(K key) {
    throw new UnsupportedOperationException();
  }

  /**
   * Removes the entry for the specified key only if it is currently mapped to the specified value.
   * Not required for Lab 8. If you don't implement this, throw an UnsupportedOperationException.
   */
  @Override
  public V remove(K key, V value) {
    throw new UnsupportedOperationException();
  }

  /**
   * Returns an iterator over elements of type {@code T}.
   */
  @Override
  public Iterator<K> iterator() {
    return keySet.iterator();
  }
}
