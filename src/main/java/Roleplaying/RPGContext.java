package Roleplaying;

public class RPGContext {

    // State fetch and reset call.
    public RPGstate state;
    public RPGContext () {reset();}

    // General stats for the game
    public String playerName;
    public String playerClass;

    public int playerCurrentHP;
    public int playerTotalHP;

    public int playerDamage;

    public void reset() {

        state = RPGstate.DEFAULT;

    }
}
