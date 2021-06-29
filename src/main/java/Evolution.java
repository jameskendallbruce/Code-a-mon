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
     * Specific constructor for generating and evolved legend.
     * @param mon -- the legendary monster that is evolving.
     */
    public Evolution(MonsterImpl mon, Modifier legendMod) {
        super(mon);
        Creature creat = monster.creature;
        setEvolved(creat);
        setMod(legendMod);
        updateName();
    }
    
    /**
     * Method to determine and update stats based off of the creature that is evolving.
     * @param creat
     */
    public void setEvolved(Creature creat) {
        MonsterImpl mon = this.monster;
        Stats myStats = mon.stats;
        double tempHp = mon.getMemento().getHp();
        switch (creat) {
            case CUB:
                evolved = EvolvedCreature.BEAR;
                myStats.attack += 1;
                myStats.defense += 2;
                myStats.health += 10;
                tempHp += 10;
                myStats.speed += 1;
                myStats.crit += 3;
                mon.createMemento(tempHp);
                break;
            case PUP:
                evolved = EvolvedCreature.HOUND;
                myStats.attack += 3;
                myStats.defense += 2;
                myStats.health += 5;
                tempHp += 5;
                myStats.speed += 3;
                myStats.crit += 1;
                mon.createMemento(tempHp);
                break;
            case TADPOLE:
                evolved = EvolvedCreature.TOAD;
                myStats.attack += 1;
                myStats.defense += 1;
                myStats.health += 10;
                tempHp += 10;
                myStats.speed += 3;
                myStats.crit += 3;
                mon.createMemento(tempHp);
                break;
            case WHELP:
                evolved = EvolvedCreature.DRAGON;
                myStats.attack += 1;
                myStats.defense += 2;
                myStats.health += 5;
                tempHp += 5;
                myStats.speed += 3;
                myStats.crit += 5;
                mon.createMemento(tempHp);
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
        
        MonsterImpl mon = this.monster;
        int rand = ThreadLocalRandom.current().nextInt(0, 4);
        Stats myStats = mon.stats;
        double tempHp = mon.getMemento().getHp();

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
            mod = Modifier.HERCULEAN;
            myStats.health += 5;
            tempHp += 5;
        }
        mon.createMemento(tempHp);
    }
    
    /**
     * Determines what new abilities the monster has gained from the evolution.
     */
    public void setMod(Modifier pMod) {
        
        MonsterImpl mon = this.monster;
        mod = pMod;
        Stats myStats = mon.stats;
        double tempHp = mon.getMemento().getHp();

        if (mod == Modifier.ELDRITCH) {
            myStats.attack += 5;
            myStats.defense += 5;
            myStats.speed += 5;
            myStats.health += 20;
            myStats.crit += 5;
        } else if (mod == Modifier.ATOMIC) {
            myStats.attack += 2;
        } else if (mod == Modifier.WERE) {
            myStats.speed += 2;
        } else if (mod == Modifier.ROBO) {
            myStats.defense += 2;
        } else {
            myStats.health += 5;
            tempHp += 5;
        }
        mon.createMemento(tempHp);
    }
    
    /**
     * Modified levelUp method to account for the monster's evolved state.
     */
    public void levelUp() {
        
        MonsterImpl mon = this.monster;
        mon.levelUp();
        Stats myStats = mon.stats;
        double tempHp = mon.getMemento().getHp();
        
        if (mod == Modifier.ATOMIC) {
            myStats.attack += 1;
        } else if(mod == Modifier.WERE) {
            myStats.speed += 1;
        } else if(mod == Modifier.ROBO) {
            myStats.defense += 1;
        } else if(mod == Modifier.HERCULEAN) {
            myStats.health += 5;
            tempHp += 5;
        }
        mon.createMemento(tempHp);
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