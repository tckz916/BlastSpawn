package com.github.tckz916.blastspawn.command;

import com.github.tckz916.blastspawn.BlastSpawn;
import com.github.tckz916.blastspawn.util.Util;
import com.github.tckz916.blastspawn.util.language.PlayerLanguage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by tckz916 on 2015/07/10.
 */
public class CurrentsettingsCommand implements CommandExecutor {

    private BlastSpawn plugin = BlastSpawn.getInstance();

    private String prefix = plugin.prefix;
    private String spawn = plugin.spawn;


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            String locale = PlayerLanguage.getPlayerLanguage(player);
            if (sender.hasPermission("blastspawn.command.currentsettings")) {
                String world = plugin.getConfig().getString(spawn + ".world");
                String x = plugin.getConfig().getString(spawn + ".x");
                String y = plugin.getConfig().getString(spawn + ".y");
                String z = plugin.getConfig().getString(spawn + ".z");
                String yaw = plugin.getConfig().getString(spawn + ".yaw");
                String pitch = plugin.getConfig().getString(spawn + ".pitch");

                if (locale != null) {
                    if (locale.equals("ja_JP")) {
                        player.sendMessage(Util.coloring(prefix + " &2>>>>> &3現在の設定 &2<<<<< "));
                    } else {
                        player.sendMessage(Util.coloring(prefix + " &2>>>>> &3Currentsettings &2<<<<< "));
                    }
                }

                sender.sendMessage(Util.coloring(prefix + "&2world: &3" + world));
                sender.sendMessage(Util.coloring(prefix + "&2x: &3" + x));
                sender.sendMessage(Util.coloring(prefix + "&2y: &3" + y));
                sender.sendMessage(Util.coloring(prefix + "&2z: &3" + z));
                sender.sendMessage(Util.coloring(prefix + "&2yaw: &3" + yaw));
                sender.sendMessage(Util.coloring(prefix + "&2pitch: &3" + pitch));
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
