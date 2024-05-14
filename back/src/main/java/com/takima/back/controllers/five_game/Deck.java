package com.takima.back.controllers.five_game;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Deck {
    private ArrayList<Carte> cartes;

    public Deck() {
        cartes = new ArrayList<>();
        // Ajouter chaque combinaison de carte (sauf pour le Joker)
        for (Symbole symbole : Symbole.values()) {
            if (symbole != Symbole.JOKER) {
                for (Valeur valeur : Valeur.values()) {
                    if (valeur != Valeur.JOKER) {
                        cartes.add(new Carte(symbole, valeur));
                    }
                }
            }
        }
    }

}
