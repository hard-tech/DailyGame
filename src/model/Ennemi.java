package model;

import java.util.ArrayList;
import java.util.List;

public class Ennemi {
    /* Tableau contennant la liste des élément looter par les ennemies */
    private ArrayList<String> loot = new ArrayList<String>() ;

    /* Capacité des ennemies */
    public void capacite(){
        System.out.println("Capacité des ennemies");
    }

    public Ennemi(ArrayList<String> loot) {
        this.loot = loot;
    }

    public ArrayList<String> getLoot() {
        return loot;
    }

    public void setLoot(ArrayList<String> loot) {
        this.loot = loot;
    }
}
