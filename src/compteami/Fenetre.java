package compteami;

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



public class Fenetre extends JPanel implements Config{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    public static JFrame fenetre;
    public static SessionUser session;
    public static PageAccueil pAccueil;
    private static PageEvenement pEvent;
    private static PageInscription pInscription;
    private static PageConnexion pConnexion;
    private static PageMessagerie pMessagerie;
    public static JToolBar navigationBar = new JToolBar();
    private static JButton accueilBouton = new JButton("Accueil");
    private static JButton messagerieBouton = new JButton("Messagerie");
    private static JButton connexionBouton = new JButton("Se Connecter");
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
        
        session = new SessionUser(null);


        Connexion connect = new Connexion();
		Utilisateur user = new Utilisateur(1, "Paul", "mail", 0, "BbQDuDim");
        Utilisateur user2 = new Utilisateur(2, "Richard", "mail", 0, "Jjkj");
        //connect.Inscription(user);
        System.out.println(connect.Authentification(user2));
        connect.Inscription(user2);

        Evenement event = new Evenement("Manger avec tata michelle", 40, "On mange samedi matin chez tata", new Date(2021, 04, 11), new Date(2021, 05, 11), connect);
        pAccueil = new PageAccueil(connect, session);
        pEvent = new PageEvenement(event, connect, user);
        pInscription = new PageInscription(connect);
        pConnexion = new PageConnexion(connect, session, pAccueil);
        pMessagerie = new PageMessagerie(event, connect, user);
        
        //Barre de navigation
        navigationBar.setName("navigationBarre");
        navigationBar.setPreferredSize(new Dimension(LARGEUR_FENETRE, HAUTEUR_NAVIGATION));
        if (!session.isStatutSession()) {
        	userPseudoLabel.setText("Bonjour, veuillez vous connecter pour acceder aux differents evenements auxquels vous participez");
        } else {
        	userPseudoLabel.setText("Bonjour "+session.getUser().getPseudo()+" !");
        }
        userPseudoLabel.setBounds(0, 0, 0, 0);

                
        //Action des boutons
        accueilBouton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Component[] components = fenetre.getContentPane().getComponents();
	            for (Component component : components) {
	            	if (component.getName().equals("pageConnexion")) {
	                	fenetre.remove(pConnexion);
	                }
	                
	                else if (component.getName().equals("pageInscription")) {
	                	fenetre.remove(pInscription);
	                }
	                
	                else if (component.getName().equals("pageEvent")) {
	                	fenetre.remove(pAccueil);
	                }
	                
	                else if (component.getName().equals("pageMessagerie")) {
	                	fenetre.remove(pMessagerie);
	                }
	            }
				fenetre.add(pAccueil);
				fenetre.repaint();
				fenetre.pack();
			}
        });
        
        messagerieBouton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Component[] components = fenetre.getContentPane().getComponents();
	            for (Component component : components) {
	                if (component.getName().equals("pageConnexion")) {
	                	fenetre.remove(pConnexion);
	                }
	                
	                else if (component.getName().equals("pageInscription")) {
	                	fenetre.remove(pInscription);
	                }
	                
	                else if (component.getName().equals("pageEvent")) {
	                	fenetre.remove(pAccueil);
	                }
	                
	                else if (component.getName().equals("pageMessagerie")) {
	                	fenetre.remove(pMessagerie);
	                }
	            }
				fenetre.add(pMessagerie);
				fenetre.repaint();
				fenetre.pack();
			}
        });
        
        inscriptionBouton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Component[] components = fenetre.getContentPane().getComponents();
	            for (Component component : components) {
	                if (component.getName().equals("pageConnexion")) {
	                	fenetre.remove(pConnexion);
	                }
	                
	                else if (component.getName().equals("pageInscription")) {
	                	fenetre.remove(pInscription);
	                }
	                
	                else if (component.getName().equals("pageEvent")) {
	                	fenetre.remove(pAccueil);
	                }
	                
	                else if (component.getName().equals("pageMessagerie")) {
	                	fenetre.remove(pMessagerie);
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
				Component[] components = fenetre.getContentPane().getComponents();
	            for (Component component : components) {
	                if (component.getName().equals("pageConnexion")) {
	                	fenetre.remove(pConnexion);
	                }
	                
	                else if (component.getName().equals("pageInscription")) {
	                	fenetre.remove(pInscription);
	                }
	                
	                else if (component.getName().equals("pageEvent")) {
	                	fenetre.remove(pAccueil);
	                }
	                
	                else if (component.getName().equals("pageMessagerie")) {
	                	fenetre.remove(pMessagerie);
	                }
	            }
				fenetre.add(pConnexion);
				fenetre.repaint();
				fenetre.pack();
			}
        });

        
        //Ajout des boutons dans la barre de navigation
        //Boutons de gauche
        JToolBar toolBarGauche = new JToolBar();
        toolBarGauche.setFloatable(false);
        toolBarGauche.addSeparator();
        toolBarGauche.add(accueilBouton);
        toolBarGauche.addSeparator();
        toolBarGauche.add(messagerieBouton);
        
        //Boutons de droite
        JToolBar toolBarDroite = new JToolBar();
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
        
        /*
        navigationBar.setFloatable(false);
        navigationBar.addSeparator();
        navigationBar.add(accueilBouton, BorderLayout.WEST);
        navigationBar.addSeparator();
        navigationBar.add(messagerieBouton, BorderLayout.WEST);
        navigationBar.addSeparator();
        navigationBar.add(userPseudoLabel);
        navigationBar.addSeparator();
        navigationBar.add(inscriptionBouton, BorderLayout.EAST);
        navigationBar.addSeparator();
        navigationBar.add(connexionBouton, BorderLayout.EAST);
        navigationBar.addSeparator(); */    
        

        /*
        Mail email = new Mail("morestelthomas445648456@gmail.com", "Yo", "^^");
        email.envoyer();
        */
        // Remboursement
        // System.out.println(event.getBudget());
        // event.remboursement(12);
        // System.out.println(event.getBudget());
        
        fenetre.add(navigationBar, BorderLayout.NORTH);
        fenetre.add(pAccueil, BorderLayout.SOUTH);
        
        // PageMessagerie pMessagerie = new PageMessagerie(event, connect, user);
        // fenetre.add(pMessagerie);

        //PageInscription pInscription = new PageInscription(connect);
        //fenetre.add(pInscription);
        fenetre.pack();
		fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //fenetre.setExtendedState(JFrame.MAXIMIZED_BOTH); 
        device.setFullScreenWindow(fenetre);
		fenetre.setVisible(true);
    }
}
