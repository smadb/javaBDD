package com.SQLJava.Data;

import java.sql.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class UserManager {
    private static Connection connexion;

    public UserManager(){
        connexion=DBConnexion.getConnexion();
    }

    public static User addUser(User user){
        User userAdd = new User();
        try{
            Statement stm = connexion.createStatement();
            String requete = "INSERT INTO users(nom,prenom,email,password) VALUE (?,?,?,?)";
                PreparedStatement preparedStatement = connexion.prepareStatement(requete);
                preparedStatement.setString(1,user.getNom());
                preparedStatement.setString(2,user.getPrenom());
                preparedStatement.setString(3,user.getEmail());
                preparedStatement.setString(4,user.getPassword());

                int addedRows =preparedStatement.executeUpdate();

                if(addedRows > 0){
                    userAdd.setNom(user.getNom());
                    userAdd.setPrenom(user.getPrenom());
                    userAdd.setEmail(user.getEmail());
                    userAdd.setPassword(user.getPassword());
                }

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return userAdd;
    }

    public User findUser(User user){
        User verif = new User();
        try {
            Statement stm = connexion.createStatement();
            //System.out.println("*Connection started on "+connexion.toString()+"*");
            String requete = "SELECT id,nom,prenom,email,password FROM users WHERE email=?";

            PreparedStatement preparedStatement = connexion.prepareStatement(requete);
            preparedStatement.setString(1,user.getEmail());
            ResultSet rs = preparedStatement.executeQuery();


            while(rs.next()){
                if(rs.getString(1)!=null){
                    verif.setId(rs.getInt("id"));
                    verif.setEmail(rs.getString("email"));
                    verif.setNom(rs.getString("nom"));
                    verif.setPrenom(rs.getString("prenom"));
                    verif.setPassword(rs.getString("password"));
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return verif;
    }

    public static User updateUser(User user){
        User updatedUser = new User();

        try{
            Statement stm = connexion.createStatement();
            String requete = "UPDATE users SET nom = ?, prenom = ? WHERE id = (SELECT id FROM users WHERE email = ?)";
            //String requete2 = "UPDATE users SET (email, nom, prenom, password) VALUE(?,?,?,?) WHERE id = ?";

            PreparedStatement prepareStatement = connexion.prepareStatement(requete);

            prepareStatement.setString(1,user.getNom());
            prepareStatement.setString(2,user.getPrenom());
            prepareStatement.setString(3,user.getEmail());

           prepareStatement.executeUpdate();

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return updatedUser;
    }

    public static void deleteUser(User user){

        try{
            Statement stm = connexion.createStatement();
            String requete = "DELETE FROM users WHERE id = (SELECT id FROM users WHERE email = ?)";

            PreparedStatement prepareStatement = connexion.prepareStatement(requete);
            prepareStatement.setString(1,user.getEmail());

            prepareStatement.executeUpdate();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<User> getAllUsers(){
        ArrayList<User> allUsers = new ArrayList<>();

        try{
            Statement stm = connexion.createStatement();
            String requete = "SELECT id,nom,prenom,email,password FROM users";

            PreparedStatement prepareStatement = connexion.prepareStatement(requete);
            ResultSet rs = prepareStatement.executeQuery();

            while (rs.next()){
                User newUser = new User();
                newUser.setEmail(rs.getString("email"));
                newUser.setId(rs.getInt("id"));
                newUser.setPassword(rs.getString("password"));
                newUser.setPrenom(rs.getString("prenom"));
                newUser.setNom(rs.getString("nom"));

                allUsers.add(newUser);

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return allUsers;
    }

    public void findUpdateCreateUser(User user1) throws SQLException {
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

    public void closeConnexion() throws SQLException {
        connexion.close();
        System.out.println("*Connection closed*");
    }

    public static int scanUserInput(){
        Scanner scanner = new Scanner(System.in);
        int nombre = scanner.nextInt();
        scanner.nextLine();
        return nombre;
    }
}
