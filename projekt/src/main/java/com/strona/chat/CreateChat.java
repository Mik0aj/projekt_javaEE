package com.strona.chat;

import com.changePassword.GenerateCode;
import com.database.DatabaseConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/CreateChat")
public class CreateChat extends HttpServlet {
    private DatabaseConnection databaseConnection = new DatabaseConnection();
    private String enterChatCode;
    private String chatID;
    private String userID;

    public String generateCode(){
        Connection connection = databaseConnection.getConnection();
        GenerateCode generateCode = new GenerateCode();
        String chatCode="0000000000";
        boolean isOK = false;
        while(!isOK){
            chatCode = generateCode.generateChatCode();
            String checkIfCodeExistQuery = "SELECT * FROM chats WHERE enter_code='"+chatCode+"';";
            try {
                Statement statement = connection.createStatement();
                ResultSet queryResult = statement.executeQuery(checkIfCodeExistQuery);
                if (queryResult.next()) {
                    isOK=false;
                }
                else{
                    return chatCode;
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
        databaseConnection.closeConnection();
        return chatCode;
    }

    public void createNewChat(String chatName){
        Connection connection = databaseConnection.getConnection();

        enterChatCode = generateCode();
        String addNewChatIntoChatsTableQuery = "INSERT INTO chats (enter_code, name) VALUES ('"+enterChatCode+"', '"+chatName+"');";
        try {
            Statement statement = connection.createStatement();
            statement.execute(addNewChatIntoChatsTableQuery);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        databaseConnection.closeConnection();
    }

    public void createChatTables(String enterChatCode){
        Connection connection = databaseConnection.getConnection();

        String getChatIDQuery = "SELECT chat_id FROM chats WHERE enter_code='"+enterChatCode+"';";
        try {
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(getChatIDQuery);
            if(queryResult.next()){
                chatID=queryResult.getString("chat_id");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        String addChatUsersTableQuery = "CREATE TABLE chat"+chatID+"_users (user_id int(20), nick varchar(20), is_owner tinyint(1), FOREIGN KEY (user_id) REFERENCES users(user_id));";
        try {
            Statement statement = connection.createStatement();
            statement.execute(addChatUsersTableQuery);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        String addChatMessagesTableQuery = "CREATE TABLE chat"+chatID+"_messages (message_id int(20), user_id int(20), send_time datetime, contents text, FOREIGN KEY (user_id) REFERENCES users(user_id));";
        try {
            Statement statement = connection.createStatement();
            statement.execute(addChatMessagesTableQuery);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        databaseConnection.closeConnection();
    }

    public void addRowInUserTable(String userLogin){
        Connection connection = databaseConnection.getConnection();

        String getUserIDQuery = "SELECT user_id FROM users WHERE login='"+userLogin+"'";
        try {
            Statement statement = connection.createStatement();
            ResultSet queryResult = statement.executeQuery(getUserIDQuery);
            if(queryResult.next()){
                userID=queryResult.getString("user_id");
                String addRowInUserTableQuery = "INSERT INTO user"+userID+"_chats (chat_id, is_owner, nick) VALUES ('"+chatID+"', 1, '"+userLogin+"');";
                try {
                    statement = connection.createStatement();
                    statement.execute(addRowInUserTableQuery);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        databaseConnection.closeConnection();
    }

    public void addRowInChatTable(String userLogin){
        Connection connection = databaseConnection.getConnection();

        String addRowInChatTableQuery = "INSERT INTO chat"+chatID+"_users (user_id, nick, is_owner) VALUES ('"+userID+"', '"+userLogin+"', 1);";
        try {
            Statement statement = connection.createStatement();
            statement.execute(addRowInChatTableQuery);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        databaseConnection.closeConnection();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String userLogin = request.getParameter("userLogin");
        String chatName = request.getParameter("chatName");

        if(chatName.length()<3 || chatName.length()>20){
            session.setAttribute("chatNameError", "Nazwa chatu musi mieć pomiędzy 3 a 20 liter.");
            session.setAttribute("login", userLogin);
            response.sendRedirect("createChat.jsp");
        }
        else{
            createNewChat(chatName);
            createChatTables(enterChatCode);
            addRowInUserTable(userLogin);
            addRowInChatTable(userLogin);
            session.setAttribute("login", userLogin);
            session.setAttribute("enterCode", enterChatCode);
            response.sendRedirect("chatCreated.jsp");
        }
    }
}
