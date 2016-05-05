package com.harry9137.api.scenes.levels;

import com.harry9137.api.main.Input;
import com.harry9137.api.physics.MathHelper;
import com.harry9137.api.render.Camera;
import com.harry9137.api.render.Transform;
import com.harry9137.api.render.Window;
import com.harry9137.api.render.lighting.BaseLight;
import com.harry9137.api.render.lighting.DirectionalLight;
import com.harry9137.api.render.math.Vector3f;
import com.harry9137.api.render.shaders.OverlayShader;
import com.harry9137.api.render.shaders.PhongShader;
import com.harry9137.api.scenes.Objects.CharacterObject;
import com.harry9137.api.scenes.Objects.GroundObject;
import com.harry9137.api.scenes.Objects.logic.TextObject;
import com.harry9137.api.scenes.SceneBase;
import com.harry9137.api.scenes.SceneType;
import com.harry9137.api.util.ObjBuilder;
import com.harry9137.api.util.ProgramRefrence;

public class SceneTitleScreen extends SceneBase {
    public SceneTitleScreen(){
        this.setUpPhysics();
        this.sceneType = SceneType.THREE_DIMENSIONAL;
        this.setRegShader(PhongShader.getInstance());
        this.setOverlayShader(OverlayShader.getInstance());
        this.setCamera(new Camera());
        this.getCamera().setPos(new Vector3f(10.959916f, 6.1402626f, 0.392349f));
        this.getCamera().setForward(new Vector3f(-0.8901258f, -0.45554522f, -0.012429923f));
        this.getCamera().setUp(new Vector3f(-0.4555008f, 0.89021266f, -0.0063607204f));
        this.setTransform(new Transform());
        this.getTransform().setProjection(70f, Window.getWidth(), Window.getHeight(), 0.1f, 110f);
        this.getTransform().setCamera(this.getCamera());

        this.addObject(ObjBuilder.build(this.getTransform(), "Desk.obj", "Desk"));

        this.addObject(ObjBuilder.build(this.getTransform(), "TitleRoom.obj", "TitleRoom"));

        this.addObject(new CharacterObject(this.getTransform()));
        com.bulletphysics.linearmath.Transform tr = this.getObject("Chara").getRigidBodyShape().getMotionState().getWorldTransform(new com.bulletphysics.linearmath.Transform());
        tr.transform(new javax.vecmath.Vector3f(0,14f,0));
        this.getObject("Chara").getRigidBodyShape().setWorldTransform(tr);

        this.addObject(new GroundObject(this.getTransform()));

        this.addOverlay(new TextObject(ProgramRefrence.fonts.arialFont, "Dang Son, where'd ya find this?", 0, 0));
    }

    @Override
    public void input(){
        this.getCamera().input();

        if(Input.getKeyDown(Input.KEY_H)){
            System.out.println(this.getCamera().getPos().toString());
            System.out.println(this.getCamera().getForward());
            System.out.println(this.getCamera().getUp());
        }

        com.bulletphysics.linearmath.Transform controlTransform = new com.bulletphysics.linearmath.Transform();

        if(Input.getKey(Input.KEY_UP) && this.getObject("Chara").getRigidBodyShape().getLinearVelocity(controlTransform.origin).x < 10f){
            this.getObject("Chara").getRigidBodyShape().getMotionState().getWorldTransform(controlTransform);
            javax.vecmath.Vector3f force = MathHelper.baked3fToVecMath(new Vector3f(-50f,0,0));
            this.getObject("Chara").getRigidBodyShape().activate(true);
            this.getObject("Chara").getRigidBodyShape().applyCentralForce(force);

        }
        else if(Input.getKey(Input.KEY_DOWN) && this.getObject("Chara").getRigidBodyShape().getLinearVelocity(controlTransform.origin).x > -10f){
            this.getObject("Chara").getRigidBodyShape().getMotionState().getWorldTransform(controlTransform);
            javax.vecmath.Vector3f force = MathHelper.baked3fToVecMath(new Vector3f(50f,0,0));
            this.getObject("Chara").getRigidBodyShape().activate(true);
            this.getObject("Chara").getRigidBodyShape().applyCentralForce(force);
        }
        if(Input.getKey(Input.KEY_LEFT) && this.getObject("Chara").getRigidBodyShape().getLinearVelocity(controlTransform.origin).z < 10f){
            this.getObject("Chara").getRigidBodyShape().getMotionState().getWorldTransform(controlTransform);
            javax.vecmath.Vector3f force = MathHelper.baked3fToVecMath(new Vector3f(0,0,-50f));
            this.getObject("Chara").getRigidBodyShape().activate(true);
            this.getObject("Chara").getRigidBodyShape().applyCentralForce(force);
        }
        else if(Input.getKey(Input.KEY_RIGHT) && this.getObject("Chara").getRigidBodyShape().getLinearVelocity(controlTransform.origin).z > -10f){
            this.getObject("Chara").getRigidBodyShape().getMotionState().getWorldTransform(controlTransform);
            javax.vecmath.Vector3f force = MathHelper.baked3fToVecMath(new Vector3f(0,0,50f));
            this.getObject("Chara").getRigidBodyShape().activate(true);
            this.getObject("Chara").getRigidBodyShape().applyCentralForce(force);
        }
        if(Input.getKey(Input.KEY_SPACE)) {
            this.getObject("Chara").getRigidBodyShape().getMotionState().getWorldTransform(controlTransform);
            javax.vecmath.Vector3f force = MathHelper.baked3fToVecMath(new Vector3f(0, 50f, 0));
            this.getObject("Chara").getRigidBodyShape().activate(true);

            this.getObject("Chara").getRigidBodyShape().applyCentralForce(force);
        }
        if(Input.getKeyDown(Input.KEY_RETURN)){

        }
    }

    @Override
    public void specialInit(){
        super.specialInit();
        PhongShader.setAmbientLight(new Vector3f(2f,2f,2f));
        PhongShader.setDirectionalLight(new DirectionalLight(new BaseLight(new Vector3f(1,1,1), 0.8f), new Vector3f(1,1,1)));
    }
}
