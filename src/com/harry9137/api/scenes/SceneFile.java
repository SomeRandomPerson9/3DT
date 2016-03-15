package com.harry9137.api.scenes;

import com.harry9137.api.render.Mesh;

public class SceneFile {
    public Mesh mesh;
    public float xLocation;
    public float yLocation;
    public float zLocation;
    public SceneFile(Mesh mesh, Float xLocation, Float yLocation, Float zLocation){
        this.mesh = mesh;
        this.xLocation = xLocation;
        this.yLocation = yLocation;
        this.zLocation = zLocation;
    }
}
