package com.harry9137.api.scenes.Objects;

import com.bulletphysics.collision.shapes.StaticPlaneShape;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.linearmath.DefaultMotionState;
import com.bulletphysics.linearmath.Transform;
import com.harry9137.api.render.Material;
import com.harry9137.api.render.Mesh;
import com.harry9137.api.scenes.Objects.logic.RenderObject;
import com.harry9137.api.scenes.Objects.logic.RigidBodyBuilder;
import com.harry9137.api.util.ResourceLoader;

import javax.vecmath.Matrix3f;
import javax.vecmath.Matrix4f;
import javax.vecmath.Quat4f;
import javax.vecmath.Vector3f;
import java.util.HashMap;

public class GroundObject extends RenderObject {
    public GroundObject(com.harry9137.api.render.Transform sceneTransform) {
        super(sceneTransform);

        Object[] mRes = ResourceLoader.loadMeshs("FloorTests.obj");
        this.setMeshs((HashMap<String, Mesh>)mRes[0]);

        HashMap<String, Material> materialHashMap = new HashMap<>();
        materialHashMap.put("Material.001", new Material(null, new com.harry9137.api.render.math.Vector3f(1.19f,2,2)));
        this.setMaterials(materialHashMap);

        this.setRigidBodyShape(RigidBodyBuilder.build(0, new StaticPlaneShape(new Vector3f(0,1,0), 0.25f), new DefaultMotionState(new Transform(new Matrix4f(new Quat4f(0,0,0,1), new Vector3f(0,0,0), 1.0f))), 0.25f));
        this.setObjName("Floor");

        this.setRigidBody(true);
    }
}
