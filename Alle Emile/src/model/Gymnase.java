package model;

public class Gymnase {
    // ATTRIBUTS
    private String nom;
    private String lieux;

    // CONSTRUCTEUR
    public Gymnase(String nom, String lieux) {
        super();
        this.nom = nom;
        this.lieux = lieux;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLieux() {
        return lieux;
    }

    public void setLieux(String lieux) {
        this.lieux = lieux;
    }



}
