package me.podder.server.base;

import me.podder.api.IPlayer;
import org.apache.log4j.Logger;
import me.podder.server.mvc.model.Player;

import java.util.Map;
import java.util.Random;


/**
 * <p>Controll the bases of players</p>
 */
public class BaseController {
    private static volatile BaseController instance;
    private static volatile Map<Byte, Boolean> update;
    private volatile long newTimeStamp;
    private volatile long oldTimeStamp;
    private IPlayerBase oldPlayerBase;
    private IPlayerBase newPlayerBase;
    private Logger log;

    private BaseController() {
        log = Logger.getLogger(this.getClass());
        oldPlayerBase = new PlayerBase();
        newPlayerBase = new PlayerBase();
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    //wait for updating
                    //log.info("Waiting time: " + (newTimeStamp - oldTimeStamp));
                    if (newTimeStamp - oldTimeStamp < 17)
                        Thread.sleep(17 - (newTimeStamp - oldTimeStamp));
                    //wait(17 - (newTimeStamp - oldTimeStamp));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (newPlayerBase) {
                    oldTimeStamp = System.currentTimeMillis();
                    oldPlayerBase = newPlayerBase.clone();
                }
                //handle colls, recount hp
                newTimeStamp = System.currentTimeMillis();
            }
        });
        thread.start();
    }

    public static BaseController getInstance() {
        synchronized (BaseController.class) {
            if (instance == null) {
                instance = new BaseController();
            }
            return instance;
        }
    }

    public Player newPlayer(byte heroClass) throws ArrayIndexOutOfBoundsException {
        Player player = new Player();
        Random random = new Random();
        player.setHeroType(heroClass);
        player.setX((short)random.nextInt(Short.MAX_VALUE));
        player.setY((short)random.nextInt(Short.MAX_VALUE));
        player.setId(newPlayerBase.addPlayer(player));
        return player;
    }

    public void deletePlayer(IPlayer player) {
        byte id = player.getId();
        newPlayerBase.deletePlayer(id);
    }

    public Map<Byte, IPlayer> getPlayersMap() {
        return oldPlayerBase.getAllPlayer();
    }

    synchronized public void updatePlayer(IPlayer player) {
        newPlayerBase.updatePlayer(player);
    }
}
