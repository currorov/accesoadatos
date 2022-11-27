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
public class BuscarPalabrasController implements Initializable {

    @FXML
    private TextArea textArea;
    @FXML
    private TextField pathOrigen;
    @FXML
    private Text numeroVecesPalabra;
    @FXML
    private TextField palabraBuscar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pathOrigen.setText("C:\\Users\\Currorov\\Desktop\\Examen2\\donQuijote.txt");
    }    

    @FXML
    private void leerArchivoBuscar(ActionEvent event) throws FileNotFoundException, IOException {
        textArea.setText(" ");
        File f = new File(pathOrigen.getText());
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        String texto=br.readLine();
        int contador=0;
        try{
            while(texto!=null){
                
                String [] palabra = texto.split(" ");
                for(String p : palabra){
                    if(p.equals(palabraBuscar.getText())){
                        contador++;
                        p=p.replace(p, "<<"+p+">>");
                        
                    }
                }
                textArea.setText(textArea.getText()+texto);
                texto=br.readLine();
            }
            numeroVecesPalabra.setText("Numero de veces que aparece la palabra: "+contador);
        } catch(Exception e){
            textArea.setText("ERROR");
        }
        
    }

    
}
