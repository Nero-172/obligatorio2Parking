package dominio;

import java.io.Serializable;

public class Contrato implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private int numeroContrato;
    private Vehiculo vehiculo;
    private Cliente cliente;
    private Empleado empleado;
    private double valorMensual;
    
    public Contrato(int unNumeroContrato, Vehiculo unVehiculo, Cliente unCliente, Empleado unEmpleado, double unValorMensual){
        this.numeroContrato = unNumeroContrato;
        this.vehiculo = unVehiculo;
        this.cliente = unCliente;
        this.empleado = unEmpleado;
        this.valorMensual = unValorMensual;
    }
    
    // Getters y Setters
    public int getNumeroContrato(){ 
        return numeroContrato; 
    }
    public void setNumeroContrato(int unNumeroContrato){ 
        this.numeroContrato = unNumeroContrato; 
    }
    
    public Vehiculo getVehiculo(){ 
        return vehiculo; 
    }
    public void setVehiculo(Vehiculo unVehiculo){ 
        this.vehiculo = unVehiculo; 
    }
    
    public Cliente getCliente(){ 
        return cliente; 
    }
    public void setCliente(Cliente unCliente){ 
        this.cliente = unCliente; 
    }
    
    public Empleado getEmpleado(){ 
        return empleado; 
    }
    public void setEmpleado(Empleado unEmpleado){ 
        this.empleado = unEmpleado; 
    }
    
    public double getValorMensual(){ 
        return valorMensual; 
    }
    public void setValorMensual(double unValorMensual){ 
        this.valorMensual = unValorMensual; 
    }
    
    @Override
    public String toString(){
        return "Contrato " + numeroContrato + " - " + vehiculo.getMatricula() + " - " + cliente.getNombre();
    }
}
