package dominio;

import java.io.Serializable;

public class Entrada implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private Vehiculo vehiculo;
    private String fecha;
    private String hora;
    private String notas;
    private Empleado empleado;
    
    public Entrada(Vehiculo vehiculo, String fecha, String hora, String notas, Empleado empleado){
        this.vehiculo = vehiculo;
        this.fecha = fecha;
        this.hora = hora;
        this.notas = notas;
        this.empleado = empleado;
    }
    
    // Getters y Setters
    public Vehiculo getVehiculo(){ 
        return vehiculo; 
    }
    public void setVehiculo(Vehiculo vehiculo){ 
        this.vehiculo = vehiculo; 
    }
    
    public String getFecha(){ 
        return fecha; 
    }
    public void setFecha(String fecha){ 
        this.fecha = fecha; 
    }
    
    public String getHora(){ 
        return hora; 
    }
    public void setHora(String hora){ 
        this.hora = hora; 
    }
    
    public String getNotas(){ 
        return notas; 
    }
    public void setNotas(String notas){ 
        this.notas = notas; 
    }
    
    public Empleado getEmpleado(){ 
        return empleado; 
    }
    public void setEmpleado(Empleado empleado){ 
        this.empleado = empleado; 
    }
    
    @Override
    public String toString(){
        return "Entrada - " + vehiculo.getMatricula() + " - " + fecha + " " + hora;
    }
}
