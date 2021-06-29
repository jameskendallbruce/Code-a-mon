import java.util.concurrent.ThreadLocalRandom;

/**
 * Class to hold the actual functioning Monster to be used in battles and stored in TeamSlots.
 * @author James Kendall Bruce
 *
 */
public class MonsterImpl implements Monster {
    
    /**
     * General monster attributes and stats.
     */
    public Creature creature;
    public Evolution evolution = null;
    public Element type;
    public String name;
    public Stats stats;
    public MonsterMemento monMemo = null;
    
    /**
     * Individual stat bonuses. Not encounter dependent but also not permanent stat buffs/debuffs.
     * Also includes int variable to track the duration of the buff.
     */
    public double atkBuff = 1.0;
    public int atkTally = 0;
    public double defBuff = 1.0;
    public int defTally = 0;
    public double spdBuff = 1.0;
    public int spdTally = 0;
    public double crtBuff = 1.0;
    public int crtTally = 0;
    
    /**
     * Tracks the monster's level and experience progress.
     */
    public int level = 1;
    public int nextLevel = 5;
    public int currentExp = 0;
    
    

    /**
     * Basic constrcutor that randomizes the monster's build.
     */
    public MonsterImpl() {
        
        int rand1 = ThreadLocalRandom.current().nextInt(0, 4);
        int rand2 = ThreadLocalRandom.current().nextInt(0, 4);
        if (rand1 == 0) {
            type = Element.WATER;
        } else if (rand1 == 1) {
            type = Element.EARTH;
        } else if (rand1 == 2) {
            type = Element.FIRE;
        } else {
            type = Element.AIR;
        }
        if (rand2 == 0) {
            creature = Creature.CUB;
        } else if (rand2 == 1) {
            creature = Creature.PUP;
        } else if (rand2 == 2) {
            creature = Creature.TADPOLE;
        } else {
            creature = Creature.WHELP;
        }
        setStats();
        
        name = type.toString() + " " + creature.toString();
        createMemento(stats.health);
    }

    /**
     * Constructor to create a specific monster.
     * @param el -- the monster's elemental type.
     * @param creature -- the monster's base creature.
     */
    public MonsterImpl(Element el, Creature creature) {
        this.type = el;
        this.creature = creature;
        setStats();
    }

    /**
     * Set method that calculates stats based on the monster's attributes.
     */
    public void setStats() {
        stats = new Stats(type, creature);
        
    }
    
    /*
     * Set a monster's stats to match a specific stats instance.
     */
    public void setStats(Stats newStats) {
        stats = newStats;
    }
    
    /**
     * Method to increase a monster's stats as they level up.
     */
    public void levelUp() {
        level++;
        nextLevel = 10;
        final int atk = ThreadLocalRandom.current().nextInt(0, 5);
        final int def = ThreadLocalRandom.current().nextInt(0, 5);
        final int hlt = ThreadLocalRandom.current().nextInt(0, 5);
        final int crt = ThreadLocalRandom.current().nextInt(0, 7);
        final int spd = ThreadLocalRandom.current().nextInt(0, 5);
        
        double tempHp = getMemento().getHp();
        
        /**
         * Procedurally updates stats based on a random 0-4 roll. 0 through 2 does not update 
         * any stats. 3 or 4 raises the stat by 1.
         * Health always go up a base amount of 5 and a maximum of 10.
         * Crit only increases on a roll of 5 or higher.
         */
        if (atk > 2) {
            stats.attack++;
        }
        if (def > 2) {
            stats.defense++;
        }
        if (spd > 2) {
            stats.speed++;
        }
        if (crt > 4) {
            stats.crit++;
        }
        if (hlt > 3) {
            stats.health += 5;
            tempHp += 5;
        }
        
        stats.health += 5;
        tempHp += 5;
        
        createMemento(tempHp);
        if (checkEvolution()) {
            evolveMonster();
        }
        
    }
    
    /**
     * Checks whether the monster has reached an appropriate level to evolve.
     * @return true if monster is at or above level 10.
     */
    public boolean checkEvolution() {
        if (evolution == null) {
            if (level >= 10) {
                System.out.println(name + " is about to evolve!!!\n");
                return true;
            } else {
                return false;
            } 
        } else {
            return false;
        }
    }
    
    /**
     * Calculates and update's the monster's experience based off of the trainer's current level
     * and the level of the monster that was just defeated.
     * @param oppLevel -- experience to be calculated.
     */
    public void updateExp(int oppLevel) {
        
        int expGained = oppLevel * 5;
        currentExp += expGained;
        
        int expUntil = nextLevel - currentExp;
        
        System.out.println(name + " gained " + expGained + "xp.");
        
        if (checkLevelUp()) {
            levelUp();
            System.out.println("Leveled up to " + level + "!");
        }
        
        System.out.println(expUntil + "xp until the next level.");
    }
    
    /**
     * Checks whether a monster has gained enough experience to level up.
     * @return
     */
    public boolean checkLevelUp() {
        
        if (currentExp >= nextLevel) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Evolves the monster and stores it as an attribute.
     */
    public void evolveMonster() {
        evolution = new Evolution(this);
    }
    
    /**
     * Create a memento of this monster.
     */
    public void createMemento(double hp) {
        
        MonsterMemento memo = new MonsterMemento(hp);
        monMemo = memo;
        
    }
    
    /**
     * Get method to retrieve the monster's memento.
     */
    public MonsterMemento getMemento() {
        return monMemo;
    }
    
    /**
     * Restores the monster to the stored memento state.
     * Removes buffs/debuffs and heals up.
     */
    public void restoreMonster() {
        
        MonsterMemento memo = getMemento();
        this.stats.health = memo.getHp();
        this.atkBuff = memo.getAtkBuff();
        this.atkTally = memo.getAtkTally();
        this.defBuff = memo.getDefBuff();
        this.defTally = memo.getDefTally();
        this.spdBuff = memo.getSpdBuff();
        this.spdTally = memo.getSpdTally();
        this.crtBuff = memo.getCritBuff();
        this.crtTally = memo.getCritTally();
        
        createMemento(this.stats.health);
    }
    
    /**
     * Creates an attack based off of the monster's build.
     * @return -- an attack with appropriate attributes.
     */
    public Attack attack() {
        
        double attackDamage = 0;
        int attackNumber = 0;
        boolean selectionMade = false;
        
        while (!selectionMade) {
            attackNumber = ThreadLocalRandom.current().nextInt(0, 4);
            selectionMade = true;
        }

        String desc = "";
        Attack attack = null;

        if (evolution != null) {
            
            switch (evolution.evolved) {
            
                case BEAR:
                    if (attackNumber == 0) {
                        desc = " uses 'I'M A BIG BEAR!', temporarily increasing defense stat by 3.";
                        defBuff = 3;
                        defTally = 3;
                        attack = new Attack(0, Element.NORMAL);
                    } else if (attackNumber == 1) {
                        desc = " uses MOUNTAINOUS SWIPE!";
                        attack = new Attack(stats.attack, Element.EARTH);
                    } else if (attackNumber == 2) {
                        desc = " uses MAJOR URSA CHOMP.";
                        attack = new Attack(15, Element.NORMAL);
                    } else {
                        desc = " uses SLASHING " + type + " CLAWS! ";
                        attack = new Attack(stats.attack, type);
                    }
                    break;
                case HOUND:
                    if (attackNumber == 0) {
                        desc = " uses LUNAR HOWL, temporarily increasing attack stat by 3.";
                        atkBuff = 3;
                        atkTally = 5;
                        attack = new Attack(0, Element.NORMAL);
                    } else if (attackNumber == 1) {
                        desc = " uses SPICY BITE!";
                        attack = new Attack(stats.attack, Element.FIRE);
                    } else if (attackNumber == 2) {
                        desc = " uses QUICK ATTACK.";
                        attack = new Attack(15, Element.NORMAL);
                    } else {
                        desc = " uses " + evolution.mod + " HOUND " + type + " TACKLE! ";
                        attack = new Attack(stats.attack, type);
                    }
                    break;
                case TOAD:
                    if (attackNumber == 0) {
                        desc = " uses IMPOSSIBLE TO CATCH WHEN WET,"
                                + " temporarily increasing speed stat by 3.";
                        spdBuff = 3;
                        spdTally = 5;
                        attack = new Attack(0, Element.NORMAL);
                    } else if (attackNumber == 1) {
                        desc = " uses MONSTROUS HYDRO BLAST!";
                        attack = new Attack(stats.attack, Element.WATER);
                    } else if (attackNumber == 2) {
                        desc = " uses REALLY STICKY TONGUE.";
                        attack = new Attack(15, Element.NORMAL);
                    } else {
                        desc = " uses " + evolution.mod + " HYPER " + type + " HOP!";
                        attack = new Attack(stats.attack, type);
                    }
                    break;
                case DRAGON:
                    if (attackNumber == 0) {
                        desc = " uses REST ON MOUNTAIN OF GOLD, temporarily increasing crit "
                                + "stat by 10!";
                        crtBuff = 10;
                        crtTally = 5;
                        attack = new Attack(0, Element.NORMAL);
                    } else if (attackNumber == 1) {
                        desc = " uses WUMBO DRACONIC SWOOP!";
                        attack = new Attack(stats.attack, Element.AIR);
                    } else if (attackNumber == 2) {
                        desc = " uses SPIKY GIANT TAIL SWIPE!";
                        attack = new Attack(15, Element.NORMAL);
                    } else {
                        desc = " uses " + evolution.mod + " SONIC " + type + " BLAST!";
                        attack = new Attack(stats.attack, type);
                    }
                    break;
                default: 
                    System.out.println("Unknown evolution type.");
                    break;   
            }
            
        } else {
            
            switch (creature) {
            
                case CUB:
                    if (attackNumber == 0) {
                        desc = " uses BURLY HIDE, temporarily increasing defense stat by 1.";
                        defBuff++;
                        defTally = 3;
                        attack = new Attack(0, Element.NORMAL);
                    } else if (attackNumber == 1) {
                        desc = " uses LUMBERING TUMBLE!";
                        attack = new Attack(stats.attack, Element.EARTH);
                    } else if (attackNumber == 2) {
                        desc = " uses CHOMP.";
                        attack = new Attack(7, Element.NORMAL);
                    } else {
                        desc = " uses SLASHING " + type + " CLAWS! ";
                        attack = new Attack(stats.attack, type);
                    }
                    break;
                case PUP:
                    if (attackNumber == 0) {
                        desc = " uses LUNAR HOWL, temporarily increasing attack stat by 1.";
                        atkBuff++;
                        atkTally = 3;
                        attack = new Attack(0, Element.NORMAL);
                    } else if (attackNumber == 1) {
                        desc = " uses SPICY BITE!";
                        attack = new Attack(stats.attack, Element.FIRE);
                    } else if (attackNumber == 2) {
                        desc = " uses QUICK ATTACK.";
                        attack = new Attack(7, Element.NORMAL);
                    } else {
                        desc = " uses PUPPY " + type + " TACKLE! ";
                        attack = new Attack(stats.attack, type);
                    }
                    break;
                case TADPOLE:
                    if (attackNumber == 0) {
                        desc = " uses SLIPPERY WHEN WET, temporarily increasing speed stat by 1.";
                        spdBuff++;
                        spdTally = 3;
                        attack = new Attack(0, Element.NORMAL);
                    } else if (attackNumber == 1) {
                        desc = " uses HYDRO BLAST!";
                        attack = new Attack(stats.attack, Element.WATER);
                    } else if (attackNumber == 2) {
                        desc = " uses STICKY TONGUE.";
                        attack = new Attack(7, Element.NORMAL);
                    } else {
                        desc = " uses HYPER " + type + " HOP!";
                        attack = new Attack(stats.attack, type);
                    }
                    break;
                case WHELP:
                    if (attackNumber == 0) {
                        desc = " uses WAIT TO STRIKE, temporarily increasing crit stat by 5!";
                        crtBuff = 5;
                        crtTally = 2;
                        attack = new Attack(0, Element.NORMAL);
                    } else if (attackNumber == 1) {
                        desc = " uses DRACONIC SWOOP!";
                        attack = new Attack(stats.attack, Element.AIR);
                    } else if (attackNumber == 2) {
                        desc = " uses SPIKY BABY TAIL SWIPE!";
                        attack = new Attack(7, Element.NORMAL);
                    } else {
                        desc = " uses SONIC " + type + " BLAST!";
                        attack = new Attack(stats.attack, type);
                    }
                    break;
                default: 
                    System.out.println("Unknown creature type.");
                    break;
            }
        }
      
        System.out.println(name + desc);
        return attack;
    }
    
    /**
     * Generates a standard name.
     * Useful for converting wild monster's to tamed ones.
     */
    public void updateName() {
        name = type.toString() + " " + creature.toString();
        if (evolution != null) {
            name = evolution.mod.toString() + " " + name;
        }
    }
    
    /**
     * Mehtod to decrease all tallies after a round of combat.
     */
    public void decrementTallies() {
        if (atkTally > 0) {
            atkTally--;
        }
        if (defTally > 0) {
            defTally--;
        }
        if (spdTally > 0) {
            spdTally--;
        }
        if (crtTally > 0) {
            crtTally--;
        }
    }

}