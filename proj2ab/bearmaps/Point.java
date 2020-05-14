package bearmaps;

/**
 * Represents a location with an x and y coordinate.
 */
public class Point {

  private double x;
  private double y;

  /**
   * Constructor for Point class.
   *
   * @param x - the given x coordinate.
   * @param y - the given y coordinate.
   */
  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Returns x coordinate.
   *
   * @return x coordinate.
   */
  public double getX() {
    return x;
  }

  /**
   * Returns y coordinate.
   *
   * @return y coordinate.
   */
  public double getY() {
    return y;
  }

  /**
   * Returns the euclidean distance (L2 norm) squared between two points. Note: This is the square
   * of the Euclidean distance, i.e. there's no square root.
   *
   * @param p1 - the point 1.
   * @param p2 - the point 2.
   * @return Returns the euclidean distance (L2 norm) squared between two points.
   */
  public static double distance(Point p1, Point p2) {
    return distance(p1.getX(), p2.getX(), p1.getY(), p2.getY());
  }

  private static double distance(double x1, double x2, double y1, double y2) {
    return Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2);
  }

  @Override
  public boolean equals(Object other) {
    if (other == null) {
      return false;
    }
    if (other.getClass() != this.getClass()) {
      return false;
    }
    Point otherPoint = (Point) other;
    return getX() == otherPoint.getX() && getY() == otherPoint.getY();
  }

  @Override
  public int hashCode() {
    return Double.hashCode(x) ^ Double.hashCode(y);
  }

  @Override
  public String toString() {
    return String.format("Point x: %.10f, y: %.10f", x, y);
  }
}
