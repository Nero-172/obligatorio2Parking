/*
 * Autores: [Tu nombre] - [Tu número de estudiante]
 *          [Nombre compañero] - [Número de estudiante compañero]
 */
package dominio;

import java.io.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Sistema implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private ArrayList<Cliente> clientes;
    private ArrayList<Vehiculo> vehiculos;
    private ArrayList<Empleado> empleados;
    private ArrayList<Contrato> contratos;
    private ArrayList<Entrada> entradas;
    private ArrayList<Salida> salidas;
    private ArrayList<ServicioAdicional> serviciosAdicionales;
    private int proximoNumeroContrato;
    
    public Sistema(){
        this.clientes = new ArrayList<>();
        this.vehiculos = new ArrayList<>();
        this.empleados = new ArrayList<>();
        this.contratos = new ArrayList<>();
        this.entradas = new ArrayList<>();
        this.salidas = new ArrayList<>();
        this.serviciosAdicionales = new ArrayList<>();
        this.proximoNumeroContrato = 1;
    }
    
    // Métodos para Cliente
    public boolean agregarCliente(String nombre, String cedula, String direccion, String celular, int anioCliente){
        boolean resultado = false;
        if (buscarClientePorCedula(cedula) == null){
            Cliente cliente = new Cliente(nombre, cedula, direccion, celular, anioCliente);
            resultado = clientes.add(cliente);
        }
        return resultado;
    }
    
    public boolean eliminarCliente(String cedula){
        boolean resultado = false;
        Cliente cliente = buscarClientePorCedula(cedula);
        if (cliente != null){
            // Eliminar contratos del cliente
            contratos.removeIf(contrato -> contrato.getCliente().equals(cliente));
            resultado = clientes.remove(cliente);
        }
        return resultado;
    }
    
    public Cliente buscarClientePorCedula(String cedula){
        Cliente clienteEncontrado = null;
        for (Cliente cliente : clientes){
            if (cliente.getCedula().equals(cedula)){
                clienteEncontrado = cliente;
            }
        }
        return clienteEncontrado;
    }
    
    // Métodos para Vehículo
    public boolean agregarVehiculo(String matricula, String marca, String modelo, String estado){
        boolean resultado = false;
        if (buscarVehiculoPorMatricula(matricula) == null){
            Vehiculo vehiculo = new Vehiculo(matricula, marca, modelo, estado);
            resultado = vehiculos.add(vehiculo);
        }
        return resultado;
    }
    
    public Vehiculo buscarVehiculoPorMatricula(String matricula){
        Vehiculo vehiculoEncontrado = null;
        for (Vehiculo vehiculo : vehiculos){
            if (vehiculo.getMatricula().equals(matricula)){
                vehiculoEncontrado = vehiculo;
            }
        }
        return vehiculoEncontrado;
    }
    
    // Métodos para Empleado
    public boolean agregarEmpleado(String nombre, String cedula, String direccion, int numeroEmpleado){
        boolean resultado = false;
        if (buscarEmpleadoPorCedula(cedula) == null && buscarEmpleadoPorNumero(numeroEmpleado) == null){
            Empleado empleado = new Empleado(nombre, cedula, direccion, numeroEmpleado);
            resultado = empleados.add(empleado);
        }
        return resultado;
    }
    
    public Empleado buscarEmpleadoPorCedula(String cedula){
        Empleado empleadoEncontrado = null;
        for (Empleado empleado : empleados){
            if (empleado.getCedula().equals(cedula)){
                empleadoEncontrado = empleado;
            }
        }
        return empleadoEncontrado;
    }
    
    public Empleado buscarEmpleadoPorNumero(int numeroEmpleado){
        Empleado empleadoEncontrado = null;
        for (Empleado empleado : empleados){
            if (empleado.getNumeroEmpleado() == numeroEmpleado){
                empleadoEncontrado = empleado;
            }
        }
        return empleadoEncontrado;
    }
    
    // Métodos para Contrato
    public boolean agregarContrato(Vehiculo vehiculo, Cliente cliente, Empleado empleado, double valorMensual){
        Contrato contrato = new Contrato(proximoNumeroContrato, vehiculo, cliente, empleado, valorMensual);
        proximoNumeroContrato++;
        return contratos.add(contrato);
    }
    
    public Contrato buscarContratoPorVehiculo(Vehiculo vehiculo){
        Contrato contratoEncontrado = null;
        for (Contrato contrato : contratos){
            if (contrato.getVehiculo().equals(vehiculo)){
                contratoEncontrado = contrato;
            }
        }
        return contratoEncontrado;
    }
    
    // Métodos para Entrada
    public boolean agregarEntrada(Vehiculo vehiculo, String fecha, String hora, String notas, Empleado empleado){
        boolean resultado = false;
        if (!vehiculoEstaEnParking(vehiculo)){
            Entrada entrada = new Entrada(vehiculo, fecha, hora, notas, empleado);
            resultado = entradas.add(entrada);
        }
        return resultado;
    }
    
    public boolean vehiculoEstaEnParking(Vehiculo vehiculo){
        boolean estaEnParking = false;
        for (Entrada entrada : entradas){
            if (entrada.getVehiculo().equals(vehiculo) && !tieneSalida(entrada)){
                estaEnParking = true;
            }
        }
        return estaEnParking;
    }
    
    public boolean tieneSalida(Entrada entrada){
        boolean tieneSalida = false;
        for (Salida salida : salidas) {
            if (salida.getEntrada().equals(entrada)){
                tieneSalida = true;
            }
        }
        return tieneSalida;
    }
    
    // Métodos para Salida
    public boolean agregarSalida(Entrada entrada, Empleado empleado, String fecha, String hora, String comentario){
        boolean resultado = false;
        if (!tieneSalida(entrada)){
            Salida salida = new Salida(entrada, empleado, fecha, hora, comentario);
            resultado = salidas.add(salida);
        }
        return resultado;
    }
    
    public ArrayList<Entrada> getEntradasSinSalida(){
        ArrayList<Entrada> entradasSinSalida = new ArrayList<>();
        for (Entrada entrada : entradas){
            if (!tieneSalida(entrada)){
                entradasSinSalida.add(entrada);
            }
        }
        return entradasSinSalida;
    }
    
    // Métodos para Servicio Adicional
    public boolean agregarServicioAdicional(String tipoServicio, String fecha, String hora, Vehiculo vehiculo, Empleado empleado, double costo){
        ServicioAdicional servicio = new ServicioAdicional(tipoServicio, fecha, hora, vehiculo, empleado, costo);
        return serviciosAdicionales.add(servicio);
    }
    
    // Métodos para Reportes
    public ArrayList<Object> getHistorialVehiculo(Vehiculo vehiculo){
        ArrayList<Object> historial = new ArrayList<>();
        
        for (Entrada entrada : entradas){
            if (entrada.getVehiculo().equals(vehiculo)){
                historial.add(entrada);
            }
        }
        
        for (Salida salida : salidas){
            if (salida.getEntrada().getVehiculo().equals(vehiculo)){
                historial.add(salida);
            }
        }
        
        for (ServicioAdicional servicio : serviciosAdicionales){
            if (servicio.getVehiculo().equals(vehiculo)){
                historial.add(servicio);
            }
        }
        
        return historial;
    }
    
    public ArrayList<Object> getMovimientosPorFechaYHora(String fecha, int horaInicio, int horaFin){
        ArrayList<Object> movimientos = new ArrayList<>();
        
        for (Entrada entrada : entradas){
            if (entrada.getFecha().equals(fecha)){
                int hora = Integer.parseInt(entrada.getHora().split(":")[0]);
                if (hora >= horaInicio && hora <= horaFin){
                    movimientos.add(entrada);
                }
            }
        }
        
        for (Salida salida : salidas){
            if (salida.getFecha().equals(fecha)) {
                int hora = Integer.parseInt(salida.getHora().split(":")[0]);
                if (hora >= horaInicio && hora <= horaFin){
                    movimientos.add(salida);
                }
            }
        }
        
        for (ServicioAdicional servicio : serviciosAdicionales){
            if (servicio.getFecha().equals(fecha)) {
                int hora = Integer.parseInt(servicio.getHora().split(":")[0]);
                if (hora >= horaInicio && hora <= horaFin){
                    movimientos.add(servicio);
                }
            }
        }
        
        return movimientos;
    }
    
    public String getServicioMasUtilizado(){
        HashMap<String, Integer> conteoServicios = new HashMap<>();
        
        for (ServicioAdicional servicio : serviciosAdicionales){
            String tipo = servicio.getTipoServicio();
            conteoServicios.put(tipo, conteoServicios.getOrDefault(tipo, 0) + 1);
        }
        
        String servicioMasUtilizado = "";
        int maxUsos = 0;
        
        for (Map.Entry<String, Integer> entry : conteoServicios.entrySet()){
            if (entry.getValue() > maxUsos){
                maxUsos = entry.getValue();
                servicioMasUtilizado = entry.getKey();
            }
        }
        
        return servicioMasUtilizado;
    }
    
    public Cliente getClienteConMasVehiculos(){
        HashMap<Cliente, Integer> conteoVehiculos = new HashMap<>();
        
        for (Contrato contrato : contratos){
            Cliente cliente = contrato.getCliente();
            conteoVehiculos.put(cliente, conteoVehiculos.getOrDefault(cliente, 0) + 1);
        }
        
        Cliente clienteConMas = null;
        int maxVehiculos = 0;
        
        for (Map.Entry<Cliente, Integer> entry : conteoVehiculos.entrySet()){
            if (entry.getValue() > maxVehiculos){
                maxVehiculos = entry.getValue();
                clienteConMas = entry.getKey();
            }
        }
        
        return clienteConMas;
    }
    
    // Métodos de serialización
    public void grabarDatos(){
        try{
            FileOutputStream fileOut = new FileOutputStream("DATOS.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public static Sistema recuperarDatos(){
        Sistema sistema = new Sistema();
        try {
            FileInputStream fileIn = new FileInputStream("DATOS.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            sistema = (Sistema) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e){
            sistema = new Sistema(); // Retorna sistema nuevo si no hay datos
        }
        return sistema;
    }
    
    // Getters
    public ArrayList<Cliente> getClientes(){ 
        return clientes; 
    }
    public ArrayList<Vehiculo> getVehiculos(){ 
        return vehiculos; 
    }
    public ArrayList<Empleado> getEmpleados(){ 
        return empleados; 
    }
    public ArrayList<Contrato> getContratos(){ 
        return contratos; 
    }
    public ArrayList<Entrada> getEntradas(){ 
        return entradas; 
    }
    public ArrayList<Salida> getSalidas(){ 
        return salidas; 
    }
    public ArrayList<ServicioAdicional> getServiciosAdicionales(){ 
        return serviciosAdicionales;
    }
}
