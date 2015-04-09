package com.harry9137.threedimensiontest.scenes;

import com.harry9137.threedimensiontest.main.Game;
import com.harry9137.threedimensiontest.main.Launch;

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
        selectedScene.update();
        for(int i = 0; i < scenes.size(); i++){
            if(scenes.get(i).getBtsUpdateLvl() > 0){
                scenes.get(i).update();
            }
            if(selectedSceneNumber == i){
                selectedScene.update();
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
    public static void updateSceneInput(){
        selectedScene.input();
    }
    public static int getSelectedSceneNumber(){
        return selectedSceneNumber;
    }
}
