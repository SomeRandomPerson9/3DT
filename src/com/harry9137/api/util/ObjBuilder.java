package com.harry9137.api.util;

import com.harry9137.api.render.Material;
import com.harry9137.api.render.Mesh;
import com.harry9137.api.scenes.Objects.logic.RenderObject;
import com.harry9137.api.render.Transform;

import java.util.HashMap;

public class ObjBuilder {
    public static RenderObject build(Transform defTransform, String filePath, String name){
        RenderObject retObj = new RenderObject(defTransform);
        Object[] objRes = ResourceLoader.loadMeshs(filePath);
        retObj.setMeshs((HashMap<String, Mesh>) objRes[0]);
        retObj.setMaterials((HashMap<String, Material>) objRes[1]);
        retObj.setObjName(name);
        return retObj;
    }
}
