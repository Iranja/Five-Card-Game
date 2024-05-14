package com.takima.back.controllers.five_game;

public enum Valeur {
    AS(1),
    DEUX(2),
    TROIS(3),
    QUATRE(4),
    CINQ(5),
    SIX(6),
    SEPT(7),
    HUIT(8),
    NEUF(9),
    DIX(10),
    VALET(10),
    DAME(10),
    ROI(10),
    JOKER(0);

    private final int valeurInt;

    Valeur(int valeurInt) {
        this.valeurInt = valeurInt;
    }

    public int getValeurInt() {
        return valeurInt;
    }
}
