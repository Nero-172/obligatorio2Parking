package obligatorio2parking;

import dominio.*;
import java.util.ArrayList;

public class Obligatorio2Parking {

    public static void main(String[] args) {
        // Inicializar el sistema
        Sistema sistema = new Sistema();
        
        // Crear algunos clientes de ejemplo
        sistema.agregarCliente("Juan Pérez", "1234567", "Av. Italia 1234", "099123456", 2020);
        sistema.agregarCliente("María González", "7654321", "Bulevar Artigas 567", "098765432", 2021);
        sistema.agregarCliente("Carlos Rodríguez", "9876543", "18 de Julio 890", "097654321", 2019);
        
        System.out.println("Clientes registrados:");
        for (Cliente cliente : sistema.getClientes()) {
            System.out.println(cliente);
        }
        
        // Crear algunos vehículos de ejemplo
        sistema.agregarVehiculo("ABC123", "Toyota", "Corolla", "Buen estado");
        sistema.agregarVehiculo("DEF456", "Honda", "Civic", "Rayón en puerta izquierda");
        sistema.agregarVehiculo("GHI789", "Ford", "Focus", "Nuevo");
        sistema.agregarVehiculo("JKL012", "Volkswagen", "Golf", "Buen estado");
        
        System.out.println("\nVehículos registrados:");
        for (Vehiculo vehiculo : sistema.getVehiculos()) {
            System.out.println(vehiculo);
        }
        
        // Crear algunos empleados de ejemplo
        sistema.agregarEmpleado("Pedro Martínez", "5555555", "Constituyente 1500", 101);
        sistema.agregarEmpleado("Ana Silva", "6666666", "Rivera 2000", 102);
        sistema.agregarEmpleado("Luis Fernández", "7777777", "Propios 3000", 103);
        
        System.out.println("\nEmpleados registrados:");
        for (Empleado empleado : sistema.getEmpleados()) {
            System.out.println(empleado);
        }
        
        // Crear contratos
        Cliente cliente1 = sistema.buscarClientePorCedula("1234567");
        Cliente cliente2 = sistema.buscarClientePorCedula("7654321");
        Vehiculo vehiculo1 = sistema.buscarVehiculoPorMatricula("ABC123");
        Vehiculo vehiculo2 = sistema.buscarVehiculoPorMatricula("DEF456");
        Vehiculo vehiculo3 = sistema.buscarVehiculoPorMatricula("GHI789");
        Empleado empleado1 = sistema.buscarEmpleadoPorNumero(101);
        
        sistema.agregarContrato(vehiculo1, cliente1, empleado1, 5000.0);
        sistema.agregarContrato(vehiculo2, cliente1, empleado1, 4500.0);
        sistema.agregarContrato(vehiculo3, cliente2, empleado1, 4800.0);
        
        System.out.println("\nContratos registrados:");
        for (Contrato contrato : sistema.getContratos()) {
            System.out.println(contrato);
        }
        
        // Registrar entradas
        sistema.agregarEntrada(vehiculo1, "2025-05-20", "08:30", "Sin observaciones", empleado1);
        sistema.agregarEntrada(vehiculo2, "2025-05-20", "09:15", "Tanque casi vacío", empleado1);
        sistema.agregarEntrada(vehiculo3, "2025-05-20", "10:00", "Sin observaciones", empleado1);
        
        System.out.println("\nEntradas registradas:");
        for (Entrada entrada : sistema.getEntradas()) {
            System.out.println(entrada);
            // Verificar si tiene contrato
            Contrato contrato = sistema.buscarContratoPorVehiculo(entrada.getVehiculo());
            if (contrato != null) {
                System.out.println("  Tiene contrato: " + contrato.getNumeroContrato());
            } else {
                System.out.println("  No tiene contrato");
            }
        }
        
        // Registrar salidas
        Entrada entradaVehiculo1 = null;
        for (Entrada entrada : sistema.getEntradas()) {
            if (entrada.getVehiculo().equals(vehiculo1)) {
                entradaVehiculo1 = entrada;
            }
        }
        
        if (entradaVehiculo1 != null) {
            sistema.agregarSalida(entradaVehiculo1, empleado1, "2025-05-20", "14:45", "Sin observaciones");
            
            System.out.println("\nSalidas registradas:");
            for (Salida salida : sistema.getSalidas()) {
                System.out.println(salida);
                System.out.println("  Tiempo de estadía: " + salida.calcularTiempoEstadia());
                
                // Verificar si tiene contrato
                Contrato contrato = sistema.buscarContratoPorVehiculo(salida.getEntrada().getVehiculo());
                if (contrato != null) {
                    System.out.println("  Tiene contrato: " + contrato.getNumeroContrato());
                } else {
                    System.out.println("  No tiene contrato");
                }
            }
        }
        
        // Registrar servicios adicionales
        sistema.agregarServicioAdicional("lavado", "2025-05-20", "11:30", vehiculo2, empleado1, 800.0);
        sistema.agregarServicioAdicional("cambio de rueda", "2025-05-20", "12:15", vehiculo3, empleado1, 1200.0);
        
        System.out.println("\nServicios adicionales registrados:");
        for (ServicioAdicional servicio : sistema.getServiciosAdicionales()) {
            System.out.println(servicio);
        }
        
        // Mostrar historial de un vehículo
        System.out.println("\nHistorial del vehículo " + vehiculo1.getMatricula() + ":");
        ArrayList<Object> historial = sistema.getHistorialVehiculo(vehiculo1);
        for (Object movimiento : historial) {
            System.out.println("  " + movimiento);
        }
        
        // Mostrar movimientos por fecha y hora
        System.out.println("\nMovimientos del 2025-05-20 entre las 8:00 y las 12:00:");
        ArrayList<Object> movimientos = sistema.getMovimientosPorFechaYHora("2025-05-20", 8, 12);
        for (Object movimiento : movimientos) {
            System.out.println("  " + movimiento);
        }
        
        // Mostrar estadísticas
        System.out.println("\nServicio más utilizado: " + sistema.getServicioMasUtilizado());
        
        Cliente clienteConMasVehiculos = sistema.getClienteConMasVehiculos();
        if (clienteConMasVehiculos != null) {
            System.out.println("Cliente con más vehículos: " + clienteConMasVehiculos.getNombre());
        }
        
        // Guardar datos
        System.out.println("\nGuardando datos...");
        sistema.grabarDatos();
        
        // Recuperar datos
        System.out.println("Recuperando datos...");
        Sistema sistemaRecuperado = Sistema.recuperarDatos();
        
        System.out.println("Clientes recuperados: " + sistemaRecuperado.getClientes().size());
        System.out.println("Vehículos recuperados: " + sistemaRecuperado.getVehiculos().size());
        System.out.println("Empleados recuperados: " + sistemaRecuperado.getEmpleados().size());
        System.out.println("Contratos recuperados: " + sistemaRecuperado.getContratos().size());
        System.out.println("Entradas recuperadas: " + sistemaRecuperado.getEntradas().size());
        System.out.println("Salidas recuperadas: " + sistemaRecuperado.getSalidas().size());
        System.out.println("Servicios adicionales recuperados: " + sistemaRecuperado.getServiciosAdicionales().size());
    }
}
