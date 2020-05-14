package bearmaps;

/**
 * Represents a set of points.
 */
public interface PointSet {

  /**
   * Returns the point in the set nearest to x, y.
   *
   * @param x - the given x coordinate.
   * @param y - the given y coordinate.
   * @return the point in the set nearest to x, y.
   */
  Point nearest(double x, double y);
}
