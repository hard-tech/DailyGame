import model.*;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
// import control.*;

public class Main {

    public void Start(){

        /* Début du jeu et choix de classe*/
        System.out.println("Bonjour jeune étudiant !!");
        System.out.println("Avant de commencer vous devez choisir une classe afin de commencer votre aventure !");
        System.out.println("Diférentes classes : ");
        System.out.println("1. Le codeur débutant : 30PV ; 5 ATTAQUE");
        System.out.println("2. Le codeur rapide : 25 PV ; 7 ATTAQUE ");
        System.out.println("3. Le codeur lent : 40PV ; 3 ATTAQUE");
        System.out.println("4. Le Codeur raciste : 28 PV ;  7 ATTAQUE soit fait *_2 soit rate son ATTAQUE_");
        Scanner scanner = new Scanner(System.in);
        int choixDeClasse = scanner.nextInt();


        while (choixDeClasse < 1 || choixDeClasse > 4){
            System.out.println("Veulliez choisir parmi les choix proposé");
            choixDeClasse = scanner.nextInt();
        }

        switch (choixDeClasse) {
            case 1:
                System.out.println("Vous avez choisi la classe codeur débutant");
                break;
            case 2:
                System.out.println("Vous avez choisi la classe codeur rapide");
                break;
            case 3:
                System.out.println("Vous avez choisi la classe codeur lent");
                break;
            case 4:
                System.out.println("Vous avez choisi la classe codeur raciste");
                break;
            default:
                System.out.println("Choix de classe non valide");
        }



        /*Premier choix du joueur et début du jeu*/
        System.out.println("Vous allez a présent pouvoir commencer votre journée de codeur");
        System.out.println("Dans ce jeu vous aller devoir faire des choix qui influencerons votre histoire");
        System.out.println("Toute journée commence d'abord par un réveil ! ");

        int day = 1;
        while(day <= 5){
            System.out.println("Jour actuel : " + day);


            /*début du cycle d'un jour de jeu*/
            System.out.println("Une fois lever que voulez vous faire ?");
            System.out.println("");

            day++;
        }

    }
    public static void main(String[] args) {
        Main mainInstance = new Main();
        Sncf kevin = new Sncf("Kévin", 30, 5, false, new ArrayList<>());
        Joueur killian = new Joueur("Killian", 30, 5, false, new Hashtable());
        killian.Attaquer(kevin);
      
        System.out.println(killian.Battle());
        kevin.Attaquer(killian);
      
        // Objet popo = new Objet("Potion","Heal",40);
        // killian.Utiliser(popo);
        System.out.println(kevin.getPointDeVie());

        //Joueur test = new Joueur("test",100,10,false,new Hashtable());
        //test.interagir(test);

        mainInstance.Start();




    }
}