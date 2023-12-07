package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public  abstract class Objet implements Interactuable {

    private String nom;
    private String capacite = "";
    private int valeur = 0;

    @Override
    public String interagir(Object cible) {
        return null;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCapacite() {
        return capacite;
    }

    public void setCapacite(String capacite) {
        this.capacite = capacite;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public Objet(String nom, String capacite, int valeur) {
        this.nom = nom;
        this.capacite = capacite;
        this.valeur = valeur;
    }
}
