package compteami;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.JToolBar.Separator;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

public class PageAccueil extends JPanel implements Config{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private transient Connexion c;
    private transient SessionUser session;
    private transient JLabel titreLabel = new JLabel("Accueil - Compt'Ami");
    private transient JLabel messageAccueil = new JLabel();
    private transient JButton createEventBouton = new JButton("Creer un evenement");

    public PageAccueil(Connexion c, SessionUser current_session) {
        this.setName("pageAccueil");
        this.c = c;
        this.session = current_session;
        
        Font defaultFont = titreLabel.getFont();
        defaultFont = defaultFont.deriveFont(30.0f);
        titreLabel.setFont(defaultFont);
        titreLabel.setBounds((LARGEUR_FENETRE/2 - (titreLabel.getPreferredSize().width+10)/2),
        		(15 + titreLabel.getPreferredSize().height),
        		titreLabel.getPreferredSize().width+10,
        		titreLabel.getPreferredSize().height);
        
        if (session.isStatutSession()) {
        	messageAccueil.setText("Voici la liste des evenements auxquels vous participez, sinon vous pouvez en creer un en cliquant sur le bouton prevu a cette effet");
        	JButton[] listeBouton = new JButton[15];
            ArrayList<Evenement> liste = c.ChargerEvent(session.getUser().getId());
            int i = 0;
            int ecart = 60;
            PageAccueil pA = this;
            for (Evenement e : liste){
                listeBouton[i] = new JButton(e.getIntitule());
                listeBouton[i].setBounds(100, 100 + ecart, 180, 40);
                listeBouton[i].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ee) {
                       PageEvenement pEvent = new PageEvenement(e, c, session.getUser());
                       Component[] components = Fenetre.fenetre.getContentPane().getComponents();
                       Fenetre.fenetre.remove(pA);
                       Fenetre.fenetre.add(pEvent);
                       Fenetre.fenetre.repaint();
            	       Fenetre.navigationBar.repaint();
            	       Fenetre.fenetre.pack();
                    }
                });
                
                this.add(listeBouton[i], BorderLayout.EAST);
                ecart+=20;
                i++;
            }
            
            this.add(createEventBouton, BorderLayout.EAST);
        	messageAccueil.setBounds((LARGEUR_FENETRE/2 - (messageAccueil.getPreferredSize().width+10)/2) ,
        			titreLabel.getY()+50 ,
        			messageAccueil.getPreferredSize().width+10,
        			messageAccueil.getPreferredSize().height);
        	createEventBouton.setBounds((LARGEUR_FENETRE/2 - (createEventBouton.getPreferredSize().width+5)/2),
        			messageAccueil.getY()+50, 
        			createEventBouton.getPreferredSize().width+5,
        			createEventBouton.getPreferredSize().height);
        	
        }
        
        else {
        	String msgString = new String("Bienvenue sur l'application ComptAmi : \n"
        			+ "Cette application vous permet de creer des evenements avec vos amis avec un budget defini a l'avance !\n"
        			+ "Chaque participant met la somme qu'il souhaite invetir dans cet evenement.\n"
        			+ "Une messagerie propre a chaque evenement est egalement integree.\n"
        			+ "Bonne visite !");
        	messageAccueil.setText("<html>"+msgString.replaceAll("\n", "<br/>")+"</html>");
        	messageAccueil.setBounds((LARGEUR_FENETRE/2 - messageAccueil.getPreferredSize().width/2) ,
        			(HAUTEUR_FENETRE/2 - (messageAccueil.getPreferredSize().height+20)/2) ,
        			messageAccueil.getPreferredSize().width,
        			messageAccueil.getPreferredSize().height+20);
        }
        

        this.setLayout(null);
        this.add(titreLabel);
        this.add(messageAccueil);
        this.add(createEventBouton);
    }

}
