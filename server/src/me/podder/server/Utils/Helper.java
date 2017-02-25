package me.podder.server.Utils;

import me.podder.api.IPlayer;
import me.podder.server.mvc.model.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 21.02.2017.
 */
public class Helper {
    private Helper() {}

    public static Map<Byte, IPlayer> copyMapOfPlayers(Map<Byte, IPlayer> origMap) {
        Map<Byte, IPlayer> dstMap = new HashMap<>();
        for (byte b : origMap.keySet()) {
            dstMap.put(b, ((Player)origMap.get(b)).clone());
        }
        return dstMap;
    }
}
