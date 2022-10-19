/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;
import modelo.Cliente;
import modelo.ClienteDAO;

/**
 * FXML Controller class
 *
 * @author pao-o
 */
public class VistaClientesontrolador implements Initializable {

    @FXML    private Button btnNuevo;
    @FXML    private Button btnModificar;
    @FXML    private Button btnEliminar;
    @FXML    private Button btnGuardar;
    @FXML    private TextField txtBuscar;
    @FXML    private TextField txtCodigoCliente;
    @FXML    private TextField txtNombreCliente;
    @FXML    private TextField txtRFC;
    @FXML    private TextField txtCorreoElectronico;
    @FXML    private Label lblAccion;
    @FXML    private TableView tbResultadoClientes;
    @FXML    private TableColumn <Cliente,String> tcNombre,tcIDCliente, tcRFC;
             private ClienteDAO cDAO;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        AgregarFormatoCamposTexto("Alfabeto",40,txtNombreCliente);
        AgregarFormatoCamposTexto("LimitarDiggitos", 13, txtRFC);
        AgregarFormatoCamposTexto("LimitarDigitos", 50, txtCorreoElectronico);
        defaultCampoTexto();
        Busqueda("GENERAL");
       
    }    

    @FXML
    private void actionBtnNuevo() {
        if (!lblAccion.getText().equals("NUEVO REGISTRO")){
            lblAccion.setText("NUEVO REGISTRO");
            limpiarCampoTexto();
            desactivarCampoTexto(false);
            txtCodigoCliente.setDisable(true);
            editarTexto(true);
            txtCodigoCliente.setEditable(false);
        }
        tbResultadoClientes.getSelectionModel().clearSelection();
        btnModificar.setDisable(true);
        btnEliminar.setDisable(true);
        btnGuardar.setDisable(false);
    }

    @FXML
    private void actionBtnModificar(ActionEvent event) {
        if (!lblAccion.getText().equals("MODIFICAR")){
            lblAccion.setText("MODIFICAR");
            editarTexto(true);
            txtCodigoCliente.setEditable(false);
            btnGuardar.setDisable(false);
        }
    }

    @FXML
    private void actionBtnGuardar() {
        if(lblAccion.getText().equals("MODIFICAR")){
            if (comprobacionCamposTextos()){
             cDAO = new ClienteDAO(txtCodigoCliente.getText(),
                txtNombreCliente.getText(),
                txtRFC.getText(),
                txtCorreoElectronico.getText());
            if(cDAO.modificar()==1){
                JOptionPane.showMessageDialog(null,"MODIFICACION EXITOSA");
                limpiarCampoTexto();
            }
        }else{
            JOptionPane.showMessageDialog(null,"Error: Rellene bien los campos de texto solicitado");
        }
            
        }else{
            if (comprobacionCamposTextos()){
                cDAO = new ClienteDAO(txtNombreCliente.getText(),
                    txtRFC.getText(),
                    txtCorreoElectronico.getText());
            
                if (cDAO.registro()==1){
                    JOptionPane.showMessageDialog(null,"REGISTRO EXITOSO");
                    limpiarCampoTexto();
                }
            }
            else{
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
            cDAO = new ClienteDAO(txtCodigoCliente.getText(),
                txtNombreCliente.getText(),
                txtRFC.getText(),
                txtCorreoElectronico.getText());
            cDAO.eliminar();
            
            
        }
        defaultCampoTexto();
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
        
        if (me.getSource().equals(tbResultadoClientes)){
            Cliente paux = (Cliente) tbResultadoClientes.getSelectionModel().getSelectedItem();
            cDAO = new ClienteDAO ();
            ObservableList<Cliente> lista = cDAO.consulta("DATOS",paux.getId());
            txtCodigoCliente.setText(lista.get(0).getId());
            txtNombreCliente.setText(lista.get(0).getNombre());
            txtRFC.setText(lista.get(0).getRfc());
            txtCorreoElectronico.setText(lista.get(0).getCorreoElectronico());
        
            desactivarCampoTexto(false);
            btnGuardar.setDisable(true);
            btnModificar.setDisable(false);
            btnEliminar.setDisable(false);   
        } 
    }
    private void Busqueda ( String tipo){
        cDAO = new ClienteDAO ();
        
               Object lista = cDAO.consulta(tipo,txtBuscar.getText());
               
                tbResultadoClientes.setItems((ObservableList<Cliente>) lista);
               
                tcIDCliente.setCellValueFactory( new PropertyValueFactory < Cliente,String>("id") );
                tcNombre.setCellValueFactory( new PropertyValueFactory  < Cliente,String>("nombre") );
                tcRFC.setCellValueFactory( new PropertyValueFactory  < Cliente,String>("rfc") );
                
                tcIDCliente.setVisible(true);
                tcNombre.setVisible(true);
                tcRFC.setVisible(true);
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
        return txtNombreCliente.getText() != null && txtNombreCliente.getText().length() > 2  && 
                txtRFC.getText() != null && txtRFC.getText().length() > 2  &&
                txtCorreoElectronico.getText() != null && txtCorreoElectronico.getText().length() > 2  &&
                esEmailCorrecto(txtCorreoElectronico.getText());
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
        tbResultadoClientes.getSelectionModel().clearSelection();
    }
    private void limpiarCampoTexto (){
        txtCodigoCliente.setText("");
        txtNombreCliente.setText("");
        txtRFC.setText("");
        txtRFC.setText("");
        txtCorreoElectronico.setText("");
    }
    
    private void editarTexto (boolean opcion){        
        txtCodigoCliente.setEditable(opcion);
        txtNombreCliente.setEditable(opcion);
        txtRFC.setEditable(opcion);
        txtCorreoElectronico.setEditable(opcion);
    }
    
    private void desactivarCampoTexto (Boolean opcion){
        txtCodigoCliente.setDisable(opcion);
        txtNombreCliente.setDisable(opcion);
        txtRFC.setDisable(opcion);
        txtCorreoElectronico.setDisable(opcion);
    }
   
}
