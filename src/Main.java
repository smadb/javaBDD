import com.SQLJava.Data.DBConnexion;
import com.SQLJava.Data.User;
import com.SQLJava.Data.UserManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        connexion connexion = new connexion(null);
    }











    public void gestionUser(){
        DBConnexion.getConnexion();

        User newUser = new User("Clément","Renault","monmail","monpass");
        User user1 = new User("Clémentmofidii","Renault","monmail","monpass");
        User user2 = new User("Michel","BBBBBBBB","unautremail","password");

        //findUpdateCreateUser(user1);
        UserManager userManager = new UserManager();
        ArrayList<User> allUsers = userManager.getAllUsers();
        allUsers.forEach(user -> System.out.println(
                user.getPrenom() +" " + user.getNom() + " " + user.getEmail()
        ));
        System.out.println(allUsers);
    }

    public static int scanUserInput(){
        Scanner scanner = new Scanner(System.in);
        int nombre = scanner.nextInt();
        scanner.nextLine();
        return nombre;
    }

    public static void findUpdateCreateUser(User user1) throws SQLException {
        UserManager userManager = new UserManager();

        //userManager.addUser(newUser);
        if(userManager.findUser(user1).getNom()!=null){
            System.out.println("Compte existe deja");
            if(!Objects.equals(user1.getEmail(), userManager.findUser(user1).getEmail()) || !Objects.equals(user1.getNom(), userManager.findUser(user1).getNom()) || !Objects.equals(user1.getPrenom(), userManager.findUser(user1).getPrenom()) || !Objects.equals(user1.getPassword(), userManager.findUser(user1).getPassword())){
                System.out.println("Voulez vous modifier l'utilisateur existant ? y:1/N:2");
                if(scanUserInput()==1){
                    userManager.updateUser(user1);
                    System.out.println("Compte modifié");
                }
                else{
                    System.out.println("Compte non modifié");
                }
            }
            else{
                System.out.println("Aucun changement");
            }

        } else {
            System.out.println("Compte n'existe pas. Voulez vous l'ajouter ? y:1/N:2");
            if(scanUserInput()==1){
                userManager.addUser(user1);
                System.out.println("Compte ajouté");

            }
            else{

            }

        }
    }

}
