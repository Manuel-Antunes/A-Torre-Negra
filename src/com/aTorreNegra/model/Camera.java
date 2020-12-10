package com.aTorreNegra.model;

public class Camera {

    private float cameraX = 0f;
    private float cameraY = 0f;

    public void update(float newCamx, float newCamY) {
        this.cameraX = newCamx;
        this.cameraY = newCamY;
    }

    public float getCameraX() {
        return cameraX;
    }

    public float getCameraY() {
        return cameraY;
    }

}
