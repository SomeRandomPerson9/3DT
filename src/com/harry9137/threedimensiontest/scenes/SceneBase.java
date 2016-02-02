package com.harry9137.threedimensiontest.scenes;

import com.harry9137.threedimensiontest.render.Camera;
import com.harry9137.threedimensiontest.render.Shader;
import com.harry9137.threedimensiontest.render.Transform;
import com.harry9137.threedimensiontest.render.Window;

import java.util.ArrayList;

public class SceneBase {
    private ArrayList<RenderObject> objects = new ArrayList<RenderObject>();
    private Shader shader;
    private Camera camera;
    private Transform transform;
    private int btsUpdateLvl = 0;
    protected SceneType sceneType;
    public void update(){

    }
    public void input(){

    }
    public void specialInit(){

    }
    public void cleanup(){

    }

    public void restartMatrix(){
        this.transform.setProjection(70f, Window.getWidth(), Window.getHeight(), 0.1f, 110f);
    }

    public ArrayList<RenderObject> getObjects() {
        return objects;
    }

    public void setObjects(ArrayList<RenderObject> objects) {
        this.objects = objects;
    }

    public Shader getShader() {
        return shader;
    }

    public void setShader(Shader shader) {
        this.shader = shader;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public Transform getTransform() {
        return transform;
    }

    public void setTransform(Transform transform) {
        this.transform = transform;
    }
    public void addObject(RenderObject object){
        objects.add(object);
    }

    public int getBtsUpdateLvl() {
        return btsUpdateLvl;
    }

    public void setBtsUpdateLvl(int btsUpdateLvl) {
        this.btsUpdateLvl = btsUpdateLvl;
    }


}
