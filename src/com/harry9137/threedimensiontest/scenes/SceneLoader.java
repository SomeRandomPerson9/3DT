package com.harry9137.threedimensiontest.scenes;

import com.harry9137.threedimensiontest.main.Game;
import com.harry9137.threedimensiontest.main.Launch;
import com.harry9137.threedimensiontest.render.math.Matrix4f;
import com.harry9137.threedimensiontest.render.math.Vector3f;
import com.harry9137.threedimensiontest.util.resources;
import org.lwjgl.opengl.Display;

import java.util.ArrayList;
import java.util.HashMap;

import org.lwjgl.opengl.GL11;

public class SceneLoader {
    private static HashMap <Integer, SceneBase> scenes = new HashMap<>();
    private static SceneBase selectedScene;
    private static int selectedSceneNumber;
    public static void addScene(Integer integer, SceneBase scene){
        scenes.put(integer, scene);
    }
    public static void selectScene(Integer integer){
        if(selectedScene != null) {
            selectedScene.cleanup();
            selectedScene.getShader().updateUniforms(new Matrix4f(),new Matrix4f(), null);
        }
        selectedScene = scenes.get(integer);
        selectedSceneNumber = integer;
        selectedScene.specialInit();
    }
    public static void updateScene(){
        if(selectedScene != null) {
            selectedScene.update();
            for (int i = 0; i < scenes.size(); i++) {
                if (scenes.get(i).getBtsUpdateLvl() > 0) {
                    scenes.get(i).update();
                }
                if (selectedSceneNumber == i) {
                    selectedScene.update();
                }
            }
        }
    }
    public static void renderScene(){
        if(selectedScene.sceneType == SceneType.THREE_DIMENSIONAL) {
            for (RenderObject renderObject : selectedScene.getObjects()) {
                selectedScene.getShader().bind();
                if(renderObject.isHeld()) {
                    Object[] temp = (renderObject.getTransform().getProjectedTransformationHeld(new Matrix4f().initTranslation(renderObject.getLocation().GetX(), renderObject.getLocation().GetY(), renderObject.getLocation().GetZ())));
                    selectedScene.getShader().updateUniforms(renderObject.getTransform().getTransformation(), (Matrix4f)temp[0], renderObject.getMaterial());
                    //renderObject.setLocation(((Matrix4f)temp[2]).get;
                }
                else{
                    selectedScene.getShader().updateUniforms(renderObject.getTransform().getTransformation(), renderObject.getTransform().getProjectedTransformation(new Matrix4f().initTranslation(renderObject.getLocation().GetX(), renderObject.getLocation().GetY(), renderObject.getLocation().GetZ())), renderObject.getMaterial());
                }
                renderObject.getMesh().draw();
            }
        }
        else if(selectedScene.sceneType == SceneType.TWO_DIMENSIONAL){
            if(selectedScene instanceof Scene2DVideo){

                ((Scene2DVideo) selectedScene).render();
            }
        }
    }
    public static void calcPhysics(){
        if(selectedScene != null && false) {
            for (RenderObject object : selectedScene.getObjects()) {
                if(object.isPhys()) {

                    object.getTransform().addTranslation(object.getLocation());

                    int tps = Game.getInstance().getTps();
                    //Gravity
                    if (object.getVelocity().GetY() >= resources.terminalVelocity) {
                        object.setAcceleration(new Vector3f((float) object.getAcceleration().GetX(), (float) (object.getAcceleration().GetY() - resources.gravity / tps), (float) object.getAcceleration().GetZ()));
                    }

                    //Acceleration to Velocity
                    if (object.getAcceleration().GetX() != 0) {
                        object.getVelocity().SetX((float) (object.getAcceleration().GetX() / tps) + object.getVelocity().GetX());
                    }
                    if (object.getAcceleration().GetY() != 0) {
                        object.getVelocity().SetY((float) (object.getAcceleration().GetY() * Launch.TPS / 100) + object.getVelocity().GetY());
                    }
                    if (object.getAcceleration().GetX() != 0) {
                        object.getVelocity().SetZ((float) (object.getAcceleration().GetZ() * Launch.TPS / 100) + object.getVelocity().GetZ());
                    }
                    object.setAcceleration(new Vector3f(0, 0, 0));

                    //Velocity to Location
                    if (object.getAcceleration().GetX() != 0) {
                        object.getLocation().SetX((float) (object.getAcceleration().GetX() / tps) + object.getVelocity().GetX());
                    }
                    if (object.getAcceleration().GetY() != 0) {
                        object.getLocation().SetY((float) (object.getAcceleration().GetY() * Launch.TPS / 100) + object.getVelocity().GetY());
                    }
                    if (object.getAcceleration().GetX() != 0) {
                        object.getLocation().SetZ((float) (object.getAcceleration().GetZ() * Launch.TPS / 100) + object.getVelocity().GetZ());
                    }
                }

            }
        }
    }
    public static void updateSceneInput(){
        if(selectedScene != null) {
            selectedScene.input();
        }
    }
    public static int getSelectedSceneNumber(){
        return selectedSceneNumber;
    }
    public static SceneBase getSelectedScene(){
        return selectedScene;
    }
    public static HashMap<Integer, SceneBase> getScenes() {
        return scenes;
    }

}
