/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author pao-o
 */
public class VistaMenuGeneralEmpleadoControlador implements Initializable {

    @FXML
    private BorderPane bpMenuGeneral;
    @FXML
    private Button btnProductos;
    @FXML
    private Button btnCliente;
    @FXML
    private Button btnProveedor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void actionBtnProductos(ActionEvent event) {
    }

    @FXML
    private void actionBtnClientes(ActionEvent event) {
    }

    @FXML
    private void actionBtnProveedor(ActionEvent event) {
    }
    
}
