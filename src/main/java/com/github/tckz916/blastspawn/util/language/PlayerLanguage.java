package com.github.tckz916.blastspawn.util.language;

import org.bukkit.entity.Player;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by tckz916 on 2015/07/10.
 */
public class PlayerLanguage {

    private static Object getValue(Object instance, String fieldName) throws Exception {
        Field field = instance.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(instance);
    }

    private static Object getEntityPlayer(Player player) throws Exception {
        Method getHandle = player.getClass().getMethod("getHandle");
        Object entityplayer = getHandle.invoke(player);
        return entityplayer;
    }

    public static String getPlayerLanguage(Player player) {
        try {
            Object object = getEntityPlayer(player);
            String string = (String) getValue(object, "locale");
            return string;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
