package model;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class Joueur extends  Personnage{
    private Dictionary inventaire = new Hashtable();
    // Ex : phoneBook.put("Key", "Value");


    public Joueur(String nom, int pointDeVie, int force, Dictionary inventaire) {
        super(nom, pointDeVie, force);
        this.inventaire = inventaire;
    }

    /* Utiliser un objet */
    public void Utiliser(){
        System.out.println("Obj Utiliser");
    }
    /* Attaquer */
    @Override
    public void Attaquer(){
        System.out.println("Attaque");
    }
    /* Defendre */
    @Override
    public void Defendre(){

    }
    // équiper
    public void Equiper(){
        System.out.println("Equiper");
    }
    // déséquiper
    public void Desequiper(){
        System.out.println("Déséquiper");
    }
}
