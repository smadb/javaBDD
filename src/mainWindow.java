import com.SQLJava.Data.User;
import com.SQLJava.Data.UserManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class mainWindow extends JFrame{

    private JPanel jpMain;
    private JButton formUser;
    private JButton listUser;

    public mainWindow(){
        super();
        setTitle("Gestion Users");
        setContentPane(jpMain);
        setMinimumSize(new Dimension(600,600));
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        formUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                formUser formUser1 = new formUser(null);
            }
        });
        listUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserManager userManager = new UserManager();
                ArrayList<User> userArrayList = userManager.getAllUsers();

                listUsers listUsers = new listUsers(null);
            }
        });
    }
}
