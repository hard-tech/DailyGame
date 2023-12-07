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
        System.out.println("Le Po vous dis qu'il faut juste chercher sur internet (gros noob). " +
                "Vous perdez " + degat + " PV.");
    }

    @Override
    public void Defendre(Personnage target) {
        this.setDefense(true);
        System.out.println("Le Po a un coup de fil à passer il ne vous écoute plus. (Défense)");
    }
}
