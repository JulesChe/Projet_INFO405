package model;

public class Infrastructure extends Gymnase {
    // EXTENDS DE GYMNASE OU ATTRIBUTS GYMNASE A VOIR /!\
    // ATTRIBUTS
    private String type;
    private int capacite;
    private int numero;

    // CONSTRUCTEUR
    public Infrastructure(String nom, String lieux, String type, int capacite, int numero) {
        super(nom,lieux);
        this.type = type;
        this.capacite = capacite;
        this.numero = numero;
    }
}