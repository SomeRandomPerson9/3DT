package com.harry9137.api.render;

import com.harry9137.api.render.math.Vector3f;
import com.harry9137.api.util.Util;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;

public class Mesh {
    private int vbo;
    private int ibo;
    private int size;

    public Mesh(){
        vbo = glGenBuffers();
        ibo = glGenBuffers();
        size = 0;
    }
    public void addVertices(Vertex[] data, int[] indices) {
        addVertices(data, indices, false);
    }

    public void addVertices(Vertex[] data, int[] indices, boolean calcNormals){
        if(calcNormals){
            calcNormals(data, indices);
        }
        size = indices.length;

        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, Util.createFlippedBuffer(data), GL_STATIC_DRAW);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, Util.createFlippedBuffer(indices), GL_STATIC_DRAW);

    }
    public void draw(){
        glEnableVertexAttribArray(0);
        glEnableVertexAttribArray(1);
        glEnableVertexAttribArray(2);

        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glVertexAttribPointer(0,3, GL_FLOAT, false, Vertex.SIZE * 4, 0);
        glVertexAttribPointer(1,2, GL_FLOAT, false, Vertex.SIZE * 4, 12);
        glVertexAttribPointer(2,3, GL_FLOAT, false, Vertex.SIZE * 4, 20);

        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER,ibo);
        glDrawElements(GL_TRIANGLES, size, GL_UNSIGNED_INT, 0);

        glDisableVertexAttribArray(0);
        glDisableVertexAttribArray(1);
        glDisableVertexAttribArray(2);
    }
    private void calcNormals(Vertex[] vertexes, int[] integers){
       for(int i = 0; i < integers.length; i += 3){
           int i0 = integers[i];
           int i1 = integers[i + 1];
           int i2 = integers[i + 2];

           Vector3f v1 = vertexes[i1].getPos().Sub(vertexes[i0].getPos());
           Vector3f v2 = vertexes[i2].getPos().Sub(vertexes[i0].getPos());

           Vector3f normal = v1.cross(v2).Normalized();
           vertexes[i0].setNormal(vertexes[i0].getNormal().Add(normal));
           vertexes[i1].setNormal(vertexes[i0].getNormal().Add(normal));
           vertexes[i2].setNormal(vertexes[i0].getNormal().Add(normal));
       }
        for(int i = 0; i < vertexes.length; i++){
            vertexes[i].setNormal(vertexes[i].getNormal().Normalized());
        }
    }

    public int getIbo() {
        return ibo;
    }

    public int getVbo() {
        return vbo;
    }
}
