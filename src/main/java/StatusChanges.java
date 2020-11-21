import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Activity;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class StatusChanges {
    public static void changeLoop(JDA jda) throws InterruptedException {

        // 16 lines

        String[] watchingLines = {
                "Mogwai suffer...",
                "Mivae get mivved",
                "Nel protecc Miu",
                "Daressa be cute",
                "Shyere's smile",

                "Saley be splendid",
                "AD drama... ugh!",
                "Ysendris closely",
                "Vil be fashionable",
                "Daressa's dates",

                "Nyth be helpful!",
                "Ash take shrooms...",
                "Minovo smirking",
                "George hit on elves",
                "Meky DM :3",

                "Capitalism burn" };

        String[] listeningLines = {
                "Sealdude sing!",
                "hissing succubi...",
                "George chug",
                "black metal \\../,",
                "Mivae growl",

                "the clever Teddy",
                "Ashira squeal",
                "Minovo's dark chuckle",
                "Mogwai groan",
                "Perwynn's flirting",

                "Mogwai rant again...",
                "all the meows ever",
                "the black cat purr",
                "Meky's advice",
                "wholesome Daressa",

                "gangsta rap w/ Nel" };

        Random rPick = new Random();
        int P;

        // Redundant boolean for the moment, but I might want to do something here later with user commands...

        boolean rotationON = true;
        while (rotationON = true) {

            P = rPick.nextInt(watchingLines.length);
            jda.getPresence().setActivity(Activity.watching(watchingLines[P]));
            TimeUnit.SECONDS.sleep(30);

            P = rPick.nextInt(watchingLines.length);
            jda.getPresence().setActivity(Activity.listening(listeningLines[P]));
            TimeUnit.SECONDS.sleep(30);

        }
    }
}
