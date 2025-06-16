/*
AUTORES - ESTUDIANTES
 JHONATAN ADALID (320368)
 LORENZO ALDAO ()
*/
package obligatorio2parking;

import dominio.*;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Obligatorio2Parking {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        Sistema sistema = new Sistema();
        // Cargar datos del archivo
        sistema = sistema.recuperarDatos();

        VentanaSistema vs = new VentanaSistema(sistema); // pasar el sistema con datos cargados
        vs.setVisible(true);

        // Cuando cierres la ventana (o después de agregar datos), guarda los datos:
        // vs.addWindowListener(...) para guardar al cerrar ventana
    }
}
