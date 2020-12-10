package com.aTorreNegra.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import java.util.ArrayList;
import java.util.List;

public class GeneralMenuScreen extends Table {
    private int selectorIndex = 0;
    private List<Label> options = new ArrayList<Label>();
    private List<Image> arrows = new ArrayList<Image>();
    private InventoryScreen inventoryScreen;
    private Table uiContainer;

    public GeneralMenuScreen(Skin skin, InventoryScreen inventoryScreen) {
        super(skin);
        this.setBackground("dialoguebox");
        this.uiContainer = new Table();
        this.add(uiContainer).pad(5);
        this.inventoryScreen = inventoryScreen;
        createLabels();
    }

    public void createLabels() {
        Label label = new Label("Inventory", this.getSkin());
        options.add(label);
        Image selectorLabel = new Image(this.getSkin(), "arrow");
        selectorLabel.setScaling(Scaling.none);
        arrows.add(selectorLabel);
        selectorLabel.setVisible(false);
        uiContainer.add(selectorLabel).expand().align(Align.left).space(5f);
        uiContainer.add(label)
                .expand()
                .align(Align.left)
                .space(8f);
        uiContainer.row();
        Label label2 = new Label("Options", this.getSkin());
        options.add(label2);
        Image selectorLabel2 = new Image(this.getSkin(), "arrow");
        selectorLabel2.setScaling(Scaling.none);
        arrows.add(selectorLabel2);
        selectorLabel2.setVisible(false);
        uiContainer.add(selectorLabel2).expand().align(Align.left).space(5f);
        uiContainer.add(label2)
                .expand()
                .align(Align.left)
                .space(8f);
        uiContainer.row();

        Label label3 = new Label("Save", this.getSkin());
        options.add(label3);
        Image selectorLabel3 = new Image(this.getSkin(), "arrow");
        selectorLabel3.setScaling(Scaling.none);
        arrows.add(selectorLabel3);
        selectorLabel3.setVisible(false);
        uiContainer.add(selectorLabel3).expand().align(Align.left).space(5f);
        uiContainer.add(label3)
                .expand()
                .align(Align.left)
                .space(8f);
        uiContainer.row();

        Label label4 = new Label("Exit", this.getSkin());
        options.add(label4);
        Image selectorLabel4 = new Image(this.getSkin(), "arrow");
        selectorLabel4.setScaling(Scaling.none);
        arrows.add(selectorLabel4);
        selectorLabel4.setVisible(false);
        uiContainer.add(selectorLabel4).expand().align(Align.left).space(5f);
        uiContainer.add(label4)
                .expand()
                .align(Align.left)
                .space(8f);
        uiContainer.row();
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

    public void clearChoices() {
        uiContainer.clearChildren();
        options.clear();
        arrows.clear();
        selectorIndex = 0;
    }

    public int getIndex() {
        return selectorIndex;
    }

    public int getAmount() {
        return options.size();
    }

    public List<Label> getOptions() {
        return options;
    }

    public List<Image> getArrows() {
        return arrows;
    }

    public InventoryScreen getInventoryScreen() {
        return inventoryScreen;
    }
    
    
}
