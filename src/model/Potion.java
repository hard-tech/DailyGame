package model;

public class Potion extends Objet{
    public Potion(String nom, String capacite, int valeur) {
        super(nom, capacite, valeur);
    }

    @Override
    public String interagir(Object cible) {
        return super.interagir(cible);
    }
}
