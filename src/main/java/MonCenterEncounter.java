import java.util.LinkedList; 

/**
 * Class to run a visit to the Monster Center where the monsters are each healed up fully 
 * and buffs are dropped.
 * @author James Kendall Bruce
 *
 */
public class MonCenterEncounter {
    
    LinkedList<TeamSlot> slots = null;
    
    Trainer visitor = null;
    
    Team team = null;
    
    /**
     * Constructor that recognizes who is visiting the center.
     * @param trnr -- the visiting trainer.
     */
    public MonCenterEncounter(Trainer trnr) {
        visitor = trnr;
        team = visitor.getTeam();
        slots = team.slots;
        healTeam();
        visitor.setTeam(team);
    }
    
    /**
     * Method to loop throught the trainer's team and return all monsters to their defautl states.
     */
    public void healTeam() {
        for (int i = 0; i < slots.size(); i++) {
            MonsterImpl mon = slots.get(i).getMonster();
            System.out.println(mon.name + "'s HP: " + mon.stats.health);
            mon.restoreMonster();
            System.out.println("    ......\n*** HEALED ***\n    ......\n\n" + mon.name + "'s HP: "
                    + mon.stats.health);
        }
        System.out.println("Your team is as good as new!");
    }
    
}