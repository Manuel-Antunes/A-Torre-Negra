package com.aTorreNegra.model;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public interface YSortable {

	public float getWorldX();

	public TextureRegion getSprite();

	public float getSizeX();

	public float getSizeY();

	public float getWorldY();

	public String getName();

	public Armor getHelmet();

	public Armor getChesplate();

	public Armor getPants();
}
