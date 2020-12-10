package com.aTorreNegra.controller;

import com.aTorreNegra.dialogue.Dialogue;
import com.aTorreNegra.model.Actor;
import com.aTorreNegra.model.Armor;
import com.aTorreNegra.model.DIRECTION;
import com.aTorreNegra.model.World;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

public class PlayerController extends InputAdapter {

    private Actor player;
    private Dialogue actNow;
    private boolean[] buttonPressWalk;
    private boolean buttonPressAttack;
    private float[] buttonTimer;
    private float WALK_REFACE_THRESHOLD = 0.07f;

    public PlayerController(Actor p) {
        this.player = p;
        buttonPressWalk = new boolean[DIRECTION.values().length];
        buttonPressWalk[DIRECTION.NORTE.ordinal()] = false;
        buttonPressWalk[DIRECTION.SUL.ordinal()] = false;
        buttonPressWalk[DIRECTION.LESTE.ordinal()] = false;
        buttonPressWalk[DIRECTION.OESTE.ordinal()] = false;
        buttonTimer = new float[DIRECTION.values().length];
        buttonTimer[DIRECTION.NORTE.ordinal()] = 0f;
        buttonTimer[DIRECTION.SUL.ordinal()] = 0f;
        buttonTimer[DIRECTION.LESTE.ordinal()] = 0f;
        buttonTimer[DIRECTION.OESTE.ordinal()] = 0f;
        buttonPressAttack = false;
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Keys.UP) {
            buttonPressWalk[DIRECTION.NORTE.ordinal()] = true;
        }
        if (keycode == Keys.DOWN) {
            buttonPressWalk[DIRECTION.SUL.ordinal()] = true;

        }
        if (keycode == Keys.LEFT) {
            buttonPressWalk[DIRECTION.OESTE.ordinal()] = true;

        }
        if (keycode == Keys.RIGHT) {
            buttonPressWalk[DIRECTION.LESTE.ordinal()] = true;
        }
        if (keycode == Keys.X) {
            player.attack();
        }
        if (keycode == Keys.ENTER) {
            player.openMenuYes();
        }
        if (keycode == Keys.Z) {
            actNow = player.interact();
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Keys.UP) {
            relisedDirection(DIRECTION.NORTE);
        }
        if (keycode == Keys.DOWN) {
            relisedDirection(DIRECTION.SUL);
        }
        if (keycode == Keys.LEFT) {
            relisedDirection(DIRECTION.OESTE);
        }
        if (keycode == Keys.RIGHT) {
            relisedDirection(DIRECTION.LESTE);
        }
        return false;
    }

    public void update(float delta, World world) {
        if (buttonPressWalk[DIRECTION.NORTE.ordinal()]) {
            updateDirection(DIRECTION.NORTE, delta);
            return;
        }
        if (buttonPressWalk[DIRECTION.SUL.ordinal()]) {
            updateDirection(DIRECTION.SUL, delta);
            return;
        }
        if (buttonPressWalk[DIRECTION.OESTE.ordinal()]) {
            updateDirection(DIRECTION.OESTE, delta);
            return;
        }
        if (buttonPressWalk[DIRECTION.LESTE.ordinal()]) {
            updateDirection(DIRECTION.LESTE, delta);
            return;
        }
        if (world.getPlayer() != player) {
            player = world.getPlayer();
        }
    }

    public Dialogue updateAgain() {
        Dialogue k = actNow;
        if (k != null) {
            actNow = null;
            return k;
        }
        return null;
    }

    private void updateDirection(DIRECTION dir, float delta) {
        buttonTimer[dir.ordinal()] += delta;
        considerMove(dir);
    }

    private void relisedDirection(DIRECTION dir) {
        buttonPressWalk[dir.ordinal()] = false;
        considerReface(dir);
        buttonTimer[dir.ordinal()] = 0f;
    }

    private void considerMove(DIRECTION dir) {
        if (buttonTimer[dir.ordinal()] > WALK_REFACE_THRESHOLD) {
            player.move(dir);
        }
    }

    private void considerReface(DIRECTION dir) {
        if (buttonTimer[dir.ordinal()] < WALK_REFACE_THRESHOLD) {
            player.reface(dir);
        }
    }
}
