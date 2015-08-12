package com.github.tckz916.blastspawn.command;

import com.github.tckz916.blastspawn.BlastSpawn;
import com.github.tckz916.blastspawn.util.Util;
import com.github.tckz916.blastspawn.util.language.PlayerLanguage;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by tckz916 on 2015/07/10.
 */
public class SetSpawnCommand implements CommandExecutor {

    private BlastSpawn plugin = BlastSpawn.getInstance();

    private String prefix = plugin.prefix;
    private String spawn = plugin.spawn;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            String locale = PlayerLanguage.getPlayerLanguage(player);
            boolean isJoin = plugin.getConfig().getBoolean(spawn + ".login");
            boolean isInventoryclear = plugin.getConfig().getBoolean(spawn + ".inventoryclear");
            boolean isEffectclear = plugin.getConfig().getBoolean(spawn + ".effectclear");
            if (sender.hasPermission("blastspawn.command.setspawn")) {

                Location location = player.getLocation();

                String world = location.getWorld().getName();

                double x = location.getX();
                double y = location.getY();
                double z = location.getZ();
                float yaw = location.getYaw();
                float pitch = location.getPitch();

                plugin.getConfig().set(spawn + ".world", world);
                plugin.getConfig().set(spawn + ".x", x);
                plugin.getConfig().set(spawn + ".y", y);
                plugin.getConfig().set(spawn + ".z", z);
                plugin.getConfig().set(spawn + ".yaw", yaw);
                plugin.getConfig().set(spawn + ".pitch", pitch);
                if(!isJoin) {
                    plugin.getConfig().set(spawn + ".login", false);
                } else {
                    plugin.getConfig().set(spawn + ".login", true);
                }

                if(!isInventoryclear) {
                    plugin.getConfig().set(spawn + ".inventoryclear", false);
                } else {
                    plugin.getConfig().set(spawn + ".inventoryclear", true);
                }

                if(!isEffectclear) {
                    plugin.getConfig().set(spawn + ".effectclear", false);
                } else {
                    plugin.getConfig().set(spawn + ".effectclear", true);
                }
                plugin.saveConfig();

                if (locale != null) {
                    if (locale.equals("ja_JP")) {
                        player.sendMessage(Util.coloring(prefix + "&7スポーン地点を設定しました。"));
                    } else {
                        player.sendMessage(Util.coloring(prefix + "&7Set the spawn point."));
                    }
                }
            } else {
                if (locale != null) {
                    if (locale.equals("ja_JP")) {
                        player.sendMessage(Util.coloring(prefix + "&c権限を持っていません。"));
                    } else {
                        player.sendMessage(Util.coloring(prefix + "&cYou dont have permission."));
                    }
                }
            }
        } else {
            sender.sendMessage(Util.coloring("&cThis command cannot be run from the console."));
        }
        return false;
    }
}
