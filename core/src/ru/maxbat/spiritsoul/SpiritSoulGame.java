package ru.maxbat.spiritsoul;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.maxbat.spiritsoul.model.GameWorld;
import ru.maxbat.spiritsoul.resource.Resource;
import ru.maxbat.spiritsoul.screen.MainMenu;


/**
 * <p>Гланый класс игры</p>
 * @since 0.1
 * @version 0.1
 */

public class SpiritSoulGame extends Game {
	private SpriteBatch batch;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		Resource.load();
		setScreen(new MainMenu(this));
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose() {
		super.dispose();
		batch.dispose();
	}

	public SpriteBatch getBatch() {
		return batch;
	}
}
