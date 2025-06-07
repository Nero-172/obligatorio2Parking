/*
 * Autores: [Tu nombre] - [Tu número de estudiante]
 *          [Nombre compañero] - [Número de estudiante compañero]
 */
package dominio;

import java.io.Serializable;

public class Empleado implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private String nombre;
    private String cedula;
    private String direccion;
    private int numeroEmpleado;
    
    public Empleado(String nombre, String cedula, String direccion, int numeroEmpleado){
        this.nombre = nombre;
        this.cedula = cedula;
        this.direccion = direccion;
        this.numeroEmpleado = numeroEmpleado;
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
    
    public int getNumeroEmpleado(){ 
        return numeroEmpleado; 
    }
    public void setNumeroEmpleado(int numeroEmpleado){ 
        this.numeroEmpleado = numeroEmpleado; 
    }
    
    @Override
    public String toString(){
        return nombre + " - " + numeroEmpleado;
    }
    
    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Empleado empleado = (Empleado) obj;
        return cedula.equals(empleado.cedula);
    }
}
