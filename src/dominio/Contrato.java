package dominio;

import java.io.Serializable;

public class Contrato implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private int numeroContrato;
    private Vehiculo vehiculo;
    private Cliente cliente;
    private Empleado empleado;
    private double valorMensual;
    
    public Contrato(int numeroContrato, Vehiculo vehiculo, Cliente cliente, Empleado empleado, double valorMensual){
        this.numeroContrato = numeroContrato;
        this.vehiculo = vehiculo;
        this.cliente = cliente;
        this.empleado = empleado;
        this.valorMensual = valorMensual;
    }
    
    // Getters y Setters
    public int getNumeroContrato(){ 
        return numeroContrato; 
    }
    public void setNumeroContrato(int numeroContrato){ 
        this.numeroContrato = numeroContrato; 
    }
    
    public Vehiculo getVehiculo(){ 
        return vehiculo; 
    }
    public void setVehiculo(Vehiculo vehiculo){ 
        this.vehiculo = vehiculo; 
    }
    
    public Cliente getCliente(){ 
        return cliente; 
    }
    public void setCliente(Cliente cliente){ 
        this.cliente = cliente; 
    }
    
    public Empleado getEmpleado(){ 
        return empleado; 
    }
    public void setEmpleado(Empleado empleado){ 
        this.empleado = empleado; 
    }
    
    public double getValorMensual(){ 
        return valorMensual; 
    }
    public void setValorMensual(double valorMensual){ 
        this.valorMensual = valorMensual; 
    }
    
    @Override
    public String toString(){
        return "Contrato " + numeroContrato + " - " + vehiculo.getMatricula() + " - " + cliente.getNombre();
    }
}
