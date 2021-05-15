package compteami;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.sun.mail.handlers.text_html;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class PageConnexion extends JPanel implements Config{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private transient Connexion c;
    private transient JTextField TextPseudo = new JTextField("");
    private transient JPasswordField TextPassword = new JPasswordField("");
    private transient JButton BoutonValider = new JButton("Se Connecter");
    private transient JButton boutonInscription = new JButton("S'Inscrire");
    private transient JLabel LabelTitre = new JLabel("Connexion");
    private transient JLabel LabelPseudo = new JLabel("Pseudo : ");
    private transient JLabel LabelPassword = new JLabel("Password : ");
    private transient JLabel LabelErreur = new JLabel();

    /**
     * Affichage de la page qui permet à l'utilisateur de s'authentifier
     * @param c
     */
    public PageConnexion(Connexion c){
    	this.setName("pageConnexion");
        this.c = c; 

        //DÃ©finition taille des champs        
        int FieldWidth = LARGEUR_FENETRE/3;
        int FieldHeight;
        FontMetrics metrics = TextPseudo.getFontMetrics(getFont());
        FieldHeight = metrics.getHeight()+8;
        int FieldSpacing = FieldHeight/2;

        TextPseudo.setBounds((LARGEUR_FENETRE/2 - FieldWidth/2) , (HAUTEUR_FENETRE/2 - FieldHeight*2 - FieldSpacing - FieldSpacing/2) , FieldWidth, FieldHeight);
        TextPassword.setBounds((LARGEUR_FENETRE/2 - FieldWidth/2) , (HAUTEUR_FENETRE/2 - FieldHeight - FieldSpacing/2) , FieldWidth, FieldHeight);
      
        //Label devant les champs
        int LabelSpacing = 5;
        int LabelWidth;
        int LabelHeight;
        //Position + Taille Pseudo
        metrics = LabelPseudo.getFontMetrics(getFont());
        LabelWidth = metrics.stringWidth(LabelPseudo.getText()+2);
        LabelHeight = metrics.getHeight()+5;
        LabelPseudo.setBounds((LARGEUR_FENETRE/2 - FieldWidth/2 - LabelWidth - LabelSpacing) , (HAUTEUR_FENETRE/2 - FieldHeight*2 - FieldSpacing - FieldSpacing/2) , LabelWidth , LabelHeight);
        //Position + Taille Password
        metrics = LabelPassword.getFontMetrics(getFont());
        LabelWidth = metrics.stringWidth(LabelPassword.getText()+2);
        LabelHeight = metrics.getHeight()+5;
        LabelPassword.setBounds((LARGEUR_FENETRE/2 - FieldWidth/2 - LabelWidth - LabelSpacing) , (HAUTEUR_FENETRE/2 - FieldHeight - FieldSpacing/2) , LabelWidth , LabelHeight);
        

        // Position + Taille Boutons        int boutonSpacing = 5;
        Dimension validerButtonSize = BoutonValider.getPreferredSize();
        BoutonValider.setBounds((LARGEUR_FENETRE/2 -validerButtonSize.width/2) , (HAUTEUR_FENETRE/2 + 2*FieldSpacing + FieldSpacing/2 + 2*FieldHeight) , validerButtonSize.width+1 , validerButtonSize.height);        
        
        //Titre de la page
        Font defaultFont = LabelTitre.getFont();
        defaultFont = defaultFont.deriveFont(30.0f);
        LabelTitre.setFont(defaultFont);
        metrics = LabelTitre.getFontMetrics(defaultFont);
        LabelWidth = metrics.stringWidth(LabelTitre.getText())+4;
        LabelHeight = metrics.getHeight();
        LabelTitre.setBounds((LARGEUR_FENETRE/2 - LabelWidth/2) , (HAUTEUR_FENETRE/2 - 2*FieldHeight - 3*FieldSpacing - FieldSpacing/2 - LabelHeight) , LabelWidth , LabelHeight);
        
        
        //Action bouton valider
        BoutonValider.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
            	String PasswordToString = new String(TextPassword.getPassword());
            	
            	if (TextPseudo.getText().trim().isEmpty() && PasswordToString.trim().isEmpty()) {
            		LabelErreur.setText("You must complete all the fields");
            		LabelErreur.setBounds((LARGEUR_FENETRE/2 - (LabelErreur.getPreferredSize().width/2)), (BoutonValider.getY() + validerButtonSize.height + 10), LabelErreur.getPreferredSize().width, LabelErreur.getPreferredSize().height);
            	}
            	
            	else {
            		
            		if (TextPseudo.getText().trim().isEmpty()) {
	            		LabelErreur.setText("Pseudo field is empty");
	            		LabelErreur.setBounds((TextPseudo.getX() + FieldWidth + LabelSpacing) , TextPseudo.getY() , LabelErreur.getPreferredSize().width , LabelErreur.getPreferredSize().height);
	            		LabelErreur.repaint();
	            	}
	            	
            		else if (PasswordToString.trim().isEmpty()) {
	            		LabelErreur.setText("Password field is empty");
	            		LabelErreur.setBounds((TextPassword.getX() + FieldWidth + LabelSpacing) , TextPassword.getY() , LabelErreur.getPreferredSize().width , LabelErreur.getPreferredSize().height);
	            		LabelErreur.repaint();
	            	}
            		
            		else {
            			Utilisateur new_user = new Utilisateur(69, TextPseudo.getText(), null, 0, PasswordToString);
            			if (c.Authentification(new_user)) {
            				System.out.println("connexion ok");
            				Fenetre.userPseudoLabel.setText(TextPseudo.getText());
            				//Fenetre.userPseudoLabel.setPreferredSize(new Dimension(Fenetre.userPseudoLabel.getPreferredSize().width, Fenetre.userPseudoLabel.getPreferredSize().height));
            				Fenetre.navigationBar.repaint();
            			} else {
                    		LabelErreur.setText("Pseudo or password are incorrect");
                    		LabelErreur.setBounds((LARGEUR_FENETRE/2 - (LabelErreur.getPreferredSize().width/2)), (BoutonValider.getY() + validerButtonSize.height + 10), LabelErreur.getPreferredSize().width, LabelErreur.getPreferredSize().height);
                    		LabelErreur.repaint();
            				System.out.println("connexion pas ok");
            			}
            			
            		}
            	}
            }
        });
        
        
        this.setLayout(null);
        this.add(TextPseudo);
        this.add(TextPassword);
        this.add(LabelPseudo);
        this.add(LabelPassword);
        this.add(BoutonValider);
        this.add(LabelTitre);
        this.add(LabelErreur);
        this.add(boutonInscription);
    }
}
