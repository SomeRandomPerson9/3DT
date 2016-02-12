package com.harry9137.threedimensiontest.scenes;

import com.harry9137.threedimensiontest.main.Input;
import com.harry9137.threedimensiontest.render.*;
import com.harry9137.threedimensiontest.render.lighting.BaseLight;
import com.harry9137.threedimensiontest.render.lighting.DirectionalLight;
import com.harry9137.threedimensiontest.render.math.Vector3f;
import com.harry9137.threedimensiontest.scenes.Objects.CounterObject;
import com.harry9137.threedimensiontest.scenes.Objects.RenderObject;
import com.harry9137.threedimensiontest.util.ResourceLoader;

public class SceneTestLevel extends SceneBase {
    public SceneTestLevel(){
        super();
        this.sceneType = SceneType.THREE_DIMENSIONAL;
        this.setShader(PhongShader.getInstance());
        this.setCamera(new Camera());
        this.setTransform(new Transform());
        this.getTransform().setProjection(70f, Window.getWidth(), Window.getHeight(), 0.1f, 110f);
        this.getTransform().setCamera(this.getCamera());
        this.addObject(new RenderObject(ResourceLoader.loadMesh("Floor.obj"), new Material(null, new Vector3f(2,2,2)), this.getTransform(), new Vector3f(0,0,0), new Vector3f(0,0,0), new Vector3f(0,0,0), true).setObjName("Floor"));
        this.addOverlay(new CounterObject(0, 10, 10).setObjName("ColorCounter"));
    }

    @Override
    public void input(){
        this.getCamera().input();

        boolean didntExecute = true;
        if(Input.getKeyDown(Input.KEY_O)){
            this.getObject("Floor").getMaterial().getColor().SetX(this.getObject("Floor").getMaterial().getColor().GetX() + 0.1f);
        }
        else if(Input.getKeyDown(Input.KEY_L)){
            this.getObject("Floor").getMaterial().getColor().SetX(this.getObject("Floor").getMaterial().getColor().GetX() - 0.1f);
        }
        else if(Input.getKeyDown(Input.KEY_I)){
            this.getObject("Floor").getMaterial().getColor().SetY(this.getObject("Floor").getMaterial().getColor().GetY() + 0.1f);
        }
        else if(Input.getKeyDown(Input.KEY_K)){
            this.getObject("Floor").getMaterial().getColor().SetY(this.getObject("Floor").getMaterial().getColor().GetY() - 0.1f);
        }
        else if(Input.getKeyDown(Input.KEY_U)){
            this.getObject("Floor").getMaterial().getColor().SetZ(this.getObject("Floor").getMaterial().getColor().GetZ() + 0.1f);
        }
        else if(Input.getKeyDown(Input.KEY_J)){
            this.getObject("Floor").getMaterial().getColor().SetZ(this.getObject("Floor").getMaterial().getColor().GetZ() - 0.1f);
        }
        else{
            didntExecute = false;
        }

        if(didntExecute){
            ((CounterObject)this.getOverlay("ColorCounter")).setString(this.getObject("Floor").getMaterial().getColor().GetX() + " " + this.getObject("Floor").getMaterial().getColor().GetY() + " " + this.getObject("Floor").getMaterial().getColor().GetZ());
        }

    }
    @Override
    public void specialInit(){
        super.specialInit();
        PhongShader.setAmbientLight(new Vector3f(1f,1f,1f));
        PhongShader.setDirectionalLight(new DirectionalLight(new BaseLight(new Vector3f(1,1,1), 0.8f), new Vector3f(1,1,1)));
    }
}
