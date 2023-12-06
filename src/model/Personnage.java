package model;
import java.util.Scanner;
public class Personnage {
    private String nom;
    private int pointDeVie;
    private int force;
    private boolean defense;

    public boolean isDefense() {
        return defense;
    }

    public void setDefense(boolean defense) {
        this.defense = defense;
    }

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

    public Personnage(String nom, int pointDeVie, int force, boolean defense) {
        this.nom = nom;
        this.pointDeVie = pointDeVie;
        this.force = force;
        this.defense = defense;
    }

    /* Methode qui à pour action d'attaquer */
    public void Attaquer(Ennemi target){
        if (target.isDefense()){
            target.setPointDeVie(target.getPointDeVie() - (this.getForce() / 2));
        }
        else {
            target.setPointDeVie(target.getPointDeVie() - this.getForce());
        }
        target.setDefense(false);
    }

    /* Methode qui à pour action de se défendre */
    public void Defendre(){
        this.setDefense(true);
    }


}
