/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package javafxapplication42;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Francisco Rovira
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private TextField nombreText;
    @FXML
    private TextField apellidosText;
    @FXML
    private TextField incidenciaText;
    @FXML
    private TextField idText;
    List<Incidencia> incidencias;
    int selec=0;
    int contador=1;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            incidencias=new ArrayList();
            Incidencia incidencia=null;
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("datos.xml"));
            document.getDocumentElement().normalize();
            NodeList nList = document.getElementsByTagName("incidencia");
            
            for(int i=0;i<nList.getLength();i++){
                org.w3c.dom.Node node = nList.item(i);
                incidencia = new Incidencia();
                Element e = (Element) node;
                if(Node.ELEMENT_NODE==node.getNodeType()){
                    incidencia.setId(Integer.parseInt(e.getAttribute("id")));
                    incidencia.setNombre(e.getElementsByTagName("nombre").item(0).getTextContent());
                    incidencia.setApellidos(e.getElementsByTagName("apellidos").item(0).getTextContent());
                    incidencia.setInci(e.getElementsByTagName("inci").item(0).getTextContent());
                    incidencias.add(incidencia);
                }
            }
            idText.setText(String.valueOf(incidencias.get(0).getId()));
            nombreText.setText(incidencias.get(0).getNombre());
            apellidosText.setText(incidencias.get(0).getApellidos());
            incidenciaText.setText(incidencias.get(0).getInci());
        }catch(Exception e){
            
        }
    }    

    @FXML
    private void configuracionBoton(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("FXMLConfiguracion.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void anteriorBoton(ActionEvent event) {
        
        if(selec>-1){
            selec--;
            idText.setText(String.valueOf(incidencias.get(selec).getId()));
            nombreText.setText(incidencias.get(selec).getNombre());
            apellidosText.setText(incidencias.get(selec).getApellidos());
            incidenciaText.setText(incidencias.get(selec).getInci());
            contador--;
        }
        if(idText.getText().equals("")){
            for(Incidencia i : incidencias){
                if(i.getId()==selec)
                    incidencias.remove(i);
                    añadir();
            }
            
        }
    }

    @FXML
    private void posteriorBoton(ActionEvent event) {
        selec++;
        
        if(selec<incidencias.size()){            
            idText.setText(String.valueOf(incidencias.get(selec).getId()));
            nombreText.setText(incidencias.get(selec).getNombre());
            apellidosText.setText(incidencias.get(selec).getApellidos());
            incidenciaText.setText(incidencias.get(selec).getInci());
            contador++;
        }
        if(contador>incidencias.size()){
            idText.setText(" ");
            nombreText.setText(" ");
            apellidosText.setText(" ");
            incidenciaText.setText(" ");  
            contador++;
        }
        if(contador>(incidencias.size()+1)){
            Incidencia nueva=null;
            nueva = new Incidencia();
            nueva.setId(Integer.parseInt(idText.getText()));
            nueva.setNombre(nombreText.getText());
            nueva.setApellidos(apellidosText.getText());
            nueva.setInci(incidenciaText.getText());
            incidencias.add(nueva);
            añadir();
        }
        if(idText.getText().equals("")){
            for(Incidencia i : incidencias){
                if(i.getId()==selec)
                    incidencias.remove(i);
                    añadir();
            }
            
        }
        
    }
    private void añadir(){
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation dom = builder.getDOMImplementation();
            Document doc = dom.createDocument(null, "libros", null);
            doc.setXmlVersion("1.0");
            int identificador=1;
            for(Incidencia i : incidencias){
                Element incidencia = doc.createElement("incidencia");
                incidencia.setAttribute("id", String.valueOf(String.valueOf(identificador)));              
                Element nombre = doc.createElement("nombre");
                nombre.appendChild(doc.createTextNode(i.getNombre()));
                incidencia.appendChild(nombre);
                Element apellidos = doc.createElement("apellidos");
                apellidos.appendChild(doc.createTextNode(i.getApellidos()));
                incidencia.appendChild(apellidos);
                Element inci = doc.createElement("inci");
                inci.appendChild(doc.createTextNode(i.getInci()));
                incidencia.appendChild(inci);               
                doc.getDocumentElement().appendChild(incidencia);
                identificador++;
            }
            Source src = new DOMSource(doc);
            Result resultado = new StreamResult(new File("datos.xml"));
            Transformer trfm = TransformerFactory.newInstance().newTransformer();
            trfm.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            trfm.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            trfm.setOutputProperty(OutputKeys.INDENT, "yes");
            trfm.transform(src, resultado);          
        }catch(Exception e){
            
        }
    }
}
