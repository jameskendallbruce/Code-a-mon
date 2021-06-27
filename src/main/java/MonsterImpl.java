import java.util.concurrent.ThreadLocalRandom;

public class MonsterImpl implements Monster {
    
    public Creature creature;
    public Type type;
    public String name;
    public Stats stats;
    public double weatherBonus = 1.0; 
    public double typeBonus = 1.0;

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
    }

    public MonsterImpl(Type type, Creature creature) {
        this.type = type;
        this.creature = creature;
        setStats();
    }


    public void setStats() {
        stats = new Stats(type, creature);
        
    }
    
    public void setStats(Stats newStats) {
        stats = newStats;
    }
    
    /**
     * Method to increase a monster's stats as they level up.
     */
    public void levelUp() {
        int atk = ThreadLocalRandom.current().nextInt(0, 5);
        int def = ThreadLocalRandom.current().nextInt(0, 5);
        int hlt = ThreadLocalRandom.current().nextInt(0, 5);
        int crt = ThreadLocalRandom.current().nextInt(0, 5);
        int spd = ThreadLocalRandom.current().nextInt(0, 5);
        
        /**
         * Procedurally updates stats based on a random 0-4 roll. 0 through 2 does not update anything.
         * 3 raises the stat by 10. 4 raises the stat by 20.
         * Health always go up a base amount of 5.
         * Crit only increases 1 point on a roll of 3 or 4 and nothing on any other roll.
         */
        if (atk > 2) {
            stats.attack +=((atk -2)*10);
        }
        if (def > 2) {
            stats.defense += ((def -2)*10);
        }
        if (hlt > 2) {
            stats.health += ((hlt -2)*10);
        } else if (hlt <= 2){
            stats.health+=5;
        }
        if (spd > 2) {
            stats.speed += ((spd -2)*10);
        }
        if (crt > 3) {
            stats.crit++;
        }
    }

    
    public enum Type {
        WATER, EARTH, FIRE, AIR
    }
    
    public enum Creature {
        CUB, PUP, TADPOLE, WHELP
    }
}