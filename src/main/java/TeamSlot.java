/**
 * Takes up 1 of the 6 slots on a trainer's team.
 * @author James Kendall Bruce
 *
 */
public class TeamSlot {
    
    private MonsterImpl monster = null;
    private Evolution evolution = null;
    private int slotNumber = 0;
    
    /**
     * Basic constructor.
     * @param mon -- monster to be held in the slot.
     */
    public TeamSlot(MonsterImpl mon) {
        monster = mon;
    }
    
    /**
     * Updates the evolution for the monster in the teamslot.
     * @param ev -- the Evolution instance.
     */
    public void setEvolution(Evolution ev) {
        evolution = ev;
    }
    
    /**
     * Get method to retrieve the monster in the slot.
     */
    public MonsterImpl getMonster() {
        if (evolution != null) {
            return evolution.monster;
        } else {
            return monster;
        }
    }
    
    /**
     * Checks whether the monster has evolved yet.
     * @return true if there is an evolution already stored in this teamslot.
     */
    public boolean checkEvolution() {
        if (evolution != null ) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Basic get method to return the placement of this slot in the team.
     * @return int value of the placement.
     */
    public int getSlotNumber() {
        return slotNumber;
    }
    
    /**
     * Basic set method to update the slotNumber for a different placement in the team.
     * @param num -- new placement in the team.
     */
    public void setSlotNumber(int num) {
        if (0 < num && num < 7) {
            slotNumber = num;
        } else {
            System.out.println("Invalid slot number for this team slot.");
        }
    }
    
}