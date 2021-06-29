import static org.junit.Assert.*;

import org.junit.Before;

import org.junit.Test;

/**
 * Tests the Creature Class.
 * @author James Kendall Bruce
 *
 */
public class BattleTest {
    
    @Before
    public void setUp() throws Exception {
    }
    
    /**
     * Tests that everything passes in appropriately to a battle that a winner is declared.
     */
    @Test
    public void victorTest() {
        
        MonsterImpl mon1 = new MonsterImpl();
        MonsterImpl mon2 = new MonsterImpl();
        Environment environ = new Environment(Weather.NEUTRAL);

        BattleScenario battle = new BattleScenario(mon1, mon2, environ);
        
        MonsterImpl winner = null;
        
        battle.initiateBattle();
        
        winner = battle.victor;
        
        assert(winner != null);
    }
    
    /**
     * Tests the calculateDamage method.
     * Cannot test exact values since there is always a chance for a miss or a crit.
     * This test cannot pass 100% of the time however it is extremely unlikely to fail.
     */
    @Test
    public void waterVfireTest() {
        MonsterImpl mon1 = new MonsterImpl();
        mon1.type = Element.WATER;
        
        MonsterImpl mon2 = new MonsterImpl();
        mon2.type = Element.FIRE;
        
        Environment environ = new Environment(Weather.RAINY);
        BattleScenario battle = new BattleScenario(mon1, mon2, environ);
        Attack atk = new Attack(30.0, Element.WATER);
        
        double dmg = battle.calculateDamage(atk, mon1, mon2);
        
        assert(dmg > 0); 
    }
    
    /**
     * Tests the calculateDamage method.
     * Cannot test exact values since there is always a chance for a miss or a crit.
     * This test cannot pass 100% of the time however it is extremely unlikely to fail.
     */
    @Test
    public void fireVairTest() {
        MonsterImpl mon1 = new MonsterImpl();
        mon1.type = Element.FIRE;
        
        MonsterImpl mon2 = new MonsterImpl();
        mon2.type = Element.AIR;
        
        Environment environ = new Environment(Weather.SUNNY);
        BattleScenario battle = new BattleScenario(mon1, mon2, environ);
        Attack atk = new Attack(30.0, Element.FIRE);
        
        double dmg = battle.calculateDamage(atk, mon1, mon2);
        
        assert(dmg > 0); 
    }
    
    /**
     * Tests the calculateDamage method.
     * Cannot test exact values since there is always a chance for a miss or a crit.
     * This test cannot pass 100% of the time however it is extremely unlikely to fail.
     */
    @Test
    public void airVEarthTest() {
        MonsterImpl mon1 = new MonsterImpl();
        mon1.type = Element.AIR;
        
        MonsterImpl mon2 = new MonsterImpl();
        mon2.type = Element.EARTH;
        
        Environment environ = new Environment(Weather.WINDY);
        BattleScenario battle = new BattleScenario(mon1, mon2, environ);
        Attack atk = new Attack(30.0, Element.AIR);
        
        double dmg = battle.calculateDamage(atk, mon1, mon2);
        
        assert(dmg > 0); 
    }
    
    /**
     * Tests the calculateDamage method.
     * Cannot test exact values since there is always a chance for a miss or a crit.
     * This test cannot pass 100% of the time however it is extremely unlikely to fail.
     */
    @Test
    public void earthVwaterTest() {
        MonsterImpl mon1 = new MonsterImpl();
        mon1.type = Element.EARTH;
        
        MonsterImpl mon2 = new MonsterImpl();
        mon2.type = Element.WATER;
        
        Environment environ = new Environment(Weather.FOGGY);
        BattleScenario battle = new BattleScenario(mon1, mon2, environ);
        Attack atk = new Attack(30.0, Element.EARTH);
        
        double dmg = battle.calculateDamage(atk, mon1, mon2);
        
        assert(dmg > 0); 
    }
    
    /**
     * Tests the calculateDamage method.
     * Cannot test exact values since there is always a chance for a miss or a crit.
     * This test cannot pass 100% of the time however it is extremely unlikely to fail.
     * Confirms that a base monster cannot kill a legendary in 1 attack (even with a ton 
     * of buffs).
     */
    @Test
    public void earthVcelestialTest() {
        MonsterImpl mon1 = new MonsterImpl();
        for (int i =0; i < 25; i++) {
            mon1 = new MonsterImpl();
            Evolution evo = new Evolution(mon1);
        }
        mon1.atkBuff = 2;
        mon1.atkTally = 1;
        mon1.spdBuff = 2;
        mon1.spdTally = 1;
        mon1.crtBuff = 2;
        mon1.crtTally = 1;
        
        
        LegendaryMonster legend = LegendaryMonster.getLegend(mon1);
        MonsterImpl mon2 = legend.getMonster();
        
        Environment environ = new Environment(Weather.STORMY);
        BattleScenario battle = new BattleScenario(mon1, mon2, environ);
        Attack atk = new Attack(50.0, Element.NORMAL);
        
        double dmg = battle.calculateDamage(atk, mon1, mon2);
        
        assert(dmg > 0 && (mon2.stats.health > 0)); 
    }
    
    /**
     * Tests the calculateDamage method.
     * Cannot test exact values since there is always a chance for a miss or a crit.
     * This test cannot pass 100% of the time however it is extremely unlikely to fail.
     * Asserts that negative damage still deals 1.0.
     */
    @Test
    public void negativeTest() {
        MonsterImpl mon1 = new MonsterImpl();
        mon1.type = Element.EARTH;
        
        MonsterImpl mon2 = new MonsterImpl();
        mon2.type = Element.WATER;
        
        Environment environ = new Environment(Weather.FOGGY);
        BattleScenario battle = new BattleScenario(mon1, mon2, environ);
        Attack atk = new Attack(-30.0, Element.EARTH);
        
        double dmg = battle.calculateDamage(atk, mon1, mon2);
        
        assert(dmg == 1); 
    }
    
    /**
     * Tests the calculateDamage method.
     * Cannot test exact values since there is always a chance for a miss or a crit.
     * This test cannot pass 100% of the time however it is extremely unlikely to fail.
     * Asserts that 0.0 damage deals 0.0.
     */
    @Test
    public void zeroTest() {
        MonsterImpl mon1 = new MonsterImpl();
        mon1.type = Element.EARTH;
        
        MonsterImpl mon2 = new MonsterImpl();
        mon2.type = Element.WATER;
        
        Environment environ = new Environment(Weather.FOGGY);
        BattleScenario battle = new BattleScenario(mon1, mon2, environ);
        Attack atk = new Attack(0.0, Element.EARTH);
        
        double dmg = battle.calculateDamage(atk, mon1, mon2);
        
        assert(dmg == 0); 
    }
    
    /**
     * Tests the calculateDamage method.
     * Cannot test exact values since there is always a chance for a miss or a crit.
     * This test cannot pass 100% of the time however it is extremely unlikely to fail.
     * Asserts that missed attack still deals 0 damage.
     */
    @Test
    public void missTest() {
        MonsterImpl mon1 = new MonsterImpl();
        mon1.type = Element.EARTH;
        
        MonsterImpl mon2 = new MonsterImpl();
        mon2.type = Element.WATER;
        mon2.defBuff = 50.0;
        
        Environment environ = new Environment(Weather.FOGGY);
        BattleScenario battle = new BattleScenario(mon1, mon2, environ);
        Attack atk = new Attack(0.0, Element.EARTH);
        
        double dmg = battle.calculateDamage(atk, mon1, mon2);
        
        assert(dmg == 0.0); 
    }
}