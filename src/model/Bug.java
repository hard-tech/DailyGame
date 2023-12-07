package model;

import java.util.ArrayList;

public class Bug extends Ennemi{
    public Bug(String nom, int pointDeVie, int force, boolean defense, ArrayList<String> loot) {
        super(nom, pointDeVie, force, defense, loot);
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
        System.out.println("Chat GPT ne vous a pas bien aidé et le bug persiste. Vous perdez " +
                degat + " PV.");
    }

    @Override
    public void Defendre(Personnage target) {
        this.setDefense(true);
        System.out.println("Le bug vous donne une documentation pourrie pour faire semblant" +
                " de vous aider. (Défense)");
    }
}
