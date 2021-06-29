import java.util.concurrent.ThreadLocalRandom;

/**
 * Manager class to handle the various types of encounters.
 * @author James Kendall Bruce
 *
 */
public class EncounterManager {

    public Encounter currE = null;
    
    public Encounter prevE = null;
    
    public DayCycle cycle = DayCycle.MIDNIGHT;
    
    public Weather weather = Weather.SUNNY;
    
    public Environment environ = null;
    
    /**
     * Randomly picks a new encounter type.
     */
    public void selectEncounter() {
        int rand = ThreadLocalRandom.current().nextInt(0, 5);
        
        prevE = currE;
        
        /**
         * Cycles throught the possible encounter types. A trainer cannot have 2 trainer challenges or
         * mon center visits in a row.
         */
        if (prevE == Encounter.MON_CENTER) {
            rand = ThreadLocalRandom.current().nextInt(1, 5);
        }
        if (rand == 0) {
            currE = Encounter.MON_CENTER;
        } else if (rand == 1) {
            if (prevE != Encounter.TRAINER_CHALLENGE) {
                currE = Encounter.TRAINER_CHALLENGE;
            } else {
                currE = Encounter.RANDOM_ENCOUNTER;
            }
        } else {
            currE = Encounter.RANDOM_ENCOUNTER;
        }
            
    }
    
    /**
     * Method to handle a new trainer encounter.
     */
    public void handleEncounter(Trainer player){
        
        selectEncounter();
        
        updateDayCycle();
        
        updateWeather();
        
        setEnvironment(weather);
        
        System.out.println(getEncounterString());
        
        if (currE == Encounter.MON_CENTER) {
            new MonCenterEncounter(player);
        } else if (currE == Encounter.TRAINER_CHALLENGE) {
            player.getTeam().getMonsterSlot(0).getMonster().attack();
        } else if (currE == Encounter.RANDOM_ENCOUNTER) {
            player.getTeam().getMonsterSlot(0).getMonster().attack();
        } else {
            player.getTeam().getMonsterSlot(0).getMonster().attack(); 
        }
        
    }
    
    /**
     * Progresses the day cycle.
     */
    public void updateDayCycle() {
        
        if (cycle == DayCycle.MIDNIGHT) {
            cycle = DayCycle.DAWN;
            System.out.print("Looks like it's dawn now ");
        } else if (cycle == DayCycle.DAWN) {
            cycle = DayCycle.MIDDAY;
            System.out.print("You've reached midday ");     
        } else if (cycle == DayCycle.MIDDAY) {
            cycle = DayCycle.DUSK;
            System.out.print("The sun is going down. It's dusk out ");
        } else {
            cycle = DayCycle.MIDNIGHT;
            System.out.print("Spooky. The clock has struck midnight "); 
        }
    }
    
    /**
     * Creates a string describing the type of encounter.
     * @return the encounter description.
     */
    public String getEncounterString() {
        
        String res = null;
        
        if (currE == Encounter.MON_CENTER) {
            res = "You've found a Monster Center. Let's heal up!\n";
        } else if (currE == Encounter.TRAINER_CHALLENGE) {
            res = "You've ran into another trainer! Prepare to battle your opponent!\n";
        } else if (currE == Encounter.RANDOM_ENCOUNTER) {
            res = "A wild monster has appeared!\n";
        } else {
            res = "\n\n???\n...\nSeems like you've found a special encounter.\n"
                    + "...\nLet's hope that you're ready!\n\n";        }
        return res;
    }
    
    /**
     * Selects a new random weather and prints a description of it.
     */
    public void updateWeather() {
        
        int rand = ThreadLocalRandom.current().nextInt(0, 6);
        
        if (rand == 0) {
            if (cycle == DayCycle.DAWN || cycle == DayCycle.MIDDAY) {
                weather = Weather.SUNNY;
                System.out.println("and it's bright and sunny.");
            } else {
                weather = Weather.CLEAR;
                System.out.println("and the sky is nice and clear.");
            }
        } else if (rand == 1) {
            weather = Weather.FOGGY;
            System.out.println("and a thick fog is coming in.");
        } else if (rand == 2) {
            weather = Weather.RAINY;
            System.out.println("and a gentle rain is coming down.");
        } else if (rand == 3){
            weather = Weather.WINDY;
            System.out.println("and a drafty breeze is coming through.");
        } else {
            weather = Weather.NEUTRAL;
            System.out.println("and the weather is oddly calm.");  
        }
    }
    
    /**
     * Passes the current weather to generate an Environment (determines weather buffs/debuffs)
     * @param w -- the current weather of the Encounter.
     */
    public void setEnvironment(Weather w) {
        
        environ = new Environment(w);
        
    }
    
    /**
     * List of day parts in the day cycle.
     */
    public enum DayCycle {
        
        DAWN, MIDDAY, DUSK, MIDNIGHT
        
    }
}