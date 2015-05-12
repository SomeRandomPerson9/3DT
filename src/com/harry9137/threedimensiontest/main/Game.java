package com.harry9137.threedimensiontest.main;

import com.harry9137.threedimensiontest.render.Transform;
import com.harry9137.threedimensiontest.scenes.Scene2DVideo;
import com.harry9137.threedimensiontest.scenes.SceneLoader;
import com.harry9137.threedimensiontest.scenes.SceneReg3D;
import com.harry9137.threedimensiontest.scenes.SceneReg3DTest;
import com.harry9137.threedimensiontest.util.RenderUtil;
import com.harry9137.threedimensiontest.util.Video;

import java.io.File;
import java.io.IOException;

public class Game {
    int tps = 0;
    private  static Game instance = null;
    protected Game() {

    }
    public static Game getInstance(){
        if(instance == null){
            instance = new Game();
            SceneLoader.addScene(0, new SceneReg3D());
            SceneLoader.addScene(1, new SceneReg3DTest());
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
    }
    public void setTps(int num){
        tps = num;
    }
    public int getTps(){
        return tps;
    }
    public void update(){
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
}
