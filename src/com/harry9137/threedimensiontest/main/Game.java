package com.harry9137.threedimensiontest.main;

import com.harry9137.threedimensiontest.render.Transform;
import com.harry9137.threedimensiontest.render.Window;
import com.harry9137.threedimensiontest.scenes.*;
import com.harry9137.threedimensiontest.util.Console;
import com.harry9137.threedimensiontest.util.RenderUtil;
import com.harry9137.threedimensiontest.util.Video;
import org.lwjgl.opengl.DisplayMode;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class Game {
    int tps = 0;
    private  static Game instance = null;
    public static Console console;
    protected Game() {

    }
    public static Game getInstance(){
        if(instance == null){
            instance = new Game();
            SceneLoader.addScene(0, new SceneReg3D());
            SceneLoader.addScene(1, new SceneReg3DTest());
            SceneLoader.addScene(3, new SceneTestLevel());
            console = new Console();
            console.addText("Scenes Added to Loader!");
            try {
                SceneLoader.addScene(2, new Scene2DVideo().setVideo(new Video(new File("F:\\Videos\\Final Exports\\Assasins Creed Unity Demo"))));
            }
            catch (IOException e){

            }
            SceneLoader.selectScene(0);
        }
        return instance;
    }
    public void input(){
        SceneLoader.updateSceneInput();
    /* if(Input.getKeyDown(Input.KEY_0) && SceneLoader.getSelectedSceneNumber() == 0){
            System.out.println("Attempting to Translate to Scene 2");
            SceneLoader.selectScene(1);
        }
        else if(Input.getKeyDown(Input.KEY_0) && SceneLoader.getSelectedSceneNumber() == 1){
            System.out.println("Attempting to Translate to Scene 1");
            SceneLoader.selectScene(0);
        }
    */
        if(Input.getKeyDown(Input.KEY_5)){
            SceneLoader.selectScene(2);
        }
        if(Input.getKeyDown(Input.KEY_6)){
            SceneLoader.selectScene(1);
        }
        if(Input.getKeyDown(Input.KEY_0)){
            SceneLoader.selectScene(3);
        }
    }
    public void setTps(int num){
        tps = num;
    }
    public int getTps(){
        return tps;
    }
    public void update(){
        if(Window.checkResize()){
            console.addText("Change Resolution to " + Window.getWidth() + "," + Window.getHeight());
            console.addText("Restarting Matrices");
            for(SceneBase sceneBase : SceneLoader.getScenes().values()) {
                if(sceneBase != null && sceneBase.getCamera() != null && sceneBase.getCamera().centerPosition != null) {
                    sceneBase.getCamera().centerPosition.SetX(Window.getWidth() / 2);
                    sceneBase.getCamera().centerPosition.SetY(Window.getHeight() / 2);
                }
            }
            for (int i = 0; i < SceneLoader.getScenes().size(); i++) {
                SceneLoader.getScenes().get(i).restartMatrix();
            }
        }
        SceneLoader.updateScene();
        //SceneLoader.calcPhysics();
        tps++;
    }
    public void render(){
        if(SceneLoader.getSelectedScene() != null) {
            RenderUtil.setClearColor(null);
            SceneLoader.renderScene();
        }
    }
    public void start(){

    }
}
