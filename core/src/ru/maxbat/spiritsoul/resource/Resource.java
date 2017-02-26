package ru.maxbat.spiritsoul.resource;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by user on 26.02.2017.
 */
public class Resource {

    public static TextureAtlas atl;

    private Resource() {}

    public static void load() {
        AssetManager manager = new AssetManager();
        manager.load("spritelist.pack", TextureAtlas.class);
        manager.finishLoadingAsset("spritelist.pack");
        atl = manager.get("spritelist.pack");
    }
}
