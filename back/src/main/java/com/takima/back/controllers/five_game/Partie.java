package com.takima.back.controllers.five_game;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Iterator;

@Getter
@Setter
public class Partie {
    
    private Joueur dernierGagnant;
    private ArrayList<Joueur> joueurs;
    
    public Partie(String username){
        initialiserJoueurs(username);
        lancerPartie();
    }

    public void initialiserJoueurs(String username){
        this.joueurs = new ArrayList<>();
        joueurs.add(new Joueur(username, "Humain"));
        for(int i=2; i <= 5 ; i++)
            joueurs.add(new Joueur("Ordi " + i, "Ordi"));
    }
    
    public void lancerPartie(){
        while(!partieFinie()){
            Manche manche = new Manche(this);
        }
    }
    
    public boolean partieFinie(){
        if(this.joueurs.size() <= 2){
            System.out.println("Partie terminée !");
            if (this.joueurs.size() == 2) {
                Joueur joueur1 = joueurs.get(0);
                Joueur joueur2 = joueurs.get(1);
                if(joueur1.getPoints() == joueurs.get(1).getPoints()){
                    System.out.println("Victoire de " + joueur1.getNom() + " et " + joueur2.getNom() + " !");
                }
                else if(joueur1.getPoints() > joueurs.get(1).getPoints()){
                    System.out.println("Victoire de " + joueur1.getNom() + " !");
                }
                else{
                    System.out.println("Victoire de " + joueur2.getNom() + " !");
                }
                return true;
            }
            return true;
        }else {
            mettreAJourJoueurs();
            return false;
        }
    }
    
    public void mettreAJourJoueurs() {
        Iterator<Joueur> iterator = this.joueurs.iterator();
        while (iterator.hasNext()) {
            Joueur joueur = iterator.next();
            int points = joueur.getPoints();
            if (points == 50) {
                joueur.setPoints(25);
            } else if (points == 100) {
                joueur.setPoints(50);
            } else if (points > 100) {
                iterator.remove(); // Supprimer le joueur de la liste
            }
        }
        this.afficherPoints();
    }
    
    public void afficherPoints(){
        for(Joueur joueur : this.joueurs){
            System.out.println(joueur.getNom() + " | Points : " + joueur.getPoints());
        }
    }

}