package com.aTorreNegra.controller;

import com.aTorreNegra.model.Actor;
import com.aTorreNegra.model.Armor;
import com.aTorreNegra.ui.InventoryScreen;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

public class InvetoryScreenController extends InputAdapter {

    private InventoryScreen box;

    public InvetoryScreenController(InventoryScreen box) {
        this.box = box;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (!box.isVisible()) {
            return false;
        }

        if (keycode == Keys.UP) {
            return true;
        } else if (keycode == Keys.DOWN) {
            return true;
        } else if (keycode == Keys.X || keycode == Keys.LEFT || keycode == Keys.RIGHT) {
            // activate
            return true;
        } else if (keycode == Keys.Z) {
            return true;
        } else if (keycode == Keys.ENTER) {
            return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {

        if (!box.isVisible()) {
            return false;
        }

        if (keycode == Keys.UP) {
            box.moveUp();
            return true;
        } else if (keycode == Keys.DOWN) {
            box.moveDown();
            return true;
        } else if (keycode == Keys.X || keycode == Keys.LEFT || keycode == Keys.RIGHT) {
            Armor a = (Armor) box.getActor().getInventory().getItems().get(box.getIndex());
            if (a.getTipo() == 1) {
                if (box.getActor().getHelmet() == null) {
                    a.setActive(true);
                    box.getActor().setHelmet(a);
                } else {
                    if (box.getActor().getHelmet().getName() == a.getName()) {
                        a.setActive(false);
                        box.getActor().setHelmet(null);
                    } else {
                        a.setActive(true);
                        box.getActor().setHelmet(a);
                    }
                }
            } else if (a.getTipo() == 2) {
                if (box.getActor().getChesplate() == null) {
                    a.setActive(true);
                    box.getActor().setChesplate(a);
                } else {
                    if (box.getActor().getChesplate().getName() == a.getName()) {
                        a.setActive(false);
                        box.getActor().setChesplate(null);
                    } else {
                        a.setActive(true);
                        box.getActor().setChesplate(a);
                    }
                }
            } else if (a.getTipo() == 3) {
                if (box.getActor().getPants() == null) {
                    a.setActive(true);
                    box.getActor().setPants(a);
                } else {
                    if (box.getActor().getPants().getName() == a.getName()) {
                        a.setActive(false);
                        box.getActor().setPants(null);
                    } else {
                        a.setActive(true);
                        box.getActor().setPants(a);
                    }
                }
            }
            return true;
        } else if (keycode == Keys.Z) {
            Actor p = this.box.getActor();
            p.openMenuYes();
            this.box.getUiContainer2().setVisible(false);
            this.box.setVisible(false);
        } else if (keycode == Keys.ENTER) {
            this.box.getUiContainer2().setVisible(false);
            this.box.setVisible(false);
        }
        return false;
    }
}
