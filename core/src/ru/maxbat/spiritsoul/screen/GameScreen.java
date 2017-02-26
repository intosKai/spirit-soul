package ru.maxbat.spiritsoul.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import ru.maxbat.spiritsoul.SpiritSoulGame;
import ru.maxbat.spiritsoul.model.GameWorld;

import static ru.maxbat.spiritsoul.util.Constants.*;

public class GameScreen implements Screen {
    private Box2DDebugRenderer renderer;
    private GameWorld world;
    private Camera cam;
    private Stage stage;
    private SpiritSoulGame game;

    public GameScreen(SpiritSoulGame game) {
        world = new GameWorld();
        this.game = game;
        renderer = new Box2DDebugRenderer();
        cam = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        cam.position.set(VIEWPORT_WIDTH / 2, VIEWPORT_HEIGHT / 2, 0);
        cam.update();
        stage = new Stage(new ScreenViewport());
    }
    @Override
    public void show() {
        world.createPlayer(5, 3);
        stage.addActor(world.getPlayer());
    }

    @Override
    public void render(float delta) {
        Gdx.graphics.getGL20().glClearColor( 1, 0, 0, 1 );
        Gdx.graphics.getGL20().glClear( GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT );
        renderer.render(world.getWorld(), cam.combined);
        world.getWorld().step(delta, 4, 4);
        cam.position.set(world.getPlayer().getPosition().x, world.getPlayer().getPosition().y, 0);
        cam.update();
        game.getBatch().begin();
        stage.draw();
        game.getBatch().end();
    }

    @Override
    public void resize(int width, int height) {
        cam.viewportHeight = (VIEWPORT_WIDTH / width) * height;
        cam.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        renderer.dispose();
    }

}
