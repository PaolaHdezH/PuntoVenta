
package modelo;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.table.DefaultTableModel;

public class ProveedorDAO {
    
    private Proveedor proveedor=null;
    private ConexionBD cBD ;

    public ProveedorDAO() {
        cBD = new ConexionBD();
    }

    public ProveedorDAO(String id,String nombre, String apellidoPaterno, String apellidoMaterno,
                    String direccion, String compania, String telefono,
                    String correoElectronico, String InstitucionBancaria, String clabe ){
        proveedor = new Proveedor(id, nombre, apellidoPaterno, apellidoMaterno,
                     direccion, compania, telefono,
                     correoElectronico, InstitucionBancaria, clabe);
        this.cBD =  new ConexionBD();
    }
    
    public ProveedorDAO(String nombre, String apellidoPaterno, String apellidoMaterno,
                    String direccion, String compania, String telefono,
                    String correoElectronico, String InstitucionBancaria, String clabe) {
   
        proveedor = new Proveedor( nombre, apellidoPaterno, apellidoMaterno,
                     direccion, compania, telefono,
                     correoElectronico, InstitucionBancaria, clabe);
        cBD = new ConexionBD();
        
    }
    
    public int modificar () {
        String query = "UPDATE `proveedor`"
                + " SET"
                + " nombre = '"+this.proveedor.getNombre()+"',"
                + " telefono = "+this.proveedor.getTelefono()+","
                + " direccion = '"+this.proveedor.getDireccion()+"',"
                + " apellido_paterno = '"+this.proveedor.getApellidoPaterno()+"',"
                + " apellido_materno = '"+this.proveedor.getApellidoMaterno()+"',"
                + " compania = '"+this.proveedor.getCompania()+"',"
                + " correo_electronico='"+this.proveedor.getCorreoElectronico()+"',"
                + " institucion_bancaria='"+this.proveedor.getInstitucionBancaria()+"',"
                + " clabe = "+this.proveedor.getClabe()+""
                + " WHERE id_proveedor = "+proveedor.getId()+";";
        try {
            cBD.conectarBase();
            cBD.insertar(query);
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public int eliminar (){
        String query = "DELETE FROM proveedor "
                + "WHERE proveedor.ID_PROVEEDOR = '"+this.proveedor.getId()+"'";
         cBD.conectarBase();
        try {
            return cBD.modificar(query);
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    
    public int registro (){
        String query = "INSERT INTO `proveedor`"
                + " (`ID_PROVEEDOR`, `nombre`,"
                + " `telefono`, `direccion`,"
                + " `apellido_paterno`, `apellido_materno`,"
                + " `compania`, `correo_electronico`,"
                + " `institucion_bancaria`, `clabe`)"
                + " VALUES "
                + "( NULL, '"+this.proveedor.getNombre()+"',"
                + " '"+this.proveedor.getTelefono()+"', '"+this.proveedor.getDireccion()+"',"
                + " '"+this.proveedor.getApellidoPaterno()+"', '"+this.proveedor.getApellidoMaterno()+"',"
                + " '"+this.proveedor.getCompania()+"', '"+this.proveedor.getCorreoElectronico()+"',"
                + " '"+this.proveedor.getInstitucionBancaria()+"', '"+this.proveedor.getClabe()+"');";
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
        
        ObservableList <Proveedor> listaProveedorDatos = FXCollections.observableArrayList();
        
        String query =null;
        
        if (tipoConsulta.equals("GENERAL")){
            query ="SELECT ID_PROVEEDOR , NOMBRE ,apellido_paterno,apellido_materno,COMPANIA FROM proveedor";
        } else if (tipoConsulta.equals("DATOS")){
            query = "SELECT * FROM proveedor WHERE ID_PROVEEDOR = '"+referencia+"'; ";
        }else{
            query ="SELECT ID_PROVEEDOR,nombre,apellido_paterno,apellido_materno,"
                    + "compania FROM `proveedor` WHERE ID_PROVEEDOR LIKE '%"+referencia+"%' \n" +
                        "UNION ( SELECT ID_PROVEEDOR,nombre,apellido_paterno,"
                            + "apellido_materno,compania FROM `proveedor` WHERE nombre LIKE '%"+referencia+"%'\n" +
                        "UNION ( SELECT ID_PROVEEDOR,nombre,apellido_paterno,"
                            + "apellido_materno,compania FROM `proveedor` WHERE apellido_paterno LIKE '%"+referencia+"%'\n" +
                        "UNION ( SELECT ID_PROVEEDOR,nombre,apellido_paterno,"
                            + "apellido_materno,compania FROM `proveedor` WHERE apellido_materno LIKE '%"+referencia+"%'\n" +
                        "UNION ( SELECT ID_PROVEEDOR,nombre,apellido_paterno,"
                            + "apellido_materno,compania FROM `proveedor` WHERE compania LIKE '%"+referencia+"%')\n" +
                        "))) ORDER BY ID_PROVEEDOR;";
        }
        this.cBD.conectarBase();
        
        try {
            cBD.consulta(query);
            
            while (cBD.getRs().next()){
                if(tipoConsulta.equals("DATOS")){
                    listaProveedorDatos.add(
                        new Proveedor(
                                cBD.getRs().getString("id_proveedor"),
                                cBD.getRs().getString("nombre"),                                        
                                cBD.getRs().getString("apellido_Paterno"),
                                cBD.getRs().getString("apellido_Materno"),
                                cBD.getRs().getString("direccion"),
                                cBD.getRs().getString("compania"),
                                cBD.getRs().getString("telefono"),
                                cBD.getRs().getString("correo_electronico"),
                                cBD.getRs().getString("institucion_bancaria"),
                                cBD.getRs().getString("clabe")
                        )
                    );
                }else{
                    listaProveedorDatos.add(
                        new Proveedor(
                                cBD.getRs().getString("id_proveedor"),
                                cBD.getRs().getString("nombre")+" "
                                        +cBD.getRs().getString("apellido_Paterno")+" "
                                                + cBD.getRs().getString("apellido_Materno"),
                                cBD.getRs().getString("compania")
                        )
                    );
                }
                
            }
            
            cBD.getRs().close();
            
            return listaProveedorDatos;
            
        } catch (SQLException ex) {
            Logger.getLogger(ProveedorDAO.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
        
        return null;
    }
    
    
    
}
