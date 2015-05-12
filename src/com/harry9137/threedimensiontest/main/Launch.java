package com.harry9137.threedimensiontest.main;


import com.harry9137.threedimensiontest.render.Window;
import com.harry9137.threedimensiontest.scenes.Scene2DVideo;
import com.harry9137.threedimensiontest.util.EnvironmentVariables;
import com.harry9137.threedimensiontest.util.RenderUtil;
import com.harry9137.threedimensiontest.util.ResourceLoader;
import com.harry9137.threedimensiontest.util.Time;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;

import java.io.File;
import java.io.IOException;

public class Launch {

    public static final int WIDTH = 960;
    public static final int HEIGHT = 540;
    public static final String TITLE = "3DT";
    public static final double FRAME_CAP = 60;
    public static String TEMP_DIRECTORY = null;
    public static final double TPS = 60;

    private boolean isRunning;
    private Game game;

    public Launch(){
        System.out.println(RenderUtil.getOpenGLVersion());
        RenderUtil.initGraphics();
        isRunning = false;
        game = Game.getInstance();
        EnvironmentVariables.update();
        TEMP_DIRECTORY = EnvironmentVariables.variables.get("TEMP") + "\\3DT";
        File tempFile = new File(TEMP_DIRECTORY);
        if(!tempFile.exists()){
            if(tempFile.mkdir()){
                System.out.println("Created TEMP Directory Successfully!");
            }
            else {
                System.out.println("Failed to Create TEMP Directory");
            }
        }
        try {
            ResourceLoader.loadScene("Scene1.scn");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start(){
        if(isRunning){
            return;
        }

        run();
    }

    public void stop(){
        if(!isRunning){
            return;
        }
        isRunning = false;
    }

    private void run(){
        isRunning = true;

        int frames  = 0;
        long frameCounter = 0;
        int timeSecond = 0;

        long lastTime = Time.getTime();
        double unprocessedTime = 0;
        final double frameTime = 1.0 / FRAME_CAP;

        while(isRunning){
            boolean render = false;

            long startTime = Time.getTime();
            long passedTime = startTime - lastTime;
            lastTime = startTime;

            unprocessedTime += passedTime / (double)Time.SECOND;
            frameCounter += passedTime;

            while(unprocessedTime > frameTime){
                render = true;
                unprocessedTime -= frameTime;
                timeSecond++;

                if(Window.isCloseRequested()){
                    stop();
                }

                Time.setDelta(frameTime);

                //Updates
                game.input();
                game.update();

                Input.update();


                if(frameCounter >= Time.SECOND){
                    System.out.println(frames);
                    frames = 0;
                    frameCounter = 0;
                    System.out.println("TPS: " + Integer.toString(game.tps));
                    game.setTps(0);
                }
            }
            if(render) {
                render();
                frames++;
            }
            else{
                try {
                    Thread.sleep(1);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
        cleanUp();
    }

    private void render(){
        RenderUtil.clearScreen();
        game.render();
        Window.render();
    }

    private void cleanUp(){
        Window.dispose();
    }

    public static void main(String[] args){
        Window.createWindow(WIDTH, HEIGHT, TITLE);

        Launch game = new Launch();

        game.start();
    }
}
