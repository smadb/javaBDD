import com.SQLJava.Data.User;
import com.SQLJava.Data.UserManager;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.cert.CertPath;
import java.util.ArrayList;

public class listUsers extends JDialog {
    private JPanel jpMain;
    private ArrayList<String> columnsNames;
    public int rowNumber = 0;

    public ArrayList<User> UserList;


    public listUsers(JDialog parent){
        super(parent);

        this.UserList = getAllUsers();

        setTitle("Liste Users");
        setMinimumSize(new Dimension(800,500));


        JPanel gui = new JPanel(new BorderLayout(5,5));
        gui.setBorder(new EmptyBorder(10,10,10,10));
        gui.setBackground(Color.white);

        Color color1 = new Color(223,225,229);
        Color color2 = new Color(203,205,209);

        UserList.forEach(user -> {
            rowNumber++;
            if(rowNumber%2==0){
                gui.add(createUserCard(user,color1));
            }
            else {
                gui.add(createUserCard(user,color2));
            }

        });

        gui.setLayout(new GridLayout(rowNumber,1));
        JScrollPane scrollPane = new JScrollPane(gui);


        jpMain.add(scrollPane);
        setContentPane(jpMain);
        setVisible(true);

    }

    private void addActionDelete(JButton button, User user){

        button.setText("Delete");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int res = JOptionPane.showConfirmDialog(null,"Suppression de l'utilisateur " + user.getNom() + " " + user.getPrenom(),"Delete Utilisateur",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null);

                if(res==JOptionPane.YES_OPTION){
                    UserManager userManager = new UserManager();
                    UserManager.deleteUser(user);
                    dispose();
                    listUsers listUsers = new listUsers(null);
                }


            }
        });
    }

    private void addActionUpdate(JButton button , User user){
        button.setText("Update");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formUser formUser = new formUser(null,user);
                dispose();
            }
        });
    }

    private void addHoverEffect(JPanel panel,JPanel panel2,Color baseColor){
        Color hoverColor = Color.white;

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                panel.setBackground(hoverColor);
                panel2.setBackground(hoverColor);

            }
        });

        panel2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                panel.setBackground(hoverColor);
                panel2.setBackground(hoverColor);

            }
        });

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                panel.setBackground(baseColor);
                panel2.setBackground(baseColor);

            }
        });
        panel2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                panel.setBackground(baseColor);
                panel2.setBackground(baseColor);
            }
        });
    }

    private ArrayList<User> getAllUsers(){
        UserManager userManager = new UserManager();
        return userManager.getAllUsers();
    }

    public JPanel createUserCard(User user,Color color){

        JPanel cardUser = new JPanel(new GridLayout(1,2));
        cardUser.setBorder(new LineBorder(Color.white,5));
        cardUser.setBackground(Color.BLACK);
        cardUser.setForeground(Color.white);
        JPanel infosPanel = new JPanel(new GridLayout(1,3));
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        //TODO LAYOUTS
        GridBagConstraints gbc = new GridBagConstraints();

        //CREATION INFO USER
        JLabel prenom = new JLabel();
        JLabel nom = new JLabel();
        JLabel mail = new JLabel();

        prenom.setText(user.getPrenom());
        nom.setText(user.getNom());
        mail.setText(user.getEmail());

        infosPanel.add(prenom);
        infosPanel.add(nom);
        infosPanel.add(mail);

        infosPanel.setBackground(color);

        //CREATION BOUTONS
        JButton deleteButton = new JButton();
        deleteButton.setBackground(Color.red);
        deleteButton.setForeground(Color.white);
        addActionDelete(deleteButton,user);
        buttonPanel.add(deleteButton,gbc);

        JButton updateButton = new JButton();
        updateButton.setBackground(Color.darkGray);
        updateButton.setForeground(Color.white);
        addActionUpdate(updateButton,user);
        buttonPanel.add(updateButton,gbc);

        buttonPanel.setBackground(color);


        //ADD ELEMENTS

        infosPanel.setMaximumSize(new Dimension(1000,100));
        buttonPanel.setMaximumSize(new Dimension(1000,100));

        addHoverEffect(infosPanel,buttonPanel,color);

        cardUser.add(infosPanel);
        cardUser.add(buttonPanel);

        cardUser.setMaximumSize(new Dimension(500,100));
        cardUser.setMaximumSize(new Dimension(0,50));

        return cardUser;
    }


    public JPanel getJpMain() {
        return jpMain;
    }

    public ArrayList<String> getColumnsNames() {
        return columnsNames;
    }

    public void setColumnsNames(ArrayList<String> columnsNames) {
        this.columnsNames = columnsNames;
    }

}


