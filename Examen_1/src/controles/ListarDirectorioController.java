/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controles;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
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
public class ListarDirectorioController implements Initializable {

    @FXML
    private TextField rutaTxt;
    @FXML
    private Button listarBoton;
    @FXML
    private TextArea resultadoTxt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rutaTxt.setText("C:\\holamundo\\");
    }    

    @FXML
    private void listarBotonAction(ActionEvent event) {
        resultadoTxt.setText(" ");
        File path = new File(rutaTxt.getText());
        if (path.exists()) {
            File[] contenido = path.listFiles();

            for (File c : contenido) {
                String info = "";
                info += "Nombre: " + c.getName() + "\n";
                info += "\t" + "Ruta completa: " + c.getAbsolutePath() + "\n";
                if (c.isDirectory()) {
                    info += "\t" + "Es un directorio" + "\n";
                } else if (c.isFile()) {
                    info += "\t" + "Es un fichero" + "\n";
                }
                if (c.isHidden()) {
                    info += "\t" + "Esta oculto" + "\n";
                } else {
                    info += "\t" + "Esta visible" + "\n";
                }
                resultadoTxt.setText(resultadoTxt.getText() + info);
            }
        }
        else{
            resultadoTxt.setText("No existe");
        }
        }
    }
    
    

