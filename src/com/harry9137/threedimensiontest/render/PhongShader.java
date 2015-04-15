package com.harry9137.threedimensiontest.render;

import com.harry9137.threedimensiontest.util.ResourceLoader;
import com.harry9137.threedimensiontest.render.math.Matrix4f;
import com.harry9137.threedimensiontest.render.math.Vector3f;
import com.harry9137.threedimensiontest.render.lighting.BaseLight;
import com.harry9137.threedimensiontest.render.lighting.DirectionalLight;
import com.harry9137.threedimensiontest.util.RenderUtil;

public class PhongShader extends Shader {
    private static final PhongShader instance = new PhongShader();

    public static PhongShader getInstance() {
        return instance;
    }

    private static Vector3f ambientLight = new Vector3f(0.1f,0.1f,0.1f);
    private static DirectionalLight directionalLight = new DirectionalLight(new BaseLight(new Vector3f(1,1,1), 0f), new Vector3f(0,0,0));

    private PhongShader() {
        super();

        addVertexShader(ResourceLoader.loadShader("phongVertex.vs"));
        addFragmentShader(ResourceLoader.loadShader("phongFragment.fs"));
        compileShader();

        addUniform("transform");
        addUniform("baseColor");
        addUniform("ambientLight");

        addUniform("directionalLight.base.color");
        addUniform("directionalLight.base.intensity");
        addUniform("directionalLight.direction");
    }

    public static DirectionalLight getDirectionalLight() {
        return directionalLight;
    }

    public static void setDirectionalLight(DirectionalLight directionalLight) {
        PhongShader.directionalLight = directionalLight;
    }

    public void updateUniforms(Matrix4f worldMatrix, Matrix4f projectedMatrix, Material material) {
        if (material.getTexture() != null)
            material.getTexture().bind();
        else
            RenderUtil.unbindTextures();

        setUniform("transform", projectedMatrix);
        setUniform("baseColor", material.getColor());
        setUniform("ambientLight", ambientLight);

        setUniform("directionalLight", directionalLight);


    }

    public static Vector3f getAmbientLight() {
        return ambientLight;
    }

    public static void setAmbientLight(Vector3f ambientLight) {
        PhongShader.ambientLight = ambientLight;
    }
    public void setUniform(String uniformName, BaseLight baseLight){
        setUniform(uniformName + ".color", baseLight.getColor());
        setUniformf(uniformName + ".intensity", baseLight.getIntensity());
    }
    public void setUniform(String uniformName, DirectionalLight directionalLight)
    {
        setUniform(uniformName + ".base", directionalLight.getBase());
        setUniform(uniformName + ".direction", directionalLight.getDirection());
    }
}