package me.podder.server.net;

import me.podder.api.IPlayer;
import org.apache.log4j.Logger;
import me.podder.server.base.BaseController;
import me.podder.server.mvc.model.Player;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class Connect extends Thread {

    private final Socket socket;
    private final Long id;
    private final Logger log;
    private long startTime;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private BaseController base;
    private IPlayer player;

    public Connect(Socket socket, Long id) {
        super("Connection-" + id);
        this.socket = socket;
        this.id = id;
        this.log = Logger.getLogger(super.getName());
        setUp();
    }

    private void setUp() {
        log.info("Setting up connection");
        setDaemon(true);
        base = BaseController.getInstance();
        startTime = 0;
        try {
            log.info("Opening streams...");
            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            log.error(e.getMessage());
            try {
                socket.close();
            } catch (IOException e1) {
                log.error(e1.getMessage());
            }
            return;
        }
        start();
    }

    @Override
    public void run() {
        log.info("Running...");
        while(isAlive() && !isInterrupted()) {
            while (System.currentTimeMillis() - startTime < 17) try {
                //log.info("Waiting...");
                sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            startTime = System.currentTimeMillis();

            //TODO: body

            try {
                //log.info("Reading firsts byte");
                byte type = inputStream.readByte();
                //log.info("First byte: " + type);
                switch (type) {
                    case NetStatus.INIT:
                        netInit(inputStream.readByte());
                        outputStream.writeObject(player);
                        outputStream.reset();
                        outputStream.flush();
                        break;
                    case NetStatus.UPDATE:
                        netServerUpdate();
                        netClientUpdate();
                        break;
                    case NetStatus.DISCONNECT:
                        netDisconnect();
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
                netDisconnect();
                break;
            }
        }
    }

    private void netInit(byte heroClass) {
        log.info("New player initializing");
        this.player = base.newPlayer(heroClass);
    }
    private void netServerUpdate() {
        try {
            player = Net.acceptPlayer(inputStream);
            base.updatePlayer(player);
        } catch (IOException | ClassNotFoundException e) {
            log.error(e.getMessage());
        }

    }

    /**
     * <p>Getting data from base and
     * sending to client</p>
     */
    private void netClientUpdate() {
        try {
            Net.sendStatesOfPlayers(outputStream, base.getPlayersMap());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void netDisconnect() {
        base.deletePlayer(player);
        dispose();
    }

    public void dispose() {
        try {
            socket.close();
            this.join();
        } catch (IOException | InterruptedException e) {
            log.error(e.getMessage());
        }
    }
}
