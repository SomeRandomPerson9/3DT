package com.harry9137.api.util;

import com.harry9137.api.main.Launch;
import com.harry9137.api.render.math.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;

public class RenderUtil {
    public static void clearScreen(){
        //TODO: Stencil Buffer
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    public static void setTextures(boolean enabled){
        if(enabled){
            glEnable(GL_TEXTURE_2D);

        }
        else{
            glDisable(GL_TEXTURE_2D);
        }
    }

    public static void initGraphics(){
        glClearColor(0.9f, 0.9f, 0.9f, 0.9f);

        glFrontFace(GL_CW);
        glCullFace(GL_BACK);
        glEnable(GL_CULL_FACE);
        glEnable(GL_DEPTH_TEST);

        //TODO: Depth clamp for later

        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glEnable(GL_FRAMEBUFFER_SRGB);

        glShadeModel(GL_SMOOTH);
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_LIGHTING);
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glEnable(GL_CULL_FACE);
        glCullFace(GL_BACK);
        glEnable(GL_COLOR_MATERIAL);
        glColorMaterial(GL_FRONT, GL_DIFFUSE);
    }

    public static String getOpenGLVersion(){
        return glGetString(GL_VERSION);
    }

    public static void setClearColor(Vector3f color){
        glClearColor(0.3f,0.3f,1.0f, 1.0f);
    }

    public static FloatBuffer reserveData(int amountOfElements) {
        return BufferUtils.createFloatBuffer(amountOfElements);
    }

    public static void unbindTextures()
    {
        glBindTexture(GL_TEXTURE_2D, 1);
    }

    //TODO: Either remove these or make them better

    public static void make3D() {

        glPopAttrib();
        glPushAttrib(GL_ENABLE_BIT);

        glEnable(GL_DEPTH_TEST);
        glDepthFunc(GL_LEQUAL);

        glViewport(0,0, Launch.WIDTH,Launch.HEIGHT);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();

        // FOV = Field of View
        // Aspect ration
        // zNear and zFar (Viewing range)
        GLU.gluPerspective(70f, (float) Launch.WIDTH / Launch.HEIGHT,0.1f,1000f);

        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();

    }

    public static void make2D() {

        glPopAttrib();
        glPushAttrib(GL_ENABLE_BIT);

        glViewport(0,0,Launch.WIDTH,Launch.HEIGHT);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, Launch.WIDTH, Launch.HEIGHT,0,-1,1);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();

    }


}

