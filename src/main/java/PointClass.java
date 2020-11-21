import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import javax.swing.*;
import java.io.*;
import java.util.Arrays;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.table.TableColumn;

public class PointClass {

    String[] columnNames = {  "Ashrandri", "Ashira", "Daressa", "George", "Matharion", "Narilaes", "Thedris", "Vilmazar", "Ysendris"  };
    Object[][] data = {{ "GEP", "Hair colour" }};

    JTable pointsTable = new JTable(data, columnNames);

    TableColumn column = null;
    
    


    public void pointsMain (MessageReceivedEvent event) throws IOException {

        Message message = event.getMessage();
        String content = message.getContentDisplay();
        MessageChannel channel = event.getChannel();
        content = content.toLowerCase();

        File pointsFile = new File("C:/Dimsel/points.txt");

        if (message.getAuthor().getName().equalsIgnoreCase("Mogwai")) {


            if (content.contains("!points give")) {
                FileWriter writePoint = new FileWriter(pointsFile);

                writePoint.write(String.valueOf(content.split("e ")));
                writePoint.close();
                channel.sendMessage("Points given!").queue();
            }


            if (content.contains("!points display")) {
                FileReader readPoint = new FileReader(pointsFile);
                String messageToSend = readPoint.toString();
                channel.sendMessage(messageToSend).queue();
            }

            if (content.contains("!points table")) {
                channel.sendMessage(pointsTable.toString()).queue();
            }


        }
    }
}
