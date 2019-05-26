package pl.grzegorz2047.chatguard.chat;

import org.bukkit.ChatColor;

public class ChatUtils {

    private static final String PREFIX = "&7[&cChatGuard&7] ";

    public static String formatChat(String value) {
        if (value.isEmpty()) {
            return "";
        }
        return ChatColor.translateAlternateColorCodes('&', PREFIX + value);
    }
}
