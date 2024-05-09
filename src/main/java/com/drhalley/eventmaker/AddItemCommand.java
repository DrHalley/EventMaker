package com.drhalley.eventmaker;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.Permission;

public class AddItemCommand implements CommandExecutor {

    private EventMaker eventMaker;
    public AddItemCommand(EventMaker eventMaker){
        this.eventMaker = eventMaker;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            if(p.hasPermission("op")){
                if(p.getItemInHand().getItemMeta() != null){
                    int i = 1;
                    while(eventMaker.getConfig().get("items.item" + i) != null){
                     i++;
                    }
                    ItemStack item = p.getItemInHand();
                    eventMaker.getConfig().set("items.item"+i, item);
                    eventMaker.saveConfig();
                }else{
                    p.sendMessage("You have to hold a item!");
                }
            }else{
                p.sendMessage("You can't use this command");
            }
        }else{
            sender.sendMessage("This command can't use on console!");
        }
        return false;
    }
}
