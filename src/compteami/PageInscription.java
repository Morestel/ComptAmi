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

public class PageInscription extends JPanel implements Config{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private transient Connexion c;
    private transient JTextField TextPseudo = new JTextField();
    private transient JPasswordField TextPassword = new JPasswordField();
    private transient JPasswordField TextConfirmPassword = new JPasswordField();
    private transient JTextField TextMail = new JTextField();
    private transient JButton BoutonValider = new JButton("S'Inscrire");
    private transient JLabel LabelTitre = new JLabel("Inscription");
    private transient JLabel LabelPseudo = new JLabel("Pseudo : ");
    private transient JLabel LabelPassword = new JLabel("Password : ");
    private transient JLabel LabelConfirmPassword = new JLabel("Confirm your password : ");
    private transient JLabel LabelMail = new JLabel("Mail : ");
    private transient JLabel LabelErreur = new JLabel();
    private transient JButton BoutonAccueil = new JButton("Accueil");
    private transient JButton BoutonMessagerie = new JButton("Messagerie");
    private transient JButton BoutonConnexion = new JButton("Se Connecter");

    /**
     * Affiche la page qui permet a l'utilisateur de s'inscrire
     * @param c
     */
    public PageInscription(Connexion c){
    	this.setName("pageInscription");
        this.c = c;

        //Définition taille des champs        
        int FieldWidth = LARGEUR_FENETRE/3;
        int FieldHeight;
        FontMetrics metrics = TextPseudo.getFontMetrics(getFont());
        FieldHeight = metrics.getHeight()+8;
        int FieldSpacing = FieldHeight/2;

        TextPseudo.setBounds((LARGEUR_FENETRE/2 - FieldWidth/2) , (HAUTEUR_FENETRE/2 - FieldHeight*2 - FieldSpacing - FieldSpacing/2) , FieldWidth, FieldHeight);
        TextPassword.setBounds((LARGEUR_FENETRE/2 - FieldWidth/2) , (HAUTEUR_FENETRE/2 - FieldHeight - FieldSpacing/2) , FieldWidth, FieldHeight);
        TextConfirmPassword.setBounds((LARGEUR_FENETRE/2 - FieldWidth/2) , (HAUTEUR_FENETRE/2 + FieldSpacing/2), FieldWidth , FieldHeight);
        TextMail.setBounds((LARGEUR_FENETRE/2 - FieldWidth/2) , (HAUTEUR_FENETRE/2 + FieldSpacing/2 + FieldHeight + FieldSpacing) , FieldWidth , FieldHeight);

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
        //Position + Taille ConfirmPassword
        metrics = LabelConfirmPassword.getFontMetrics(getFont());
        LabelWidth = metrics.stringWidth(LabelConfirmPassword.getText()+2);
        LabelHeight = metrics.getHeight()+5;
        LabelConfirmPassword.setBounds((LARGEUR_FENETRE/2 - FieldWidth/2 - LabelWidth - LabelSpacing) , (HAUTEUR_FENETRE/2 + FieldSpacing/2) , LabelWidth , LabelHeight);
        //Position + Taille Mail
        metrics = LabelMail.getFontMetrics(getFont());
        LabelWidth = metrics.stringWidth(LabelMail.getText()+2);
        LabelHeight = metrics.getHeight()+5;
        LabelMail.setBounds((LARGEUR_FENETRE/2 - FieldWidth/2 - LabelWidth - LabelSpacing) , (HAUTEUR_FENETRE/2 + FieldSpacing/2 + FieldHeight + FieldSpacing) , LabelWidth , LabelHeight);

        // Position + Taille Boutons
        Dimension boutonSizeDimension = BoutonValider.getPreferredSize();
        BoutonValider.setBounds((LARGEUR_FENETRE/2 - boutonSizeDimension.width/2) , (HAUTEUR_FENETRE/2 + 2*FieldSpacing + FieldSpacing/2 + 2*FieldHeight) , boutonSizeDimension.width , boutonSizeDimension.height);        
        BoutonAccueil.setBounds(20, 20, BoutonAccueil.getPreferredSize().width, BoutonAccueil.getPreferredSize().height);
        BoutonMessagerie.setBounds(BoutonAccueil.getX() , (BoutonAccueil.getY()+BoutonAccueil.getPreferredSize().height+5) , (BoutonMessagerie.getPreferredSize().width+1) , BoutonMessagerie.getPreferredSize().height);
        BoutonConnexion.setBounds((LARGEUR_FENETRE - BoutonConnexion.getPreferredSize().width - 20) , BoutonAccueil.getY() , (BoutonConnexion.getPreferredSize().width+1) , BoutonConnexion.getPreferredSize().height);


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
            	String ConfirmPasswordToString = new String(TextConfirmPassword.getPassword());
            	
            	if (TextPseudo.getText().trim().isEmpty() && PasswordToString.trim().isEmpty() && ConfirmPasswordToString.trim().isEmpty() && TextMail.getText().trim().isEmpty()) {
            		LabelErreur.setText("You must complete all the fields");
            		LabelErreur.setBounds((LARGEUR_FENETRE/2 - (LabelErreur.getPreferredSize().width/2)), (BoutonValider.getY() + boutonSizeDimension.height + 10), LabelErreur.getPreferredSize().width, LabelErreur.getPreferredSize().height);
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
	            	
            		else if (ConfirmPasswordToString.trim().isEmpty()) {
	            		LabelErreur.setText("You must confirm your password");
	            		LabelErreur.setBounds((TextConfirmPassword.getX() + FieldWidth + LabelSpacing) , TextConfirmPassword.getY() , LabelErreur.getPreferredSize().width , LabelErreur.getPreferredSize().height);
	            		LabelErreur.repaint();
	            	}
	            	
            		else if (TextMail.getText().trim().isEmpty()) {
	            		LabelErreur.setText("Mail field is empty");
	            		LabelErreur.setBounds((TextMail.getX() + FieldWidth + LabelSpacing) , (TextMail.getY()) , LabelErreur.getPreferredSize().width , LabelErreur.getPreferredSize().height);
	            		LabelErreur.repaint();
	            	}
	            	
            		else if (!Arrays.equals(TextPassword.getPassword(), TextConfirmPassword.getPassword())) {
	            		LabelErreur.setText("Passwords do not match");
	            		LabelErreur.setBounds((TextConfirmPassword.getX() + FieldWidth + LabelSpacing) , TextConfirmPassword.getY() , LabelErreur.getPreferredSize().width , LabelErreur.getPreferredSize().height);
	            		LabelErreur.repaint();
	            	}
            		
            		else {
            			Utilisateur new_user = new Utilisateur(69, TextPseudo.getText(), TextMail.getText(), 0, PasswordToString);
            			c.Inscription(new_user);
            		}
            	}
            }
        });
        
        
        this.setLayout(null);
        this.add(TextPseudo);
        this.add(TextPassword);
        this.add(TextConfirmPassword);
        this.add(TextMail);
        this.add(LabelPseudo);
        this.add(LabelPassword);
        this.add(LabelConfirmPassword);
        this.add(LabelMail);
        this.add(BoutonValider);
        this.add(LabelTitre);
        this.add(LabelErreur);
    }
}
