package com.harry9137.api.scenes.Objects.logic;

import org.newdawn.slick.TrueTypeFont;

public class TextObject extends GenericObject {
    private TrueTypeFont font;
    String string;

    String objName;

    int x;
    int y;
    public TextObject(String string){
        this.string = string;
    }

    public TextObject(TrueTypeFont updatedFont, String string){
        this.string = string;
        font = updatedFont;
    }

    public TextObject(String string, int updatedX, int updatedY){
        this.string = string;
        x = updatedX;
        y = updatedY;
    }

    public TextObject(TrueTypeFont updatedFont, String string, int updatedX, int updatedY){
        this.string = string;
        x = updatedX;
        y = updatedY;
        font = updatedFont;
    }

    public TextObject(TrueTypeFont updatedFont){
        font = updatedFont;
    }

    public TextObject(TrueTypeFont updatedFont, int updatedX, int updatedY){
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

    public TrueTypeFont getFont() {
        return font;
    }

    public void setFont(TrueTypeFont font) {
        this.font = font;
    }

    public String getObjName() {
        return objName;
    }

    public TextObject setObjName(String objName) {
        this.objName = objName;
        return this;
    }
}