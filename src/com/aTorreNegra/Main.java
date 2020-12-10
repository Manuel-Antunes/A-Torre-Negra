/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aTorreNegra;

/**
 *
 * @author Manuel
 */
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "A Torre Negra";
        config.width = Settings.ScreenWidth;
        config.height = Settings.ScreenHeight;
        config.vSyncEnabled = true;
        new LwjglApplication(new TorreNegra(), config);
    }
}
