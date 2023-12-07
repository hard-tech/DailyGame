import model.*;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
// import control.*;

public class Main {

    Sncf train1 = new Sncf("RER B",100,5,false,new ArrayList<>());
    Sncf train2 = new Sncf("RER A",110,4,false,new ArrayList<>());
    Sncf train3 = new Sncf("RER C",95,7,false,new ArrayList<>());

    Bug bug1 = new Bug("Erreur 404",50,2,false,new ArrayList<>());
    Bug bug2 = new Bug("Erreur 405",45,3,false,new ArrayList<>());
    Bug bug3 = new Bug("403 Forbidden",60,4,false,new ArrayList<>());

    Po po1 = new Po("François",200,15,false,new ArrayList<>());
    Po po2 = new Po("Benoit",50,25,false,new ArrayList<>());
    Po po3 = new Po("Joachim",150,18,false,new ArrayList<>());

    public Boolean ChoixEvenementJoueur(String question){
        boolean choixX = false;
        String prendreDejeuner = "";

        while (!prendreDejeuner.toLowerCase().equals("y") && !prendreDejeuner.toLowerCase().equals("n")) {
            System.out.println(question);
            Scanner dejeuner = new Scanner(System.in);
            prendreDejeuner = dejeuner.nextLine();

            if (prendreDejeuner.toLowerCase().equals("y")) {
                choixX = true;
            }
            if (prendreDejeuner.toLowerCase().equals("n")) {
                choixX = false;
            }
        }
        return choixX;
    }

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

        System.out.println("Comment vous applez vous ? : ");
        Scanner name = new Scanner(System.in);
        String nomJoueur = name.nextLine();



        while (choixDeClasse < 1 || choixDeClasse > 4){
            System.out.println("Veulliez choisir parmi les choix proposé");
            choixDeClasse = scanner.nextInt();
        }

        Joueur joueur = null;
        
        switch (choixDeClasse) {
            case 1:
                System.out.println("Vous avez choisi la classe codeur débutant");
                Joueur debutant = new Joueur(nomJoueur, 30,5,false, new Hashtable());
                joueur = debutant;
                break;
            case 2:
                System.out.println("Vous avez choisi la classe codeur rapide");
                Joueur rapide = new Joueur(nomJoueur, 25,7,false, new Hashtable());
                joueur = rapide;
                break;
            case 3:
                System.out.println("Vous avez choisi la classe codeur lent");
                Joueur lent = new Joueur(nomJoueur, 40,3,false, new Hashtable());
                joueur = lent;
                break;
            case 4:
                System.out.println("Vous avez choisi la classe codeur raciste");
                Joueur raciste = new Joueur(nomJoueur, 28,7,false, new Hashtable());
                joueur = raciste;
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


            /* Début du cycle d'un jour de jeu */

            System.out.println("Vous venez de vous lever.");
            boolean dejeuner = ChoixEvenementJoueur("Maintenant que vous êtes debout, voulez-vous prendre votre petit déjeuner ? (y/n) : ");
            boolean bienSapper = ChoixEvenementJoueur("Sachan que vous risquer d'être en retard voulez vous bien vous sapper ^_^ ? (y/n) : ");

            /* Le joueur avez prit votre petit déjeuner (donc ...) */
            if(dejeuner){
                    if(bienSapper){
                        /* il se fait compimenter dans le train */
                        System.out.println("Quelle fier allure ! (une fille vous fait clin d'oeuil)");

                        /* Un combat se lance contre la SNCF */
                        /* fonction fight "return Bool True/False" fight(joueur, train1) */

                        /* Le joueur arrive à la coding factory en retard !*/
                        System.out.println("Vous êtes arrivé à la coding factory. Mais ! En retard ...");

                        /* Le Po enguele l'élève en retard */
                        System.out.println(String.format("Le PO : Alors, %s vous arrivez en retard ! (le PO s'énèrve, ce qui vous stress ...)", joueur.getNom()));

                        /* Comme le joueur à prit un bon repas, il ne répond pas au PO */
                        System.out.println(String.format("%s : Désoler sa ne reproduira plus.", joueur.getNom()));

                        /* Le cour commence, le jouer peux choisire de faire sa Daily ou pas ! */
                        Boolean Daily = ChoixEvenementJoueur("Le cour a commencé, voulez vous faire votre Daily ?");
                        if (Daily){
                            /* Le joueur à fait sa Daily, (donc ...) */
                            /* Le joueur est plus performent durant la journé */
                            System.out.println("Comme vous avez fait votre daily, vous êtes mieux préparer à affronter vos problèmes.");


                            /* Un combat se lance contre un Bug de niveau Easy */
                            System.out.println("Vous rencontrez un bug lors de votre projet !");
                            /* fonction fight "return Bool True/False" fight(joueur, bug1) */

                            /* Malheuresuement le joueur rencontre un autre problème */
                            System.out.println("Malheuresement un second bug apparait !");
                            /* fonction fight "return Bool True/False" fight(joueur,bug1) */


                            /* le joueur rencontre un combat pour chauffer son repas lors de la pause */
                            System.out.println("Vous l'avez bien mérité vous prenez une pause pour manger");
                            System.out.println("Malheuresement un étudiant vous empeche de chauffer votre repas");
                            boolean combatRepas = ChoixEvenementJoueur("Voulez vous l'affrontez ??");

                            if (combatRepas){

                                /* un combat se lance avec l'étudiant */
                                System.out.println("Vous avez décidé d'affronter l'étudiant !");
                                /* fonction fight "return Bool True/false" fight (joueur,élève de l'école) */

                                /* résultat du combat avec l'élève */
                                if (/* retour du bool du combat */){

                                    /* Vous arrivez à l'heure et le Po de dis rien vos pv sont restaurés */
                                    System.out.println("Vous avez battu l'étudiant");
                                    System.out.println("Vous arrivez à l'heure et le Po de dis rien vos pv sont restaurés");
                                    /* restaurer les pv du joueur */

                                    /* Un nouveau problème est rencontré pendant votre projet */
                                    System.out.println("Un nouveau problème est rencontré pendant votre projet");
                                    /* fonction fight "return Bool True/False" fight(joueur,bug1) */

                                    /* Combat contre un gros bug */
                                    System.out.println("Pas de chance un bug majeur est rencontrer");
                                    /* fonction fight "return Bool True/False" fight(joueur,bug3) */

                                    /* choix de la pause café */
                                    System.out.println("Votre journée est bientot terminé !");
                                    System.out.println("Voulez vous prendre une pause café avant la fin de la journée ?");
                                    boolean pauseCafé = ChoixEvenementJoueur("voulez prendre une pause café ??");

                                    if (pauseCafé){

                                        /* bois un café et restaure ces pv */
                                        System.out.println("Vous avez bu un café vos PV sont restaurés !!");

                                        /* affronte le po */
                                        System.out.println("Votre journée touche à sa fin maintenant vous devez affronter votre PO");
                                        /* fonction fight "return Bool True/False" fight(joueur,PO) */


                                    }else {

                                        /* na pas bu de café */
                                        System.out.println("Vous n'avez pas bu de café");

                                        /* combat contre un bug */
                                        System.out.println("Vous devez affronter un dernier bug avant de voir le PO");
                                        /* fonction fight "return Bool True/False" fight(joueur,bug1) */

                                        /* combat contre le Po */
                                        System.out.println("Vous y etes batter le PO pour finir la journée");
                                        /* fonction fight "return Bool True/False" fight(joueur,PO) */


                                    }




                                }
                                else {

                                    /* Vous arrivez en retard et le Po s'énerve contre vous */
                                    System.out.println("Vous arrivez en retard et le Po s'énerve contre vous");

                                    /* Un nouveau problème est rencontré pendant votre projet */
                                    System.out.println("Un nouveau problème est rencontré pendant votre projet");
                                    /* fonction fight "return Bool True/False" fight(joueur,bug1) */

                                    /* Combat contre un gros bug */
                                    System.out.println("Pas de chance un bug majeur est rencontrer");
                                    /* fonction fight "return Bool True/False" fight(joueur,bug3) */

                                    /* choix de la pause café */
                                    System.out.println("Votre journée est bientot terminé !");
                                    System.out.println("Voulez vous prendre une pause café avant la fin de la journée ?");
                                    boolean pauseCafé = ChoixEvenementJoueur("voulez prendre une pause café ??");

                                    if (pauseCafé){

                                        /* bois un café et restaure ces pv */
                                        System.out.println("Vous avez bu un café vos PV sont restaurés !!");

                                        /* affronte le po */
                                        System.out.println("Votre journée touche à sa fin maintenant vous devez affronter votre PO");
                                        /* fonction fight "return Bool True/False" fight(joueur,PO) */

                                        /* affronte benoit */
                                        System.out.println("Vous pensiez avoir fini votre journée mais benoit vous lance une attaque administrative");
                                        /* fonction fight "return Bool True/False" fight(joueur,Benoit) */


                                    }else {

                                        /* na pas bu de café */
                                        System.out.println("Vous n'avez pas bu de café");

                                        /* combat contre un bug */
                                        System.out.println("Vous devez affronter un dernier bug avant de voir le PO");
                                        /* fonction fight "return Bool True/False" fight(joueur,bug1) */

                                        /* combat contre le Po */
                                        System.out.println("Vous y etes batter le PO pour finir la journée");
                                        /* fonction fight "return Bool True/False" fight(joueur,PO) */

                                        /* affronte benoit */
                                        System.out.println("Vous pensiez avoir fini votre journée mais benoit vous lance une attaque administrative");
                                        /* fonction fight "return Bool True/False" fight(joueur,Benoit) */


                                    }



                                }


                            }
                            else {
                                /* Vous arrivez en retard et le Po s'énerve contre vous */
                                System.out.println("Vous arrivez en retard et le Po s'énerve contre vous");

                                /* Un nouveau problème est rencontré pendant votre projet */
                                System.out.println("Un nouveau problème est rencontré pendant votre projet");
                                /* fonction fight "return Bool True/False" fight(joueur,bug1) */

                                /* Combat contre un gros bug */
                                System.out.println("Pas de chance un bug majeur est rencontrer");
                                /* fonction fight "return Bool True/False" fight(joueur,bug3) */

                                /* choix de la pause café */
                                System.out.println("Votre journée est bientot terminé !");
                                System.out.println("Voulez vous prendre une pause café avant la fin de la journée ?");
                                boolean pauseCafé = ChoixEvenementJoueur("voulez prendre une pause café ??");

                                if (pauseCafé){

                                    /* bois un café et restaure ces pv */
                                    System.out.println("Vous avez bu un café vos PV sont restaurés !!");

                                    /* affronte le po */
                                    System.out.println("Votre journée touche à sa fin maintenant vous devez affronter votre PO");
                                    /* fonction fight "return Bool True/False" fight(joueur,PO) */

                                    /* affronte benoit */
                                    System.out.println("Vous pensiez avoir fini votre journée mais benoit vous lance une attaque administrative");
                                    /* fonction fight "return Bool True/False" fight(joueur,Benoit) */


                                }else {

                                    /* na pas bu de café */
                                    System.out.println("Vous n'avez pas bu de café");

                                    /* combat contre un bug */
                                    System.out.println("Vous devez affronter un dernier bug avant de voir le PO");
                                    /* fonction fight "return Bool True/False" fight(joueur,bug1) */

                                    /* combat contre le Po */
                                    System.out.println("Vous y etes batter le PO pour finir la journée");
                                    /* fonction fight "return Bool True/False" fight(joueur,PO) */

                                    /* affronte benoit */
                                    System.out.println("Vous pensiez avoir fini votre journée mais benoit vous lance une attaque administrative");
                                    /* fonction fight "return Bool True/False" fight(joueur,Benoit) */


                                }



                            }

                            /*  */
                        }
                        else{
                            /* Le joueur n'a pas fait sa Daily, (donc ...) */

                            /* Le joueur est moins performent durant la journé */
                            System.out.println("Comme vous n'avez pas fait votre daily, vous êtes mal préparer à affronter vos problèmes.");


                            /* Un combat se lance contre un Bug de niveau medium */
                            System.out.println("Vous rencontrez un bug lors de votre projet !");
                            /* fonction fight "return Bool True/False" fight(joueur, bug2) */

                            /* Malheuresuement le joueur rencontre un autre problème */
                            System.out.println("Malheuresement un second bug apparait !");
                            /* fonction fight "return Bool True/False" fight(joueur,bug1) */


                            /* le joueur rencontre un combat pour chauffer son repas lors de la pause */
                            System.out.println("Vous l'avez bien mérité vous prenez une pause pour manger");
                            System.out.println("Malheuresement un étudiant vous empeche de chauffer votre repas");
                            boolean combatRepas = ChoixEvenementJoueur("Voulez vous l'affrontez ??");

                            if (combatRepas){

                                /* un combat se lance avec l'étudiant */
                                System.out.println("Vous avez décidé d'affronter l'étudiant !");
                                /* fonction fight "return Bool True/false" fight (joueur,élève de l'école) */

                                /* résultat du combat avec l'élève */
                                if (/* retour du bool du combat */){

                                    /* Vous arrivez à l'heure et le Po de dis rien vos pv sont restaurés */
                                    System.out.println("Vous avez battu l'étudiant");
                                    System.out.println("Vous arrivez à l'heure et le Po de dis rien vos pv sont restaurés");
                                    /* restaurer les pv du joueur */

                                    /* Un nouveau problème est rencontré pendant votre projet */
                                    System.out.println("Un nouveau problème est rencontré pendant votre projet");
                                    /* fonction fight "return Bool True/False" fight(joueur,bug1) */

                                    /* Combat contre un gros bug */
                                    System.out.println("Pas de chance un bug majeur est rencontrer");
                                    /* fonction fight "return Bool True/False" fight(joueur,bug3) */

                                    /* choix de la pause café */
                                    System.out.println("Votre journée est bientot terminé !");
                                    System.out.println("Voulez vous prendre une pause café avant la fin de la journée ?");
                                    boolean pauseCafé = ChoixEvenementJoueur("voulez prendre une pause café ??");

                                    if (pauseCafé){

                                        /* bois un café et restaure ces pv */
                                        System.out.println("Vous avez bu un café vos PV sont restaurés !!");

                                        /* affronte le po */
                                        System.out.println("Votre journée touche à sa fin maintenant vous devez affronter votre PO");
                                        /* fonction fight "return Bool True/False" fight(joueur,PO) */


                                    }else {

                                        /* na pas bu de café */
                                        System.out.println("Vous n'avez pas bu de café");

                                        /* combat contre un bug */
                                        System.out.println("Vous devez affronter un dernier bug avant de voir le PO");
                                        /* fonction fight "return Bool True/False" fight(joueur,bug1) */

                                        /* combat contre le Po */
                                        System.out.println("Vous y etes batter le PO pour finir la journée");
                                        /* fonction fight "return Bool True/False" fight(joueur,PO) */


                                    }




                                }
                                else {

                                    /* Vous arrivez en retard et le Po s'énerve contre vous */
                                    System.out.println("Vous arrivez en retard et le Po s'énerve contre vous");

                                    /* Un nouveau problème est rencontré pendant votre projet */
                                    System.out.println("Un nouveau problème est rencontré pendant votre projet");
                                    /* fonction fight "return Bool True/False" fight(joueur,bug1) */

                                    /* Combat contre un gros bug */
                                    System.out.println("Pas de chance un bug majeur est rencontrer");
                                    /* fonction fight "return Bool True/False" fight(joueur,bug3) */

                                    /* choix de la pause café */
                                    System.out.println("Votre journée est bientot terminé !");
                                    System.out.println("Voulez vous prendre une pause café avant la fin de la journée ?");
                                    boolean pauseCafé = ChoixEvenementJoueur("voulez prendre une pause café ??");

                                    if (pauseCafé){

                                        /* bois un café et restaure ces pv */
                                        System.out.println("Vous avez bu un café vos PV sont restaurés !!");

                                        /* affronte le po */
                                        System.out.println("Votre journée touche à sa fin maintenant vous devez affronter votre PO");
                                        /* fonction fight "return Bool True/False" fight(joueur,PO) */

                                        /* affronte benoit */
                                        System.out.println("Vous pensiez avoir fini votre journée mais benoit vous lance une attaque administrative");
                                        /* fonction fight "return Bool True/False" fight(joueur,Benoit) */


                                    }else {

                                        /* na pas bu de café */
                                        System.out.println("Vous n'avez pas bu de café");

                                        /* combat contre un bug */
                                        System.out.println("Vous devez affronter un dernier bug avant de voir le PO");
                                        /* fonction fight "return Bool True/False" fight(joueur,bug1) */

                                        /* combat contre le Po */
                                        System.out.println("Vous y etes batter le PO pour finir la journée");
                                        /* fonction fight "return Bool True/False" fight(joueur,PO) */

                                        /* affronte benoit */
                                        System.out.println("Vous pensiez avoir fini votre journée mais benoit vous lance une attaque administrative");
                                        /* fonction fight "return Bool True/False" fight(joueur,Benoit) */


                                    }



                                }


                            }
                            else {
                                /* Vous arrivez en retard et le Po s'énerve contre vous */
                                System.out.println("Vous arrivez en retard et le Po s'énerve contre vous");

                                /* Un nouveau problème est rencontré pendant votre projet */
                                System.out.println("Un nouveau problème est rencontré pendant votre projet");
                                /* fonction fight "return Bool True/False" fight(joueur,bug1) */

                                /* Combat contre un gros bug */
                                System.out.println("Pas de chance un bug majeur est rencontrer");
                                /* fonction fight "return Bool True/False" fight(joueur,bug3) */

                                /* choix de la pause café */
                                System.out.println("Votre journée est bientot terminé !");
                                System.out.println("Voulez vous prendre une pause café avant la fin de la journée ?");
                                boolean pauseCafé = ChoixEvenementJoueur("voulez prendre une pause café ??");

                                if (pauseCafé){

                                    /* bois un café et restaure ces pv */
                                    System.out.println("Vous avez bu un café vos PV sont restaurés !!");

                                    /* affronte le po */
                                    System.out.println("Votre journée touche à sa fin maintenant vous devez affronter votre PO");
                                    /* fonction fight "return Bool True/False" fight(joueur,PO) */

                                    /* affronte benoit */
                                    System.out.println("Vous pensiez avoir fini votre journée mais benoit vous lance une attaque administrative");
                                    /* fonction fight "return Bool True/False" fight(joueur,Benoit) */


                                }else {

                                    /* na pas bu de café */
                                    System.out.println("Vous n'avez pas bu de café");

                                    /* combat contre un bug */
                                    System.out.println("Vous devez affronter un dernier bug avant de voir le PO");
                                    /* fonction fight "return Bool True/False" fight(joueur,bug1) */

                                    /* combat contre le Po */
                                    System.out.println("Vous y etes batter le PO pour finir la journée");
                                    /* fonction fight "return Bool True/False" fight(joueur,PO) */

                                    /* affronte benoit */
                                    System.out.println("Vous pensiez avoir fini votre journée mais benoit vous lance une attaque administrative");
                                    /* fonction fight "return Bool True/False" fight(joueur,Benoit) */


                                }



                            }
                        }
                    }else{
                        /* Le joueur à déjeneur mais ne c'est pas bien sapper ! (donc ...) */
                        System.out.println("étant donner que vous n'avez pas perdu de temps, vous n'est pas en retard");
                        System.out.println("et vous n'avez pas rencontrer de problème en chemain");

                        /* Le joueur arrive à la coding factory à l'heure ! */
                        System.out.println("Vous arriver à la Coding à l'heure");

                        /* Le cour commence, le jouer peux choisire de faire sa Daily ou pas ! */
                        Boolean Daily = ChoixEvenementJoueur("Le cour a commencé, voulez vous faire votre Daily ?");
                        if (Daily){
                            /* Le joueur à fait sa Daily, (donc ...) */
                            /* Le joueur est plus performent durant la journé */
                            System.out.println("Comme vous avez fait votre daily, vous êtes mieux préparer à affronter vos problèmes.");


                            /* Un combat se lance contre un Bug de niveau Easy */
                            System.out.println("Vous rencontrez un bug lors de votre projet !");
                            /* fonction fight "return Bool True/False" fight(joueur, bug1) */

                            /* Malheuresuement le joueur rencontre un autre problème */
                            System.out.println("Malheuresement un second bug apparait !");
                            /* fonction fight "return Bool True/False" fight(joueur,bug1) */


                            /* le joueur rencontre un combat pour chauffer son repas lors de la pause */
                            System.out.println("Vous l'avez bien mérité vous prenez une pause pour manger");
                            System.out.println("Malheuresement un étudiant vous empeche de chauffer votre repas");
                            boolean combatRepas = ChoixEvenementJoueur("Voulez vous l'affrontez ??");

                            if (combatRepas){

                                /* un combat se lance avec l'étudiant */
                                System.out.println("Vous avez décidé d'affronter l'étudiant !");
                                /* fonction fight "return Bool True/false" fight (joueur,élève de l'école) */

                                /* résultat du combat avec l'élève */
                                if (/* retour du bool du combat */){

                                    /* Vous arrivez à l'heure et le Po de dis rien vos pv sont restaurés */
                                    System.out.println("Vous avez battu l'étudiant");
                                    System.out.println("Vous arrivez à l'heure et le Po de dis rien vos pv sont restaurés");
                                    /* restaurer les pv du joueur */

                                    /* Un nouveau problème est rencontré pendant votre projet */
                                    System.out.println("Un nouveau problème est rencontré pendant votre projet");
                                    /* fonction fight "return Bool True/False" fight(joueur,bug1) */

                                    /* Combat contre un gros bug */
                                    System.out.println("Pas de chance un bug majeur est rencontrer");
                                    /* fonction fight "return Bool True/False" fight(joueur,bug3) */

                                    /* choix de la pause café */
                                    System.out.println("Votre journée est bientot terminé !");
                                    System.out.println("Voulez vous prendre une pause café avant la fin de la journée ?");
                                    boolean pauseCafé = ChoixEvenementJoueur("voulez prendre une pause café ??");

                                    if (pauseCafé){

                                        /* bois un café et restaure ces pv */
                                        System.out.println("Vous avez bu un café vos PV sont restaurés !!");

                                        /* affronte le po */
                                        System.out.println("Votre journée touche à sa fin maintenant vous devez affronter votre PO");
                                        /* fonction fight "return Bool True/False" fight(joueur,PO) */


                                    }else {

                                        /* na pas bu de café */
                                        System.out.println("Vous n'avez pas bu de café");

                                        /* combat contre un bug */
                                        System.out.println("Vous devez affronter un dernier bug avant de voir le PO");
                                        /* fonction fight "return Bool True/False" fight(joueur,bug1) */

                                        /* combat contre le Po */
                                        System.out.println("Vous y etes batter le PO pour finir la journée");
                                        /* fonction fight "return Bool True/False" fight(joueur,PO) */


                                    }




                                }
                                else {

                                    /* Vous arrivez en retard et le Po s'énerve contre vous */
                                    System.out.println("Vous arrivez en retard et le Po s'énerve contre vous");

                                    /* Un nouveau problème est rencontré pendant votre projet */
                                    System.out.println("Un nouveau problème est rencontré pendant votre projet");
                                    /* fonction fight "return Bool True/False" fight(joueur,bug1) */

                                    /* Combat contre un gros bug */
                                    System.out.println("Pas de chance un bug majeur est rencontrer");
                                    /* fonction fight "return Bool True/False" fight(joueur,bug3) */

                                    /* choix de la pause café */
                                    System.out.println("Votre journée est bientot terminé !");
                                    System.out.println("Voulez vous prendre une pause café avant la fin de la journée ?");
                                    boolean pauseCafé = ChoixEvenementJoueur("voulez prendre une pause café ??");

                                    if (pauseCafé){

                                        /* bois un café et restaure ces pv */
                                        System.out.println("Vous avez bu un café vos PV sont restaurés !!");

                                        /* affronte le po */
                                        System.out.println("Votre journée touche à sa fin maintenant vous devez affronter votre PO");
                                        /* fonction fight "return Bool True/False" fight(joueur,PO) */

                                        /* affronte benoit */
                                        System.out.println("Vous pensiez avoir fini votre journée mais benoit vous lance une attaque administrative");
                                        /* fonction fight "return Bool True/False" fight(joueur,Benoit) */


                                    }else {

                                        /* na pas bu de café */
                                        System.out.println("Vous n'avez pas bu de café");

                                        /* combat contre un bug */
                                        System.out.println("Vous devez affronter un dernier bug avant de voir le PO");
                                        /* fonction fight "return Bool True/False" fight(joueur,bug1) */

                                        /* combat contre le Po */
                                        System.out.println("Vous y etes batter le PO pour finir la journée");
                                        /* fonction fight "return Bool True/False" fight(joueur,PO) */

                                        /* affronte benoit */
                                        System.out.println("Vous pensiez avoir fini votre journée mais benoit vous lance une attaque administrative");
                                        /* fonction fight "return Bool True/False" fight(joueur,Benoit) */


                                    }



                                }


                            }
                            else {
                                /* Vous arrivez en retard et le Po s'énerve contre vous */
                                System.out.println("Vous arrivez en retard et le Po s'énerve contre vous");

                                /* Un nouveau problème est rencontré pendant votre projet */
                                System.out.println("Un nouveau problème est rencontré pendant votre projet");
                                /* fonction fight "return Bool True/False" fight(joueur,bug1) */

                                /* Combat contre un gros bug */
                                System.out.println("Pas de chance un bug majeur est rencontrer");
                                /* fonction fight "return Bool True/False" fight(joueur,bug3) */

                                /* choix de la pause café */
                                System.out.println("Votre journée est bientot terminé !");
                                System.out.println("Voulez vous prendre une pause café avant la fin de la journée ?");
                                boolean pauseCafé = ChoixEvenementJoueur("voulez prendre une pause café ??");

                                if (pauseCafé){

                                    /* bois un café et restaure ces pv */
                                    System.out.println("Vous avez bu un café vos PV sont restaurés !!");

                                    /* affronte le po */
                                    System.out.println("Votre journée touche à sa fin maintenant vous devez affronter votre PO");
                                    /* fonction fight "return Bool True/False" fight(joueur,PO) */

                                    /* affronte benoit */
                                    System.out.println("Vous pensiez avoir fini votre journée mais benoit vous lance une attaque administrative");
                                    /* fonction fight "return Bool True/False" fight(joueur,Benoit) */


                                }else {

                                    /* na pas bu de café */
                                    System.out.println("Vous n'avez pas bu de café");

                                    /* combat contre un bug */
                                    System.out.println("Vous devez affronter un dernier bug avant de voir le PO");
                                    /* fonction fight "return Bool True/False" fight(joueur,bug1) */

                                    /* combat contre le Po */
                                    System.out.println("Vous y etes batter le PO pour finir la journée");
                                    /* fonction fight "return Bool True/False" fight(joueur,PO) */

                                    /* affronte benoit */
                                    System.out.println("Vous pensiez avoir fini votre journée mais benoit vous lance une attaque administrative");
                                    /* fonction fight "return Bool True/False" fight(joueur,Benoit) */


                                }



                            }

                            /*  */
                        }
                        else{
                            /* Le joueur n'a pas fait sa Daily, (donc ...) */

                            /* Le joueur est moins performent durant la journé */
                            System.out.println("Comme vous n'avez pas fait votre daily, vous êtes mal préparer à affronter vos problèmes.");


                            /* Un combat se lance contre un Bug de niveau medium */
                            System.out.println("Vous rencontrez un bug lors de votre projet !");
                            /* fonction fight "return Bool True/False" fight(joueur, bug2) */

                            /* Malheuresuement le joueur rencontre un autre problème */
                            System.out.println("Malheuresement un second bug apparait !");
                            /* fonction fight "return Bool True/False" fight(joueur,bug1) */


                            /* le joueur rencontre un combat pour chauffer son repas lors de la pause */
                            System.out.println("Vous l'avez bien mérité vous prenez une pause pour manger");
                            System.out.println("Malheuresement un étudiant vous empeche de chauffer votre repas");
                            boolean combatRepas = ChoixEvenementJoueur("Voulez vous l'affrontez ??");

                            if (combatRepas){

                                /* un combat se lance avec l'étudiant */
                                System.out.println("Vous avez décidé d'affronter l'étudiant !");
                                /* fonction fight "return Bool True/false" fight (joueur,élève de l'école) */

                                /* résultat du combat avec l'élève */
                                if (/* retour du bool du combat */){

                                    /* Vous arrivez à l'heure et le Po de dis rien vos pv sont restaurés */
                                    System.out.println("Vous avez battu l'étudiant");
                                    System.out.println("Vous arrivez à l'heure et le Po de dis rien vos pv sont restaurés");
                                    /* restaurer les pv du joueur */

                                    /* Un nouveau problème est rencontré pendant votre projet */
                                    System.out.println("Un nouveau problème est rencontré pendant votre projet");
                                    /* fonction fight "return Bool True/False" fight(joueur,bug1) */

                                    /* Combat contre un gros bug */
                                    System.out.println("Pas de chance un bug majeur est rencontrer");
                                    /* fonction fight "return Bool True/False" fight(joueur,bug3) */

                                    /* choix de la pause café */
                                    System.out.println("Votre journée est bientot terminé !");
                                    System.out.println("Voulez vous prendre une pause café avant la fin de la journée ?");
                                    boolean pauseCafé = ChoixEvenementJoueur("voulez prendre une pause café ??");

                                    if (pauseCafé){

                                        /* bois un café et restaure ces pv */
                                        System.out.println("Vous avez bu un café vos PV sont restaurés !!");

                                        /* affronte le po */
                                        System.out.println("Votre journée touche à sa fin maintenant vous devez affronter votre PO");
                                        /* fonction fight "return Bool True/False" fight(joueur,PO) */


                                    }else {

                                        /* na pas bu de café */
                                        System.out.println("Vous n'avez pas bu de café");

                                        /* combat contre un bug */
                                        System.out.println("Vous devez affronter un dernier bug avant de voir le PO");
                                        /* fonction fight "return Bool True/False" fight(joueur,bug1) */

                                        /* combat contre le Po */
                                        System.out.println("Vous y etes batter le PO pour finir la journée");
                                        /* fonction fight "return Bool True/False" fight(joueur,PO) */


                                    }




                                }
                                else {

                                    /* Vous arrivez en retard et le Po s'énerve contre vous */
                                    System.out.println("Vous arrivez en retard et le Po s'énerve contre vous");

                                    /* Un nouveau problème est rencontré pendant votre projet */
                                    System.out.println("Un nouveau problème est rencontré pendant votre projet");
                                    /* fonction fight "return Bool True/False" fight(joueur,bug1) */

                                    /* Combat contre un gros bug */
                                    System.out.println("Pas de chance un bug majeur est rencontrer");
                                    /* fonction fight "return Bool True/False" fight(joueur,bug3) */

                                    /* choix de la pause café */
                                    System.out.println("Votre journée est bientot terminé !");
                                    System.out.println("Voulez vous prendre une pause café avant la fin de la journée ?");
                                    boolean pauseCafé = ChoixEvenementJoueur("voulez prendre une pause café ??");

                                    if (pauseCafé){

                                        /* bois un café et restaure ces pv */
                                        System.out.println("Vous avez bu un café vos PV sont restaurés !!");

                                        /* affronte le po */
                                        System.out.println("Votre journée touche à sa fin maintenant vous devez affronter votre PO");
                                        /* fonction fight "return Bool True/False" fight(joueur,PO) */

                                        /* affronte benoit */
                                        System.out.println("Vous pensiez avoir fini votre journée mais benoit vous lance une attaque administrative");
                                        /* fonction fight "return Bool True/False" fight(joueur,Benoit) */


                                    }else {

                                        /* na pas bu de café */
                                        System.out.println("Vous n'avez pas bu de café");

                                        /* combat contre un bug */
                                        System.out.println("Vous devez affronter un dernier bug avant de voir le PO");
                                        /* fonction fight "return Bool True/False" fight(joueur,bug1) */

                                        /* combat contre le Po */
                                        System.out.println("Vous y etes batter le PO pour finir la journée");
                                        /* fonction fight "return Bool True/False" fight(joueur,PO) */

                                        /* affronte benoit */
                                        System.out.println("Vous pensiez avoir fini votre journée mais benoit vous lance une attaque administrative");
                                        /* fonction fight "return Bool True/False" fight(joueur,Benoit) */


                                    }



                                }


                            }
                            else {
                                /* Vous arrivez en retard et le Po s'énerve contre vous */
                                System.out.println("Vous arrivez en retard et le Po s'énerve contre vous");

                                /* Un nouveau problème est rencontré pendant votre projet */
                                System.out.println("Un nouveau problème est rencontré pendant votre projet");
                                /* fonction fight "return Bool True/False" fight(joueur,bug1) */

                                /* Combat contre un gros bug */
                                System.out.println("Pas de chance un bug majeur est rencontrer");
                                /* fonction fight "return Bool True/False" fight(joueur,bug3) */

                                /* choix de la pause café */
                                System.out.println("Votre journée est bientot terminé !");
                                System.out.println("Voulez vous prendre une pause café avant la fin de la journée ?");
                                boolean pauseCafé = ChoixEvenementJoueur("voulez prendre une pause café ??");

                                if (pauseCafé){

                                    /* bois un café et restaure ces pv */
                                    System.out.println("Vous avez bu un café vos PV sont restaurés !!");

                                    /* affronte le po */
                                    System.out.println("Votre journée touche à sa fin maintenant vous devez affronter votre PO");
                                    /* fonction fight "return Bool True/False" fight(joueur,PO) */

                                    /* affronte benoit */
                                    System.out.println("Vous pensiez avoir fini votre journée mais benoit vous lance une attaque administrative");
                                    /* fonction fight "return Bool True/False" fight(joueur,Benoit) */


                                }else {

                                    /* na pas bu de café */
                                    System.out.println("Vous n'avez pas bu de café");

                                    /* combat contre un bug */
                                    System.out.println("Vous devez affronter un dernier bug avant de voir le PO");
                                    /* fonction fight "return Bool True/False" fight(joueur,bug1) */

                                    /* combat contre le Po */
                                    System.out.println("Vous y etes batter le PO pour finir la journée");
                                    /* fonction fight "return Bool True/False" fight(joueur,PO) */

                                    /* affronte benoit */
                                    System.out.println("Vous pensiez avoir fini votre journée mais benoit vous lance une attaque administrative");
                                    /* fonction fight "return Bool True/False" fight(joueur,Benoit) */


                                }



                            }
                        }
                    }
            }
            /* Vous n'avez pas prit votre petit déjeuner (donc ...) */
            else{

                /* le joueur n'a pris de petit dejeuner il ne peut pas commencer ça journée il meurt */
                System.out.println("Vous n'avez pas pris de petit déjeuner vous etes mort !!");
                /* mettre les pv du joueur a 0 */
            }

            /* Incrémenter un jours */
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