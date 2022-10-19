
package modelo;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class ConexionBD {
    
    private Connection cn;
    private Statement stmt;
    private ResultSet rs;

   
    
    public void conectarBase(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            cn=DriverManager.getConnection("jdbc:mysql://localhost/puntodeventa","root","");
            stmt=cn.createStatement();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error de base de datos 1: \n"+ ex);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error de base de datos 2:"+ e);
        }
    }
    protected void consulta(String query) throws SQLException{
        rs = stmt.executeQuery( query );
        
    }
    
    public int modificar (String query) throws SQLException {
        int modificacionExitosa;
        PreparedStatement pstmt;
        pstmt = cn.prepareStatement(query);
        modificacionExitosa=pstmt.executeUpdate();
        return modificacionExitosa;
    }
    
    protected int insertar (String query) throws SQLException{
        int exito;
        exito=stmt.executeUpdate(query);
        return exito;
        
    }
    
    protected ResultSet getRs() {
        return rs;
    }
    
}
