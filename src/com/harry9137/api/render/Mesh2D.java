package com.harry9137.api.render;


import org.lwjgl.opengl.GL11;

public class Mesh2D {
    int texID;
    int xSize;
    int ySize;
    public Mesh2D(int texID, int xSize, int ySize){
        this.texID = texID;
        this.xSize = xSize;
        this.ySize = ySize;
    }

    public void draw(float xPos, float yPos){
        GL11.glPushMatrix();
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texID);
        GL11.glTranslatef(xPos, yPos, 0f);
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glTexCoord2f(0, 0);
        GL11.glVertex2i(0, 0);

        GL11.glTexCoord2f(1, 0);
        GL11.glVertex2i(xSize, 0);

        GL11.glTexCoord2f(1, 1);;
        GL11.glVertex2i(xSize, ySize);

        GL11.glTexCoord2f(0, 1);
        GL11.glVertex2i(0, ySize);
        GL11.glEnd();

        GL11.glPopMatrix();
    }
}
