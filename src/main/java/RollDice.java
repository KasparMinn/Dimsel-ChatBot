import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.SubscribeEvent;

public class RollDice {

    public void roller(MessageReceivedEvent event)  {


        Message message = event.getMessage();
        String content = message.getContentDisplay();
        MessageChannel channel = event.getChannel();
        content = content.toLowerCase();


        int simpleDice = (int) (Math.random() * 20 + 1);

        if (content.equals("!roll")) {

            channel.sendMessage("With the default d20, you've rolled: **" + simpleDice + "** !").queue();

        } else {

            String diceSubString = content.substring(6);
            diceSubString.trim();
            String[] diceSplitString = diceSubString.split("d");

            int diceAmount = Integer.parseInt(diceSplitString[0]);
            int diceSides = Integer.parseInt(diceSplitString[1]);

            int hardDice = (int) (diceAmount * (Math.random() * diceSides + 1));

            channel.sendMessage("Rolling " + diceSplitString[0] + " " + diceSplitString[1] +
            "-sided dice, the result is: **" + hardDice + "** !").queue();


        }
    }
}

