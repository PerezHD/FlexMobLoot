package com.flexprison.FlexMobLoot.Events;


import com.flexprison.FlexMobLoot.Main;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by PerezHD on 1/23/2015.
 */
public class EventListener implements Listener {

    Main plugin;

    public EventListener(Main instance)
    {
        this.plugin = instance;
    }

    @EventHandler(priority= EventPriority.MONITOR, ignoreCancelled=true)
    public void onDeath(EntityDeathEvent event) {
        if ((event.getEntity() == null) || (event.getEntity().getType() != EntityType.CREEPER)) {
            return;
        }
        for (ItemStack creeper_drop : event.getDrops())
        {
            int chance_of_drop = this.plugin.getConfig().getInt("chance_of_drop");
            int chance = (int) (Math.random() * 100);
            if (chance >= chance_of_drop) {
                event.getEntity().getLocation().getWorld().dropItemNaturally(event.getEntity().getLocation(), creeper_drop.clone());
            }
        }
    }
}
