/**
 * Public outer LinkedListDequeue class.
 */
public class LinkedListDeque<T> implements Deque<T> {

  /**
   * Private helper TNode class.
   */
  private class TNode {

    private T item;
    private TNode prev;
    private TNode next;

    /**
     * Constructor of the TNode class.
     *
     * @param item - the item.
     * @param prev - the previous node.
     * @param item - the next node.
     */
    public TNode(T item, TNode prev, TNode next) {
      this.item = item;
      this.prev = prev;
      this.next = next;
    }
  }

  private TNode sentinel;
  private int size;

  /**
   * Creates an empty linked list deque.
   */
  public LinkedListDeque() {
    sentinel = new TNode(null, null, null);
    sentinel.prev = sentinel;
    sentinel.next = sentinel;
    size = 0;
  }

  /**
   * Creates a deep copy of other.
   *
   * @param other - the other linked list deque to copy.
   */
  public LinkedListDeque(LinkedListDeque other) {
    sentinel = new TNode(null, null, null);
    sentinel.next = sentinel;
    sentinel.prev = sentinel;
    size = 0;
    for (int i = 0; i < other.size; i++) {
      addLast((T) other.get(i));
    }
  }

  /**
   * Adds an item of type T to the front of the deque.
   *
   * @param item - the item to be added.
   */
  @Override
  public void addFirst(T item) {
    sentinel.next = new TNode(item, sentinel, sentinel.next);
    sentinel.next.next.prev = sentinel.next;
    size += 1;
  }

  /**
   * Adds an item of type T to the back of the deque.
   *
   * @param item - the item to be added.
   */
  @Override
  public void addLast(T item) {
    sentinel.prev = new TNode(item, sentinel.prev, sentinel);
    sentinel.prev.prev.next = sentinel.prev;
    size += 1;
  }

  /**
   * Returns the number of items in the deque.
   *
   * @return the number of items in the deque.
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * Prints the items in the deque from first to last, separated by a space. Once all the items have
   * been printed, print out a new line.
   */
  @Override
  public void printDeque() {
    TNode ptr = sentinel.next;
    while (ptr != sentinel) {
      System.out.print(ptr.item + " ");
      ptr = ptr.next;
    }
    System.out.println();
  }

  /**
   * Removes and returns the item at the front of the deque. If no such item exists, returns null.
   *
   * @return the item at the front of the deque. If no such item exists, returns null.
   */
  @Override
  public T removeFirst() {
    if (this.isEmpty()) {
      return null;
    }

    TNode front = sentinel.next;
    sentinel.next = sentinel.next.next;
    sentinel.next.prev = sentinel;
    front.next = null;
    front.prev = null;
    size -= 1;
    return front.item;
  }

  /**
   * Removes and returns the item at the back of the deque. If no such item exists, returns null.
   *
   * @return the item at the back of the deque. If no such item exists, returns null.
   */
  @Override
  public T removeLast() {
    if (this.isEmpty()) {
      return null;
    }

    TNode last = sentinel.prev;
    sentinel.prev = sentinel.prev.prev;
    sentinel.prev.next = sentinel;
    last.next = null;
    last.prev = null;
    size -= 1;
    return last.item;
  }

  /**
   * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no
   * such item exists, returns null. Must not alter the deque!
   *
   * @param index - the index given.
   * @return the item at the given index. If no such item exists, returns null.
   */
  @Override
  public T get(int index) {
    if ((index >= size) || (index <= -1)) {
      return null;
    }

    TNode ptr = sentinel.next;
    for (int i = 0; i < index; i++) {
      ptr = ptr.next;
    }
    return ptr.item;
  }

  private T getRecursiveHelper(TNode cur, int index) {
    if (cur == null || index < 0) {
      return null;
    }

    if (index == 0) {
      return cur.item;
    }

    return getRecursiveHelper(cur.next, index - 1);
  }

  /**
   * Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no
   * such item exists, returns null. Must not alter the deque!
   *
   * @param index - the index given.
   * @return the item at the given index. If no such item exists, returns null.
   */
  public T getRecursive(int index) {
    return getRecursiveHelper(sentinel.next, index);
  }
}