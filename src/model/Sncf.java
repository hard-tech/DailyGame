package model;

import java.util.ArrayList;

public class Sncf extends Ennemi{
    public Sncf(String nom, int pointDeVie, int force, ArrayList<String> loot) {
        super(nom, pointDeVie, force, loot);
    }

    public void MalaiseVoyageur(){
        System.out.println("Un voyageur à fait un malaise");
    }

    public void TrainAnnuler(){
        System.out.println("Votre train est annuler");
    }

    public void BagageAbbandone(){
        System.out.println("Un bagage est abandonner");
    }
}
