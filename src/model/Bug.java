package model;

import java.util.ArrayList;

public class Bug extends Ennemi{
    public Bug(String nom, int pointDeVie, int force, ArrayList<String> loot) {
        super(nom, pointDeVie, force, loot);
    }

    public void MergeConflict(){
        System.out.println("Git ne peux pas automatiquement merge");
    }

    public void ErreurDeSyntax(){
        System.out.println("Vous avez fait une err de merde");
    }

    public void ErreurDeConnection(){
        System.out.println("La connection à crash");
    }
}
