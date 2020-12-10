package com.aTorreNegra.model;

import com.aTorreNegra.dialogue.Dialogue;
import com.aTorreNegra.dialogue.DialogueNode;
import com.aTorreNegra.util.AnimationSet;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import connection.AnimationSetDao;
import connection.DialogueDao;
import connection.InventoryDao;
import java.util.ArrayList;
import java.util.List;

public class Actor implements YSortable {
    private List<Quest> myQuests = new ArrayList<Quest>();
    private List<Actor> deadActors = new ArrayList<Actor>();
    private List<Dialogue> dialoguesAccepteds = new ArrayList<Dialogue>();
    private boolean active;
    private boolean openMenu;
    private Inventory inventory;
    private TileMap map;
    private int x;
    private int y;
    private DIRECTION facing;
    private String name;
    private float worldX, worldY;
    private float SizeX, SizeY;
    private int Hp, hpat;
    private int dano;
    // estado do personagem
    private int srcX, srcY;
    private int destX, destY;
    private float animTimer;
    private float WALK_TIME_PER_TILE = 0.3f;
    private float ATTACK_TIME = 0.7f;
    private float DIE_TIME = 2;
    private float REFACE_TIME = 0.1f;
    private float deadTimer;
    private float attackTimer;
    private float walkTimer;
    private boolean moveRequestThisFrame;
    private NOW_STATE now;
    protected Armor helmet;
    protected Armor chesplate;
    protected Armor pants;
    private int id;
    private boolean alive;
    private ACTOR_STATE state;
    private AnimationSet animations;

    public Actor(TileMap map, int x, int y, AnimationSet animations, String name, float SizeX, float SizeY, int hp,
            int dano, Inventory inventory, int id) {
        this.map = map;
        this.x = x;
        this.y = y;
        this.SizeX = SizeX;
        this.SizeY = SizeY;
        this.worldX = x;
        this.worldY = y;
        this.name = name;
        this.animations = animations;
        map.getTile(x, y).setActor(this);
        this.state = ACTOR_STATE.STANDING;
        this.facing = DIRECTION.SUL;
        this.Hp = hp;
        this.hpat = hp;
        this.dano = dano;
        this.now = NOW_STATE.ALIVE;
        this.inventory = inventory;
        this.id = id;
    }

    public Actor(int x, int y, String name, float SizeX, float SizeY, int hp,
            int dano, int id, TileMap map, boolean active, boolean alive) {
        this.alive = alive;
        if (alive == true) {
            this.name = name;
            this.Hp = hp;
            this.hpat = hp;
            this.dano = dano;
            this.id = id;
            this.inventory = new InventoryDao().consultarG(id);
            this.map = map;
            this.x = x;
            this.y = y;
            this.SizeX = SizeX;
            this.SizeY = SizeY;
            this.worldX = x;
            this.worldY = y;
            this.helmet = inventory.getHelmet();
            this.chesplate = inventory.getChesplate();
            this.pants = inventory.getPants();
            this.animations = new AnimationSetDao().consultaAnimationSet(id);
            this.active = active;
            this.state = ACTOR_STATE.STANDING;
            this.facing = DIRECTION.SUL;
            this.now = NOW_STATE.ALIVE;
            this.openMenu = false;
        }

    }

    public Actor() {

    }

    public enum ACTOR_STATE {
        WALKING, STANDING, REFACING, ATTACKING, DYEING, TELEPORTING;
    }

    public enum NOW_STATE {
        ALIVE, DEAD
    }

    public void update(float delta) {
        if (state == ACTOR_STATE.WALKING) {
            animTimer += delta;

            walkTimer += delta;
            worldX = Interpolation.linear.apply(srcX, destX, animTimer / WALK_TIME_PER_TILE);
            worldY = Interpolation.linear.apply(srcY, destY, animTimer / WALK_TIME_PER_TILE);

            if (animTimer > WALK_TIME_PER_TILE) {
                float leftOverTime = animTimer - WALK_TIME_PER_TILE;
                finishMove();
                if (moveRequestThisFrame) {
                    if (move(facing)) {
                        animTimer += leftOverTime;
                        worldX = Interpolation.linear.apply(srcX, destX, animTimer / WALK_TIME_PER_TILE);
                        worldY = Interpolation.linear.apply(srcY, destY, animTimer / WALK_TIME_PER_TILE);
                    }
                } else {

                    walkTimer = 0f;
                }
            }
        }
        if (state == ACTOR_STATE.ATTACKING) {
            animTimer += delta;
            attackTimer += delta;
            if (animTimer > ATTACK_TIME) {
                attackTimer = 0;
                state = ACTOR_STATE.STANDING;
            }
        }
        if (state == ACTOR_STATE.DYEING) {
            animTimer += delta;
            deadTimer += delta;
            if (animTimer > DIE_TIME) {
                deadTimer = 0;
                alive = false;
                now = NOW_STATE.DEAD;
            }
        }
        if (state == ACTOR_STATE.REFACING) {
            animTimer += delta;
            if (animTimer > REFACE_TIME) {
                state = ACTOR_STATE.STANDING;
            }
        }

        moveRequestThisFrame = false;

    }

    public boolean reface(DIRECTION dir) {
        if (state != ACTOR_STATE.STANDING) {
            return false;
        }
        if (facing == dir) {
            return true;
        }
        facing = dir;
        state = ACTOR_STATE.REFACING;
        animTimer = 0f;
        return true;
    }

    public boolean attack() {
        if (state == ACTOR_STATE.STANDING) {
            state = ACTOR_STATE.ATTACKING;
            animTimer = 0f;
            if (x + facing.getDx() >= map.getWidth() || x + facing.getDx() < 0
                    || y + facing.getDy() >= map.getHeight() || y + facing.getDy() < 0) {
                return false;
            }
            if (map.getTile(x + facing.getDx(), y + facing.getDy()).getActor() != null) {
                map.getTile(x + facing.getDx(), y + facing.getDy()).getActor().receberDano(dano);
                System.out.println(map.getTile(x + facing.getDx(), y + facing.getDy()).getActor().getHpat());
            }

            return true;
        }
        return false;
    }

    public Dialogue interact() {
        Dialogue d = null;
        if (state == ACTOR_STATE.STANDING) {
            if (map.getTile(x + facing.getDx(), y + facing.getDy()).getActor() != null) {
                DialogueDao dao = new DialogueDao();
                d = dao.consultaDialogue(id, map.getTile(x + facing.getDx(), y + facing.getDy()).getActor().getId(), (ArrayList<Dialogue>) dialoguesAccepteds);
            }
        }
        return d;
    }

    public boolean move(DIRECTION dir) {
        if (state == ACTOR_STATE.WALKING) {
            if (facing == dir) {
                moveRequestThisFrame = true;
            }
            return false;
        }
        if (x + dir.getDx() >= map.getWidth() || x + dir.getDx() < 0 || dir.getDy() + y >= map.getHeight()
                || dir.getDy() + y < 0) {
            reface(dir);
            return false;
        }
        if (map.getTile(x + dir.getDx(), y + dir.getDy()).getActor() != null) {
            reface(dir);
            return false;
        }
        if (map.getTile(x + dir.getDx(), y + dir.getDy()).getObject() != null) {
            WorldObject o = map.getTile(x + dir.getDx(), y + dir.getDy()).getObject();
            if (!o.isWalkable()) {
                reface(dir);
                return false;
            }
        }
        initializeMove(dir);
        map.getTile(x, y).setActor(null);
        x += dir.getDx();
        y += dir.getDy();
        map.getTile(x, y).setActor(this);
        return true;
    }

    public float getWorldX() {
        return worldX;
    }

    public float getWorldY() {
        return worldY;
    }

    private void initializeMove(DIRECTION dir) {
        this.facing = dir;
        this.srcX = x;
        this.srcY = y;
        this.destX = x + dir.getDx();
        this.destY = y + dir.getDy();
        this.worldX = x;
        this.worldY = y;
        animTimer = 0f;
        state = ACTOR_STATE.WALKING;
    }

    private void finishMove() {
        state = ACTOR_STATE.STANDING;
        this.worldX = destX;
        this.worldY = destY;
        this.srcX = 0;
        this.srcY = 0;
        this.destX = 0;
        this.destY = 0;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int gety() {
        return y;
    }

    public void sety(int y) {
        this.y = y;
    }

    public TextureRegion getSprite() {
        if (state == ACTOR_STATE.WALKING) {
            return animations.getWalking(facing).getKeyFrame(walkTimer);
        } else if (state == ACTOR_STATE.STANDING) {
            return animations.getStanding(facing);

        } else if (state == ACTOR_STATE.REFACING) {
            if (facing == DIRECTION.NORDESTE) {
                facing = DIRECTION.LESTE;
            }
            return animations.getWalking(facing).getKeyFrames()[0];
        } else if (state == ACTOR_STATE.ATTACKING) {
            return animations.getAttacking(facing).getKeyFrame(attackTimer);
        } else if (state == ACTOR_STATE.DYEING) {
            return animations.getDyeing(facing).getKeyFrame(deadTimer);
        }
        return animations.getStanding(DIRECTION.SUL);
    }

    public TextureRegion getArmorSprite() {
        return helmet.getRegion();
    }

    @Override
    public float getSizeX() {
        return SizeX;
    }

    @Override
    public float getSizeY() {
        return SizeY;
    }

    public String getName() {
        return name;
    }

    public ACTOR_STATE getState() {
        return state;
    }

    public void setHp(int dano) {
        this.hpat = hpat - dano;
    }

    public int getDano() {
        return dano;
    }

    public int getHpat() {
        return hpat;
    }

    public void receberDano(int dano) {
        if (hpat - dano > 0) {
            hpat = hpat - dano;
        } else {
            hpat = 0;
            state = ACTOR_STATE.DYEING;
        }
    }

    public NOW_STATE getNow() {
        return now;
    }

    public void setMap(TileMap map) {
        this.map = map;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void remove() {
        map.getTile(x, y).setActor(null);
    }

    public AnimationSet getAnimationSet() {
        return animations;
    }

    public void telport() {

    }

    public Armor getHelmet() {
        return helmet;
    }

    public Armor getChesplate() {
        return chesplate;
    }

    public Armor getPants() {
        return pants;
    }

    public void setHelmet(Armor helmet) {
        this.helmet = helmet;
    }

    public void setChesplate(Armor chesplate) {
        this.chesplate = chesplate;
    }

    public void setPants(Armor pants) {
        this.pants = pants;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public int getCode() {
        return this.id;
    }

    public int getId() {
        return id;
    }

    public boolean isActive() {
        return active;
    }

    public void openMenuYes() {
        if (openMenu == true) {
            openMenu = false;
        } else {
            openMenu = true;
        }
    }

    public boolean getOpen() {
        return openMenu;
    }

    public TextureRegion createRegion(Texture texture) {
        TextureRegion region = new TextureRegion();
        region.setRegion(texture);
        return region;
    }

    public TileMap getMap() {
        return map;
    }

    public boolean isAlive() {
        return alive;
    }

    public List<Actor> getDeadActors() {
        return deadActors;
    }

    public List<Dialogue> getDialoguesAccepteds() {
        return dialoguesAccepteds;
    }
    public void addDeadDialogue(Dialogue dialogue){
        dialoguesAccepteds.add(dialogue);
    }
    public void receberRecompensas(DialogueNode node){
        for (int i = 0; i < myQuests.size(); i++) {
            if(node.getId() == myQuests.get(i).getObjetivoTexto().getId() && myQuests.get(i).isState() != true){
                ArrayList<Item>newItems = myQuests.get(i).completarQuest(node, inventory);
                for(int j=0;j<newItems.size();j++){
                    inventory.addItem(newItems.get(i));
                }
                myQuests.get(i).setState(true);
            }
        }
    }
}
