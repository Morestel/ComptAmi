package compteami;

import javax.swing.JPanel;
import javax.swing.JToolBar;
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



public class Fenetre extends JPanel implements Config{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    public static JFrame fenetre;
    public static SessionUser session = new SessionUser(null);
    private static PageAccueil pAccueil;
    private static PageInscription pInscription;
    private static PageConnexion pConnexion;
    public static JToolBar navigationBar = new JToolBar();
    public static JToolBar toolBarGauche = new JToolBar();
    public static JToolBar toolBarDroite = new JToolBar();
    private static JButton accueilBouton = new JButton("Accueil");
    public static JButton connexionBouton = new JButton("Se Connecter");
    private static JButton inscriptionBouton = new JButton("S'Inscrire");
    public static JLabel userPseudoLabel = new JLabel();
    
    static GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
    
    public static void main(String[] args) {
		fenetre = new JFrame("Compte Entre Amis");
        fenetre.setUndecorated(true);
        fenetre.setLayout(new BorderLayout());
        
		fenetre.setSize(LARGEUR_FENETRE, HAUTEUR_FENETRE);
		fenetre.setLocation(POS_X, POS_Y);
		fenetre.setBackground(Color.GRAY);
        fenetre.setPreferredSize(new Dimension(LARGEUR_FENETRE, HAUTEUR_FENETRE));

        Connexion connect = new Connexion();
        
        pAccueil = new PageAccueil(connect, session);
        pInscription = new PageInscription(connect, session);
        pConnexion = new PageConnexion(connect, session);

        
        //Barre de navigation
        navigationBar.setName("navigationBarre");
        navigationBar.setPreferredSize(new Dimension(LARGEUR_FENETRE, HAUTEUR_NAVIGATION));
        if (!session.isStatutSession()) {
        	userPseudoLabel.setText("Bonjour, veuillez vous connecter pour acceder aux differents evenements auxquels vous participez");
        } else {
        	userPseudoLabel.setText("Bonjour "+session.getUser().getPseudo()+" !");
        	connexionBouton.setText("Se Deconnecter");
        	navigationBar.repaint();
        }
        userPseudoLabel.setBounds(0, 0, 0, 0);

        
        //Action des boutons
        accueilBouton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Component[] components = fenetre.getContentPane().getComponents();
				for (Component component : components) {
	            	if (!(component.getName().equals("navigationBarre"))) {
	                	fenetre.remove(component);
	            	}
	            }
				fenetre.add(new PageAccueil(connect, session));
				fenetre.repaint();
				fenetre.pack();
			}
        });
        
        inscriptionBouton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Component[] components = fenetre.getContentPane().getComponents();
				for (Component component : components) {
	            	if (!(component.getName().equals("navigationBarre"))) {
	                	fenetre.remove(component);
	            	}
	            }
				fenetre.add(pInscription);
				fenetre.repaint();
				fenetre.pack();
			}
        });
        
        connexionBouton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!session.isStatutSession()) {
					Component[] components = fenetre.getContentPane().getComponents();
					for (Component component : components) {
		            	if (!(component.getName().equals("navigationBarre"))) {
		                	fenetre.remove(component);
		            	}
		            }
				}
				
				else {
					userPseudoLabel.setText("Bonjour, veuillez vous connecter pour acceder aux differents evenements auxquels vous participez");
					connexionBouton.setText("Se connecter");
					session.setStatutSession(USER_NOT_CONNECTED);
					session.setUser(null);
					Component[] components = fenetre.getContentPane().getComponents();
					for (Component component : components) {
		            	if (component.getName().equals("pageAccueil")) {
		                	fenetre.remove(component);
		                	fenetre.add(new PageAccueil(connect, session));
		                	fenetre.repaint();
		                	fenetre.pack();
		            	}
		            }
				}

	            navigationBar.repaint();
				fenetre.add(pConnexion);
				fenetre.repaint();
				fenetre.pack();
			}
        });

        
        //Ajout des boutons dans la barre de navigation
        //Boutons de gauche
        toolBarGauche = new JToolBar();
        toolBarGauche.setFloatable(false);
        toolBarGauche.addSeparator();
        toolBarGauche.add(accueilBouton);
        toolBarGauche.addSeparator();
        
        //Boutons de droite
        toolBarDroite = new JToolBar();
        toolBarDroite.setFloatable(false);
        toolBarDroite.add(inscriptionBouton);
        toolBarDroite.addSeparator();
        toolBarDroite.add(connexionBouton);
        toolBarGauche.addSeparator();
        
        JToolBar toolBarCentre = new JToolBar();
        toolBarCentre.setFloatable(false);
        toolBarCentre.add(userPseudoLabel);
        
        navigationBar.setFloatable(false);
        navigationBar.setLayout(new BorderLayout());
        navigationBar.add(toolBarGauche, BorderLayout.WEST);
        navigationBar.add(toolBarDroite, BorderLayout.EAST);
        navigationBar.add(toolBarCentre, BorderLayout.CENTER);
        
        
        fenetre.add(navigationBar, BorderLayout.NORTH);
        fenetre.add(pAccueil, BorderLayout.SOUTH);
        fenetre.repaint();
        fenetre.pack();
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        device.setFullScreenWindow(fenetre);
		fenetre.setVisible(true);
    }
}
