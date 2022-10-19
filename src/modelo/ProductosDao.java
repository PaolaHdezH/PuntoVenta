package modelo;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;




/**
 *
 * @author Sergio
 */
public class ProductosDao {
    
    
    private Productos productos=null;
    private ConexionBD cBD ;

    public ProductosDao() {
        cBD = new ConexionBD();
    }
    
    public ProductosDao(String CodigoBarras, String Nombre, double Precio,int Stock,
                    int FK_PROVEEDOR) {
   
        productos = new Productos(Nombre,CodigoBarras, Precio,
                     Stock, FK_PROVEEDOR);
        cBD = new ConexionBD();
        
    }
    
    public int modificar () {
        String query = "UPDATE `producto`"
                + " SET"
                + " codigoBarras = '"+this.productos.getCodigoBarras()+"',"
                + " nombre = "+this.productos.getNombre()+","
                + " precio = '"+this.productos.getPrecio()+"',"
                + " stock = '"+this.productos.getStock()+"',"
                + " FK_PROVEEDOR = '"+this.productos.getFK_PROVEEDOR()+"',";
        try {
            cBD.conectarBase();
            cBD.modificar(query);
        } catch (SQLException ex) {
            Logger.getLogger(ProductosDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    
    public int eliminar (){
        String query = "DELETE FROM producto "
                + "WHERE producto.nombre = '"+this.productos.getNombre()+"'"
                + "AND producto.codigoBarras = '"+this.productos.getCodigoBarras()+"'";
         cBD.conectarBase();
        try {
            return cBD.modificar(query);
        } catch (SQLException ex) {
            Logger.getLogger(ProductosDao.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    public int Buscar (){
        String query = "SELECT nombre,precio,stock,FK_PROVEEDOR FROM producto "
                + "WHERE producto.codigoBarras = '"+this.productos.getCodigoBarras()+"'";
             //   + "AND producto.FK_PROVEEDOR = '"+this.productos.getFK_PROVEEDOR()+"'";
         cBD.conectarBase();
        try {
            cBD.consulta(query);
            return 1;
        } catch (SQLException ex) {
        //    JOptionPane.showMessageDialog(null,"Error  3 de modulo  baja a BD\n"+ ex);
            return 0;
        }

    }
    public int nuevo (){
        String query = "INSERT INTO `producto`"
                + " (`codigoBarras`,"
                + " `nombre`, `precio`,"
                + " `stock`, `FK_PROVEEDOR`)"
                + " VALUES "
                + "("
                + " '"+this.productos.getCodigoBarras()+"', '"+this.productos.getNombre()+"',"
                + " "+this.productos.getPrecio()+", "+this.productos.getStock()+","
                + " "+this.productos.getFK_PROVEEDOR()+");";
        cBD.conectarBase();
        try {
            cBD.insertar(query);
            return 1;
        } catch (SQLException ex) {
            Logger.getLogger(ProductosDao.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
        
    }
    
    
    
}
    
