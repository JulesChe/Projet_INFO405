
public class Gardien implements Complexe {
	
	/*Le gardien est une des nombreuses catégories de personne utilisant le complexe 
	 * pour le moment il peut rajouter des interventions,il peut également ce mettre en indisponibilté
	 */
	
	
	private String nom;
	private String prenom;
	private int idGardien;
	
	
	public Gardien(String nom,String prenom,int idGardien) {
		this.nom = nom;
		this.prenom = prenom;
		this.idGardien = idGardien;
	}
	
	public void interviens(Datetime creneau) {
		if (creneau.estdispo = false) {
			this.creneau = creneau;
			creneau.estdispo = true;
		}		
	}
	public void indisponible(Date creneau) {
		
	}

}
