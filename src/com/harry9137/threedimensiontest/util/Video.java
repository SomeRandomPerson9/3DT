package com.harry9137.threedimensiontest.util;

import com.harry9137.threedimensiontest.render.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.util.ArrayList;

public class Video {
    ArrayList<Texture> vid = new ArrayList<Texture>();
    public Video(File loctation) throws IOException{
        importVid(loctation);
    }
    public Video importVid(File loctation) throws IOException{
        ArrayList<Texture> temp2 = new ArrayList<Texture>();
        if(!loctation.exists()){
            throw new FileNotFoundException("You done goofed, that's not a file");
        }
        if(loctation.getName().contains(".")){
            throw new FileSystemException("Directory Plz");
        }
        File[] images = loctation.listFiles();
        for(File temp : images){
            temp2.add(new Texture(TextureLoader.getTexture("png", new FileInputStream(temp)).getTextureID()));
        }
        vid = temp2;
        return this;

    }
}
