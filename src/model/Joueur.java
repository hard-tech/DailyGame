package model;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;

public class Joueur extends  Personnage implements Interactuable{
    private Dictionary inventaire = new Hashtable();
    private String armeActuelle = "";
    // Ex : phoneBook.put("Key", "Value");


    public Joueur(String nom, int pointDeVie, int force, boolean defense, Dictionary inventaire) {
        super(nom, pointDeVie, force, defense);
        this.inventaire = inventaire;
    }

    public Dictionary getInventaire() {
        return inventaire;
    }

    public void setInventaire(Dictionary inventaire) {
        this.inventaire = inventaire;
    }

    public String getArmeActuelle() {
        return armeActuelle;
    }

    public void setArmeActuelle(String armeActuelle) {
        this.armeActuelle = armeActuelle;
    }

    /* Utiliser un objet */
    public void Utiliser(Objet objet){
        if (objet.getCapacite().toLowerCase().contains("heal")){
            this.setPointDeVie(this.getPointDeVie() + objet.getValeur());
        }
    }
    /* Attaquer */

    @Override
    public void Attaquer(Personnage target) {
        int degat = 0;
        if (target.isDefense()) {
            target.setPointDeVie(target.getPointDeVie() - (this.getForce() / 2));
            degat = (this.getForce() / 2);
        } else {
            target.setPointDeVie(target.getPointDeVie() - this.getForce());
            degat = this.getForce();
        }
        target.setDefense(false);
        if (target instanceof Bug) {
            System.out.println("Vous écrivez un nouveau code qui tue ça mère. Vous infligez " +
                    degat + " dégats");
        }
        if (target instanceof Po) {
            System.out.println("Vous demandez encore une solution a un nième problème que vous avez. " +
                    "Vous infligez " +
                    degat + " dégats");
        }
        if (target instanceof Sncf) {
            System.out.println("Vous insultez mentalement tout les travailleurs de la SNCF pour leurs problèmes. " +
                    "Vous infligez " +
                    degat + " dégats");
        }
    }

    /* Defendre */

    @Override
    public void Defendre(Personnage target) {
        this.setDefense(true);
        if (target instanceof Bug) {
            System.out.println("Vous installez un VPN et vous vous préparez au prochain bug.");
        }
        if (target instanceof Po) {
            System.out.println("Vous vous préparez mentalement à l'ennuie et au sommeil " +
                    "qui va arriver.");
        }
        if (target instanceof Sncf) {
            System.out.println("Vous mettez votre casque et mettez votre musique pour " +
                    "ne pas entendre les nouvelles (vous êtes un peu dans le déni).");
        }
    }

    // équiper
    public void Equiper(Arme arme){
        this.setForce(this.getForce() + arme.getForceBoost());
        this.setArmeActuelle(arme.getNom());
    }

    // déséquiper
    public void Desequiper(Arme arme){
        this.setForce(this.getForce() - arme.getForceBoost());
        this.setArmeActuelle("");
    }

    @Override
    public void interagir(Object cible) {

        if (cible instanceof Personnage) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Que voulez-vous faire ?");
            System.out.println("1. Ramasser");
            System.out.println("2. Utiliser");
            System.out.println("3. Parler à un PNJ");

            int choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    ramasser();
                    break;
                case 2:
                    utiliser();
                    break;
                case 3:
                    parlerPNJ();
                    break;
                default:
                    System.out.println("Choix invalide");
            }
        }
    }

    private void ramasser() {
        System.out.println("Le personnage ramasse un objet.");
        // Implémentez la logique pour ramasser ici
    }

    private void utiliser() {
        System.out.println("Le personnage utilise un objet.");
        // Implémentez la logique pour utiliser ici
    }

    private void parlerPNJ() {
        System.out.println("Le personnage parle à un PNJ.");
        // Implémentez la logique pour parler à un PNJ ici
    }
}

