package compteami;

import java.awt.Dimension;

public interface Config {
    Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    public final int HAUTEUR_FENETRE = (int)dimension.getHeight();
    public final int LARGEUR_FENETRE = (int)dimension.getWidth();
    public final int HAUTEUR_NAVIGATION = HAUTEUR_FENETRE*5/100;
    public final int HAUTEUR_PAGE = HAUTEUR_FENETRE - HAUTEUR_NAVIGATION;
    public final int POS_X = 0;
    public final int POS_Y = 0;


    public final String MAIL = "comptami456@gmail.com";
    public final String MOT_DE_PASSE = "BaobabMVP";
    
    //FENETRE
    public final int PAGE_ACCUEIL = 1;
    public final int PAGE_MESSAGE = 2;
    public final int PAGE_CONNEXION = 3;
    public final int PAGE_INSCRIPTION = 4;
}
