/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package examen_s4;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Currorov
 */
public class FXMLConfiguracionController implements Initializable {

    @FXML
    private TextField ubicacionTxt;
    @FXML
    private TextField archivoTxt;
    @FXML
    private TextField servidorTxt;
    @FXML
    private TextField puertoTxt;
    @FXML
    private TextField nombreTxt;
    @FXML
    private TextField usuarioTxt;
    private TextField contrasenombreTxtñaTxt;
    @FXML
    private TextArea resultadoTxt;
    @FXML
    private Button crearBoton;
    @FXML
    private TextField contraseñaTxt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
        
    }    

    @FXML
    private void crearBotonAction(ActionEvent event) {
         
        String pathTXT = "C:\\Users\\Currorov\\Desktop\\Practicas\\Acceso a Datos\\EJ1-6"+"\\"+archivoTxt.getText()+".txt";
        try{
            PrintWriter pw = new PrintWriter(new FileOutputStream(new File(pathTXT),true));
            pw.println(puertoTxt.getText());
            pw.println(servidorTxt.getText());
            pw.println(nombreTxt.getText());
            pw.println(usuarioTxt.getText());
            pw.println(contraseñaTxt.getText());
            pw.flush();
            pw.close();  
            
        } catch (IOException ex) {
            Logger.getLogger(FXMLConfiguracionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
