package ru.maxbat.spiritsoul.controller;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;

/** <p>Класс-фабрика создающий тела</p>
 * @since 0.1
 * @version 0.1
 *
 */
public class BodyFactory {
    private BodyFactory() {}


    /**
     * <p>Метод фабрики создающий в мире пустое тело</p>
     * @param world принимает мир для создания в нем тела
     * @param type тип создаваемого тела
     * @return возвращает пустое тело, требуется создать fixture
     */
    public static Body getBody(World world, BodyDef.BodyType type) {
        return getBody(world, type, new Vector2(0, 0));
    }

    /**
     * <p>Метод фабрики создающий в мире пустое тело с заданными координатами</p>
     * @param world принимает мир для создания в нем тела
     * @param type тип создаваемого тела
     * @param pos координаты тела
     * @return возвращает пустое тело, требуется создать fixture
     */
    public static Body getBody(World world, BodyDef.BodyType type, Vector2 pos) {
        Body body;
        BodyDef def = new BodyDef();
        def.type = type;
        def.fixedRotation = true;
        def.position.set(pos.x, pos.y);
        body = world.createBody(def);
        return body;
    }
}
