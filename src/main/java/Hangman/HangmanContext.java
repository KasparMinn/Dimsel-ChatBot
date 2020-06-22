package Hangman;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

public class HangmanContext {

    public HangmanContext parentContext;
    public String solution;

    public static User dudeGuy;
    public HangmanState state;
    public int questionCount;
    public int rightAnswers;
    public int wrongAnswers;
    public MessageChannel gameChannel;
    public char[] arraySolution;
    public char[] censoredSolution;
    public String censoredString;

    public HangmanContext() {reset();}

    public void reset() {
        parentContext = null;
        solution = "";
        gameChannel = null;
        state = HangmanState.DEFAULT;
        questionCount = 0;
        rightAnswers = 0;
        wrongAnswers = 0;

    }
}
