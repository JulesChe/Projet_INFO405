package ressource;

import org.mindrot.jbcrypt.BCrypt;
public abstract class PasswordHash {

    private String hashMdp;

    public static String getHashPassword(String mdp){

        // Hacher le mot de passe
        String hashMdp = BCrypt.hashpw(mdp, BCrypt.gensalt());

        return hashMdp;

    }

    public static boolean isPasswordValid(String mdp, String hashMdp){

        // Vérifier le mot de passe
        boolean res = BCrypt.checkpw(mdp, hashMdp);

        return res;
    }


}
