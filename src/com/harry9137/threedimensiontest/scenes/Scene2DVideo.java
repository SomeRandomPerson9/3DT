package com.harry9137.threedimensiontest.scenes;

import com.harry9137.threedimensiontest.render.Material;
//import com.harry9137.threedimensiontest.render.Texture;
import org.newdawn.slick.opengl.Texture;
import com.harry9137.threedimensiontest.render.math.Vector3f;
import com.harry9137.threedimensiontest.util.Video;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;

public class Scene2DVideo extends SceneBase {
    private Video videoa;

    float[] vertices;

    byte[] indices;

    FloatBuffer verticesBuffer;

    ByteBuffer indicesBuffer;

    int indicesCount;

    private int vaoId = 0;

    private int vboId = 0;

    private int vboiId = 0;

    int timer;

    public Scene2DVideo(){
        this.sceneType = SceneType.TWO_DIMENSIONAL;
    }

    public Video getVideo() {
        return videoa;
    }

    public SceneBase setVideo(Video video) {
        this.videoa = video;
        return this;
    }
    public void render(){

        Texture temp = null;
        try {
            temp = TextureLoader.getTexture("png", new FileInputStream(videoa.getVid()[timer]));
        }
        catch (IOException e){

        }
        glBegin(GL_TRIANGLES);

        glBindTexture(GL_TEXTURE_2D, temp.getTextureID());
        glTexCoord2f(1, 0);
        glVertex2i(960,540);
        glTexCoord2f(0, 0);
        glVertex2i(-960, 540);
        glTexCoord2f(0, 1);
        glVertex2i(960, -540);

        glTexCoord2f(0, 1);
        glVertex2i(-960, -540);
        glTexCoord2f(1, 1);
        glVertex2i(-960, 540);
        glTexCoord2f(1, 0);
        glVertex2i(960, -540);

        glEnd();
        temp.release();
    }
    @Override
    public void update(){
        if(videoa.getVid().length > timer) {
            timer++;
        }
        else{
            timer = 0;
        }
    }
    @Override
    public void cleanup(){
        GL20.glDisableVertexAttribArray(0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        GL15.glDeleteBuffers(vboId);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
        GL15.glDeleteBuffers(vboiId);
        GL30.glBindVertexArray(0);
        GL30.glDeleteVertexArrays(vaoId);
    }
    @Override
    public void specialInit(){
        vertices = new float[]{-(10.5f*16)/9, -10.5f, 0,
                -(10.5f*16)/9,  10.5f, 0,
                (10.5f*16)/9,  10.5f, 0,
                (10.5f*16)/9, -10.5f, 0};
        indices = new byte[]{0,1,2,
                0,2,3};
        verticesBuffer = BufferUtils.createFloatBuffer(vertices.length);
        verticesBuffer.put(vertices);
        verticesBuffer.flip();
        indicesCount = indices.length;
        indicesBuffer = BufferUtils.createByteBuffer(indicesCount);
        indicesBuffer.put(indices);
        indicesBuffer.flip();
        vaoId = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(vaoId);
        vboId = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboId);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, verticesBuffer, GL15.GL_STATIC_DRAW);
        GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
        GL30.glBindVertexArray(0);
        vboiId = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboiId);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL15.GL_STATIC_DRAW);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
    }
}
