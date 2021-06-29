import static org.junit.Assert.*;

import org.junit.Before;

import org.junit.Test;

/**
 * Tests the Legendary Class.
 * @author James Kendall Bruce
 *
 */
public class LegendaryTest {
    
    @Before
    public void setUp() throws Exception {
    }
    
    /**
     * Tests that any instance of LegendaryMonster always refers to the same object.
     */
    @Test
    public void singletonTest() {
        MonsterImpl mon1 = new MonsterImpl();
        mon1.type = Element.EARTH;
        
        MonsterImpl mon2 = new MonsterImpl();
        mon1.type = Element.WATER;
        
        LegendaryMonster legend1 = LegendaryMonster.getLegend(mon1);
        LegendaryMonster legend2 = LegendaryMonster.getLegend(mon2);
        
        assertEquals(legend1, legend2);
    }
}