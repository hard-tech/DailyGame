package model;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

public class Ennemi extends Personnage implements Interactuable {
    /* Tableau contennant la liste des élément looter par les ennemies */
    private Dictionary loot = new Hashtable();

    public Ennemi(String nom, int pointDeVie, int force, boolean defense, Dictionary loot) {
        super(nom, pointDeVie, force, defense);
        this.loot = loot;
    }
    public void Raciste(Joueur target){
        System.out.println("");
    }

    @Override
    public void Attaquer(Personnage target) {
        super.Attaquer(target);
    }

    @Override
    public void Defendre(Personnage target) {

    }

    public Dictionary getLoot() {
        return loot;
    }

    public void setLoot(String nom, int valeur) {
        this.loot.put(nom, valeur);
    }


    @Override
    public String interagir(Object cible) {
        return "0";
    }
}
