package com.aTorreNegra;

import com.aTorreNegra.screen.AbstractScreen;
import com.aTorreNegra.screen.GameScreen;
import com.aTorreNegra.screen.MainMenuScreen;
import com.aTorreNegra.util.SkinGenerator;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class TorreNegra extends Game {

    private String nome = "principal";
    private AssetManager assetManager;
    private Skin skin;
    private AbstractScreen screen;

    @Override
    public void create() {

        assetManager = new AssetManager();
        assetManager.load("div/packed/tile/textures.atlas", TextureAtlas.class);
        assetManager.load("div/packed/ui/uipack.atlas", TextureAtlas.class);
        assetManager.load("div/font/small_letters_font.fnt", BitmapFont.class);
        assetManager.finishLoading();
        skin = SkinGenerator.generateSkin(assetManager);
        screen = new MainMenuScreen(this);
        this.setScreen(screen);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        super.render();
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public Skin getSkin() {

        return skin;
    }

    public String getNome() {
        return nome;
    }
}
