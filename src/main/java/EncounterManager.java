import java.util.LinkedList; 
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
    
    public boolean gameOver = false;
    
    LegendaryMonster legend = null;
    MonsterImpl legendMon = null;
    String legendName = null;
    
    /**
     * Randomly picks a new encounter type.
     */
    public void selectEncounter() {
        int rand = ThreadLocalRandom.current().nextInt(0, 5);
        
        prevE = currE;
        
        /**
         * Cycles throught the possible encounter types. A trainer cannot have 2 trainer 
         * challenges or mon center visits in a row.
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
    public void handleEncounter(Trainer player) {
        
        MonsterImpl firstMon = player.getTeam().slots.get(0).getMonster();
        
        legend = LegendaryMonster.getLegend(firstMon);
        legendMon = legend.getMonster();
        legendName = legendMon.name;
        
        System.out.println("\n*=======================================*\n");
        
        System.out.println("There's a legend out there..." + "\nthe elusive " + legendName + "...");
        
        System.out.println("\n*=======================================*\n");
        
        selectEncounter();
        
        if (checkLoss(player.team)) {
            System.out.println("Looks like you can't do anything with these fainted monsters.");
            System.out.println("...\nLet's go straight to a Monster Center!");
            System.out.println("...\nWait you have no money?");
            int wins = player.getWins();
            if (wins > 1) {
                player.winCount--;
                System.out.println(player.trainerName + " wins: " + wins + "--> " 
                        + player.winCount);
                currE = Encounter.MON_CENTER;
                System.out.println("It's gonna take a little longer to find that "
                        + "elusive " + legendName + "...");
            } else {
                System.out.println("Well, I guess it's on us just this one time...");
                currE = Encounter.MON_CENTER;
            }

            
        }
        
        updateDayCycle();
        
        updateWeather();
        
        setEnvironment(weather);
        
        System.out.println(getEncounterString());
        
        if (currE == Encounter.MON_CENTER) {
            new MonCenterEncounter(player);
        } else if (currE == Encounter.TRAINER_CHALLENGE) {
            player.getTeam().getMonsterSlot(0).getMonster().attack();
        } else if (currE == Encounter.RANDOM_ENCOUNTER) {
            runRandomEncounter(player);
            //player.getTeam().getMonsterSlot(0).getMonster().attack();
        } else {
            player.getTeam().getMonsterSlot(0).getMonster().attack(); 
        }
        
    }
    
    /**
     * Runs a specific combat scenario against a rare legendary monster.
     * Winnin scenarios are very uncommon.
     * @param player -- the trainer entering the combat.
     */
    public void handleSpecialEncounter(Trainer player) {
        currE = Encounter.LEGENDARY_ENCOUNTER;
        updateDayCycle();
        
        updateSpecialWeather();
        
        setEnvironment(weather);
        
        System.out.println(getEncounterString());
        
        Team team = player.getTeam();
        
        MonsterImpl mon = getRandomMonster(team);
        
        BattleScenario battle = new BattleScenario(mon, legendMon, environ);
        battle.initiateBattle();
        System.out.println("\n*=======================================*\n");
        if (mon == battle.victor) {
            mon.updateExp((player.getWins() + legendMon.level));
            System.out.println(legendName + "was defeated by " + player.trainerName + "'s "
                    + mon.name + "!");
            System.out.println("\n" + player.trainerName + " is attempting to catch " 
                    + legendName + ".");
            System.out.println(player.trainerName + "caught " + legendName + "!\n");
        } else {
            System.out.println(legendName + " was the victor!");
            System.out.println(mon.stats.health + " health left? \nSeems like a pretty "
                    + "futile effort.");
            System.out.println("\nGAME OVER!!\n");
        }
        System.out.println("\n*=======================================*\n");
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
                    + "...\nLet's hope that you're ready!\n\n";
        }
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
        } else if (rand == 3) {
            weather = Weather.WINDY;
            System.out.println("and a drafty breeze is coming through.");
        } else {
            weather = Weather.NEUTRAL;
            System.out.println("and the weather is oddly calm.");  
        }
    }
    
    /**
     * Changes the weather to the specific environment for a legendary encounter.
     */
    public void updateSpecialWeather() {
        weather = Weather.STORMY;
        System.out.println("and the sky looks very ominous. Almost supernaturally so.");
        System.out.println("It looks like the sky is about to open up.\n");
    }
    
    /**
     * Passes the current weather to generate an Environment (determines weather buffs/debuffs).
     * @param w -- the current weather of the Encounter.
     */
    public void setEnvironment(Weather w) {
        
        environ = new Environment(w);
        
    }
    
    /**
     * Runs a random encounter between a trainer and wild monster.
     * @param player -- the trainer in the encounter.
     */
    public void runRandomEncounter(Trainer player) {
        
        Team team = player.getTeam();
        
        MonsterImpl mon = getRandomMonster(team);
        
        MonsterImpl random = new MonsterImpl();
        random.name = "WILD " +  random.name;
        int trainerLvl = mon.level;
        int ranLvl = ThreadLocalRandom.current().nextInt(0, 3);
        
        if (trainerLvl > 1) {
            
            if (ranLvl == 0) {
                for (int i = 0; i < trainerLvl - 1; i++) {
                    random.levelUp();
                }
            } else if (ranLvl == 1) {
                for (int i = 0; i < trainerLvl; i++) {
                    random.levelUp();
                }
            } else if (ranLvl == 2) {
                for (int i = 0; i < trainerLvl + 1; i++) {
                    random.levelUp();
                }
            }

        }
        
        BattleScenario battle = new BattleScenario(mon, random, environ);
        battle.initiateBattle();
        if (mon == battle.victor) {
            mon.updateExp((player.getWins() + random.level));
            if (team.getTeamSize() < 6) {
                System.out.println("\n" + player.trainerName + " is attempting to catch "
                        + random.name + ".");
                int catchChance = ThreadLocalRandom.current().nextInt(0, 3);
                System.out.println("...");
                if (catchChance == 0) {
                    team.addNewMonster(random);
                    random.updateName();
                    System.out.println(player.trainerName + "caught " + random.name + "!\n");
                    player.getTeam().printTeam();
                } else {
                    System.out.println(random.name + " escaped.");
                }
            }
        }
        
    }
    
    /**
     * Runs a trainer v, trainer battle scenario. The opposing trainer is randomly selected from
     * a list of 3.
     * @param player -- incoming trainer that is the initial attacker.
     */
    public void runTrainerChallenge(Trainer player) {
        
        Trainer opp = null;
        
        Trainer taako = new Trainer("Taako");
        
        Trainer merle = new Trainer("Merle");
        
        Trainer magnus = new Trainer("Magnus");
        
        int ranOpp = ThreadLocalRandom.current().nextInt(0, 3);
        int ranLvl = ThreadLocalRandom.current().nextInt(0, 3);
        
        Team team = player.getTeam();
        MonsterImpl mon = getRandomMonster(team);
        int monLvl = mon.level;
        
        MonsterImpl random = new MonsterImpl();
        if (monLvl > 1) {
            
            if (ranLvl == 0) {
                for (int i = 0; i < monLvl - 1; i++) {
                    random.levelUp();
                }
            } else if (ranLvl == 1) {
                for (int i = 0; i < monLvl; i++) {
                    random.levelUp();
                }
            } else if (ranLvl == 2) {
                for (int i = 0; i < monLvl + 1; i++) {
                    random.levelUp();
                }
            }

        }
        if (ranOpp == 0) {
            opp = taako;
        } else if (ranOpp == 1) {
            opp = merle;
        } else {
            opp = magnus;
        }
        random.name = opp.trainerName + "'s " + random.name;
        opp.getTeam().addNewMonster(random);
        
        BattleScenario battle = new BattleScenario(mon, random, environ);
        battle.initiateBattle();
        if (mon == battle.victor) {
            mon.updateExp((player.getWins() + random.level));
            System.out.println("SUCCESS!\n" + player.trainerName);
            System.out.println(player.trainerName + "defeated " + opp.trainerName + ".");
        }
        
    }
    
    /**
     * List of day parts in the day cycle.
     */
    public enum DayCycle {
        
        DAWN, MIDDAY, DUSK, MIDNIGHT
        
    }
    
    /**
     * Checks whether the trainer's whole team has fainted.
     * @param team -- the trainer's team.
     * @return -- true if all party members have fainted.
     */
    public boolean checkLoss(Team team) {
        LinkedList<TeamSlot> monsters = team.slots;
        boolean res = true;
        for (int i = 0; i < team.getTeamSize(); i++) {
            MonsterImpl mon = monsters.get(i).getMonster();
            if (mon.stats.health > 0) {
                res = false;
                return res;
            }
        }
        return res;
    }
    
    public MonsterImpl getRandomMonster(Team team) {
        LinkedList<TeamSlot> monsters = team.slots;
        MonsterImpl mon = null;
        int size = team.getTeamSize();
        int idx = ThreadLocalRandom.current().nextInt(0, size);
        mon = monsters.get(idx).getMonster();
        return mon;
    }
}