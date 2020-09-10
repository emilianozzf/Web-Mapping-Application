package bearmaps.hw4;

import bearmaps.proj2ab.ArrayHeapMinPQ;
import edu.princeton.cs.algs4.Stopwatch;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {

  private double solutionWeight;
  private List<Vertex> solution;
  private Map<Vertex, Double> distTo;
  private Map<Vertex, Vertex> edgeTo;
  private ArrayHeapMinPQ<Vertex> pq;
  private SolverOutcome outcome;
  private int numStatesExplored;
  private double timeSpent;

  /**
   * Constructor which finds the solution, computing everything necessary for all other methods to
   * return their results in constant time. Note that timeout passed in is in seconds.
   *
   * @param input   - the input graph.
   * @param start   - the starting position.
   * @param end     - the ending position.
   * @param timeout - the timeout set.
   */
  public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {

    Instant startTime = Instant.now();

    solutionWeight = 0.0;
    solution = new ArrayList<>();
    distTo = new HashMap<>();
    edgeTo = new HashMap<>();
    pq = new ArrayHeapMinPQ<>();

    distTo.put(start, 0.0);
    edgeTo.put(start, null);
    pq.add(start, 0.0 + input.estimatedDistanceToGoal(start, end));

    while (pq.size() != 0 && !pq.getSmallest().equals(end) && Duration.between(startTime, Instant.now()).getSeconds() < timeout) {
      Vertex u = pq.removeSmallest();
      numStatesExplored += 1;
      for (WeightedEdge<Vertex> e : input.neighbors(u)) {
        relax(input, end, e);
      }
    }

    if (Duration.between(startTime, Instant.now()).getSeconds() >= timeout) {
      outcome = SolverOutcome.TIMEOUT;
    } else if (pq.size() == 0) {
      outcome = SolverOutcome.UNSOLVABLE;
    } else if (pq.getSmallest().equals(end)) {
      outcome = SolverOutcome.SOLVED;

      Vertex v = end;
      solutionWeight = distTo.get(v);
      solution.add(v);
      while (edgeTo.get(v) != null) {
        solution.add(edgeTo.get(v));
        v = edgeTo.get(v);
      }
      Collections.reverse(solution);
    }

    timeSpent = Duration.between(startTime, Instant.now()).getSeconds();
  }

  private void relax(AStarGraph<Vertex> input, Vertex end, WeightedEdge<Vertex> e) {
    Vertex u = e.from();
    Vertex v = e.to();
    double w = e.weight();

    if (!distTo.containsKey(v) || distTo.get(v) > distTo.get(u) + w) {
      distTo.put(v, distTo.get(u) + w);
      edgeTo.put(v, u);
      if (pq.contains(v)) {
        pq.changePriority(v, distTo.get(v) + input.estimatedDistanceToGoal(v, end));
      } else {
        pq.add(v, distTo.get(v) + input.estimatedDistanceToGoal(v, end));
      }
    }
  }

  /**
   * Returns one of SolverOutcome.SOLVED, SolverOutcome.TIMEOUT, or SolverOutcome.UNSOLVABLE. Should
   * be SOLVED if the AStarSolver was able to complete all work in the time given. UNSOLVABLE if the
   * priority queue became empty. TIMEOUT if the solver ran out of time. You should check to see if
   * you have run out of time every time you dequeue.
   *
   * @return one of SolverOutcome.SOLVED, SolverOutcome.TIMEOUT, or SolverOutcome.UNSOLVABLE.
   */
  @Override
  public SolverOutcome outcome() {
    return outcome;
  }

  /**
   * A list of vertices corresponding to a solution. Should be empty if result was TIMEOUT or
   * UNSOLVABLE.
   *
   * @return a list of vertices corresponding to a solution.
   */
  @Override
  public List<Vertex> solution() {
    return solution;
  }

  /**
   * The total weight of the given solution, taking into account edge weights. Should be 0 if result
   * was TIMEOUT or UNSOLVABLE.
   *
   * @return - the total weight of the given solution, taking into account edge weights.
   */
  @Override
  public double solutionWeight() {
    return solutionWeight;
  }

  /**
   * The total number of priority queue dequeue operations.
   *
   * @return the total number of priority queue dequeue operations.
   */
  @Override
  public int numStatesExplored() {
    return numStatesExplored;
  }

  /**
   * The total time spent in seconds by the constructor.
   *
   * @return the total time spent in seconds by the constructor.
   */
  @Override
  public double explorationTime() {
    return timeSpent;
  }
}
