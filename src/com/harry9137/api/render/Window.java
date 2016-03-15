package com.harry9137.api.render;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Window {
    public static void createWindow(int width, int height, String title){
        Display.setTitle(title);
        try {
            Display.setDisplayMode(new DisplayMode(width, height));
            Display.setResizable(true);
            Display.create();
            Keyboard.create();
            Mouse.create();
        }
        catch(LWJGLException e){
            e.printStackTrace();
        }
    }
    public static void render(){
        Display.update();
    }
    public static void dispose(){
        Display.destroy();
        Keyboard.destroy();
        Mouse.destroy();
    }
    public static boolean isCloseRequested(){
        return Display.isCloseRequested();
    }
    public static boolean checkResize(){
        return Display.wasResized();
    }
    public static int getWidth(){
        return Display.getWidth();
    }
    public static int getHeight(){
        return Display.getHeight();
    }
    public static String getTitle(){
        return Display.getTitle();
    }
    public static void setDisplayMode(DisplayMode displayMode){

    }
}
