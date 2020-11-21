import net.dv8tion.jda.api.entities.MessageChannel;

public class GenerateName {
    public void nameGen(MessageChannel channel) {

    String[] firstPart = {
            "Ar", "Au", "Ae",
            "Ca", "Ci", "Ce",
            "Va", "Ve", "Vae",
            "Ra", "Ri", "Rae",
    };

    String [] seconPart = {
            "ni", "nil", "nae",
            "ri", "rau", "ria",
            "vae", "var", "via",
            "lie", "lua", "lien",
    };

    String [] thirdPart = {
            "ale", "ae", "aya",
            "lae", "lir", "lai",
            "lynn", "lanna", "riel",
            "yia", "era", "sae",
    };

    String randomFirst = firstPart[(int)(Math.random() * firstPart.length)];
    String randomSecon = seconPart[(int)(Math.random() * seconPart.length)];
    String randomThird = thirdPart[(int)(Math.random() * thirdPart.length)];

    channel.sendMessage("Here's your randomly generated elf name: " + randomFirst + randomSecon + randomThird).queue();

    }
}
