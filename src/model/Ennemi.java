package model;

import java.util.ArrayList;
import java.util.List;

public class Ennemi extends Personnage{
    /* Tableau contennant la liste des élément looter par les ennemies */
    private ArrayList<String> loot = new ArrayList<String>() ;

    public Ennemi(String nom, int pointDeVie, int force, ArrayList<String> loot) {
        super(nom, pointDeVie, force);
        this.loot = loot;
    }

    public ArrayList<String> getLoot() {
        return loot;
    }

    public void setLoot(ArrayList<String> loot) {
        this.loot = loot;
    }
}
