package com.harry9137.threedimensiontest.scenes;

import com.harry9137.threedimensiontest.render.Material;
import com.harry9137.threedimensiontest.render.Mesh;
import com.harry9137.threedimensiontest.render.Transform;
import com.harry9137.threedimensiontest.render.math.Vector3f;

public class RenderObject {
    private Material material;
    private Mesh mesh;
    private Transform transform;
    private Vector3f velocity;
    private Vector3f acceleration;
    private Vector3f location;

    public RenderObject(Mesh mesh, Material material, Transform transform, Vector3f location, Vector3f velocity, Vector3f acceleration){
        this.mesh = mesh;
        this.material = material;
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.transform = transform.addTranslation(location);
        this.location = location;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Mesh getMesh() {
        return mesh;
    }

    public void setMesh(Mesh mesh) {
        this.mesh = mesh;
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
}