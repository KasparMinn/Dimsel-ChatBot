import net.dv8tion.jda.core.entities.MessageChannel;

import javax.swing.table.TableColumn;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class Appreciation {
        public void appreciationMethod(MessageChannel channel) throws InterruptedException {

            DateTimeFormatter dDate = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDateTime now = LocalDateTime.now();

            channel.sendMessage("Oh my goodness! Look at the date ... it is ... " + dDate.format(now) + "!!!").queue();
            TimeUnit.SECONDS.sleep(4);
            channel.sendMessage("This means... <:blobcatthink:655271305897312266> ... yep, it must be ...!").queue();
            TimeUnit.SECONDS.sleep(4);
            channel.sendMessage("<:blobcatcheer:655275209930178561> Happy Daressa appreciation day!!! <:blobcatcheer:655275209930178561> ").queue();

        }
}
