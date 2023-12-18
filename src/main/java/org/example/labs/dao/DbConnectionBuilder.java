package org.example.labs.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnectionBuilder implements ConnectionBuilder{

    public DbConnectionBuilder(){
        try {
            //Class.forName(ConnectionProperty.getProperty("db.driver.class"));
            Class.forName("org.postgresql.Driver");
        }catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        /*String url =  ConnectionProperty.getProperty("db.url");
        String login = ConnectionProperty.getProperty("db.login");
        String password = ConnectionProperty.getProperty("db.password");*/
        String url =  "jdbc:postgresql://localhost:5432/addressDB";
        String login = "omegalul";
        String password = "newpassword";
        return DriverManager.getConnection(url, login, password);
    }
}
