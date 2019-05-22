package pl.grzegorz2047.chatguard.chat;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Map;
import java.util.StringTokenizer;

public class ChatListener implements Listener {

    private final String incorectWords;
    private Map<String, String> messages;

    public ChatListener(Map<String, String> messages, String incorrectWords) {
        this.messages = messages;
        this.incorectWords = incorrectWords;
    }

    @EventHandler
    public void onAsyncChat(AsyncPlayerChatEvent e) {
        String message = e.getMessage().toLowerCase();
        Player player = e.getPlayer();
        StringTokenizer tokenizer = new StringTokenizer(message, " ,.\t!-");
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            String value = messages.get(token);
            if (value != null) {
                player.sendMessage(ChatUtils.formatChat(incorectWords));
                player.sendMessage(ChatUtils.formatChat(value));
                e.setCancelled(true);
                return;
            }
        }
    }
}
