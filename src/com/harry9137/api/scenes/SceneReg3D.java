package com.harry9137.api.scenes;

import com.harry9137.api.main.Input;
import com.harry9137.api.render.math.Vector2f;
import com.harry9137.api.render.math.Vector3f;
import com.harry9137.api.render.*;
import com.harry9137.api.render.lighting.BaseLight;
import com.harry9137.api.render.lighting.DirectionalLight;
import com.harry9137.api.render.shaders.PhongShader;
import com.harry9137.api.scenes.Objects.logic.RenderObject;
import com.harry9137.api.util.ResourceLoader;
import com.harry9137.api.util.Time;

public class SceneReg3D extends SceneBase {
    public SceneReg3D(){
        super();
        this.setRegShader(PhongShader.getInstance());
        this.setCamera(new Camera());
        Transform tempTransform = new Transform();
        //TODO: DELETE MATRIX TO GET PERFORMANCE
        tempTransform.setProjection(70f, Window.getWidth(), Window.getHeight(), 0.1f, 110f);
        tempTransform.setCamera(this.getCamera());
        this.setTransform(tempTransform);
        Mesh mesh = new Mesh();
        Vertex[] vertices = new Vertex[] {new Vertex(new Vector3f(-1,-1,0), new Vector2f(0,0)),
                new Vertex(new Vector3f(0,1,0), new Vector2f(0.5f,0)),
                new Vertex(new Vector3f(1,-1,0), new Vector2f(1.0f,0)),
                new Vertex(new Vector3f(0,-1,1), new Vector2f(0.5f,1.0f))};

        int[] indices = new int[] {3,1,0,
                2,1,3,
                0,1,2,
                0,2,3};
        mesh.addVertices(vertices, indices);
        //this.addObject(new RenderObject(mesh, new Material(ResourceLoader.loadTexture("test.png"), new Vector3f(1,1,1)), this.getTransform(), new Vector3f(0,0,5), new Vector3f(0,0,0), new Vector3f(0,0,0), true));
        this.setBtsUpdateLvl(2);
        this.sceneType = SceneType.THREE_DIMENSIONAL;
    }
    @Override
    public void specialInit(){
        super.specialInit();
        PhongShader.setAmbientLight(new Vector3f(1f,1f,1f));
        PhongShader.setDirectionalLight(new DirectionalLight(new BaseLight(new Vector3f(1,1,1), 0.8f), new Vector3f(1,1,1)));
    }
    float temp = 0.0F;
    @Override
    public void update(){
        temp += Time.getDelta();

        float sinTemp = (float)Math.sin(temp);

        this.getTransform().setRotation(0,sinTemp * 180,0);
        //  transform.setScale(0.25F,0.25F,0.25F);

    }
    @Override
    public void input(){
        this.getCamera().input();

        if(Input.getKeyDown(Input.KEY_0) && SceneLoader.getSelectedSceneNumber() == 0){
            System.out.println("Attempting to Translate to Scene 2");
            SceneLoader.selectScene(1);
        }
        else if(Input.getKeyDown(Input.KEY_0) && SceneLoader.getSelectedSceneNumber() == 1){
            System.out.println("Attempting to Translate to Scene 1");
            SceneLoader.selectScene(0);
        }
    }
}
