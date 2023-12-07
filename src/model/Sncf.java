package model;

import java.util.ArrayList;
import java.util.Dictionary;

public class Sncf extends Ennemi{
    public Sncf(String nom, int pointDeVie, int force, boolean defense, Dictionary loot) {
        super(nom, pointDeVie, force, defense, loot);
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

    @Override
    public void Attaquer(Personnage target) {
        int degat = 0;
        if (target.isDefense()) {
            target.setPointDeVie(target.getPointDeVie() - (this.getForce() / 2));
            degat = (this.getForce() / 2);
        } else {
            target.setPointDeVie(target.getPointDeVie() - this.getForce());
            degat = this.getForce();
        }
        target.setDefense(false);
        System.out.println("Une annonce SNCF retenti pour vous dire que vous allez " +
                "devoir attendre 10min de plus. Vous perdez " + degat + " PV.");
    }

    @Override
    public void Defendre(Personnage target) {
        this.setDefense(true);
        System.out.println("Un controleur arrive et contrôle les titres de transports. " +
                "(Défense)");
    }

}
