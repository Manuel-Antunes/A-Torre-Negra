package com.aTorreNegra.model;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Armor extends Item {

    private int ip, tipo;
    private boolean active;

    public Armor(float peso, float valor, TextureRegion region, int ip, int tipo, String name) {
        super(peso, valor, region, name);
        this.ip = ip;
        this.tipo = tipo;
    }
    public Armor(float peso, float valor, int ip, int tipo, String name, int id,boolean active, String endereco){
        super(name, peso, valor, id, endereco);
        this.ip = ip;
        this.tipo = tipo;
        this.active = active;
    }

    public int getIp() {
        return ip;
    }

    public int getTipo() {
        return tipo;
    }
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
}
