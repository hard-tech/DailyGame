package model;

import java.util.ArrayList;

public class Po extends Ennemi{
    public Po(String nom, int pointDeVie, int force, boolean defense, ArrayList<String> loot) {
        super(nom, pointDeVie, force, defense, loot);
    }

    public void Slide(){
        System.out.println("Vous vous faite attaquer par des slides");
    }

    public void Retard(){
        System.out.println("Attaque de retard");
    }

    public void Presentation(){
        System.out.println("Présentation assomante !");
    }
}
