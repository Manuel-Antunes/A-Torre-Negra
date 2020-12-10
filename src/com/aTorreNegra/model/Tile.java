package com.aTorreNegra.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import connection.ActorDao;

public class Tile {
    private int foreignMap;
    private TileMap mymap;
    private ActorDao daoA = new ActorDao();
    private TERRAIN terrain;
    private Actor actor;
    private WorldObject object;
    private TeleportTile tt;
    private int x;
    private int y;
    private TextureRegion region;

    public Tile(TERRAIN terrain, int id) {
        this.terrain = terrain;
    }
    public Tile(int x, int y,int foreignId,TileMap mymap,String endereco) {
        this.x = x;
        this.y = y;
        this.foreignMap = foreignId;
        this.mymap = mymap;
        actor = daoA.consultaTileMap(this);
        this.region = createRegion(new Texture(endereco));
    }
    
    public TERRAIN getTerrain() {
        return terrain;
    }

    public Actor getActor() {
        return actor;
    }

    public WorldObject getObject() {
        return object;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public void setObject(WorldObject o) {
        this.object = o;

    }

    public TeleportTile getTt() {
        return tt;
    }

    public void setTt(TeleportTile tt) {
        this.tt = tt;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getForeignMap() {
        return foreignMap;
    }

    public TileMap getMymap() {
        return mymap;
    }
    
    public TextureRegion createRegion(Texture texture) {
        TextureRegion region = new TextureRegion();
        region.setRegion(texture);
        return region;
    }

    public TextureRegion getRegion() {
        return region;
    }
    
}
