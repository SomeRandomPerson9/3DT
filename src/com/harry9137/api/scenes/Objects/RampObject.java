package com.harry9137.api.scenes.Objects;

import com.bulletphysics.collision.broadphase.BroadphaseNativeType;
import com.bulletphysics.collision.shapes.*;
import com.harry9137.api.render.Material;
import com.harry9137.api.render.Mesh;
import com.harry9137.api.render.Transform;
import com.harry9137.api.render.math.Vector3f;
import com.harry9137.api.scenes.Objects.logic.RenderObject;
import com.harry9137.api.util.ResourceLoader;

public class RampObject extends RenderObject {
    public RampObject(Transform sceneTransform){
        super(sceneTransform);
        //super(ResourceLoader.loadMesh("Ramp.obj"), new Material(null, new Vector3f(2f, 1.4f, 0f)), sceneTransform, new Vector3f(15,0,15), new Vector3f(0,0,0), new Vector3f(0,0,0), false);

        //BvhTriangleMeshShape bvhTriangleMeshShape = new BvhTriangleMeshShape(ResourceLoader.getCollisionMesh("Ramp.obj"), true);
    }
}
