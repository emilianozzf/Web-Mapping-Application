import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Solver for the Flight problem (#9) from CS 61B Spring 2018 Midterm 2. Assumes valid input, i.e.
 * all Flight start times are >= end times. If a flight starts at the same time as a flight's end
 * time, they are considered to be in the air at the same time.
 */
public class FlightSolver {

  private List<Flight> flights;

  /**
   * Constructor that takes in an ArrayList of Flight objects as described above.
   *
   * @param flights - an ArrayList of Flight objects.
   */
  public FlightSolver(ArrayList<Flight> flights) {
    this.flights = flights;
  }

  /**
   * Returns the largest number of people that have ever been in flight at once.
   * <p>
   * Your algorithm must run in θ(NlogN) time, where N is the number of total commercial flights
   * ever taken.
   *
   * @return the largest number of people that have ever been in flight at once.
   */
  public int solve() {
      Queue<Flight> q = new PriorityQueue<>(new NumOfPassengersComparator());

  }

}
