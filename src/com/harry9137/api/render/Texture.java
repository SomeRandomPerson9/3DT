package com.harry9137.api.render;

import static org.lwjgl.opengl.GL11.*;

public class Texture {
    private int id;
    private org.newdawn.slick.opengl.Texture texturel;
    public Texture(int id){
        this.id = id;
    }
    public Texture(org.newdawn.slick.opengl.Texture texturez){
        texturel = texturez;
    }

    public int getId() {
        return id;
    }

    public void bind(){
        try {
            texturel.bind();
        }
        catch (NullPointerException e){
            glBindTexture(GL_TEXTURE_2D, id);
        }
    }

    public org.newdawn.slick.opengl.Texture getTexturel() {
        return texturel;
    }

    public void setTexturel(org.newdawn.slick.opengl.Texture texturel) {
        this.texturel = texturel;
    }
}
