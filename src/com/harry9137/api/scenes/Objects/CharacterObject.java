package com.harry9137.api.scenes.Objects;

import com.bulletphysics.collision.shapes.BoxShape;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.collision.shapes.SphereShape;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.linearmath.DefaultMotionState;
import com.bulletphysics.linearmath.MotionState;
import com.bulletphysics.linearmath.Transform;
import com.harry9137.api.render.Material;
import com.harry9137.api.render.Mesh;
import com.harry9137.api.scenes.Objects.logic.RenderObject;
import com.harry9137.api.scenes.Objects.logic.RigidBodyBuilder;
import com.harry9137.api.util.ResourceLoader;

import javax.vecmath.Matrix4f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import java.util.HashMap;

public class CharacterObject extends RenderObject {

    private static final Transform DEFAULT_BALL_TRANSFORM = new Transform(new Matrix4f(new Quat4f(0, 0, 0, 1), new Vector3f(0, 35, 0), 1.0f));

    public CharacterObject(com.harry9137.api.render.Transform sceneTransform) {
        super(sceneTransform);

        Object[] mRes = ResourceLoader.loadMeshs("Chara.obj");
        this.setMeshs((HashMap<String, Mesh>) mRes[0]);

        HashMap<String, Material> materialHashMap = new HashMap<>();
        materialHashMap.put("None", new Material(null, new com.harry9137.api.render.math.Vector3f(1,0,2)));
        this.setMaterials(materialHashMap);
        this.setLocation(new com.harry9137.api.render.math.Vector3f(0,1,0));
        this.setPhys(true);

        CollisionShape ballShape = new BoxShape(new Vector3f(2.0f, 2.0f, 2.0f));
        MotionState ballMotionState = new DefaultMotionState(DEFAULT_BALL_TRANSFORM);
        Vector3f ballInertia = new Vector3f(0, 0, 0);
        ballShape.calculateLocalInertia(2.5f, ballInertia);

        this.setRigidBodyShape(RigidBodyBuilder.build(2.5f, ballShape, ballMotionState, 0.5f));
        this.setObjName("Chara");
        this.setRigidBody(true);
        this.getRigidBodyShape().setFriction(this.getRigidBodyShape().getFriction() + 1f);
    }
}
