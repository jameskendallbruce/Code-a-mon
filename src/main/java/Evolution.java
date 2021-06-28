import java.util.concurrent.ThreadLocalRandom;

/**
 * The class for evolved monsters. It contains some special attributres and modified methods.
 * Stores a copy of the monster that is evolving and extends the MonsterEvolver decorator.
 * @author James Kendall Bruce
 *
 */
public class Evolution extends MonsterEvolver {
    
    public EvolvedCreature evolved;
    
    public Modifier mod;
    
    /**
     * Basic constructor that sets the monster to be evolved.
     * @param mon -- the monster that is evolving.
     */
    public Evolution(MonsterImpl mon) {
        super(mon);
        Creature creat = monster.creature;
        setEvolved(creat);
        setMod();
        updateName();
    }
    
    /**
     * Method to determine and update stats based off of the creature that is evolving.
     * @param creat
     */
    public void setEvolved(Creature creat) {
        Stats myStats = this.monster.stats;
        switch (creat) {
            case CUB:
                evolved = EvolvedCreature.BEAR;
                myStats.attack += 1;
                myStats.defense += 2;
                myStats.health += 10;
                myStats.speed += 1;
                myStats.crit += 3;
                break;
            case PUP:
                evolved = EvolvedCreature.HOUND;
                myStats.attack += 3;
                myStats.defense += 2;
                myStats.health += 5;
                myStats.speed += 3;
                myStats.crit += 1;
                break;
            case TADPOLE:
                evolved = EvolvedCreature.TOAD;
                myStats.attack += 1;
                myStats.defense += 1;
                myStats.health += 10;
                myStats.speed += 3;
                myStats.crit += 3;
                break;
            case WHELP:
                evolved = EvolvedCreature.DRAGON;
                myStats.attack += 1;
                myStats.defense += 2;
                myStats.health += 5;
                myStats.speed += 3;
                myStats.crit += 5;
                break;
        }
        
    }
    
    /**
     * Sets the evolutions stats to match the monster's stats.
     */
    public void setStats() {
        monster.setStats();
    }
    
    /**
     * Determines what new abilities the monster has gained from the evolution.
     */
    public void setMod() {
        
        int rand = ThreadLocalRandom.current().nextInt(0, 4);
        Stats myStats = this.monster.stats;

        if (rand == 0) {
            mod = Modifier.ATOMIC;
            myStats.attack += 2;
        } else if (rand == 1) {
            mod = Modifier.WERE;
            myStats.speed += 2;
        } else if (rand == 2) {
            mod = Modifier.ROBO;
            myStats.defense += 2;
        } else {
            mod = Modifier.ELDRITCH;
            myStats.health += 5;
        }
        
    }
    
    /**
     * Modified levelUp method to account for the monster's evolved state.
     */
    public void levelUp() {
        this.monster.levelUp();
        Stats myStats = this.monster.stats;
        
        if (mod == Modifier.ATOMIC) {
            myStats.attack += 1;
        } else if(mod == Modifier.WERE) {
            myStats.speed += 1;
        } else if(mod == Modifier.ROBO) {
            myStats.defense += 1;
        } else if(mod == Modifier.ELDRITCH) {
            myStats.health += 5;
        }
    }
    
    /**
     * Changes to monster's name to have an evolved name.
     */
    public void updateName() {
        String tString = monster.type.toString();
        String cString = evolved.toString();
        String mString = mod.toString();
        monster.name = mString + " " + tString + " " + cString;
    }
}