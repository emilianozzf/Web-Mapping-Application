package es.datastructur.synthesizer;

/**
 * A class which uses an ArrayRingBuffer<Double> to implement the Karplus-Strong algorithm to
 * synthesize a guitar string sound.
 */
public class GuitarString {
  /** Constants. Do not change. In case you're curious, the keyword final
   * means the values cannot be changed at runtime. */
  /**
   * Sampling Rate
   */
  private static final int SR = 44100;
  /**
   * Energy decay factor
   */
  private static final double DECAY = .996;

  /**
   * Buffer for storing sound data.
   */
  private BoundedQueue<Double> buffer;

  /**
   * Create a guitar string of the given frequency.
   */
  public GuitarString(double frequency) {
    int capacity = (int) Math.round(SR / frequency);

    buffer = new ArrayRingBuffer<>(capacity);

    for (int i = 0; i < capacity; i++) {
      buffer.enqueue(0.0);
    }
  }

  /**
   * Pluck the guitar string by replacing the buffer with white noise.
   */
  public void pluck() {
    for (int i = 0; i < buffer.capacity(); i++) {
      buffer.dequeue();

      double r = Math.random() - 0.5;
      while (buffer.contains(r)) {
        r = Math.random() - 0.5;
      }

      buffer.enqueue(r);
    }
  }

  /**
   * Advance the simulation one time step by performing one iteration of the Karplus-Strong
   * algorithm.
   */
  public void tic() {
    double newDouble = (buffer.dequeue() + sample()) * 0.5 * DECAY;
    buffer.enqueue(newDouble);
  }

  /**
   * Returns the double at the front of the buffer.
   */
  public double sample() {
    return buffer.peek();
  }
}
