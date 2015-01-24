package com.flexprison.FlexMobLoot.Util;

import com.flexprison.FlexMobLoot.Main;
import org.bukkit.ChatColor;

/**
 * Created by John on 1/23/2015.
 */
public class Messages {

    public String prefix = null;

    Main plugin;

    public Messages(Main instance)
    {
        this.plugin = instance;
    }

    public String NO_PERMISSION = ChatColor.RED + "You don't have permission.";
}
