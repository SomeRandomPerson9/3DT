package com.harry9137.threedimensiontest.scenes.Objects;

import com.harry9137.threedimensiontest.render.Material;
import com.harry9137.threedimensiontest.render.Mesh;
import com.harry9137.threedimensiontest.render.Transform;
import com.harry9137.threedimensiontest.render.math.Vector3f;

public class CounterObject extends RenderObject {
    private float currentValue;
    private Mesh finalMesh;

    private enum numberMesh{

        things;

        Mesh zero = new Mesh();
        Mesh one = new Mesh();
        Mesh two = new Mesh();
        Mesh three = new Mesh();
        Mesh four = new Mesh();
        Mesh five = new Mesh();
        Mesh six = new Mesh();
        Mesh seven = new Mesh();
        Mesh eight = new Mesh();
        Mesh nine = new Mesh();
    }

    public CounterObject(Material material, Transform transform, Vector3f location) {
        super(null, material, transform, location, new Vector3f(0,0,0), new Vector3f(0,0,0), false);
        finalMesh =
    }

    public float getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(float currentValue) {
        this.currentValue = currentValue;
    }

    public Mesh stitchMesh(float value){

    }
}
