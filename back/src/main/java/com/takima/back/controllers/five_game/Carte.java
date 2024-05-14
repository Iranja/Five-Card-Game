package com.takima.back.controllers.five_game;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class Carte {
    private Symbole symbole;

    private Valeur valeur;

    private boolean joker;
    
    public Carte(Symbole symbole, Valeur valeur) {
        this.symbole = symbole;
        this.valeur = valeur;
        this.joker = false;
    }

    public String getImage(){
        return this.toString() + ".png";
    }
    
    public int getValeurInt(){
        if(joker)
            return 0;
        else
            return this.valeur.getValeurInt();
    }

    @Override
    public String toString() {
        return valeur + " de " + symbole;
    }
}
