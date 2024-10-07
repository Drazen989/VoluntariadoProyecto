package proyecto.voluntariado;


import javax.swing.*;
import java.io.IOException;
import java.util.HashMap;

public class AplicacionAyudantia {
    public static void main(String[] args) {
        HashMap<String, Personas> mapaPersonas = new HashMap<>();
        String archivoCSV = "src/proyecto/voluntariado/resources/personas_chile_realista_50_fixed_1.csv";



        try {
            LeerCSV.leerCSV(archivoCSV, mapaPersonas);
        } catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }

        SwingUtilities.invokeLater(() -> {
            VentanaAyudantia ventana = new VentanaAyudantia(mapaPersonas);
            ventana.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            ventana.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                    System.exit(0);
                }
            });
            ventana.setVisible(true);
        });
    }
}
