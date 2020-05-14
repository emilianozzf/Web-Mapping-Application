package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {

  private static Random r = new Random();

  private static KDTree buildLectureTree() {
    Point p1 = new Point(2, 3);
    Point p2 = new Point(4, 2);
    Point p3 = new Point(4, 2);
    Point p4 = new Point(4, 5);
    Point p5 = new Point(3, 3);
    Point p6 = new Point(1, 5);
    Point p7 = new Point(4, 4);

    KDTree kd = new KDTree(List.of(p1, p2, p3, p4, p5, p6, p7));
    return kd;
  }

  private static void buildTreeWithDoubles() {
    Point p1 = new Point(2, 3);
    Point p2 = new Point(2, 3);

    KDTree kd = new KDTree(List.of(p1, p2));
  }

  @Test
  public void testNearestDemoSlides() {
    KDTree kd = buildLectureTree();
    Point actual = kd.nearest(0, 7);
    Point expected = new Point(1, 5);
    assertEquals(expected, actual);
  }

  @Test
  public void testWith1000PointsAnd200Queries() {
    int pointCount = 1000;
    int queryCount = 200;
    testWithNPointsAndQQueries(pointCount, queryCount);
  }

  @Test
  public void testWith10000PointsAnd2000Queries() {
    int pointCount = 10000;
    int queryCount = 2000;
    testWithNPointsAndQQueries(pointCount, queryCount);
  }

  @Test
  public void timeWith10000PointsAnd2000Queries() {
    int pointCount = 10000;
    int queryCount = 2000;
    timeWithNPointsAndQQueries(pointCount, queryCount);
  }

  @Test
  public void timeWithVariousNumberOfPoints() {
    List<Integer> pointCounts = List.of(1000, 10000, 100000, 1000000);
    for (int N : pointCounts) {
      timeWithNPointsAndQQueries(N, 10000);
    }
  }

  @Test
  public void compareTimingOfNaiveVsKdTreeTheSpec() {
    List<Point> randomPoints = randomPoints(100000);
    KDTree kd = new KDTree(randomPoints);
    NaivePointSet nps = new NaivePointSet(randomPoints);
    List<Point> queryPoints = randomPoints(10000);

    Stopwatch sw = new Stopwatch();
    for (Point p : queryPoints) {
      nps.nearest(p.getX(), p.getY());
    }
    double time = sw.elapsedTime();
    System.out.println("Naive 10000 queries on 100000 points: " + time);

    sw = new Stopwatch();
    for (Point p : queryPoints) {
      kd.nearest(p.getX(), p.getY());
    }
    time = sw.elapsedTime();
    System.out.println("KD 10000 queries on 100000 points: " + time);
  }

  private void testWithNPointsAndQQueries(int pointCount, int queryCount) {
    List<Point> points = randomPoints(pointCount);
    NaivePointSet nps = new NaivePointSet(points);
    KDTree kd = new KDTree(points);

    List<Point> queries = randomPoints(queryCount);
    for (Point p : queries) {
      Point expected = nps.nearest(p.getX(), p.getY());
      Point actual = kd.nearest(p.getX(), p.getY());
      assertEquals(expected, actual);
    }
  }

  private void timeWithNPointsAndQQueries(int pointCount, int queryCount) {
    List<Point> points = randomPoints(pointCount);
    KDTree kd = new KDTree(points);

    Stopwatch sw = new Stopwatch();
    List<Point> queries = randomPoints(queryCount);
    for (Point p : queries) {
      Point actual =
          kd.nearest(p.getX(), p.getY());
    }
    System.out.println(
        "Time elapsed for " + queryCount + " queries on " + pointCount + " points: " + sw
            .elapsedTime());
  }

  private List<Point> randomPoints(int N) {
    List<Point> points = new ArrayList<>();
    for (int i = 0; i < N; i++) {
      points.add(randomPoint());
    }
    return points;
  }

  private Point randomPoint() {
    double x = r.nextDouble();
    double y = r.nextDouble();
    return new Point(x, y);
  }
}
