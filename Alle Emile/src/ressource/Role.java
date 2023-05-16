package ressource;

public class Role {

    public String role(int grade){

        String res = "";

        switch(grade){

            case 1:
                res = "Gardien";
                break;

            case 2:
                res = "Logistique";
                break;

            case 3:
                res = "Directeur";
                break;
            default:
                res = "Role non indiqué";
                break;
        }


        return res;
    }

    public final static int NiveauGardien = 1;

    public final static int NiveauLogistique = 2;

    public final static int NiveauDirecteur = 3;


}
