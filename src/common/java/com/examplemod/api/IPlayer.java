package com.examplemod.api;

import com.badlogic.gdx.math.Vector3;

public interface IPlayer {

    Vector3 getViewDirection();

    void setViewDirection(Vector3 viewDirection);
}
