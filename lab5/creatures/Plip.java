package creatures;

import huglife.Action.ActionType;
import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.HugLifeUtils;
import huglife.Occupant;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

/**
 * An implementation of a motile pacifist photosynthesizer.
 *
 * @author Josh Hug
 */
public class Plip extends Creature {

    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;
    /**
     * probability of taking a move when ample space available.
     */
    private double moveProbability = 0.5;

    private void validateEnergy() {
        if (super.energy > 2) {
            super.energy = 2;
        }
        if (super.energy < 0) {
            super.energy = 0;
        }
    }

    /**
     * creates plip with energy equal to E.
     */
    public Plip(double e) {
        super("plip");
        super.energy = e;
        this.validateEnergy();
        this.r = 99;
        this.g = (int) (96 * super.energy + 63);
        this.b = 76;
    }

    /**
     * creates a plip with energy equal to 1.
     */
    public Plip() {
        this(1);
    }

    /**
     * Should return a color with red = 99, blue = 76, and green that varies
     * linearly based on the energy of the Plip. If the plip has zero energy,
     * it should have a green value of 63. If it has max energy, it should
     * have a green value of 255. The green value should vary with energy
     * linearly in between these two extremes. It's not absolutely vital
     * that you get this exactly correct.
     */
    public Color color() {
        return color(this.r, this.g, this.b);
    }

    /**
     * Do nothing with C, Plips are pacifists.
     */
    public void attack(Creature c) {
        // do nothing.
    }

    /**
     * Plips should lose 0.15 units of energy when moving. If you want to
     * to avoid the magic number warning, you'll need to make a
     * private static final variable. This is not required for this lab.
     */
    public void move() {
        super.energy -= 0.15;
        this.validateEnergy();
    }


    /**
     * Plips gain 0.2 energy when staying due to photosynthesis.
     */
    public void stay() {
        super.energy += 0.2;
        this.validateEnergy();
    }

    /**
     * Plips and their offspring each get 50% of the energy, with none
     * lost to the process. Now that's efficiency! Returns a baby
     * Plip.
     */
    public Plip replicate() {
        super.energy *= 0.5;
        this.validateEnergy();
        return new Plip(super.energy);
    }

    /**
     * Plips take exactly the following actions based on NEIGHBORS:
     * 1. If no empty adjacent spaces, STAY.
     * 2. Otherwise, if energy >= 1, REPLICATE towards an empty direction
     * chosen at random.
     * 3. Otherwise, if any Cloruses, MOVE with 50% probability,
     * towards an empty direction chosen at random.
     * 4. Otherwise, if nothing else, STAY
     * <p>
     * Returns an object of type Action. See Action.java for the
     * scoop on how Actions work. See SampleCreature.chooseAction()
     * for an example to follow.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        // Rule 1
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        boolean anyClorus = false;
        for (Direction direction : neighbors.keySet()) {
            Occupant occupant = neighbors.get(direction);
            if (occupant.name().equals("empty")) {
                emptyNeighbors.add(direction);
            }
            else if (occupant.name().equals("chlorus")) {
                anyClorus = true;
            }
        }
        if (emptyNeighbors.isEmpty()) {
            return new Action(ActionType.STAY);
        }

        // Rule 2
        // HINT: randomEntry(emptyNeighbors)
        if (super.energy() >= 1) {
            return new Action(ActionType.REPLICATE, HugLifeUtils.randomEntry(emptyNeighbors));
        }

        // Rule 3
        if (anyClorus && Math.random() < this.moveProbability) {
            return new Action(ActionType.MOVE, HugLifeUtils.randomEntry(emptyNeighbors));
        }

        // Rule 4
        return new Action(Action.ActionType.STAY);
    }
}
