package com.harry9137.threedimensiontest.scenes;

import com.harry9137.threedimensiontest.main.Game;
import com.harry9137.threedimensiontest.main.Launch;
import com.harry9137.threedimensiontest.render.math.Vector3f;
import com.harry9137.threedimensiontest.util.resources;

import java.util.ArrayList;
import java.util.HashMap;

public class SceneLoader {
    private static HashMap <Integer, SceneBase> scenes = new HashMap<>();
    private static SceneBase selectedScene;
    private static int selectedSceneNumber;
    public static void addScene(Integer integer, SceneBase scene){
        scenes.put(integer, scene);
    }
    public static void selectScene(Integer integer){
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
        for(RenderObject object : selectedScene.getObjects()){
            selectedScene.getShader().bind();
            selectedScene.getShader().updateUniforms(object.getTransform().getTransformation(), selectedScene.getTransform().getProjectedTransformation(),object.getMaterial());
            object.getMesh().draw();
        }
    }
    public static void calcPhysics(){
        if(selectedScene != null) {
            for (RenderObject object : selectedScene.getObjects()) {

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
}
