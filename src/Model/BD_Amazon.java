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
    
	public static Connection conn;
    
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

        // méthode d'initialisation de la BD
        
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
