package pl.grzegorz2047.chatguard;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import pl.grzegorz2047.chatguard.chat.ChatListener;
import pl.grzegorz2047.chatguard.command.ChatGuardCommand;

import java.util.HashMap;
import java.util.logging.Level;

public class ChatGuard extends JavaPlugin {

    private ConfigLoader configLoader;


    @Override
    public void onEnable() {
        this.saveDefaultConfig();
        configLoader = new ConfigLoader(this);
        configLoader.load();
        Bukkit.getPluginManager().registerEvents(new ChatListener(configLoader), this);

        getCommand("chatguard").setExecutor(new ChatGuardCommand(configLoader));
        getLogger().log(Level.INFO, this.getName() + " włączony!");
    }


    @Override
    public void onDisable() {
        super.onDisable();
    }

}
