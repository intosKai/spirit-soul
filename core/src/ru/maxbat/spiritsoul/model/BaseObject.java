package ru.maxbat.spiritsoul.model;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.Actor;
import static ru.maxbat.spiritsoul.util.Constants.PPM;

/**
 * Created by user on 26.02.2017.
 */
public class BaseObject extends Actor {
    protected Body body;
    protected Sprite sprite;

    public BaseObject(Body body) {
        super();
        this.body = body;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        //sprite.setPosition(this.getWidth(), this.getHeight());
        sprite.draw(batch, parentAlpha);
        super.draw(batch, parentAlpha);
    }

    @Override
    public float getX() {
        return body.getPosition().x;
    }

    public float getPixelX() {
        return body.getPosition().x * PPM;
    }

    @Override
    public void setX(float x) {
        body.getPosition().x = x;
    }

    @Override
    public float getY() {
        return body.getPosition().y;
    }

    public float getPixelY() {
        return body.getPosition().y * PPM;
    }

    @Override
    public void setY(float y) {
        body.getPosition().y = y;
    }

    @Override
    public void setPosition(float x, float y) {
        body.getPosition().x = x;
        body.getPosition().y = y;
    }
}
