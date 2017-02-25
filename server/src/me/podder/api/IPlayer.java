package me.podder.api;

import java.io.Serializable;

public interface IPlayer extends Serializable {
    byte getId();
    float getY();
    void setY(float y);
    float getX();
    void setX(float x);
    boolean isDirection();
    void setDirection(boolean direction);
    byte getCurAnim();
    void setCurAnim(byte curAnim);
    byte getHeroType();
    void setHeroType(byte heroType);
    void setId(byte id);
}
