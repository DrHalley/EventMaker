package com.drhalley.eventmaker;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collection;

public class MakeEventCommand implements CommandExecutor {
    private EventMaker eventMaker;
    MakeEventCommand(EventMaker eventMaker){
        this.eventMaker = eventMaker;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender.isOp() || !(sender instanceof Player)){
            int i = 1;
            ArrayList<Player> players = new ArrayList<>(Bukkit.getOnlinePlayers());
            String message = (String) eventMaker.getConfig().get("event-message");
            Bukkit.broadcastMessage(message);
            while (eventMaker.getConfig().get("items.item" + i) != null) {


                ItemStack item = (ItemStack) eventMaker.getConfig().get("items.item" + i);

                for (int a = 0; a < players.size(); a++) {

                    players.get(a).getInventory().addItem(item);
                }
                i++;
            }
            ;
        }
        return false;
    }
}
