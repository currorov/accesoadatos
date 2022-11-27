/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package examen_s4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Currorov
 */
public class nuevo implements Initializable {

    private Label label;
    @FXML
    private TextField idTxt;
    @FXML
    private TextField ciudadTxt;
    @FXML
    private Button atrasBoton;
    @FXML
    private Button siguienteBoton;
    @FXML
    private Button configuracionBoton;
    @FXML
    private Button consulatsBoton;
    ArrayList<Ciudad> ciudades = new ArrayList<Ciudad>();
    @FXML
    private TextField codigoTxt;
    @FXML
    private TextField distritoTxt;
    @FXML
    private TextField poblacionTxt;
    private static Connection con;
    ResultSet datos = null;
    String idTemp="";
    String ciudadTemp="";
    String codigoTemp="";
    String distritoTemp="";
    String poblacionTemp="";

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        File f = new File("C:\\Users\\Currorov\\Desktop\\Practicas\\Acceso a Datos\\EJ1-6" + "\\conf.txt.txt");
        //File f = new File("conf.txt");
        try {
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String puerto = br.readLine();
            String servidor = br.readLine();
            String bdatos = br.readLine();
            String usuario = br.readLine();
            String contraseña = br.readLine();
            br.close();
            fr.close();
            con = null;
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection("jdbc:mysql://" + servidor + ":" + puerto + "/" + bdatos, usuario, contraseña);
            if (con != null) {
                System.out.println("Conexión establecida");
            }
            String sql = "SELECT * FROM city";
            Statement st;
            st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            datos = st.executeQuery(sql);

      
            datos.next();
            idTxt.setText(datos.getString(1));
            ciudadTxt.setText(datos.getString(2));
            codigoTxt.setText(datos.getString(3));
            distritoTxt.setText(datos.getString(4));
            poblacionTxt.setText(datos.getString(5));

            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(nuevo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(nuevo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(nuevo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(nuevo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void atrasBotonAction(ActionEvent event) throws SQLException {
        if (!datos.isFirst()) {
            if (idTxt.equals("")) {
                //datos.next();
                datos.deleteRow();
            }
            datos.previous();
            idTxt.setText(datos.getString(1));
            ciudadTxt.setText(datos.getString(2));
            codigoTxt.setText(datos.getString(3));
            distritoTxt.setText(datos.getString(4));
            poblacionTxt.setText(datos.getString(5));
            datos.previous();
            idTemp = datos.getString(1);
            ciudadTemp = datos.getString(2);
            codigoTemp = datos.getString(3);
            distritoTemp = datos.getString(4);
            poblacionTemp = datos.getString(5);
            datos.next();
        
        }

    }

    @FXML
    private void siguienteBotonAction(ActionEvent event) throws SQLException {
        if(!ciudadTemp.equals(ciudadTxt.getText())||!codigoTemp.equals(codigoTxt.getText())||!distritoTemp.equals(distritoTxt.getText())||!poblacionTemp.equals(poblacionTxt.getText())){
            if(!ciudadTemp.equals("")){
                datos.updateString("Name", ciudadTxt.getText());
                datos.updateString("CountryCode", codigoTxt.getText());
                datos.updateString("District", distritoTxt.getText());
                datos.updateString("Population", poblacionTxt.getText());
                datos.updateRow();
            }
        }
        //comprobar actualizar antes de avnzar
        if (idTxt.equals("")) {
            //datos.previous();
            datos.deleteRow();
        }

        datos.next();

        if (datos.isAfterLast()) {

            if (idTemp.equals("") && !ciudadTxt.getText().equals("")) {
                System.out.println("insert");
                datos.moveToInsertRow();
                datos.updateString("Name", ciudadTxt.getText());
                datos.updateString("CountryCode", codigoTxt.getText());
                datos.updateString("District", distritoTxt.getText());
                datos.updateString("Population", poblacionTxt.getText());
                datos.insertRow();

            }

            idTxt.setText("");
            ciudadTxt.setText("");
            codigoTxt.setText("");
            distritoTxt.setText("");
            poblacionTxt.setText("");
        } else {
            idTxt.setText(datos.getString(1));
            ciudadTxt.setText(datos.getString(2));
            codigoTxt.setText(datos.getString(3));
            distritoTxt.setText(datos.getString(4));
            poblacionTxt.setText(datos.getString(5));
        }

        //temporales
        idTemp = idTxt.getText();
        ciudadTemp = ciudadTxt.getText();
        codigoTemp = codigoTxt.getText();
        distritoTemp = distritoTxt.getText();
        poblacionTemp = poblacionTxt.getText();

    }

    @FXML
    private void configuracionBotonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLConfiguracion.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void consultasBotonAction(ActionEvent event) {

    }

}
