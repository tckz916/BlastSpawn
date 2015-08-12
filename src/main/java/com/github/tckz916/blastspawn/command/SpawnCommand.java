package com.github.tckz916.blastspawn.command;

import com.github.tckz916.blastspawn.BlastSpawn;
import com.github.tckz916.blastspawn.util.Util;
import com.github.tckz916.blastspawn.util.language.PlayerLanguage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by tckz916 on 2015/07/10.
 */
public class SpawnCommand implements CommandExecutor {

    private BlastSpawn plugin = BlastSpawn.getInstance();

    private String prefix = plugin.prefix;
    private String spawn = plugin.spawn;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            String locale = PlayerLanguage.getPlayerLanguage(player);
            if (sender.hasPermission("blastspawn.command.spawn")) {

                if (plugin.getConfig().get(spawn) == null) {
                    if (locale.equals("ja_JP")) {
                        player.sendMessage(Util.coloring(prefix + "&7設定が見つかりません。"));
                    } else {
                        player.sendMessage(Util.coloring(prefix + "&7Setting Not Found"));
                    }
                    return true;
                }

                Location location = player.getLocation();

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

                if (locale != null) {
                    if (locale.equals("ja_JP")) {
                        player.sendMessage(Util.coloring(prefix + "&7テレポート中..."));
                    } else {
                        player.sendMessage(Util.coloring(prefix + "&7Teleporting..."));
                    }
                }

            } else {
                if (locale != null) {
                    if (locale.equals("ja_JP")) {
                        sender.sendMessage(Util.coloring(prefix + "&c権限を持っていません。"));
                    } else {
                        sender.sendMessage(Util.coloring(prefix + "&cYou dont have permission."));
                    }
                }
            }
        } else {
            sender.sendMessage(Util.coloring("&cThis command cannot be run from the console."));
        }
        return false;
    }
}
