package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbConnection {

    public static Connection Connect() throws SQLException {
        //Your database url string,ensure it is correct

            try {
                //Your database url string,ensure it is correct
                String url = "jdbc:mysql://localhost:3306/data_hosp";
                String user = "root";
                String password = "";

                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection(url, user, password);
                return conn;

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
            }

            return null;
        }

    }
