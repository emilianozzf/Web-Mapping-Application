package bearmaps;

import java.util.HashMap;
import java.util.NoSuchElementException;

/**
 * A class representing an ArrayHeapMinPQ implements an ExtrinsicMinPQ interface.
 *
 * @param <T> - the item type.
 */
public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {

  private Node[] minHeap;
  private HashMap<T, Integer> itemIndexMapping;
  private int size;

  /**
   * Helper class Node.
   */
  private class Node {

    T item;
    double priority;

    /**
     * Constructor for helper class node.
     *
     * @param item     - the given item.
     * @param priority - the given priority.
     */
    Node(T item, double priority) {
      this.item = item;
      this.priority = priority;
    }
  }

  /**
   * Initializes an empty priority queue.
   */
  public ArrayHeapMinPQ() {
    this(1);
  }

  /**
   * Initializes an empty min heap with the given initial capacity.
   *
   * @param initCapacity - the given initial capacity.
   */
  public ArrayHeapMinPQ(int initCapacity) {
    this.minHeap = new ArrayHeapMinPQ.Node[initCapacity+1];
    this.itemIndexMapping = new HashMap<>();
    this.size = 0;
  }

  /**
   * Adds an item of type T with the given priority value. Throws an IllegalArgumentException if the
   * item is already present. You may assume that item is never null.
   *
   * @param item     - the item to add.
   * @param priority - the given priority.
   * @throws IllegalArgumentException if the item is already present.
   */
  @Override
  public void add(T item, double priority) throws IllegalArgumentException {
    if (contains(item)) {
      throw new IllegalArgumentException("the item is already present");
    }

    if (size() == minHeap.length - 1) {
      resize(2 * minHeap.length);
    }

    minHeap[size() + 1] = new Node(item, priority);
    itemIndexMapping.put(item, size() + 1);
    size += 1;
    swim(size());
  }

  /**
   * Returns true if the PQ contains the given item.
   *
   * @param item - the given item.
   * @return true if the PQ contains the given item.
   */
  @Override
  public boolean contains(T item) {
    return itemIndexMapping.containsKey(item);
  }

  /**
   * Returns the item with smallest priority. Throws NoSuchElementException if the PQ is empty.
   *
   * @return the item with smallest priority.
   * @throws NoSuchElementException if the PQ is empty.
   */
  @Override
  public T getSmallest() throws NoSuchElementException {
    if (size() == 0) {
      throw new NoSuchElementException("called getSmallest() on an empty pq");
    }

    return minHeap[1].item;
  }

  /**
   * Removes and returns the item with smallest priority. Throws NoSuchElementException if the PQ is
   * empty.
   *
   * @return the item with smallest priority.
   * @throws NoSuchElementException if the PQ is empty.
   */
  @Override
  public T removeSmallest() throws NoSuchElementException {
    if (size() == 0) {
      throw new NoSuchElementException("called removeSmallest() on an empty pq");
    }

    T min = minHeap[1].item;
    swap(1, size());
    size -= 1;
    sink(1);
    minHeap[size() + 1] = null;
    itemIndexMapping.remove(min);

    if ((size() > 0) && (size() == (minHeap.length - 1) / 4)) {
      resize(minHeap.length / 2);
    }

    return min;
  }

  /**
   * Returns the number of items in the PQ.
   *
   * @return the number of items in the PQ.
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * Sets the priority of the given item to the given value. Throws NoSuchElementException if the
   * item doesn't exist.
   *
   * @param item     - the given item.
   * @param priority - the given priority.
   * @throws NoSuchElementException if the item doesn't exist.
   */
  @Override
  public void changePriority(T item, double priority) throws NoSuchElementException {
    if (!contains(item)) {
      throw new NoSuchElementException("the item doesn't exist");
    }

    int i = itemIndexMapping.get(item);
    double oldPriority = minHeap[i].priority;
    minHeap[i].priority = priority;
    if (oldPriority < priority) {
      sink(i);
    } else {
      swim(i);
    }
  }

  /**
   * Helper method to resize the min heap array and priorities array.
   *
   * @param capacity - the given capacity.
   */
  private void resize(int capacity) {
    Node[] minHeapTmp = new ArrayHeapMinPQ.Node[capacity];

    for (int i = 1; i <= size; i++) {
      minHeapTmp[i] = minHeap[i];
    }

    minHeap = minHeapTmp;
  }

  private void swim(int i) {
    while (i > 1) {
      int p = parent(i);
      if (greater(p, i)) {
        swap(p, i);
        i = p;
      } else {
        break;
      }
    }
  }

  private void sink(int i) {
    while (leftChild(i) <= size()) {
      int toSwap = i;

      if (greater(i, leftChild(i))) {
        toSwap = leftChild(i);
      }

      if (rightChild(i) <= size() && greater(toSwap, rightChild(i))) {
        toSwap = rightChild(i);
      }

      if (toSwap == i) {
        break;
      }

      swap(i, toSwap);
      i = toSwap;
    }
  }

  private static int parent(int i) {
    return i / 2;
  }

  private static int leftChild(int i) {
    return 2 * i;
  }

  private static int rightChild(int i) {
    return 2 * i + 1;
  }

  private boolean greater(int i, int j) {
    return minHeap[i].priority > minHeap[j].priority;
  }

  private void swap(int i, int j) {
    Node tmp = minHeap[i];
    minHeap[i] = minHeap[j];
    minHeap[j] = tmp;

    itemIndexMapping.put(minHeap[i].item, i);
    itemIndexMapping.put(minHeap[j].item, i);
  }
}
