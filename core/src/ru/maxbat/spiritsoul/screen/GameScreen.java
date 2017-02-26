package ru.maxbat.spiritsoul.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import ru.maxbat.spiritsoul.SpiritSoulGame;
import ru.maxbat.spiritsoul.player.GameObject;

public class GameScreen implements Screen {
    private final SpiritSoulGame game;
    private final World world;
    private OrthographicCamera camera;
    private Box2DDebugRenderer renderer;
    private Stage stage;
    private GameObject player;

    public GameScreen(SpiritSoulGame game) {
        this.game = game;
        this.world = new World(new Vector2(0, -9.8f), false);
        renderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera();
       // stage = new Stage(new ScreenViewport(camera));
    }
    @Override
    public void show() {
        camera.setToOrtho(false, Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight());
        player = new GameObject(world);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        renderer.render(world, camera.combined);
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width / 2, height / 2);
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
        world.dispose();
    }

    private void update() {
        world.step(1 / 60f, 6, 2);
    }
}
