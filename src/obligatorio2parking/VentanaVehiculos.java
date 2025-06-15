package obligatorio2parking;

import dominio.Vehiculo;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Ventana para gestión de vehículos
 * @author USUARIO
 */
public class VentanaVehiculos extends javax.swing.JFrame {
    
    // Lista para almacenar los vehículos
    private List<Vehiculo> vehiculos;
    private DefaultListModel<String> modeloLista;

    /**
     * Creates new form VentanaVehiculos
     */
    public VentanaVehiculos() {
        // Inicializar estructuras de datos
        vehiculos = new ArrayList<>();
        modeloLista = new DefaultListModel<>();
        
        initComponents();
        configurarEventos();
        
        // Configurar la lista
        listaVehiculos.setModel(modeloLista);
        
        // Hacer que el área de texto no sea editable
        txtAreaVehiculo.setEditable(false);
    }
    
    /**
     * Configura los eventos de los botones
     */
    private void configurarEventos() {
        // Evento para agregar vehículo
        btnAgregarV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarVehiculo();
            }
        });
        
        // Evento para eliminar vehículo
        btnElminarV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarVehiculo();
            }
        });
        
        // Evento para vaciar lista
        btnVaciarV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vaciarLista();
            }
        });
        
        // Evento para mostrar detalles
        btnMostrarDetallesVehiculos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarDetalles();
            }
        });
        
        // Evento para selección en la lista
        listaVehiculos.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                mostrarDetallesSeleccionado();
            }
        });
    }
    
    /**
     * Agrega un nuevo vehículo a la lista
     */
    private void agregarVehiculo() {
        try {
            // Obtener datos de los campos de texto
            String matricula = txtMatricula.getText().trim();
            String marca = txtMarca.getText().trim();
            String modelo = txtModelo.getText().trim();
            String estado = txtEstado.getText().trim();
            
            // Validar que los campos no estén vacíos
            if (matricula.isEmpty() || marca.isEmpty() || modelo.isEmpty() || estado.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "Todos los campos son obligatorios", 
                    "Error de validación", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Verificar que la matrícula no exista
            for (Vehiculo v : vehiculos) {
                if (v.getMatricula().equalsIgnoreCase(matricula)) {
                    JOptionPane.showMessageDialog(this, 
                        "Ya existe un vehículo con esa matrícula", 
                        "Error de duplicado", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            
            // Crear nuevo vehículo
            Vehiculo nuevoVehiculo = new Vehiculo(matricula, marca, modelo, estado);
            
            // Agregar a la lista
            vehiculos.add(nuevoVehiculo);
            modeloLista.addElement(nuevoVehiculo.toString());
            
            // Limpiar campos
            limpiarCampos();
            
            JOptionPane.showMessageDialog(this, 
                "Vehículo agregado exitosamente", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
                
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Error al agregar vehículo: " + ex.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Elimina el vehículo seleccionado
     */
    private void eliminarVehiculo() {
        int indiceSeleccionado = listaVehiculos.getSelectedIndex();
        
        if (indiceSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, 
                "Seleccione un vehículo para eliminar", 
                "Advertencia", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Confirmar eliminación
        int confirmacion = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de que desea eliminar este vehículo?", 
            "Confirmar eliminación", 
            JOptionPane.YES_NO_OPTION);
            
        if (confirmacion == JOptionPane.YES_OPTION) {
            // Eliminar de ambas listas
            vehiculos.remove(indiceSeleccionado);
            modeloLista.removeElementAt(indiceSeleccionado);
            
            // Limpiar área de detalles
            txtAreaVehiculo.setText("");
            
            JOptionPane.showMessageDialog(this, 
                "Vehículo eliminado exitosamente", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
     * Vacía toda la lista de vehículos
     */
    private void vaciarLista() {
        if (vehiculos.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "La lista ya está vacía", 
                "Información", 
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        // Confirmar vaciado
        int confirmacion = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de que desea eliminar todos los vehículos?", 
            "Confirmar vaciado", 
            JOptionPane.YES_NO_OPTION);
            
        if (confirmacion == JOptionPane.YES_OPTION) {
            vehiculos.clear();
            modeloLista.clear();
            txtAreaVehiculo.setText("");
            limpiarCampos();
            
            JOptionPane.showMessageDialog(this, 
                "Lista vaciada exitosamente", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /**
     * Muestra los detalles de todos los vehículos
     */
    private void mostrarDetalles() {
        if (vehiculos.isEmpty()) {
            txtAreaVehiculo.setText("No hay vehículos registrados.");
            return;
        }
        
        StringBuilder detalles = new StringBuilder();
        detalles.append("=== LISTA COMPLETA DE VEHÍCULOS ===\n\n");
        
        for (int i = 0; i < vehiculos.size(); i++) {
            Vehiculo v = vehiculos.get(i);
            detalles.append("Vehículo ").append(i + 1).append(":\n");
            detalles.append("  Matrícula: ").append(v.getMatricula()).append("\n");
            detalles.append("  Marca: ").append(v.getMarca()).append("\n");
            detalles.append("  Modelo: ").append(v.getModelo()).append("\n");
            detalles.append("  Estado: ").append(v.getEstado()).append("\n");
            detalles.append("------------------------\n");
        }
        
        detalles.append("\nTotal de vehículos: ").append(vehiculos.size());
        txtAreaVehiculo.setText(detalles.toString());
        txtAreaVehiculo.setCaretPosition(0); // Ir al inicio del texto
    }
    
    /**
     * Muestra los detalles del vehículo seleccionado
     */
    private void mostrarDetallesSeleccionado() {
        int indiceSeleccionado = listaVehiculos.getSelectedIndex();
        
        if (indiceSeleccionado == -1) {
            return;
        }
        
        Vehiculo vehiculoSeleccionado = vehiculos.get(indiceSeleccionado);
        
        StringBuilder detalles = new StringBuilder();
        detalles.append("=== DETALLES DEL VEHÍCULO SELECCIONADO ===\n\n");
        detalles.append("Matrícula: ").append(vehiculoSeleccionado.getMatricula()).append("\n");
        detalles.append("Marca: ").append(vehiculoSeleccionado.getMarca()).append("\n");
        detalles.append("Modelo: ").append(vehiculoSeleccionado.getModelo()).append("\n");
        detalles.append("Estado: ").append(vehiculoSeleccionado.getEstado()).append("\n");
        
        txtAreaVehiculo.setText(detalles.toString());
    }
    
    /**
     * Limpia todos los campos de entrada
     */
    private void limpiarCampos() {
        txtMatricula.setText("");
        txtMarca.setText("");
        txtModelo.setText("");
        txtEstado.setText("");
        txtMatricula.requestFocus(); // Poner el foco en el primer campo
    }
    
    /**
     * Obtiene la lista de vehículos (útil para integración con otras clases)
     */
    public List<Vehiculo> getVehiculos() {
        return new ArrayList<>(vehiculos); // Retorna una copia para evitar modificaciones externas
    }
    
    /**
     * Establece una lista de vehículos (útil para cargar datos externos)
     */
    public void setVehiculos(List<Vehiculo> vehiculos) {
        this.vehiculos.clear();
        this.modeloLista.clear();
        
        if (vehiculos != null) {
            for (Vehiculo v : vehiculos) {
                this.vehiculos.add(v);
                this.modeloLista.addElement(v.toString());
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        lblMatricula = new javax.swing.JLabel();
        lblMarca = new javax.swing.JLabel();
        lblModelo = new javax.swing.JLabel();
        txtMatricula = new javax.swing.JTextField();
        txtMarca = new javax.swing.JTextField();
        txtModelo = new javax.swing.JTextField();
        lblEstado = new javax.swing.JLabel();
        txtEstado = new javax.swing.JTextField();
        panelScrollVehiculos = new javax.swing.JScrollPane();
        listaVehiculos = new javax.swing.JList<>();
        lblVehiculos = new javax.swing.JLabel();
        btnVaciarV = new javax.swing.JButton();
        btnAgregarV = new javax.swing.JButton();
        btnElminarV = new javax.swing.JButton();
        scrollDetallesVehiculos = new javax.swing.JScrollPane();
        txtAreaVehiculo = new javax.swing.JTextArea();
        lblDetalles = new javax.swing.JLabel();
        btnMostrarDetallesVehiculos = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión de Vehículos");

        lblMatricula.setText("Matrícula:");

        lblMarca.setText("Marca:");

        lblModelo.setText("Modelo:");

        txtMatricula.setColumns(4);

        txtMarca.setColumns(4);

        txtModelo.setColumns(4);

        lblEstado.setText("Estado:");

        txtEstado.setColumns(4);

        panelScrollVehiculos.setViewportView(listaVehiculos);

        lblVehiculos.setText("Vehículos:");

        btnVaciarV.setText("Vaciar");

        btnAgregarV.setText("Agregar");

        btnElminarV.setText("Eliminar");

        txtAreaVehiculo.setColumns(20);
        txtAreaVehiculo.setRows(5);
        scrollDetallesVehiculos.setViewportView(txtAreaVehiculo);

        lblDetalles.setText("Detalles de Vehículo:");

        btnMostrarDetallesVehiculos.setText("Mostrar Detalles");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnVaciarV, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAgregarV, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnElminarV, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnMostrarDetallesVehiculos))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(lblEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelScrollVehiculos, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblVehiculos))
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scrollDetallesVehiculos, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDetalles))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblVehiculos)
                    .addComponent(lblDetalles))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(scrollDetallesVehiculos)
                    .addComponent(panelScrollVehiculos, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblMatricula)
                                .addGap(18, 18, 18)
                                .addComponent(lblMarca)
                                .addGap(24, 24, 24)
                                .addComponent(lblModelo))
                            .addComponent(lblEstado))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnVaciarV)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAgregarV)
                        .addComponent(btnElminarV)
                        .addComponent(btnMostrarDetallesVehiculos)))
                .addGap(33, 33, 33))
        );

        setBounds(0, 0, 772, 342);
    }// </editor-fold>                        

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaVehiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaVehiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaVehiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaVehiculos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaVehiculos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnAgregarV;
    private javax.swing.JButton btnElminarV;
    private javax.swing.JButton btnMostrarDetallesVehiculos;
    private javax.swing.JButton btnVaciarV;
    private javax.swing.JLabel lblDetalles;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblMarca;
    private javax.swing.JLabel lblMatricula;
    private javax.swing.JLabel lblModelo;
    private javax.swing.JLabel lblVehiculos;
    private javax.swing.JList<String> listaVehiculos;
    private javax.swing.JScrollPane panelScrollVehiculos;
    private javax.swing.JScrollPane scrollDetallesVehiculos;
    private javax.swing.JTextArea txtAreaVehiculo;
    private javax.swing.JTextField txtEstado;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtMatricula;
    private javax.swing.JTextField txtModelo;
    // End of variables declaration                   
}