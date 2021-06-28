/**
 * Abstract decorator class to create monster's evolution.
 * @author James Kendall Bruce
 *
 */
public abstract class MonsterEvolver implements Monster {
    
    protected MonsterImpl monster;
    
    public MonsterEvolver(MonsterImpl mon) {
        super();
        this.monster = mon;
    }
    
}