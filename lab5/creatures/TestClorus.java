package creatures;
import huglife.Action.ActionType;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.awt.Color;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.Impassible;
import huglife.Empty;

/** Tests the clorus class
 *  @authr Emiliano Zhu
 */

public class TestClorus {
  @Test
  public void testBasics() {
    Clorus c = new Clorus(2);
    assertEquals("clorus", c.name());
    assertEquals(2, c.energy(), 0.01);
    assertEquals(new Color(34, 0, 231), c.color());
  }

  @Test
  public void testAttack() {
    Clorus c = new Clorus(1);
    Plip p = new Plip(1);
    c.attack(p);
    assertEquals(2, c.energy(), 0.01);
  }

  @Test
  public void testReplicate() {
    Clorus c = new Clorus(1);
    Clorus cReplicate = c.replicate();
    assertNotEquals(c, cReplicate);
    assertEquals(0.5, c.energy(), 0.01);
    assertEquals(0.5, cReplicate.energy(), 0.01);
  }

  @Test
  public void testChoose() {
    // No empty adjacent spaces; stay.
    Clorus c = new Clorus(1);
    HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
    surrounded.put(Direction.TOP, new Impassible());
    surrounded.put(Direction.BOTTOM, new Impassible());
    surrounded.put(Direction.LEFT, new Impassible());
    surrounded.put(Direction.RIGHT, new Impassible());

    Action actual = c.chooseAction(surrounded);
    Action expected = new Action(Action.ActionType.STAY);

    assertEquals(expected, actual);

    // Any Plips are seen; attack one of them randomly.
    c = new Clorus(1);
    HashMap<Direction, Occupant> topPlips = new HashMap<Direction, Occupant>();
    topPlips.put(Direction.TOP, new Plip(1));
    topPlips.put(Direction.BOTTOM, new Empty());
    topPlips.put(Direction.LEFT, new Impassible());
    topPlips.put(Direction.RIGHT, new Impassible());

    actual = c.chooseAction(topPlips);
    expected = new Action(Action.ActionType.ATTACK, Direction.TOP);

    assertEquals(expected, actual);


    // Energy >= 1; replicate to a random empty square.
    c = new Clorus(1);
    HashMap<Direction, Occupant> topEmpty = new HashMap<Direction, Occupant>();
    topEmpty.put(Direction.TOP, new Empty());
    topEmpty.put(Direction.BOTTOM, new Impassible());
    topEmpty.put(Direction.LEFT, new Impassible());
    topEmpty.put(Direction.RIGHT, new Impassible());

    actual = c.chooseAction(topEmpty);
    expected = new Action(ActionType.REPLICATE, Direction.TOP);

    assertEquals(expected, actual);


    // Energy < 1; move to a random empty square.
    c = new Clorus(0.5);
    topEmpty = new HashMap<Direction, Occupant>();
    topEmpty.put(Direction.TOP, new Empty());
    topEmpty.put(Direction.BOTTOM, new Impassible());
    topEmpty.put(Direction.LEFT, new Impassible());
    topEmpty.put(Direction.RIGHT, new Impassible());

    actual = c.chooseAction(topEmpty);
    expected = new Action(ActionType.MOVE, Direction.TOP);

    assertEquals(expected, actual);
  }
}
