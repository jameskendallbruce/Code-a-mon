import java.util.concurrent.ThreadLocalRandom;

public class Stats {

    double attack;
    double defense;
    double health;
    double crit;
    double speed;

    public Stats() {
        attack = 50;
        defense = 50;
        health = 100;
        crit = 3;
        speed = 50;
    }

    public Stats(MonsterImpl.Type type, MonsterImpl.Creature creature) {
        switch (creature) {
            case CUB: // Raw Stats: 260 (~290)
                attack = 40;
                defense = 80;
                health = 100;
                crit = 3;
                speed = 40;
                break;
            case PUP: // Raw Stats: 265 (~275)
                attack = 70;
                defense = 50;
                health = 90;
                crit = 1;
                speed = 55;
                break;
            case TADPOLE: // Raw Stats: 260 (~290)
                attack = 50;
                defense = 30;
                health = 120;
                crit = 3;
                speed = 60;
                break;
            case WHELP: // Raw Stats: 235 (~285)
                attack = 45;
                defense = 55;
                health = 80;
                crit = 5;
                speed = 55;
                break;
        }
        
        addType(type);

    }
    
    public void addType(MonsterImpl.Type type) {
        switch (type) {
        case WATER:
            health += 10;
            break;
        case EARTH:
            defense += 10;
            break;
        case FIRE:
            attack += 10;
            break;
        case AIR:
            crit += 2;
            break;
        }
    }
    
}