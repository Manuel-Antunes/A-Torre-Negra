package com.aTorreNegra.screen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.aTorreNegra.Settings;
import com.aTorreNegra.TorreNegra;
import com.aTorreNegra.controller.DialogueController;
import com.aTorreNegra.controller.InvetoryScreenController;
import com.aTorreNegra.controller.MenuScreenController;
import com.aTorreNegra.controller.PlayerController;
import com.aTorreNegra.dialogue.Dialogue;
import com.aTorreNegra.model.Actor;
import com.aTorreNegra.model.Armor;
import com.aTorreNegra.model.Camera;
import com.aTorreNegra.model.TeleportTile;
import com.aTorreNegra.model.World;
import com.aTorreNegra.model.WorldObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.aTorreNegra.model.YSortable;
import com.aTorreNegra.ui.DialogueBox;
import com.aTorreNegra.ui.GeneralMenuScreen;
import com.aTorreNegra.ui.InventoryScreen;
import com.aTorreNegra.ui.OptionBox;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Align;
import connection.WorldDao;

public class GameScreen extends AbstractScreen {

    private DialogueController dialogueController;
    private Dialogue dialogue;
    private DialogueBox dialogueBox;
    private Actor player2;
    private InventoryScreen inventoryScreen;
    private World world;
    private SpriteBatch batch;
    private Camera camera;
    private PlayerController playerController;
    private Texture grass1;
    private Texture grass2;
    private int uiScale = 2;
    private Viewport gameViewPort;
    private TeleportTile door;
    private Texture port;
    private Stage uiStage;
    private WorldObject house1;
    private WorldObject house2;
    private Table root;
    private List<YSortable> forRendering = new ArrayList<YSortable>();
    private List<Integer> renderedObjects = new ArrayList<Integer>();
    private List<World> worlds;
    private MenuScreenController menuScreenController;
    private List<Armor> armosRendering = new ArrayList<Armor>();
    private TeleportTile door2;
    private WorldObject flower;
    private WorldObject flower2;
    private OptionBox optionBox;
    private InputMultiplexer multiplexer;
    private GeneralMenuScreen menuScreen;

    public enum OBJECTS {
        SMALL_HOUSE;
    }

    public GameScreen(TorreNegra app) {

        super(app);
        worlds = new ArrayList<World>();
        worlds.add(new World(1, true));
        for (int i = 0; i < worlds.size(); i++) {
            if (worlds.get(i).isActive()) {
                world = worlds.get(i);
            } else {

            }

        }
        for (int j = 0; j < world.getMap().getWidth(); j++) {
            for (int k = 0; k < world.getMap().getHeight(); k++) {
                if (world.getMap().getTile(j, k).getActor() != null) {
                    world.addActor(world.getMap().getTile(j, k).getActor());
                    if (world.getMap().getTile(j, k).getActor().isActive()) {
                        world.setPlayer(world.getMap().getTile(j, k).getActor());
                    }
                }
            }
        }
        gameViewPort = new ScreenViewport();
        batch = new SpriteBatch();
        camera = new Camera();
        initUI();
        multiplexer = new InputMultiplexer();
        playerController = new PlayerController(world.getPlayer());
        menuScreenController = new MenuScreenController(menuScreen, this.world);
        InvetoryScreenController optionBoxController = new InvetoryScreenController(inventoryScreen);
        dialogueController = new DialogueController(dialogueBox, optionBox);
        multiplexer.addProcessor(0, menuScreenController);
        multiplexer.addProcessor(1, dialogueController);
        multiplexer.addProcessor(2, optionBoxController);
        multiplexer.addProcessor(3, playerController);

    }

    @Override
    public void dispose() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void render(float delta) {
        openMenu();
        playerController.update(delta, world);
        dialogueController.update(delta);
        world.getPlayer().update(delta);
        world.update(delta);
        camera.update(world.getPlayer().getWorldX() + 0.5f, world.getPlayer().getWorldY() + 0.5f);
        uiStage.act(delta);
        gameViewPort.apply();
        Dialogue d = playerController.updateAgain();
        if (d != null) {
            System.out.println(d.getId());
            if (!dialogueController.isActive()) {
                dialogueController.startDialogue(d);
                world.getPlayer().addDeadDialogue(d);
            }
        }
        if (dialogueController.getTraverser().getCurrentNode() != null) {
            world.getPlayer().receberRecompensas(dialogueController.getTraverser().getCurrentNode());
        }
        inventoryScreen.update(world.getPlayer());

        if (menuScreenController.isiCanSave()) {
            dialogueBox.setVisible(true);
            dialogueBox.animateText("Jogo Salvo.                 ");
            dialogueBox.isFinishend();

            menuScreenController.setiCanSave(false);
        }
        if (dialogueBox.getText() == "Jogo Salvo.                 " && dialogueBox.isFinishend()) {
            dialogueBox.setVisible(false);
        }
        saveSprites();
        batch.begin();
        float worldStartX = Gdx.graphics.getWidth() / 2 - camera.getCameraX() * Settings.SCALED_TILE_SIZE;
        float worldStartY = Gdx.graphics.getHeight() / 2 - camera.getCameraY() * Settings.SCALED_TILE_SIZE;
        for (int x = 0; x < world.getMap().getWidth(); x++) {
            for (int y = 0; y < world.getMap().getHeight(); y++) {
                batch.draw(world.getMap().getTile(x, y).getRegion(), worldStartX + x * Settings.SCALED_TILE_SIZE,
                        worldStartY + y * Settings.SCALED_TILE_SIZE, Settings.SCALED_TILE_SIZE,
                        Settings.SCALED_TILE_SIZE);

            }
        }
        Collections.sort(forRendering, new WorldObjectYComparator());
        Collections.reverse(forRendering);

        for (YSortable loc : forRendering) {
            TextureRegion sprite = loc.getSprite();
            batch.draw(sprite, worldStartX + loc.getWorldX() * Settings.SCALED_TILE_SIZE,
                    worldStartY + loc.getWorldY() * Settings.SCALED_TILE_SIZE, loc.getSizeX() * 2, loc.getSizeY() * 4);
            if (loc.getHelmet() != null) {
                batch.draw(loc.getHelmet().getRegion(), worldStartX + loc.getWorldX() * Settings.SCALED_TILE_SIZE,
                        worldStartY + loc.getWorldY() * Settings.SCALED_TILE_SIZE, loc.getSizeX() * 2, loc.getSizeY() * 4);
            }
            if (loc.getPants() != null) {
                batch.draw(loc.getPants().getRegion(), worldStartX + loc.getWorldX() * Settings.SCALED_TILE_SIZE,
                        worldStartY + loc.getWorldY() * Settings.SCALED_TILE_SIZE, loc.getSizeX() * 2, loc.getSizeY() * 4);
            }
            if (loc.getChesplate() != null) {
                batch.draw(loc.getChesplate().getRegion(), worldStartX + loc.getWorldX() * Settings.SCALED_TILE_SIZE,
                        worldStartY + loc.getWorldY() * Settings.SCALED_TILE_SIZE, loc.getSizeX() * 2, loc.getSizeY() * 4);
            }

        }
        batch.end();
        uiStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        batch.getProjectionMatrix().setToOrtho2D(0, 0, width, height);
        uiStage.getViewport().update(width / uiScale, height / uiScale, true);
        gameViewPort.update(width, height);
    }

    @Override
    public void resume() {

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(multiplexer);

    }

    private void initUI() {
        uiStage = new Stage(new ScreenViewport());
        uiStage.getViewport().update(Gdx.graphics.getWidth() / uiScale, Gdx.graphics.getHeight() / uiScale);
        root = new Table();
        root.setFillParent(true);
        uiStage.addActor(root);
        Table dialogueTable = new Table();
        dialogueTable.setFillParent(true);
        Table menuTable = new Table();
        dialogueBox = new DialogueBox(getApp().getSkin());
        optionBox = new OptionBox(getApp().getSkin());
        inventoryScreen = new InventoryScreen(getApp().getSkin(), world.getPlayer());
        menuScreen = new GeneralMenuScreen(getApp().getSkin(), inventoryScreen);
        dialogueTable.add(dialogueBox);
        dialogueTable.add(optionBox).pad(5);
        menuTable.add(inventoryScreen.getUiContainer2());
        menuTable.add(inventoryScreen);
        menuTable.add(menuScreen);
        inventoryScreen.getUiContainer2().setVisible(inventoryScreen.isVisible());
        root.add(menuTable).expand().align(Align.bottom).pad(60);
        dialogueBox.setVisible(false);
        optionBox.setVisible(false);
        uiStage.addActor(dialogueTable.align(Align.bottom).pad(5));
    }

    public List<YSortable> getRedering() {
        return forRendering;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public World getWorld() {
        return world;
    }

    public void screenUpdate() {
        forRendering.clear();
        renderedObjects.clear();
    }

    public void saveSprites() {
        for (int i = 0; i < world.getMap().getWidth(); i++) {
            for (int j = 0; j < world.getMap().getHeight(); j++) {
                if (world.getMap().getTile(i, j).getActor() != null) {
                    boolean test = true;
                    for (int k = 0; k < forRendering.size(); k++) {
                        if (forRendering.get(k).getName().equals(world.getMap().getTile(i, j).getActor().getName())) {

                            test = false;
                        }

                    }
                    if (test) {
                        forRendering.add(world.getMap().getTile(i, j).getActor());
                        if (world.getMap().getTile(i, j).getActor().getHelmet() != null) {
                            armosRendering.add(world.getMap().getTile(i, j).getActor().getHelmet());
                        }
                    }
                }

                if (world.getMap().getTile(i, j).getObject() != null) {
                    WorldObject object = world.getMap().getTile(i, j).getObject();
                    if (renderedObjects.contains(object.hashCode())) {
                        continue;
                    } else {
                        forRendering.add(object);
                        renderedObjects.add(object.hashCode());
                    }
                }
                if (world.getMap().getTile(i, j).getActor() != null) {
                    boolean remove = false;
                    for (int k = 0; k < forRendering.size(); k++) {
                        if (forRendering.get(k).getName().equals(world.getMap().getTile(i, j).getActor().getName())) {
                            if (world.getMap().getTile(i, j).getActor().getNow() == Actor.NOW_STATE.DEAD) {
                                forRendering.remove(k);
                                remove = true;
                            }
                        }
                    }
                    if (remove) {
                        world.getMap().getTile(i, j).setActor(null);
                    }
                }
            }
        }
    }

    public GridPoint2[] createObject(int worldX, int worldY, int sizeX, int sizeY) {
        GridPoint2 tiles[] = new GridPoint2[sizeX * sizeY];
        for (int i = 0; i < tiles.length; i++) {
            tiles[i] = new GridPoint2();
        }
        int i = 0;
        while (i != tiles.length) {
            for (int j = worldX; j < worldX + sizeX; j++) {
                for (int k = worldY; k < worldY + sizeY; k++) {
                    tiles[i].set(j, k);
                    i++;
                }
            }
        }
        return tiles;
    }

    public TextureRegion createRegion(Texture texture) {
        TextureRegion region = new TextureRegion();
        region.setRegion(texture);
        return region;
    }

    public void openMenu() {
        if (world.getPlayer().getOpen() == true) {
            menuScreen.setVisible(true);
        } else {
            menuScreen.setVisible(false);
        }
    }
}
