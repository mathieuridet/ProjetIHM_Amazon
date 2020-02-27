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

/**
 *
 * @author mathieuridet
 */
public class BD_Amazon {
    
    Connection conn = null;
    String nom, url, username, pwd;
    
    public BD_Amazon(String nom, String url, String username, String pwd) {
        this.nom = nom;
        this.url = url;
        this.username = username;
        this.pwd = pwd;
        
        connexionToBd();
    }
    
    private void connexionToBd() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("Driver O.K.");

            conn = DriverManager.getConnection(url, username, pwd);
            System.out.println("Connexion effective !");         

        } catch (Exception e) {
            e.printStackTrace();
        }      
    }
    
    public void execute_select(String query) throws SQLException {
        // create the java statement
        Statement st = conn.createStatement();

        // execute the query, and get a java resultset
        ResultSet rs = st.executeQuery(query);

        // iterate through the java resultset
        while (rs.next())
        {
          //String nomAttr = rs.getString("nomColonne");
          
          // print the results
          //System.out.format("%s, %s, %s, %s, %s, %s\n", rs.getString("ENGINE"), support, comment, transactions, xa, savepoints);
        }
        st.close();
    }
    
    public void execute_insertOrUpdate(String query) throws SQLException {
        Statement st = conn.createStatement();
        st.executeUpdate(query);
        st.close();
    }
    
    
    public void close_connection() throws SQLException {
        conn.close();
    }
    
    
    
}
