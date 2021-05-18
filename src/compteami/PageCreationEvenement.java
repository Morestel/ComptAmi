package compteami;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class PageCreationEvenement implements Config{

	private static JFrame fenetre;
	private static Evenement event;
	private static JTextField intituleField = new JTextField();
	private static JTextField descriptionField = new JTextField();
	private static JLabel argentJLabel = new JLabel("0");
	private static JButton augmenterMontantButton = new JButton("+");
	private static JButton diminuerMontantButton = new JButton("-");
	private static JButton creationEvenementButton = new JButton("Creer");
	private static JLabel intituleJLabel = new JLabel("Intitule de l'evenement :");
	private static JLabel descriptionJLabel = new JLabel("Description :");
	private static JLabel montantJLabel = new JLabel("Montant :");
	private static JLabel startDateJLabel = new JLabel("Date de début :");
	private static JLabel endDateJLabel = new JLabel("Date de fin :");
	private static JTextField startDateField = new JTextField("yyyy-mm-dd");
	private static JTextField endDateField = new JTextField("yyyy-mm-dd");
	
	public PageCreationEvenement(Connexion c, Utilisateur current_user) {		
		fenetre = new JFrame("Creer un evenement");
		fenetre.setLayout(null);
		
		fenetre.setLocation((LARGEUR_FENETRE/2 - fenetre.getPreferredSize().width/2), (HAUTEUR_FENETRE/2 - fenetre.getPreferredSize().height/2));
		fenetre.setBackground(Color.GRAY);
		fenetre.setPreferredSize(new Dimension(600 , 250));		
		
		intituleJLabel.setBounds(10, 10, intituleJLabel.getPreferredSize().width+5, intituleJLabel.getPreferredSize().height);
		descriptionJLabel.setBounds(intituleJLabel.getX(), (intituleJLabel.getY()+intituleJLabel.getHeight()+10), descriptionJLabel.getPreferredSize().width+2, descriptionJLabel.getPreferredSize().height);
		intituleField.setBounds((intituleJLabel.getX()+intituleJLabel.getWidth()+5), intituleJLabel.getY(), 300, intituleField.getPreferredSize().height);
		descriptionField.setBounds(descriptionJLabel.getX()+descriptionJLabel.getWidth()+5, descriptionJLabel.getY(), 300, descriptionField.getPreferredSize().height);
		montantJLabel.setBounds(descriptionJLabel.getX(), descriptionJLabel.getY()+descriptionJLabel.getHeight()+10, montantJLabel.getPreferredSize().width+2, montantJLabel.getPreferredSize().height);
		argentJLabel.setBounds(descriptionField.getX(), (descriptionField.getY()+descriptionField.getHeight()+10), argentJLabel.getPreferredSize().width+20, argentJLabel.getPreferredSize().height);
		diminuerMontantButton.setBounds(argentJLabel.getX(), (argentJLabel.getY()+argentJLabel.getHeight()+10), diminuerMontantButton.getPreferredSize().width, diminuerMontantButton.getPreferredSize().height);
		augmenterMontantButton.setBounds((diminuerMontantButton.getX()+diminuerMontantButton.getPreferredSize().width +5), diminuerMontantButton.getY(), augmenterMontantButton.getPreferredSize().width, augmenterMontantButton.getPreferredSize().height);
		startDateJLabel.setBounds(diminuerMontantButton.getX(), (diminuerMontantButton.getY()+diminuerMontantButton.getHeight()+10), startDateJLabel.getPreferredSize().width+2, startDateJLabel.getPreferredSize().height);
		startDateField.setBounds(startDateJLabel.getX()+startDateJLabel.getWidth()+5, startDateJLabel.getY(), startDateField.getPreferredSize().width+2 , startDateField.getPreferredSize().height);
		endDateJLabel.setBounds(startDateJLabel.getX(), startDateJLabel.getY()+startDateJLabel.getHeight()+5, endDateJLabel.getPreferredSize().width+2, endDateJLabel.getPreferredSize().height);
		endDateField.setBounds(endDateJLabel.getX()+endDateJLabel.getWidth()+5, endDateJLabel.getY(), endDateField.getPreferredSize().width+2 , endDateField.getPreferredSize().height);
		creationEvenementButton.setBounds(endDateJLabel.getX(), (endDateJLabel.getY()+endDateJLabel.getHeight()+10), (creationEvenementButton.getPreferredSize().width+2) , creationEvenementButton.getPreferredSize().height);
		

		
		
		//Action des boutons
		diminuerMontantButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int montant = Integer.parseInt(argentJLabel.getText());
				if (montant > 0) {
					montant--;
				}
				argentJLabel.setText(montant+"");
			}
		});
		
		augmenterMontantButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int montant = Integer.parseInt(argentJLabel.getText());
				montant++;
				argentJLabel.setText(montant+"");				
			}
		});
		
		creationEvenementButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String startDateString = startDateField.getText();
				String endDateString = endDateField.getText();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				try {
					java.util.Date startDate = format.parse(startDateString);
					java.util.Date endDate = format.parse(endDateString);
					java.sql.Date startSQLDate = new java.sql.Date(startDate.getTime());
					java.sql.Date endSQLDate = new java.sql.Date(endDate.getTime());
					event = new Evenement(0, intituleField.getText(), Integer.parseInt(argentJLabel.getText()), descriptionField.getText(), startSQLDate, endSQLDate);
					c.Creer_Event(event);
					event.setId(c.RetournerIdEvent(event));
					String id_userString = String.valueOf(current_user.getId());
					c.Participe(id_userString, event);
					fenetre.dispose();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}	
			}
		});
		
		fenetre.add(intituleJLabel);
		fenetre.add(descriptionJLabel);
		fenetre.add(intituleField);
		fenetre.add(descriptionField);
		fenetre.add(montantJLabel);
		fenetre.add(argentJLabel);
		fenetre.add(augmenterMontantButton);
		fenetre.add(diminuerMontantButton);
		fenetre.add(startDateJLabel);
		fenetre.add(startDateField);
		fenetre.add(endDateJLabel);
		fenetre.add(endDateField);
		fenetre.add(creationEvenementButton);
		fenetre.repaint();
		fenetre.pack();
		fenetre.setVisible(true);
	}
}
