package bearmaps;

import static org.junit.Assert.*;

import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;

public class ArrayHeapMinPQTest {

  private ExtrinsicMinPQ<String> minPQ;
  private ExtrinsicMinPQ<String> minPQNaive;

  @Before
  public void setUp() throws Exception {
    minPQ = new ArrayHeapMinPQ<>();
    minPQNaive = new NaiveMinPQ<>();
  }

  @Test(expected = IllegalArgumentException.class)
  public void addDuplicateItem() {
    minPQ.add("Emiliano", 1);
    minPQ.add("Emiliano", 1);
  }

  @Test
  public void add() {
    minPQ.add("Emiliano", 1);
    assertTrue(minPQ.contains("Emiliano"));
    assertEquals("Emiliano", minPQ.getSmallest());
    assertEquals(1, minPQ.size());

    minPQ.add("Jack", 0);
    assertTrue(minPQ.contains("Emiliano"));
    assertTrue(minPQ.contains("Jack"));
    assertEquals("Jack", minPQ.getSmallest());
    assertEquals(2, minPQ.size());
  }

  @Test
  public void contains() {
    minPQ.add("Emiliano", 1);
    assertTrue(minPQ.contains("Emiliano"));
    assertFalse(minPQ.contains("Jack"));
  }

  @Test(expected = NoSuchElementException.class)
  public void getSmallestOnEmpty() {
    minPQ.getSmallest();
  }

  @Test
  public void getSmallest() {
    minPQ.add("Emiliano", 1);
    assertEquals("Emiliano", minPQ.getSmallest());
    minPQ.add("Jack", 0);
    assertEquals("Jack", minPQ.getSmallest());
    minPQ.add("Sandro", 2);
    assertEquals("Jack", minPQ.getSmallest());
  }

  @Test(expected = NoSuchElementException.class)
  public void removeSmallestOnEmpty() {
    minPQ.removeSmallest();
  }

  @Test
  public void removeSmallest() {
    minPQ.add("Emiliano", 1);
    minPQ.add("Jack", 0);
    minPQ.add("Sandro", 2);
    assertEquals("Jack", minPQ.removeSmallest());
    assertFalse(minPQ.contains("Jack"));
    assertEquals(2, minPQ.size());
    assertEquals("Emiliano", minPQ.removeSmallest());
    assertFalse(minPQ.contains("Emiliano"));
    assertEquals(1, minPQ.size());
    assertEquals("Sandro", minPQ.removeSmallest());
    assertFalse(minPQ.contains("Sandro"));
    assertEquals(0, minPQ.size());
  }

  @Test
  public void size() {
    assertEquals(0, minPQ.size());
  }

  @Test(expected = NoSuchElementException.class)
  public void changePriorityOfNonItem() {
    minPQ.changePriority("Emiliano", 2);
  }

  @Test
  public void changePriority() {
    minPQ.add("Emiliano", 1);
    minPQ.add("Jack", 0);
    minPQ.add("Sandro", 2);

    minPQ.changePriority("Jack", 3);
    assertEquals("Emiliano", minPQ.removeSmallest());
    assertFalse(minPQ.contains("Emiliano"));
    assertEquals(2, minPQ.size());

    minPQ.changePriority("Sandro", 4);
    assertEquals("Jack", minPQ.removeSmallest());
    assertFalse(minPQ.contains("Jack"));
    assertEquals(1, minPQ.size());

    assertEquals("Sandro", minPQ.removeSmallest());
    assertFalse(minPQ.contains("Sandro"));
    assertEquals(0, minPQ.size());
  }

  @Test
  public void TimingTest() {
    long start = System.currentTimeMillis();

    for (int i = 0; i < 1000000; i += 1) {
      minPQ.add(String.valueOf(i), i);
    }

    long end = System.currentTimeMillis();
    System.out.println("Total time elapsed: " + (end - start) / 1000.0 + " seconds.");

    start = System.currentTimeMillis();

    for (int i = 0; i < 1000000; i += 1) {
      minPQNaive.add(String.valueOf(i), i);
    }

    end = System.currentTimeMillis();
    System.out.println("Total time elapsed: " + (end - start) / 1000.0 + " seconds.");
  }
}