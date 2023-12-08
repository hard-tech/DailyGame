import model.*;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
// import control.*;

public class Main {
    Arme MacBook = new Arme("MacBook", 3);
    Arme MacBookPro = new Arme("MacBook Pro", 5);
    boolean combat = true;
    Sncf train1 = new Sncf("RER B", 20, 3, false, new Hashtable<>());
    Sncf train2 = new Sncf("RER A", 20, 3, false, new Hashtable<>());
    Sncf train3 = new Sncf("RER C", 25, 3, false, new Hashtable<>());

    Bug bug1 = new Bug("Erreur 404", 10, 3, false, new Hashtable<>());
    Bug bug2 = new Bug("Erreur 405", 20, 5, false, new Hashtable<>());
    Bug bug3 = new Bug("403 Forbidden", 25, 6, false, new Hashtable<>());

    Po po1 = new Po("François", 50, 10, false, new Hashtable<>());
    Po po2 = new Po("Benoit", 60, 12, false, new Hashtable<>());
    Po po3 = new Po("Joachim", 1000, 1, false, new Hashtable<>());

    Etudiant kevin = new Etudiant("Kévin", 35, 1, false, new Hashtable<>());

    // Initialisation des loots
    {
        train1.setLoot("exp", 15);
        train1.setLoot("J'ai besoin d'une pause", 1);

        train2.setLoot("exp", 15);
        train2.setLoot("J'ai besoin d'une pause", 1);

        train3.setLoot("exp", 15);
        train3.setLoot("J'ai besoin d'une pause", 1);

        bug1.setLoot("exp", 20);
        bug1.setLoot("pins", (int)(Math.random() * 15));
        bug1.setLoot("J'ai besoin d'une pause", 1);

        bug2.setLoot("exp", 30);
        bug2.setLoot("pins", (int)(Math.random() * 35));
        bug2.setLoot("J'ai besoin d'une pause", 1);

        bug3.setLoot("exp", 40);
        bug3.setLoot("pins", (int)(Math.random() * 45));

        po1.setLoot("exp", 65);
        po1.setLoot("pins", (int)(Math.random() * 70));

        po2.setLoot("exp", 1000);
        po2.setLoot("pins", 1000);

        po3.setLoot("exp", 1);
        po3.setLoot("pins", 1);

        kevin.setLoot("exp", 1);
        kevin.setLoot("pins", 0);
        kevin.setLoot("J'ai besoin d'une pause", 3);
        kevin.setLoot("Je suis noir", 1);
    }
    Potion pause = new Potion("J'ai besoin d'une pause", "heal", 15);
    Potion noir = new Potion("Je suis noir", "buff", 2);

    public void Shop(Joueur joueur){
        boolean shop = true;
        while (shop){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Un shop est apparue (C'est de la magiiiie)");
            System.out.println("1. MacBook : 40 pins");
            System.out.println("2. MacBook Pro : 60 pins");
            System.out.println("3. J'ai besoin d'une pause : 15 pins");
            System.out.println("4. Quitter le shop");
            System.out.println("");
            System.out.println("Vous avez " + joueur.getPins() + " pins.");
            String choix = scanner.next();

            switch (choix){
                case "1":
                    if (joueur.getPins() >= 40){
                        joueur.setPins(joueur.getPins() - 40);
                        joueur.Equiper(MacBook);
                    }
                    else{
                        System.out.println("Pas asser de pins.");
                    }
                case "2":
                    if (joueur.getPins() >= 60){
                        joueur.setPins(joueur.getPins() - 60);
                        joueur.Equiper(MacBookPro);
                    }
                    else{
                        System.out.println("Pas asser de pins.");
                    }
                case "3":
                    int quantiteActuelle = joueur.getInventaire().get("J'ai besoin d'une pause");
                    joueur.getInventaire().put("J'ai besoin d'une pause", quantiteActuelle + 1);
                case "4":
                    break;
                default:
                    System.out.println("Choix invalide");
            }
        }
    }

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
                        if (joueur.getInventaire().get("J'ai besoin d'une pause") > 0 &&
                                joueur.getInventaire().get("Je suis noir") > 0){
                            Scanner scanner = new Scanner(System.in);
                            System.out.println("Que voulez-vous utiliser ?");
                            System.out.println("1. J'ai besoin d'une pause");
                            System.out.println("2. Je suis noir");
                            String choixObjet = scanner.next();
                            if (choixObjet == "1"){
                                joueur.UtiliserObjet(joueur, pause);
                                choixFait = false;
                                }
                            else if (choixObjet == "2"){
                                joueur.UtiliserObjet(joueur, noir);
                                choixFait = false;
                            }
                            else {
                                System.out.println("C'est 1 ou 2 pas autre chose (débile).");
                            }
                        }
                        else if (joueur.getInventaire().get("J'ai besoin d'une pause") > 0){
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
                if (ennemi.getNom() == "Kévin" && ennemi.getPointDeVie() <= 11){
                    ennemi.Raciste(joueur);
                    choixFait = true;
                }
                else{
                    System.out.println("Attaque ennemi");
                    ennemi.Attaquer(joueur);
                    choixFait = true;
                }
                System.out.println("");
                if (joueur.getPointDeVie() <= 0 || ennemi.getPointDeVie() <= 0) {
                    fight = false;
                }
            }
        }
        return victoire;
    }
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

    public void Start(Joueur joueur){
        if(joueur.getDay() == 0){
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

                joueur = null;

                switch (choixDeClasse) {
                    case 1:
                        System.out.println("Vous avez choisi la classe codeur débutant");
                        Joueur debutant = new Joueur(nomJoueur, 30, 5, false,
                                new Hashtable<String, Integer>(), 0, 5, 0, 0);
                        joueur = debutant;
                        break;
                    case 2:
                        System.out.println("Vous avez choisi la classe codeur rapide");
                        Joueur rapide = new Joueur(nomJoueur, 25, 7, false,
                                new Hashtable<String, Integer>(), 0, 5, 0, 0);
                        joueur = rapide;
                        break;
                    case 3:
                        System.out.println("Vous avez choisi la classe codeur lent");
                        Joueur lent = new Joueur(nomJoueur, 40, 3, false,
                                new Hashtable<String, Integer>(), 0, 5, 0, 0);
                        joueur = lent;
                        break;
                    case 4:
                        System.out.println("Vous avez choisi la classe codeur raciste");
                        Joueur raciste = new Joueur(nomJoueur, 28, 7, false,
                                new Hashtable<String, Integer>(), 0, 5, 0, 0);
                        joueur = raciste;
                        break;
                    default:
                        System.out.println("Choix de classe non valide");
                }
                joueur.getInventaire().put("J'ai besoin d'une pause", 0);
                joueur.getInventaire().put("Je suis noir", 0);

                /*Premier choix du joueur et début du jeu*/
                System.out.println("Vous allez a présent pouvoir commencer votre journée de codeur");
                System.out.println("Dans ce jeu vous aller devoir faire des choix qui influencerons votre histoire");
                System.out.println("Toute journée commence d'abord par un réveil ! ");

                joueur.setDay(1);
        }
        while(joueur.getDay() <= 5){
            System.out.println("Jour actuel : " + joueur.getDay());

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
                        /* fonction fight "return Bool True/False" fight(joueur, train3) */
                        combat = Fight(joueur, train3);
                        train3.setPointDeVie(15);
                        if (combat){
                            System.out.println("Vous avez surmonté le rer.");
                        }else {
                            System.out.println("Game over t viré de la coding le rer t as ez.");
                            break;
                        }

                        /* Le joueur arrive à la coding factory en retard !*/
                        System.out.println("Vous êtes arrivé à la coding factory. Mais ! En retard ...");

                        /* Le Po engueule l'élève en retard */
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
                            combat = Fight(joueur, bug1);
                            bug1.setPointDeVie(10);
                            if (combat){
                                System.out.println("Vous avez surmonté le bug IZI.");
                            }else {
                                System.out.println("Game over t viré de la coding tu sais même pas gérer un petit bug.");
                                break;
                            }

                            /* Malheuresuement le joueur rencontre un autre problème */
                            System.out.println("Malheuresement un second bug apparait !");
                            /* fonction fight "return Bool True/False" fight(joueur,bug1) */
                            combat = Fight(joueur, bug1);
                            bug1.setPointDeVie(10);
                            if (combat){
                                System.out.println("Vous avez surmonté le bug IZI.");
                            }else {
                                System.out.println("Game over t viré de la coding tu sais même pas gérer un petit bug.");
                                break;
                            }

                            /* le joueur rencontre un combat pour chauffer son repas lors de la pause */
                            System.out.println("Vous l'avez bien mérité vous prenez une pause pour manger");
                            System.out.println("Malheuresement un étudiant vous empeche de chauffer votre repas");
                            boolean combatRepas = ChoixEvenementJoueur("Voulez vous l'affrontez ??");

                            if (combatRepas){

                                /* un combat se lance avec l'étudiant */
                                System.out.println("Vous avez décidé d'affronter l'étudiant qui n'était autre " +
                                        "que Kévin !");
                                /* fonction fight "return Bool True/false" fight (joueur,élève de l'école) */
                                combat = Fight(joueur, kevin);
                                kevin.setPointDeVie(35);

                                /* résultat du combat avec l'élève */
                                if (combat){

                                    /* Vous arrivez à l'heure et le Po de dis rien vos pv sont restaurés */
                                    System.out.println("Vous avez battu l'étudiant");
                                    System.out.println("Vous arrivez à l'heure et le Po de dis rien vos pv sont restaurés");
                                    /* restaurer les pv du joueur */
                                    joueur.setPointDeVie(35);
                                    Shop(joueur);

                                    /* Un nouveau problème est rencontré pendant votre projet */
                                    System.out.println("Un nouveau problème est rencontré pendant votre projet");
                                    /* fonction fight "return Bool True/False" fight(joueur,bug1) */
                                    combat = Fight(joueur, bug1);
                                    bug1.setPointDeVie(10);
                                    if (combat){
                                        System.out.println("Vous avez surmonté le bug IZI.");
                                    }else {
                                        System.out.println("Game over t viré de la coding tu sais même pas gérer un bug.");
                                        break;
                                    }

                                    /* Combat contre un gros bug */
                                    System.out.println("Pas de chance un bug majeur est rencontrer");
                                    /* fonction fight "return Bool True/False" fight(joueur,bug3) */
                                    combat = Fight(joueur, bug3);
                                    bug3.setPointDeVie(25);
                                    if (combat){
                                        System.out.println("Vous avez surmonté le bug IZI.");
                                    }else {
                                        System.out.println("Game over t viré de la coding tu sais même pas gérer un bug.");
                                        break;
                                    }

                                    /* choix de la pause café */
                                    System.out.println("Votre journée est bientot terminé !");
                                    System.out.println("Voulez vous prendre une pause café avant la fin de la journée ?");
                                    boolean pauseCafé = ChoixEvenementJoueur("voulez prendre une pause café ??");

                                    if (pauseCafé){

                                        /* bois un café et restaure ces pv */
                                        System.out.println("Vous avez bu un café vos PV sont restaurés !!");
                                        joueur.setPointDeVie(35 + joueur.getNiv());

                                        /* affronte le po */
                                        System.out.println("Votre journée touche à sa fin maintenant vous devez affronter votre PO");
                                        /* fonction fight "return Bool True/False" fight(joueur,PO) */
                                        combat = Fight(joueur, po1);
                                        po1.setPointDeVie(50);
                                        if (combat){
                                            System.out.println("Bien joué le Po vous a tout réglé.");
                                        }else {
                                            System.out.println("Le Po pris d'énervement explose votre mac contre le mur, " +
                                                    "oups... PERDU !.");
                                            break;
                                        }
                                    }else {

                                        /* na pas bu de café */
                                        System.out.println("Vous n'avez pas bu de café");

                                        /* combat contre un bug */
                                        System.out.println("Vous devez affronter un dernier bug avant de voir le PO");
                                        /* fonction fight "return Bool True/False" fight(joueur,bug1) */
                                        combat = Fight(joueur, bug2);
                                        bug2.setPointDeVie(20);
                                        if (combat){
                                            System.out.println("Vous avez surmonté le bug IZI.");
                                        }else {
                                            System.out.println("Game over t viré de la coding tu sais même pas gérer un bug.");
                                            break;
                                        }

                                        /* combat contre le Po */
                                        System.out.println("Vous y etes batter le PO pour finir la journée");
                                        /* fonction fight "return Bool True/False" fight(joueur,PO) */
                                        combat = Fight(joueur, po1);
                                        po1.setPointDeVie(50);
                                        if (combat){
                                            System.out.println("Bien joué le Po vous a tout réglé.");
                                        }else {
                                            System.out.println("Le Po pris d'énervement explose votre mac contre le mur, " +
                                                    "oups... PERDU !.");
                                            break;
                                        }
                                    }
                                }
                                else {

                                    /* Vous arrivez en retard et le Po s'énerve contre vous */
                                    System.out.println("Vous arrivez en retard et le Po s'énerve contre vous");

                                    /* Un nouveau problème est rencontré pendant votre projet */
                                    System.out.println("Un nouveau problème est rencontré pendant votre projet");
                                    /* fonction fight "return Bool True/False" fight(joueur,bug1) */
                                    combat = Fight(joueur, bug1);
                                    bug1.setPointDeVie(10);
                                    if (combat){
                                        System.out.println("Vous avez surmonté le bug IZI.");
                                    }else {
                                        System.out.println("Game over t viré de la coding tu sais même pas gérer un bug.");
                                        break;
                                    }

                                    /* Combat contre un gros bug */
                                    System.out.println("Pas de chance un bug majeur est rencontrer");
                                    /* fonction fight "return Bool True/False" fight(joueur,bug3) */
                                    combat = Fight(joueur, bug3);
                                    bug3.setPointDeVie(25);
                                    if (combat){
                                        System.out.println("Vous avez surmonté le bug IZI.");
                                    }else {
                                        System.out.println("Game over t viré de la coding tu sais même pas gérer un bug.");
                                        break;
                                    }

                                    /* choix de la pause café */
                                    System.out.println("Votre journée est bientot terminé !");
                                    System.out.println("Voulez vous prendre une pause café avant la fin de la journée ?");
                                    boolean pauseCafé = ChoixEvenementJoueur("voulez prendre une pause café ??");

                                    if (pauseCafé){

                                        /* bois un café et restaure ces pv */
                                        System.out.println("Vous avez bu un café vos PV sont restaurés !!");
                                        joueur.setPointDeVie(35 + joueur.getNiv());

                                        /* affronte le po */
                                        System.out.println("Votre journée touche à sa fin maintenant vous devez affronter votre PO");
                                        /* fonction fight "return Bool True/False" fight(joueur,PO) */
                                        combat = Fight(joueur, po1);
                                        po1.setPointDeVie(50);
                                        if (combat){
                                            System.out.println("Bien joué le Po vous a tout réglé.");
                                        }else {
                                            System.out.println("Le Po pris d'énervement explose votre mac contre le mur, " +
                                                    "oups... PERDU !.");
                                            break;
                                        }


                                        /* affronte benoit */
                                        System.out.println("Vous pensiez avoir fini votre journée mais benoit vous lance une attaque administrative");
                                        /* fonction fight "return Bool True/False" fight(joueur,Benoit) */
                                        combat = Fight(joueur, po2);
                                        po2.setPointDeVie(60);
                                        if (combat){
                                            System.out.println("Bien joué vous avez vaincu Benoit !");
                                        }else {
                                            System.out.println("Benoit vous vire... CHEH.");
                                            break;
                                        }
                                    }else {

                                        /* na pas bu de café */
                                        System.out.println("Vous n'avez pas bu de café");

                                        /* combat contre un bug */
                                        System.out.println("Vous devez affronter un dernier bug avant de voir le PO");
                                        /* fonction fight "return Bool True/False" fight(joueur,bug1) */
                                        combat = Fight(joueur, bug1);
                                        bug1.setPointDeVie(10);
                                        if (combat){
                                            System.out.println("Vous avez surmonté le bug IZI.");
                                        }else {
                                            System.out.println("Game over t viré de la coding tu sais même pas gérer un bug.");
                                            break;
                                        }

                                        /* combat contre le Po */
                                        System.out.println("Vous y etes batter le PO pour finir la journée");
                                        /* fonction fight "return Bool True/False" fight(joueur,PO) */
                                        combat = Fight(joueur, po1);
                                        po1.setPointDeVie(50);
                                        if (combat){
                                            System.out.println("Bien joué le Po vous a tout réglé.");
                                        }else {
                                            System.out.println("Le Po pris d'énervement explose votre mac contre le mur, " +
                                                    "oups... PERDU !.");
                                            break;
                                        }


                                        /* affronte benoit */
                                        System.out.println("Vous pensiez avoir fini votre journée mais benoit vous lance une attaque administrative");
                                        /* fonction fight "return Bool True/False" fight(joueur,Benoit) */
                                        combat = Fight(joueur, po2);
                                        po2.setPointDeVie(60);
                                        if (combat){
                                            System.out.println("Bien joué vous avez vaincu Benoit !");
                                        }else {
                                            System.out.println("Benoit vous vire... CHEH.");
                                            break;
                                        }
                                    }
                                }
                            }
                            else {

                                /* Vous arrivez en retard et le Po s'énerve contre vous */
                                System.out.println("Vous arrivez en retard et le Po s'énerve contre vous");
                                combat = Fight(joueur, po1);
                                po1.setPointDeVie(50);
                                if (combat){
                                    System.out.println("Bien joué le Po vous a tout réglé.");
                                }else {
                                    System.out.println("Le Po pris d'énervement explose votre mac contre le mur, " +
                                            "oups... PERDU !.");
                                    break;
                                }

                                /* Un nouveau problème est rencontré pendant votre projet */
                                System.out.println("Un nouveau problème est rencontré pendant votre projet");
                                /* fonction fight "return Bool True/False" fight(joueur,bug1) */
                                combat = Fight(joueur, bug1);
                                bug1.setPointDeVie(10);
                                if (combat){
                                    System.out.println("Vous avez surmonté le bug IZI.");
                                }else {
                                    System.out.println("Game over t viré de la coding tu sais même pas gérer un bug.");
                                    break;
                                }

                                /* Combat contre un gros bug */
                                System.out.println("Pas de chance un bug majeur est rencontrer");
                                /* fonction fight "return Bool True/False" fight(joueur,bug3) */
                                combat = Fight(joueur, bug3);
                                bug3.setPointDeVie(25);
                                if (combat){
                                    System.out.println("Vous avez surmonté le bug IZI.");
                                }else {
                                    System.out.println("Game over t viré de la coding tu sais même pas gérer un bug.");
                                    break;
                                }

                                /* choix de la pause café */
                                System.out.println("Votre journée est bientot terminé !");
                                System.out.println("Voulez vous prendre une pause café avant la fin de la journée ?");
                                boolean pauseCafé = ChoixEvenementJoueur("voulez prendre une pause café ??");

                                if (pauseCafé){

                                    /* bois un café et restaure ces pv */
                                    System.out.println("Vous avez bu un café vos PV sont restaurés !!");
                                    joueur.setPointDeVie(35 + joueur.getNiv());

                                    /* affronte le po */
                                    System.out.println("Votre journée touche à sa fin maintenant vous devez affronter votre PO");
                                    /* fonction fight "return Bool True/False" fight(joueur,PO) */
                                    combat = Fight(joueur, po1);
                                    po1.setPointDeVie(50);
                                    if (combat){
                                        System.out.println("Bien joué le Po vous a tout réglé.");
                                    }else {
                                        System.out.println("Le Po pris d'énervement explose votre mac contre le mur, " +
                                                "oups... PERDU !.");
                                        break;
                                    }

                                    /* affronte benoit */
                                    System.out.println("Vous pensiez avoir fini votre journée mais benoit vous lance une attaque administrative");
                                    /* fonction fight "return Bool True/False" fight(joueur,Benoit) */
                                    combat = Fight(joueur, po2);
                                    po2.setPointDeVie(60);
                                    if (combat){
                                        System.out.println("Bien joué vous avez vaincu Benoit !");
                                    }else {
                                        System.out.println("Benoit vous vire... CHEH.");
                                        break;
                                    }


                                }else {

                                    /* na pas bu de café */
                                    System.out.println("Vous n'avez pas bu de café");

                                    /* combat contre un bug */
                                    System.out.println("Vous devez affronter un dernier bug avant de voir le PO");
                                    /* fonction fight "return Bool True/False" fight(joueur,bug1) */
                                    combat = Fight(joueur, bug1);
                                    bug1.setPointDeVie(10);
                                    if (combat){
                                        System.out.println("Vous avez surmonté le bug IZI.");
                                    }else {
                                        System.out.println("Game over t viré de la coding tu sais même pas gérer un bug.");
                                        break;
                                    }

                                    /* combat contre le Po */
                                    System.out.println("Vous y etes batter le PO pour finir la journée");
                                    /* fonction fight "return Bool True/False" fight(joueur,PO) */
                                    combat = Fight(joueur, po1);
                                    po1.setPointDeVie(50);
                                    if (combat){
                                        System.out.println("Bien joué le Po vous a tout réglé.");
                                    }else {
                                        System.out.println("Le Po pris d'énervement explose votre mac contre le mur, " +
                                                "oups... PERDU !.");
                                        break;
                                    }

                                    /* affronte benoit */
                                    System.out.println("Vous pensiez avoir fini votre journée mais benoit vous lance une attaque administrative");
                                    /* fonction fight "return Bool True/False" fight(joueur,Benoit) */
                                    combat = Fight(joueur, po2);
                                    po2.setPointDeVie(60);
                                    if (combat){
                                        System.out.println("Bien joué vous avez vaincu Benoit !");
                                    }else {
                                        System.out.println("Benoit vous vire... CHEH.");
                                        break;
                                    }

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
                            combat = Fight(joueur, bug2);
                            bug2.setPointDeVie(25);
                            if (combat){
                                System.out.println("Vous avez surmonté le bug IZI.");
                            }else {
                                System.out.println("Game over t viré de la coding tu sais même pas gérer un bug.");
                                break;
                            }

                            /* Malheuresuement le joueur rencontre un autre problème */
                            System.out.println("Malheuresement un second bug apparait !");
                            /* fonction fight "return Bool True/False" fight(joueur,bug1) */
                            combat = Fight(joueur, bug1);
                            bug1.setPointDeVie(10);
                            if (combat){
                                System.out.println("Vous avez surmonté le bug IZI.");
                            }else {
                                System.out.println("Game over t viré de la coding tu sais même pas gérer un bug.");
                                break;
                            }

                            /* le joueur rencontre un combat pour chauffer son repas lors de la pause */
                            System.out.println("Vous l'avez bien mérité vous prenez une pause pour manger");
                            System.out.println("Malheuresement un étudiant vous empeche de chauffer votre repas");
                            boolean combatRepas = ChoixEvenementJoueur("Voulez vous l'affrontez ??");

                            if (combatRepas){

                                /* un combat se lance avec l'étudiant */
                                System.out.println("Vous avez décidé d'affronter l'étudiant !");
                                /* fonction fight "return Bool True/false" fight (joueur,élève de l'école) */
                                combat = Fight(joueur, kevin);
                                kevin.setPointDeVie(35);

                                /* résultat du combat avec l'élève */
                                if (combat){

                                    /* Vous arrivez à l'heure et le Po de dis rien vos pv sont restaurés */
                                    System.out.println("Vous avez battu l'étudiant");
                                    System.out.println("Vous arrivez à l'heure et le Po de dis rien vos pv sont restaurés");
                                    /* restaurer les pv du joueur */
                                    joueur.setPointDeVie(35 + joueur.getNiv());
                                    Shop(joueur);

                                    /* Un nouveau problème est rencontré pendant votre projet */
                                    System.out.println("Un nouveau problème est rencontré pendant votre projet");
                                    /* fonction fight "return Bool True/False" fight(joueur,bug1) */
                                    combat = Fight(joueur, bug1);
                                    bug1.setPointDeVie(10);
                                    if (combat){
                                        System.out.println("Vous avez surmonté le bug IZI.");
                                    }else {
                                        System.out.println("Game over t viré de la coding tu sais même pas gérer un bug.");
                                        break;
                                    }

                                    /* Combat contre un gros bug */
                                    System.out.println("Pas de chance un bug majeur est rencontrer");
                                    /* fonction fight "return Bool True/False" fight(joueur,bug3) */
                                    combat = Fight(joueur, bug3);
                                    bug3.setPointDeVie(25);
                                    if (combat){
                                        System.out.println("Vous avez surmonté le bug IZI.");
                                    }else {
                                        System.out.println("Game over t viré de la coding tu sais même pas gérer un bug.");
                                        break;
                                    }

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
                                        combat = Fight(joueur, po1);
                                        po1.setPointDeVie(50);
                                        if (combat){
                                            System.out.println("Bien joué le Po vous a tout réglé.");
                                        }else {
                                            System.out.println("Le Po pris d'énervement explose votre mac contre le mur, " +
                                                    "oups... PERDU !.");
                                            break;
                                        }

                                    }else {

                                        /* na pas bu de café */
                                        System.out.println("Vous n'avez pas bu de café");

                                        /* combat contre un bug */
                                        System.out.println("Vous devez affronter un dernier bug avant de voir le PO");
                                        /* fonction fight "return Bool True/False" fight(joueur,bug1) */
                                        combat = Fight(joueur, bug1);
                                        bug1.setPointDeVie(10);
                                        if (combat){
                                            System.out.println("Vous avez surmonté le bug IZI.");
                                        }else {
                                            System.out.println("Game over t viré de la coding tu sais même pas gérer un bug.");
                                            break;
                                        }

                                        /* combat contre le Po */
                                        System.out.println("Vous y etes batter le PO pour finir la journée");
                                        /* fonction fight "return Bool True/False" fight(joueur,PO) */
                                        combat = Fight(joueur, po1);
                                        po1.setPointDeVie(50);
                                        if (combat){
                                            System.out.println("Bien joué le Po vous a tout réglé.");
                                        }else {
                                            System.out.println("Le Po pris d'énervement explose votre mac contre le mur, " +
                                                    "oups... PERDU !.");
                                            break;
                                        }
                                    }
                                }
                                else {

                                    /* Vous arrivez en retard et le Po s'énerve contre vous */
                                    System.out.println("Vous arrivez en retard et le Po s'énerve contre vous");

                                    /* Un nouveau problème est rencontré pendant votre projet */
                                    System.out.println("Un nouveau problème est rencontré pendant votre projet");
                                    /* fonction fight "return Bool True/False" fight(joueur,bug1) */
                                    combat = Fight(joueur, bug1);
                                    bug1.setPointDeVie(10);
                                    if (combat){
                                        System.out.println("Vous avez surmonté le bug IZI.");
                                    }else {
                                        System.out.println("Game over t viré de la coding tu sais même pas gérer un bug.");
                                        break;
                                    }

                                    /* Combat contre un gros bug */
                                    System.out.println("Pas de chance un bug majeur est rencontrer");
                                    /* fonction fight "return Bool True/False" fight(joueur,bug3) */
                                    combat = Fight(joueur, bug3);
                                    bug3.setPointDeVie(25);
                                    if (combat){
                                        System.out.println("Vous avez surmonté le bug IZI.");
                                    }else {
                                        System.out.println("Game over t viré de la coding tu sais même pas gérer un bug.");
                                        break;
                                    }

                                    /* choix de la pause café */
                                    System.out.println("Votre journée est bientot terminé !");
                                    System.out.println("Voulez vous prendre une pause café avant la fin de la journée ?");
                                    boolean pauseCafé = ChoixEvenementJoueur("voulez prendre une pause café ??");

                                    if (pauseCafé){

                                        /* bois un café et restaure ces pv */
                                        System.out.println("Vous avez bu un café vos PV sont restaurés !!");
                                        joueur.setPointDeVie(35 + joueur.getNiv());

                                        /* affronte le po */
                                        System.out.println("Votre journée touche à sa fin maintenant vous devez affronter votre PO");
                                        /* fonction fight "return Bool True/False" fight(joueur,PO) */
                                        combat = Fight(joueur, po1);
                                        po1.setPointDeVie(50);
                                        if (combat){
                                            System.out.println("Bien joué le Po vous a tout réglé.");
                                        }else {
                                            System.out.println("Le Po pris d'énervement explose votre mac contre le mur, " +
                                                    "oups... PERDU !.");
                                            break;
                                        }

                                        /* affronte benoit */
                                        System.out.println("Vous pensiez avoir fini votre journée mais benoit vous lance une attaque administrative");
                                        /* fonction fight "return Bool True/False" fight(joueur,Benoit) */
                                        combat = Fight(joueur, po2);
                                        po2.setPointDeVie(60);
                                        if (combat){
                                            System.out.println("Bien joué vous avez vaincu Benoit !");
                                        }else {
                                            System.out.println("Benoit vous vire... CHEH.");
                                            break;
                                        }

                                    }else {

                                        /* na pas bu de café */
                                        System.out.println("Vous n'avez pas bu de café");

                                        /* combat contre un bug */
                                        System.out.println("Vous devez affronter un dernier bug avant de voir le PO");
                                        /* fonction fight "return Bool True/False" fight(joueur,bug1) */
                                        combat = Fight(joueur, bug1);
                                        bug1.setPointDeVie(10);
                                        if (combat){
                                            System.out.println("Vous avez surmonté le bug IZI.");
                                        }else {
                                            System.out.println("Game over t viré de la coding tu sais même pas gérer un bug.");
                                            break;
                                        }

                                        /* combat contre le Po */
                                        System.out.println("Vous y etes batter le PO pour finir la journée");
                                        /* fonction fight "return Bool True/False" fight(joueur,PO) */
                                        combat = Fight(joueur, po1);
                                        po1.setPointDeVie(50);
                                        if (combat){
                                            System.out.println("Bien joué le Po vous a tout réglé.");
                                        }else {
                                            System.out.println("Le Po pris d'énervement explose votre mac contre le mur, " +
                                                    "oups... PERDU !.");
                                            break;
                                        }

                                        /* affronte benoit */
                                        System.out.println("Vous pensiez avoir fini votre journée mais benoit vous lance une attaque administrative");
                                        /* fonction fight "return Bool True/False" fight(joueur,Benoit) */
                                        combat = Fight(joueur, po2);
                                        po2.setPointDeVie(60);
                                        if (combat){
                                            System.out.println("Bien joué vous avez vaincu Benoit !");
                                        }else {
                                            System.out.println("Benoit vous vire... CHEH.");
                                            break;
                                        }
                                    }
                                }
                            }
                            else {
                                /* Vous arrivez en retard et le Po s'énerve contre vous */
                                System.out.println("Vous arrivez en retard et le Po s'énerve contre vous");

                                /* Un nouveau problème est rencontré pendant votre projet */
                                System.out.println("Un nouveau problème est rencontré pendant votre projet");
                                /* fonction fight "return Bool True/False" fight(joueur,bug1) */
                                combat = Fight(joueur, bug1);
                                bug1.setPointDeVie(10);
                                if (combat){
                                    System.out.println("Vous avez surmonté le bug IZI.");
                                }else {
                                    System.out.println("Game over t viré de la coding tu sais même pas gérer un bug.");
                                    break;
                                }

                                /* Combat contre un gros bug */
                                System.out.println("Pas de chance un bug majeur est rencontrer");
                                /* fonction fight "return Bool True/False" fight(joueur,bug3) */
                                combat = Fight(joueur, bug3);
                                bug3.setPointDeVie(25);

                                if (combat){
                                    System.out.println("Vous avez surmonté le bug IZI.");
                                }else {
                                    System.out.println("Game over t viré de la coding tu sais même pas gérer un bug.");
                                    break;
                                }

                                /* choix de la pause café */
                                System.out.println("Votre journée est bientot terminé !");
                                System.out.println("Voulez vous prendre une pause café avant la fin de la journée ?");
                                boolean pauseCafé = ChoixEvenementJoueur("voulez prendre une pause café ??");

                                if (pauseCafé){

                                    /* bois un café et restaure ces pv */
                                    System.out.println("Vous avez bu un café vos PV sont restaurés !!");
                                    joueur.setPointDeVie(35 + joueur.getNiv());

                                    /* affronte le po */
                                    System.out.println("Votre journée touche à sa fin maintenant vous devez affronter votre PO");
                                    /* fonction fight "return Bool True/False" fight(joueur,PO) */
                                    combat = Fight(joueur, po1);
                                    po1.setPointDeVie(50);
                                    if (combat){
                                        System.out.println("Bien joué le Po vous a tout réglé.");
                                    }else {
                                        System.out.println("Le Po pris d'énervement explose votre mac contre le mur, " +
                                                "oups... PERDU !.");
                                        break;
                                    }

                                    /* affronte benoit */
                                    System.out.println("Vous pensiez avoir fini votre journée mais benoit vous lance une attaque administrative");
                                    /* fonction fight "return Bool True/False" fight(joueur,Benoit) */
                                    combat = Fight(joueur, po2);
                                    po2.setPointDeVie(60);
                                    if (combat){
                                        System.out.println("Bien joué vous avez vaincu Benoit !");
                                    }else {
                                        System.out.println("Benoit vous vire... CHEH.");
                                        break;
                                    }

                                }else {

                                    /* na pas bu de café */
                                    System.out.println("Vous n'avez pas bu de café");

                                    /* combat contre un bug */
                                    System.out.println("Vous devez affronter un dernier bug avant de voir le PO");
                                    /* fonction fight "return Bool True/False" fight(joueur,bug1) */
                                    combat = Fight(joueur, bug1);
                                    bug1.setPointDeVie(10);
                                    if (combat){
                                        System.out.println("Vous avez surmonté le bug IZI.");
                                    }else {
                                        System.out.println("Game over t viré de la coding tu sais même pas gérer un bug.");
                                        break;
                                    }

                                    /* combat contre le Po */
                                    System.out.println("Vous y etes batter le PO pour finir la journée");
                                    /* fonction fight "return Bool True/False" fight(joueur,PO) */
                                    combat = Fight(joueur, po1);
                                    po1.setPointDeVie(50);
                                    if (combat){
                                        System.out.println("Bien joué le Po vous a tout réglé.");
                                    }else {
                                        System.out.println("Le Po pris d'énervement explose votre mac contre le mur, " +
                                                "oups... PERDU !.");
                                        break;
                                    }

                                    /* affronte benoit */
                                    System.out.println("Vous pensiez avoir fini votre journée mais benoit vous lance une attaque administrative");
                                    /* fonction fight "return Bool True/False" fight(joueur,Benoit) */
                                    combat = Fight(joueur, po2);
                                    po2.setPointDeVie(60);
                                    if (combat){
                                        System.out.println("Bien joué vous avez vaincu Benoit !");
                                    }else {
                                        System.out.println("Benoit vous vire... CHEH.");
                                        break;
                                    }

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
                            combat = Fight(joueur, bug1);
                            bug1.setPointDeVie(10);
                            if (combat){
                                System.out.println("Vous avez surmonté le bug IZI.");
                            }else {
                                System.out.println("Game over t viré de la coding tu sais même pas gérer un bug.");
                                break;
                            }

                            /* Malheuresuement le joueur rencontre un autre problème */
                            System.out.println("Malheuresement un second bug apparait !");
                            /* fonction fight "return Bool True/False" fight(joueur,bug1) */
                            combat = Fight(joueur, bug1);
                            bug1.setPointDeVie(10);
                            if (combat){
                                System.out.println("Vous avez surmonté le bug IZI.");
                            }else {
                                System.out.println("Game over t viré de la coding tu sais même pas gérer un bug.");
                                break;
                            }

                            /* le joueur rencontre un combat pour chauffer son repas lors de la pause */
                            System.out.println("Vous l'avez bien mérité vous prenez une pause pour manger");
                            System.out.println("Malheuresement un étudiant vous empeche de chauffer votre repas");
                            boolean combatRepas = ChoixEvenementJoueur("Voulez vous l'affrontez ??");

                            if (combatRepas){

                                /* un combat se lance avec l'étudiant */
                                System.out.println("Vous avez décidé d'affronter l'étudiant !");
                                /* fonction fight "return Bool True/false" fight (joueur,élève de l'école) */
                                combat = Fight(joueur, kevin);
                                kevin.setPointDeVie(35);

                                /* résultat du combat avec l'élève */
                                if (combat){

                                    /* Vous arrivez à l'heure et le Po de dis rien vos pv sont restaurés */
                                    System.out.println("Vous avez battu l'étudiant");
                                    System.out.println("Vous arrivez à l'heure et le Po de dis rien vos pv sont restaurés");
                                    /* restaurer les pv du joueur */
                                    joueur.setPointDeVie(35 + joueur.getNiv());
                                    Shop(joueur);

                                    /* Un nouveau problème est rencontré pendant votre projet */
                                    System.out.println("Un nouveau problème est rencontré pendant votre projet");
                                    /* fonction fight "return Bool True/False" fight(joueur,bug1) */
                                    combat = Fight(joueur, bug1);
                                    bug1.setPointDeVie(10);
                                    if (combat){
                                        System.out.println("Vous avez surmonté le bug IZI.");
                                    }else {
                                        System.out.println("Game over t viré de la coding tu sais même pas gérer un bug.");
                                        break;
                                    }

                                    /* Combat contre un gros bug */
                                    System.out.println("Pas de chance un bug majeur est rencontrer");
                                    /* fonction fight "return Bool True/False" fight(joueur,bug3) */
                                    combat = Fight(joueur, bug3);
                                    bug3.setPointDeVie(25);
                                    if (combat){
                                        System.out.println("Vous avez surmonté le bug IZI.");
                                    }else {
                                        System.out.println("Game over t viré de la coding tu sais même pas gérer un bug.");
                                        break;
                                    }

                                    /* choix de la pause café */
                                    System.out.println("Votre journée est bientot terminé !");
                                    System.out.println("Voulez vous prendre une pause café avant la fin de la journée ?");
                                    boolean pauseCafé = ChoixEvenementJoueur("voulez prendre une pause café ??");

                                    if (pauseCafé){

                                        /* bois un café et restaure ces pv */
                                        System.out.println("Vous avez bu un café vos PV sont restaurés !!");
                                        joueur.setPointDeVie(35 + joueur.getNiv());

                                        /* affronte le po */
                                        System.out.println("Votre journée touche à sa fin maintenant vous devez affronter votre PO");
                                        /* fonction fight "return Bool True/False" fight(joueur,PO) */
                                        combat = Fight(joueur, po1);
                                        po1.setPointDeVie(50);
                                        if (combat){
                                            System.out.println("Bien joué le Po vous a tout réglé.");
                                        }else {
                                            System.out.println("Le Po pris d'énervement explose votre mac contre le mur, " +
                                                    "oups... PERDU !.");
                                            break;
                                        }

                                    }else {

                                        /* na pas bu de café */
                                        System.out.println("Vous n'avez pas bu de café");

                                        /* combat contre un bug */
                                        System.out.println("Vous devez affronter un dernier bug avant de voir le PO");
                                        /* fonction fight "return Bool True/False" fight(joueur,bug1) */
                                        combat = Fight(joueur, bug1);
                                        bug1.setPointDeVie(10);
                                        if (combat){
                                            System.out.println("Vous avez surmonté le bug IZI.");
                                        }else {
                                            System.out.println("Game over t viré de la coding tu sais même pas gérer un bug.");
                                            break;
                                        }

                                        /* combat contre le Po */
                                        System.out.println("Vous y etes batter le PO pour finir la journée");
                                        /* fonction fight "return Bool True/False" fight(joueur,PO) */
                                        combat = Fight(joueur, po1);
                                        po1.setPointDeVie(50);
                                        if (combat){
                                            System.out.println("Bien joué le Po vous a tout réglé.");
                                        }else {
                                            System.out.println("Le Po pris d'énervement explose votre mac contre le mur, " +
                                                    "oups... PERDU !.");
                                            break;
                                        }

                                    }
                                }
                                else {

                                    /* Vous arrivez en retard et le Po s'énerve contre vous */
                                    System.out.println("Vous arrivez en retard et le Po s'énerve contre vous");

                                    /* Un nouveau problème est rencontré pendant votre projet */
                                    System.out.println("Un nouveau problème est rencontré pendant votre projet");
                                    /* fonction fight "return Bool True/False" fight(joueur,bug1) */
                                    combat = Fight(joueur, bug1);
                                    bug1.setPointDeVie(10);
                                    if (combat){
                                        System.out.println("Vous avez surmonté le bug IZI.");
                                    }else {
                                        System.out.println("Game over t viré de la coding tu sais même pas gérer un bug.");
                                        break;
                                    }

                                    /* Combat contre un gros bug */
                                    System.out.println("Pas de chance un bug majeur est rencontrer");
                                    /* fonction fight "return Bool True/False" fight(joueur,bug3) */
                                    combat = Fight(joueur, bug3);
                                    bug3.setPointDeVie(25);
                                    if (combat){
                                        System.out.println("Vous avez surmonté le bug IZI.");
                                    }else {
                                        System.out.println("Game over t viré de la coding tu sais même pas gérer un bug.");
                                        break;
                                    }

                                    /* choix de la pause café */
                                    System.out.println("Votre journée est bientot terminé !");
                                    System.out.println("Voulez vous prendre une pause café avant la fin de la journée ?");
                                    boolean pauseCafé = ChoixEvenementJoueur("voulez prendre une pause café ??");

                                    if (pauseCafé){

                                        /* bois un café et restaure ces pv */
                                        System.out.println("Vous avez bu un café vos PV sont restaurés !!");
                                        joueur.setPointDeVie(35 + joueur.getNiv());

                                        /* affronte le po */
                                        System.out.println("Votre journée touche à sa fin maintenant vous devez affronter votre PO");
                                        /* fonction fight "return Bool True/False" fight(joueur,PO) */
                                        combat = Fight(joueur, po1);
                                        po1.setPointDeVie(50);
                                        if (combat){
                                            System.out.println("Bien joué le Po vous a tout réglé.");
                                        }else {
                                            System.out.println("Le Po pris d'énervement explose votre mac contre le mur, " +
                                                    "oups... PERDU !.");
                                            break;
                                        }


                                        /* affronte benoit */
                                        System.out.println("Vous pensiez avoir fini votre journée mais benoit vous lance une attaque administrative");
                                        /* fonction fight "return Bool True/False" fight(joueur,Benoit) */
                                        combat = Fight(joueur, po2);
                                        po2.setPointDeVie(60);
                                        if (combat){
                                            System.out.println("Bien joué vous avez vaincu Benoit !");
                                        }else {
                                            System.out.println("Benoit vous vire... CHEH.");
                                            break;
                                        }

                                    }else {

                                        /* na pas bu de café */
                                        System.out.println("Vous n'avez pas bu de café");

                                        /* combat contre un bug */
                                        System.out.println("Vous devez affronter un dernier bug avant de voir le PO");
                                        /* fonction fight "return Bool True/False" fight(joueur,bug1) */
                                        combat = Fight(joueur, bug1);
                                        bug1.setPointDeVie(10);
                                        if (combat){
                                            System.out.println("Vous avez surmonté le bug IZI.");
                                        }else {
                                            System.out.println("Game over t viré de la coding tu sais même pas gérer un bug.");
                                            break;
                                        }

                                        /* combat contre le Po */
                                        System.out.println("Vous y etes batter le PO pour finir la journée");
                                        /* fonction fight "return Bool True/False" fight(joueur,PO) */
                                        combat = Fight(joueur, po1);
                                        po1.setPointDeVie(50);
                                        if (combat){
                                            System.out.println("Bien joué le Po vous a tout réglé.");
                                        }else {
                                            System.out.println("Le Po pris d'énervement explose votre mac contre le mur, " +
                                                    "oups... PERDU !.");
                                            break;
                                        }


                                        /* affronte benoit */
                                        System.out.println("Vous pensiez avoir fini votre journée mais benoit vous lance une attaque administrative");
                                        /* fonction fight "return Bool True/False" fight(joueur,Benoit) */
                                        combat = Fight(joueur, po2);
                                        po2.setPointDeVie(60);
                                        if (combat){
                                            System.out.println("Bien joué vous avez vaincu Benoit !");
                                        }else {
                                            System.out.println("Benoit vous vire... CHEH.");
                                            break;
                                        }

                                    }
                                }
                            }
                            else {
                                /* Vous arrivez en retard et le Po s'énerve contre vous */
                                System.out.println("Vous arrivez en retard et le Po s'énerve contre vous");

                                /* Un nouveau problème est rencontré pendant votre projet */
                                System.out.println("Un nouveau problème est rencontré pendant votre projet");
                                /* fonction fight "return Bool True/False" fight(joueur,bug1) */
                                combat = Fight(joueur, bug1);
                                bug1.setPointDeVie(10);
                                if (combat){
                                    System.out.println("Vous avez surmonté le bug IZI.");
                                }else {
                                    System.out.println("Game over t viré de la coding tu sais même pas gérer un bug.");
                                    break;
                                }

                                /* Combat contre un gros bug */
                                System.out.println("Pas de chance un bug majeur est rencontrer");
                                /* fonction fight "return Bool True/False" fight(joueur,bug3) */
                                combat = Fight(joueur, bug3);
                                bug3.setPointDeVie(25);
                                if (combat){
                                    System.out.println("Vous avez surmonté le bug IZI.");
                                }else {
                                    System.out.println("Game over t viré de la coding tu sais même pas gérer un bug.");
                                    break;
                                }

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
                                    combat = Fight(joueur, po1);
                                    po1.setPointDeVie(50);
                                    if (combat){
                                        System.out.println("Bien joué le Po vous a tout réglé.");
                                    }else {
                                        System.out.println("Le Po pris d'énervement explose votre mac contre le mur, " +
                                                "oups... PERDU !.");
                                        break;
                                    }

                                    /* affronte benoit */
                                    System.out.println("Vous pensiez avoir fini votre journée mais benoit vous lance une attaque administrative");
                                    /* fonction fight "return Bool True/False" fight(joueur,Benoit) */
                                    combat = Fight(joueur, po2);
                                    po2.setPointDeVie(60);
                                    if (combat){
                                        System.out.println("Bien joué vous avez vaincu Benoit !");
                                    }else {
                                        System.out.println("Benoit vous vire... CHEH.");
                                        break;
                                    }

                                }else {

                                    /* na pas bu de café */
                                    System.out.println("Vous n'avez pas bu de café");

                                    /* combat contre un bug */
                                    System.out.println("Vous devez affronter un dernier bug avant de voir le PO");
                                    /* fonction fight "return Bool True/False" fight(joueur,bug1) */
                                    combat = Fight(joueur, bug1);
                                    bug1.setPointDeVie(10);
                                    if (combat){
                                        System.out.println("Vous avez surmonté le bug IZI.");
                                    }else {
                                        System.out.println("Game over t viré de la coding tu sais même pas gérer un bug.");
                                        break;
                                    }

                                    /* combat contre le Po */
                                    System.out.println("Vous y etes batter le PO pour finir la journée");
                                    /* fonction fight "return Bool True/False" fight(joueur,PO) */
                                    combat = Fight(joueur, po1);
                                    po1.setPointDeVie(50);
                                    if (combat){
                                        System.out.println("Bien joué le Po vous a tout réglé.");
                                    }else {
                                        System.out.println("Le Po pris d'énervement explose votre mac contre le mur, " +
                                                "oups... PERDU !.");
                                        break;
                                    }

                                    /* affronte benoit */
                                    System.out.println("Vous pensiez avoir fini votre journée mais benoit vous lance une attaque administrative");
                                    /* fonction fight "return Bool True/False" fight(joueur,Benoit) */
                                    combat = Fight(joueur, po2);
                                    po2.setPointDeVie(60);
                                    if (combat){
                                        System.out.println("Bien joué vous avez vaincu Benoit !");
                                    }else {
                                        System.out.println("Benoit vous vire... CHEH.");
                                        break;
                                    }

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
                            combat = Fight(joueur, bug2);
                            bug2.setPointDeVie(20);
                            if (combat){
                                System.out.println("Vous avez surmonté le bug IZI.");
                            }else {
                                System.out.println("Game over t viré de la coding tu sais même pas gérer un bug.");
                                break;
                            }

                            /* Malheuresuement le joueur rencontre un autre problème */
                            System.out.println("Malheuresement un second bug apparait !");
                            /* fonction fight "return Bool True/False" fight(joueur,bug1) */
                            combat = Fight(joueur, bug1);
                            bug1.setPointDeVie(10);
                            if (combat){
                                System.out.println("Vous avez surmonté le bug IZI.");
                            }else {
                                System.out.println("Game over t viré de la coding tu sais même pas gérer un bug.");
                                break;
                            }

                            /* le joueur rencontre un combat pour chauffer son repas lors de la pause */
                            System.out.println("Vous l'avez bien mérité vous prenez une pause pour manger");
                            System.out.println("Malheuresement un étudiant vous empeche de chauffer votre repas");
                            boolean combatRepas = ChoixEvenementJoueur("Voulez vous l'affrontez ??");

                            if (combatRepas){

                                /* un combat se lance avec l'étudiant */
                                System.out.println("Vous avez décidé d'affronter l'étudiant !");
                                /* fonction fight "return Bool True/false" fight (joueur,élève de l'école) */
                                combat = Fight(joueur, kevin);
                                kevin.setPointDeVie(35);

                                /* résultat du combat avec l'élève */
                                if (combat){

                                    /* Vous arrivez à l'heure et le Po de dis rien vos pv sont restaurés */
                                    System.out.println("Vous avez battu l'étudiant");
                                    System.out.println("Vous arrivez à l'heure et le Po de dis rien vos pv sont restaurés");
                                    /* restaurer les pv du joueur */
                                    joueur.setPointDeVie(35 + joueur.getNiv());
                                    Shop(joueur);

                                    /* Un nouveau problème est rencontré pendant votre projet */
                                    System.out.println("Un nouveau problème est rencontré pendant votre projet");
                                    /* fonction fight "return Bool True/False" fight(joueur,bug1) */
                                    combat = Fight(joueur, bug1);
                                    bug1.setPointDeVie(10);
                                    if (combat){
                                        System.out.println("Vous avez surmonté le bug IZI.");
                                    }else {
                                        System.out.println("Game over t viré de la coding tu sais même pas gérer un bug.");
                                        break;
                                    }

                                    /* Combat contre un gros bug */
                                    System.out.println("Pas de chance un bug majeur est rencontrer");
                                    /* fonction fight "return Bool True/False" fight(joueur,bug3) */
                                    combat = Fight(joueur, bug3);
                                    bug3.setPointDeVie(25);
                                    if (combat){
                                        System.out.println("Vous avez surmonté le bug IZI.");
                                    }else {
                                        System.out.println("Game over t viré de la coding tu sais même pas gérer un bug.");
                                        break;
                                    }

                                    /* choix de la pause café */
                                    System.out.println("Votre journée est bientot terminé !");
                                    System.out.println("Voulez vous prendre une pause café avant la fin de la journée ?");
                                    boolean pauseCafé = ChoixEvenementJoueur("voulez prendre une pause café ??");

                                    if (pauseCafé){

                                        /* bois un café et restaure ces pv */
                                        System.out.println("Vous avez bu un café vos PV sont restaurés !!");
                                        joueur.setPointDeVie(35 + joueur.getNiv());

                                        /* affronte le po */
                                        System.out.println("Votre journée touche à sa fin maintenant vous devez affronter votre PO");
                                        /* fonction fight "return Bool True/False" fight(joueur,PO) */
                                        combat = Fight(joueur, po1);
                                        po1.setPointDeVie(50);
                                        if (combat){
                                            System.out.println("Bien joué le Po vous a tout réglé.");
                                        }else {
                                            System.out.println("Le Po pris d'énervement explose votre mac contre le mur, " +
                                                    "oups... PERDU !.");
                                            break;
                                        }

                                    }else {

                                        /* na pas bu de café */
                                        System.out.println("Vous n'avez pas bu de café");

                                        /* combat contre un bug */
                                        System.out.println("Vous devez affronter un dernier bug avant de voir le PO");
                                        /* fonction fight "return Bool True/False" fight(joueur,bug1) */
                                        combat = Fight(joueur, bug1);
                                        bug1.setPointDeVie(10);
                                        if (combat){
                                            System.out.println("Vous avez surmonté le bug IZI.");
                                        }else {
                                            System.out.println("Game over t viré de la coding tu sais même pas gérer un bug.");
                                            break;
                                        }

                                        /* combat contre le Po */
                                        System.out.println("Vous y etes batter le PO pour finir la journée");
                                        /* fonction fight "return Bool True/False" fight(joueur,PO) */
                                        combat = Fight(joueur, po1);
                                        po1.setPointDeVie(50);
                                        if (combat){
                                            System.out.println("Bien joué le Po vous a tout réglé.");
                                        }else {
                                            System.out.println("Le Po pris d'énervement explose votre mac contre le mur, " +
                                                    "oups... PERDU !.");
                                            break;
                                        }

                                    }
                                }
                                else {

                                    /* Vous arrivez en retard et le Po s'énerve contre vous */
                                    System.out.println("Vous arrivez en retard et le Po s'énerve contre vous");

                                    /* Un nouveau problème est rencontré pendant votre projet */
                                    System.out.println("Un nouveau problème est rencontré pendant votre projet");
                                    /* fonction fight "return Bool True/False" fight(joueur,bug1) */
                                    combat = Fight(joueur, bug1);
                                    bug1.setPointDeVie(10);
                                    if (combat){
                                        System.out.println("Vous avez surmonté le bug IZI.");
                                    }else {
                                        System.out.println("Game over t viré de la coding tu sais même pas gérer un bug.");
                                        break;
                                    }

                                    /* Combat contre un gros bug */
                                    System.out.println("Pas de chance un bug majeur est rencontrer");
                                    /* fonction fight "return Bool True/False" fight(joueur,bug3) */
                                    combat = Fight(joueur, bug3);
                                    bug3.setPointDeVie(25);
                                    if (combat){
                                        System.out.println("Vous avez surmonté le bug IZI.");
                                    }else {
                                        System.out.println("Game over t viré de la coding tu sais même pas gérer un bug.");
                                        break;
                                    }

                                    /* choix de la pause café */
                                    System.out.println("Votre journée est bientot terminé !");
                                    System.out.println("Voulez vous prendre une pause café avant la fin de la journée ?");
                                    boolean pauseCafé = ChoixEvenementJoueur("voulez prendre une pause café ??");

                                    if (pauseCafé){

                                        /* bois un café et restaure ces pv */
                                        System.out.println("Vous avez bu un café vos PV sont restaurés !!");
                                        joueur.setPointDeVie(35 + joueur.getNiv());

                                        /* affronte le po */
                                        System.out.println("Votre journée touche à sa fin maintenant vous devez affronter votre PO");
                                        /* fonction fight "return Bool True/False" fight(joueur,PO) */
                                        combat = Fight(joueur, po1);
                                        po1.setPointDeVie(50);
                                        if (combat){
                                            System.out.println("Bien joué le Po vous a tout réglé.");
                                        }else {
                                            System.out.println("Le Po pris d'énervement explose votre mac contre le mur, " +
                                                    "oups... PERDU !.");
                                            break;
                                        }

                                        /* affronte benoit */
                                        System.out.println("Vous pensiez avoir fini votre journée mais benoit vous lance une attaque administrative");
                                        /* fonction fight "return Bool True/False" fight(joueur,Benoit) */
                                        combat = Fight(joueur, po2);
                                        po2.setPointDeVie(60);
                                        if (combat){
                                            System.out.println("Bien joué vous avez vaincu Benoit !");
                                        }else {
                                            System.out.println("Benoit vous vire... CHEH.");
                                            break;
                                        }

                                    }else {

                                        /* na pas bu de café */
                                        System.out.println("Vous n'avez pas bu de café");

                                        /* combat contre un bug */
                                        System.out.println("Vous devez affronter un dernier bug avant de voir le PO");
                                        /* fonction fight "return Bool True/False" fight(joueur,bug1) */
                                        combat = Fight(joueur, bug1);
                                        bug1.setPointDeVie(10);
                                        if (combat){
                                            System.out.println("Vous avez surmonté le bug IZI.");
                                        }else {
                                            System.out.println("Game over t viré de la coding tu sais même pas gérer un bug.");
                                            break;
                                        }

                                        /* combat contre le Po */
                                        System.out.println("Vous y etes batter le PO pour finir la journée");
                                        /* fonction fight "return Bool True/False" fight(joueur,PO) */
                                        combat = Fight(joueur, po1);
                                        po1.setPointDeVie(50);
                                        if (combat){
                                            System.out.println("Bien joué le Po vous a tout réglé.");
                                        }else {
                                            System.out.println("Le Po pris d'énervement explose votre mac contre le mur, " +
                                                    "oups... PERDU !.");
                                            break;
                                        }

                                        /* affronte benoit */
                                        System.out.println("Vous pensiez avoir fini votre journée mais benoit vous lance une attaque administrative");
                                        /* fonction fight "return Bool True/False" fight(joueur,Benoit) */
                                        combat = Fight(joueur, po2);
                                        po2.setPointDeVie(60);
                                        if (combat){
                                            System.out.println("Bien joué vous avez vaincu Benoit !");
                                        }else {
                                            System.out.println("Benoit vous vire... CHEH.");
                                            break;
                                        }

                                    }
                                }
                            }
                            else {
                                /* Vous arrivez en retard et le Po s'énerve contre vous */
                                System.out.println("Vous arrivez en retard et le Po s'énerve contre vous");

                                /* Un nouveau problème est rencontré pendant votre projet */
                                System.out.println("Un nouveau problème est rencontré pendant votre projet");
                                /* fonction fight "return Bool True/False" fight(joueur,bug1) */
                                combat = Fight(joueur, bug1);
                                bug1.setPointDeVie(10);
                                if (combat){
                                    System.out.println("Vous avez surmonté le bug IZI.");
                                }else {
                                    System.out.println("Game over t viré de la coding tu sais même pas gérer un bug.");
                                    break;
                                }

                                /* Combat contre un gros bug */
                                System.out.println("Pas de chance un bug majeur est rencontrer");
                                /* fonction fight "return Bool True/False" fight(joueur,bug3) */
                                combat = Fight(joueur, bug3);
                                bug3.setPointDeVie(25);
                                if (combat){
                                    System.out.println("Vous avez surmonté le bug IZI.");
                                }else {
                                    System.out.println("Game over t viré de la coding tu sais même pas gérer un bug.");
                                    break;
                                }

                                /* choix de la pause café */
                                System.out.println("Votre journée est bientot terminé !");
                                System.out.println("Voulez vous prendre une pause café avant la fin de la journée ?");
                                boolean pauseCafé = ChoixEvenementJoueur("voulez prendre une pause café ??");

                                if (pauseCafé){

                                    /* bois un café et restaure ces pv */
                                    System.out.println("Vous avez bu un café vos PV sont restaurés !!");
                                    joueur.setPointDeVie(35 + joueur.getNiv());

                                    /* affronte le po */
                                    System.out.println("Votre journée touche à sa fin maintenant vous devez affronter votre PO");
                                    /* fonction fight "return Bool True/False" fight(joueur,PO) */
                                    combat = Fight(joueur, po1);
                                    po1.setPointDeVie(50);
                                    if (combat){
                                        System.out.println("Bien joué le Po vous a tout réglé.");
                                    }else {
                                        System.out.println("Le Po pris d'énervement explose votre mac contre le mur, " +
                                                "oups... PERDU !.");
                                        break;
                                    }

                                    /* affronte benoit */
                                    System.out.println("Vous pensiez avoir fini votre journée mais benoit vous lance une attaque administrative");
                                    /* fonction fight "return Bool True/False" fight(joueur,Benoit) */
                                    combat = Fight(joueur, po2);
                                    po2.setPointDeVie(60);
                                    if (combat){
                                        System.out.println("Bien joué vous avez vaincu Benoit !");
                                    }else {
                                        System.out.println("Benoit vous vire... CHEH.");
                                        break;
                                    }

                                }else {

                                    /* na pas bu de café */
                                    System.out.println("Vous n'avez pas bu de café");

                                    /* combat contre un bug */
                                    System.out.println("Vous devez affronter un dernier bug avant de voir le PO");
                                    /* fonction fight "return Bool True/False" fight(joueur,bug1) */
                                    combat = Fight(joueur, bug1);
                                    bug1.setPointDeVie(10);
                                    if (combat){
                                        System.out.println("Vous avez surmonté le bug IZI.");
                                    }else {
                                        System.out.println("Game over t viré de la coding tu sais même pas gérer un bug.");
                                        break;
                                    }

                                    /* combat contre le Po */
                                    System.out.println("Vous y etes batter le PO pour finir la journée");
                                    /* fonction fight "return Bool True/False" fight(joueur,PO) */
                                    combat = Fight(joueur, po1);
                                    po1.setPointDeVie(50);
                                    if (combat){
                                        System.out.println("Bien joué le Po vous a tout réglé.");
                                    }else {
                                        System.out.println("Le Po pris d'énervement explose votre mac contre le mur, " +
                                                "oups... PERDU !.");
                                        break;
                                    }

                                    /* affronte benoit */
                                    System.out.println("Vous pensiez avoir fini votre journée mais benoit vous lance une attaque administrative");
                                    /* fonction fight "return Bool True/False" fight(joueur,Benoit) */
                                    combat = Fight(joueur, po2);
                                    po2.setPointDeVie(60);
                                    if (combat){
                                        System.out.println("Bien joué vous avez vaincu Benoit !");
                                    }else {
                                        System.out.println("Benoit vous vire... CHEH.");
                                        break;
                                    }

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
                break;
            }

            /* Incrémenter un jours */
            joueur.setDay(joueur.getDay() + 1);
            joueur.sauvegarderDonnees("sauvegarde.txt");
        }

    }
    public static void main(String[] args) {
        Joueur joueur = new Joueur("", 0, 0, false,
                new Hashtable<String, Integer>(), 0, 0, 0, 0);
        joueur.chargerDonnees("sauvegarde.txt");
        Main mainInstance = new Main();
        mainInstance.Start(joueur);
    }
}