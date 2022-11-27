/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package repaso_exxamen3;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
 * @author Currorov
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private TextField idTxt;
    @FXML
    private TextField marcaTxt;
    @FXML
    private TextField modeloTxt;
    @FXML
    private TextField almacenamientoTxt;
    @FXML
    private Button atrasBoton;
    @FXML
    private Button posteriorBoton;
    @FXML
    private Button configuracionBoton;
    List<Ordenador> ordenadores;
    int selec=0;
    String idTemp="hh";
    String marcaTemp="hh";
    String modeloTemp="hh";
    String almacenamientoTemp="hh";
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            ordenadores=new ArrayList();
            Ordenador ordenador=null;
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("ordenadores.xml"));
            document.getDocumentElement().normalize();
            NodeList nList = document.getElementsByTagName("ordenador");
            
            for(int i=0;i<nList.getLength();i++){
                org.w3c.dom.Node node = nList.item(i);
                ordenador = new Ordenador();
                Element e = (Element) node;
                if(Node.ELEMENT_NODE==node.getNodeType()){
                    ordenador.setId(Integer.parseInt(e.getAttribute("id")));
                    ordenador.setMarca(e.getElementsByTagName("marca").item(0).getTextContent());
                    ordenador.setModelo(e.getElementsByTagName("modelo").item(0).getTextContent());
                    ordenador.setAlmacenamiento(e.getElementsByTagName("almacenamiento").item(0).getTextContent());
                    ordenadores.add(ordenador);
                }
            }
            idTxt.setText(String.valueOf(ordenadores.get(0).getId()));
            marcaTxt.setText(ordenadores.get(0).getMarca());
            modeloTxt.setText(ordenadores.get(0).getModelo());
            almacenamientoTxt.setText(ordenadores.get(0).getAlmacenamiento());
            
        }catch(Exception e){
            
        }
    }    

    @FXML
    private void atrasBotonAction(ActionEvent event) {
        if(selec>0){
            selec--;
            idTxt.setText(String.valueOf(ordenadores.get(selec).getId()));
            marcaTxt.setText(ordenadores.get(selec).getMarca());
            modeloTxt.setText(ordenadores.get(selec).getModelo());
            almacenamientoTxt.setText(ordenadores.get(selec).getAlmacenamiento());
        }
    }

    @FXML
    private void posteriorBotonAction(ActionEvent event) {
        //temporales
        idTemp = idTxt.getText();
        marcaTemp = marcaTxt.getText();
        modeloTemp = modeloTxt.getText();
        almacenamientoTemp = almacenamientoTxt.getText();
        
        if(!marcaTemp.equals(marcaTxt.getText())||!modeloTemp.equals(modeloTxt.getText())||almacenamientoTemp.equals(almacenamientoTxt.getText())){
            if(!marcaTemp.equals("")){
                for(Ordenador o : ordenadores){
                    if(o.getId()==Integer.parseInt(idTxt.getText())){
//                      o.setId(Integer.parseInt(idTxt.getText()));
                        o.setMarca(marcaTxt.getText());
                        o.setModelo(modeloTxt.getText());
                        o.setAlmacenamiento(almacenamientoTxt.getText());
                        añadir();
                    }
                }
            }
        }
        if(idTemp.equals("")){
            for(Ordenador o : ordenadores){
                    if(o.getId()==Integer.parseInt(idTxt.getText())){
                        ordenadores.remove(o);
                        añadir();
                    }
                }
        }
        if(!idTemp.equals("")){
            if(selec<(ordenadores.size()-1)){
                selec++;
                idTxt.setText(String.valueOf(ordenadores.get(selec).getId()));
                marcaTxt.setText(ordenadores.get(selec).getMarca());
                modeloTxt.setText(ordenadores.get(selec).getModelo());
                almacenamientoTxt.setText(ordenadores.get(selec).getAlmacenamiento());
            }else{
                    idTxt.setText("");
                    marcaTxt.setText("");
                    modeloTxt.setText("");
                    almacenamientoTxt.setText("");
            }
        } else {
            Ordenador nueva=null;
            nueva = new Ordenador();
//          nueva.setId(Integer.parseInt(idTxt.getText()));
            nueva.setMarca(marcaTxt.getText());
            nueva.setModelo(modeloTxt.getText());
            nueva.setAlmacenamiento(almacenamientoTxt.getText());
            ordenadores.add(nueva);
            añadir();
        }
        
    }
    private void añadir(){
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation dom = builder.getDOMImplementation();
            Document doc = dom.createDocument(null, "ordenadores", null);
            doc.setXmlVersion("1.0");
            int identificador=1;
            for(Ordenador o : ordenadores){
                Element ordenador = doc.createElement("ordenador");
                ordenador.setAttribute("id", String.valueOf(String.valueOf(identificador)));              
                Element marca = doc.createElement("marca");
                marca.appendChild(doc.createTextNode(o.getMarca()));
                ordenador.appendChild(marca);
                Element modelo = doc.createElement("modelo");
                modelo.appendChild(doc.createTextNode(o.getModelo()));
                ordenador.appendChild(modelo);
                Element almacenamiento = doc.createElement("almacenamiento");
                almacenamiento.appendChild(doc.createTextNode(o.getAlmacenamiento()));
                ordenador.appendChild(almacenamiento);               
                doc.getDocumentElement().appendChild(ordenador);
                identificador++;
            }
            Source src = new DOMSource(doc);
            Result resultado = new StreamResult(new File("ordenadores.xml"));
            Transformer trfm = TransformerFactory.newInstance().newTransformer();
            trfm.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            trfm.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            trfm.setOutputProperty(OutputKeys.INDENT, "yes");
            trfm.transform(src, resultado);          
        }catch(Exception e){
            
        }
    }

    @FXML
    private void configuracionBotonAction(ActionEvent event) {
    }
    
}
