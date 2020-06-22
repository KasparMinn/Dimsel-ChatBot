package Roleplaying;

import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class RPGSetup extends RPG {
    public static void putName(MessageReceivedEvent event, RPGContext context) {

        if (event.getMessage().getContentDisplay().startsWith("!name ")) {

            String sName = event.getMessage().getContentDisplay().substring(6);
            context.playerName = sName;

            event.getChannel().sendMessage("Gotcha, " + sName + " ... yeah? I mean, uh. That's, um... a nice name, I guess. Anyway, for the next step!").queue();

        }
    }


    public static void putClass(MessageReceivedEvent event, RPGContext context) {

        if (context.state == RPGstate.SETCLASS) {

            if (event.getMessage().getContentDisplay().startsWith("!class ")) {
                String pClass = event.getMessage().getContentDisplay().substring(7);

                if (pClass.length() > 1 ||
                   !pClass.equals("1") &&
                   !pClass.equals("2") &&
                   !pClass.equals("3")) {

                    event.getChannel().sendMessage("Sorry, couldn't understand \"" + pClass + "\" try again please!").queue();

                } else {

                    String sClass = "Placeholder";

                    context.playerClass = pClass;
                    context.state = RPGstate.PLAYING;

                    if (pClass.equals("1")) { sClass = "the incredible **Wizzer!**!"; }
                    if (pClass.equals("2")) { sClass = "the magnificent **Dudu**!"; }
                    if (pClass.equals("3")) { sClass = "the **cat**."; }

                    event.getChannel().sendMessage("So, you've chosen " + sClass).queue();

                }
            }
        }
    }
}
