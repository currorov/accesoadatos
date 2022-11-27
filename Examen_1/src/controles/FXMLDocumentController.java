/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package controles;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Currorov
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Button crearBoton;
    @FXML
    private Button listarBoton;
    @FXML
    private Button borrarBoton;
    @FXML
    private TextField borrarTxt;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        borrarTxt.setText("C:\\holamundo\\");
    }    

    @FXML
    private void crearBotonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/vistas/crearDirectorio.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void listarBotonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/vistas/listarDirectorio.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void borrarBotonAction(ActionEvent event) {
        BorrarRecursivo(borrarTxt.getText());
        
    }
    
        private void BorrarRecursivo(String nombreElemento) {
        
        File path = new File(nombreElemento);
        File[] contenido = path.listFiles();
        for (int i=0;i<contenido.length;i++){
            System.out.println(contenido[i].getName());
            if(contenido[i].isFile()){
                contenido[i].delete();
            }
            else{
                BorrarRecursivo(contenido[i].getAbsolutePath());
                contenido[i].delete();
            }
        }
        path.delete();
        }

  
    
}
