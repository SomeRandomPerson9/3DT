package com.harry9137.api.render;

import com.harry9137.api.render.math.Matrix4f;
import com.harry9137.api.render.math.Vector3f;

public class Transform {

    private static float zNear;
    private static float zFar;
    private static float width;
    private static float height;
    private static float fov;
    private static Camera camera;

    private Vector3f translation;
    private Vector3f rotation;
    private Vector3f scale;
    public Transform(){
        translation = new Vector3f(0,0,0);
        rotation = new Vector3f(0,0,0);
        scale = new Vector3f(1,1,1);
    }
    public Transform(float zNear,float zFar,float width, float height, float fov, Camera camera, Vector3f translation, Vector3f rotation, Vector3f scale){
        this.zNear = zNear;
        this.zFar = zFar;
        this.width = width;
        this.height = height;
        this.fov = fov;
        this.camera = camera;
        this.translation = translation;
        this.rotation = rotation;
        this.scale = scale;
    }


    public Matrix4f getTransformation(){
        Matrix4f translationMatrix = new Matrix4f().initTranslation(translation.GetX(), translation.GetY(), translation.GetZ());
        Matrix4f rotationMatrix = new Matrix4f().initRotation(rotation.GetX(), rotation.GetY(), rotation.GetZ());
        Matrix4f scaleMatrix = new Matrix4f().initScale(scale.GetX(),scale.GetY(),scale.GetZ());

        return translationMatrix.mul(rotationMatrix.mul(scaleMatrix));
    }
    public Transform mulTranslation(Vector3f vec3){
        translation.Mul(vec3);
        return this;
    }
    public Transform addTranslation(Vector3f vec3){
        setTranslationBase(getTranslation().GetX() + vec3.GetX(),getTranslation().GetY() + vec3.GetY(),getTranslation().GetZ() + vec3.GetZ());
        return this;
    }
    public Matrix4f getProjectedTransformation(Matrix4f locationTrans){
        Matrix4f transformationMatrix = this.copy().getTransformation();
        Matrix4f projectionMatrix = new Matrix4f().initProjection(fov,width,height,zNear,zFar);
        Matrix4f cameraMatrix = new Matrix4f().initCamera(camera.getForward(), camera.getUp());
        Matrix4f cameraTranslationMatrix = new Matrix4f().initTranslation(-camera.getPos().GetX(), -camera.getPos().GetY(), -camera.getPos().GetZ());

        return projectionMatrix.mul(cameraMatrix.mul(cameraTranslationMatrix.mul(locationTrans.mul(transformationMatrix))));

    }
    public Object[] getProjectedTransformationHeld(Matrix4f locationTrans){
        Matrix4f transformationMatrix = this.copy().getTransformation();
        Matrix4f projectionMatrix = new Matrix4f().initProjection(fov,width,height,zNear,zFar);
        Matrix4f cameraMatrix = new Matrix4f().initCamera(camera.getForward(), camera.getUp());
        Matrix4f cameraTranslationMatrix = new Matrix4f().initTranslation(-camera.getPos().GetX(), -camera.getPos().GetY(), -camera.getPos().GetZ());
        Matrix4f locationTrans2 = locationTrans.mul(cameraMatrix.conjugate().mul(cameraTranslationMatrix.conjugate()));

        return new Object[]{projectionMatrix.mul(cameraMatrix.mul(cameraTranslationMatrix.mul(locationTrans2.mul(transformationMatrix)))), locationTrans2};

    }
    public Vector3f getTranslation() {
        return translation;
    }
    public static void setProjection(float fov, float width, float height, float zNear, float zFar){
        Transform.fov = fov;
        Transform.width = width;
        Transform.height = height;
        Transform.zFar = zFar;
        Transform.zNear = zNear;

    }

    public void setTranslation(Vector3f translation) {
        this.translation = translation;
    }
    public void setTranslation(float x,float y, float z) {
        this.translation = new Vector3f(x,y,z);
    }
    public void setTranslationBase(float x,float y, float z) {
        this.translation = new Vector3f(x,y,z);
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
    }
    public void setRotation(float x, float y,float z) {
        this.rotation = new Vector3f(x,y,z);
    }

    public Vector3f getScale() {
        return scale;
    }

    public void setScale(Vector3f scale) {
        this.scale = scale;
    }
    public void setScale(float x, float y,float z) {
        this.scale = new Vector3f(x,y,z);
    }
    public static Camera getCamera() {
        return camera;
    }

    public static void setCamera(Camera camera) {
        Transform.camera = camera;
    }

    public Transform copy(){
        return new Transform(zNear,zFar,width,height,fov,camera,translation,rotation,scale);
    }

}
