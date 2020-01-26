package creatures;

import huglife.Action;
import huglife.Action.ActionType;
import huglife.Creature;
import huglife.Direction;
import huglife.HugLifeUtils;
import huglife.Occupant;
import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class Clorus extends Creature {
  private int r;
  private int g;
  private int b;
  private double moveProbability = 0.5;

  public Clorus(double e) {
    super("clorus");
    super.energy = e;
    this.r = 34;
    this.g = 0;
    this.b = 231;
  }

  public Clorus() {
    this(1);
  }

  public Color color() {
    return color(this.r, this.g, this.b);
  }

  public void attack(Creature c) {
    super.energy += c.energy();
  }

  public void move() {
    super.energy -= 0.03;
  }

  public void stay() {
    super.energy -= 0.01;
  }

  public Clorus replicate() {
    super.energy *= 0.5;
    return new Clorus(super.energy);
  }

  public Action chooseAction(Map<Direction, Occupant> neighbors) {
    // Rule 1
    Deque<Direction> emptyNeighbors = new ArrayDeque<>();
    Deque<Direction> plipNeighbors = new ArrayDeque<>();
    for (Direction direction : neighbors.keySet()) {
      Occupant occupant = neighbors.get(direction);
      if (occupant.name().equals("empty")) {
        emptyNeighbors.add(direction);
      }
      else if (occupant.name().equals("plip")) {
        plipNeighbors.add(direction);
      }
    }
    if (emptyNeighbors.isEmpty()) {
      return new Action(ActionType.STAY);
    }

    // Rule 2
    // HINT: randomEntry(emptyNeighbors)
    if (! plipNeighbors.isEmpty()) {
      return new Action(ActionType.ATTACK, HugLifeUtils.randomEntry(plipNeighbors));
    }

    // Rule 3
    if (super.energy >= 1) {
      return new Action(ActionType.REPLICATE, HugLifeUtils.randomEntry(emptyNeighbors));
    }

    // Rule 4
    return new Action(ActionType.MOVE, HugLifeUtils.randomEntry(emptyNeighbors));
  }
}
