package model;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class Joueur extends  Personnage{
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

    }
    /* Attaquer */
    @Override
    public void Attaquer(Ennemi target){
        if (target.isDefense()){
            target.setPointDeVie(target.getPointDeVie() - (this.getForce() / 2));
        }
        else {
            target.setPointDeVie(target.getPointDeVie() - this.getForce());
        }
        target.setDefense(false);
    }
    /* Defendre */
    @Override
    public void Defendre(){
        this.setDefense(true);
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
}

