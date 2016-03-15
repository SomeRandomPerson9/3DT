package com.harry9137.api.scenes;

import com.harry9137.api.main.Game;
import com.harry9137.api.main.Launch;
import com.harry9137.api.render.Material;
import com.harry9137.api.render.math.Matrix4f;
import com.harry9137.api.render.math.Vector3f;
import com.harry9137.api.scenes.Objects.GenericObject;
import com.harry9137.api.scenes.Objects.RenderObject;
import com.harry9137.api.scenes.Objects.TextObject;
import com.harry9137.api.util.ProgramRefrence;
import org.newdawn.slick.Color;
import com.bulletphysics.collision.*;

import java.util.HashMap;

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
            selectedScene.getRegShader().updateUniforms(new Matrix4f(),new Matrix4f(), null);
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
                selectedScene.getRegShader().bind();
                if(renderObject.isHeld()) {
                    Object[] temp = (renderObject.getTransform().getProjectedTransformationHeld(new Matrix4f().initTranslation(renderObject.getLocation().GetX(), renderObject.getLocation().GetY(), renderObject.getLocation().GetZ())));
                    selectedScene.getRegShader().updateUniforms(renderObject.getTransform().getTransformation(), (Matrix4f)temp[0], renderObject.getMaterial());
                    //renderObject.setLocation(((Matrix4f)temp[2]).get;
                }
                else{
                    selectedScene.getRegShader().updateUniforms(renderObject.getTransform().getTransformation(), renderObject.getTransform().getProjectedTransformation(new Matrix4f().initTranslation(renderObject.getLocation().GetX(), renderObject.getLocation().GetY(), renderObject.getLocation().GetZ())), renderObject.getMaterial());
                }
                renderObject.getMesh().draw();
            }
            for(GenericObject genericObject : selectedScene.getOverlayObjects()){
                if(genericObject instanceof TextObject){
                    TextObject textObj = (TextObject)genericObject;
                    try {
                        selectedScene.getOverlayShader().bind();
                        selectedScene.getOverlayShader().updateUniforms(null, null, new Material(null, new Vector3f(0,0,0)));
                        ProgramRefrence.fonts.arialFont.drawString(textObj.getX(), textObj.getY(), textObj.getString(), Color.black);
                    }catch(NullPointerException e){
                        if(ProgramRefrence.fonts.arialFont == null){ 
                            System.err.println("Global font is null");
                        }
                        //if(textObj.getFont() == null) {
                        //    System.err.println("Text Object " + textObj.getObjName() + ": Font is Null");
                        //}
                        if(textObj.getString() == null){
                            System.err.println("Text Object " + textObj.getObjName() + ": String is Null");
                        }
                    }
                }
            }
        }
        else if(selectedScene.sceneType == SceneType.TWO_DIMENSIONAL){
            if(selectedScene instanceof Scene2DVideo){

                ((Scene2DVideo) selectedScene).render();
            }
        }
    }
    public static void calcPhysics(){
        if(selectedScene != null) {
            for (RenderObject object : selectedScene.getObjects()) {
                if(object.isPhys()) {

                    object.getTransform().addTranslation(object.getLocation());

                    int tps = Game.getInstance().getTps();
                    //Gravity
                    if (object.getVelocity().GetY() >= ProgramRefrence.terminalVelocity) {
                        object.setAcceleration(new Vector3f((float) object.getAcceleration().GetX(), (float) (object.getAcceleration().GetY() - ProgramRefrence.gravity / tps), (float) object.getAcceleration().GetZ()));
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
                        if(object.getLocation().GetY() > 0f) {
                            object.getLocation().SetY((float) (object.getAcceleration().GetY() * Launch.TPS / 100) + object.getVelocity().GetY());
                        }
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
