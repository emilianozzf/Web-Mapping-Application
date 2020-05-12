package bearmaps;

import java.util.NoSuchElementException;

/**
 * Priority queue where objects have a priority that is provided extrinsically, i.e. are are
 * supplied as an argument during insertion and can be changed using the changePriority method.
 */
public interface ExtrinsicMinPQ<T> {

  /**
   * Adds an item of type T with the given priority value. Throws an IllegalArgumentException if the
   * item is already present. You may assume that item is never null.
   *
   * @param item     - the item to add.
   * @param priority - the given priority.
   * @throws IllegalArgumentException if the item is already present.
   */
  void add(T item, double priority) throws IllegalArgumentException;

  /**
   * Returns true if the PQ contains the given item.
   *
   * @param item - the given item.
   * @return true if the PQ contains the given item.
   */
  boolean contains(T item);

  /**
   * Returns the item with smallest priority. Throws NoSuchElementException if the PQ is empty.
   *
   * @return the item with smallest priority.
   * @throws NoSuchElementException if the PQ is empty.
   */
  T getSmallest() throws NoSuchElementException;

  /**
   * Removes and returns the item with smallest priority. Throws NoSuchElementException if the PQ is
   * empty.
   *
   * @return the item with smallest priority.
   * @throws NoSuchElementException if the PQ is empty.
   */
  T removeSmallest() throws NoSuchElementException;

  /**
   * Returns the number of items in the PQ.
   *
   * @return the number of items in the PQ.
   */
  int size();

  /**
   * Sets the priority of the given item to the given value. Throws NoSuchElementException if the
   * item doesn't exist.
   *
   * @param item     - the given item.
   * @param priority - the given priority.
   * @throws NoSuchElementException if the item doesn't exist.
   */
  void changePriority(T item, double priority) throws NoSuchElementException;
}
