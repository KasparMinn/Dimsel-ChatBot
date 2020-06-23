import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class Cherish {
    public void cherishMethod(MessageReceivedEvent event) {

        // This is a silly command to cherish a friend with some wholesome emojis!

        String list = event.getGuild().getMembers().toString();

        Message message = event.getMessage();
        String content = message.getContentDisplay();
        MessageChannel channel = event.getChannel();

        String name1 = content.substring(12).toLowerCase();
        String firstLetter = name1.substring(0, 1).toUpperCase();
        String otherLetters = name1.substring(1);
        String name2 = firstLetter + otherLetters;

        String[] firstThing = { "\uD83D\uDC31 kitten", "\uD83E\uDD8B butterfly", "\uD83E\uDD84 unicorn", "\uD83D\uDD4A white dove", "\uD83C\uDF4E juicy apple", "\uD83C\uDF53 yummy strawberry", "\uD83E\uDD68 sugary pretzel", "\uD83C\uDF2E gorgeous taco", "\uD83E\uDDC1 pink cupcake", "\uD83C\uDF6A tasty cookie",   };
        String[] secondThing = { "\uD83D\uDC9E my heart", "\uD83C\uDF0C the stars at night", "\uD83D\uDD25 a fire in my chest", "\uD83C\uDF08 a colorful rainbow", "⭐ bright star", "\uD83C\uDF1E my sun", "\uD83C\uDF3B a golden flower", "\uD83C\uDF3C blossoms of the spring", "\uD83C\uDF38 all the cherry blossoms", };

        String firstRandom = firstThing[(int)(Math.random() * firstThing.length)];
        String secondRandom = secondThing[(int)(Math.random() * secondThing.length)];

        if (list.toLowerCase().contains(name1)) {
            channel.sendMessage("I appreciate " + name2 + " a lot! They are a " + firstRandom + " and " + secondRandom + "!" ).queue();
        } else {
            channel.sendMessage("The user " + name2 + " is currently not in the server, and therefore is unable to be appreciated by me!").queue();
        }
    }
}
