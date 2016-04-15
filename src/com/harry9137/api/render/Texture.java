package com.harry9137.api.render;

import static org.lwjgl.opengl.GL11.*;

public class Texture {
    private int id;
    public Texture(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void bind(){
        glBindTexture(GL_TEXTURE_2D, id);
    }
}