package com.harry9137.api.render.lighting;

import com.harry9137.api.render.math.Vector3f;

public class BaseLight {
    private Vector3f color;
    private float intensity;

    public BaseLight(Vector3f color, float intensity){
        this.color = color;
        this.intensity = intensity;
    }

    public Vector3f getColor() {
        return color;
    }

    public void setColor(Vector3f color) {
        this.color = color;
    }

    public float getIntensity() {
        return intensity;
    }

    public void setIntensity(float intensity) {
        this.intensity = intensity;
    }
}
