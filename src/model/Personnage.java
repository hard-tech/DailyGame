package model;

public class Personnage implements Interactuable {
    private String nom;
    private int pointDeVie;
    private int force;

    public String getNom() {
        return nom;
    }

    public int getPointDeVie() {
        return pointDeVie;
    }

    public int getForce() {
        return force;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPointDeVie(int pointDeVie) {
        this.pointDeVie = pointDeVie;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public Personnage(String nom, int pointDeVie, int force) {
        this.nom = nom;
        this.pointDeVie = pointDeVie;
        this.force = force;
    }

    /* Methode qui à pour action d'attaquer */
    public void Attaquer(){
        System.out.println("Le personnage attaque");
    }

    /* Methode qui à pour action de se défendre */
    public void Defendre(){
        System.out.println("Le personnage se défend");
    }

    @Override
    public void interagir() {

    }
}
