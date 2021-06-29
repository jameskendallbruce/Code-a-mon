import java.util.LinkedList; 

public class MonCenterEncounter{
    
    LinkedList<TeamSlot> slots = null;
    
    Trainer visitor = null;
    
    Team team = null;
    
    public MonCenterEncounter(Trainer trnr) {
        visitor = trnr;
        team = visitor.getTeam();
        slots = team.slots;
        healTeam();
        visitor.setTeam(team);
    }
    
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