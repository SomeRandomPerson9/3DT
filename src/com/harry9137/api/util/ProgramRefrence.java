package com.harry9137.api.util;

import org.newdawn.slick.TrueTypeFont;

import java.awt.*;

public class ProgramRefrence {
    public static final boolean FreeMovingCamera = true;
    public static final float terminalVelocity = -56;
    public static final double gravity  = 9.807;

    public static class fonts{
            private static Font awtArialFont;
            public static org.newdawn.slick.TrueTypeFont arialFont;


    }

    public static void init(){
        try{
            fonts.awtArialFont = new Font("Arial", Font.PLAIN, 24);
            fonts.arialFont = new TrueTypeFont(fonts.awtArialFont, false);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
