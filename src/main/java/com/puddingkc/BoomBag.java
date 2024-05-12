package com.puddingkc;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class BoomBag extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player && event.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK) {
            Player player = (Player) event.getEntity();

            if (player.hasPermission("boombag.bypass") || player.getGameMode().equals(GameMode.CREATIVE)) {
                return;
            }

            if (player.getInventory().contains(Material.TNT)) {
                player.getInventory().removeItem(new ItemStack(Material.TNT, 1));
                player.getWorld().spawn(player.getLocation(), TNTPrimed.class);
            }
        }
    }
}
