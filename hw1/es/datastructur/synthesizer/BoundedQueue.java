package es.datastructur.synthesizer;

import java.util.Iterator;

/**
 * An interface which declares all the methods that must be implemented by any class that implements
 * BoundedQueue.
 *
 * @param <T> - the item type.
 */
public interface BoundedQueue<T> extends Iterable<T> {

  /**
   * Returns size of the buffer.
   *
   * @return size of the buffer.
   */
  int capacity();

  /**
   * Returns number of items currently in the buffer.
   *
   * @return number of items currently in the buffer.
   */
  int fillCount();


  /**
   * Adds item x to the end.
   *
   * @param x - the item to be added.
   */
  void enqueue(T x);

  /**
   * Deletes and returns item from the front.
   *
   * @return the item from the front.
   */
  T dequeue();

  /**
   * Returns (but do not delete) item from the front.
   *
   * @return (but do not delete) item from the front.
   */
  T peek();

  /**
   * Determines if a given item is in the buffer.
   *
   * @param x - the given item.
   * @return if a given item is in the buffer.
   */
  boolean contains(T x);

  /**
   * Returns if the buffer is empty.
   *
   * @return if the buffer is empty.
   */
  default boolean isEmpty() {
    return fillCount() == 0;
  }

  /**
   * Returns if the buffer is full.
   *
   * @return if the buffer is full.
   */
  default boolean isFull() {
    return fillCount() == capacity();
  }

  @Override
  Iterator<T> iterator();
}
