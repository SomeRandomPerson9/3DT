package com.harry9137.api.render.shaders;

import com.harry9137.api.render.Material;
import com.harry9137.api.render.math.Matrix4f;
import com.harry9137.api.util.RenderUtil;
import com.harry9137.api.util.ResourceLoader;

public class OverlayShader extends Shader {
    private static final OverlayShader instance = new OverlayShader();

    public static OverlayShader getInstance() {
        return instance;
    }

    private OverlayShader(){
        super();

        addVertexShader(ResourceLoader.loadShader("overlayVertex.vs"));
        addFragmentShader(ResourceLoader.loadShader("overlayFragment.fs"));
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

        setUniform("transform", worldMatrix);
        setUniform("color", material.getColor());
    }
}
