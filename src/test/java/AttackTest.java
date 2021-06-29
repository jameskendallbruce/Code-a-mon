import static org.junit.Assert.*;

import org.junit.Before;

import org.junit.Test;

/**
 * Tests the Attack Class.
 * @author James Kendall Bruce
 *
 */
public class AttackTest {
    
    @Before
    public void setUp() throws Exception {
    }
    
    /**
     * Tests that everything passes in appropriately to an attack.
     */
    @Test
    public void constructorTest() {
        
        Attack atk = new Attack(25.0, Element.FIRE);
        assertTrue((atk instanceof Attack) && (atk.damage instanceof Double)
                && (atk.type instanceof Element));
        
    }
    
    /**
     * Testing the alternate way to create an attack (through MonsterImpl).
     */
    @Test
    public void altConstructorTest() {
        
        MonsterImpl mon1 = new MonsterImpl();
        Attack atk = mon1.attack();
        for (int i = 0; i < 25; i++) {
            mon1 = new MonsterImpl();
            atk = mon1.attack();
        }
        
        assertTrue((atk instanceof Attack) && (atk.damage instanceof Double)
                && (atk.type instanceof Element));
    }
}