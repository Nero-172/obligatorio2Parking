package dominio;

import java.io.Serializable;

public class ServicioAdicional implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private String tipoServicio;
    private String fecha;
    private String hora;
    private Vehiculo vehiculo;
    private Empleado empleado;
    private double costo;
    
    public ServicioAdicional(String tipoServicio, String fecha, String hora, Vehiculo vehiculo, Empleado empleado, double costo){
        this.tipoServicio = tipoServicio;
        this.fecha = fecha;
        this.hora = hora;
        this.vehiculo = vehiculo;
        this.empleado = empleado;
        this.costo = costo;
    }
    
    // Getters y Setters
    public String getTipoServicio(){ 
        return tipoServicio; 
    }
    public void setTipoServicio(String tipoServicio){ 
        this.tipoServicio = tipoServicio; 
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
    
    public Vehiculo getVehiculo(){ 
        return vehiculo; 
    }
    public void setVehiculo(Vehiculo vehiculo){ 
        this.vehiculo = vehiculo; 
    }
    
    public Empleado getEmpleado(){ 
        return empleado; 
    }
    public void setEmpleado(Empleado empleado){ 
        this.empleado = empleado; 
    }
    
    public double getCosto(){ 
        return costo; 
    }
    public void setCosto(double costo){ 
        this.costo = costo; 
    }
    
    @Override
    public String toString(){
        return tipoServicio + " - " + vehiculo.getMatricula() + " - $" + costo;
    }
}
