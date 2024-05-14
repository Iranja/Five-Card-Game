package com.takima.back.controllers.five_game;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Joueur{
    private ArrayList<Carte> main;
    private ArrayList<Carte> pile;
    private String nom;
    private String type;
    @Setter
    private int points;
    
    public Joueur (String nom, String type) {
        this.nom = nom;
        this.type = type;
        this.main = new ArrayList<>();
        pile = new ArrayList<>();
        this.points = 0;
    }

    // Carte au dessus de la pile
    public Carte getPileCourante(){
        if(!this.pile.isEmpty())
            return this.pile.get(this.pile.size() - 1);
        else
            return null;
    }

    public void ajouterPoints(int points){
        this.points += points;
    }
    
    // Prend une carte dans la pioche
    public void piocher(Carte c){
        this.main.add(c);
    }
    
    // Prend une carte dans sa pile
    public void prendrePile(){
        // Vérifier si la pile n'est pas vide
        if (!this.pile.isEmpty()) {
            this.main.add(this.pile.remove(this.pile.size() - 1));
        } else {
            System.out.println("La pile est vide, impossible de prendre une carte.");
        }
    }
    
    // Pose une ou plusieurs cartes de sa main dans la pile du joueur suivant
    public void poserMain(ArrayList<Carte> cartes){
        for(Carte c : cartes){
            this.main.removeIf(carte -> carte.toString().equals(c.toString()));
        }
    }
    
    // Récupère une carte du joueur précendant dans sa pile
    public void recupererPile(ArrayList<Carte> cartes){
        for(Carte c : cartes){
            this.pile.add(c);
        }
    }
    
    public void viderPile(){
        this.pile.clear();
    }
    
    public void viderMain(){
        this.main.clear();
    }
    
    public void resetManche(){
        this.viderPile();
        this.viderMain();
    }
    
    public String mainToString(){
        String s = "";
        for(Carte c : this.main)
           s+=" | " + c.toString() + " | ";
        
        return s;
    }
    
    public String pileToString(){
        if (this.getPileCourante() == null)
            return "Pile vide";
        else
            return this.getPileCourante().toString();
    }
    
    @Override
    public String toString(){
        return this.getNom() + " :\nMain :" + this.mainToString() + "\nPile : " + this.pileToString();
    }
    
}