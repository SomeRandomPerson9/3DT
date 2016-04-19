package com.harry9137.api.util;

import com.harry9137.api.render.Material;
import com.harry9137.api.render.math.Matrix4f;
import com.harry9137.api.render.Vertex;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

public class Util {

    public static FloatBuffer createFloatBuffer(int size){
        return BufferUtils.createFloatBuffer(size);
    }
    public static IntBuffer createIntBuffer(int size){
        return BufferUtils.createIntBuffer(size);
    }
    public static IntBuffer createFlippedBuffer(int... values){
        IntBuffer buffer = createIntBuffer(values.length);
        buffer.put(values);
        buffer.flip();
        return buffer;
    }
    public static FloatBuffer createFlippedBuffer(Vertex[] vertices){
        FloatBuffer buffer = createFloatBuffer(vertices.length * Vertex.SIZE);

        for(int i = 0; i < vertices.length; i++){
            buffer.put(vertices[i].getPos().GetX());
            buffer.put(vertices[i].getPos().GetY());
            buffer.put(vertices[i].getPos().GetZ());
            buffer.put(vertices[i].getTexCoord().GetX());
            buffer.put(vertices[i].getTexCoord().GetY());
            buffer.put(vertices[i].getNormal().GetX());
            buffer.put(vertices[i].getNormal().GetY());
            buffer.put(vertices[i].getNormal().GetZ());
        }
        buffer.flip();

        return buffer;
    }
    public static FloatBuffer createFlippedBuffer(Matrix4f value){
        FloatBuffer buffer = createFloatBuffer(4 * 4);

        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                buffer.put(value.get(i,j));
            }
        }
        buffer.flip();

        return buffer;
    }

    public static String[] removeEmptyStrings(String[] tokens) {
        ArrayList<String> result = new ArrayList<String>();
        for(int i = 0; i < tokens.length; i++){
            if(!tokens[i].equals("")){
                result.add(tokens[i]);
            }
        }
        String[] res = new String[result.size()];
        result.toArray(res);
        return res;
    }

    public static int[] toIntArray(Integer[] data) {
        int[] result = new int[data.length];
        for(int i = 0; i < data.length; i++){
            result[i] = data[i].intValue();
        }
        return result;
    }

    public static Material[] toMaterialArray(ArrayList<Material> materialArrayList){
        Material[] result = new Material[materialArrayList.size()];
        for(int i = 0; i < materialArrayList.size(); i++){
            result[i] = materialArrayList.get(i);
        }
        return result;
    }

    public static float[] stringToFloatArray(String[] strings){
        float[] result = new float[strings.length];
        for(int i = 0; i < strings.length; i++){
            result[i] = Float.parseFloat(strings[i]);
        }
        return result;
    }
}
