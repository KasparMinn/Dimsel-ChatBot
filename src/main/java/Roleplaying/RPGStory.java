package Roleplaying;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class RPGStory extends RPG {
    public static void RPGStart(MessageReceivedEvent event, RPGContext context) {

        event.getChannel().sendMessage("And thusly, " + context.playerName + ", you have landed! Not with a brilliant flash of light and not with a magnificent crash either, but rather... with a simple, damp thud with which you slammed into the high grass. But, unexpectedly, you survived! Perhaps it was that you were met with some cosmically fanciful odds in the infinite (or near-infinite - physicists haven't made up their minds quite yet) random number generator known as the Universe, or perhaps this is all happening for a reason... \n\n" +
                "But onto more mundane matters!\n\n" +
                "It's a nice and sunny day. You see white tufts of clouds above your head and the birds chirp happily as you lie still in the grass, pondering the mind-muddling metaphysics described above... when suddenly, a smiling face appears and blots out the bright rays of the sun! \n\n \"Hi!\" it says.").queue();

    }
}
