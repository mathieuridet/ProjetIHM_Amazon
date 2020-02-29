/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mathieuridet
 */
public class BD_Amazon {
    
    static Connection conn;
    
    public static void connexionToBd(String nom, String url, String username, String pwd) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(url, username, pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }      
    }
    
    //--------------------------------------------------------------------------
    // Remplissage de la BD
    public static void execute_insertOrUpdate(String query) throws SQLException {
        Statement st = conn.createStatement();
        st.executeUpdate(query);
        st.close();
    }
    
    public static void fillDB() throws SQLException {
        List<String> elements = new ArrayList<>();

        // Table Commande
        String cmd1 = "INSERT INTO Commande VALUES(1);";
        String cmd2 = "INSERT INTO Commande VALUES(2);";
        elements.add(cmd1);
        elements.add(cmd2);

        // Table Produit
        String prod1 = "INSERT INTO Produit VALUES(1, 'Produit 1', 19.99, 'Description 1', 'NULL')";
        String prod2 = "INSERT INTO Produit VALUES(2, 'Produit 2', 99.99, 'Description 2', 'NULL')";
        String prod3 = "INSERT INTO Produit VALUES(3, 'Produit 3', 79.99, 'Description 3', 'NULL')";
        String prod4 = "INSERT INTO Produit VALUES(4, 'Produit 4', 119.99, 'Description 4', 'NULL')";
        String prod5 = "INSERT INTO Produit VALUES(5, 'Produit 5', 10.00, 'Description 5', 'NULL')";
        elements.add(prod1);
        elements.add(prod2);
        elements.add(prod3);
        elements.add(prod4);
        elements.add(prod5);
        
        // Table Achat
        String achat1 = "INSERT INTO Achat VALUES(1, 1, 1, 2)";
        String achat2 = "INSERT INTO Achat VALUES(2, 1, 5, 5)";
        String achat3 = "INSERT INTO Achat VALUES(3, 2, 1, 1)";
        String achat4 = "INSERT INTO Achat VALUES(4, 2, 4, 1)";
        String achat5 = "INSERT INTO Achat VALUES(5, 2, 3, 10)";
        elements.add(achat1);
        elements.add(achat2);
        elements.add(achat3);
        elements.add(achat4);
        elements.add(achat5);
        
        for(String el : elements) {
            execute_insertOrUpdate(el);
        }
        
    }
     
    //--------------------------------------------------------------------------
    
    /**
     * 
     * @param query
     * @throws SQLException 
     */
    public static ResultSet execute_select(String query) throws SQLException {
        // create the java statement
        Statement st = conn.createStatement();

        // execute the query, and get a java resultset
        ResultSet rs = st.executeQuery(query);
        return rs;
    }
    
    public static void close_connection() throws SQLException {
        conn.close();
    }
    
}
