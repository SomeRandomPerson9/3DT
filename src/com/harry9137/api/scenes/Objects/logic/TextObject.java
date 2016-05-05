package com.harry9137.api.scenes.Objects.logic;

import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.UnicodeFont;

public class TextObject extends GenericObject {
    private UnicodeFont font;
    String string;

    String objName;

    int x;
    int y;
    public TextObject(String string){
        this.string = string;
    }

    public TextObject(UnicodeFont updatedFont, String string){
        this.string = string;
        font = updatedFont;
    }

    public TextObject(String string, int updatedX, int updatedY){
        this.string = string;
        x = updatedX;
        y = updatedY;
    }

    public TextObject(UnicodeFont updatedFont, String string, int updatedX, int updatedY){
        this.string = string;
        x = updatedX;
        y = updatedY;
        font = updatedFont;
    }

    public TextObject(UnicodeFont updatedFont){
        font = updatedFont;
    }

    public TextObject(UnicodeFont updatedFont, int updatedX, int updatedY){
        font = updatedFont;
        x = updatedX;
        y = updatedY;
    }

    public TextObject(){

    };

    public void render(){
        font.drawString(this.x, this.y, string);

    }
    public void render(int x, int y){
        font.drawString(x, y, string);

    }
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public UnicodeFont getFont() {
        return font;
    }

    public void setFont(UnicodeFont font) {
        this.font = font;
    }

    public String getObjName() {
        return objName;
    }

    public GenericObject setObjName(String objName) {
        this.objName = objName;
        return this;
    }
}
