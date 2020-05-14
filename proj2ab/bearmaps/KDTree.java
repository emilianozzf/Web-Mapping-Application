package bearmaps;

import java.util.List;

/**
 * An immutable k-d tree class implements PointSet interface. It only works for the 2-dimensional
 * case, i.e. when our points have only x and y coordinates.
 */
public class KDTree implements PointSet {

  private Node root;

  /**
   * Private helper class Node representing a tree node.
   */
  private static class Node {

    private Point point;
    private Node left;
    private Node right;

    /**
     * Constructor for Node.
     *
     * @param point - the given point.
     */
    public Node(Point point) {
      this.point = point;
      this.left = null;
      this.right = null;
    }
  }

  /**
   * Constructor for KDTree class.
   *
   * @param points - the given list of points.
   */
  public KDTree(List<Point> points) {
    for (Point p : points) {
      root = add(root, p, true);
    }
  }

  private Node add(Node n, Point p, boolean isHorizontal) {
    if (n == null) {
      return new Node(p);
    }

    int cmp = compare(p, n.point, isHorizontal);
    if (cmp < 0) {
      n.left = add(n.left, p, !isHorizontal);
    } else if (cmp > 0) {
      n.right = add(n.right, p, !isHorizontal);
    } else {
      n.point = p;
    }
    return n;
  }

  private int compare(Point p1, Point p2, boolean isHorizontal) {
    int xCmp = Double.compare(p1.getX(), p2.getX());
    int yCmp = Double.compare(p1.getY(), p2.getY());

    if (xCmp == 0 && yCmp == 0) {
      return 0;
    }

    if (isHorizontal) {
      if (xCmp != 0) {
        return xCmp;
      } else {
        return 1;
      }
    } else {
      if (yCmp != 0) {
        return yCmp;
      } else {
        return 1;
      }
    }
  }

  /**
   * Returns the point in the set nearest to x, y.
   * <p>
   * This should take O(logN) time on average, where N is the number of points.
   *
   * @param x - the given x coordinate.
   * @param y - the given y coordinate.
   * @return the point in the set nearest to x, y.
   */
  @Override
  public Point nearest(double x, double y) {
    return nearest(root, new Point(x, y), root, true).point;
  }

  private Node nearest(Node n, Point goal, Node best, boolean isHorizontal) {
    if (n == null) {
      return best;
    }

    if (Point.distance(n.point, goal) < Point.distance(best.point, goal)) {
      best = n;
    }

    Node goodSide;
    Node badSide;
    if (compare(goal, n.point, isHorizontal) < 0) {
      goodSide = n.left;
      badSide = n.right;
    } else {
      goodSide = n.right;
      badSide = n.left;
    }

    best = nearest(goodSide, goal, best, !isHorizontal);
    best = nearest(badSide, goal, best, !isHorizontal);
    return best;
  }
}
