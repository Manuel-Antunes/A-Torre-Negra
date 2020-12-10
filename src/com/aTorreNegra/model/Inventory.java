package com.aTorreNegra.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import connection.ArmorDao;
import connection.ItemDao;
import java.util.ArrayList;
import java.util.List;

public class Inventory {

    private ArmorDao daoA = new ArmorDao();
    private List<Item> items = new ArrayList<Item>();
    private int id;
    private int idFk;
    private ItemDao dao = new ItemDao();

    public Inventory(int id, int idfk) {
        this.idFk = idfk;
        this.id = id;
        items = dao.listarItems(id);
        items = daoA.consultaAtu((ArrayList<Item>) items);
    }

    public Armor getHelmet() {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getClass().getName() == "com.aTorreNegra.model.Armor") {
                Armor a = (Armor) items.get(i);
                if (a.isActive() && a.getTipo() == 1) {
                    return (Armor) items.get(i);
                }
            }
        }
        return null;
    }

    public Armor getChesplate() {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getClass().getName() == "com.aTorreNegra.model.Armor") {
                Armor a = (Armor) items.get(i);
                if (a.isActive() && a.getTipo() == 2) {
                    return (Armor) items.get(i);
                }
            }
        }
        return null;
    }

    public Armor getPants() {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getClass().getName() == "com.aTorreNegra.model.Armor") {
                Armor a = (Armor) items.get(i);
                if (a.isActive() && a.getTipo() == 3) {
                    return (Armor) items.get(i);
                }
            }
        }
        return null;
    }

    public Inventory() {
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }

    public int getId() {
        return id;
    }

    public int getIdFk() {
        return idFk;
    }
    
}
