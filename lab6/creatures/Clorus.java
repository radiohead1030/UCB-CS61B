package creatures;
import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.HugLifeUtils;
import java.awt.Color;
import java.util.Map;
import java.util.List;

public class Clorus extends Creature {
    /** red color. */
    private int r;
    /** green color. */
    private int g;
    /** blue color. */
    private int b;
    
    /** creates plip with energy equal to E. */
    public Clorus(double e) {
        super("clorus");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    /** creates a clorus with energy equal to 1. */
    public Clorus() {
        this(1);
    }

    public Color color() {
	r = 34;
        g = 0;
	b = 231;
        return color(r, g, b);
    }

    public void attack(Creature c) {
	    energy += c.energy();
    }
    
    public void move() {
	    energy -= 0.03;
    }
    
    public void stay() {
	    energy -= 0.01;
    }
    
    public Clorus replicate() {
	energy = energy * 0.5;
        return new Clorus(energy);
    }
    
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        List<Direction> empties = getNeighborsOfType(neighbors, "empty");
        List<Direction> plips = getNeighborsOfType(neighbors, "plip");
	if(empties.size() == 0){
		return new Action(Action.ActionType.STAY);
	}
	else if(plips.size() >= 1){
                Direction moveDir = HugLifeUtils.randomEntry(plips);
		return new Action(Action.ActionType.ATTACK, moveDir);
	}
	else {
                Direction moveDir = HugLifeUtils.randomEntry(empties);
		if(energy >= 1)
			return new Action(Action.ActionType.REPLICATE, moveDir);
		else
			return new Action(Action.ActionType.MOVE, moveDir);
	}
    }
}
