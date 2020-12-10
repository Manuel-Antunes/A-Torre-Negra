package com.aTorreNegra.model;

import com.aTorreNegra.screen.GameScreen;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.GridPoint2;
import java.util.List;

public class TeleportTile extends WorldObject {

    private World world;
    private World oldWorld;
    private GameScreen screen;
    private int worldX, worldY;
    int destX, destY;
    float ENTER_TIME;
    float enterTimer;

    private enum STATE {
        CLOSED, OPENING
    }

    private STATE state;

    public TeleportTile(int x, int y, boolean walkable, TextureRegion texture, float sizeX, float sizeY,
            GridPoint2 tile, String name, GameScreen screen, World world, World oldWorld, int destX, int destY) {
        super(x, y, walkable, texture, sizeX, sizeY, tile, name);
        this.world = world;
        this.screen = screen;
        this.destX = destX;
        this.destY = destY;
        this.oldWorld = oldWorld;
        this.worldX = x;
        this.worldY = y;
    }

    public TeleportTile(int x, int y, boolean walkable, Animation animation, float sizeX, float sizeY, GridPoint2 tile,
            String name, GameScreen screen, World world, World oldWorld, int destX, int destY, float enterTimer) {
        super(x, y, walkable, animation, sizeX, sizeY, tile, name);
        animationTimer = 0f;
        this.world = world;
        this.screen = screen;
        this.destX = destX;
        this.destY = destY;
        this.oldWorld = oldWorld;
        this.worldX = x;
        this.worldY = y;
        this.ENTER_TIME = enterTimer;
    }

    @Override
    public void update(float delta) {

        if (oldWorld.getMap().getTile(worldX, worldY).getActor() != null) {
            animationTimer += delta;
            enterTimer += delta;
            if (animationTimer > ENTER_TIME) {
                enterTimer = 0;
            }
            while (enterTimer == 0) {
                Actor player = oldWorld.getMap().getTile(x, y).getActor();
                screen.getWorld().getMap().getTile(x, y).setActor(null);
                Actor NowPlayer = new Actor(world.getMap(), destX, destY, player.getAnimationSet(), player.getName(),
                        player.getSizeX(), player.getSizeY(), player.getHpat(), player.getDano(), player.getInventory(), player.getCode());
                NowPlayer.setHelmet(player.getHelmet());
                NowPlayer.setPants(player.getPants());
                NowPlayer.setChesplate(player.getChesplate());
                world.addActor(NowPlayer);
                screen.setWorld(world);
                world.setPlayer(NowPlayer);
                screen.screenUpdate();
                screen.saveSprites();
                enterTimer = delta;
                animationTimer = 0;
            }
        } else {

        }
    }

    public World getWorld() {
        return world;
    }

}
