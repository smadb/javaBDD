import com.SQLJava.Data.User;
import com.SQLJava.Data.UserManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class connexion extends JFrame {
    private JPanel jpMain;
    private JTextField mail;
    private JPasswordField password;

    public connexion(JDialog parent){
        super();
        setTitle("Connexion");
        setMinimumSize(new Dimension(600,600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel gui = new JPanel(new BorderLayout(200,200));
        gui.setBorder(new EmptyBorder(100,100,100,100));
        gui.setBackground(Color.white);

        JPanel container = new JPanel();
        container.setBorder(new EmptyBorder(20,20,20,20));
        container.setLayout(new GridLayout(6,1));

        JLabel mailLabel = new JLabel("Mail");
        JLabel passwordLabel = new JLabel("Password");

        JTextField tfMail = new JTextField();
        tfMail.setBorder(new LineBorder(Color.darkGray,10));
        tfMail.setHorizontalAlignment(JTextField.CENTER);
        tfMail.setToolTipText("Email");
        mail = tfMail;

        JPasswordField tfPassword = new JPasswordField();
        tfPassword.setBorder(new LineBorder(Color.darkGray,10));
        tfPassword.setHorizontalAlignment(JPasswordField.CENTER);
        tfPassword.setToolTipText("Password");
        password = tfPassword;

        JButton validerButton = new JButton("Valider");
        validerButton.setSize(new Dimension(100,50));
        validerButton.setBorder(new LineBorder(Color.darkGray,2));
        addValidateAction(validerButton);

        container.add(mailLabel);
        container.add(tfMail);
        container.add(passwordLabel);
        container.add(tfPassword);
        container.add(new JPanel());
        container.add(validerButton);

        gui.add(container);
        jpMain.add(gui);

        setContentPane(jpMain);
        setVisible(true);



    }

    public JPanel getJpMain() {
        return jpMain;
    }

    public void setJpMain(JPanel jpMain) {
        this.jpMain = jpMain;

    }

    public boolean isConnected(String mail,String password){
        UserManager userManager = new UserManager();
        User verifUser = userManager.findUser(mail);
        return Objects.equals(mail, verifUser.getEmail()) && Objects.equals(password, verifUser.getPassword());
    }

    public void addValidateAction(JButton button){
        button.addActionListener(e -> {
            if(isConnected(mail.getText(),password.getText())){
                mainWindow mainWindow = new mainWindow();
                dispose();
            }
            else{
                JOptionPane.showInternalMessageDialog(null,"Identifiant non valides");
            }
        });
    }

    public JTextField getMail() {
        return mail;
    }

    public void setMail(JTextField mail) {
        this.mail = mail;
    }

    public JPasswordField getPassword() {
        return password;
    }

    public void setPassword(JPasswordField password) {
        this.password = password;
    }
}
