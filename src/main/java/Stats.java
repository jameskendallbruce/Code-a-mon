import java.util.concurrent.ThreadLocalRandom;

/**
 * Class to store a monster's stats --determined by the monster's other attributes.
 * @author James Kendal Bruce
 *
 */
public class Stats {

    double attack;
    double defense;
    double health;
    double crit;
    double speed;

    /**
     * Constructor that generates generic base stats wihtout any other specific attributes
     * accounted for.
     */
    public Stats() {
        attack = 2;
        defense = 2;
        health = 50;
        crit = 2;
        speed = 2;
    }

    /**
     * Constructor that used the monster's type and creature attributes to determine
     * the values of the stats.
     * @param type -- the monster's elemental type.
     * @param creature -- the monster's base creature.
     */
    public Stats(Element type, Creature creature) {
        
        switch (creature) {
            case CUB:
                attack = 1;
                defense = 3;
                health = 50;
                crit = 3;
                speed = 1;
                break;
            case PUP:
                attack = 3;
                defense = 2;
                health = 50;
                crit = 2;
                speed = 2;
                break;
            case TADPOLE:
                attack = 2;
                defense = 1;
                health = 65;
                crit = 3;
                speed = 3;
                break;
            case WHELP:
                attack = 2;
                defense = 3;
                health = 40;
                crit = 5;
                speed = 2;
                break;
            default:
                attack = 2;
                defense = 2;
                health = 50;
                crit = 2;
                speed = 2;
        }
        
        addType(type);

    }
    
    /**
     * Method to increase 1 stat based off of the monster's type.
     * @param type -- new elemental type.
     */
    public void addType(Element type) {
        switch (type) {
            case WATER:
                health += 5;
                break;
            case EARTH:
                defense += 1;
                break;
            case FIRE:
                attack += 1;
                break;
            case AIR:
                crit += 1;
                break;
            default:
                break;
        }
    }
    
}