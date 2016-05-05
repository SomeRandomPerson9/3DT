package com.harry9137.api.render;

import com.harry9137.api.main.Input;
import com.harry9137.api.util.Time;
import com.harry9137.api.render.math.Vector2f;
import com.harry9137.api.render.math.Vector3f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class Camera
{
    public static final Vector3f yAxis = new Vector3f(0,1,0);

    private Vector3f pos;
    private Vector3f forward;
    private Vector3f up;

    public Camera()
    {
        this(new Vector3f(0,0,0), new Vector3f(0,0,1), new Vector3f(0,1,0));
    }

    public Camera(Vector3f pos, Vector3f forward, Vector3f up)
    {
        this.pos = pos;
        this.forward = forward.Normalized();
        this.up = up.Normalized();
    }

    boolean mouseLocked = false;
    public Vector2f centerPosition = new Vector2f(Window.getWidth()/2, Window.getHeight()/2);

    public void input()
    {
        centerPosition = new Vector2f(Window.getWidth()/2, Window.getHeight()/2);
        float sensitivity = 0.1f;
        float movAmt = (float)(10 * Time.getDelta());
//		float rotAmt = (float)(100 * Time.getDelta());

        if(Input.getKey(Input.KEY_ESCAPE))
        {
            Input.setCursor(true);
            mouseLocked = false;
        }
        if(Input.getMouseDown(0))
        {
            Input.setMousePosition(centerPosition);
            Input.setCursor(false);
            mouseLocked = true;
        }

        if(Input.getKey(Input.KEY_W))
            move(getForward(), movAmt);
        if(Input.getKey(Input.KEY_S))
            move(getForward(), -movAmt);
        if(Input.getKey(Input.KEY_A))
            move(getLeft(), movAmt);
        if(Input.getKey(Input.KEY_D))
            move(getRight(), movAmt);

        if(mouseLocked)
        {
            Vector2f deltaPos = Input.getMousePosition().Sub(centerPosition);

            boolean rotY = deltaPos.GetX() != 0;
            boolean rotX = deltaPos.GetY() != 0;

            if(rotY) {
                rotateY(deltaPos.GetX() * sensitivity);
            }
            if(rotX){
                rotateX(-deltaPos.GetY() * sensitivity);
            }

            if(rotY || rotX)
                Input.setMousePosition(new Vector2f(Window.getWidth()/2, Window.getHeight()/2));
        }

//		if(Input.getKey(Input.KEY_UP))
//			rotateX(-rotAmt);
//		if(Input.getKey(Input.KEY_DOWN))
//			rotateX(rotAmt);
//		if(Input.getKey(Input.KEY_LEFT))
//			rotateY(-rotAmt);
//		if(Input.getKey(Input.KEY_RIGHT))
//			rotateY(rotAmt);
    }

    public void move(Vector3f dir, float amt)
    {
        pos = pos.Add(dir.Mul(amt));
    }

    public void rotateY(float angle)
    {
        Vector3f Haxis = yAxis.cross(forward).Normalized();

        forward = forward.Rotate(yAxis, angle).Normalized();

        up = forward.cross(Haxis).Normalized();
    }

    public void rotateX(float angle)
    {
        Vector3f Haxis = yAxis.cross(forward).Normalized();

        forward = forward.Rotate(Haxis, angle).Normalized();

        up = forward.cross(Haxis).Normalized();
    }

    public void applyPerspectiveMatrix() {
        GL11.glPushAttrib(GL11.GL_TRANSFORM_BIT);
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GLU.gluPerspective(70f, Window.getWidth()/Window.getHeight(), 0.1f, 110f);
        GL11.glPopAttrib();
    }

    public Vector3f getLeft()
    {
        return forward.cross(up).Normalized();
    }

    public Vector3f getRight()
    {
        return up.cross(forward).Normalized();
    }

    public Vector3f getPos()
    {
        return pos;
    }

    public void setPos(Vector3f pos)
    {
        this.pos = pos;
    }

    public Vector3f getForward()
    {
        return forward;
    }

    public void setForward(Vector3f forward)
    {
        this.forward = forward;
    }

    public Vector3f getUp()
    {
        return up;
    }

    public void setUp(Vector3f up)
    {
        this.up = up;
    }
}
