package ru.maxbat.spiritsoul.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import ru.maxbat.spiritsoul.resource.Resource;

import static ru.maxbat.spiritsoul.util.Constants.PPM;
import static ru.maxbat.spiritsoul.util.Constants.SCALE;

/**
 * Created by user on 26.02.2017.
 */
public class Player extends BaseObject {
    final static float MAX_VELOCITY = 3f;
    public final static float SPEED = 5f;
    public final static float W_SIZE = 64;
    public final static float H_SIZE = 128;
    public final static float ANIM_SPEED = 2f;
    private float stateTime;
    private byte state;

    public Fixture playerPhysicsFixture;
    public Fixture playerSensorFixture;
    private Animation[] animations;

    @Override
    public void draw(Batch batch, float parentAlpha) {
        stateTime += parentAlpha;
        batch.draw(animations[state].getKeyFrame(stateTime, true), getPixelX() - W_SIZE / 2, getPixelY() + H_SIZE * 1.4f);
        //WTF: Без понятий почему 1.4f, просто работает
    }

    public Player(Body b) {
        super(b);
        body = b;
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(W_SIZE / PPM / SCALE, H_SIZE / PPM / SCALE);
        playerPhysicsFixture = body.createFixture(shape, 0);
        shape.dispose();

//        CircleShape circle = new CircleShape();
//        circle.setRadius(0.5f);
//        circle.setPosition(new Vector2(0, -1f));
//        playerSensorFixture = body.createFixture(circle, 0);
//        circle.dispose();
        //box.setBullet(true); //под вопросом

        initAnim();
    }

    private void initAnim() {
        stateTime = 0;
        state = 0;
        animations = new Animation[10];
        animations[0] = new Animation(ANIM_SPEED, Resource.atl.findRegions("Stand"));
        animations[1] = new Animation(ANIM_SPEED, Resource.atl.findRegions("Walk"));
    }

    public float getFriction() {
        return playerSensorFixture.getFriction();
    }

    public  Body getBody() {
        return body;
    }

    public void setFriction(float f) {
        playerSensorFixture.setFriction(f);
        playerPhysicsFixture.setFriction(f);
    }

    public Vector2 getPosition() {
        return body.getPosition();
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    Vector2 velocity = new Vector2();

    public void update(float delta) {
        Vector2 vel = body.getLinearVelocity();
        velocity.y = vel.y;
        body.setLinearVelocity(velocity);
        if (isJump) {
            body.applyLinearImpulse(0, 14, body.getPosition().x, body.getPosition().y, true);
            isJump = false;
        }
    }
    boolean isJump = false;
    public void jump() {
        isJump = true;
    }
    public void resetVelocity() {
        getVelocity().x = 0;
        getVelocity().y = 0;
    }


}
