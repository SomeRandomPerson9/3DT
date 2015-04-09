package com.harry9137.threedimensiontest.render.lighting;

import com.harry9137.threedimensiontest.render.math.Vector3f;

public class DirectionalLight {
    private BaseLight base;
    private Vector3f direction;

    public  DirectionalLight(BaseLight base, Vector3f direction){
        this.base = base;
        this.direction = direction.Normalized();
    }

    public BaseLight getBase() {
        return base;
    }

    public void setBase(BaseLight base) {
        this.base = base;
    }

    public Vector3f getDirection() {
        return direction;
    }

    public void setDirection(Vector3f direction) {
        this.direction = direction;
    }
}
