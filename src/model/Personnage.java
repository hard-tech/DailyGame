package model;
import java.util.Scanner;
public class Personnage implements Interactuable {
    private String nom;
    private int pointDeVie;
    private int force;
    private boolean defense;

    public boolean isDefense() {
        return defense;
    }

    public void setDefense(boolean defense) {
        this.defense = defense;
    }

    public String getNom() {
        return nom;
    }

    public int getPointDeVie() {
        return pointDeVie;
    }

    public int getForce() {
        return force;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPointDeVie(int pointDeVie) {
        this.pointDeVie = pointDeVie;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public Personnage(String nom, int pointDeVie, int force, boolean defense) {
        this.nom = nom;
        this.pointDeVie = pointDeVie;
        this.force = force;
        this.defense = defense;
    }

    /* Methode qui à pour action d'attaquer */
    public void Attaquer(Ennemi target){
        if (target.isDefense()){
            target.setPointDeVie(target.getPointDeVie() - (this.getForce() / 2));
        }
        else {
            target.setPointDeVie(target.getPointDeVie() - this.getForce());
        }
        target.setDefense(false);
    }

    /* Methode qui à pour action de se défendre */
    public void Defendre(){
        this.setDefense(true);
    }

    @Override
    public void interagir(Object cible) {

        if (cible instanceof Personnage) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Que voulez-vous faire ?");
            System.out.println("1. Ramasser");
            System.out.println("2. Utiliser");
            System.out.println("3. Parler à un PNJ");

            int choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    ramasser();
                    break;
                case 2:
                    utiliser();
                    break;
                case 3:
                    parlerPNJ();
                    break;
                default:
                    System.out.println("Choix invalide");
            }
        }
    }

    private void ramasser() {
        System.out.println("Le personnage ramasse un objet.");
        // Implémentez la logique pour ramasser ici
    }

    private void utiliser() {
        System.out.println("Le personnage utilise un objet.");
        // Implémentez la logique pour utiliser ici
    }

    private void parlerPNJ() {
        System.out.println("Le personnage parle à un PNJ.");
        // Implémentez la logique pour parler à un PNJ ici
    }
}
