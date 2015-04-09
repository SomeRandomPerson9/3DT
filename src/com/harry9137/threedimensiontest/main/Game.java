package com.harry9137.threedimensiontest.main;

import com.harry9137.threedimensiontest.render.Transform;
import com.harry9137.threedimensiontest.scenes.SceneLoader;
import com.harry9137.threedimensiontest.scenes.SceneReg3D;
import com.harry9137.threedimensiontest.scenes.SceneReg3DTest;
import com.harry9137.threedimensiontest.util.RenderUtil;

public class Game {
    public Game(){
        SceneLoader.addScene(0, new SceneReg3D());
        SceneLoader.addScene(1, new SceneReg3DTest());
        SceneLoader.selectScene(0);
    }
    public void input(){
        SceneLoader.updateSceneInput();
        if(Input.getKeyDown(Input.KEY_0) && SceneLoader.getSelectedSceneNumber() == 0){
            System.out.println("Attempting to Translate to Scene 2");
            SceneLoader.selectScene(1);
        }
        else if(Input.getKeyDown(Input.KEY_0) && SceneLoader.getSelectedSceneNumber() == 1){
            System.out.println("Attempting to Translate to Scene 1");
            SceneLoader.selectScene(0);
        }
    }

    public void update(){
        SceneLoader.updateScene();
    }
    public void render(){
        RenderUtil.setClearColor(Transform.getCamera().getPos().Div(2048f).Abs());
        SceneLoader.renderScene();
    }
}
