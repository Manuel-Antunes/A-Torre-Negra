package com.aTorreNegra.controller;

import com.aTorreNegra.model.Actor;
import com.aTorreNegra.model.World;
import com.aTorreNegra.ui.GeneralMenuScreen;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import connection.ActorDao;

public class MenuScreenController extends InputAdapter {

    private GeneralMenuScreen box;
    private boolean iCanSave = false;
    private World w;
    public MenuScreenController(GeneralMenuScreen box, World w) {
        this.box = box;
        this.w = w;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (!box.isVisible()) {
            return false;
        }
        if (keycode == Keys.UP) {
            return true;
        }
        if (keycode == Keys.DOWN) {
            return true;
        }
        if (keycode == Keys.X || keycode == Keys.LEFT || keycode == Keys.RIGHT) {
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
            if (box.getIndex() == 0) {
                Actor p = box.getInventoryScreen().getActor();
                p.openMenuYes();
                box.getInventoryScreen().setVisible(true);
                box.getInventoryScreen().getUiContainer2().setVisible(true);
            } else if (box.getIndex() == 2) {
                save(w);
                iCanSave = true;
            } else if (box.getIndex() == 3) {
                Actor p = box.getInventoryScreen().getActor();
                p.openMenuYes();
            }
        }
        return false;
    }

    public boolean isiCanSave() {
        return iCanSave;
    }

    public void setiCanSave(boolean iCanSave) {
        this.iCanSave = iCanSave;
    }

    public void save(World w) {
        for (int i = 0; i < w.getMap().getWidth(); i++) {
            for (int j = 0; j < w.getMap().getHeight(); j++) {
                if (w.getMap().getTile(i, j).getActor() != null) {
                    ActorDao daoA = new ActorDao();
                    daoA.atualizar(w.getMap().getTile(i, j).getActor());
                }
            }
        }
    }

}
