package compteami;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PageAccueil extends JPanel implements Config{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private transient Connexion c;
    private transient SessionUser session;
    private transient JLabel titreLabel = new JLabel("Accueil");
    private transient JLabel messageAccueil = new JLabel();
    private transient JButton createEventBouton = new JButton("Creer un evenement");

    public PageAccueil(Connexion c, SessionUser current_session) {
        this.setName("pageAccueil");
        this.c = c;
        this.session = current_session;

        titreLabel.setBounds(0, 0, titreLabel.getPreferredSize().width, titreLabel.getPreferredSize().height);
        
        if (session.isStatutSession()) {
        	messageAccueil.setText("Voici la liste des evenements auxquels vous participez, sinon vous pouvez en creer un en cliquant sur le bouton prevu a cette effet");
        	this.add(createEventBouton, BorderLayout.EAST);
        }
        
        else {
        	String msgString = new String("Bienvenue sur l'application ComptAmi : \n"
        			+ "Cette application vous permet de creer des evenements avec vos amis avec un budget defini a l'avance !\n"
        			+ "Chaque participant met la somme qu'il souhaite invetir dans cet evenement.\n"
        			+ "Une messagerie propre a chaque evenement est egalement integree.\n"
        			+ "Bonne visite !");
        	messageAccueil.setText("<html>"+msgString.replaceAll("\n", "<br/>")+"</html>");
        }
        

        this.setLayout(new BorderLayout());
        this.add(titreLabel, BorderLayout.NORTH);
        this.add(messageAccueil, BorderLayout.WEST);
    }

}
