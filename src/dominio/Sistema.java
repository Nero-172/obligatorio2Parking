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
    public boolean agregarCliente(String unNombre, String unaCedula, String unaDireccion, String unCelular, int unAnioCliente){
        boolean resultado = false;
        if (buscarClientePorCedula(unaCedula) == null){
            Cliente cliente = new Cliente(unNombre, unaCedula, unaDireccion, unCelular, unAnioCliente);
            resultado = clientes.add(cliente);
        }
        return resultado;
    }
    
    public boolean eliminarCliente(String cedula){
        boolean resultado = false;
        Cliente cliente = buscarClientePorCedula(cedula);
        if (cliente != null){
            for (int i = contratos.size() - 1; i >= 0; i--) {
                if (contratos.get(i).getCliente().equals(cliente)) {
                    contratos.remove(i);
                }
            }
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
    public boolean agregarVehiculo(String unaMatricula, String unaMarca, String unModelo, String unEstado){
        boolean resultado = false;
        if (buscarVehiculoPorMatricula(unaMatricula) == null){
            Vehiculo vehiculo = new Vehiculo(unaMatricula, unaMarca, unModelo, unEstado);
            resultado = vehiculos.add(vehiculo);
        }
        return resultado;
    }
    
    public Vehiculo buscarVehiculoPorMatricula(String unaMatricula){
        Vehiculo vehiculoEncontrado = null;
        for (Vehiculo vehiculo : vehiculos){
            if (vehiculo.getMatricula().equals(unaMatricula)){
                vehiculoEncontrado = vehiculo;
            }
        }
        return vehiculoEncontrado;
    }
    
    // Métodos para Empleado
    public boolean agregarEmpleado(String unNombreE, String unaCedulaE, String unaDireccionE, int unNumeroDeEmpleado){
        boolean resultado = false;
        if (buscarEmpleadoPorCedula(unaCedulaE) == null && buscarEmpleadoPorNumero(unNumeroDeEmpleado) == null){
            Empleado empleado = new Empleado(unNombreE, unaCedulaE, unaDireccionE, unNumeroDeEmpleado);
            resultado = empleados.add(empleado);
        }
        return resultado;
    }
    
    public Empleado buscarEmpleadoPorCedula(String cedulaE){
        Empleado empleadoEncontrado = null;
        for (Empleado empleado : empleados){
            if (empleado.getCedula().equals(cedulaE)){
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
    public boolean agregarContrato(Vehiculo unVehiculo, Cliente unClienteC, Empleado unEmpleadoC, double unValorMensual){
        Contrato contrato = new Contrato(proximoNumeroContrato, unVehiculo, unClienteC, unEmpleadoC, unValorMensual);
        proximoNumeroContrato++;
        return contratos.add(contrato);
    }
    
    public Contrato buscarContratoPorVehiculo(Vehiculo vehiculoC){
        Contrato contratoEncontrado = null;
        for (Contrato contrato : contratos){
            if (contrato.getVehiculo().equals(vehiculoC)){
                contratoEncontrado = contrato;
            }
        }
        return contratoEncontrado;
    }
    
    // Métodos para Entrada
    public boolean agregarEntrada(Vehiculo vehiculoEntrada, String fechaEntrada, String horaEntrada, String notaEntrada, Empleado empleadoDeEntrada){
        boolean resultado = false;
        if (!vehiculoEstaEnParking(vehiculoEntrada)){
            Entrada entrada = new Entrada(vehiculoEntrada, fechaEntrada, horaEntrada, notaEntrada, empleadoDeEntrada);
            resultado = entradas.add(entrada);
        }
        return resultado;
    }
    
    public boolean vehiculoEstaEnParking(Vehiculo vehiculoP){
        boolean estaEnParking = false;
        for (Entrada entrada : entradas){
            if (entrada.getVehiculo().equals(vehiculoP) && !tieneSalida(entrada)){
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
    public boolean agregarSalida(Entrada entrada, Empleado empleadoSalida, String fechaSalida, String horaSalida, String comentario){
        boolean resultado = false;
        if (!tieneSalida(entrada)){
            Salida salida = new Salida(entrada, empleadoSalida, fechaSalida, horaSalida, comentario);
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
    public boolean agregarServicioAdicional(String tipoServicio, String fechaServicio, String horaServicio, Vehiculo vehiculoServicio, Empleado empleadoServicio, double costoDelServicio){
        ServicioAdicional servicio = new ServicioAdicional(tipoServicio, fechaServicio, horaServicio, vehiculoServicio, empleadoServicio, costoDelServicio);
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
