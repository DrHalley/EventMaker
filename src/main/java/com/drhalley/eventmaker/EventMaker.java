package com.drhalley.eventmaker;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class EventMaker extends JavaPlugin {

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        getCommand("em-add").setExecutor(new AddItemCommand(this));
        getCommand("make-event").setExecutor(new MakeEventCommand(this));
        int i = (int) getConfig().get("delay")*20;
        Bukkit.getScheduler().runTaskTimer(this, new Runnable() {
            @Override
            public void run() {
                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                String command = "make-event";
                Bukkit.dispatchCommand(console, command);
            }
        },20, i);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
