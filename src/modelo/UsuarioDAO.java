/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author pao-o
 */
public class UsuarioDAO {
    
    private Usuario usuario=null;
    private ConexionBD cBD ;

    public UsuarioDAO() {
        cBD = new ConexionBD();
    }
    

    
    
    public String consulta (String usuarioDigitado,String paswwordDigitado){
        
        usuario = new Usuario();
        
        System.out.println(usuario.evularUsuario(usuarioDigitado));
        System.out.println(usuario.evularPassword(paswwordDigitado));
        
        if (usuario.evularUsuario(usuarioDigitado) && usuario.evularPassword(paswwordDigitado)){
            System.out.println("entre en validacion");
            String consultaBD =null;
            usuario.setInformacion(usuarioDigitado, paswwordDigitado);
            consultaBD="SELECT  usuario.usuario, usuario.password ,empleado.puesto\n" +
                                "from empleado ,usuario\n" +
                                "WHERE empleado.FK_USUARIO =usuario.ID_USUARIO \n" +
                                "AND usuario.usuario = '"+usuarioDigitado+"'\n" +
                                "AND usuario.password = '"+paswwordDigitado+"'\n" +
                                ";";
            String puesto="";
            
            try {
                cBD.conectarBase();
                cBD.consulta(consultaBD);
                
                while(cBD.getRs().next()){
                    
                    puesto = cBD.getRs().getString("puesto");
                    
                }
                return puesto;
                
            } catch (SQLException ex) {
                Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }else{
           
        }
        
        return "CampoVacio"; 
    }
}
