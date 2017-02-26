package ru.maxbat.spiritsoul.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import ru.maxbat.spiritsoul.controller.BodyFactory;
import ru.maxbat.spiritsoul.controller.MyContactListener;

/**
 * Created by user on 26.02.2017.
 */
public class GameWorld {
    private final Array<HoverPlatform> platforms;
    private final float width;
    private final float height;
    private World world;

    public Player getPlayer() {
        return player;
    }

    private Player player;

    public GameWorld() {
        platforms = new Array<HoverPlatform>();
        width = 30;
        height = 8;
        world = new World(new Vector2(0, -20), true);
        world.setContactListener(new MyContactListener(world));
        createWorld();
    }

    private void createWorld() {
        platforms.add(new HoverPlatform(world, 3F, 3, 1,0.25F, 2, 0, 2));

        for(int i=0;i<width; ++i){
            Body boxGround = createBox(BodyDef.BodyType.StaticBody, 0.5F, 0.5F, 2);
            boxGround.setTransform(i,0,0);
            boxGround.getFixtureList().get(0).setUserData("bd");
            boxGround = createBox(BodyDef.BodyType.StaticBody, 0.5F, 0.5F, 0);
            boxGround.setTransform(i, height-1,0);
            boxGround.getFixtureList().get(0).setUserData("b");
        }
    }

    private Body createBox(BodyDef.BodyType staticBody, float w, float h, int density) {
        BodyDef def = new BodyDef();
        def.type = staticBody;
        Body box = world.createBody(def);
        PolygonShape poly = new PolygonShape();
        poly.setAsBox(w, h);
        box.createFixture(poly, density);
        poly.dispose();
        return box;
    }


    /**
     * <p>Создаем игрока в мире</p>
     * @param x координата
     * @param y координата
     */
    public void createPlayer(float x, float y) {
        player = new Player(BodyFactory.getBody(world, BodyDef.BodyType.DynamicBody, new Vector2(x, y)));
        player.getBody().setTransform(6.0f, 4.0f, 0);
        player.getBody().setFixedRotation(true);
    }

    public World getWorld() {
        return world;
    }
}
