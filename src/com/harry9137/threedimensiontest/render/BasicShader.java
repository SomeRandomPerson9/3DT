package com.harry9137.threedimensiontest.render;

import com.harry9137.threedimensiontest.util.ResourceLoader;
import com.harry9137.threedimensiontest.render.math.Matrix4f;
import com.harry9137.threedimensiontest.util.RenderUtil;

public class BasicShader extends Shader {

    private static final BasicShader instance = new BasicShader();

    public static BasicShader getInstance(){
        return instance;
    }
    public BasicShader(){
        super();

        addVertexShader(ResourceLoader.loadShader("basicVertex.vs"));
        addFragmentShader(ResourceLoader.loadShader("basicFragment.fs"));
        compileShader();

        addUniform("transform");
        addUniform("baseColor");
    }

    @Override
    public void updateUniforms(Matrix4f worldMatrix, Matrix4f projectedMatrix, Material material){
        if(material.getTexture() != null){
            material.getTexture().bind();

        }
        else
            RenderUtil.unbindTextures();

        setUniform("transform", projectedMatrix);
        setUniform("color", material.getColor());
    }
}
