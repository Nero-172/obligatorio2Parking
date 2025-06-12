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
            //Hecho con chatGPT
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); 
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Inicializar el sistema
        Sistema sistema = new Sistema();
        
        VentanaSistema vs = new VentanaSistema();
        vs.setVisible(true);
    }
}
