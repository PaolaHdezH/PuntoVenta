/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Ale Del Angel
 */

public class VistaMenuGeneralControlador implements Initializable {
    
    @FXML
    BorderPane bpMenuGeneral;
    
    @FXML
    Button btnProveedor;
    
    @FXML Button btnProductos;
    @FXML Button btnCliente;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML
    public void actionBtnClientes(){
        String seccion = "vistaClientes";
        cargaPagina(seccion);
                
    }
    @FXML 
    public void actionBtnProductos (){
        String seccion ="vistaPanelProductos";
        cargaPagina(seccion);
    }
    
    
    @FXML 
    public void actionBtnProveedor (){
        String seccion ="vistaPanelProveedor";
        cargaPagina(seccion);
    }
    
    private void cargaPagina(String seccion){
    Parent root = null;
    try {
        root= FXMLLoader.load(getClass().getResource("/vista/"+seccion+".fxml"));
        root.setUserData(btnProveedor.getScene().getRoot().getUserData());
    } catch (IOException ex) {
        Logger.getLogger(VistaMenuGeneralControlador.class.getName()).log(Level.SEVERE,null,ex);
    }
    bpMenuGeneral.setCenter(null);
    bpMenuGeneral.setCenter(root);
        
    }
}
