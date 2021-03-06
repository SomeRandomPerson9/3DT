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

public class SceneReg3DTest extends SceneBase {
    boolean temp3;
    public SceneReg3DTest(){
        super();
        this.setRegShader(PhongShader.getInstance());
        this.setCamera(new Camera());
        Transform tempTransform = new Transform();
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
        //this.addObject(new RenderObject(ResourceLoader.loadMesh("Cube.obj"), new Material(null, new Vector3f(1,0,0)), this.getTransform(), new Vector3f(-0.5f,0,5), new Vector3f(0,0,0), new Vector3f(0,0,0), true));
        //this.addObject(new RenderObject(ResourceLoader.loadMesh("Sphere.obj"), new Material(null, new Vector3f(0,1,0)), this.getTransform(), new Vector3f(5f,0,2), new Vector3f(0,0,0), new Vector3f(0,0,0), true));
        //this.addObject(new RenderObject(ResourceLoader.loadMesh("soccer ball.obj"), new Material(null, new Vector3f(0,1,0)), this.getTransform(), new Vector3f(10f,0,2), new Vector3f(0,0,0), new Vector3f(0,0,0), true));
        this.sceneType = SceneType.THREE_DIMENSIONAL;
    }
    float temp = 0.0F;
    @Override
    public void update(){
        temp += Time.getDelta();

        float sinTemp = (float)Math.sin(temp);
        if(temp3) {
            this.getObjects().get(0).getTransform().setRotation(22.5f, sinTemp * 180, 0);
            this.getObjects().get(0).getTransform().setTranslation(0.0f,5.0f,0.0f);
        }
        this.getObjects().get(0).getTransform().setTranslation(0f,1f,0f);
        //  transform.setScale(0.25F,0.25F,0.25F);


    }
    @Override
    public void input(){
        this.getCamera().input();

        if(Input.getKeyDown(Input.KEY_NUMPAD9)){
            this.getObjects().get(1).getLocation().SetZ(this.getObjects().get(0).getLocation().GetX() + 0.1f);
            System.out.println("9");
        }
        if(Input.getKeyDown(Input.KEY_NUMPAD7)){
            this.getObjects().get(1).getLocation().SetZ(this.getObjects().get(0).getLocation().GetX() - 0.1f);
            System.out.println("7");
        }

        if(Input.getKeyDown(Input.KEY_0) && SceneLoader.getSelectedSceneNumber() == 0){
            System.out.println("Attempting to Translate to Scene 2");
            SceneLoader.selectScene(1);
        }
        else if(Input.getKeyDown(Input.KEY_0) && SceneLoader.getSelectedSceneNumber() == 1){
            System.out.println("Attempting to Translate to Scene 1");
            SceneLoader.selectScene(0);
        }
        if(Input.getKeyDown(Input.KEY_NUMLOCK)){
            temp3 = !temp3;
        }
        if(Input.getKeyDown(Input.KEY_E)){
           // this.getObjects().get(1).getTransform().m;
            this.getObjects().get(1).setHeld(!(this.getObjects().get(1).isHeld()));
        }
    }
    @Override
    public void specialInit(){
        super.specialInit();
        PhongShader.setAmbientLight(new Vector3f(1f,1f,1f));
        PhongShader.setDirectionalLight(new DirectionalLight(new BaseLight(new Vector3f(1,1,1), 0.8f), new Vector3f(1,1,1)));
    }
}
