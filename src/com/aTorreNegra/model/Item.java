package com.aTorreNegra.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Item {
    private String name;
    private float peso;
    private float valor;
    private TextureRegion region;
    private int id;
    private String endereco;

    public Item(float peso, float valor, TextureRegion region, String name) {
        this.peso = peso;
        this.valor = valor;
        this.region = region;
        this.name = name;
    }

    public Item(String name, float peso, float valor, int id,String endereco) {
        this.name = name;
        this.peso = peso;
        this.valor = valor;
        this.id = id;
        this.endereco = endereco;
        this.region = createRegion(new Texture(endereco));
    }
    
    public TextureRegion getRegion() {
        return region;
    }

    public String getName() {
        return name;
    }

    public float getPeso() {
        return peso;
    }

    public float getValor() {
        return valor;
    }

    public int getId() {
        return id;
    }
    public TextureRegion createRegion(Texture texture) {
        TextureRegion region = new TextureRegion();
        region.setRegion(texture);
        return region;
    }

    public String getEndereco() {
        System.out.println("aksdma");
        return endereco;
    }
    
}
