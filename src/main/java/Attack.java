/**
 * Class to hold the attributes of a monster's attack.
 * @author James Kendall Bruce
 *
 */
public class Attack {

    public Element type;
    public double damage;

    public Attack(double dmg, Element el){
        this.type = el;
        this.damage = dmg;
    }


}