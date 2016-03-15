package com.harry9137.api.scenes;

import com.harry9137.api.main.Input;
import com.harry9137.api.render.*;
import com.harry9137.api.render.lighting.BaseLight;
import com.harry9137.api.render.lighting.DirectionalLight;
import com.harry9137.api.render.math.Vector3f;
import com.harry9137.api.render.shaders.OverlayShader;
import com.harry9137.api.render.shaders.PhongShader;
import com.harry9137.api.scenes.Objects.RenderObject;
import com.harry9137.api.scenes.Objects.TextObject;
import com.harry9137.api.util.ProgramRefrence;
import com.harry9137.api.util.ResourceLoader;

public class SceneTestLevel extends SceneBase {
    public SceneTestLevel(){
        super();
        this.sceneType = SceneType.THREE_DIMENSIONAL;
        this.setRegShader(PhongShader.getInstance());
        this.setOverlayShader(OverlayShader.getInstance());
        this.setCamera(new Camera());
        this.setTransform(new Transform());
        this.getTransform().setProjection(70f, Window.getWidth(), Window.getHeight(), 0.1f, 110f);
        this.getTransform().setCamera(this.getCamera());
        this.addObject(new RenderObject(ResourceLoader.loadMesh("FloorTests.obj"), new Material(null, new Vector3f(1.19f,2,2)), this.getTransform(), new Vector3f(0,0,0), new Vector3f(0,0,0), new Vector3f(0,0,0), false).setObjName("Floor"));
        this.addObject(new RenderObject(ResourceLoader.loadMesh("Chara.obj"), new Material(null, new Vector3f(1,0,2)), this.getTransform(), new Vector3f(0,1,0), new Vector3f(0,0,0), new Vector3f(0,0,0), true).setObjName("Chara"));
        this.getObject("Floor").getMesh().getVbo();
        this.addOverlay(new TextObject(ProgramRefrence.fonts.arialFont, Integer.toString(0), 0, 0).setObjName("ColorCounter"));
    }

    @Override
    public void update(){
        //Vector2f pointInCircle = MathHelper.calcPosInCircle(new Vector2f(this.getObject("Chara").getLocation().));
        float zPos = this.getObject("Chara").getLocation().GetZ();
        //this.getCamera().setPos();
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
            System.out.println(this.getObject("Floor").getMaterial().getColor().GetX() + " " + this.getObject("Floor").getMaterial().getColor().GetY() + " " + this.getObject("Floor").getMaterial().getColor().GetZ());
        }

        if(Input.getKey(Input.KEY_UP)){
            this.getObject("Chara").getLocation().SetX(this.getObject("Chara").getLocation().GetX() + 0.2f);
        }
        else if(Input.getKey(Input.KEY_DOWN)){
            this.getObject("Chara").getLocation().SetX(this.getObject("Chara").getLocation().GetX() - 0.2f);
        }
        if(Input.getKey(Input.KEY_LEFT)){
            this.getObject("Chara").getLocation().SetZ(this.getObject("Chara").getLocation().GetZ() + 0.2f);
        }
        else if(Input.getKey(Input.KEY_RIGHT)){
            this.getObject("Chara").getLocation().SetZ(this.getObject("Chara").getLocation().GetZ() - 0.2f);
        }

    }
    @Override
    public void specialInit(){
        super.specialInit();
        PhongShader.setAmbientLight(new Vector3f(1f,1f,1f));
        PhongShader.setDirectionalLight(new DirectionalLight(new BaseLight(new Vector3f(1,1,1), 0.8f), new Vector3f(1,1,1)));
    }
}
