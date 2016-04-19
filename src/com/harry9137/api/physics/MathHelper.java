package com.harry9137.api.physics;

import com.harry9137.api.render.Vertex;
import com.harry9137.api.render.math.Vector3f;
import org.lwjgl.util.vector.Vector2f;

import java.util.ArrayList;

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
    public Vector3f calcRotPos(Vector3f origin, float sideAngle, float topAngle, float dist){
        return null;
    }

    public static Vector3f vecMathToBaked3f(javax.vecmath.Vector3f vector3fOld){
        return new Vector3f(vector3fOld.x, vector3fOld.y, vector3fOld.z);
    }
    public static javax.vecmath.Vector3f baked3fToVecMath(Vector3f oldVec3f){
        return new javax.vecmath.Vector3f(oldVec3f.GetX(), oldVec3f.GetY(), oldVec3f.GetZ());
    }

    public static float[] toFloatArray(Vertex[] vertexes, int[] ints){
        float[] retVal1 = new float[vertexes.length];

        int j = 0;
        for(int i = 1; i < ints.length; i = i + 3){
            retVal1[j] = vertexes[ints[i]].getPos().GetX();
            retVal1[i + 1] = vertexes[ints[i]].getPos().GetY();
            retVal1[j + 2] = vertexes[ints[i]].getPos().GetZ();

            retVal1[j + 3] = vertexes[ints[i + 1]].getPos().GetX();
            retVal1[j + 4] = vertexes[ints[i + 1]].getPos().GetY();
            retVal1[j + 5] = vertexes[ints[i + 1]].getPos().GetZ();

            retVal1[j + 6] = vertexes[ints[i + 2]].getPos().GetX();
            retVal1[j + 7] = vertexes[ints[i + 2]].getPos().GetY();
            retVal1[j + 8] = vertexes[ints[i + 2]].getPos().GetZ();

            j =+ 9;
        }

        return retVal1;
    }

    public static boolean anyGreaterThan(float check, javax.vecmath.Vector3f vector3f){
        if(vector3f.x > check || vector3f.y > check || vector3f.z > check){
            return false;
        }
        return true;
    }

    public static boolean anyGreaterThan(float check, Vector3f vector3f){
        if(vector3f.GetX() > check || vector3f.GetY() > check || vector3f.GetZ() > check){
            return false;
        }
        return true;
    }
}
