/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JAVAFXPuntoVenta;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class PuntoVenta extends Application {
        private Parent root ;
    @Override
    public void start(Stage primaryStage) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/vista/vistaLogin.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Punto de Venta");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop(){
        
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
