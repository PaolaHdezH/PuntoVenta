/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import modelo.UsuarioDAO;


/**
 * FXML Controller class
 *
 * @author Sergio
 */
public class VistaLoginController implements Initializable {
    
    public Connection cn;
    public Statement stmt;
    public ResultSet rs;
    private RadioButton rbtAdmin;
    @FXML
    private ToggleGroup rbtngrupo;
    @FXML
    private RadioButton rbtAdministrador;
    
    public void conectarBase() {//inicia metodo conectar
        try {//inicia try
            //puente de conexion
            Class.forName("com.mysql.jdbc.Driver");//puente de conexion
            cn = DriverManager.getConnection("jdbc:mysql://localhost/puntodeventa", "root", "");
            stmt = cn.createStatement();//genera sentencias sql
        } catch (SQLException ex) {//inicia exeption
            JOptionPane.showMessageDialog(null, "Error 1 de BD\n" + ex);
        } catch (Exception e) {//inica exception errores generales
            JOptionPane.showMessageDialog(null, "Error general de aplicacion \n" + e);

        }
    }//termina metodo conectar


    @FXML
    private Button btnAcceder;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private TextField txtUsuario;

    @FXML
    private RadioButton rbtEmpleado;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        txtUsuario.setText("");
        txtPassword.setText("");
    }

    @FXML
    private void acceder() throws IOException{
        UsuarioDAO uDAO = new UsuarioDAO();
        String resultado = uDAO.consulta(txtUsuario.getText(), txtPassword.getText());
        
        System.out.println(resultado);
        
            
            String ruta= "";
            if (resultado.equals("administrador")){
                ruta= "/vista/vistaMenuGeneral.fxml";
            }else if(resultado.equals("vendedor")){
                ruta= "/vista/vistaMenuGeneralEmpleado.fxml";
            }
            //te cargan el  archivo fxml
            Parent root= null;
                FXMLLoader loader =new FXMLLoader(getClass().getResource(ruta));
                //te carga el controlador del fxml
                
                if (resultado.equals("administrador")){
                    VistaMenuGeneralControlador vmg =loader.getController();
                    root=loader.load();
                
                
                    Scene scene = new Scene(root);
                    //recupero  mi  Stage desde el main 
                    Stage stage =(Stage)this.btnAcceder.getScene().getWindow();
                    stage.setScene(scene);
                stage.show();
                }
                else if(resultado.equals("vendedor")){
                    VistaMenuGeneralEmpleadoControlador vmgc=loader.getController();
                    root=loader.load();
                
                
                    Scene scene = new Scene(root);
                    //recupero  mi  Stage desde el main 
                    Stage stage =(Stage)this.btnAcceder.getScene().getWindow();
                    stage.setScene(scene);
                stage.show();
                }
                
                
        
    }
}

