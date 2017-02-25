package me.podder.server.mvc.model;

import me.podder.api.IPlayer;

import java.io.Serializable;

public class Player implements Cloneable, Serializable, IPlayer {
    //states
    private byte heroType;
    private byte curAnim;
    private boolean direction;
    private float x;
    private float y;
    private byte hp;

    private byte id;

    public Player() {
    }

    public byte getId() {
        return id;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public boolean isDirection() {
        return direction;
    }

    public void setDirection(boolean direction) {
        this.direction = direction;
    }

    public byte getCurAnim() {
        return curAnim;
    }

    public void setCurAnim(byte curAnim) {
        this.curAnim = curAnim;
    }

    public byte getHeroType() {
        return heroType;
    }

    public void setHeroType(byte heroType) {
        this.heroType = heroType;
    }

    public void setId(byte id) {
        this.id = id;
    }
    public Player clone() {
        Player player = new Player();
        player.setId(this.id);
        player.setY(this.y);
        player.setX(this.x);
        player.setHeroType(this.heroType);
        player.setCurAnim(this.curAnim);
        player.setDirection(this.direction);
        player.setHp(this.hp);
        return player;
    }
    @Override
    public String toString() {
        return "{ id = " + id +
                ", heroType = " + heroType +
                ", curAnim = " + curAnim +
                ", direction = " + direction +
                ", x = " + x +
                ", y = " + y + " }";
    }

    public byte getHp() {
        return hp;
    }

    public void setHp(byte hp) {
        this.hp = hp;
    }
}
