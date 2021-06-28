import java.util.concurrent.ThreadLocalRandom;

public class MonsterImpl implements Monster {
    
    public Creature creature;
    public Evolution evolution;
    public Type type;
    public String name;
    public Stats stats;
    public double weatherBonus = 1.0; 
    public double typeBonus = 1.0;
    public int level = 1;
    public int nextLevel = 10;
    public int currentExp = 0;
    

    /**
     * Basic constrcutor that randomizes the monster's build.
     */
    public MonsterImpl() {
        
        int rand1 = ThreadLocalRandom.current().nextInt(0, 4);
        int rand2 = ThreadLocalRandom.current().nextInt(0, 4);
        if (rand1 == 0) {
            type = Type.WATER;
        } else if (rand1 == 1) {
            type = Type.EARTH;
        } else if (rand1 == 2) {
            type = Type.FIRE;
        } else {
            type = Type.AIR;
        }
        if (rand2 == 0) {
            creature = Creature.CUB;
        } else if (rand2 == 1) {
            creature = Creature.PUP;
        } else if (rand2 == 2) {
            creature = Creature.TADPOLE;
        } else {
            creature = Creature.WHELP;
        }
        setStats();
        
        name = type.toString() + " " + creature.toString();
    }

    /**
     * Constructor to create a specific monster.
     * @param type -- the monster's elemental type.
     * @param creature -- the monster's base creature.
     */
    public MonsterImpl(Type type, Creature creature) {
        this.type = type;
        this.creature = creature;
        setStats();
    }

    /**
     * Set method that calculates stats based on the monster's attributes.
     */
    public void setStats() {
        stats = new Stats(type, creature);
        
    }
    
    /*
     * Set a monster's stats to match a specific stats instance.
     */
    public void setStats(Stats newStats) {
        stats = newStats;
    }
    
    /**
     * Method to increase a monster's stats as they level up.
     */
    public void levelUp() {
        level++;
        nextLevel = nextLevel*2;
        int atk = ThreadLocalRandom.current().nextInt(0, 5);
        int def = ThreadLocalRandom.current().nextInt(0, 5);
        int hlt = ThreadLocalRandom.current().nextInt(0, 5);
        int crt = ThreadLocalRandom.current().nextInt(0, 7);
        int spd = ThreadLocalRandom.current().nextInt(0, 5);
        
        /**
         * Procedurally updates stats based on a random 0-4 roll. 0 through 2 does not update anything.
         * 3 or 4 raises the stat by 1.
         * Health always go up a base amount of 5 and a maximum of 10.
         * Crit only increases on a roll of 5 or higher.
         */
        if (atk > 2) {
            stats.attack++;
        }
        if (def > 2) {
            stats.defense++;
        }
        if (hlt > 3) {
            stats.health += 5;
        }
        stats.health+=5;
        if (spd > 2) {
            stats.speed++;
        }
        if (crt > 4) {
            stats.crit++;
        }
        
        if (checkEvolution()) {
            evolveMonster();
        }
    }
    /**
     * Checks whether the monster has reached an appropriate level to evolve.
     * @return true if monster is at or above level 10.
     */
    public boolean checkEvolution() {
        if (evolution == null) {
            if (level >= 10) {
                System.out.println(name + " is about to evolve!!!\n");
                return true;
            } else {
                return false;
            } 
        } else {
            return false;
        }
    }
    
    /**
     * Calculates and update's the monster's experience based on the level of the monster that it just defeated.
     * @param oppLevel
     */
    public void updateExp(int oppLevel) {
        
        int expGained = oppLevel*3;
        currentExp += expGained;
        
        if (checkLevelUp()) {
            levelUp();
        }
    }
    
    /**
     * Checks whether a monster has gained enough experience to level up.
     * @return
     */
    public boolean checkLevelUp() {
        
        if (currentExp >= nextLevel) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Evolves the monster and stores it as an attribute.
     */
    public void evolveMonster() {
        evolution = new Evolution(this);
    }

}