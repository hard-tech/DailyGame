package model;

import java.util.ArrayList;
import java.util.List;

public class Ennemi extends Personnage implements Interactuable {
    /* Tableau contennant la liste des élément looter par les ennemies */
    private ArrayList<String> loot = new ArrayList<String>();

    public Ennemi(String nom, int pointDeVie, int force, boolean defense, ArrayList<String> loot) {
        super(nom, pointDeVie, force, defense);
        this.loot = loot;
    }

    @Override
    public void Attaquer(Ennemi target) {
        super.Attaquer(target);
    }

    @Override
    public void Defendre() {
        super.Defendre();
    }

    public ArrayList<String> getLoot() {
        return loot;
    }

    public void setLoot(ArrayList<String> loot) {
        this.loot = loot;
    }


    @Override
    public void interagir(Object cible) {

    }
}
