package model;

public class Arme {
    private String nom;
    private int forceBoost;

    /* Contructeur */
    public Arme(String nom, int forceBoost) {
        this.nom = nom;
        this.forceBoost = forceBoost;
    }

    /* Récupérer le nom de l'arme */
    public String getNom() {
        return nom;
    }

    /* Mettre à jour le nom de l'arme */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /* Récupérer la valeur de la forceBoost de l'arme */
    public int getForceBoost() {
        return forceBoost;
    }

    /* Mettre à jour la valeur de la forceBoost */
    public void setForceBoost(int forceBoost) {
        this.forceBoost = forceBoost;
    }
}
