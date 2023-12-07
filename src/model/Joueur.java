package model;
import java.util.*;

public class Joueur extends  Personnage implements Interactuable{
    private Hashtable<String, Integer> inventaire = new Hashtable<>();
    private Arme armeActuelle = new Arme("", 0);
    private int exp = 0;
    private int niv = 5;
    private int pins = 0;

    public Joueur(String nom, int pointDeVie, int force, boolean defense, Hashtable<String, Integer> inventaire,
                  int exp, int niv, int pins) {
        super(nom, pointDeVie, force, defense);
        this.inventaire = inventaire;
        this.exp = exp;
        this.niv = niv;
        this.pins = pins;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getNiv() {
        return niv;
    }

    public void setNiv(int niv) {
        this.niv = niv;
    }

    public Hashtable<String, Integer> getInventaire() {
        return inventaire;
    }

    public void setInventaire(Hashtable<String, Integer> inventaire) {
        this.inventaire = inventaire;
    }

    public Arme getArmeActuelle() {
        return armeActuelle;
    }

    public void setArmeActuelle(Arme armeActuelle) {
        this.armeActuelle = armeActuelle;
    }

    public void levelUp(){
        this.setExp(this.getExp() - 35);
        this.setForce(this.getForce() + 1);
        this.setPointDeVie(this.getPointDeVie() + 3);
        System.out.println("Level Up !");
    }

    public int getPins() {
        return pins;
    }

    public void setPins(int pins) {
        this.pins = pins;
    }

    public void lootEnnemi(Ennemi ennemi) {
        Dictionary<String, Integer> lootEnnemi = ennemi.getLoot();

        if (!lootEnnemi.isEmpty()) {
            System.out.println("Vous avez looté les éléments suivants :");

            // Ajoutez les éléments lootés à l'inventaire du joueur
            Enumeration<String> keys = lootEnnemi.keys();
            while (keys.hasMoreElements()) {
                String element = keys.nextElement();
                int quantite = lootEnnemi.get(element);
                System.out.println(element + " : " + quantite);
                if (element == "exp"){
                    this.setExp(this.getExp() + quantite);
                }
                else if (element == "pins"){
                    this.setPins(this.getPins() + quantite);
                }
                else if (this.inventaire.containsKey(element)){
                    int quantiteActuelle = this.inventaire.get(element);
                    this.inventaire.put(element, quantiteActuelle + quantite);
                } else {
                    // Sinon, ajoutez l'élément à l'inventaire du joueur
                    this.inventaire.put(element, quantite);
                }

            }
        }
    }

    public String Utiliser(Joueur cible) {
        String i = "0";
        String choix = "0";
        Scanner scanner = new Scanner(System.in);

        System.out.println("Quel objet voulez-vous utiliser ?");
        Enumeration<String> keys = cible.getInventaire().keys();
        while (keys.hasMoreElements()) {
            i += 1;
            String element = keys.nextElement();
            int quantite = cible.getInventaire().get(element);
            System.out.println(i + ". " + element + " : " + quantite);
        }
        choix = scanner.next();
        return choix;
    }

    /* Utiliser un objet */
    public void UtiliserObjet(Joueur joueur, Objet objet) {
        if (objet.getCapacite().toLowerCase().contains("heal")) {
            this.setPointDeVie(this.getPointDeVie() + objet.getValeur());
            int pv = objet.getValeur();
            System.out.println("Vous prenez " + objet.getNom() + " vous gagnez : " + pv + " PV.");

            // Mettez à jour la quantité de l'objet dans l'inventaire
            String nomObjet = objet.getNom();
            if (joueur.getInventaire().containsKey(nomObjet)) {
                System.out.println("un objet est retiré.");
                int quantiteActuelle = joueur.getInventaire().get(nomObjet);
                joueur.getInventaire().replace(nomObjet, quantiteActuelle - 1);
            }
        }
    }
    /* Attaquer */

    @Override
    public void Attaquer(Personnage target) {
        int degat = 0;
        if (target.isDefense()) {
            target.setPointDeVie(target.getPointDeVie() - (this.getForce() / 2));
            degat = (this.getForce() / 2);
        } else {
            target.setPointDeVie(target.getPointDeVie() - this.getForce());
            degat = this.getForce();
        }
        target.setDefense(false);
        if (target instanceof Bug) {
            System.out.println("Vous écrivez un nouveau code qui tue ça mère. Vous infligez " +
                    degat + " dégats");
        }
        if (target instanceof Po) {
            System.out.println("Vous demandez encore une solution a un nième problème que vous avez. " +
                    "Vous infligez " +
                    degat + " dégats");
        }
        if (target instanceof Sncf) {
            System.out.println("Vous insultez mentalement tout les travailleurs de la SNCF pour leurs problèmes. " +
                    "Vous infligez " +
                    degat + " dégats");
        }
    }

    /* Defendre */

    @Override
    public void Defendre(Personnage target) {
        this.setDefense(true);
        if (target instanceof Bug) {
            System.out.println("Vous installez un VPN et vous vous préparez au prochain bug.");
        }
        if (target instanceof Po) {
            System.out.println("Vous vous préparez mentalement à l'ennuie et au sommeil " +
                    "qui va arriver.");
        }
        if (target instanceof Sncf) {
            System.out.println("Vous mettez votre casque et mettez votre musique pour " +
                    "ne pas entendre les nouvelles (vous êtes un peu dans le déni).");
        }
    }

    // déséquiper
    public void Desequiper(Arme arme){
        this.setForce(this.getForce() - arme.getForceBoost());
        this.setArmeActuelle(new Arme("", 0));
    }

    // équiper
    public void Equiper(Arme arme){
        if (this.getArmeActuelle().getNom() == ""){
            this.setForce(this.getForce() + arme.getForceBoost());
            this.setArmeActuelle(arme);
        }
        else{
            Desequiper(this.armeActuelle);
            this.setForce(this.getForce() + arme.getForceBoost());
            this.setArmeActuelle(arme);
        }
    }

    @Override
    public String interagir(Object cible) {
        String choix = "0";
        if (this instanceof Personnage) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Que voulez-vous faire ?");
            System.out.println("1. Attaquer");
            System.out.println("2. Défense");
            System.out.println("3. J'ai besoin d'une pause");
            System.out.println("4. Analyse");

            choix = scanner.next();
        }
        return choix;
    }
}

