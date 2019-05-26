package pl.grzegorz2047.chatguard.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.grzegorz2047.chatguard.ConfigLoader;
import pl.grzegorz2047.chatguard.chat.ChatUtils;

import java.util.HashMap;

public class ChatGuardCommand implements CommandExecutor {
    private final ConfigLoader configLoader;

    public ChatGuardCommand(ConfigLoader configLoader) {
        this.configLoader = configLoader;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (args.length == 1) {
            if (args[0].equals("reload")) {
                if (!(commandSender instanceof Player)) {
                    configLoader.reload();
                    commandSender.sendMessage(ChatUtils.formatChat("&bPrzeladowano config"));
                    return true;
                }
                commandSender.sendMessage("tylko dla console");
                return false;
            }
        }
        return false;
    }
}
