package pl.grzegorz2047.chatguard.chat;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import pl.grzegorz2047.chatguard.ConfigLoader;

import java.util.StringTokenizer;

public class ChatListener implements Listener {


    private final ConfigLoader configLoader;

    public ChatListener(ConfigLoader configLoader) {
        this.configLoader = configLoader;
    }

    @EventHandler
    public void onAsyncChat(AsyncPlayerChatEvent e) {
        String message = e.getMessage().toLowerCase();
        Player player = e.getPlayer();
        StringTokenizer tokenizer = new StringTokenizer(message, " ,.\t!-");
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            String value = configLoader.getMessages().get(token);
            if (value != null) {
                player.sendMessage(ChatUtils.formatChat(configLoader.getIncorrectWordsMsg()));
                player.sendMessage(ChatUtils.formatChat(value));
                e.setCancelled(true);
                return;
            }
        }
    }
}
