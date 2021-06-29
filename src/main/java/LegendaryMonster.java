/**
 * Singlton class to hold a Celestial, Eldritch creature matching the evolved creature of the 
 * trainer's first monster.
 * @author James Kendall Bruce
 *
 */
public class LegendaryMonster {
    
    private Modifier mod;
    
    private Element type;
    
    private Evolution evolution;
    
    private MonsterImpl monster;
    
    private static LegendaryMonster legend = null;
    
    /**
     * Private constructor that creates a wildly powerful monster.
     * @param mon -- the type of monster that the legend will be.
     */
    private LegendaryMonster(MonsterImpl mon) {
        Creature critter = mon.creature;
        mod = Modifier.ELDRITCH;
        type = Element.CELESTIAL;
        monster = new MonsterImpl(type, critter);
        monster.createMemento(monster.stats.health);
        int maxLevel = 10;
        for (int i = 0; i < maxLevel; i++) {
            monster.levelUp(); 
        }
        evolution = new Evolution(monster, mod);
        evolution.mod = mod;
    }
    
    /**
     * Static get method that retrieves the legendary monster (generates 1 if it doesn't exists).
     * @param mon -- the monster to determine the base type.
     * @return -- a singleton instance of the legendary monster.
     */
    public static LegendaryMonster getLegend(MonsterImpl mon) {
        if (legend == null) {
            legend = new LegendaryMonster(mon);
        }
        return legend;
    }
    
    /**
     * Gets the MonsterImpl with the legendary attributes.
     * @return -- the legendary MonsterImpl.
     */
    public MonsterImpl getMonster() {
        return evolution.monster;
    }
    
}