package com.harry9137.threedimensiontest.physics;

import com.harry9137.threedimensiontest.render.Vertex;
import com.harry9137.threedimensiontest.render.math.Vector3f;
import org.lwjgl.util.vector.Vector2f;

public class MathHelper {
    //fluidDensity = kg/m cubed
    //velocity = m/s
    //crossSectionalArea = m squared
    public double dragCalc(double fluidDensity, double velocity, double crossSectionalArea){
        return .5 * fluidDensity * Math.pow(velocity, 2) * 1.05 * crossSectionalArea;
    }
    public Vector2f vector3fToRadians(Vector3f vector3f){
        return new Vector2f((float)Math.atan2(vector3f.GetX(), vector3f.GetZ()), (float)Math.atan2(vector3f.GetY(),vector3f.GetX()));
    }
}
