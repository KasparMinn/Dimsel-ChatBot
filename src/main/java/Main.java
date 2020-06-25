import Hangman.HangManGame;
import Hangman.HangmanContext;
import Hangman.HangmanState;
import Roleplaying.RPG;
import Roleplaying.RPGContext;
import Roleplaying.RPGstate;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.AnnotatedEventManager;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.hooks.SubscribeEvent;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.net.URISyntaxException;

public class Main extends ListenerAdapter {

    static Quiz quizGame = new Quiz();
    static HangManGame hangGame = new HangManGame();
    static RPG roleGame = new RPG();

    public static void main(String[] args) throws LoginException, InterruptedException {

        Token token = new Token();

        JDA api = new JDABuilder(token.getToken())
                .setEventManager(new AnnotatedEventManager())
                .addEventListener(new MyListener())
                .setStatus(OnlineStatus.ONLINE)
                .setGame(Game.watching("you read this..."))
                .build();

        StatusChanges.changeLoop(api);

    }

    public static class MyListener extends ListenerAdapter {

        // This prints out all the messages that Dimsel sees:
        @SubscribeEvent
        public void ohHeyAMessage(MessageReceivedEvent event) {

            System.out.println(

                    "Channel: " +
                            event.getChannel() +
                            " By: " +
                            event.getAuthor());

            System.out.println(

                    "Message: " +
                            event.getMessage().getContentDisplay());

            System.out.println("\n");

        }

        @SubscribeEvent
        public static void msgReceived(MessageReceivedEvent event) throws IOException, InterruptedException, URISyntaxException {

            // These lines here make the bot not respond to other bots:
            if (event.getAuthor().isBot()) { return; }

            // TODO: Combine the context batches.
            QuizContext contextQ = quizGame.getContext(event);
            if (contextQ.state != QuizState.DEFAULT) {
                quizGame.quizMethod(event);
                return; }

            HangmanContext contextH = hangGame.getContext(event);
            if (contextH.state != HangmanState.DEFAULT) {
                hangGame.hangmanMethod(event);
                return; }

            RPGContext contextR = roleGame.getContext(event);
            if (contextR.state != RPGstate.DEFAULT) {
                roleGame.rpgMethod(event);
                return; }


            // Identifies message to display it in the 'content' string:
            Message message = event.getMessage();
            String content = message.getContentDisplay();


            // This puts the incoming message to lowercase, so that content.contains could find it:
            content = content.toLowerCase();
            MessageChannel channel = event.getChannel();

            // Introduction:

                if (content.contains("!whoareyou")) {
                    channel.sendMessage("Hello, I am a simple Java bot with a JDA-type bone structure. My creator is learning this language" +
                            " and making (slow) progress in fitting me with new stuff. If you like, you may type !commands to see what I do. " +
                            "So far, it is very little, but I still do my bestest.").queue();

                    // Command list, adding new stuff constantly:

                } else if (content.contains("!commands")) {
                    channel.sendMessage("Here is a list of my commands:\n \n" +

                            "**!flip** - Flips a coin. \n" +
                            "**!roll** - Roll a simple 20-sided die. Type !roll XdY to roll X amount of Y-sided dice. \n" +
                            "**!cat** - Fetches a cat picture from The Cat API. \n" +
                            "**!fact** - Fetches a cat fact from The Cat API. \n" +
                            "**!elfname** - Generates a (really bad) elf name. \n" +
                            "**!quiz start** - Starts a 10-question quiz about Night Elven lore. \n" +
                            "**!hangman start** - Starts a game of hangman with user input. \n" +
                            "**!appreciate NAME** - Appreciate someone by replacing NAME with their name! \n" +

                            "\n ... welp! That's about it. Hopefully my creator sticks with it and adds more!").queue();

                    // Deviations into other classes:

                } else if (content.contains("!flip")) {

                    CoinFlip cFlip = new CoinFlip();
                    cFlip.flippy(channel);

                } else if (content.contains("!roll")) {

                    // Dunno
                    RollDice rollD = new RollDice();
                    rollD.roller(event);

                } else if (content.startsWith("!cat") && !content.contains("!catbot")) {

                    CatPic catPicture = new CatPic();
                    catPicture.cPic(event);

                } else if (content.contains("!fact")) {

                    CatFact catFactoid = new CatFact();
                    catFactoid.cFact(event);

                } else if (content.contains("!elfname")) {

                    GenerateName nelfName = new GenerateName();
                    nelfName.nameGen(channel);

                } else if (content.equals("!hangman start")) {

                    contextH.reset();
                    hangGame.hangmanMethod(event);

                } else if (content.equals("!parse channelhistory")) {

                    MakeHistory parseHistory = new MakeHistory();
                    parseHistory.historyGen(event);

                } else if (content.contains("!points")) {

                    PointClass pointsClass = new PointClass();
                    pointsClass.pointsMain(event);

                } else if (content.startsWith("!quiz start")) {

                    contextQ.reset();
                    quizGame.quizMethod(event);

                } else if (content.startsWith("!rpg")) {

                    contextR.reset();
                    roleGame.rpgMethod(event);

                } else if (content.startsWith("!daressa")) {

                    Appreciation dApp = new Appreciation();
                    dApp.appreciationMethod(channel);

                } else if (content.startsWith("!appreciate ")) {

                    Cherish uCherish = new Cherish();
                    uCherish.cherishMethod(event);

                // WIP:
                } else if (content.startsWith("?message")) {

                    MessageHistoryCommands commands = new MessageHistoryCommands();
                    commands.messageMethod(event);

                } else {

                    StringResponses stringo = new StringResponses();
                    stringo.responder(event);

                }
            }
        }
    }

