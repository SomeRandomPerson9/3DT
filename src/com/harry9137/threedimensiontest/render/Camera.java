package com.harry9137.threedimensiontest.render;

import com.harry9137.threedimensiontest.main.Input;
import com.harry9137.threedimensiontest.util.Time;
import com.harry9137.threedimensiontest.render.math.Vector2f;
import com.harry9137.threedimensiontest.render.math.Vector3f;
import com.harry9137.threedimensiontest.util.resources;
import org.lwjgl.input.Keyboard;

public class Camera {
    public static final Vector3f yAxis = new Vector3f(0,1,0);

    private Vector3f pos;
    private Vector3f forward;
    private Vector3f up;

    public Camera(){
        this(new Vector3f(0,0,0), new Vector3f(0,0,1), new Vector3f(0,1,0));
    }
    public Camera(Vector3f pos, Vector3f forward, Vector3f up){
        this.pos = pos;
        this.forward = forward;
        this.up = up;

        up.Normalized();
        forward.Normalized();
    }
    boolean mouseLocked = false;
    Vector2f centerPosition = new Vector2f(Window.getWidth()/2, Window.getHeight()/2);

    public void input(){
        float moveAmt = (float)(10 * Time.getDelta());
        float rotAmt = (float)(100 * Time.getDelta());
        float sensitivity = 0.5f;

        if(Input.getKey(Keyboard.KEY_ESCAPE))
        {
            Input.setCursor(true);
            mouseLocked = false;
        }
        if(Input.getMouse(0))
        {
            Input.setMousePosition(centerPosition);
            Input.setCursor(false);
            mouseLocked = true;
        }
        if(resources.FreeMovingCamera) {

            if (Input.getKey(Keyboard.KEY_W)) {
                move(getForward(), moveAmt);
            }
            if (Input.getKey(Keyboard.KEY_S)) {
                move(getForward(), -moveAmt);
            }
            if (Input.getKey(Keyboard.KEY_A)) {
                move(getLeft(), moveAmt);
            }
            if (Input.getKey(Keyboard.KEY_D)) {
                move(getRight(), moveAmt);
            }

            if (Input.getKey(Keyboard.KEY_UP)) {
                rotateX(-rotAmt);
            }
            if (Input.getKey(Keyboard.KEY_DOWN)) {
                rotateX(rotAmt);
            }
            if (Input.getKey(Keyboard.KEY_LEFT)) {
                rotateY(-rotAmt);
            }
            if (Input.getKey(Keyboard.KEY_RIGHT)) {
                rotateY(rotAmt);
            }

            if(mouseLocked)
            {
                Vector2f deltaPos = Input.getMousePosition().Sub(centerPosition);

                boolean rotY = deltaPos.GetX() != 0;
                boolean rotX = deltaPos.GetY() != -180;

                if(rotY)
                    rotateY(deltaPos.GetX() * sensitivity);
                if(rotX)
                    rotateX(-deltaPos.GetY() * sensitivity);

                if(rotY || rotX)
                    Input.setMousePosition(new Vector2f(Window.getWidth()/2, Window.getHeight()/2));
            }
        }
    }
    public void move(Vector3f dir, float amt){
        pos = pos.Add(dir.Mul(amt));
    }
    public void rotateY(float angle){
        Vector3f HAxis = yAxis.Cross(forward).Normalized();

        forward = forward.Rotate(yAxis,angle).Normalized();

        up = forward.Cross(HAxis).Normalized();
    }

    public void rotateX(float angle){
        Vector3f Haxis = yAxis.Cross(forward).Normalized();

        forward = forward.Rotate(yAxis, angle).Normalized();

        up = forward.Cross(Haxis).Normalized();
    }

    public Vector3f getLeft(){
        Vector3f left = forward.Cross(up).Normalized();
        return left;
    }
    public Vector3f getRight(){
        Vector3f left = up.Cross(forward).Normalized();
        return left;
    }

    public Vector3f getPos() {
        return pos;
    }

    public void setPos(Vector3f pos) {
        this.pos = pos;
    }

    public Vector3f getForward() {
        return forward;
    }

    public void setForward(Vector3f forward) {
        this.forward = forward;
    }

    public Vector3f getUp() {
        return up;
    }

    public void setUp(Vector3f up) {
        this.up = up;
    }
}
