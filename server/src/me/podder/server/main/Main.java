package me.podder.server.main;


import me.podder.api.IPlayer;
import me.podder.server.Utils.Converter;
import me.podder.server.base.PlayerBase;
import me.podder.server.net.Connect;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Properties;

public class Main {
    private static final Logger log = Logger.getLogger(Main.class);
    private static byte[] ip;
    private static Properties properties;
    private static int port;
    private static long connectionCounter;


    public static void main(String[] args) {
        init();
        setProperty();
        try {
            startServer();
        } catch (IOException e) {
            log.error(e.getMessage());
        } finally {
            log.info("Server shutdown");
        }
    }



    private static void setProperty() {
        log.info("Setting up properties");
        log.info("\tCheck for existing...");
        //TODO: Checking on exists files with props
        log.info("\tInit ip");


    }
    private static void startServer() throws IOException {
        log.info("Server starting! Opening socket...");
        ServerSocket serverSocket = new ServerSocket(port, 0, InetAddress.getByAddress(ip));
        log.info("Listening on " + serverSocket.getInetAddress() + ":" + serverSocket.getLocalPort());
        while (!serverSocket.isClosed()) {
            Socket socket = serverSocket.accept();
            log.info("New connection from " + socket.getInetAddress() + " accepted");
            new Connect(socket, connectionCounter++);
        }
    }

    private static void init() {
        log.info("Loading default properties...");
        properties = new Properties();
        try {
            properties.load(Main.class.getResourceAsStream("/server.properties"));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        ip = Converter.StringArrayToByteArray(properties.getProperty("ip").split("\\."));
        log.info("Default IP is " + Arrays.toString(ip));
        port = Integer.valueOf(properties.getProperty("port"));
        log.info("Default port is " + port);
        PlayerBase.maxPlayers = Byte.parseByte(properties.getProperty("max_players"));
        log.info("Default max players 12");
    }
}
