package com.harry9137.api.scenes.Objects;

import com.harry9137.api.render.Mesh;
import com.harry9137.api.render.Mesh2D;
import com.harry9137.api.scenes.Objects.logic.GenericObject;
import com.harry9137.api.util.ResourceLoader;

public class ChoiceMenuObject extends GenericObject {

    public Mesh2D mesh;
    public float xPos;
    public float yPos;

    public ChoiceMenuObject(){
        super();
        mesh = ResourceLoader.loadMesh2D("ChoiceMenu");
        xPos = 0;
        yPos = 0;
    }
}
