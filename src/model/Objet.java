package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Objet {
    private String nom;
    private int capacite;

    private Map<String, Integer> capaciteCheck = new HashMap<>() {{
        put("Potion", 3);
    }};



    public String getNom() {
        return nom;
    }

    public Map getCapaciteCheck() {
        return capaciteCheck;
    }

    public void setCapaciteCheck(Map capaciteCheck) {
        this.capaciteCheck = capaciteCheck;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public Objet(String nom, int capacite) {
        this.nom = nom;
        this.capacite = capacite;
    }
}