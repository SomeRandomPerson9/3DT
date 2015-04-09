package com.harry9137.threedimensiontest.scenes;

import com.harry9137.threedimensiontest.render.Material;
import com.harry9137.threedimensiontest.render.Mesh;
import com.harry9137.threedimensiontest.render.Transform;
import com.harry9137.threedimensiontest.render.math.Vector3f;

public class RenderObject {
    private Material material;
    private Mesh mesh;
    private Transform transform;

    public RenderObject(Mesh mesh, Material material, Transform transform, Vector3f location){
        this.mesh = mesh;
        this.material = material;
        this.transform = transform.addTranslation(location);
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
}
