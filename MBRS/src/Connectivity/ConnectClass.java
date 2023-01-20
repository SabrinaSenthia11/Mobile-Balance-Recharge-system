package Connectivity;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author HP
 */
public class ConnectClass {

    public static Connection connect;

    public Connection connectDb() {
        try {
            final String url = "jdbc:mysql://localhost/MBRSDB";
            final String user = "root";
            final String pass = "";
            connect = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            System.out.println("Error occurred while trying to connect to the database");
        }
        return connect;
    }
}
