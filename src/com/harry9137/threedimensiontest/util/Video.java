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
    File[] vid;
    public Video(File loctation) throws IOException{
        importVid(loctation);
    }
    public Video importVid(File loctation) throws IOException{
        if(!loctation.exists()){
            throw new FileNotFoundException("You done goofed, that's not a file");
        }
        if(loctation.getName().contains(".")){
            throw new FileSystemException("Directory Plz");
        }
        vid = loctation.listFiles();
        return this;

    }
    public File[] getVid(){
        return vid;
    }
}
