package com.aTorreNegra.model;

public enum DIRECTION {

    NORTE(0, 1), SUL(0, -1), LESTE(1, 0), OESTE(-1, 0), NORDESTE(1, 1), NOROESTE(-1, 1), SUDESTE(1, -1), SUDOESTE(-1, -1);

    private int dx, dy;

    private DIRECTION(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

}
