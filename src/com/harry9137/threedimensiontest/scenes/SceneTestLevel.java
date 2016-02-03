package com.harry9137.threedimensiontest.scenes;

import com.harry9137.threedimensiontest.render.Camera;
import com.harry9137.threedimensiontest.render.Material;
import com.harry9137.threedimensiontest.render.PhongShader;
import com.harry9137.threedimensiontest.render.Transform;
import com.harry9137.threedimensiontest.render.lighting.BaseLight;
import com.harry9137.threedimensiontest.render.lighting.DirectionalLight;
import com.harry9137.threedimensiontest.render.math.Vector3f;
import com.harry9137.threedimensiontest.util.ResourceLoader;

public class SceneTestLevel extends SceneBase {
    public SceneTestLevel(){
        super();
        this.sceneType = SceneType.THREE_DIMENSIONAL;
        this.setShader(PhongShader.getInstance());
        this.setCamera(new Camera());
        this.setTransform(new Transform());
        this.addObject(new RenderObject(ResourceLoader.loadMesh("Floor.obj"), new Material(null, new Vector3f(0,0,0)), this.getTransform(), new Vector3f(0,0,0), new Vector3f(0,0,0), new Vector3f(0,0,0), true));
    }
    @Override
    public void specialInit(){
        super.specialInit();
        PhongShader.setAmbientLight(new Vector3f(1f,1f,1f));
        PhongShader.setDirectionalLight(new DirectionalLight(new BaseLight(new Vector3f(1,1,1), 0.8f), new Vector3f(1,1,1)));
    }
}
