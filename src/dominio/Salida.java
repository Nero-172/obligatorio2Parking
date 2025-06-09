package dominio;

import java.io.Serializable;

public class Salida implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private Entrada entrada;
    private Empleado empleado;
    private String fecha;
    private String hora;
    private String comentario;
    
    public Salida(Entrada entrada, Empleado empleado, String fecha, String hora, String comentario) {
        this.entrada = entrada;
        this.empleado = empleado;
        this.fecha = fecha;
        this.hora = hora;
        this.comentario = comentario;
    }
    
// Modificar el método calcularTiempoEstadia para tener un solo return
public String calcularTiempoEstadia(){
    String resultado = "";
    try{
        String[] horaEntrada = entrada.getHora().split(":");
        String[] horaSalida = this.hora.split(":");
        
        int minutosEntrada = Integer.parseInt(horaEntrada[0]) * 60 + Integer.parseInt(horaEntrada[1]);
        int minutosSalida = Integer.parseInt(horaSalida[0]) * 60 + Integer.parseInt(horaSalida[1]);
        
        int diferenciaMinutos = minutosSalida - minutosEntrada;
        if (diferenciaMinutos < 0){
            diferenciaMinutos += 24 * 60; // Asume que pasó al día siguiente
        }
        
        int horas = diferenciaMinutos / 60;
        int minutos = diferenciaMinutos % 60;
        
        resultado = horas + " horas y " + minutos + " minutos";
    } catch (Exception e){
        resultado = "Error calculando tiempo";
    }
    return resultado;
}
    
    // Getters y Setters
    public Entrada getEntrada(){ 
        return entrada; 
    }
    public void setEntrada(Entrada entrada){ 
        this.entrada = entrada; 
    }
    
    public Empleado getEmpleado(){ 
        return empleado; 
    }
    public void setEmpleado(Empleado empleado){ 
        this.empleado = empleado; 
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
    
    public String getComentario(){
        return comentario; 
    }
    public void setComentario(String comentario){ 
        this.comentario = comentario; 
    }
    
    @Override
    public String toString(){
        return "Salida - " + entrada.getVehiculo().getMatricula() + " - " + fecha + " " + hora;
    }
}
