import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class MakeHistory {
    public void historyGen (MessageReceivedEvent event) throws IOException {

        if (event.getAuthor().getName().equals("Mogwai")) {

            MessageChannel currentChannel = event.getChannel();

            String thisOne = event.getMessageId();


            List<Message> history = (List<Message>) currentChannel.getHistory();
            System.out.println();

            File historyFile = new File("C:/Dimsel/history.txt");

            BufferedWriter writeHistory = new BufferedWriter(new FileWriter(historyFile));
            writeHistory.write(String.valueOf(history));
            writeHistory.close();

            event.getChannel().sendMessage("Working on it!").queue();

        } else {
            event.getChannel().sendMessage("Sorry, you do not have permission to do that.").queue();
        }
    }
}
