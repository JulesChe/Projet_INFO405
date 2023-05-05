package model;

import java.util.ArrayList;
import java.util.List;

public class Utilisateur {
    // ATTRIBUTS
    private int id;
    private String nom;
    private int niveau;
    private String prenom;
    private String mdp;

    private List<Creneau> indisponibilites;


    // CONSTRUCTEURS
    public Utilisateur(String nom,String prenom,String mdp, int niveau){
        this.nom = nom;
        this.niveau = niveau;
        this.prenom= prenom;
        this.mdp = mdp;
    }

    public Utilisateur(){

    }

    // METHODE

    public List<Creneau> getIndisponibilites() {
        return indisponibilites;
    }

    public void setIndisponibilites(List<Creneau> indisponibilites) {
        this.indisponibilites = indisponibilites;
    }

    public void addIndisponibilite(Creneau creneau) {
        this.indisponibilites.add(creneau);
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNiveau() {
        return niveau;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }


}
