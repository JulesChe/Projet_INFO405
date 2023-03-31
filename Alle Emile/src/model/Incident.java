package model;

public class Incident{
    // ATTRIBUTS
    private Infrastructure i;
    private String desc;
    private String reparateur;

    // CONSTRUCTEUR
    public Incident(Infrastructure i, String desc, String reparateur) {
        this.i = i;
        this.desc = desc;
        this.reparateur = reparateur;
    }

}