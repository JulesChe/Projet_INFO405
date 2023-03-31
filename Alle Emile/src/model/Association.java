package model;
import java.util.ArrayList;

public class Association{
    // ATTRIBUTS
    private int nbLicence;
    private ArrayList<Creneau> listeCreneaux; // liste des créneaux de l'utilisateur
    private String sport;
    private String mdp;
    private String nom;

    // CONSTRUCTEUR
    public Association(String nom, String mdp) {
        this.nom = nom;
        this.mdp = mdp;
        ArrayList<Creneau> listeCreneaux = new ArrayList<Creneau>();
    }

    // METHODES

    public void setNbLicence(int nb) {
        nbLicence = nb;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setListeCreaneau(ArrayList<Creneau> listeCreneaux) {
        this.listeCreneaux = listeCreneaux;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }





    /*public Gardien getGardien(Creneau c) {

    }

    public void demande(Creneau c){

    }
    */
}