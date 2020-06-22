public class QuizContext {

    public QuizState state;
    public int rightAnswers;
    public int wrongAnswers;
    public int questionCount;

    public QuizContext () {reset();}

    public void reset () {
        state = QuizState.DEFAULT;
        rightAnswers = 0;
        wrongAnswers = 0;
        questionCount = 0;
    }
}