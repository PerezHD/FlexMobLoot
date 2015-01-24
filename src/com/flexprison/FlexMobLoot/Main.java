package com.flexprison.FlexMobLoot;

import com.flexprison.FlexMobLoot.Commands.CommandHandler;
import com.flexprison.FlexMobLoot.Events.EventListener;
import com.flexprison.FlexMobLoot.Util.Messages;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public static Main plugin;

    public Messages messages = new Messages(this);
    public String prefix = null;

    public void onEnable()
    {
        plugin = this;

        PluginDescriptionFile pdf = getDescription();
        log("[ Enabling FlexMobLoot " + pdf.getVersion() + " ]");

        registerCommands();
        registerEvents();
        loadConfiguration();

        prefix = translateToColorCode(getConfig().getString("prefix"));

    }

    public void onDisable()
    {
        PluginDescriptionFile pdf = getDescription();
        log("[ Disabling FlexMobLoot " + pdf.getVersion()+ " ]");
    }

    public void log(String msg)
    {
        getServer().getLogger().info(msg);
    }

    public void loadConfiguration(){
        getConfig().addDefault("prefix", "&b[FML]");
        getConfig().addDefault("chance_of_drop", Integer.valueOf(50));
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    public static String translateToColorCode(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    private void registerCommands(){
        getCommand("fml").setExecutor(new CommandHandler(this));
    }

    private void registerEvents(){
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new EventListener(this), this);
    }
}
