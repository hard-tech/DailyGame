package model;

public  abstract class Objet implements Interactuable {
    private String nom;
    private String capacite;

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

    public Objet(String nom, String capacite) {
        this.nom = nom;
        this.capacite = capacite;
    }

    @Override
    public void interagir() {

    }
}
