package me.podder.server.net;

import me.podder.api.IPlayer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Map;

public interface IServer {
    static void sendMap(Map map) throws IOException {}
    static void sendState(int netStatus) throws IOException {}
    static void acceptPlayer(ObjectInputStream reader, IPlayer player) throws IOException, ClassNotFoundException {}
    static void sendStatesOfPlayers(ObjectInputStream inputStream, Map<Byte, IPlayer> map) throws  IOException, ClassNotFoundException {}
}
