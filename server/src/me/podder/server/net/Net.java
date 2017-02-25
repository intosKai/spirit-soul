package me.podder.server.net;

import me.podder.api.IPlayer;

import java.io.*;
import java.util.Map;

public class Net implements IServer {

    private Net() {}

    public static void sendMap(Map map) throws IOException {

    }

    public static void sendCoords(int netStatus) throws IOException {

    }

    public static IPlayer acceptPlayer(ObjectInputStream reader) throws IOException, ClassNotFoundException {
        IPlayer player;
        player = (IPlayer) reader.readObject();
        return player;
    }
    static void sendStatesOfPlayers(ObjectOutputStream out, Map<Byte, IPlayer> map) throws  IOException, ClassNotFoundException {
        //TODO: send map
        out.writeObject(map);
        out.flush();
        out.reset();
    }

}
