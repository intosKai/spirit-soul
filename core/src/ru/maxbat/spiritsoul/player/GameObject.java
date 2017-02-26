package ru.maxbat.spiritsoul.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 *
 */
public abstract class GameObject extends Actor{
    protected Sprite sprite;
    protected Body body;
    private World world;

    public GameObject (World world) {
        this.world = world;
        sprite = new Sprite(new Texture(Gdx.files.internal("badlogic.jpg")));
        setBounds(300, 500,sprite.getWidth() / 2, sprite.getHeight() / 2);
    }

    public void moveRight(float power) {
        System.out.println("moved");
    }

    protected void createBody(Shape shape, BodyDef.BodyType type) {
        BodyDef bDef = new BodyDef();
        bDef.position.set(getX(), getY());
        bDef.type = type;

        body = world.createBody(bDef);

        FixtureDef fDef = new FixtureDef();
        fDef.shape = shape;
        fDef.density = 0.5f;
        fDef.friction = 0.4f;
        fDef.restitution = 0.6f; // Make it bounce a little bit

        // Create our fixture and attach it to the body
        Fixture fixture = body.createFixture(fDef);

        // Remember to dispose of any shapes after you're done with them!
        // BodyDef and FixtureDef don't need disposing, but shapes do.
        shape.dispose();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        setPosition(body.getPosition().x - sprite.getWidth() / 2, body.getPosition().y - sprite.getHeight() /2 );
        batch.draw(sprite, getX(), getY());
    }
}
