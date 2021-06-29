import static org.junit.Assert.*;

import org.junit.Before;

import org.junit.Test;

/**
 * Tests the Creature Class.
 * @author James Kendall Bruce
 *
 */
public class StatsTest {
    
    @Before
    public void setUp() throws Exception {
    }
    
    /**
     * Tests that everything passes in appropriately to a Stats object.
     * A default Stats class does not have the same attributes of a specific Stats class.
     * They will start with the same stats but end different.
     */
    @Test
    public void constructorTests() {
        
        Stats stats1 = new Stats();
        
        double hp1 = stats1.health;
        
        Stats stats2 = new Stats(Element.WATER, Creature.CUB);
        stats2 = new Stats(Element.FIRE, Creature.PUP);
        stats2 = new Stats(Element.AIR, Creature.TADPOLE);
        stats2 = new Stats(Element.CELESTIAL, Creature.WHELP);
        stats2 = new Stats(Element.EARTH, Creature.WHELP);
        
        
        
        assertTrue((stats1 != stats2) && (hp1 == stats1.health));
        
    }
}