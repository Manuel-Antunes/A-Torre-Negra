package com.aTorreNegra.screen;

import com.aTorreNegra.Settings;
import com.aTorreNegra.TorreNegra;
import com.aTorreNegra.controller.MainMenuScreenController;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import java.util.ArrayList;

public class MainMenuScreen extends AbstractScreen {

    private SpriteBatch batch;
    private Texture raj;
    private MainMenuScreenController controller;
    private Label newGame;
    private Label options;
    private int selectorIndex = 0;
    private Label credits;
    private Label load;
    private Label exit;
    private Stage selectStage = new Stage();
    private Table uiContainer2 = new Table();
    private ArrayList<Image> arrows = new ArrayList<Image>();
    private Table ScreenContainer = new Table();

    public MainMenuScreen(TorreNegra app) {

        super(app);
        uiContainer2.setFillParent(true);
        batch = new SpriteBatch();
        raj = new Texture("div/bigbangtheoryraj-bf9a.png");
        newGame = new Label("NEW GAME", app.getSkin());
        load = new Label("LOAD GAME", app.getSkin());
        options = new Label("OPTIONS", app.getSkin());
        credits = new Label("CREDITS", app.getSkin());
        exit = new Label("EXIT", app.getSkin());
        cadastrar();
        uiContainer2.add(newGame).expand()
                .align(Align.left)
                .space(8f);
        uiContainer2.row();
        cadastrar();
        uiContainer2.add(load).expand()
                .align(Align.left)
                .space(8f);
        uiContainer2.row();
        cadastrar();
        uiContainer2.add(options).expand()
                .align(Align.left)
                .space(8f);
        uiContainer2.row();
        cadastrar();
        uiContainer2.add(credits).expand()
                .align(Align.left)
                .space(8f);
        uiContainer2.row();
        cadastrar();
        uiContainer2.add(exit).expand()
                .align(Align.left)
                .space(8f);
        uiContainer2.row();
        uiContainer2.align(Align.bottom).pad(60);
        selectStage.addActor(uiContainer2);
        controller = new MainMenuScreenController(this);
    }

    @Override
    public void dispose() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(raj, 1, 1, Settings.ScreenWidth, Settings.ScreenHeight);
        batch.end();
        selectStage.draw();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void resume() {
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(controller);

    }

    public void cadastrar() {
        Image selectorLabel = new Image(getApp().getSkin(), "arrow");
        selectorLabel.setScaling(Scaling.none);
        arrows.add(selectorLabel);
        if (arrows.size() != 1) {
            selectorLabel.setVisible(false);
        }
        uiContainer2.add(selectorLabel).pad(5);
    }

    public void moveUp() {
        selectorIndex--;
        if (selectorIndex < 0) {
            selectorIndex = 0;
        }
        for (int i = 0; i < arrows.size(); i++) {
            if (i == selectorIndex) {
                arrows.get(i).setVisible(true);
            } else {
                arrows.get(i).setVisible(false);
            }
        }
    }

    public void moveDown() {
        selectorIndex++;
        if (selectorIndex >= arrows.size()) {
            selectorIndex = arrows.size() - 1;
        }
        for (int i = 0; i < arrows.size(); i++) {
            if (i == selectorIndex) {
                arrows.get(i).setVisible(true);
            } else {
                arrows.get(i).setVisible(false);
            }
        }
    }

    public int getSelectorIndex() {
        return selectorIndex;
    }
    
}
