package me.podder.server.base;

import me.podder.api.IPlayer;
import me.podder.server.Utils.Helper;
import me.podder.server.mvc.model.Player;

import java.util.*;

public class PlayerBase implements IPlayerBase, Cloneable {
    private volatile Map<Byte, IPlayer> lastPlayerMap;
    private Set<Byte> emptyID;
    public static byte maxPlayers;

    public PlayerBase() {
        lastPlayerMap = new HashMap<>();
        emptyID = new HashSet<>();
        for (byte i = 0; i < maxPlayers; i++) emptyID.add(i);
    }

    @Override
    synchronized public byte addPlayer(Player player) {
        if (emptyID.isEmpty()) throw new IndexOutOfBoundsException("All slots are busy");
        else {
            for (byte i = 0; i < maxPlayers; i++) {
                if (emptyID.contains(i)) {
                    lastPlayerMap.put(i, player);
                    emptyID.remove(i);
                    return i;
                }
            }
            throw new IndexOutOfBoundsException("All slots are busy");
        }
    }

    @Override
    synchronized public void deletePlayer(byte id) {
        synchronized (lastPlayerMap) {
            if (lastPlayerMap.containsKey(id))
                lastPlayerMap.remove(id);
        }
    }

    @Override
    public void updatePlayer(IPlayer player) {
        byte id = player.getId();
        if(lastPlayerMap.containsKey(id)) {
            lastPlayerMap.put(id, player);
        }
    }

    @Override
    public Map<Byte, IPlayer> getAllPlayer() {
        return lastPlayerMap;
    }


    @Override
    public PlayerBase clone() {
        PlayerBase newBase = new PlayerBase();
        newBase.lastPlayerMap = Helper.copyMapOfPlayers(this.lastPlayerMap);
        newBase.emptyID.retainAll(this.emptyID);
        return newBase;
    }
}
