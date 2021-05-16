package compteami;

import javax.swing.JPanel;

public class PageAccueil extends JPanel implements Config{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private transient Connexion c;
    private transient SessionUser session;

    public PageAccueil(Connexion c, SessionUser current_session) {
        this.setName("pageAccueil");
        this.c = c;
        this.session = current_session;

    }

}
