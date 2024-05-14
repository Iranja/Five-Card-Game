package com.takima.back.controllers.five_game;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Manche {
    
    private Partie partie;
    private Joueur jCourant;
    private Carte joker;
    private boolean mancheFinie;
    
    private ArrayList<Carte> pioche;
    
    public Manche (Partie partie) {
        this.partie = partie;
        initialiserManche();
        jouerManche();
    }
    
    public void initialiserManche(){
        
        System.out.println("Nouvelle manche !");
        mancheFinie = false;
        initialiserPioche();
        initialiserJoueurs();
        initialiserJoker();
        initialiserPremierJoueur();
    }
    
    public void initialiserPioche(){
        Deck deck = new Deck();
        pioche = new ArrayList<>();
        pioche = deck.getCartes();
        melanger(pioche);
        System.out.println("Pioche initialisée !\n" + pioche.size());
    }
    
    public void initialiserJoueurs(){
        for(Joueur j : partie.getJoueurs()){
            j.resetManche();
            piocher(5, j);
        }
        System.out.println("Joueurs initialisés !\n" + partie.getJoueurs().size());
        
    }
    
    public void initialiserPremierJoueur(){
        jCourant = partie.getJoueurs().get(0);
        ArrayList<Carte> premiereCarte = new ArrayList<>();
        premiereCarte.add(this.pioche.get(0));
        this.getJCourant().recupererPile(premiereCarte);
        System.out.println(this.getJCourant().toString());
    }
    
    public void initialiserJoker(){
        // Retirer le joker de la pioche
        this.joker = this.pioche.remove(0);
        System.out.println("Joker : " + this.joker.toString());
    
        // Parcourir la pioche pour marquer les cartes ayant la même valeur que le joker comme jokers
        for (Carte carte : pioche) {
            if (carte.getValeur().equals(this.joker.getValeur())) {
                carte.setJoker(true);
            }
        }
    }
    
    public Joueur getJCourant() {
        for (Joueur joueur : partie.getJoueurs()) {
            if (joueur.getNom().equals(this.jCourant.getNom())) {
                return joueur;
            }
        }
        return null; // Retourne null si aucun joueur correspondant à jCourant n'est trouvé
    }
    
    public int getIndexJCourant() {
        for (int i = 0; i < partie.getJoueurs().size(); i++) {
            if (partie.getJoueurs().get(i).getNom().equals(jCourant.getNom())) {
                return i; // Retourne l'index du joueur courant
            }
        }
        return -1; // Retourne -1 si aucun joueur correspondant à jCourant n'est trouvé
    }

    
    public void jouerManche(){
        while (!mancheFinie){
            
            mancheFinie = five();
            
            if(mancheFinie){
                System.out.println("\n"+this.getJCourant().toString()+"\n");
                ajouterPointsAuxJoueurs();
                System.out.println("Manche terminée !");
            }
            else {
                actionsJCourant();
                this.passerAuJoueurSuivant();
            }
            
        }
        
    }
    
    public void ajouterPointsAuxJoueurs() {
        // Initialisation des variables pour stocker la valeur de la main la moins élevée et les joueurs gagnants
        int valeurMinMain = Integer.MAX_VALUE;
        List<Joueur> joueursGagnants = new ArrayList<>();
    
        // Affichage des valeurs des mains de chaque joueur
        System.out.println("Valeurs des mains des joueurs :");
        for (Joueur joueur : this.partie.getJoueurs()) {
            int valeurMain = calculerValeurMain(joueur);
            System.out.println(joueur.getNom() + " : " + valeurMain);
            
            // Mise à jour de la valeur de la main la moins élevée et des joueurs gagnants
            if (valeurMain < valeurMinMain) {
                valeurMinMain = valeurMain;
                joueursGagnants.clear();
                joueursGagnants.add(joueur);
            } else if (valeurMain == valeurMinMain) {
                joueursGagnants.add(joueur);
            }
        }
    
        // Parcours de tous les joueurs pour leur attribuer des points en fonction de la valeur de leur main
        for (Joueur joueur : this.partie.getJoueurs()) {
            int valeurMain = calculerValeurMain(joueur);
            if (valeurMain == valeurMinMain) {
                joueur.ajouterPoints(0); // Les joueurs ayant la main la moins élevée obtiennent 0 points en cas d'égalité
            } else if (!joueur.getNom().equals(this.getJCourant().getNom())) {
                joueur.ajouterPoints(valeurMain); // Les autres joueurs obtiennent des points équivalents à la valeur de leur main
            } else {
                joueur.ajouterPoints(50); // Olivier obtient 50 points si sa main n'est pas la moins élevée
            }
        }
    
        // Affichage du message aux joueurs gagnants
        if (joueursGagnants.size() == 1) {
            System.out.println("Le joueur gagnant est : " + joueursGagnants.get(0).getNom());
        } else {
            System.out.println("Les joueurs gagnants sont : ");
            for (Joueur joueur : joueursGagnants) {
                System.out.println(joueur.getNom());
            }
        }
    }
    
     
    
    public int calculerValeurMain(Joueur j){
        int sommeValeurs = 0;
        for (Carte carte : j.getMain()) {
            sommeValeurs += carte.getValeurInt();
        }
        return sommeValeurs;
    }
    
    public boolean five(){
        if(calculerValeurMain(this.getJCourant()) <= 5){
            if(this.getJCourant().getType().equals("Ordi")){
                System.out.println("Five de " + this.getJCourant().getNom() + " !");
                return true;
            }
            Scanner scanner = new Scanner(System.in);
            int choix;
            System.out.println("1. Five");
            System.out.println("2. Poser une ou plusieurs cartes");
            System.out.print("Choix : ");
            choix = scanner.nextInt();
            if (choix == 1) {
                System.out.println("Five de " + this.getJCourant().getNom() + " !");
                return true;
            } else if (choix == 2) {
                return false;
            } else {
                System.out.println("Choix invalide. Veuillez réessayer.");
                five();
            }
        } else {
            return false;
        }
        
        return false;
    }
    
    public void actionsJCourant(){
        poserMain();
        recupererMain();
    }
    
    public String getTypeJCourant(){
        return this.getJCourant().getType();
    }

    public ArrayList<Carte> obtenirCartesAvecValeurMax() {
        Carte cartePileCourante = jCourant.getPileCourante();
        Map<Integer, ArrayList<Carte>> cartesParValeur = new HashMap<>();
        int valeurMax = 0;

        // Grouper les cartes par valeur entière et trouver la valeur maximale
        for (Carte carte : this.getJCourant().getMain()) {
            if (carte.getValeurInt() != cartePileCourante.getValeurInt()) {
                int valeurInt = carte.getValeurInt();
                if (!cartesParValeur.containsKey(valeurInt)) {
                    cartesParValeur.put(valeurInt, new ArrayList<>());
                }
                cartesParValeur.get(valeurInt).add(carte);
                valeurMax = Math.max(valeurMax, valeurInt);
            }
        }

        // Retourner les cartes ayant la valeur maximale
        return cartesParValeur.getOrDefault(valeurMax, new ArrayList<>());
    }
    
    public void poserMain() {
        ArrayList<Carte> cartes = new ArrayList<>();
        if(getTypeJCourant() == "Ordi"){
            cartes = obtenirCartesAvecValeurMax();
        }
        else{
            Scanner scanner = new Scanner(System.in);
            boolean saisieValide = false;
            do {
                System.out.println("Choisissez les cartes à poser en saisissant leur indice (séparés par des espaces) :");
                System.out.println("Exemple : '1 3 5' pour choisir les cartes à l'indice 1, 3 et 5.");
        
                String[] indices = scanner.nextLine().split(" ");
                for (String indiceStr : indices) {
                    int indice;
                    try {
                        indice = Integer.parseInt(indiceStr) - 1;
                    } catch (NumberFormatException e) {
                        System.out.println("Valeur invalide : " + indiceStr);
                        continue;
                    }
                    if (indice >= 0 && indice < this.getJCourant().getMain().size()) {
                        cartes.add(this.getJCourant().getMain().get(indice));
                    } else {
                        System.out.println("Indice invalide : " + indice);
                        poserMain();
                    }
                }
        
                if (!cartes.isEmpty() && sontMemesValeurs(cartes)) {
                    saisieValide = true;
                } else {
                    System.out.println("Les cartes sélectionnées n'ont pas la même valeur ou aucune carte n'a été sélectionnée.");
                    cartes.clear();
                }
            } while (!saisieValide);
        }
        this.getJCourant().poserMain(cartes);
        this.getJSuivant().recupererPile(cartes);
    }
    
    private boolean sontMemesValeurs(ArrayList<Carte> cartes) {
        if (cartes.isEmpty()) {
            return false;
        }
        int valeur = cartes.get(0).getValeurInt();
        for (Carte carte : cartes) {
            if (carte.getValeurInt() != valeur) {
                return false;
            }
        }
        return true;
    }
    
    public void recupererOrdi() {
        Carte cartePileCourante = jCourant.getPileCourante();
        int valeurIntPileCourante = cartePileCourante.getValeurInt();
    
        // Vérifier si la carte de la pile courante a la même valeur Int que l'une des cartes de la main
        for (Carte carte : this.getJCourant().getMain()) {
            if (carte.getValeurInt() == valeurIntPileCourante) {
                this.getJCourant().prendrePile();
                return;
            }
        }
    
        // Vérifier si la valeur Int de la carte de la pile courante est inférieure ou égale à 2
        if (valeurIntPileCourante <= 2) {
            this.getJCourant().prendrePile();
        } else {
            piocher(1, this.getJCourant());
        }
    }

    public void recupererMain() {
        if(this.getTypeJCourant() == "Ordi"){
            recupererOrdi();
        }
        else {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Choisissez une action :");
            System.out.println("1. Piocher une carte");
            System.out.println("2. Prendre la pile");
        
            int choix = scanner.nextInt();
            switch (choix) {
                case 1:
                    piocher(1, this.getJCourant());
                    break;
                case 2:
                    this.getJCourant().prendrePile();
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
                    recupererMain(); // Redemander le choix si l'entrée est invalide
                    break;
            }
        }
    }

    
    public void passerAuJoueurSuivant() {
        jCourant = getJSuivant(); // Met à jour le joueur courant avec le prochain joueur
        System.out.println("\nA " + jCourant.getNom() + " de jouer !\n");
        System.out.println(jCourant.toString());
    }
    
    public Joueur getJSuivant() {
        int indexCourant = getIndexJCourant();
        int prochainIndex = (indexCourant + 1) % partie.getJoueurs().size(); // Calcul de l'index du prochain joueur en tenant compte du fait que le joueur suivant après le dernier est le premier joueur
        
        return partie.getJoueurs().get(prochainIndex); // Renvoie le prochain joueur
    }


    
    public void melanger(ArrayList<Carte> pioche){
        Collections.shuffle(pioche);
    }
    
    public void piocher(int nbCartes, Joueur j) {
        if (pioche.size() < nbCartes) {
            System.out.println("La pioche ne contient pas assez de cartes, reconstruction de la pioche...");
            reconstruirePioche();
            piocher(nbCartes, j); // Appel récursif avec le même nombre de cartes
            return;
        }
    
        int taillePioche = pioche.size();
        int debutPioche = Math.max(0, taillePioche - nbCartes); // Début de la sous-liste
        int finPioche = taillePioche; // Fin de la sous-liste (exclusif)
    
        List<Carte> cartesPiochees = pioche.subList(debutPioche, finPioche);
    
        // Ajouter les cartes piochées à la main du joueur
        for (Carte carte : cartesPiochees) {
            j.piocher(carte);
        }
    
        // Retirer les cartes de la pioche
        pioche.removeAll(cartesPiochees);
    }

    public void reconstruirePioche() {
        // Prendre toutes les cartes des piles de tous les joueurs, y compris le joueur courant mais sans prendre la dernière carte de sa pile
        ArrayList<Carte> derniereCarte = new ArrayList<>();
        derniereCarte.add(jCourant.getPileCourante());
        for (Joueur joueur : partie.getJoueurs()) {
            List<Carte> cartesPile = joueur.getPile();
            for (int i = 0; i < cartesPile.size() - 1; i++) {
                pioche.add(cartesPile.get(i));
            }
            joueur.viderPile(); // Vider la pile du joueur après avoir pris ses cartes
        }
        jCourant.recupererPile(derniereCarte);

        // Mélanger la pioche
        melanger(pioche);
    }

}