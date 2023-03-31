package model;
import java.util.ArrayList;

public class Gardien extends Utilisateur{
    // ATTRIBUTS
    private ArrayList<String> listeHabilitaion;

    // CONSTRUCTEUR
    public Gardien(String nom, int niveau) {
        super(nom,niveau);
        ArrayList<String> listeHabilitaion = new ArrayList<String>();
    }

    // METHODES

}
