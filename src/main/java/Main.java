/**
* This is the base core class that will run the entire application.
* Currently it is just prints a teaser page [v.1 (06/18/2021)].
* @author James Kendall Bruce
*/
public class Main {
    /**
     * Current placeholder method that teases the application to come.
     * @param args --default arguments.
     */
    public static void main(String[] args) {
        System.out.println("\n=========================="
                + "\n||      Code-a-mon      ||\n|| Gotta catch 'em all! "
                + "||\n|| Coming June 28, 2021 ||\n==========================\n");
        
        Trainer angus = new Trainer("Angus");
        
        Team team = angus.team;
        
        MonsterImpl newMon1 = new MonsterImpl();
        team.addNewMonster(newMon1);
        
        TeamSlot mon1slot = team.getMonsterSlot(1);
        MonsterImpl mon1 = mon1slot.getMonster();
        
        EncounterManager encMan = new EncounterManager();
                
        System.out.println("\n" + angus.trainerName + ", you are about to start your monster catching journey.\n");
        
        
        String name1 = mon1.name;
        System.out.println(angus.trainerName + ", here's an untrained " + name1 + "!"
                + "\n It's dangerous to go alone!\n");
        Stats m1S = mon1.stats;
        System.out.println(angus.trainerName + "'s " + name1 + "'s initial stats:");
        System.out.println("HP: " + m1S.health + " DEF: " + m1S.defense);
        System.out.println("ATK: " + m1S.attack + " SPD: " + m1S.speed);
        System.out.println("CRT: " + m1S.crit);
        
        encMan.handleEncounter(angus);
        encMan.handleEncounter(angus);
        encMan.handleEncounter(angus);
        encMan.handleEncounter(angus);
        
        while (mon1.evolution == null) {
            encMan.handleEncounter(angus);
            if (mon1.evolution != null) {
                String name1ev = mon1.name;
                Stats m1evS = mon1.stats;
                System.out.println(angus.trainerName + "'s " + name1 + " evolved into " + name1ev + "\n");
                System.out.println("HP: " + m1evS.health + " DEF: " + m1evS.defense);
                System.out.println("ATK: " + m1evS.attack + " SPD: " + m1evS.speed);
                System.out.println("CRT: " + m1evS.crit + "\n");
                System.out.println("\n*=======================================*\n");
                System.out.println("\n...\nSomething is happening...\n...");
                encMan.handleSpecialEncounter(angus);
            }

            
            //encMan.handleEncounter(angus);
            //encMan.handleEncounter(angus);
            
            /*
            else if (mon1.level > currentLevel) {
                System.out.println(name1 + " leveled up!!\n");
                System.out.println("HP: " + m1S.health + " DEF: " + m1S.defense);
                System.out.println("ATK: " + m1S.attack + " SPD: " + m1S.speed);
                System.out.println("CRT: " + m1S.crit + "\n");    
            }
            */
            
            
        }
        /*
        mon1.levelUp();

        
        Evolution mon1ev = new Evolution(mon1);
        String name1ev = mon1ev.monster.name.toString();
        Stats m1evS = mon1ev.monster.stats;
        System.out.println(name1 + " evolved into " + name1ev);
        System.out.println("HP: " + m1evS.health + " DEF: " + m1evS.defense);
        System.out.println("ATK: " + m1evS.attack + " SPD: " + m1evS.speed);
        System.out.println("CRT: " + m1evS.crit + "\n");
        
        System.out.println("\nSECOND MONSTER:");
        
        MonsterImpl mon2 = new MonsterImpl();
        String name2 = mon2.name;
        System.out.println("Monster 2: " + name2);
        Stats m2S = mon2.stats;
        System.out.println("HP: " + m2S.health + " DEF: " + m2S.defense);
        System.out.println("ATK: " + m2S.attack + " SPD: " + m2S.speed);
        System.out.println("CRT: " + m2S.crit + "\n");
        mon2.levelUp();
        System.out.println(name2 + " leveled up!!\n");
        System.out.println("HP: " + m2S.health + " DEF: " + m2S.defense);
        System.out.println("ATK: " + m2S.attack + " SPD: " + m2S.speed);
        System.out.println("CRT: " + m2S.crit + "\n");
        
        Evolution mon2ev = new Evolution(mon2);
        String name2ev = mon2ev.monster.name.toString();
        Stats m2evS = mon2ev.monster.stats;
        System.out.println(name2 + " evolved into " + name2ev);
        System.out.println("HP: " + m2evS.health + " DEF: " + m2evS.defense);
        System.out.println("ATK: " + m2evS.attack + " SPD: " + m2evS.speed);
        System.out.println("CRT: " + m2evS.crit + "\n");
        */
        
    }
}