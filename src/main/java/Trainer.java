/**
 * Class of trainers that can battle each other with their team of monsters.
 * @author James Kendall Bruce
 *
 */
public class Trainer {
    
    public String trainerName = null;
    
    public Team team = new Team(this);
    
    public int winCount = 0;
    
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
        setTeam(newTeam);
    }
    
    /**
     * Increment the number of battle wins that the trainer has gotten.
     */
    public void winIncrement() {
        winCount++;
    }
    
    /**
     * Method to check how many times the trainer has won.
     * @return the number of times that the trainer has won.
     */
    public int getWins() {
        return winCount;
    }
    
    /**
     * Basic set method to set the trainer's team of monsters.
     * @param newTeam -- the new/updated team of monsters.
     */
    public void setTeam(Team newTeam) {
        team = newTeam;
    }
    
    /**
     * Basic get method to retrieve the Team of monsters.
     * @return -- the team of monsters.
     */
    public Team getTeam() {
        return team;
    }
    
}