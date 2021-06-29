public class Environment {

    Weather weather = Weather.NEUTRAL;
    Element buff = null;
    Element debuff = null;
    double buffMod = 1.25;
    double debuffMod = 0.75;

    public Environment(){
        weather = Weather.NEUTRAL;
    }

    public Environment(Weather wthr) {
        weather = wthr;
        switch (weather){
            case SUNNY:
               buff = Element.FIRE;
               debuff = Element.WATER;
               break;
            case CLEAR:
                buff = Element.FIRE;
                debuff = Element.WATER;
                break;
            case WINDY:
                buff = Element.AIR;
                debuff = Element.EARTH;
                break;
            case RAINY:
                buff = Element.WATER;
                debuff = Element.FIRE;
                break;
            case FOGGY:
                buff = Element.EARTH;
                debuff = Element.AIR;
                break;
            case STORMY:
                buff = Element.CELESTIAL;
                debuff = Element.ANY;
                break;
            default:
                weather = Weather.NEUTRAL;
                buff = null;
                debuff = null;
                break;
        }
    }

    public Element getBuffedType(){
        return buff;
    }

    public Element getDebuffedType(){
        return debuff;
    }

}
