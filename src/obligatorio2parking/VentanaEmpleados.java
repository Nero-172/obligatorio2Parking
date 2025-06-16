/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package obligatorio2parking;

import dominio.Empleado;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author USUARIO
 */
public class VentanaEmpleados extends javax.swing.JFrame {
    
    private List<Empleado> empleados;
    private DefaultListModel<String> modeloLista;

    public VentanaEmpleados() {
        initComponents();
        inicializarDatos();
        configurarEventos();
    }
    
    private void inicializarDatos() {
        empleados = new ArrayList<>();
        modeloLista = new DefaultListModel<>();
        listaEmpleados.setModel(modeloLista);
        txtAreaEmpleados.setEditable(false);
    }
    
    private void configurarEventos() {
        // Evento para agregar empleado
        btnAgregarE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarEmpleado();
            }
        });
        
        // Evento para vaciar campos
        btnVaciarE.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vaciarCampos();
            }
        });
        
        // Evento para eliminar empleado
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarEmpleado();
            }
        });
        
        // Evento para mostrar detalles del empleado
        btnMostrarEmpleado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarDetalleEmpleado();
            }
        });
        
        // Evento para selección en la lista
        listaEmpleados.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                mostrarDetalleEmpleado();
            }
        });
    }
    
    private void agregarEmpleado() {
        try {
            // Validar campos
            if (!validarCampos()) {
                return;
            }
            
            String nombre = txtNombreE.getText().trim();
            String cedula = txtCedulaE.getText().trim();
            String direccion = txtDireccionE.getText().trim();
            int numeroEmpleado = Integer.parseInt(txtNumEmpleado.getText().trim());
            
            // Verificar si ya existe un empleado con la misma cédula
            for (Empleado emp : empleados) {
                if (emp.getCedula().equals(cedula)) {
                    JOptionPane.showMessageDialog(this, 
                        "Ya existe un empleado con esa cédula.", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            
            // Verificar si ya existe un empleado con el mismo número
            for (Empleado emp : empleados) {
                if (emp.getNumeroEmpleado() == numeroEmpleado) {
                    JOptionPane.showMessageDialog(this, 
                        "Ya existe un empleado con ese número.", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            
            // Crear y agregar el empleado
            Empleado nuevoEmpleado = new Empleado(nombre, cedula, direccion, numeroEmpleado);
            empleados.add(nuevoEmpleado);
            
            // Actualizar la lista
            actualizarLista();
            
            // Limpiar campos
            vaciarCampos();
            
            JOptionPane.showMessageDialog(this, 
                "Empleado agregado exitosamente.", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
                
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, 
                "El número de empleado debe ser un valor numérico válido.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private boolean validarCampos() {
        if (txtNombreE.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "El nombre es obligatorio.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            txtNombreE.requestFocus();
            return false;
        }
        
        if (txtCedulaE.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "La cédula es obligatoria.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            txtCedulaE.requestFocus();
            return false;
        }
        
        if (txtDireccionE.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "La dirección es obligatoria.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            txtDireccionE.requestFocus();
            return false;
        }
        
        if (txtNumEmpleado.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "El número de empleado es obligatorio.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            txtNumEmpleado.requestFocus();
            return false;
        }
        
        return true;
    }
    
    private void vaciarCampos() {
        txtNombreE.setText("");
        txtCedulaE.setText("");
        txtDireccionE.setText("");
        txtNumEmpleado.setText("");
        txtAreaEmpleados.setText("");
        txtNombreE.requestFocus();
    }
    
    private void eliminarEmpleado() {
        int indiceSeleccionado = listaEmpleados.getSelectedIndex();
        
        if (indiceSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, 
                "Debe seleccionar un empleado de la lista para eliminar.", 
                "Advertencia", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        int confirmacion = JOptionPane.showConfirmDialog(this, 
            "¿Está seguro de que desea eliminar este empleado?", 
            "Confirmar eliminación", 
            JOptionPane.YES_NO_OPTION);
            
        if (confirmacion == JOptionPane.YES_OPTION) {
            empleados.remove(indiceSeleccionado);
            actualizarLista();
            txtAreaEmpleados.setText("");
            
            JOptionPane.showMessageDialog(this, 
                "Empleado eliminado exitosamente.", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void mostrarDetalleEmpleado() {
        int indiceSeleccionado = listaEmpleados.getSelectedIndex();
        
        if (indiceSeleccionado == -1) {
            txtAreaEmpleados.setText("");
            return;
        }
        
        Empleado empleadoSeleccionado = empleados.get(indiceSeleccionado);
        
        StringBuilder detalles = new StringBuilder();
        detalles.append("DETALLES DEL EMPLEADO\n");
        detalles.append("========================\n\n");
        detalles.append("Nombre: ").append(empleadoSeleccionado.getNombre()).append("\n");
        detalles.append("Cédula: ").append(empleadoSeleccionado.getCedula()).append("\n");
        detalles.append("Dirección: ").append(empleadoSeleccionado.getDireccion()).append("\n");
        detalles.append("Número de Empleado: ").append(empleadoSeleccionado.getNumeroEmpleado()).append("\n");
        
        txtAreaEmpleados.setText(detalles.toString());
    }
    
    private void actualizarLista() {
        modeloLista.clear();
        for (Empleado empleado : empleados) {
            modeloLista.addElement(empleado.toString());
        }
    }
    
    // Método público para obtener la lista de empleados (útil para otras ventanas)
    public List<Empleado> getEmpleados() {
        return new ArrayList<>(empleados);
    }
    
    // Método público para establecer la lista de empleados (útil para cargar datos)
    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = new ArrayList<>(empleados);
        actualizarLista();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNombre = new javax.swing.JLabel();
        lblCedula = new javax.swing.JLabel();
        lblDireccion = new javax.swing.JLabel();
        lblNumEmpleado = new javax.swing.JLabel();
        txtNombreE = new javax.swing.JTextField();
        txtCedulaE = new javax.swing.JTextField();
        txtDireccionE = new javax.swing.JTextField();
        txtNumEmpleado = new javax.swing.JTextField();
        btnVaciarE = new javax.swing.JButton();
        btnAgregarE = new javax.swing.JButton();
        scrollEmpleados = new javax.swing.JScrollPane();
        listaEmpleados = new javax.swing.JList<>();
        lblListaEmpleados = new javax.swing.JLabel();
        lblDetallesEmpleado = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtAreaEmpleados = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        btnMostrarEmpleado = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Empleados");

        lblNombre.setText("Nombre:");

        lblCedula.setText("Cédula:");

        lblDireccion.setText("Dirección:");

        lblNumEmpleado.setText("Número de Empleado:");

        txtNombreE.setColumns(4);

        txtCedulaE.setColumns(4);

        txtDireccionE.setColumns(4);

        txtNumEmpleado.setColumns(4);

        btnVaciarE.setText("Vaciar");

        btnAgregarE.setText("Agregar");

        scrollEmpleados.setViewportView(listaEmpleados);

        lblListaEmpleados.setText("Empleados:");

        lblDetallesEmpleado.setText("Detalles:");

        txtAreaEmpleados.setColumns(20);
        txtAreaEmpleados.setRows(5);
        jScrollPane1.setViewportView(txtAreaEmpleados);

        jButton1.setText("Eliminar");

        btnMostrarEmpleado.setText("Mostrar Detalle");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblCedula)
                        .addGap(18, 18, 18)
                        .addComponent(txtCedulaE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDireccion)
                        .addGap(18, 18, 18)
                        .addComponent(txtDireccionE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblNombre)
                        .addGap(18, 18, 18)
                        .addComponent(txtNombreE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(lblNumEmpleado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNumEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnVaciarE)
                        .addGap(18, 18, 18)
                        .addComponent(btnAgregarE, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scrollEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblListaEmpleados))
                        .addGap(37, 37, 37))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnMostrarEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDetallesEmpleado)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {lblCedula, lblDireccion, lblNombre});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtCedulaE, txtDireccionE, txtNombreE, txtNumEmpleado});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblListaEmpleados)
                    .addComponent(lblDetallesEmpleado))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(scrollEmpleados, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblNombre)
                            .addComponent(lblNumEmpleado)
                            .addComponent(txtNombreE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNumEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblCedula)
                            .addComponent(txtCedulaE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDireccion)
                            .addComponent(txtDireccionE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(74, 74, 74)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVaciarE)
                    .addComponent(btnAgregarE)
                    .addComponent(jButton1)
                    .addComponent(btnMostrarEmpleado))
                .addGap(27, 27, 27))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {lblCedula, lblDireccion, lblNombre});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtCedulaE, txtDireccionE, txtNombreE, txtNumEmpleado});

        setBounds(0, 0, 885, 348);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
   public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaEmpleados().setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarE;
    private javax.swing.JButton btnMostrarEmpleado;
    private javax.swing.JButton btnVaciarE;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCedula;
    private javax.swing.JLabel lblDetallesEmpleado;
    private javax.swing.JLabel lblDireccion;
    private javax.swing.JLabel lblListaEmpleados;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNumEmpleado;
    private javax.swing.JList<String> listaEmpleados;
    private javax.swing.JScrollPane scrollEmpleados;
    private javax.swing.JTextArea txtAreaEmpleados;
    private javax.swing.JTextField txtCedulaE;
    private javax.swing.JTextField txtDireccionE;
    private javax.swing.JTextField txtNombreE;
    private javax.swing.JTextField txtNumEmpleado;
    // End of variables declaration//GEN-END:variables
}
