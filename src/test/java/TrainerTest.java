import static org.junit.Assert.*;

import org.junit.Before;

import org.junit.Test;

/**
 * Tests the Creature Class.
 * @author James Kendall Bruce
 *
 */
public class TrainerTest {
    
    @Before
    public void setUp() throws Exception {
    }
    
    /**
     * Tests that you cannot add more than 6 monsters
     */
    @Test
    public void maxTest() {
        
        Trainer trainer = new Trainer("TestDummy");
        
        Team team = new Team(trainer);
        
        MonsterImpl mon = new MonsterImpl();
        team.addNewMonster(mon);
        MonsterImpl mon2 = team.getMonsterSlot(1).getMonster();
        team.addNewMonsterAt(mon2, 1);
        
        for (int i = 0; i < 10; i++) {
            mon = new MonsterImpl();
            team.addNewMonster(mon);
            
        }
        
        trainer.setTeam(team);
        
        assert((team.getTeamSize() == 6) && (trainer.getTeam().getTeamSize()==6));
        
    }
    
    /**
     * Tests that you cannot add more than 6 monsters
     */
    @Test
    public void trainerDuel() {
        
        Trainer trainer = new Trainer("TestDummy");
        
        Team team = trainer.getTeam();
        
        MonsterImpl mon = new MonsterImpl();
        team.addNewMonster(mon);
        
        mon.atkBuff = 50;
        
        EncounterManager enc = new EncounterManager();
        
        for (int i = 0; i < 10; i++) {
            enc.handleEncounter(trainer);
        }
        
        assert(trainer.getWins() > 0);
        
    }
    
    /**
     * Tests that you cannot add more than 6 monsters
     */
    @Test
    public void specialDuel() {
        
        Trainer trainer = new Trainer("TestDummy");
        
        Team team = trainer.getTeam();
        
        MonsterImpl mon = new MonsterImpl();
        team.addNewMonster(mon);
                
        EncounterManager enc = new EncounterManager();
        
        enc.handleEncounter(trainer);
        
        enc.handleSpecialEncounter(trainer);

        assert(mon.stats.health <= 0);
    }
}