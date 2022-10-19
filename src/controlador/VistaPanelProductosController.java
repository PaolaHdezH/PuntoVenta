/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javax.swing.JOptionPane;
import modelo.ProductosDao;







/**
 * FXML Controller class
 *
 * @author Sergio
 */
public class VistaPanelProductosController implements Initializable {
    
    
    
public Connection cn;
    public Statement stmt;
    public ResultSet rs;




    public void conectarBase() {//inicia metodo conectar
        try {//inicia try
            //puente de conexion
            Class.forName("com.mysql.jdbc.Driver");//puente de conexion
            cn = DriverManager.getConnection("jdbc:mysql://localhost/puntoventa", "root", "");
            stmt = cn.createStatement();//genera sentencias sql
        } catch (SQLException ex) {//inicia exeption
            JOptionPane.showMessageDialog(null, "Error 1 de BD\n" + ex);
        } catch (Exception e) {//inica exception errores generales
            JOptionPane.showMessageDialog(null, "Error general de aplicacion \n" + e);

        }
    }//termina metodo conectar
    
    
    
    

    @FXML
    private Tab TabAgregar;
    @FXML
    private Label lblcofigo1;
    @FXML
    private Label lblnombre1;
    @FXML
    private Label lblProveedor1;
    @FXML
    private TextField txtCodigo1;
    @FXML
    private Label lblPrecio1;
    @FXML
    private Label lblExistencias1;
    @FXML
    private TextField txtNombre1;
    @FXML
    private TextField txtPrecio1;
    @FXML
    private TextField txtExistencias1;
    @FXML
    private TextField txtProveedor1;
    @FXML
    private Button btnAgregar1;
    @FXML
    private Label lblNuevo;
    @FXML
    private Button btnLimpiar1;
    @FXML
    private Tab TabModificar;
    @FXML
    private Label lblCofigo2;
    @FXML
    private Label lblNombre2;
    @FXML
    private Label lblProveedor2;
    @FXML
    private TextField txtCodigo2;
    @FXML
    private Label lblPrecio2;
    @FXML
    private Label lblExistencias2;
    @FXML
    private TextField txtNombre2;
    @FXML
    private TextField txtPrecio2;
    @FXML
    private TextField txtExistencias2;
    @FXML
    private TextField txtProveedor2;
    @FXML
    private Button btnBuscar2;
    @FXML
    private Label lblModificar;
    @FXML
    private Button btnLimpiar2;
    @FXML
    private Button btnGuardar2;
    @FXML
    private Tab TabEliminar;
    @FXML
    private Label lblCodigo3;
    @FXML
    private Label lblNombre3;
    @FXML
    private Label lblProveedor3;
    @FXML
    private TextField txtCodigo3;
    @FXML
    private Label lblPrecio3;
    @FXML
    private Label lblExistencias3;
    @FXML
    private TextField txtNombre3;
    @FXML
    private TextField txtPrecio3;
    @FXML
    private TextField txtExistencia3;
    @FXML
    private TextField txtProveedor3;
    @FXML
    private Button btnBuscar3;
    @FXML
    private Label lblEliminar;
    @FXML
    private Button btnLimpiar3;
    @FXML
    private Button btnEliminar3;
    @FXML
    private Label lblProductos;
    private ProductosDao pDAO;   

    /**
     * Initializes the controller class.
     */
@Override
    public void initialize(URL url, ResourceBundle rb) {

       // String[] listaInstitucionBancaria = {"Bancomer", "Banamex", "Santander", "HSBC", "American Express", "STP"};
      //  cbInstitucionBancaria.getItems().addAll((Object[]) listaInstitucionBancaria);
        editableTexto(false);
        AgregarFormatoCamposTexto("Numerico", 12, txtCodigo1);
        AgregarFormatoCamposTexto("Numerico", 12, txtCodigo2);
        AgregarFormatoCamposTexto("Numerico", 12, txtCodigo3);
        AgregarFormatoCamposTexto("Alfabeto", 40, txtNombre1);
        AgregarFormatoCamposTexto("Alfabeto", 40, txtNombre2);
        AgregarFormatoCamposTexto("Alfabeto", 40, txtNombre3);
        AgregarFormatoCamposTexto("Numerico", 40, txtPrecio1);
        AgregarFormatoCamposTexto("Numerico", 40, txtPrecio2);
        AgregarFormatoCamposTexto("Numerico", 40, txtPrecio3);
        AgregarFormatoCamposTexto("Numerico", 150, txtExistencias1);
        AgregarFormatoCamposTexto("Numerico", 150, txtExistencias2);
        AgregarFormatoCamposTexto("Numerico", 150, txtExistencia3);
        AgregarFormatoCamposTexto("Numerico", 40, txtProveedor1);
        AgregarFormatoCamposTexto("Numerico", 40, txtProveedor2);
        AgregarFormatoCamposTexto("Numerico", 40, txtProveedor3);
        //Busqueda("GENERAL");
    } 

    @FXML
    private void actionBtnAgregar1(ActionEvent event) throws SQLException {
        conectarBase();
        if (txtCodigo1.getText() != null && txtCodigo1.getText().length() == 12) {
            rs = stmt.executeQuery("SELECT * from producto where codigoBarras = '" + txtCodigo1.getText() + "'");
            if (rs.next() == false) {
                if (txtNombre1.getText() != null && txtNombre1.getText().length() > 1) {
                    if (txtPrecio1.getText() != null && txtPrecio1.getText().length() > 0) {
                        if (txtExistencias1.getText() != null && txtPrecio1.getText().length() > 0) {
                            if (txtProveedor1.getText() != null) {
                                pDAO = new ProductosDao(
                                        txtCodigo1.getText(),
                                        txtNombre1.getText(),
                                        Double.parseDouble(txtPrecio1.getText()),
                                        (int) Integer.parseInt(txtExistencias1.getText()),
                                        (int) Integer.parseInt(txtProveedor1.getText()));

                                if (pDAO.nuevo() == 1) {
                                    JOptionPane.showMessageDialog(null, "REGISTRO EXITOSO");
                                    limpiarCampoTexto1();

                                } else {
                                    JOptionPane.showMessageDialog(null, "Error: Proveedor no existe");
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Error: Ingrese Proveedor");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Error: Ingrese Existencias");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Error: Ingrese Precio");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error: Nombre muy corto");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error: El producto Ya existe");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Error: Codigo de barras muy corto");
        }
    }


    @FXML
    private void actionBtnLimpiar1(ActionEvent event) {
        limpiarCampoTexto1();
    }

    @FXML
    private void actionBtnBuscar2(ActionEvent event) throws SQLException {
        conectarBase();
        rs = stmt.executeQuery("SELECT * from producto where codigoBarras = '" + txtCodigo2.getText() + "'");
        JOptionPane.showMessageDialog(null, "Buscando Producto ....");
        //si lo encuentra imprime en la cajas de texto los valores
        if (rs.next() == true) {
            txtCodigo2.setText(rs.getString("codigoBarras"));
            txtNombre2.setText(rs.getString("nombre"));
            txtPrecio2.setText(rs.getString("precio"));
            txtExistencias2.setText(rs.getString("stock"));
            txtProveedor2.setText(rs.getString("FK_PROVEEDOR"));
        } else {
            JOptionPane.showMessageDialog(null, "Error no existe Producto con ese codigo");
        }
    }
    
    @FXML
    private void actionBtnLimpiar2(ActionEvent event) {
        limpiarCampoTexto2();
    }

    @FXML
    private void actionBtnGuardar2(ActionEvent event) {
        String editarProducto = "";
        try {
            conectarBase();
            //Instruccion SQL DML Lenguaje de Manipulacion de datos para Update
            editarProducto = "UPDATE producto SET nombre='" + txtNombre2.getText() + "',stock ='" + txtExistencias2.getText() + "',FK_PROVEEDOR ='" + txtProveedor2.getText() + "',precio ='" + txtPrecio2.getText() + "' where codigoBarras= '" + txtCodigo2.getText() + "'";
            stmt.executeUpdate(editarProducto);
            JOptionPane.showMessageDialog(null, "Datos del producto actualizados \n\n"
                    + "\nCodigo: " + txtCodigo2.getText()
                    + "\nNombre: " + txtNombre2.getText()
                    + "\nPrecio: " + txtPrecio2.getText()
                    + "\nCantidad: " + txtExistencias2.getText()
                    + "\nProvedor: " + txtProveedor2.getText());
            limpiarCampoTexto2();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de BD No.5 \n" + ex);
        }
    }

    @FXML
    private void actionBtnBuscar3(ActionEvent event) throws SQLException {
        conectarBase();
        rs = stmt.executeQuery("SELECT * from producto where codigoBarras = '" + txtCodigo3.getText() + "'");
      //  rs = stmt.executeQuery("SELECT * from producto where nombre = '" + txtNombre3.getText() + "'");
        JOptionPane.showMessageDialog(null, "Buscando Producto ....");
        //si lo encuentra imprime en la cajas de texto los valores
        if (rs.next() == true) {
            txtCodigo3.setText(rs.getString("codigoBarras"));
            txtNombre3.setText(rs.getString("nombre"));
            txtPrecio3.setText(rs.getString("precio"));
            txtExistencia3.setText(rs.getString("stock"));
            txtProveedor3.setText(rs.getString("FK_PROVEEDOR"));
        } else {
            JOptionPane.showMessageDialog(null, "Error no existe Producto con ese codigo");
        }
    }


    @FXML
    private void actionBtnLimpiar3(ActionEvent event) {
        limpiarCampoTexto3();
    }

    @FXML
    private void actionBtnEliminar3(ActionEvent event) throws SQLException {
        int confirmaBaja;//variable que guarda un 1 si elimina  o 0 si no
        try {
            conectarBase();
            confirmaBaja=JOptionPane.showConfirmDialog(null, "Desea eliminar el siguiente producto:\n" + txtNombre3.getText());

            if (confirmaBaja == 0) {
                confirmaBaja = stmt.executeUpdate("DELETE from producto where codigoBarras='" + txtCodigo3.getText() + "'");
                JOptionPane.showMessageDialog(null, "Producto Eliminado con Exito !!!\n"
                    + "\nCodigo: " + txtCodigo3.getText()
                    + "\nNombre: " + txtNombre3.getText()
                    + "\nPrecio: " + txtPrecio3.getText()
                    + "\nCantidad: " + txtExistencia3.getText()
                    + "\nProvedor: " + txtProveedor3.getText());

                
            } else {
                JOptionPane.showMessageDialog(null, "no se elimino nada");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error  3 de modulo  baja a BD\n" + ex);
        }
        limpiarCampoTexto3();
    }

    
    private void editableTexto (boolean opcion){
        
        txtCodigo1.setEditable(!opcion);
        txtCodigo2.setEditable(!opcion);
        txtCodigo3.setEditable(!opcion);
        txtNombre1.setEditable(!opcion);
        txtNombre2.setEditable(!opcion);
        txtNombre3.setEditable(!opcion);
        txtPrecio1.setEditable(!opcion);
        txtPrecio2.setEditable(!opcion);
        txtPrecio3.setEditable(!opcion);
        txtExistencias1.setEditable(!opcion);
        txtExistencias2.setEditable(!opcion);
        txtExistencia3.setEditable(!opcion);
        txtProveedor1.setEditable(!opcion);
        txtProveedor2.setEditable(!opcion);
        txtProveedor3.setEditable(!opcion);
    }
    
    private void AgregarFormatoCamposTexto (String tipo ,int limite, TextField auxiliar){
        
        switch (tipo) {
            case "Alfabeto":
                auxiliar.addEventHandler(KeyEvent.KEY_TYPED,( KeyEvent e)-> {
                    if(auxiliar.getText().length()<limite){
                        if (!Character.isLetter(e.getCharacter().charAt(0))
                                && !Character.isWhitespace(e.getCharacter().charAt(0))){
                            e.consume();
                        }
                    }else{
                        e.consume();
                    }
                }); break;
            case "LimitarDiggitos":
                auxiliar.addEventHandler(KeyEvent.KEY_TYPED,( KeyEvent e)-> {
                    if(auxiliar.getText().length()>limite){
                        e.consume();
                }
            }); break;
            case "Numerico":
                auxiliar.addEventHandler(KeyEvent.KEY_TYPED,( KeyEvent e)-> {
                    if(auxiliar.getText().length()<limite){
                        if (!Character.isDigit(e.getCharacter().charAt(0))
                                && !Character.isWhitespace(e.getCharacter().charAt(0))){
                            e.consume();
                        }
                    }else{
                        e.consume();
                }
            }); break;
        }
        
    }
    
    private void limpiarCampoTexto1 (){
        txtCodigo1.setText("");
        txtNombre1.setText("");
        txtPrecio1.setText("");
        txtExistencias1.setText("");
        txtProveedor1.setText("");

    }
    private void limpiarCampoTexto2 (){
        txtCodigo2.setText("");
        txtNombre2.setText("");
        txtPrecio2.setText("");
        txtExistencias2.setText("");
        txtProveedor2.setText("");

    }
    private void limpiarCampoTexto3 (){
        txtCodigo3.setText("");
        txtNombre3.setText("");
        txtPrecio3.setText("");
        txtExistencia3.setText("");
        txtProveedor3.setText("");

    }
    
    private Boolean comprobacionCamposTextos(){
        return txtCodigo1.getText() != null && txtCodigo1.getText().length() == 12  && 
                txtNombre1.getText() != null && txtNombre1.getText().length() > 2  &&
                txtPrecio1.getText() != null && txtPrecio1.getText().length() > 2  &&
                txtExistencias1.getText() != null && txtExistencias1.getText().length() > 2  &&
                txtProveedor1.getText() != null && txtProveedor1.getText().length() > 1;
    }

    
}

