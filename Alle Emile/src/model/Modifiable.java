package model;

public interface Modifiable {
    // METHODES
    public void ajouter(Creneau c,Association a);
    public void accepter(Demande d);
    public void supprimer(Creneau c);

}