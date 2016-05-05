package com.harry9137.api.scenes.Objects.logic;

import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.UnicodeFont;

public class CounterObject extends TextObject {
    public int number;
    public CounterObject(UnicodeFont font, String string, int x, int y, int startNum){
        super(font, string, x, y);
        number = startNum;

    }
    public CounterObject(int startNum){
        super(Integer.toString(startNum));
        number = startNum;
    }
    public CounterObject(int startNum, int x, int y){
        super(Integer.toString(startNum));
        number = startNum;
    }
    public void countUp(){
        number++;
        super.setString(Integer.toString(number));
    }
    public void countDown(){
        number--;
        super.setString(Integer.toString(number));
    }
}
