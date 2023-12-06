import model.*;

import java.util.ArrayList;
import java.util.Hashtable;
// import control.*;

public class Main {
    public static void main(String[] args) {
        Ennemi kevin = new Ennemi("Kévin", 30, 5, false, new ArrayList<>());
        Joueur killian = new Joueur("Killian", 30, 5, false, new Hashtable());
        killian.Attaquer(kevin);
        Objet popo = new Objet("Potion","Heal",30);

        System.out.println(popo.getNom());
        System.out.println(popo.getCapacite());
        System.out.println(popo.getValeur());
    }
}