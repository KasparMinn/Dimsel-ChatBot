import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import okhttp3.*;

public class CatPic {
    public void cPic(MessageReceivedEvent event) throws IOException {

        Message message = event.getMessage();
        String content = message.getContentDisplay();
        MessageChannel channel = event.getChannel();
        content = content.toLowerCase();

        OkHttpClient client = new OkHttpClient().newBuilder()

                .build();

        Request request = new Request.Builder()

                .url("https://api.thecatapi.com/v1/images/search?format=json")
                .method("GET", null)
                .addHeader("Content-Type", "application/json")
                .addHeader("x-api-key", "17d94b92-754f-46eb-99a0-65be65b5d18f")
                .build();

        Response response = client.newCall(request).execute();

        String meowPicture = response.toString();
        String[] meowPictureOne = meowPicture.split("rl=");

        URL url = new URL(meowPictureOne[1]);
        InputStream is = url.openStream();
        int ptr = 0;
        StringBuffer buffer = new StringBuffer();
        while ((ptr = is.read()) != -1) {
            buffer.append((char)ptr);
        }

        String bufferString = buffer.toString();

        String[] meowPictureTwo = bufferString.split("url\":\"");
        String meowFinalSplit = meowPictureTwo[1];
        String[] meowPictureThree = meowFinalSplit.split("\",\"");


        channel.sendMessage("\uD83D\uDC08 " + meowPictureThree[0]).queue();

    }
}
