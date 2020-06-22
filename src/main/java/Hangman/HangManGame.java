package Hangman;

import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.SubscribeEvent;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;


public class HangManGame {
     public static String[] hangManStages = {

                "```  +---+\n" +
                        "  |   |\n" +
                        "      |\n" +
                        "      |\n" +
                        "      |\n" +
                        "      |\n" +
                        "=========```",

                "```  +---+\n" +
                        "  |   |\n" +
                        " `O´  |\n" +
                        "      |\n" +
                        "      |\n" +
                        "      |\n" +
                        "=========```",

                "```  +---+\n" +
                        "  |   |\n" +
                        " `O´  |\n" +
                        "  |   |\n" +
                        "      |\n" +
                        "      |\n" +
                        "=========```",

                "```  +---+\n" +
                        "  |   |\n" +
                        " `O´  |\n" +
                        " /|   |\n" +
                        "      |\n" +
                        "      |\n" +
                        "=========```",

                "```  +---+\n" +
                        "  |   |\n" +
                        " `O´  |\n" +
                        " /|\\  |\n" +
                        "      |\n" +
                        "      |\n" +
                        "=========```",

                "```  +---+\n" +
                        "  |   |\n" +
                        " `O´  |\n" +
                        " /|\\  |\n" +
                        " /    |\n" +
                        "      |\n" +
                        "=========```",

                "```  +---+\n" +
                        "  |   |\n" +
                        " `O´  |\n" +
                        " /|\\  |\n" +
                        " / \\  |\n" +
                        "      |\n" +
                        "=========```"


        };

     Map<String, HangmanContext> states = new ConcurrentHashMap<>();

     public String getKey(MessageReceivedEvent event) {
         if (event.getChannelType() == ChannelType.PRIVATE) {
             return getUser(event);
         }
         return "" + event.getGuild().getName() + "-" + event.getChannel().getName();
     }

     // HangmanContext.hangmanUser =  event.getAuthor().getName();

     public String getUser(MessageReceivedEvent event) {
         return "" + event.getAuthor().getName();
     }

     public HangmanContext getContext(MessageReceivedEvent event) {
         HangmanContext context = states.get(getKey(event));

         if (context == null) {
             context = new HangmanContext();
             setContext(event, context);
         }
         return context;
     }

     public void setContext(MessageReceivedEvent event, HangmanContext context) {
         states.put(getKey(event), context);
     }

     public void askInput(User user, String say) {

        user.openPrivateChannel().queue((channel) -> {
            channel.sendMessage(say).queue();
        });
     }

     public HangmanContext createUserContext(MessageReceivedEvent event, HangmanContext parentContext) {
         HangmanContext context = new HangmanContext();
         states.put(getUser(event), context);
         context.parentContext = parentContext;
         return context;
     }

     public void hangmanMethod (MessageReceivedEvent event) {

     Message message = event.getMessage();
     String content = message.getContentDisplay();
     MessageChannel channel = event.getChannel();
     content = content.toLowerCase();

     HangmanContext context = getContext(event);

     char asterisk = '*';

     if (context.state == HangmanState.DEFAULT) {
         channel.sendMessage("**Hangman game started!** \n \n" +
                 "Let's try to guess the word before ***Elfie Elfingston***, the Night Elf that everyone knows and loves" +
                 ", gets hanged for YOUR crimes! \n \nI will now PM you and ask you to submit the word that the other " +
                 "users will begin to guess! Please wait...").queue();
         context.state = HangmanState.ASKING;
         context.questionCount = 0;
     }

     // Bot finds the username and has to message the user in the INPUT stage.

     if (context.state == HangmanState.ASKING) {
         HangmanContext.dudeGuy = event.getAuthor();
         askInput(HangmanContext.dudeGuy,
                 "Hey there. Please respond with a private message in this channel " +
                      "and choose your word by typing '!word WORD' - replace the WORD with the word of your choosing.");

         context.state = HangmanState.DEFAULT;
         context.gameChannel = channel;
         HangmanContext userContext = createUserContext(event, context);
         userContext.state = HangmanState.INPUT;
         return;
     }

     if (context.state == HangmanState.INPUT) {
         if (event.isFromType(ChannelType.PRIVATE)) {
             if (content.startsWith("!word ") && content.substring(5).length() > 0) {

                 String[] responseParts = message.getContentDisplay().split(" ");
                 context.parentContext.solution = responseParts[1];

                 context.state = HangmanState.DEFAULT;


                 askInput(HangmanContext.dudeGuy, "Your chosen word is: '" + context.parentContext.solution + "' - " +
                         "now, the other users will begin to guess it!");

                 context.parentContext.solution = context.parentContext.solution.toLowerCase();

                 context.parentContext.gameChannel.sendMessage("The user **" + HangmanContext.dudeGuy.getName() +
                         "** has chosen the word! The rules are as follows: \n\n" +
                         "Type '!letter X' to guess the letter on the place of X. \n" +
                         "Type '!guess WORD' to make a guess for the word itself.  \n" +
                         "Type '!hangman end' to force an end to the game in case of an error. \n\n" +
                         "You've got **6** tries until Elfie Elfingston dies. Good luck!").queue();

                 context.parentContext.censoredSolution = new char[context.parentContext.solution.length()];
                 context.parentContext.arraySolution = context.parentContext.solution.toCharArray();

                 for(int n = 0; n < context.parentContext.solution.length(); n++) {
                     context.parentContext.censoredSolution[n] = '*';
                 }


                 context.parentContext.gameChannel.sendMessage(
                         "Current word: ```" + new String(context.parentContext.censoredSolution) + "```").queue();
                 context.parentContext.gameChannel.sendMessage(
                         "Current state: " + hangManStages[context.wrongAnswers]).queue();
                 context.parentContext.state = HangmanState.WAITING;
                 return;
             } else {
                 askInput(HangmanContext.dudeGuy, "Sorry, couldn't understand! Try again?");
             }
         }
     }

     if (context.state == HangmanState.WAITING) {
         if (context.wrongAnswers < 6) {

             if (content.startsWith("!letter")) {

                 String letterAnswer = content.substring(8);
                 char charAnswer = letterAnswer.charAt(0);

                 if (content.length() == 9) {
                     channel.sendMessage("Let's check for letter: " + letterAnswer + ".").queue();

                     if (context.solution.contains(letterAnswer)) {

                         for (int i = 0; i < context.arraySolution.length; i++) {
                             if (context.arraySolution[i] == charAnswer) {
                                 context.censoredSolution[i] = charAnswer;
                             }
                         }

                         context.gameChannel.sendMessage("Correct. Your letter does appear in the word!").queue();
                         context.gameChannel.sendMessage("Current word: ```" + new String(context.censoredSolution) + "```").queue();

                     } else {

                         channel.sendMessage("Wrong answer! Elfie is one step closer to death...").queue();
                         context.wrongAnswers++;
                         context.gameChannel.sendMessage(
                                 "Current word: ```" + new String(context.censoredSolution) + "```").queue();
                         context.gameChannel.sendMessage(hangManStages[context.wrongAnswers]).queue();
                     }

                 } else {
                     context.gameChannel.sendMessage("Sorry, couldn't understand! Try again?").queue();
                 }

             }

             if (!new String(context.censoredSolution).contains("*")) {
                 context.gameChannel.sendMessage("Congratulations! \\o/ Elfie is saved! \\o/!").queue();
                 context.state = HangmanState.END;
             }

             if (content.startsWith("!guess ")) {

                 if (content.substring(7).equalsIgnoreCase(context.solution)) {
                     context.gameChannel.sendMessage("Congratulations! You've guessed the word outright!" +
                             " \\o/ Elfie is saved! \\o/ !").queue();
                     context.state = HangmanState.END;
                 } else {
                     context.gameChannel.sendMessage("Sorry, wrong guess! Elfie is another step closer to death...").queue();
                     context.gameChannel.sendMessage(
                             "Current word: ```" + new String(context.censoredSolution) + "```").queue();
                     context.gameChannel.sendMessage("Current state:" + hangManStages[context.wrongAnswers]).queue();
                     context.wrongAnswers++;

                 }


             } else if (content.equalsIgnoreCase("!hangman end")) {
                 context.gameChannel.sendMessage("You've forcefully terminated the game.").queue();
                 context.state = HangmanState.END;
             }

         }

         if (context.wrongAnswers == 6) {
             context.gameChannel.sendMessage("Oh no! Elfie Elfingston has been killed by hanging, all because you couldn't " +
                     "guess the word on time. Oh well, no one lives forever...").queue();
             context.state = HangmanState.END;
         }
     }

     if (context.state == HangmanState.END) {
         channel.sendMessage("This is the end of the game. If you like, type !hangman start to initiate a new one. \n" +
                 "The correct answer for this time was: ```" + context.solution + "```").queue();
         context.state = HangmanState.DEFAULT;
         return;
     }


     }
}


