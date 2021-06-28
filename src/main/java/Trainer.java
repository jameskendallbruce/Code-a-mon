/**
 * Class of trainers that can battle each other with their team of monsters.
 * @author James Kendall Bruce
 *
 */
public class Trainer {
    
    public String trainerName = null;
    
    Team team = new Team(this);
    
    /**
     * Generic constructor that sets a trainer's name.
     * @param name -- name of the trainer.
     */
    public Trainer(String name) {
        trainerName = name;
    }
    
    /**
     * Generic constructor that sets a trainer's name and team.
     * @param name -- name of the trainer.
     * @param team -- trainer's new team of monsters.
     */
    public Trainer(String name, Team newTeam) {
        trainerName = name;
        team = newTeam;
    }
    
}