public class LegendaryMonster {
    
    private Modifier mod;
    
    private Element type;
    
    private Evolution evolution;
    
    private MonsterImpl monster;
    
    private LegendaryMonster(MonsterImpl mon) {
        Creature critter = mon.creature;
        mod = Modifier.ELDRITCH;
        type = Element.CELESTIAL;
        monster = new MonsterImpl(type, critter);
        evolution = new Evolution(monster);
        evolution.mod = mod;
        
    }
    
}