package obligatorio2parking;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class VentanaMiniJuego extends JFrame implements KeyListener, ActionListener {

    private Timer timer;
    private int jugadorX = 200;
    private final int jugadorY = 400;
    private ArrayList<Rectangle> obstaculos = new ArrayList<>();
    private Random rand = new Random();
    private boolean juegoActivo = true;
    private int puntaje = 0;

    public VentanaMiniJuego() {
        setTitle("MiniJuego - Esquiva Autos");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        addKeyListener(this);

        timer = new Timer(30, this);
        timer.start();

        crearObstaculo();
        setVisible(true);
    }

    public void crearObstaculo() {
        int lane = rand.nextInt(3); // 0, 1, 2
        int x = 100 * lane + 50;
        obstaculos.add(new Rectangle(x, -100, 50, 100));
    }

    @Override
    public void paint(Graphics g) {
        Image offImage = createImage(getWidth(), getHeight());
        Graphics2D g2 = (Graphics2D) offImage.getGraphics();

        // Fondo
        g2.setColor(Color.DARK_GRAY);
        g2.fillRect(0, 0, getWidth(), getHeight());

        // Carriles
        g2.setColor(Color.WHITE);
        for (int i = 1; i < 4; i++) {
            g2.drawLine(i * 100, 0, i * 100, getHeight());
        }

        // Auto jugador
        g2.setColor(Color.RED);
        g2.fillRect(jugadorX, jugadorY, 50, 100);

        // Obstáculos
        g2.setColor(Color.BLUE);
        for (Rectangle r : obstaculos) {
            g2.fill(r);
        }

        // Puntaje
        g2.setColor(Color.WHITE);
        g2.drawString("Puntaje: " + puntaje, 10, 50);

        // Fin del juego
        if (!juegoActivo) {
            g2.setColor(Color.YELLOW);
            g2.setFont(new Font("Arial", Font.BOLD, 30));
            g2.drawString("¡Perdiste!", 130, 250);
        }

        g.drawImage(offImage, 0, 0, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!juegoActivo) return;

        Iterator<Rectangle> it = obstaculos.iterator();
        while (it.hasNext()) {
            Rectangle r = it.next();
            r.y += 10;

            // Colisión
            Rectangle jugador = new Rectangle(jugadorX, jugadorY, 50, 100);
            if (r.intersects(jugador)) {
                juegoActivo = false;
                timer.stop();
            }

            // Sale de pantalla
            if (r.y > 500) {
                it.remove();
                puntaje += 10;
                crearObstaculo();
            }
        }

        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!juegoActivo) return;

        if (e.getKeyCode() == KeyEvent.VK_LEFT && jugadorX > 50) {
            jugadorX -= 100;
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && jugadorX < 250) {
            jugadorX += 100;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Entradas");
        getContentPane().setLayout(null);

        setBounds(0, 0, 490, 308);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(VentanaMiniJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaMiniJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaMiniJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaMiniJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaMiniJuego().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
