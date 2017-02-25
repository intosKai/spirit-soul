package ru.maxbat.spiritsoul.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

/**
 * <p>Скрин меню</p>
 * @since 0.1
 * @version 0.1
 */
public class MainMenu implements Screen {
    private final Game game;

    public MainMenu(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        System.out.println("ОТОБРАЖЕН");
    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

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

    }
}
