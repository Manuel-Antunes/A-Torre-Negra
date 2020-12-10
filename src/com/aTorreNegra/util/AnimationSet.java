package com.aTorreNegra.util;

import java.util.HashMap;
import java.util.Map;

import com.aTorreNegra.model.DIRECTION;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationSet {

	private Map<DIRECTION, Animation> walking;
	private Map<DIRECTION, TextureRegion> standing;
	private Map<DIRECTION, Animation> attacking;
	private Map<DIRECTION, Animation> dyeing;

	public AnimationSet(Animation walkNorte, Animation walkSul, Animation walkOeste, Animation walkLeste,
			Animation attackNorte, Animation attackSul, Animation attackLeste, Animation attackOeste,
			Animation deadNorte, Animation deadSul, Animation deadLeste, Animation deadOeste, TextureRegion standNorte,
			TextureRegion standSul, TextureRegion standLeste, TextureRegion standOeste) {

		walking = new HashMap<DIRECTION, Animation>();
		walking.put(DIRECTION.NORTE, walkNorte);
		walking.put(DIRECTION.SUL, walkSul);
		walking.put(DIRECTION.OESTE, walkLeste);
		walking.put(DIRECTION.LESTE, walkOeste);
		standing = new HashMap<DIRECTION, TextureRegion>();
		standing.put(DIRECTION.NORTE, standNorte);
		standing.put(DIRECTION.SUL, standSul);
		standing.put(DIRECTION.LESTE, standLeste);
		standing.put(DIRECTION.OESTE, standOeste);
		attacking = new HashMap<DIRECTION, Animation>();
		attacking.put(DIRECTION.NORTE, attackNorte);
		attacking.put(DIRECTION.SUL, attackSul);
		attacking.put(DIRECTION.LESTE, attackLeste);
		attacking.put(DIRECTION.OESTE, attackOeste);
		dyeing = new HashMap<DIRECTION, Animation>();
		dyeing.put(DIRECTION.NORTE, deadNorte);
		dyeing.put(DIRECTION.SUL, deadSul);
		dyeing.put(DIRECTION.LESTE, deadLeste);
		dyeing.put(DIRECTION.OESTE, deadOeste);
	}

    public AnimationSet() {
    }

	public Animation getWalking(DIRECTION dir) {
		return walking.get(dir);
	}

	public TextureRegion getStanding(DIRECTION dir) {
		return standing.get(dir);
	}

	public Animation getAttacking(DIRECTION dir) {
		return attacking.get(dir);
	}

	public Animation getDyeing(DIRECTION dir) {
		return dyeing.get(dir);
	}
}
