import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class Quiz {

    public static String[] quizzes = {
            "Which food is most commonly blessed in the Temple of the Moon?",
            "Who is the patron of the Druids of the Talon?",
            "Which legendary artifact can call the spirits of all night elves?",
            "Who lead the Wardens during Maiev's imprisonment in Black Rook Hold?",
            "What is the name of Tyrande Whisperwind's mount?",

            "Which ancient race do furbolgs descend from?",
            "Which tribe of Broken helped Illidan in Outland?",
            "Who is the penultimate leader of the Moon Guard?",
            "What was the original name of the city Zin-Azshari?",
            "What is the name of Azshara's aquatic scepter?"
    };

    public static String[] answers = {
            "rice cakes",
            "aviana",
            "horn of cenarius",
            "sira",
            "ash'alah",

            "jalgar",
            "ashtongue",
            "latosius",
            "elun'dris",
            "sharas'dal",
    };

    public String in;
    // public QuizState state = QuizState.DEFAULT;

    Map<String, QuizContext> states = new ConcurrentHashMap<>();

    public String getKey(MessageReceivedEvent event) {
        if (event.getChannelType() == ChannelType.PRIVATE) {
            return "" + event.getAuthor().getName();
        }
        return "" + event.getGuild().getName() + event.getChannel().getName();
    }

    public QuizContext getContext(MessageReceivedEvent event) {
        QuizContext context = states.get(getKey(event));
        if (context == null) {
            context = new QuizContext();
            setContext(event, context);
        }
        return context;
    }

    public void setContext(MessageReceivedEvent event, QuizContext context) {
        states.put(getKey(event), context);
    }

    public void quizMethod (MessageReceivedEvent event) throws InterruptedException {

        Message message = event.getMessage();
        String content = message.getContentDisplay();
        MessageChannel channel = event.getChannel();
        content = content.toLowerCase();

        Random random = new Random();
        int pickRandom = random.nextInt(quizzes.length);

        QuizContext context = getContext(event);

        if (context.state == QuizState.DEFAULT) {
            channel.sendMessage(
                         "Quiz Started! \n " +
                         "Please type your answers in the following format, in which your answer is 'X': ```!answer X``` \n" +
                         "I will only process the answer if it follows the correct format, other messages are ignored. \n" +
                         "Good luck!").queue();
            context.state = QuizState.ASKING;
        }


        if (context.state == QuizState.WAITING) {

            if (content.startsWith("!answer ") && content.contains(answers[context.questionCount])) {
                channel.sendMessage("Right answer!").queue();
                context.rightAnswers++;
            } else if (content.startsWith("!answer")) {
                channel.sendMessage("Wrong answer!").queue();
                context.wrongAnswers++;
            } else {
                return;
            }

            context.questionCount++;

            if (context.questionCount < 10) {
                context.state = QuizState.ASKING;
            } else {
                context.state = QuizState.END;
            }
        }

        if (context.state == QuizState.ASKING) {
            channel.sendMessage(quizzes[context.questionCount]).queue();
            context.state = QuizState.WAITING;
            return;
        }

        if (context.state == QuizState.END) {

            channel.sendMessage("Quiz ended! Here are your results:" +
                    "\n \n" +
                    "Right answers: **" + context.rightAnswers + "**, \nWrong answers: **" + context.wrongAnswers + "**.").queue();
            context.state = QuizState.DEFAULT;
            return;

        }

        
        
        }
    }


