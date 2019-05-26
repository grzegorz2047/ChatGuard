package pl.grzegorz2047.chatguard;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.List;

public class ConfigLoader {

    private FileConfiguration config;
    private final ChatGuard chatGuard;
    private String sentenceAlternativeTemplate;
    private String sentenceNoAlternativeTemplate;
    private String incorrectWords;
    private final HashMap<String, String> messages = new HashMap<String, String>();

    public ConfigLoader(ChatGuard chatGuard) {
        this.chatGuard = chatGuard;
        this.config = chatGuard.getConfig();
    }

    public void reload() {
        chatGuard.reloadConfig();
        this.config = chatGuard.getConfig();
        this.messages.clear();
        load();
    }

    public void load() {
        List<String> forms = config.getStringList("forms");
        this.sentenceAlternativeTemplate = config.getString("sentenceAlternativeTemplate");
        this.sentenceNoAlternativeTemplate = config.getString("sentenceNoAlternativeTemplate");
        this.incorrectWords = config.getString("incorrectWords");
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

    }

    private String getCorrectFormText(String first, String second) {
        return sentenceAlternativeTemplate.replace("{first}", first).replace("{second}", second);
    }

    private String getCorrectFormText(String first) {
        return sentenceNoAlternativeTemplate.replace("{first}", first);
    }

    public String getSentenceAlternativeTemplate() {
        return sentenceAlternativeTemplate;
    }

    public String getSentenceNoAlternativeTemplate() {
        return sentenceNoAlternativeTemplate;
    }

    public String getIncorrectWordsMsg() {
        return incorrectWords;
    }

    public HashMap<String, String> getMessages() {
        return messages;
    }
}