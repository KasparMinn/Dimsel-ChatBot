import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.apache.commons.lang.ArrayUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MessageHistoryCommands {

    public void messageMethod(MessageReceivedEvent event) throws IOException {

        Message message = event.getMessage();
        String content = message.getContentDisplay();
        MessageChannel channel = event.getChannel();
        content = content.toLowerCase();

        File GENERAL = new File("C:\\discordMessages\\general.txt");
        File RANDOM = new File("C:\\discordMessages\\random.txt");

        String[] catCount = null;
        int wordsTyped = 0;

        FileReader catReaderGeneral = new FileReader(GENERAL);
        FileReader catReaderRandom = new FileReader(RANDOM);

        BufferedReader buffCatGeneral = new BufferedReader(catReaderGeneral);
        BufferedReader buffCatRandom = new BufferedReader(catReaderRandom);

        String cattoG;
        String cattoR;

        channel.sendMessage("The current statistics are from files converted at: 27/05/2020").queue();
        channel.sendMessage("At present, my message history parsing capabilities are: \n "
                + "```!message words total - counts all the words in both channels." +
                "\n!message words WORD - counts instances of any word." +
                "\n!message quoteme - fetches a random quote from the user.```").queue();


        if (content.startsWith("!message words total")) {

            while ((cattoG = buffCatGeneral.readLine()) != null &&
                    (cattoR = buffCatRandom.readLine()) != null) {

                String[] l1 = cattoG.split(" ");
                String[] l2 = cattoR.split(" ");

                catCount = (String[]) ArrayUtils.addAll(l1, l2);
                wordsTyped = wordsTyped + catCount.length;

            }

            channel.sendMessage("The amount of words is: " + wordsTyped).queue();

        }

        if (content.startsWith("!message quoteme")) {

            buffCatGeneral.close();
            buffCatRandom.close();

        }
    }
}
