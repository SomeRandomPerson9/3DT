package com.harry9137.api.scenes;

import com.harry9137.api.main.Input;
import com.harry9137.api.render.*;
import com.harry9137.api.render.lighting.BaseLight;
import com.harry9137.api.render.lighting.DirectionalLight;
import com.harry9137.api.render.math.Vector3f;
import com.harry9137.api.render.shaders.OverlayShader;
import com.harry9137.api.render.shaders.PhongShader;
import com.harry9137.api.scenes.Objects.CharacterObject;
import com.harry9137.api.scenes.Objects.GroundObject;
import com.harry9137.api.scenes.Objects.logic.RenderObject;
import com.harry9137.api.scenes.Objects.logic.RigidBodyBuilder;
import com.harry9137.api.scenes.Objects.logic.TextObject;
import com.harry9137.api.util.ProgramRefrence;
import com.harry9137.api.util.ResourceLoader;

public class SceneTestLevel extends SceneBase {
    public SceneTestLevel(){
        super();
        this.setUpPhysics();
        this.sceneType = SceneType.THREE_DIMENSIONAL;
        this.setRegShader(PhongShader.getInstance());
        this.setOverlayShader(OverlayShader.getInstance());
        this.setCamera(new Camera());
        this.getCamera().setPos(new Vector3f(0, 10f, -15f));
        this.setTransform(new Transform());
        this.getTransform().setProjection(70f, Window.getWidth(), Window.getHeight(), 0.1f, 110f);
        this.getTransform().setCamera(this.getCamera());
        this.addObject(new GroundObject(this.getTransform()));
        this.addObject(new CharacterObject(this.getTransform()));
        this.getObject("Floor").getMesh().getVbo();
        this.addOverlay(new TextObject(ProgramRefrence.fonts.arialFont, Integer.toString(0), 0, 0).setObjName("ColorCounter"));
    }

    @Override
    public void update(){
        //Vector2f pointInCircle = MathHelper.calcPosInCircle(new Vector2f(this.getObject("Chara").getLocation().));
        //float zPos = this.getObject("Chara").getLocation().GetZ();
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

        com.bulletphysics.linearmath.Transform controlTransform = new com.bulletphysics.linearmath.Transform();

        if(Input.getKey(Input.KEY_UP) && this.getObject("Chara").getRigidBodyShape().getLinearVelocity(controlTransform.origin).x < 10f){
            this.getObject("Chara").getRigidBodyShape().getMotionState().getWorldTransform(controlTransform);
            javax.vecmath.Vector3f force = new javax.vecmath.Vector3f(50f, 0, 0);
            this.getObject("Chara").getRigidBodyShape().activate(true);
            this.getObject("Chara").getRigidBodyShape().applyCentralForce(force);

        }
        else if(Input.getKey(Input.KEY_DOWN) && this.getObject("Chara").getRigidBodyShape().getLinearVelocity(controlTransform.origin).x > -10f){
            this.getObject("Chara").getRigidBodyShape().getMotionState().getWorldTransform(controlTransform);
            javax.vecmath.Vector3f force = new javax.vecmath.Vector3f(-50f, 0, 0);
            this.getObject("Chara").getRigidBodyShape().activate(true);
            this.getObject("Chara").getRigidBodyShape().applyCentralForce(force);
        }
        if(Input.getKey(Input.KEY_LEFT) && this.getObject("Chara").getRigidBodyShape().getLinearVelocity(controlTransform.origin).z < 10f){
            this.getObject("Chara").getRigidBodyShape().getMotionState().getWorldTransform(controlTransform);
            javax.vecmath.Vector3f force = new javax.vecmath.Vector3f(0, 0, 50f);
            this.getObject("Chara").getRigidBodyShape().activate(true);
            this.getObject("Chara").getRigidBodyShape().applyCentralForce(force);
        }
        else if(Input.getKey(Input.KEY_RIGHT) && this.getObject("Chara").getRigidBodyShape().getLinearVelocity(controlTransform.origin).z > -10f){
            this.getObject("Chara").getRigidBodyShape().getMotionState().getWorldTransform(controlTransform);
            javax.vecmath.Vector3f force = new javax.vecmath.Vector3f(0f, 0, -50f);
            this.getObject("Chara").getRigidBodyShape().activate(true);
            this.getObject("Chara").getRigidBodyShape().applyCentralForce(force);
        }
        if(Input.get)

    }
    @Override
    public void specialInit(){
        super.specialInit();
        PhongShader.setAmbientLight(new Vector3f(1f,1f,1f));
        PhongShader.setDirectionalLight(new DirectionalLight(new BaseLight(new Vector3f(1,1,1), 0.8f), new Vector3f(1,1,1)));
    }
}
