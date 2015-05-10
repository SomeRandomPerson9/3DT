package com.harry9137.threedimensiontest.scenes;

import com.harry9137.threedimensiontest.util.Video;

public class Scene2DVideo extends SceneBase {
    private Video video;
    Scene2DVideo(){
        this.sceneType = SceneType.TWO_DIMENSIONAL;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }
}
