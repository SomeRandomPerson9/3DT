package com.harry9137.api.scenes;

import com.bulletphysics.collision.broadphase.BroadphaseInterface;
import com.bulletphysics.collision.broadphase.DbvtBroadphase;
import com.bulletphysics.collision.dispatch.CollisionConfiguration;
import com.bulletphysics.collision.dispatch.CollisionDispatcher;
import com.bulletphysics.collision.dispatch.DefaultCollisionConfiguration;
import com.bulletphysics.dynamics.DiscreteDynamicsWorld;
import com.bulletphysics.dynamics.DynamicsWorld;
import com.bulletphysics.dynamics.constraintsolver.ConstraintSolver;
import com.bulletphysics.dynamics.constraintsolver.SequentialImpulseConstraintSolver;
import com.harry9137.api.physics.EulerCamera;
import com.harry9137.api.render.Camera;
import com.harry9137.api.render.shaders.Shader;
import com.harry9137.api.render.Transform;
import com.harry9137.api.render.Window;
import com.harry9137.api.scenes.Objects.logic.GenericObject;
import com.harry9137.api.scenes.Objects.logic.RenderObject;

import javax.vecmath.Vector3f;
import java.util.ArrayList;

public class SceneBase {
    String objName;

    private ArrayList<RenderObject> objects = new ArrayList<RenderObject>();
    private ArrayList<GenericObject> overlayObjects = new ArrayList<>();
    private Shader regShader;
    private Shader overlayShader;
    private Camera camera;
    private Transform transform;
    private int btsUpdateLvl = 0;
    protected SceneType sceneType;

    // TEST
    private EulerCamera eulerCamera = new EulerCamera.Builder()
            .setFieldOfView(300)
            .setNearClippingPane(0.3f)
            .setFarClippingPane(500)
            .setPosition(0, 25, 15)
            .build();

    private DynamicsWorld dynamicsWorld;

    public void setUpPhysics(){
        BroadphaseInterface broadphase = new DbvtBroadphase();
        CollisionConfiguration collisionConfiguration = new DefaultCollisionConfiguration();
        CollisionDispatcher dispatcher = new CollisionDispatcher(collisionConfiguration);
        ConstraintSolver solver = new SequentialImpulseConstraintSolver();
        dynamicsWorld = new DiscreteDynamicsWorld(dispatcher, broadphase, solver, collisionConfiguration);
        dynamicsWorld.setGravity(new Vector3f(0, -15 /* m/s2 */, 0));
    }

    public void addObject(RenderObject object){
        objects.add(object);
        if(object.isRigidBody()){
            System.out.println("OYYY M888!!!");
            dynamicsWorld.addRigidBody(object.getRigidBodyShape());
        }
    }

    public void update(){

    }
    public void input(){

    }
    public void specialInit(){

    }
    public void cleanup(){

    }

    public void restartMatrix(){
        Transform.setProjection(70f, Window.getWidth(), Window.getHeight(), 0.1f, 110f);
    }

    public ArrayList<RenderObject> getObjects() {
        return objects;
    }

    public void setObjects(ArrayList<RenderObject> objects) {
        this.objects = objects;
    }

    public Shader getRegShader() {
        return regShader;
    }

    public void setRegShader(Shader regShader) {
        this.regShader = regShader;
    }

    public Shader getOverlayShader(){
        return overlayShader;
    }

    public void setOverlayShader(Shader overlayShader1){
        this.overlayShader = overlayShader1;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public Transform getTransform() {
        return transform;
    }

    public void setTransform(Transform transform) {
        this.transform = transform;
    }

    public RenderObject getObject(int num){
        return objects.get(num);
    }

    public RenderObject getObject(String name){
        RenderObject retVal = null;
        for(RenderObject loopObj : objects){
            if(loopObj.getObjName().equals(name)){
                retVal = loopObj;
                break;
            }
        }
        return retVal;
    }

    public void addOverlay(GenericObject object){
        overlayObjects.add(object);
    }

    public GenericObject getOverlay(String name){
        GenericObject retVal = null;
        for(GenericObject loopObj : overlayObjects){
            if(loopObj.getObjName().equals(name)){
                retVal = loopObj;
                break;
            }
        }
        return retVal;
    }

    public int getBtsUpdateLvl() {
        return btsUpdateLvl;
    }

    public void setBtsUpdateLvl(int btsUpdateLvl) {
        this.btsUpdateLvl = btsUpdateLvl;
    }

    public SceneType getSceneType() {
        return sceneType;
    }

    public void setSceneType(SceneType sceneType) {
        this.sceneType = sceneType;
    }

    public ArrayList<GenericObject> getOverlayObjects() {
        return overlayObjects;
    }

    public void setOverlayObjects(ArrayList<GenericObject> overlayObjects) {
        this.overlayObjects = overlayObjects;
    }

    public String getObjName() {
        return objName;
    }

    public SceneBase setObjName(String objName) {
        this.objName = objName;
        return this;
    }

    public DynamicsWorld getDynamicsWorld() {
        return dynamicsWorld;
    }

    public void setDynamicsWorld(DynamicsWorld dynamicsWorld) {
        this.dynamicsWorld = dynamicsWorld;
    }
}
