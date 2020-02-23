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
    
    static Connection conn = null;
    
    public static void connectToDB() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("Driver O.K.");

            String nomBD = "nomBD";
            String url = "jdbc:mariadb://localhost:5432/Ecole";
            String user = "postgres";
            String mdp = "postgres";

            conn = DriverManager.getConnection(url, user, mdp);
            System.out.println("Connexion effective !");         

        } catch (Exception e) {
            e.printStackTrace();
        }      
    }
    
    public static void execute_select(String query) throws SQLException {
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
    
    public static void execute_insertOrUpdate(String query) throws SQLException {
        Statement st = conn.createStatement();
        st.executeUpdate(query);
        st.close();
    }
    
    
    public static void close_connection() throws SQLException {
        conn.close();
    }
    
    
    
}
