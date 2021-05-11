package compteami;

import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import org.json.JSONObject;


public class PageInscription extends JPanel implements Config{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private transient Connexion c;
    private transient JTextField TextNom;
    private transient JTextField TextPrenom;
    private transient JPasswordField TextPassword;
    private transient JTextField TextConfirmPassword;
    private transient JTextField TextMail;
    private transient JButton BoutonValider;

    public PageInscription(Connexion c){
        this.c = c;
        TextNom = new JTextField(50);
        TextPrenom = new JTextField(50);
        TextMail = new JTextField(50);
        TextPassword = new JPasswordField(50);
        TextConfirmPassword = new JTextField(50);

        TextNom.setBounds(LARGEUR_FENETRE/3, HAUTEUR_FENETRE/3, LARGEUR_FENETRE/2, HAUTEUR_FENETRE/2);
        TextPrenom.setBounds(LARGEUR_FENETRE/3, HAUTEUR_FENETRE/3 + 100, LARGEUR_FENETRE/2, HAUTEUR_FENETRE/2 + 100);
        TextPassword.setBounds(LARGEUR_FENETRE/3, HAUTEUR_FENETRE/3 + 200, LARGEUR_FENETRE/2, HAUTEUR_FENETRE/2 + 200);
        TextConfirmPassword.setBounds(LARGEUR_FENETRE/3, HAUTEUR_FENETRE/3 + 300, LARGEUR_FENETRE/2, HAUTEUR_FENETRE/2 + 300);
        TextMail.setBounds(LARGEUR_FENETRE/3, HAUTEUR_FENETRE/3 + 400, LARGEUR_FENETRE/2, HAUTEUR_FENETRE/2 + 400);
        
        TextNom.setHorizontalAlignment(JTextField.CENTER);
        TextPrenom.setHorizontalAlignment(JTextField.CENTER);
        TextPassword.setHorizontalAlignment(JTextField.CENTER);
        TextConfirmPassword.setHorizontalAlignment(JTextField.CENTER);
        TextMail.setHorizontalAlignment(JTextField.CENTER);

        TextPassword.setEchoChar('*');

        this.add(TextNom);
        this.add(TextPrenom);
        this.add(TextPassword);
        this.add(TextConfirmPassword);
        this.add(TextMail);

        BoutonValider = new JButton("Valider");

        BoutonValider.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                try(Socket sc = new Socket("localhost", 8001)){
                 
                    System.out.println("SOCKET = " + sc);
                    InputStream is = sc.getInputStream();
                    OutputStream os = sc.getOutputStream();
                    PrintWriter writer = new PrintWriter(os);
                    JSONObject new_user = new JSONObject();
                    new_user.put("requete", "inscription");
                    new_user.put("pseudo", TextNom.getText());
                    new_user.put("email", TextMail.getText());
                    new_user.put("password", TextPassword.getText());
                    
                    writer.write(new_user.toString());
                    System.out.println(new_user.toString());
                    writer.flush();
                    System.out.println("On est là");
                    sc.close();
                    
                } catch (UnknownHostException e1) {
                    e1.printStackTrace();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        });
        this.add(BoutonValider);
    }

}
