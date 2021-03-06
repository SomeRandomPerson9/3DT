package com.harry9137.api.scenes;

import com.harry9137.api.render.Transform;
import com.harry9137.api.main.Game;
import com.harry9137.api.physics.MathHelper;
import com.harry9137.api.render.Material;
import com.harry9137.api.render.Mesh;
import com.harry9137.api.render.math.Matrix4f;
import com.harry9137.api.render.math.Vector3f;
import com.harry9137.api.scenes.Objects.ChoiceMenuObject;
import com.harry9137.api.scenes.Objects.logic.GenericObject;
import com.harry9137.api.scenes.Objects.logic.RenderObject;
import com.harry9137.api.scenes.Objects.logic.TextObject;
import com.harry9137.api.util.ProgramRefrence;
import com.harry9137.api.util.RenderUtil;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.Sphere;
import org.newdawn.slick.Color;

import java.util.HashMap;

public class SceneLoader {
    private static HashMap <Integer, SceneBase> scenes = new HashMap<>();
    private static SceneBase selectedScene;
    private static int selectedSceneNumber;
    private static Sphere sphere = new Sphere();

    public static void addScene(Integer integer, SceneBase scene){
        scenes.put(integer, scene);
    }
    public static void selectScene(Integer integer){
        if(selectedScene != null) {
            selectedScene.cleanup();
            //selectedScene.getRegShader().updateUniforms(new Matrix4f(),new Matrix4f(), null);
        }
        selectedScene = scenes.get(integer);
        selectedSceneNumber = integer;
        selectedScene.specialInit();
    }
    public static void updateScene(){
        if(selectedScene != null) {
            selectedScene.update();
            if(selectedScene.getDynamicsWorld() != null) {
                //System.out.println("blah");
                glLoadIdentity();
                selectedScene.getDynamicsWorld().stepSimulation(1.0f / 60.0f);
                for(RenderObject renderObject : selectedScene.getObjects()){
                    if (renderObject.isPhys()) {
                        renderObject.setLocation(MathHelper.vecMathToBaked3f(renderObject.getRigidBodyShape().getMotionState().getWorldTransform(new com.bulletphysics.linearmath.Transform()).origin));
                        //System.out.println(renderObject.getRigidBodyShape().getMotionState().getWorldTransform(new Transform()).origin);
                    }
                }

            }
            for (int i = 0; i < scenes.size(); i++) {
                if (scenes.get(i) != null) {
                    if (scenes.get(i).getBtsUpdateLvl() > 0) {
                        scenes.get(i).update();
                    }
                    if (selectedSceneNumber == i) {
                        selectedScene.update();
                    }
                }
            }
        }
    }
    public static void renderScene(){
        if(selectedScene.sceneType == SceneType.THREE_DIMENSIONAL) {

            RenderUtil.make3D();

            for (RenderObject renderObject : selectedScene.getObjects()) {
                /*if(renderObject.isHeld()) {
                        Object[] temp = (renderObject.getTransform().getProjectedTransformationHeld(new Matrix4f().initTranslation(renderObject.getLocation().GetX(), renderObject.getLocation().GetY(), renderObject.getLocation().GetZ())));
                        selectedScene.getRegShader().updateUniforms(renderObject.getTransform().getTransformation(), (Matrix4f) temp[0], renderObject.getMaterial());
                        //renderObject.setLocation(((Matrix4f)temp[2]).get;
                }
                else{
                    selectedScene.getRegShader().updateUniforms(renderObject.getTransform().getTransformation(), renderObject.getTransform().getProjectedTransformation(new Matrix4f().initTranslation(renderObject.getLocation().GetX(), renderObject.getLocation().GetY(), renderObject.getLocation().GetZ())), renderObject.getMaterial());
                }*/
                for(Mesh meshyThing : renderObject.getMeshs().values()){
                    if(renderObject.getObjName().equals("Desk")){
                        //System.out.println("tryingtorender");
                    }
                    selectedScene.getRegShader().bind();
                    //System.out.println("Object " + meshyThing.getObjName() + " has material " + meshyThing.getRequiredMtl());
                    //System.out.println(renderObject.getLocation());
                    //System.out.println("Stuf + Tjomgs: " + renderObject.getMaterials());
                    selectedScene.getRegShader().updateUniforms(renderObject.getTransform().getTransformation(), renderObject.getTransform().getProjectedTransformation(new Matrix4f().initTranslation(renderObject.getLocation().GetX(), renderObject.getLocation().GetY(), renderObject.getLocation().GetZ())), renderObject.getMaterial(meshyThing.getRequiredMtl()));
                    meshyThing.draw();
                    glLoadIdentity();
                }

                //renderObject.getMesh().draw();
                /*if(Game.showBoundingBoxes && selectedScene.getDynamicsWorld() != null && renderObject.isPhys()){
                    GL11.glPushMatrix();
                    javax.vecmath.Vector3f position = renderObject.getRigidBodyShape().getMotionState().getWorldTransform(new Transform()).origin;
                    sphere.setDrawStyle(GLU.GLU_SILHOUETTE);
                    GL11.glTranslatef(position.x, position.y, position.z);
                    GL11.glColor4f(0, 1, 0, 1);
                    sphere.draw(renderObject.getRigidBodyShape().getCollisionShape().getAngularMotionDisc(), 30, 30);
                    GL11.glPopMatrix();
                */
            }

            glMatrixMode(GL_PROJECTION);
            glLoadMatrix(selectedScene.getOrthographicProjectionMatrix());
            glMatrixMode(GL_MODELVIEW);

            for(GenericObject genericObject : selectedScene.getOverlayObjects()){
                if(genericObject instanceof TextObject){
                    TextObject textObj = (TextObject)genericObject;
                    try {
                        glPushMatrix();
                        glLoadIdentity();
                        //glDisable(GL_LIGHTING);
                        ProgramRefrence.fonts.arialFont.drawString(10, 10, "EulerCamera: [x=" + ProgramRefrence.formatters.hundredths.format(selectedScene.getCamera().getPos().GetX()) +
                                ",y=" + ProgramRefrence.formatters.hundredths.format(selectedScene.getCamera().getPos().GetY()) + ",z=" + ProgramRefrence.formatters.hundredths.format(selectedScene.getCamera().getPos().GetZ()) + "]");
                        //glEnable(GL_LIGHTING);
                        glPopMatrix();
                    }catch(NullPointerException e){
                        if(ProgramRefrence.fonts.arialFont == null){ 
                            System.err.println("Global font is null");
                        }
                        //if(textObj.getFont() == null) {
                        //    System.err.println("Text Object " + textObj.getObjName() + ": Font is Null");
                        //}
                        if(textObj.getString() == null){
                            System.err.println("Text Object " + textObj.getObjName() + ": String is Null");
                        }
                    }
                }
                else if(genericObject instanceof ChoiceMenuObject){
                    ChoiceMenuObject choiceMenuObject = (ChoiceMenuObject)genericObject;

                    try{
                        //choiceMenuObject.mesh.draw(choiceMenuObject.xPos, choiceMenuObject.yPos);
                    }
                    catch(Exception e){

                    }
                }
            }

            //glMatrixMode(GL_PROJECTION);
            //glLoadMatrix(selectedScene.getPerspectiveProjectionMatrix());
            //glMatrixMode(GL_MODELVIEW);

        }
        else if(selectedScene.sceneType == SceneType.TWO_DIMENSIONAL){
            if(selectedScene instanceof Scene2DVideo){

                ((Scene2DVideo) selectedScene).render();
            }
        }
    }
    /* public static void calcPhysics(){
        if(selectedScene != null) {
            for (RenderObject object : selectedScene.getObjects()) {
                if(object.isPhys()) {

                    object.getTransform().addTranslation(object.getLocation());

                    int tps = Game.getInstance().getTps();
                    //Gravity
                    if (object.getVelocity().GetY() >= ProgramRefrence.terminalVelocity) {
                        object.setAcceleration(new Vector3f((float) object.getAcceleration().GetX(), (float) (object.getAcceleration().GetY() - ProgramRefrence.gravity / tps), (float) object.getAcceleration().GetZ()));
                    }

                    //Acceleration to Velocity
                    if (object.getAcceleration().GetX() != 0) {
                        object.getVelocity().SetX((float) (object.getAcceleration().GetX() / tps) + object.getVelocity().GetX());
                    }
                    if (object.getAcceleration().GetY() != 0) {
                        object.getVelocity().SetY((float) (object.getAcceleration().GetY() * Launch.TPS / 100) + object.getVelocity().GetY());
                    }
                    if (object.getAcceleration().GetX() != 0) {
                        object.getVelocity().SetZ((float) (object.getAcceleration().GetZ() * Launch.TPS / 100) + object.getVelocity().GetZ());
                    }
                    object.setAcceleration(new Vector3f(0, 0, 0));

                    //Velocity to Location
                    if (object.getAcceleration().GetX() != 0) {
                        object.getLocation().SetX((float) (object.getAcceleration().GetX() / tps) + object.getVelocity().GetX());
                    }
                    if (object.getAcceleration().GetY() != 0) {
                        if(object.getLocation().GetY() > 0f) {
                            object.getLocation().SetY((float) (object.getAcceleration().GetY() * Launch.TPS / 100) + object.getVelocity().GetY());
                        }
                    }
                    if (object.getAcceleration().GetX() != 0) {
                        object.getLocation().SetZ((float) (object.getAcceleration().GetZ() * Launch.TPS / 100) + object.getVelocity().GetZ());
                    }
                }

            }
        }
    }   */
    public static void updateSceneInput(){
        if(selectedScene != null) {
            selectedScene.input();
        }
    }
    public static int getSelectedSceneNumber(){
        return selectedSceneNumber;
    }
    public static SceneBase getSelectedScene(){
        return selectedScene;
    }
    public static HashMap<Integer, SceneBase> getScenes() {
        return scenes;
    }

}
