package es.datastructur.synthesizer;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

/**
 * A class which implements BoundedQueue and uses an array as the actual implementation of the
 * BoundedQueue.
 *
 * @param <T> - the item type.
 */
public class ArrayRingBuffer<T> implements BoundedQueue<T> {

  private class ArrayRingBufferIterator implements Iterator<T> {

    private int wizPos;

    public ArrayRingBufferIterator() {
      wizPos = 0;
    }

    @Override
    public boolean hasNext() {
      return wizPos < fillCount;
    }

    @Override
    public T next() {
      T returnItem = rb[wizPos];
      wizPos += 1;
      return returnItem;
    }
  }

  /**
   * Index for the next dequeue or peek.
   */
  private int first;
  /**
   * Index for the next enqueue.
   */
  private int last;
  /**
   * Variable for the fillCount.
   */
  private int fillCount;
  /**
   * Array for storing the buffer data.
   */
  private T[] rb;

  /**
   * Creates a new ArrayRingBuffer with the given capacity.
   *
   * @param capacity - the given capacity.
   */
  public ArrayRingBuffer(int capacity) {
    first = 0;
    last = 0;
    fillCount = 0;
    rb = (T[]) new Object[capacity];
  }

  /**
   * Returns size of the buffer.
   *
   * @return size of the buffer.
   */
  @Override
  public int capacity() {
    return rb.length;
  }

  /**
   * Returns number of items currently in the buffer.
   *
   * @return number of items currently in the buffer.
   */
  @Override
  public int fillCount() {
    return fillCount;
  }

  /**
   * Adds item x to the end.
   *
   * @param x - the item to be added.
   * @throws RuntimeException with the String “Ring Buffer overflow” when a user attempts to enqueue
   *                          into a full ArrayRingBuffer.
   */
  @Override
  public void enqueue(T x) throws RuntimeException {
    if (isFull()) {
      throw new RuntimeException("Ring buffer overflow");
    }

    rb[last] = x;
    last += 1;
    if (last == capacity()) {
      last = 0;
    }
    fillCount += 1;
  }

  /**
   * Deletes and returns item from the front.
   *
   * @return the item from the front.
   * @throws RuntimeException with the String “Ring Buffer underflow” when a user attempts to
   *                          dequeue on an empty ArrayRingBuffer.
   */
  @Override
  public T dequeue() throws RuntimeException {
    if (isEmpty()) {
      throw new RuntimeException("Ring buffer underflow");
    }

    T item = rb[first];
    rb[first] = null;
    first += 1;
    if (first == capacity()) {
      first = 0;
    }
    fillCount -= 1;
    return item;
  }

  /**
   * Returns (but do not delete) item from the front.
   *
   * @return (but do not delete) item from the front.
   * @throws RuntimeException with the String “Ring Buffer underflow” when a user attempts to peek
   *                          on an empty ArrayRingBuffer.
   */
  @Override
  public T peek() throws RuntimeException {
    if (isEmpty()) {
      throw new RuntimeException("Ring buffer underflow");
    }

    return rb[first];
  }

  /**
   * Determines if a given item is in the buffer.
   *
   * @param x - the given item.
   * @return if a given item is in the buffer.
   */
  @Override
  public boolean contains(T x) {
    for (T item : rb) {
      if (item == x) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || this.getClass() != o.getClass()) {
      return false;
    }
    ArrayRingBuffer<?> that = (ArrayRingBuffer<?>) o;
    return first == that.first &&
        last == that.last &&
        fillCount == that.fillCount &&
        Arrays.equals(rb, that.rb);
  }

  @Override
  public int hashCode() {
    int result = Objects.hash(first, last, fillCount);
    result = 31 * result + Arrays.hashCode(rb);
    return result;
  }

  @Override
  public Iterator<T> iterator() {
    return new ArrayRingBufferIterator();
  }
}
