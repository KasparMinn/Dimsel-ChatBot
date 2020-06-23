import net.dv8tion.jda.core.entities.MessageChannel;

public class CoinFlip {
     public void flippy(MessageChannel channel) {

         // Perhaps my first working piece of Java code.

            if (Math.random() < 0.5) {
                channel.sendMessage("Heads!").queue();
            } else {
                channel.sendMessage("Tails!").queue();
            }

        }
    }

