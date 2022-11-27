package examen_s4;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Currorov
 */
public class Ciudad {
    int id;
    String nombre;
    String codigo;
    String distrito;
    int poblacion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public int getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(int poblacion) {
        this.poblacion = poblacion;
    }

    public Ciudad(int id, String nombre, String codigo, String distrito, int poblacion) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.distrito = distrito;
        this.poblacion = poblacion;
    }
    
    
    
    
    
}
