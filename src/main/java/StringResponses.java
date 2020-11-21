import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.ActivityFlag;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.exceptions.RateLimitedException;
import net.dv8tion.jda.api.hooks.AnnotatedEventManager;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.hooks.SubscribeEvent;

import java.util.concurrent.TimeUnit;

public class StringResponses {

    @SubscribeEvent
    public void responder(MessageReceivedEvent event) throws InterruptedException {

            Message message = event.getMessage();
            String content = message.getContentDisplay();
            MessageChannel channel = event.getChannel();
            content = content.toLowerCase();

        if (content.contains("meowdy")) {
            channel.sendMessage("Meowdy do, Purrtner! <:meowdy:653571478276669441>").queue();
        } else if (content.contains("hello")) {
            message.addReaction(":blobcatwavereverse:655271305222029314").queue();
        } else if (content.equalsIgnoreCase("hi")) {
            message.addReaction(":blobcatwavereverse:655271305222029314").queue();
        } else if (content.equalsIgnoreCase("hi.")) {
            message.addReaction(":blobcatwavereverse:655271305222029314").queue();
        } else if (content.equalsIgnoreCase("hi!") || content.contains("hi, dimsel") ||content.contains("hi ")) {
            message.addReaction(":blobcatwavereverse:655271305222029314").queue();
        } else if (content.equalsIgnoreCase("KABOOM!")) {
            channel.sendMessage("Alrrrright, Purrtner. Today... we make kah-boom!").queue();
            TimeUnit.SECONDS.sleep(4);
            channel.sendMessage("It might get a little loud... <:blobcatpanic:653571478385590281>").queue();
            TimeUnit.SECONDS.sleep(2);
            channel.sendMessage("Ahem... Take cover! <:blobcatpeek:653571478033399809>").queue();
            TimeUnit.SECONDS.sleep(2);
            channel.sendMessage("Three!").queue();
            TimeUnit.SECONDS.sleep(2);
            channel.sendMessage("Two!").queue();
            TimeUnit.SECONDS.sleep(2);
            channel.sendMessage("One...").queue();
            TimeUnit.SECONDS.sleep(2);
            channel.sendMessage("\uD83D\uDCA5**K** \uD83D\uDCA5 **A** \uD83D\uDCA5 **B** \uD83D\uDCA5 **O** \uD83D\uDCA5 **O** \uD83D\uDCA5 **M** \uD83D\uDCA5 **!!!**").queue();
        }  else if (content.contains("meow")) {
            message.addReaction("\uD83D\uDC31").queue();
            message.addReaction("\uD83C\uDDF2").queue();
            message.addReaction("\uD83C\uDDEA").queue();
            message.addReaction("\uD83C\uDDF4").queue();
            message.addReaction("\uD83C\uDDFC").queue();
        } else if (content.contains("purr")) {
            message.addReaction(":blobcatmelt:653571478284926976").queue();
            message.addReaction("\uD83D\uDCE2").queue();
            message.addReaction("\uD83C\uDDF5").queue();
            message.addReaction("\uD83C\uDDF7").queue();
            message.addReaction("♥").queue();
        } else if (content.contains("hiss")) {
            message.addReaction(":blobcattableflip:655271306282926081").queue();
            message.addReaction("\uD83D\uDCA2").queue();
            message.addReaction("‼").queue();
        } else if (content.contains("grr")) {
            message.addReaction(":blobcatangry:653571478091989002").queue();
            message.addReaction("\uD83D\uDCA2").queue();
            message.addReaction("‼").queue();
        } else if (content.contains("salami") || content.contains("saloomi")) {
            message.addReaction(":blobcatreach:653571478091988993").queue();
            message.addReaction("\uD83C\uDDF5").queue();
            message.addReaction("\uD83C\uDDF1").queue();
            message.addReaction("\uD83C\uDDF8").queue();
        } else if (content.contains("good dimsel") || content.contains("good job, dimsel") || content.contains("good boy, dimsel")) {
            message.addReaction(":blobcatderppat:655271306165616690").queue();
            message.addReaction("\uD83D\uDC9E").queue();
            message.addReaction("\uD83C\uDDF9").queue();
            message.addReaction("\uD83C\uDDED").queue();
            message.addReaction("\uD83C\uDDE6").queue();
            message.addReaction("\uD83C\uDDF3").queue();
            message.addReaction("\uD83C\uDDF0").queue();
        }
    }
}


