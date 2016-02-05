package com.harry9137.threedimensiontest.scenes.Objects;

import com.harry9137.threedimensiontest.render.Material;
import com.harry9137.threedimensiontest.render.Mesh;
import com.harry9137.threedimensiontest.render.Transform;
import com.harry9137.threedimensiontest.render.math.Vector3f;

import java.util.ArrayList;

public class CounterObject extends RenderObject {
    private float currentValue;
    private Mesh finalMesh;

    private enum defaultMeshes{

        numbers;

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
        Mesh decimal = new Mesh();
    }

    public CounterObject(Material material, Transform transform, Vector3f location) {
        super(null, material, transform, location, new Vector3f(0,0,0), new Vector3f(0,0,0), false);
        finalMesh = stitchMesh(0F);
    }

    public float getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(float currentValue) {
        this.currentValue = currentValue;
        stitchMesh(currentValue);
    }

    public Mesh stitchMesh(float value){
        String valString = Float.toString(value);
        ArrayList<Mesh> meshs = new ArrayList<Mesh>(valString.length());
        for(int i = 0; i < valString.length(); i++){
            char valChar = valString.charAt(i);
            switch(valChar){
                case '0': meshs.set(i, defaultMeshes.numbers.zero);
                case '1': meshs.set(i, defaultMeshes.numbers.one);
                case '2': meshs.set(i, defaultMeshes.numbers.two);
                case '3': meshs.set(i, defaultMeshes.numbers.three);
                case '4': meshs.set(i, defaultMeshes.numbers.four);
                case '5': meshs.set(i, defaultMeshes.numbers.five);
                case '6': meshs.set(i, defaultMeshes.numbers.six);
                case '7': meshs.set(i, defaultMeshes.numbers.seven);
                case '8': meshs.set(i, defaultMeshes.numbers.eight);
                case '9': meshs.set(i, defaultMeshes.numbers.nine);
                case '.': meshs.set(i, defaultMeshes.numbers.decimal);
            }
        }
        for(Mesh tempMesh : meshs){

        }
    }
}
