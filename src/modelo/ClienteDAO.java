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


public class ClienteDAO {
    
    private Cliente cliente=null;
    private ConexionBD cBD ;

    public ClienteDAO() {
        cBD = new ConexionBD();
    }
    

    public ClienteDAO(String id,String nombre,
                    String rfc,String correoElectronico){
        cliente = new Cliente(id, nombre, correoElectronico);
        this.cBD =  new ConexionBD();
    }
    
    public ClienteDAO(String nombre,
                    String rfc, String correoElectronico) {
   
        cliente = new Cliente( nombre, rfc,correoElectronico);
        cBD = new ConexionBD();
        
    }
    
    public int modificar () {
        String query = "UPDATE cliente"
                + " SET "
                + " nombre = '"+this.cliente.getNombre()+"',"
                + " rfc = '"+this.cliente.getRfc()+"',"
                + " correoElectronico='"+this.cliente.getCorreoElectronico()+"'"
                + " WHERE id_cliente = "+cliente.getNombre()+";";
        try {
            cBD.conectarBase();
            cBD.insertar(query);
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public int eliminar (){
        String query = "DELETE FROM cliente "
                + "WHERE cliente.ID_CLIENTE = '"+this.cliente.getId()+"'";
         cBD.conectarBase();
        try {
            return cBD.modificar(query);
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    
    public int registro (){
        String query = "INSERT INTO `cliente`"
                + " (`ID_CLIENTE`, `nombre`,"
                + " `correoElectronico`, `rfc`,"
                + " VALUES "
                + "( NULL, '"+this.cliente.getNombre()+"',"
                + " '"+this.cliente.getCorreoElectronico()+"', '"+this.cliente.getRfc()+"');";
        cBD.conectarBase();
        try {
            cBD.insertar(query);
            return 1;
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
        
    }
    
    public ObservableList consulta (String tipoConsulta,String referencia){
        
        ObservableList <Cliente> listaClienteDatos = FXCollections.observableArrayList();
        
        String query =null;
        
        if (tipoConsulta.equals("GENERAL")){
            query ="SELECT * FROM cliente";
        } else if (tipoConsulta.equals("DATOS")){
            query = "SELECT * FROM cliente WHERE ID_CLIENTE = '"+referencia+"'; ";
        }
        this.cBD.conectarBase();
        
        try {
            cBD.consulta(query);
            
            while (cBD.getRs().next()){
                if(tipoConsulta.equals("DATOS")){
                    listaClienteDatos.add(
                        new Cliente(
                                cBD.getRs().getString("id_cliente"),
                                cBD.getRs().getString("nombre"),
                                cBD.getRs().getString("rfc"),
                                cBD.getRs().getString("correoElectronico")
                        ));
                }else{
                    listaClienteDatos.add(
                        new Cliente(
                                cBD.getRs().getString("id_cliente"),
                                cBD.getRs().getString("nombre"),
                                cBD.getRs().getString("rfc"),
                                cBD.getRs().getString("correoElectronico")
                        ));
                }
                
            }
            
            cBD.getRs().close();
            
            return listaClienteDatos;
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        
        return null;
    }
}
