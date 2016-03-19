package com.harry9137.api.scenes.Objects.logic;

import com.bulletphysics.collision.dispatch.CollisionObject;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.dynamics.RigidBodyConstructionInfo;
import com.bulletphysics.linearmath.DefaultMotionState;
import com.bulletphysics.linearmath.MotionState;

import javax.vecmath.Vector3f;

public class RigidBodyBuilder {
    public static RigidBody build(float mass, CollisionShape collisionShape, MotionState motionState, float restitution){
        RigidBodyConstructionInfo bodyConstructionInfo = new RigidBodyConstructionInfo(mass, motionState, collisionShape, new Vector3f(0,0,0));
        bodyConstructionInfo.restitution = restitution;
        RigidBody returnBody = new RigidBody(bodyConstructionInfo);
        returnBody.setActivationState(CollisionObject.DISABLE_DEACTIVATION);
        return returnBody;
    }
}
