package pl.grzegorz2047.chatguard;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import pl.grzegorz2047.chatguard.chat.ChatListener;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

public class ChatGuard extends JavaPlugin {

    private String sentenceAlternativeTemplate;
    private String sentenceNoAlternativeTemplate;
    private String incorrectWords;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        FileConfiguration config = this.getConfig();
        this.sentenceAlternativeTemplate = config.getString("sentenceAlternativeTemplate");
        this.sentenceNoAlternativeTemplate = config.getString("sentenceNoAlternativeTemplate");
        this.incorrectWords = config.getString("incorrectWords");

        List<String> forms = config.getStringList("forms");
        HashMap<String, String> messages = loadForms(forms);
        Bukkit.getPluginManager().registerEvents(new ChatListener(messages, incorrectWords), this);
        getLogger().log(Level.INFO, this.getName() + " włączony!");
    }

    private HashMap<String, String> loadForms(List<String> forms) {
        HashMap<String, String> messages = new HashMap<String, String>();
        for (String form : forms) {
            String[] split = form.split(":");
            int length = split.length;
            if (length == 3) {
                String incorrectForm = split[0];
                String firstCorrectForm = split[1];
                String secondCorrectForm = split[2];
                messages.put(incorrectForm, getCorrectFormText(firstCorrectForm, secondCorrectForm));
            } else if (length == 2) {
                String incorrectForm = split[0];
                String firstCorrectForm = split[1];
                messages.put(incorrectForm, getCorrectFormText(firstCorrectForm));
            } else {
                messages.put(form, "");
            }
        }
        return messages;
    }

    private String getCorrectFormText(String first, String second) {
        return sentenceAlternativeTemplate.replace("{first}", first).replace("{second}", second);
    }

    private String getCorrectFormText(String first) {
        return sentenceNoAlternativeTemplate.replace("{first}", first);

    }


    @Override
    public void onDisable() {
        super.onDisable();
    }

}
