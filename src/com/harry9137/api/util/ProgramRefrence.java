package com.harry9137.api.util;

import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

import java.awt.*;
import java.text.DecimalFormat;

public class ProgramRefrence {
    public static final boolean FreeMovingCamera = true;
    public static final float terminalVelocity = -56;
    public static final double gravity  = 9.807;

    public static class fonts{
            private static Font awtArialFont;
            public static UnicodeFont arialFont;


    }

    public static class formatters{
        public static DecimalFormat hundredths = new DecimalFormat("#.##");
    }


    public static void init(){
        try{
            fonts.awtArialFont = new Font("Times New Roman", Font.PLAIN, 18);
            fonts.arialFont = new UnicodeFont(fonts.awtArialFont);
            fonts.arialFont.getEffects().add(new ColorEffect(Color.orange));
            fonts.arialFont.addAsciiGlyphs();
            fonts.arialFont.loadGlyphs();


        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
