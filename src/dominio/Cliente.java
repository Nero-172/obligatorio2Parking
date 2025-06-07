/*
 * Autores: [Tu nombre] - [Tu número de estudiante]
 *          [Nombre compañero] - [Número de estudiante compañero]
 */
package dominio;

import java.io.Serializable;

public class Cliente implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private String nombre;
    private String cedula;
    private String direccion;
    private String celular;
    private int anioCliente;
    
    public Cliente(String nombre, String cedula, String direccion, String celular, int anioCliente){
        this.nombre = nombre;
        this.cedula = cedula;
        this.direccion = direccion;
        this.celular = celular;
        this.anioCliente = anioCliente;
    }
    
    // Getters y Setters
    public String getNombre(){ 
        return nombre; 
    }
    public void setNombre(String nombre){ 
        this.nombre = nombre; 
    }
    
    public String getCedula(){ 
        return cedula; 
    }
    public void setCedula(String cedula){ 
        this.cedula = cedula; 
    }
    
    public String getDireccion(){ 
        return direccion; 
    }
    public void setDireccion(String direccion){ 
        this.direccion = direccion; 
    }
    
    public String getCelular(){ 
        return celular; 
    }
    public void setCelular(String celular){ 
        this.celular = celular; 
    }
    
    public int getAnioCliente(){ 
        return anioCliente; 
    }
    public void setAnioCliente(int anioCliente){ 
        this.anioCliente = anioCliente; 
    }
    
    @Override
    public String toString(){
        return nombre + " - " + cedula;
    }
    
    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cliente cliente = (Cliente) obj;
        return cedula.equals(cliente.cedula);
    }
}
