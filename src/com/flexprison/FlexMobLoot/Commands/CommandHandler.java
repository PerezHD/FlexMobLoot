package com.flexprison.FlexMobLoot.Commands;

import com.flexprison.FlexMobLoot.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;

/**
 * Created by PerezHD on 1/23/2015.
 */
public class CommandHandler implements CommandExecutor {

    Main plugin;

    public CommandHandler(Main instance)
    {
        this.plugin = instance;
    }

    int chance_of_drop;
    String prefix;
    FileConfiguration config;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("fml")){
            if (args.length == 0){
                if (!p.hasPermission("fml.help")){
                    PluginDescriptionFile pdf = plugin.getDescription();
                    p.sendMessage(plugin.prefix + ChatColor.YELLOW + " FlexMobLoot is enabled on version " + pdf.getVersion());
                }
                else {
                    PluginDescriptionFile pdf = plugin.getDescription();
                    p.sendMessage(plugin.prefix + ChatColor.YELLOW + " FlexMobLoot is enabled on version " + pdf.getVersion());
                    p.sendMessage(plugin.prefix + ChatColor.RED + " /fml <reload>");
                }
            }
            if (args.length == 1){
                if (args[0].equalsIgnoreCase("reload")){
                    reloadConfig();
                    p.sendMessage(plugin.prefix + ChatColor.YELLOW + " FlexMobLoot configuration has been reloaded!");
                    plugin.log("Successfully reloaded FlexMobLoot!");
                }
            }
        }
        return false;
    }

    public void reloadConfig()
    {
        this.plugin.reloadConfig();
        load(this.plugin.getConfig());
    }

    void load(FileConfiguration paramFileConfiguration) {
        this.config = paramFileConfiguration;
        this.prefix = paramFileConfiguration.getString("prefix", "&b[FML]");
        this.chance_of_drop = paramFileConfiguration.getInt("chance_of_drop", 50);
    }
}
