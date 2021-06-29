/**
 * Class method to hold a memento of MonsterImpl adjustable stats.
 * Can be used to store and restore values.
 * @author James Kendall Bruce
 *
 */
public class MonsterMemento {
    
    /*
     * Stats to be saved and restored at Monster Center.
     */
    protected double monsterHp;
    protected double atkBuff = 1.0;
    protected int atkTally = 0;
    protected double defBuff = 1.0;
    protected int defTally = 0;
    protected double spdBuff = 1.0;
    protected int spdTally = 0;
    protected double crtBuff = 1.0;
    protected int crtTally = 0;
    
    /*
     * Basic constructor that stores the previous buffs and full hp.
     */
    public MonsterMemento(double hp) {
        this.monsterHp = hp;
    }
    
    /*
     * Basic get method for health.
     */
    public double getHp() {
        return monsterHp;
    }

    /*
     * Basic get method for attack buff.
     */
    public double getAtkBuff() {
        return atkBuff;
    }
    
    /*
     * Basic get method for attack buff tally.
     */
    public int getAtkTally() {
        return atkTally;
    }
    
    /*
     * Basic get method for defense buff.
     */
    public double getDefBuff() {
        return defBuff;
    }

    /*
     * Basic get method for defense buff tally.
     */
    public int getDefTally() {
        return defTally;
    }
    
    /*
     * Basic get method for speed buff.
     */
    public double getSpdBuff() {
        return spdBuff;
    }
    
    /*
     * Basic get method for speed buff tally.
     */
    public int getSpdTally() {
        return spdTally;
    }
    
    /*
     * Basic get method for crit buff.
     */
    public double getCritBuff() {
        return crtBuff;
    }

    /*
     * Basic get method for crit buff tally.
     */
    public int getCritTally() {
        return crtTally;
    }
    
}