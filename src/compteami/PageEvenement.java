package compteami;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PageEvenement extends JPanel implements Config{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    private transient Evenement event;
    private transient JButton BoutonDelete;
    private transient JButton BoutonDepense;
    private transient Connexion c;
    private transient JTextField TextMontant;
    private transient Utilisateur user;
    private transient JButton messagerieBouton = new JButton("Messagerie");
    
    /**
     * Affiche la page de l'evenement passe en parametre
     * @param event
     * @param c
     * @param current_user
     */
    public PageEvenement(Evenement event, Connexion c, Utilisateur current_user){
    	this.setName("pageEvent");
        this.c = c;
        this.event = event;
        this.user = current_user;
        this.setPreferredSize(new Dimension(LARGEUR_FENETRE, HAUTEUR_PAGE));

        TextMontant = new JTextField(6);
        BoutonDelete = new JButton("Supprimer");
        BoutonDepense = new JButton("Rembourser");
        
        PageMessagerie pMessagerie = new PageMessagerie(event, c, user);

        TextMontant.setBounds(500, 500, 50, 35);
        TextMontant.setHorizontalAlignment(JTextField.CENTER);
        
        messagerieBouton.setBounds(10, 10, (messagerieBouton.getPreferredSize().width), (messagerieBouton.getPreferredSize().height));
        
        //Action du bouton delete
        BoutonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
               c.Delete_Event(event);
            }

        });
        //Action bouton depense
        BoutonDepense.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (!TextMontant.getText().isEmpty()){
                    int montant = Integer.parseInt(TextMontant.getText());
                    Connexion connect = new Connexion();
                    connect.Transaction(event.getId(), user.getId(), montant);
                            
                }
            }
        });
        
        //Action bouton messagerie
        messagerieBouton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Component[] components = Fenetre.fenetre.getContentPane().getComponents();
	            for (Component component : components) {
	            	if (!(component.getName().equals("navigationBarre"))) {
	                	Fenetre.fenetre.remove(component);
	            	}
	            }
	            Fenetre.fenetre.add(pMessagerie);
	            Fenetre.fenetre.repaint();
	            Fenetre.fenetre.pack();
			}
        });
        
        this.add(TextMontant);
        this.add(BoutonDelete);
        this.add(BoutonDepense);
        this.add(messagerieBouton);           
    }


    public Evenement getEvent() {
        return event;
    }

    public void setEvent(Evenement event) {
        this.event = event;
    }



    @Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
        g.drawString("Intitulé : " + getEvent().getIntitule(), 30, 60);
        g.drawString("Description : " + getEvent().getTexte(), 30, 100);
        g.drawString("Budget initial: " + String.valueOf(getEvent().getBudget()), 30, 140);
        g.drawString("Reste à payer: " + getEvent().getBudget(), 200, 140);
        g.drawString("Date début : " + getEvent().getStart().toString(), 30, 180);
        g.drawString("Date fin : " + getEvent().getEnd().toString(), 200, 180);

        
	}
}
