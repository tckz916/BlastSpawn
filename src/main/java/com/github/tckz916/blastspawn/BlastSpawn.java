package com.github.tckz916.blastspawn;

import com.github.tckz916.blastspawn.command.CurrentsettingsCommand;
import com.github.tckz916.blastspawn.command.SetSpawnCommand;
import com.github.tckz916.blastspawn.command.SpawnCommand;
import com.github.tckz916.blastspawn.util.Util;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

/**
 * Created by tckz916 on 2015/07/10.
 */
public class BlastSpawn extends JavaPlugin {

    private static BlastSpawn instance;

    public static String prefix = Util.coloring(" [&c&lBSS&r] ");
    public static String spawn = Util.coloring("Spawn");

    public void onEnable() {
        instance = this;

        super.onEnable();

        /*
         *config.ymlの生成
         */
        File config = new File(getDataFolder(), "config.yml");
        if (!config.exists()) {
            YamlConfiguration configFile = YamlConfiguration.loadConfiguration(config);
            saveDefaultConfig();
            try {
                configFile.save(config);
            } catch (IOException ex) {
            }
            reloadConfig();
        }

        registercommand();

    }

    public void onDisable() {
        super.onDisable();
    }

    private void registercommand(){
        getCommand("currentsettings").setExecutor(new CurrentsettingsCommand());
        getCommand("setspawn").setExecutor(new SetSpawnCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());
    }

    public static BlastSpawn getInstance() {
        return instance;
    }
}