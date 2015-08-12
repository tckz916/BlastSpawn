package com.github.tckz916.blastspawn;

import com.github.tckz916.blastspawn.command.CurrentsettingsCommand;
import com.github.tckz916.blastspawn.command.SetSpawnCommand;
import com.github.tckz916.blastspawn.command.SpawnCommand;
import com.github.tckz916.blastspawn.listener.PlayerListener;
import com.github.tckz916.blastspawn.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

/**
 * Created by tckz916 on 2015/07/10.
 */
public class BlastSpawn extends JavaPlugin {

    private static BlastSpawn instance;

    private PluginManager plm = Bukkit.getServer().getPluginManager();

    public static String prefix = Util.coloring(" [&c&lBSS&r] ");
    public static String spawn = Util.coloring("spawn");

    public void onEnable() {
        instance = this;

        super.onEnable();

        /*
         *config.ymlの生成
         */

        saveDefaultConfig();

        registercommand();
        registerlistener();

    }

    public void onDisable() {
        super.onDisable();
    }

    private void registercommand(){
        getCommand("currentsettings").setExecutor(new CurrentsettingsCommand());
        getCommand("setspawn").setExecutor(new SetSpawnCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
    }

    private void registerlistener(){
        plm.registerEvents(new PlayerListener(), this);
    }

    public static BlastSpawn getInstance() {
        return instance;
    }
}
