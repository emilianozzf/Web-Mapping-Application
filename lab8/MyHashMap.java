import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Your implementation MyHashMap should implement this interface. To do so,
 * append "implements Map61B<K, V>" to the end of your "public class..."
 * declaration, though you can use other formal type parameters if you'd like.
 *
 * MyHashMap operations should all be constant amortized time, assuming that the hashCode of any
 * objects inserted spread things out nicely (hint: every Object in Java has its own hashCode method).
 */
public class MyHashMap<K, V> implements Map61B<K, V> {
  public class Entry<K, V> {
    private K key;
    private V value;
    private Entry<K, V> next;

    private Entry(K key, V value, Entry<K, V> next) {
      this.key = key;
      this.value = value;
      this.next = next;
    }
  }

  private int numOfBuckets;
  private double loadFactor;
  private Entry<K, V>[] buckets;
  private HashSet<K> keySet;
  private int size;

  /**
   * Your MyHashMap should initially have a number of buckets equal to initialSize.
   *
   * You should increase the size of your MyHashMap when the load factor exceeds the set loadFactor.
   *
   * If initialSize and loadFactor aren’t given, you should set defaults initialSize = 16 and
   * loadFactor = 0.75 (as Java’s built-in HashMap does).
   */
  public MyHashMap() {
    this(16);
  }

  /**
   * Your MyHashMap should initially have a number of buckets equal to initialSize.
   *
   * You should increase the size of your MyHashMap when the load factor exceeds the set loadFactor.
   *
   * If initialSize and loadFactor aren’t given, you should set defaults initialSize = 16 and
   * loadFactor = 0.75 (as Java’s built-in HashMap does).
   */
  public MyHashMap(int initialSize) {
    this(initialSize, 0.75);
  }

  /**
   * Your MyHashMap should initially have a number of buckets equal to initialSize.
   *
   * You should increase the size of your MyHashMap when the load factor exceeds the set loadFactor.
   *
   * If initialSize and loadFactor aren’t given, you should set defaults initialSize = 16 and
   * loadFactor = 0.75 (as Java’s built-in HashMap does).
   */
  public MyHashMap(int initialSize, double loadFactor) {
    this.numOfBuckets = initialSize;
    this.loadFactor = loadFactor;
    this.buckets = (Entry<K, V>[]) new Entry[this.numOfBuckets];
    this.keySet = new HashSet<>();
    this.size = 0;
  }

  /**
   * Removes all of the mappings from this map.
   */
  @Override
  public void clear() {
    this.numOfBuckets = 16;
    this.loadFactor = 0.75;
    this.buckets = (Entry<K, V>[]) new Entry[this.numOfBuckets];
    this.keySet = new HashSet<>();
    this.size = 0;
  }

  /**
   * Returns true if this map contains a mapping for the specified key.
   */
  @Override
  public boolean containsKey(K key) {
    return get(key) != null;
  }

  /**
   * Returns the value to which the specified key is mapped, or null if this map contains no mapping
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
   * Returns the number of key-value mappings in this map.
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * Associates the specified value with the specified key in this map. If the map previously
   * contained a mapping for the key, the old value is replaced.
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
      size++;
      keySet.add(key);
    } else {
      for (Entry<K, V> e = buckets[i]; e != null; e = e.next) {
        if (e.key.equals(key)) {
          e.value = value;
        }
      }
    }

  }

  /**
   * Returns a Set view of the keys contained in this map.
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
    return null;
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
    this.keySet = temp.keySet;
    this.size  = temp.size;
  }

  private int hash(K key) {
    return (key.hashCode() & 0x7fffffff) % numOfBuckets;
  }
}
