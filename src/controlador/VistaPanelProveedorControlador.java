/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;



import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import modelo.Proveedor;
import modelo.ProveedorDAO;

public class VistaPanelProveedorControlador implements Initializable {

    
    @FXML    private Button btnNuevo;
    @FXML    private Button btnModificar;
    @FXML    private Button btnEliminar;
    @FXML    private Button btnGuardar;
    @FXML    private ComboBox cbInstitucionBancaria;
    @FXML    private TextField txtBuscar;
    @FXML    private TextField txtCodigoProveedor;
    @FXML    private TextField txtNombreProveedor;
    @FXML    private TextField txtApellidoPaterno;
    @FXML    private TextField txtApellidoMaterno;
    @FXML    private TextField txtDireccion;
    @FXML    private TextField txtCompania;
    @FXML    private TextField txtTelefono;
    @FXML    private TextField txtCorreoElectronico;
    @FXML    private TextField txtClabe;
    @FXML    private Label lblAccion;
    @FXML    private TableView tbResultadoProveedores;
    @FXML    private TableColumn <Proveedor,String> tcNombre,tcIDProveedor, tcCompania;
             private ProveedorDAO pDAO;   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        String [] listaInstitucionBancaria = {"Bancomer","Banamex","Santander","HSBC","American Express","STP"};
        cbInstitucionBancaria.getItems().addAll((Object[]) listaInstitucionBancaria);
        AgregarFormatoCamposTexto("Alfabeto",40,txtNombreProveedor);
        AgregarFormatoCamposTexto("Alfabeto",40, txtApellidoPaterno);
        AgregarFormatoCamposTexto("Alfabeto",40, txtApellidoMaterno);
        AgregarFormatoCamposTexto("LimitarDiggitos",150, txtDireccion);
        AgregarFormatoCamposTexto("Alfabeto", 40, txtCompania);
        AgregarFormatoCamposTexto("Numerico", 10, txtTelefono);
        AgregarFormatoCamposTexto("LimitarDigitos", 60, txtCorreoElectronico);
        AgregarFormatoCamposTexto("Numerico", 18, txtClabe);
        defaultCampoTexto();
        Busqueda("GENERAL");
        
    }    
    
    @FXML
    public void actionBtnNuevo(){
        if (!lblAccion.getText().equals("NUEVO REGISTRO")){
            lblAccion.setText("NUEVO REGISTRO");
            limpiarCampoTexto();
            desactivarCampoTexto(false);
            txtCodigoProveedor.setDisable(true);
            editarTexto(true);
            txtCodigoProveedor.setEditable(false);
        }
        tbResultadoProveedores.getSelectionModel().clearSelection();
        btnModificar.setDisable(true);
        btnEliminar.setDisable(true);
        btnGuardar.setDisable(false);
        
    }
    
    @FXML
    public void actionBtnModificar(){
        if (!lblAccion.getText().equals("MODIFICAR")){
            lblAccion.setText("MODIFICAR");
            editarTexto(true);
            txtCodigoProveedor.setEditable(false);
            btnGuardar.setDisable(false);
        }
    }
    
    @FXML
    public void actionBtnGuardar ( ){
        //evalua en que funcion esta y asi mismo  realiza la acion 
        if(lblAccion.getText().equals("MODIFICAR")){
            if (comprobacionCamposTextos()){
            pDAO = new ProveedorDAO(txtCodigoProveedor.getText(),
                txtNombreProveedor.getText(),
                txtApellidoPaterno.getText(),
                txtApellidoMaterno.getText(),
                txtDireccion.getText(),
                txtCompania.getText(),
                txtTelefono.getText(),
                txtCorreoElectronico.getText(),
                cbInstitucionBancaria.getSelectionModel().getSelectedItem().toString(),
                txtClabe.getText());
            if(pDAO.modificar()==1){
                JOptionPane.showMessageDialog(null,"MODIFICACION EXITOSA");
                limpiarCampoTexto();
            }
        }else{
            JOptionPane.showMessageDialog(null,"Error: Rellene bien los campos de texto solicitado");
        }
            
        }else{
            if (comprobacionCamposTextos()){
                pDAO = new ProveedorDAO(txtNombreProveedor.getText(),
                    txtApellidoPaterno.getText(),
                    txtApellidoMaterno.getText(),
                    txtDireccion.getText(),
                    txtCompania.getText(),
                    txtTelefono.getText(),
                    txtCorreoElectronico.getText(),
                    cbInstitucionBancaria.getSelectionModel().getSelectedItem().toString(),
                    txtClabe.getText());
            
                if (pDAO.registro()==1){
                    JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                    limpiarCampoTexto();
                }
            }else{
                JOptionPane.showMessageDialog(null,"Error: Rellene bien los campos de texto solicitado");
            }
        }
        defaultCampoTexto();
        Busqueda("GENERAL");
    }
   
    @FXML
    public void actionBtnEliminar(){
        if (0 == JOptionPane.showConfirmDialog(null,
                "Realmente desea Eliminarlo?",
                "si no", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE)){
            pDAO = new ProveedorDAO(txtCodigoProveedor.getText(),
                txtNombreProveedor.getText(),
                txtApellidoPaterno.getText(),
                txtApellidoMaterno.getText(),
                txtDireccion.getText(),
                txtCompania.getText(),
                txtTelefono.getText(),
                txtCorreoElectronico.getText(),
                cbInstitucionBancaria.getSelectionModel().getSelectedItem().toString(),
                txtClabe.getText());
            
            pDAO.eliminar();
            
            
        }
        defaultCampoTexto();
        cbInstitucionBancaria.getSelectionModel().clearSelection();
        Busqueda("GENERAL");
    }
    
    
    @FXML 
    public void keyBusqueda (KeyEvent e){
        if(e.getTarget().equals(txtBuscar)){
            if (txtBuscar.getText().equals("")){
                Busqueda("GENERAL");                
            }else{
                Busqueda("REFERENCIA");
            }
        }
    }
    
    @FXML
    public void mouseReleasedSeleccion(MouseEvent me){
        
        lblAccion.setText("DETALLE");
        desactivarCampoTexto(false);
        editarTexto(false);
        
        if (me.getSource().equals(tbResultadoProveedores)){
            Proveedor paux = (Proveedor) tbResultadoProveedores.getSelectionModel().getSelectedItem();
            pDAO = new ProveedorDAO ();
            ObservableList<Proveedor> lista = pDAO.consulta("DATOS",paux.getId());
            txtCodigoProveedor.setText(lista.get(0).getId());
            txtNombreProveedor.setText(lista.get(0).getNombre());
            txtApellidoPaterno.setText(lista.get(0).getApellidoPaterno());
            txtApellidoMaterno.setText(lista.get(0).getApellidoMaterno());
            txtDireccion.setText(lista.get(0).getDireccion());
            txtCompania.setText(lista.get(0).getCompania());
            txtTelefono.setText(lista.get(0).getTelefono());
            txtCorreoElectronico.setText(lista.get(0).getCorreoElectronico());
            cbInstitucionBancaria.setValue(lista.get(0).getInstitucionBancaria());
            txtClabe.setText(lista.get(0).getClabe());
        
            desactivarCampoTexto(false);
            btnGuardar.setDisable(true);
            btnModificar.setDisable(false);
            btnEliminar.setDisable(false);   
        } 
    }
    private void Busqueda ( String tipo){
        pDAO = new ProveedorDAO ();
        
               Object lista = pDAO.consulta(tipo,txtBuscar.getText());
                tbResultadoProveedores.setItems((ObservableList<Proveedor>) lista);
               
                tcIDProveedor.setCellValueFactory( new PropertyValueFactory < Proveedor,String>("id") );
                tcNombre.setCellValueFactory( new PropertyValueFactory  < Proveedor,String>("nombre") );
                tcCompania.setCellValueFactory( new PropertyValueFactory  < Proveedor,String>("compania") );
                
                tcIDProveedor.setVisible(true);
                tcNombre.setVisible(true);
                tcCompania.setVisible(true);
    }
    
    private void AgregarFormatoCamposTexto (String tipo ,int limite, TextField auxiliar){
        switch (tipo) {
            case "Alfabeto":
                auxiliar.addEventHandler(KeyEvent.KEY_TYPED,( KeyEvent e)-> {
                    if(auxiliar.getText().length()<limite){
                        if (!Character.isLetter(e.getCharacter().charAt(0))
                                && !Character.isWhitespace(e.getCharacter().charAt(0))){
                            e.consume();
                        }else {
                            if (Character.isLowerCase(e.getCharacter().charAt(0))&& !auxiliar.equals(txtCorreoElectronico)){
                                char minuscula = e.getCharacter().charAt(0);
                                char mayuscula = Character.toUpperCase(minuscula);
                                System.out.println(minuscula);
                                System.out.println(mayuscula);
                                e.getCharacter().replace(minuscula, mayuscula);
                                
                            }
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
    private Boolean comprobacionCamposTextos(){
        return txtNombreProveedor.getText() != null && txtNombreProveedor.getText().length() > 2  && 
                txtApellidoPaterno.getText() != null && txtApellidoPaterno.getText().length() > 2  &&
                txtApellidoMaterno.getText() != null && txtApellidoMaterno.getText().length() > 2  &&
                txtDireccion.getText() != null && txtDireccion.getText().length() > 2  &&
                txtCompania.getText() != null && txtCompania.getText().length() > 2  &&
                txtCorreoElectronico.getText() != null && txtCorreoElectronico.getText().length() > 2  &&
                esEmailCorrecto(txtCorreoElectronico.getText()) &&
                txtTelefono.getText() != null && txtTelefono.getText().length() == 10  &&
                txtClabe.getText() != null && txtClabe.getText().length() == 18;
    }
    
    
    private static boolean esEmailCorrecto(String email) {
        boolean valido = false;       
        Pattern patronEmail = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
        Matcher mEmail = patronEmail.matcher(email.toLowerCase());
        if (mEmail.matches()){
           valido = true; 
        }
        return valido;
    }
    private void defaultCampoTexto(){
        desactivarCampoTexto(true);
        editarTexto(false);
        limpiarCampoTexto();
        lblAccion.setText("DETALLE");
        btnGuardar.setDisable(true);
        btnModificar.setDisable(true);
        btnEliminar.setDisable(true);
        txtBuscar.setText("");
        cbInstitucionBancaria.getSelectionModel().clearSelection();
        Busqueda("GENERAL");
        tbResultadoProveedores.getSelectionModel().clearSelection();
    }
    private void limpiarCampoTexto (){
        txtCodigoProveedor.setText("");
        txtNombreProveedor.setText("");
        txtApellidoPaterno.setText("");
        txtApellidoMaterno.setText("");
        txtDireccion.setText("");
        txtCompania.setText("");
        txtTelefono.setText("");
        txtCorreoElectronico.setText("");
        txtClabe.setText("");
    }
    
    private void editarTexto (boolean opcion){        
        txtCodigoProveedor.setEditable(opcion);
        txtNombreProveedor.setEditable(opcion);
        txtApellidoPaterno.setEditable(opcion);
        txtApellidoMaterno.setEditable(opcion);
        txtDireccion.setEditable(opcion);
        txtCompania.setEditable(opcion);
        txtTelefono.setEditable(opcion);
        txtCorreoElectronico.setEditable(opcion);
        txtClabe.setEditable(opcion);
    }
    
    private void desactivarCampoTexto (Boolean opcion){
        txtCodigoProveedor.setDisable(opcion);
        txtNombreProveedor.setDisable(opcion);
        txtApellidoPaterno.setDisable(opcion);
        txtApellidoMaterno.setDisable(opcion);
        txtDireccion.setDisable(opcion);
        txtCompania.setDisable(opcion);
        txtTelefono.setDisable(opcion);
        txtCorreoElectronico.setDisable(opcion);
        cbInstitucionBancaria.setDisable(opcion);
        txtClabe.setDisable(opcion);
    }
   
}
