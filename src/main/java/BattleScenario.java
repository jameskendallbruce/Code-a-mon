import java.util.concurrent.ThreadLocalRandom;

/**
 * Class to run the actual battles.
 * @author James Kendall Bruce (adapted from Mascotmon SER322 application)
 *
 */
public class BattleScenario {

    MonsterImpl mon1;
    MonsterImpl mon2;
    Environment environ;
    MonsterImpl victor;

    /**
     * Constructor to generate a specific battle scenario.
     * @param parMon1 -- First attacking monster.
     * @param parMon2 -- First defending monster.
     * @param env -- the environment of the battle (includes buffs and weather).
     */
    public BattleScenario(MonsterImpl parMon1, MonsterImpl parMon2, Environment env) {
        setMon1(parMon1);
        setMon2(parMon2);
        environ = env;
    } 


    /**
     * Mehtod to start the battle.
     */
    public void initiateBattle() {

        System.out.println(mon1.name + " vs. " + mon2.name);

        MonsterImpl winner = fight();
        
        victor = winner;
        
        System.out.println(winner.name + " has won with " + winner.stats.health + " health left");
        
    }

    /**
     * Runs the turn taking combat aspect of battle.
     */
    public MonsterImpl fight() {
        int round = 1;
        double damage1;
        double damage2;

        Attack attack1;
        Attack attack2;

        while (true) {

            System.out.println("\n" + mon1.name + " launches an attack against " + mon2.name + "!");
            
            attack1 = mon1.attack();
            
            damage1 = calculateDamage(attack1, mon1, mon2);
            System.out.println(damage1 + " damage dealt");

            mon2.stats.health = mon2.stats.health - damage1;
            System.out.println(mon2.name + " has " + mon2.stats.health + " health left");

            if (mon2.stats.health <= 0.0) {
                System.out.println(mon2.name + " has fainted.");
                return mon1;
            }
            mon2.decrementTallies();

            System.out.println("\n" + mon2.name  + " launches an attack against " 
                    + mon1.name + "!");
            
            attack2 = mon2.attack();

            damage2 = calculateDamage(attack2, mon2, mon1);
            System.out.println(damage2 + " damage dealt");

            mon1.stats.health = mon1.stats.health - damage2;
            System.out.println(mon1.name + " has " + mon1.stats.health + " health left.");

            if (mon1.stats.health <= 0.0) {
                System.out.println(mon1.name + " has fainted.");
                return mon2;
            }
            mon1.decrementTallies();
            round++;
            
        }
    }

    /**
     * Set method for first attacker.
     * @param parMon --first attacking monster.
     */
    public void setMon1(MonsterImpl parMon) {
        mon1 = parMon;
    }

    /**
     * Set method for first defender.
     * @param parMon --first defending monster.
     */
    public void setMon2(MonsterImpl parMon) {
        mon2 =  parMon;
    }

    /**
     * Very different attack calculation. Damage is mostly the same as in Mascotmon but the "attack
     * roll" uses DnD logic with armorClass and a D20 roll.
     * @param parAttack is the attack value given to the method where that attack value is based on
     *      the monsters damage value
     * @param parAttacker the attacking monster
     * @param parDefender the defending monster (the defending monster will never get damage)
     *      to calculate damage output.
     * @return total damage output
     */
    public double calculateDamage(Attack parAttack, MonsterImpl parAttacker, 
            MonsterImpl parDefender) {
        
        // stab is Pokemon slang for Same-Type-Attack-Bonus
        double stab = 1.0;
        double atkBuff = parAttacker.atkBuff;
        double defBuff = parDefender.defBuff;
        double atkSpdB = parAttacker.spdBuff;
        double defSpdB = parDefender.spdBuff;
        
        // Buff variables refer to the weather buff/debuff for attacker and defender
        double attackBuff = 1.0;
        double defendBuff = 1.0;
        
        // Adv variables refer to the type advantage/disadvantage for attacker and defender
        double attackAdv = 1.0;
        double defendAdv = 1.0;
        
        // variable to hold the calculated damage-- starts as the attack input
        double atk = parAttack.damage;
        
        double dmg = 0.0;
        
        if (parAttack.damage == 0.0) {
            return 0.0;
        } else {
            //checking for STAB
            if (parAttacker.type == parAttack.type) {
                stab = 1.2;
            }
            
            // updating Weather buffs/debuffs
            if (parAttacker.type == environ.buff) {
                attackBuff = environ.buffMod;
            } else if (parAttacker.type == environ.debuff) {
                attackBuff = environ.debuffMod;
            }
            
            if (parDefender.type == environ.buff) {
                defendBuff = environ.buffMod;
            } else if (parDefender.type == environ.debuff) {
                defendBuff = environ.debuffMod;
            }
            
            // updating type advantages/disadvantages
            if (parAttacker.type == Element.WATER && parDefender.type == Element.FIRE) {
                attackAdv = 1.25;
                defendAdv = 0.75;
            } else if (parAttacker.type == Element.FIRE && parDefender.type == Element.WATER) {
                attackAdv = 0.75;
                defendAdv = 1.25;
            } else if (parAttacker.type == Element.EARTH && parDefender.type == Element.WATER) {
                attackAdv = 1.25;
                defendAdv = 0.75;
            } else if (parAttacker.type == Element.WATER && parDefender.type == Element.EARTH) {
                attackAdv = 0.75;
                defendAdv = 1.25;
            } else if (parAttacker.type == Element.AIR && parDefender.type == Element.EARTH) {
                attackAdv = 1.25;
                defendAdv = 0.75;
            } else if (parAttacker.type == Element.EARTH && parDefender.type == Element.AIR) {
                attackAdv = 0.75;
                defendAdv = 1.25;
            } else if (parAttacker.type == Element.FIRE && parDefender.type == Element.AIR) {
                attackAdv = 1.25;
                defendAdv = 0.75;
            } else if (parAttacker.type == Element.AIR && parDefender.type == Element.FIRE) {
                attackAdv = 0.75;
                defendAdv = 1.25;
            }
            
            Stats atkStats = parAttacker.stats;
            Stats defStats = parDefender.stats;
            
            
            double d20Roll = ThreadLocalRandom.current().nextInt(1, 21);
            double atkRoll = d20Roll + atkStats.attack + atkStats.speed + atkBuff + atkSpdB;
            double armorClass = defStats.defense + defStats.speed + defBuff + defSpdB;
            double critRoll = ThreadLocalRandom.current().nextInt(1, 100) + atkStats.crit
                    + parAttacker.crtBuff;
            if (atkRoll >= armorClass) {
                
                dmg = Math.round((((atkRoll + atk + atkBuff) * parAttacker.level) * stab
                        * attackBuff * attackAdv) - (defStats.defense * defendBuff * defendAdv));
                if (critRoll >= 99) {
                    dmg = dmg * 2;
                }
                
            } else {
                System.out.println("Oof. The attack missed.");
                dmg = 1.0;
                System.out.println("No wait! Barely scratched them!");
            }
            
            if (dmg < 0) {
                return 1.0;
            } else {
                return dmg;
            }
        }
    }

}