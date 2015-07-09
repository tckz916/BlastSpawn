package com.github.tckz916.blastspawn.util;

/**
 * Created by tckz916 on 2015/07/10.
 */
public class Util {

    public static String coloring(String str) {
        return str.replaceAll("&([0-9a-fk-or])", "\u00A7$1");
    }
}
