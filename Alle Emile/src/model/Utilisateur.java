package model;

public abstract class Utilisateur {
    // ATTRIBUTS
    private String nom;
    private int niveau;

    // CONSTRUCTEURS
    public Utilisateur(String nom, int niveau){
        this.nom = nom;
        this.niveau = niveau;
    }

    // METHODE
    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNiveau() {
        return niveau;
    }

}
