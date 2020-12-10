package com.aTorreNegra.model;

import connection.TileDao;
import java.util.ArrayList;

public class TileMap {

    private int width, height;
    private Tile[][] tiles;
    private int id;
    private TileDao dao = new TileDao();
    private ArrayList<Tile> tilelist;
    public TileMap(int width, int height) {
        this.width = width;
        this.height = height;
        tiles = new Tile[width][height];
        int z = 0;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (Math.random() < 0.25d) {
                    tiles[x][y] = new Tile(TERRAIN.GRASS_1,z);
                    z++;
                } else {
                    tiles[x][y] = new Tile(TERRAIN.GRASS_2,z);
                    z++;
                }
            }
        }
    }
    public TileMap(int width, int height, int id) {
        this.width = width;
        this.height = height;
        tiles = new Tile[width][height];
        this.id = id;
        tilelist = dao.listarClientes(this);
        for (int i = 0; i < tilelist.size(); i++) {
            tiles[tilelist.get(i).getX()-1][tilelist.get(i).getY()-1] = tilelist.get(i);
        }
    }
    public TileMap(){
        
    }

    public Tile getTile(int x, int y) {
        return tiles[x][y];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getId() {
        return id;
    }

}
