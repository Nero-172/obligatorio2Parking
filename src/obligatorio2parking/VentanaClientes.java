package obligatorio2parking;

import dominio.Cliente;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaClientes extends javax.swing.JFrame {
    
    // Lista para almacenar los clientes
    private List<Cliente> clientes;
    private DefaultListModel<String> modeloLista;

    public VentanaClientes() {
        initComponents();
        inicializarComponentes();
    }
    
    private void inicializarComponentes() {
        // Inicializar la lista de clientes y el modelo de la lista
        clientes = new ArrayList<>();
        modeloLista = new DefaultListModel<>();
        listaClientes.setModel(modeloLista);
        
        // Agregar listeners a los botones
        btnAgregar.addActionListener(e -> agregarCliente());
        btnEliminar.addActionListener(e -> eliminarCliente());
        btnVaciar.addActionListener(e -> vaciarLista());
        btnDetallesClientes.addActionListener(e -> mostrarDetalles());
        
        // Agregar listener para selección en la lista
        listaClientes.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                mostrarDetalles();
            }
        });
        
        // Hacer que el área de texto no sea editable
        txtAreaClientes.setEditable(false);
    }
    
    private void agregarCliente() {
        try {
            // Validar que todos los campos estén llenos
            if (txtNombre.getText().trim().isEmpty() || 
                txtCedula.getText().trim().isEmpty() || 
                txtDireccion.getText().trim().isEmpty() || 
                txtCelular.getText().trim().isEmpty() || 
                txtAnio.getText().trim().isEmpty()) {
                
                JOptionPane.showMessageDialog(this, 
                    "Por favor, complete todos los campos.", 
                    "Campos Vacíos", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Obtener datos de los campos
            String nombre = txtNombre.getText().trim();
            String cedula = txtCedula.getText().trim();
            String direccion = txtDireccion.getText().trim();
            String celular = txtCelular.getText().trim();
            int anio = Integer.parseInt(txtAnio.getText().trim());
            
            // Crear nuevo cliente
            Cliente nuevoCliente = new Cliente(nombre, cedula, direccion, celular, anio);
            
            // Verificar si el cliente ya existe (por cédula)
            if (clientes.contains(nuevoCliente)) {
                JOptionPane.showMessageDialog(this, 
                    "Ya existe un cliente con esa cédula.", 
                    "Cliente Duplicado", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Agregar cliente a la lista
            clientes.add(nuevoCliente);
            modeloLista.addElement(nuevoCliente.toString());
            
            // Limpiar campos
            limpiarCampos();
            
            JOptionPane.showMessageDialog(this, 
                "Cliente agregado exitosamente.", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
                
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "El año debe ser un número válido.", 
                "Error de Formato", 
                JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al agregar cliente: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void eliminarCliente() {
        int indiceSeleccionado = listaClientes.getSelectedIndex();
        
        if (indiceSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, 
                "Por favor, seleccione un cliente para eliminar.", 
                "Sin Selección", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        // Confirmar eliminación
        int respuesta = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de que desea eliminar este cliente?", 
            "Confirmar Eliminación", 
            JOptionPane.YES_NO_OPTION);
            
        if (respuesta == JOptionPane.YES_OPTION) {
            // Eliminar cliente de ambas listas
            clientes.remove(indiceSeleccionado);
            modeloLista.removeElementAt(indiceSeleccionado);
            
            // Limpiar área de detalles
            txtAreaClientes.setText("");
            
            JOptionPane.showMessageDialog(this, 
                "Cliente eliminado exitosamente.", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void vaciarLista() {
        if (clientes.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "La lista ya está vacía.", 
                "Lista Vacía", 
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        // Confirmar vaciado
        int respuesta = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de que desea eliminar todos los clientes?", 
            "Confirmar Vaciado", 
            JOptionPane.YES_NO_OPTION);
            
        if (respuesta == JOptionPane.YES_OPTION) {
            // Vaciar ambas listas
            clientes.clear();
            modeloLista.clear();
            
            // Limpiar área de detalles
            txtAreaClientes.setText("");
            
            JOptionPane.showMessageDialog(this, 
                "Lista vaciada exitosamente.", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void mostrarDetalles() {
        int indiceSeleccionado = listaClientes.getSelectedIndex();
        
        if (indiceSeleccionado == -1) {
            txtAreaClientes.setText("");
            return;
        }
        
        // Mostrar detalles del cliente seleccionado
        Cliente clienteSeleccionado = clientes.get(indiceSeleccionado);
        txtAreaClientes.setText(clienteSeleccionado.getDetallesCompletos());
    }
    
    private void limpiarCampos() {
        txtNombre.setText("");
        txtCedula.setText("");
        txtDireccion.setText("");
        txtCelular.setText("");
        txtAnio.setText("");
        txtNombre.requestFocus(); // Poner el foco en el primer campo
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        lblNombre = new javax.swing.JLabel();
        lblDireccion = new javax.swing.JLabel();
        lblCelular = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtDireccion = new javax.swing.JTextField();
        txtCelular = new javax.swing.JTextField();
        lblCedula = new javax.swing.JLabel();
        txtCedula = new javax.swing.JTextField();
        txtAnio = new javax.swing.JTextField();
        lblAnio = new javax.swing.JLabel();
        lblClientes = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaClientes = new javax.swing.JList<>();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnVaciar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAreaClientes = new javax.swing.JTextArea();
        lblDetalles = new javax.swing.JLabel();
        btnDetallesClientes = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestión de Clientes");

        lblNombre.setText("Nombre:");
        lblDireccion.setText("Dirección:");
        lblCelular.setText("Celular:");
        txtNombre.setColumns(4);
        txtDireccion.setColumns(4);
        txtCelular.setColumns(4);
        lblCedula.setText("Cédula:");
        txtCedula.setColumns(4);
        txtAnio.setColumns(4);
        lblAnio.setText("Año:");
        lblClientes.setText("Clientes:");
        jScrollPane1.setViewportView(listaClientes);
        btnAgregar.setText("Agregar");
        btnEliminar.setText("Eliminar");
        btnVaciar.setText("Vaciar");
        txtAreaClientes.setColumns(20);
        txtAreaClientes.setRows(5);
        jScrollPane2.setViewportView(txtAreaClientes);
        lblDetalles.setText("Detalles:");
        btnDetallesClientes.setText("Mostrar Detalles");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnVaciar)
                        .addGap(18, 18, 18)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtNombre))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lblCelular)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lblDireccion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(91, 91, 91)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblCedula)
                            .addComponent(lblAnio))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblClientes))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDetalles)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(btnDetallesClientes)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtCelular, txtDireccion, txtNombre});
        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lblCelular, lblDireccion, lblNombre});
        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtAnio, txtCedula});
        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lblAnio, lblCedula});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblClientes)
                    .addComponent(lblDetalles))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNombre)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCedula)
                            .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDireccion)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAnio))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCelular)
                            .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnVaciar)
                    .addComponent(btnEliminar)
                    .addComponent(btnDetallesClientes))
                .addContainerGap(63, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtCelular, txtDireccion, txtNombre});
        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lblCelular, lblDireccion, lblNombre});
        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtAnio, txtCedula});
        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lblAnio, lblCedula});

        setBounds(0, 0, 890, 321);
    }// </editor-fold>                        

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaClientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnDetallesClientes;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnVaciar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAnio;
    private javax.swing.JLabel lblCedula;
    private javax.swing.JLabel lblCelular;
    private javax.swing.JLabel lblClientes;
    private javax.swing.JLabel lblDetalles;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JList<String> listaClientes;
    private javax.swing.JTextField txtAnio;
    private javax.swing.JTextArea txtAreaClientes;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration                   
}