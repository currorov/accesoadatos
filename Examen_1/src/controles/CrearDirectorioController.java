/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controles;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Currorov
 */
public class CrearDirectorioController implements Initializable {

    @FXML
    private TextField rutaTxt;
    @FXML
    private TextField dirTxt;
    @FXML
    private TextField fichTxt;
    @FXML
    private Button crearBoton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rutaTxt.setText("C:\\holamundo\\");
    }

    @FXML
    private void crearBotonAction(ActionEvent event) throws IOException {
        File path = new File(rutaTxt.getText());
        File dele = new File(path.getParent());
        int contador = 0;
        int contador2 = 0;

        int n = Integer.parseInt(dirTxt.getText());
        int num = Integer.parseInt(fichTxt.getText());

        if (path.exists()) {     //si existe borra y luego lo vuelev a crear el directorio
            File[] contenido = path.listFiles();
            for (File c : contenido) {
                while (c.isDirectory()) {
                    for (File b : c.listFiles()) {
                        b.delete();
                    }
                    c.delete();
                }
                c.delete();
            }
            path.delete();
            //f.delete();
            File nuevo = new File(rutaTxt.getText());
            nuevo.mkdirs();
        } else {
            path.mkdirs();
        }

        for (int i = 1; i <= n; i++) {
            File carpeta = new File(path, "Curro_" + contador);
            carpeta.mkdir();
            contador++;
        }
        for (int t = 1; t <= num; t++) {
            File fichero = new File(path, "Rovira_" + contador2);
            fichero.createNewFile();
            contador2++;
        }
    }
}
