package model;

public class Utilisateur {
    // ATTRIBUTS
    private String nom;
    private int niveau;
    private String prenom;
    private String mdp;

    // CONSTRUCTEURS
    public Utilisateur(String nom,String prenom,String mdp, int niveau){
        this.nom = nom;
        this.niveau = niveau;
        this.prenom=prenom;
        this.mdp = mdp;
    }

    // METHODE
    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNiveau() {
        return niveau;
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
