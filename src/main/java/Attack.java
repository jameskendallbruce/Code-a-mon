/**
 * Class to hold the attributes of a monster's attack.
 * @author James Kendall Bruce
 *
 */
public class Attack {

    public String type;
    public double damage;

    public Attack(double attack, String type){
        this.type = type;
        this.damage = attack;
    }


}