package pl.grzegorz2047.chatguard.chat;

import org.bukkit.ChatColor;

class ChatUtils {

    private static final String PREFIX = "&7[&cChatGuard&7] ";

    static String formatChat(String value) {
        if (value.isEmpty()) {
            return "";
        }
        return ChatColor.translateAlternateColorCodes('&', PREFIX + value);
    }
}
