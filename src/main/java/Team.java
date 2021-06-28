import java.util.LinkedList; 

/**
 * Class to hold the trainer's team of monsters.
 * Contains a linkedlist of the teamslots.
 * @author brantley_black
 *
 */
public class Team {
    
    Trainer trainer = null;
    
    LinkedList<TeamSlot> slots = new LinkedList<TeamSlot>();
    
    /**
     * Basic constructor.
     * @param t -- the trainer that the team belongs to.
     */
    public Team(Trainer t) {
        trainer = t;
    }
    
    /**
     * Retrieves the monster whose slotNumber matches the input.
     * @param num -- the number of the slot.
     * @return the TeamSlot holding the monster at the slot.
     */
    public TeamSlot getMonsterSlot(int num) {
        TeamSlot t = null;
        for (int i = 0; i < slots.size(); i++) {
            t = slots.get(i);
            if (t.getSlotNumber() == num) {
                return t;
            }
        }
        if (t == null) {
            System.out.println("No monster in that slot.");
        }
        return t;
    }
    
    /**
     * Adds a new monster into the Team's lineup.
     * @param slot -- the TeamSlot containing the monster that is being added.
     */
    public void addNewMonster(MonsterImpl mon) {
        TeamSlot slot = new TeamSlot(mon);
        int size = getTeamSize();
        if (size >= 6) {
            System.out.println("Your team is already full. Please delete a party member.");
        } else {
            int index = size + 1;
            slots.add(slot);
            slot.setSlotNumber(index);
        }
    }
    
    /**
     * Adds a monster into a specific slot within a team's lineup.
     * @param slot -- the TeamSlot containing the monster that is being added.
     * @param num -- the Slot # of the added TeamSlot (1 greater than the LinkedList index).
     */
    public void addNewMonsterAt(MonsterImpl mon, int num) {
        TeamSlot slot = new TeamSlot(mon);
        int size = getTeamSize();
        if (size >= 6) {
            System.out.println("Your team is already full. Please delete a party member.");
        } else if (num < 0 || num > 6){
            System.out.println("That team slot is not between 1 and 6.");
        } else {
            int index = num - 1;
            slots.add(index, slot);
            slot.setSlotNumber(num);
        }
    }
    
    /**
     * Get method tp determine the number of monster's on the team.
     * @return
     */
    public int getTeamSize() {
        return slots.size();
    }
}