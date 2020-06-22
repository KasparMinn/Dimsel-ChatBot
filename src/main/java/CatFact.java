import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import org.apache.commons.lang.StringEscapeUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class CatFact {
    public void cFact (MessageReceivedEvent event) throws IOException {

        Message message = event.getMessage();
        String content = message.getContentDisplay();
        MessageChannel channel = event.getChannel();
        content = content.toLowerCase();

        URL url = new URL("https://catfact.ninja/fact");
        InputStream is = url.openStream();
        int ptr = 0;
        StringBuffer buffer = new StringBuffer();
        while ((ptr = is.read()) != -1) {
            buffer.append((char)ptr);
        }

        String bufferString = buffer.toString();
        String[] meowFactOne = bufferString.split("\"fact\":\"");

        String meowSecondSplit = meowFactOne[1];
        String[] meowFactTwo = meowSecondSplit.split("\",\"l");

        String meowFactFinal = StringEscapeUtils.unescapeJava(meowFactTwo[0]);

        channel.sendMessage(meowFactFinal).queue();



    }
}
