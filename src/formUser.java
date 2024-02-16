import com.SQLJava.Data.User;
import com.SQLJava.Data.UserManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;


public class formUser extends JDialog {
    private JTextField tfNom;
    private JTextField tfPrenom;
    private JPanel jpMain;
    private JTextField tfMail;
    private JTextField tfPassword;
    private JLabel lbPrenom;
    private JLabel lbNom;
    private JLabel lbMail;
    private JLabel lbPassword;
    private JButton validerButton;


    public formUser(JDialog parent){
        super(parent);
        setTitle("Formulaire User");
        setContentPane(jpMain);
        setMinimumSize(new Dimension(300,500));
        setModal(false);
        setVisible(true);

        validerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User newUser = new User(tfNom.getText(),tfPrenom.getText(),tfMail.getText(),tfPassword.getText());
                UserManager userManager = new UserManager();
                User verifUser = userManager.findUser(newUser);

                if(verifUser.getNom()!=null){
                    int res = JOptionPane.showConfirmDialog(null,"Modification de l'utilisateur " + verifUser.getNom() + " " + verifUser.getPrenom(),"Update Utilisateur",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null);
                    if(res==JOptionPane.YES_OPTION){
                        UserManager.updateUser(newUser);
                    }
                }
                else {
                    int res = JOptionPane.showConfirmDialog(null,"Création de l'utilisateur " + newUser.getNom(),"Création Utilisateur",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null);
                    if(res==JOptionPane.YES_OPTION){
                        UserManager.addUser(newUser);
                    }
                }
            }
        });
    }

    public formUser(JDialog parent,User user){
        super(parent);
        setTitle("Formulaire User");
        setContentPane(jpMain);
        setMinimumSize(new Dimension(300,500));
        setModal(false);
        setVisible(true);


        tfNom.setText(user.getNom());
        tfPrenom.setText(user.getPrenom());
        tfMail.setText(user.getEmail());
        tfPassword.setText(user.getPassword());

        lbMail.setText("Mail (non Modifiable)");
        lbMail.setForeground(new Color(223,225,229));
        tfMail.disable();
        tfMail.setBackground(new Color(223,225,229));
        tfMail.setForeground(Color.red);


        validerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User newUser = new User(tfNom.getText(),tfPrenom.getText(),tfMail.getText(),tfPassword.getText());
                UserManager userManager = new UserManager();
                User verifUser = userManager.findUser(newUser);

                if(verifUser.getNom()!=null){
                    if(Objects.equals(newUser.getNom(), verifUser.getNom()) && Objects.equals(newUser.getPrenom(), verifUser.getPrenom()) && Objects.equals(newUser.getEmail(), verifUser.getEmail()) && Objects.equals(newUser.getPassword(), verifUser.getPassword())){
                        int res = JOptionPane.showConfirmDialog(null,"Aucun changement détecté","Aucun Changement", JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE,null);
                    }
                    else {
                        int res = JOptionPane.showConfirmDialog(null,"Modification de l'utilisateur " + verifUser.getNom() + " " + verifUser.getPrenom(),"Update Utilisateur",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null);
                        if(res==JOptionPane.YES_OPTION){
                            UserManager.updateUser(newUser);
                            dispose();
                            newWindow();
                        }
                    }
                }
                else {
                    int res = JOptionPane.showConfirmDialog(null,"Création de l'utilisateur " + newUser.getNom(),"Création Utilisateur",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null);
                    if(res==JOptionPane.YES_OPTION){
                        UserManager.addUser(newUser);
                    }
                }
            }
        });
    }

    public void newWindow(){
        listUsers listUsers = new listUsers(null);
    }
    public JTextField getTfNom() {
        return tfNom;
    }

    public void setTfNom(JTextField tfNom) {
        this.tfNom = tfNom;
    }

    public JTextField getTfPrenom() {
        return tfPrenom;
    }

    public void setTfPrenom(JTextField tfPrenom) {
        this.tfPrenom = tfPrenom;
    }

    public JPanel getJpMain() {
        return jpMain;
    }

    public void setJpMain(JPanel jpMain) {
        this.jpMain = jpMain;
    }

    public JTextField getTfMail() {
        return tfMail;
    }

    public void setTfMail(JTextField tfMail) {
        this.tfMail = tfMail;
    }

    public JTextField getTfPassword() {
        return tfPassword;
    }

    public void setTfPassword(JTextField tfPassword) {
        this.tfPassword = tfPassword;
    }

    public JLabel getLbPrenom() {
        return lbPrenom;
    }

    public void setLbPrenom(JLabel lbPrenom) {
        this.lbPrenom = lbPrenom;
    }

    public JLabel getLbNom() {
        return lbNom;
    }

    public void setLbNom(JLabel lbNom) {
        this.lbNom = lbNom;
    }

    public JLabel getLbMail() {
        return lbMail;
    }

    public void setLbMail(JLabel lbMail) {
        this.lbMail = lbMail;
    }

    public JLabel getLbPassword() {
        return lbPassword;
    }

    public void setLbPassword(JLabel lbPassword) {
        this.lbPassword = lbPassword;
    }

    public JButton getValiderButton() {
        return validerButton;
    }

    public void setValiderButton(JButton validerButton) {
        this.validerButton = validerButton;
    }
}
