/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package examen2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Curro Rovira Yago
 */
public class ContarVocalesController implements Initializable {

    @FXML
    private TextArea textArea;
    @FXML
    private TextField pathOrigen;
    @FXML
    private Text numeroVecesVocales;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pathOrigen.setText("C:\\Users\\Currorov\\Desktop\\Examen2\\donQuijote.txt");
    }    

    @FXML
    private void leerArchivoContar(ActionEvent event) throws FileNotFoundException, IOException {
        textArea.setText(" ");
        File f = new File(pathOrigen.getText());
        FileReader fr = new FileReader(f);
        int caracter=0;
        int conta=0;
        int conte=0;
        int conti=0;
        int conto=0;
        int contu=0;
        try{
            while(caracter!=-1){
                textArea.setText(textArea.getText()+(char)caracter);
                caracter=fr.read();
                char a = 'a';
                char e = 'e';
                char i = 'i';
                char o = 'o';
                char u = 'u';
                if(a==(char)caracter){
                    conta++;
                }
                if(e==(char)caracter){
                    conte++;
                }
                if(i==(char)caracter){
                    conti++;
                }
                if(o==(char)caracter){
                    conto++;
                }
                if(u==(char)caracter){
                    contu++;
                }
            }
            numeroVecesVocales.setText("La vocal a aparece: "+conta+", la e: "+conte+", la i: "+conti+", la o: "+conto+", la u: "+contu);
            
        } catch(Exception e){
            textArea.setText("ERROR");
        }
    }
    
}
