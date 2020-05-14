package bearmaps;

import java.util.List;

/**
 * A naive linear-time solution to solve the problem of finding the closest point to a given
 * coordinate. Immutable.
 */
public class NaivePointSet implements PointSet {

  private List<Point> points;

  /**
   * Constructor for NaivePointSet.
   *
   * @param points - the given list of points.
   */
  public NaivePointSet(List<Point> points) {
    this.points = points;
  }

  /**
   * Returns the point in the set nearest to x, y.
   * <p>
   * This should take θ(N) time where N is the number of points.
   *
   * @param x - the given x coordinate.
   * @param y - the given y coordinate.
   * @return the point in the set nearest to x, y.
   */
  @Override
  public Point nearest(double x, double y) {
    Point goal = new Point(x, y);
    Point minPoint = points.get(0);
    double minDistance = Point.distance(minPoint, goal);

    for (Point point : points) {
      double distance = Point.distance(point, goal);
      if (distance < minDistance) {
        minPoint = point;
        minDistance = distance;
      }
    }

    return minPoint;
  }

  public static void main(String[] args) {
    Point p1 = new Point(1.1, 2.2);
    Point p2 = new Point(3.3, 4.4);
    Point p3 = new Point(-2.9, 4.2);

    NaivePointSet nn = new NaivePointSet(List.of(p1, p2, p3));
    Point ret = nn.nearest(3.0, 4.0);
    System.out.println(ret);
    System.out.println(ret.getX());
    System.out.println(ret.getY());
  }
}
