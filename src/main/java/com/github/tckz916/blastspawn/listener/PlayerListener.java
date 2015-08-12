package com.github.tckz916.blastspawn.listener;

import com.github.tckz916.blastspawn.BlastSpawn;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;

/**
 * Created by tckz916 on 2015/07/10.
 */
public class PlayerListener implements Listener {

    private BlastSpawn plugin = BlastSpawn.getInstance();

    private String spawn = plugin.spawn;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Location location = player.getLocation();
        boolean isJoin = plugin.getConfig().getBoolean(spawn + ".login");
        boolean isInventoryclear = plugin.getConfig().getBoolean(spawn + ".inventoryclear");
        boolean isEffectclear = plugin.getConfig().getBoolean(spawn + ".effectclear");

        if (isJoin) {
            String getWorld = plugin.getConfig().getString(spawn + ".world");
            World world = Bukkit.getServer().getWorld(getWorld);
            double x = plugin.getConfig().getDouble(spawn + ".x");
            double y = plugin.getConfig().getDouble(spawn + ".y");
            double z = plugin.getConfig().getDouble(spawn + ".z");
            double yaw = plugin.getConfig().getDouble(spawn + ".yaw");
            double pitch = plugin.getConfig().getDouble(spawn + ".pitch");

            location.setWorld(world);
            location.setX(x);
            location.setY(y);
            location.setZ(z);
            location.setPitch((float) pitch);
            location.setYaw((float) yaw);

            player.teleport(location);
        }

        if (isInventoryclear) {
            player.getInventory().clear();
        }

        if (isEffectclear) {
            for (PotionEffect effect : player.getActivePotionEffects()) {
                player.removePotionEffect(effect.getType());
            }
        }


    }
}
