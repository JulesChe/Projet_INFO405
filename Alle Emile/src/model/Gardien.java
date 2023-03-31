package model;
import java.util.ArrayList;

public class Gardien extends Utilisateur{
    // ATTRIBUTS
    private ArrayList<String> listeHabilitaion;

    // CONSTRUCTEUR
    public Gardien(String nom,String prenom,String mdp, int niveau) {
        super(nom,prenom,mdp,niveau);
        ArrayList<String> listeHabilitaion = new ArrayList<String>();
    }

    // METHODES

}
