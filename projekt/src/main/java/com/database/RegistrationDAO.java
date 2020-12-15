package com.database;

import com.changePassword.GenerateCode;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RegistrationDAO {
    DatabaseConnection databaseConnection = new DatabaseConnection();
    PBKDF2_Algorithm hashAlgorithm = new PBKDF2_Algorithm();
    GenerateCode generateCode = new GenerateCode();

    public boolean isLoginNotAvailable(String login) {
        Connection connection = databaseConnection.getConnection();
        String verifyLoginQuery = "SELECT * FROM `users` WHERE login='"+login+"'";
        try{
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLoginQuery);
            if(queryResult.next()){
                databaseConnection.closeConnection();
                return true;
            }
            else{
                databaseConnection.closeConnection();
                return false;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean isEmailNotAvailable(String email) {
        Connection connection = databaseConnection.getConnection();
        String verifyEmailQuery = "SELECT * FROM `users` WHERE email='"+email+"'";
        try{
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyEmailQuery);
            if(queryResult.next()){
                databaseConnection.closeConnection();
                return true;
            }
            else{
                databaseConnection.closeConnection();
                return false;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public void addUser(String login, String password, String email){
        addUserToDatabase(login, password, email);
        addVeryfingCodeValue(login);
    }

    public void addUserToDatabase(String login, String password, String email){
        String hashedPassword="";
        try {
            hashedPassword = hashAlgorithm.hashPassword(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        Connection connection = databaseConnection.getConnection();
        String addUserQuery = "INSERT INTO `users` (`login`, `password`, `email`) VALUES ('"+login+"', '"+hashedPassword+"', '"+email+"')";
        try {
            Statement statement = connection.createStatement();
            statement.execute(addUserQuery);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        databaseConnection.closeConnection();
    }

    public void addVeryfingCodeValue(String login){
        Connection connection = databaseConnection.getConnection();
        boolean isOK = false;
        while(!isOK){
            String code = generateCode.generateResetCode();
            String addVeryfingCodeValueQuery = "INSERT INTO user_verifying_code (code, user_id) SELECT '"+code+"', user_id FROM users WHERE login='"+login+"'";
            try {
                Statement statement = connection.createStatement();
                statement.execute(addVeryfingCodeValueQuery);
                isOK=true;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                isOK=false;
            }
        }
        databaseConnection.closeConnection();
    }
}