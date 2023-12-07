import model.*;

import java.util.ArrayList;
import java.util.Hashtable;
// import control.*;

public class Main {
    public static void main(String[] args) {
        Sncf kevin = new Sncf("Kévin", 30, 5, false, new ArrayList<>());
        Joueur killian = new Joueur("Killian", 30, 5, false, new Hashtable());
        killian.Attaquer(kevin);
      
        System.out.println(killian.Battle());
        kevin.Attaquer(killian);
      
        // Objet popo = new Objet("Potion","Heal",40);
        // killian.Utiliser(popo);
        System.out.println(kevin.getPointDeVie());

        Joueur test = new Joueur("test",100,10,false,new Hashtable());
        test.interagir(test);



    }
}