package Roleplaying;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RPG {

    Map<String, RPGContext> states = new ConcurrentHashMap<>();

    public String getKey(MessageReceivedEvent event) {
        return "" + event.getGuild().getName() + event.getChannel().getName() + event.getAuthor().getName();
    }

    public RPGContext getContext (MessageReceivedEvent event) {
        RPGContext context = states.get(getKey(event));
        if (context == null) {
            context = new RPGContext();
            setContext(event, context);
        }
        return context;
    }

    public String getRPGUser(MessageReceivedEvent event) {
        return " " + event.getAuthor().getName();
    }
    public void setContext (MessageReceivedEvent event, RPGContext context) { states.put(getKey(event), context); }

    // Main RPG method begins here.
    public void rpgMethod (MessageReceivedEvent event) {

        Message message = event.getMessage();
        String content = message.getContentDisplay();
        MessageChannel channel = event.getChannel();
        content = content.toLowerCase();

        boolean gameState = false;
        RPGContext context = getContext(event);

        // Information display:
        if (content.equals("!rpg")) {
            // fill out with info about the RPG game.

        }


        // Here we boot up the game:
        if (content.contains("!rpg start")) {

            channel.sendMessage("~ **Welcome to the text-based roleplaying adventure 'Dungeons and Dimsel', v1.0.0!** ~ \n\n" +
                                     "Before we continue, we'll need to set up your character. Tell me, mighty adventurer, what is your **name**?").queue();

            channel.sendMessage("```Please specify the name of your character by typing !name YOURNAME for the game to continue!```").queue();

            gameState = true;
            context.state = RPGstate.SETNAME;

        }

        if (gameState = true) {

            // Toss it to the setup method which changes state by itself on finish:
            if (context.state == RPGstate.SETNAME) {

                RPGSetup setupName = new RPGSetup();
                RPGSetup.putName(event, context);

                if (content.startsWith("!name ")) {
                    context.state = RPGstate.SETCLASS;

                    // This message seems like it has to be here, or otherwise it'll keep repeating itself on event input.
                    channel.sendMessage(context.playerName + ", please select your **class**! The options available to you are as follows:\n \n" +
                            "```1. Wizzer - A mighty wizard, capable of summoning salami from the heavens and turning any object purple!\n\n" +
                            "2. Dudu - An nature-attuned mystic, one who knows the language of birds and the way of all butterflies!\n\n" +
                            "3. Catte - A cat.```\n Please select one option by typing '!class X' where X equals the number of the option you picked.").queue();

                } else {
                    return;
                }
            }

            // Toss it to the setup method again! ... it just works, okay.
            if (context.state == RPGstate.SETCLASS) {

                RPGSetup setupClass = new RPGSetup();
                RPGSetup.putClass(event, context);

            }


            if (context.state == RPGstate.PLAYING) {

                channel.sendMessage("Wohoo! Playing...").queue();
                RPGStory intro = new RPGStory();
                RPGStory.RPGStart(event, context);

            }

            // Force ending addon:
            if (content.equals("!rpg end")) { context.state = RPGstate.END; }
            if (context.state == RPGstate.END) {

                context.state = RPGstate.DEFAULT;
                gameState = false;

            }
        }
    }
}
