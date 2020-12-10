package com.aTorreNegra.model;

import java.util.List;
import java.util.ArrayList;

import com.badlogic.gdx.math.GridPoint2;
import connection.TileMapDao;

public class World {

    private TileMap map;
    private List<Actor> actors;
    private List<WorldObject> objects;
    private Actor player;
    private int id;
    private boolean active;
    private TileMapDao dao = new TileMapDao();

    public World(int width, int height, int id, boolean active) {
        this.map = new TileMap(width, height);
        actors = new ArrayList<Actor>();
        objects = new ArrayList<WorldObject>();
        this.id = id;
        this.active = active;
    }

    public World(int id, boolean active) {
        actors = new ArrayList<Actor>();
        objects = new ArrayList<WorldObject>();
        this.id = id;
        this.map = dao.consultaTileMap(id);
        this.active = active;
    }

    public World() {

    }

    public void addActor(Actor a) {
        map.getTile(a.getX(), a.gety()).setActor(a);
        actors.add(a);
    }

    public void addObject(WorldObject o) {
        for (int i = 0; i < o.getTiles().size(); i++) {
            map.getTile(o.getTiles().get(i).x, o.getTiles().get(i).y).setObject(o);
            objects.add(o);
        }
        objects.add(o);
    }

    public void update(float delta) {
        for (Actor a : actors) {
            a.update(delta);
        }
        for (WorldObject o : objects) {
            o.update(delta);
        }
    }

    public TileMap getMap() {
        return map;
    }

    public void getActor() {
        for (int i = 0; i < this.actors.size(); i++) {
            System.out.println(this.actors.get(i).getName());
        }
    }

    public Actor getPlayer() {
        return player;
    }

    public void setPlayer(Actor player) {
        this.player = player;
    }

    public int getId() {
        return id;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public List<WorldObject> getObjects() {
        return objects;
    }

    public boolean isActive() {
        return active;
    }

    public void setMap(TileMap map) {
        this.map = map;
    }

}
