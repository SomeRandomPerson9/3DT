package com.harry9137.threedimensiontest.util;

import org.newdawn.slick.TrueTypeFont;

import java.awt.*;
import java.util.HashMap;

public class resources {
    public static final boolean FreeMovingCamera = true;
    public static final float terminalVelocity = -56;
    public static final double gravity  = 9.807;

    public class fonts{
            private Font awtArialFont;
            public TrueTypeFont arialFont;

        public void init(){
            try{
                awtArialFont = new Font("Arial", Font.PLAIN, 24);
                arialFont = new TrueTypeFont(awtArialFont, false);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

}
