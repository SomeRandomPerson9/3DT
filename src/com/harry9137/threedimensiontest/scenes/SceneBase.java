package com.harry9137.threedimensiontest.scenes;

import com.harry9137.threedimensiontest.render.Camera;
import com.harry9137.threedimensiontest.render.shaders.Shader;
import com.harry9137.threedimensiontest.render.Transform;
import com.harry9137.threedimensiontest.render.Window;
import com.harry9137.threedimensiontest.scenes.Objects.GenericObject;
import com.harry9137.threedimensiontest.scenes.Objects.RenderObject;

import java.util.ArrayList;

public class SceneBase {
    String objName;

    private ArrayList<RenderObject> objects = new ArrayList<RenderObject>();
    private ArrayList<GenericObject> overlayObjects = new ArrayList<>();
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
        Transform.setProjection(70f, Window.getWidth(), Window.getHeight(), 0.1f, 110f);
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

    public RenderObject getObject(int num){
        return objects.get(num);
    }

    public RenderObject getObject(String name){
        RenderObject retVal = null;
        for(RenderObject loopObj : objects){
            if(loopObj.getObjName().equals(name)){
                retVal = loopObj;
                break;
            }
        }
        return retVal;
    }

    public void addOverlay(GenericObject object){
        overlayObjects.add(object);
    }

    public GenericObject getOverlay(String name){
        GenericObject retVal = null;
        for(GenericObject loopObj : overlayObjects){
            if(loopObj.getObjName().equals(name)){
                retVal = loopObj;
                break;
            }
        }
        return retVal;
    }

    public int getBtsUpdateLvl() {
        return btsUpdateLvl;
    }

    public void setBtsUpdateLvl(int btsUpdateLvl) {
        this.btsUpdateLvl = btsUpdateLvl;
    }

    public SceneType getSceneType() {
        return sceneType;
    }

    public void setSceneType(SceneType sceneType) {
        this.sceneType = sceneType;
    }

    public ArrayList<GenericObject> getOverlayObjects() {
        return overlayObjects;
    }

    public void setOverlayObjects(ArrayList<GenericObject> overlayObjects) {
        this.overlayObjects = overlayObjects;
    }

    public String getObjName() {
        return objName;
    }

    public SceneBase setObjName(String objName) {
        this.objName = objName;
        return this;
    }


}
