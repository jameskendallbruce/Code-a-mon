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
        MonsterImpl mon1 = new MonsterImpl();
        String name1 = mon1.type.toString() +  mon1.creature.toString();
        System.out.println("Monster 1: " + name1);
        Stats m1S = mon1.stats;
        System.out.println("HP: " + m1S.health + " DEF: " + m1S.defense);
        System.out.println("ATK: " + m1S.attack + " SPD: " + m1S.speed);
        System.out.println("CRT: " + m1S.crit + "\n");
        mon1.levelUp();
        System.out.println(name1 + " leveled up!!\n");
        System.out.println("HP: " + m1S.health + " DEF: " + m1S.defense);
        System.out.println("ATK: " + m1S.attack + " SPD: " + m1S.speed);
        System.out.println("CRT: " + m1S.crit + "\n");
        
        MonsterImpl mon2 = new MonsterImpl();
        String name2 = mon2.type.toString() + mon2.creature.toString();
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
    }
}