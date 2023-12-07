import model.*;
import java.util.*;
// import control.*;

public class Main {
    Potion pause = new Potion("J'ai besoin d'une pause", "heal", 15);

    public boolean Fight(Joueur joueur, Ennemi ennemi){
        boolean ennemiVie = true;
        boolean victoire = false;
        boolean fight = true;
        boolean choixFait = true;
        while (fight){
            while (choixFait){
                System.out.println("Vous avez " + joueur.getPointDeVie() + " PV");
                String choix = joueur.interagir(joueur);
                switch (choix) {
                    case "1":
                        joueur.Attaquer(ennemi);
                        choixFait = false;
                        break;
                    case "2":
                        joueur.Defendre(ennemi);
                        choixFait = false;
                        break;
                    case "3":
                        if (joueur.getInventaire().get("J'ai besoin d'une pause") > 0){
                            joueur.UtiliserObjet(joueur, pause);
                            choixFait = false;
                        }
                        else{
                            System.out.println("T'as pris une pause y'a 2min frère...");
                            System.out.println("");
                        }
                        break;
                    case "4":
                        System.out.println("Votre ennemi " + ennemi.getNom() + " a " + ennemi.getPointDeVie()
                        + " PV et " + ennemi.getForce() + " de force.");
                        choixFait = false;
                        break;
                    default:
                        System.out.println("Choix invalide");
                }
            }

            if (ennemi.getPointDeVie() <= 0) {
                joueur.lootEnnemi(ennemi);
                System.out.println("");
                if (joueur.getExp() >= 35){
                    joueur.levelUp();
                }
                System.out.println(joueur.getInventaire());
                ennemiVie = false;
                fight = false;
                victoire = true;
            }

            try {
                Thread.sleep(1500);  // Pause d'une seconde (1000 millisecondes)
            } catch (InterruptedException e) {
                // Gestion de l'exception si la pause est interrompue
                e.printStackTrace();
            }

            if (ennemiVie) {
                System.out.println("");
                System.out.println("Attaque ennemi");
                ennemi.Attaquer(joueur);
                choixFait = true;
                System.out.println("");
                if (joueur.getPointDeVie() <= 0) {
                    fight = false;
                }
            }
        }
        return victoire;
    }
    public static void main(String[] args) {
        Sncf train1 = new Sncf("RER B",20,3,false, new Hashtable<>());
        train1.setLoot("exp", 15);
        Sncf train2 = new Sncf("RER A",20,3,false, new Hashtable<>());
        train2.setLoot("exp", 15);
        Sncf train3 = new Sncf("RER C",25,3,false, new Hashtable<>());
        train3.setLoot("exp", 15);

        Bug bug1 = new Bug("Erreur 404",22,5,false, new Hashtable<>());
        bug1.setLoot("exp", 20);
        bug1.setLoot("pins", 15);
        Bug bug2 = new Bug("Erreur 405",25,6,false, new Hashtable<>());
        bug2.setLoot("exp", 30);
        bug2.setLoot("pins", 25);
        Bug bug3 = new Bug("403 Forbidden",30,7,false, new Hashtable<>());
        bug3.setLoot("exp", 40);
        bug3.setLoot("pins", 35);

        Po po1 = new Po("François",50,10,false, new Hashtable<>());
        po1.setLoot("exp", 65);
        po1.setLoot("pins", 80);
        Po po2 = new Po("Benoit",60,12,false, new Hashtable<>());
        po2.setLoot("exp", 1000);
        po2.setLoot("pins", 1000);
        Po po3 = new Po("Joachim",1000,1,false, new Hashtable<>());
        po3.setLoot("exp", 1);
        po3.setLoot("pins", 1);
        Main mainInstance = new Main();
    }
}