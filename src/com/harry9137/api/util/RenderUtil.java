package com.harry9137.api.util;

import com.harry9137.api.render.math.Vector3f;

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
        glEnable(GL_FRAMEBUFFER_SRGB);
    }

    public static String getOpenGLVersion(){
        return glGetString(GL_VERSION);
    }

    public static void setClearColor(Vector3f color){
        glClearColor(0.3f,0.3f,1.0f, 1.0f);
    }
    public static void unbindTextures()
    {
        glBindTexture(GL_TEXTURE_2D, 1);
    }
}
