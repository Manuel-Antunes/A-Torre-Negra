package com.aTorreNegra.ui;

import com.aTorreNegra.model.Actor;
import com.aTorreNegra.model.Armor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import java.util.ArrayList;
import java.util.List;

public class InventoryScreen extends Table {

    private int selectorIndex = 0;
    private Actor actor;
    private Table uiContainer;
    private Table uiContainer2;
    private Label title;
    private List<Label> options = new ArrayList<Label>();
    private List<Image> arrows = new ArrayList<Image>();

    public InventoryScreen(Skin skin, Actor actor) {
        super(skin);
        this.setBackground("dialoguebox");
        uiContainer = new Table();
        uiContainer2 = new Table(skin);
        uiContainer2.setBackground("dialoguebox");
        this.add(uiContainer).pad(5);
        this.actor = actor;
        this.setVisible(false);
        uiContainer.add(new Label("     ", getSkin())).expand().align(Align.left).space(5f);
        uiContainer.add(new Label("                    Items", getSkin())).expand()
                .align(Align.left)
                .space(8f);
        uiContainer.row();
        createLabels();
        uiContainer2.add(new Image(actor.getInventory().getItems().get(selectorIndex).getRegion()));
        uiContainer.add(new Label("     ", getSkin())).expand().align(Align.left).space(5f);
        uiContainer.add(new Label("      Press 'X' to select an item", getSkin())).expand()
                .align(Align.left)
                .space(8f);
        uiContainer.row();
    }

    public void update(Actor actor) {
        this.actor = actor;
    }

    private void createLabels() {
        
        for (int i = 0; i < actor.getInventory().getItems().size(); i++) {    
            String tiponome = null;
            Armor a = (Armor) actor.getInventory().getItems().get(i);
            if (a.getTipo() == 1) {
                tiponome = "Elmo";
            } else if (a.getTipo() == 2) {
                tiponome = "Peitoral";
            } else if (a.getTipo() == 3) {
                tiponome = "Calca";
            } else if (tiponome == null) {
                tiponome = "Miscelaneus";
            }
            Label label = new Label("NOME: " + a.getName() + "/TIPO: " + tiponome + "/VALOR: " + a.getValor(), this.getSkin());
            options.add(label);
            Image preview = new Image(a.getRegion());
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
        }
    }

    public void moveUp() {
        uiContainer2.clear();
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
        uiContainer2.add(new Image(actor.getInventory().getItems().get(selectorIndex).getRegion()));

    }

    public void moveDown() {
        uiContainer2.clear();
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
        uiContainer2.add(new Image(actor.getInventory().getItems().get(selectorIndex).getRegion()));
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

    public Actor getActor() {
        return actor;
    }

    public Table getUiContainer2() {
        return uiContainer2;
    }

}
