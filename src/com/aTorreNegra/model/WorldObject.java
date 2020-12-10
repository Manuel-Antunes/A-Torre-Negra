package com.aTorreNegra.model;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;

public class WorldObject implements YSortable {
    protected String name;
    protected float sizeX, sizeY;
    protected int x, y;
    protected List<GridPoint2> tiles;
    protected boolean walkable;

    protected TextureRegion texture;

    protected Animation animation;
    float animationTimer;

    public WorldObject(int x, int y, TextureRegion texture, float sizeX, float sizeY, GridPoint2[] tiles, String name) {
        this.x = x;
        this.y = y;
        this.texture = texture;
        this.sizeX = sizeX;
        this.name = name;
        this.sizeY = sizeY;
        texture.getRegionWidth();
        texture.getRegionHeight();
        this.tiles = new ArrayList<GridPoint2>();
        for (GridPoint2 p : tiles) {
            this.tiles.add(p);
        }
        this.walkable = true;
    }

    public WorldObject(int x, int y, boolean walkable, TextureRegion texture, float sizeX, float sizeY, GridPoint2 tile,
            String name) {
        this.x = x;
        this.y = y;
        this.texture = texture;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.name = name;
        this.tiles = new ArrayList<GridPoint2>();
        this.tiles.add(tile);
        this.walkable = walkable;
    }

    public WorldObject(int x, int y, boolean walkable, TextureRegion texture, float sizeX, float sizeY,
            GridPoint2[] tiles, String name) {
        this.x = x;
        this.y = y;
        this.texture = texture;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.name = name;
        this.tiles = new ArrayList<GridPoint2>();
        for (GridPoint2 p : tiles) {
            this.tiles.add(p);
        }
        this.walkable = walkable;
    }

    public WorldObject(int x, int y, boolean walkable, Animation animation, float sizeX, float sizeY,
            GridPoint2[] tiles, String name) {
        this.x = x;
        this.y = y;
        this.animation = animation;
        this.animationTimer = 0f;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.name = name;
        this.tiles = new ArrayList<GridPoint2>();
        for (GridPoint2 p : tiles) {
            this.tiles.add(p);
        }
        this.walkable = walkable;
    }

    public WorldObject(int x, int y, boolean walkable, Animation animation, float sizeX, float sizeY, GridPoint2 tile,
            String name) {
        this.x = x;
        this.y = y;
        this.animation = animation;
        this.animationTimer = 0f;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.name = name;
        this.tiles = new ArrayList<GridPoint2>();
        this.tiles.add(tile);
        this.walkable = walkable;
    }

    public void update(float delta) {
        if (animation != null) {
            animationTimer += delta;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public float getSizeX() {
        return sizeX;
    }

    public float getSizeY() {
        return sizeY;
    }

    public boolean isWalkable() {
        return walkable;
    }

    public boolean containsTile(int x, int y) {
        for (GridPoint2 point : tiles) {
            if (point.x + this.x == x && point.y + this.y == y) {
                return true;
            }
        }
        return false;
    }

    public TextureRegion getSprite() {
        if (texture != null) {
            return texture;
        } else {
            return animation.getKeyFrame(animationTimer);
        }
    }

    public List<GridPoint2> getTiles() {
        return tiles;
    }

    public String getName() {
        return name;
    }

    @Override
    public float getWorldX() {
        return x;
    }

    @Override
    public float getWorldY() {
        return y;
    }

    @Override
    public Armor getHelmet() {
        return null;
    }

    @Override
    public Armor getChesplate() {
        return null;
    }

    @Override
    public Armor getPants() {
        return null;
    }
}
