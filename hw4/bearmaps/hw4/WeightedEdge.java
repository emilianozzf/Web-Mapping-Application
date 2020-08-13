package bearmaps.hw4;

/**
 * Utility class that represents a weighted directed edge. Created by hug.
 */
public class WeightedEdge<Vertex> {

  private Vertex v;
  private Vertex w;
  private double weight;

  /**
   * Constructor for WeightedEdge.
   *
   * @param v      - the outgoing vertex v.
   * @param w      - the incoming vertex w.
   * @param weight - the weight of the edge.
   */
  public WeightedEdge(Vertex v, Vertex w, double weight) {
    this.v = v;
    this.w = w;
    this.weight = weight;
  }

  /**
   * The source of this edge.
   *
   * @return the source of this edge.
   */
  public Vertex from() {
    return v;
  }

  /**
   * The destination of this edge.
   *
   * @return the destination of this edge.
   */
  public Vertex to() {
    return w;
  }

  /**
   * The weight of this edge.
   *
   * @return the weight of this edge.
   */
  public double weight() {
    return weight;
  }
}
