package me.pieralini.flyplugin;

import me.pieralini.flyplugin.commands.FlyCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class FlyPlugin extends JavaPlugin {

    @Override
    public void onEnable() {

        getCommand("fly").setExecutor(new FlyCommand(this));

        getConfig().options().copyDefaults();
        saveDefaultConfig();

    }
}
