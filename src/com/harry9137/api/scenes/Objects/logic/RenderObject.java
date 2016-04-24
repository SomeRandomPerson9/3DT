package com.harry9137.api.scenes.Objects.logic;

import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.dynamics.RigidBody;
import com.harry9137.api.render.Material;
import com.harry9137.api.render.Mesh;
import com.harry9137.api.render.Transform;
import com.harry9137.api.render.math.Vector3f;

import java.util.ArrayList;
import java.util.HashMap;

public class RenderObject extends GenericObject  {

    String objName;

    boolean rigidBody;

    private boolean phys;

    private CollisionShape collisionShape;
    private RigidBody rigidBodyShape;

    private HashMap<String, Material> material;
    private HashMap<String, Mesh> mesh;
    private Transform transform;
    private Vector3f velocity;
    private Vector3f acceleration;
    private Vector3f location;
    private Vector3f camTransform;
    private boolean held;

    public RenderObject(Transform globalTransform){
        this.velocity = new Vector3f(0,0,0);
        this.acceleration = new Vector3f(0,0,0);
        this.location = new Vector3f(0,0,0);
        this.transform = globalTransform.copy().addTranslation(location);
        this.phys = false;
    }

    public RenderObject(HashMap<String, Mesh> mesh, HashMap<String, Material> material, Transform transform, Vector3f location, Vector3f velocity, Vector3f acceleration, boolean phys){
        this.mesh = mesh;
        this.material = material;
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.transform = transform.copy().addTranslation(location);
        this.location = location;
        this.phys = phys;
    }

    public RenderObject(HashMap<String, Mesh> mesh1, RigidBody body){
        this.mesh = mesh1;
        this.rigidBodyShape = body;
        this.setRigidBody(true);
        //this.collisionShape = body.getCollisionShape();
    }

    public void move(Vector3f dir, float amt)
    {
        location = location.Add(dir.Mul(amt));
    }

    public HashMap<String, Material> getMaterials() {
        return material;
    }

    public void setMaterials(HashMap<String, Material> material) {
        this.material = material;
    }
    public void addMaterial(String key, Material value){
        material.put(key, value);
    }

    public Material getMaterial(String key){
        return material.get(key);
    }

    public HashMap<String, Mesh> getMeshs() {
        return mesh;
    }

    public void setMeshs(HashMap<String, Mesh> mesh) {
        this.mesh = mesh;
    }

    public void addMesh(String key, Mesh value){
        mesh.put(key, value);
    }

    public Mesh getMesh(String key){
        return mesh.get(key);
    }

    public Transform getTransform() {
        return transform;
    }

    public void setTransform(Transform transform) {
        this.transform = transform;
    }

    public Vector3f getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Vector3f acceleration) {
        this.acceleration = acceleration;
    }

    public Vector3f getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector3f velocity) {
        this.velocity = velocity;
    }

    public Vector3f getLocation() {
        return location;
    }

    public void setLocation(Vector3f location) {
        this.location = location;
    }

    public Vector3f getCamTransform() {
        return camTransform;
    }

    public void setCamTransform(Vector3f camTransform) {
        this.camTransform = camTransform;
    }

    public boolean isPhys() {
        return phys;
    }

    public void setPhys(boolean noPhys) {
        this.phys = noPhys;
    }

    public boolean isHeld() {
        return held;
    }

    public void setHeld(boolean held) {
        this.held = held;
    }

    public String getObjName() {
        return objName;
    }

    public RenderObject setObjName(String objName) {
        this.objName = objName;
        return this;
    }

    public boolean isRigidBody() {
        return rigidBody;
    }

    public void setRigidBody(boolean rigidBody) {
        this.rigidBody = rigidBody;
    }

    public RigidBody getRigidBodyShape() {
        return rigidBodyShape;
    }

    public void setRigidBodyShape(RigidBody rigidBodyShape) {
        this.rigidBodyShape = rigidBodyShape;
    }
}
