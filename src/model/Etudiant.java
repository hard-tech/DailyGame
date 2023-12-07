package model;

import java.util.Dictionary;

public class Etudiant extends Ennemi{
    public Etudiant(String nom, int pointDeVie, int force, boolean defense, Dictionary loot) {
        super(nom, pointDeVie, force, defense, loot);
    }

    @Override
    public void Raciste(Joueur target){
        System.out.println("Kévin est fatigué mais il est prêt à sortir sa botte secrète.");
        try {
            Thread.sleep(1500);  // Pause d'une seconde (1000 millisecondes)
        } catch (InterruptedException e) {
            // Gestion de l'exception si la pause est interrompue
            e.printStackTrace();
        }
        target.setPointDeVie(1);
        System.out.println("Kévin vous traîte de RACISTE vous tombé à 1 PV.");
        try {
            Thread.sleep(1500);  // Pause d'une seconde (1000 millisecondes)
        } catch (InterruptedException e) {
            // Gestion de l'exception si la pause est interrompue
            e.printStackTrace();
        }
        System.out.println("Vous vous relevez fasse à ce mensonge grotesque et vous mettez votre plat " +
                "dans le micro-onde avant Kévin ce qui l'achève.");
        this.setPointDeVie(0);
    }
    @Override
    public void Attaquer(Personnage target) {
        System.out.println("Kévin vous tape avec ses points. Mais cela ne vous fait pas de dégâts.");
    }

    @Override
    public void Defendre(Personnage target) {
        this.setDefense(true);
        System.out.println("Kévin essaye de se défendre mais n'y arrive pas.");
    }
}
