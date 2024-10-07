package proyecto.voluntariado;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class LeerCSV {
    // Método para leer el archivo CSV y cargar los datos en el mapa de personas
    public static void leerCSV(String archivoCSV, HashMap<String, Personas> mapaPersonas) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
            String linea;
            boolean primeraLinea = true;

            while ((linea = br.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                }

                String[] datos = linea.split(";");
                if (datos.length < 10) {
                    System.out.println("Línea inválida en el archivo CSV: " + linea);
                    continue; // Salta líneas que no tengan el número esperado de columnas
                }
                try {
                    String nombre = datos[0];
                    String primerApellido = datos[1];
                    String segundoApellido = datos[2];
                    String rut = datos[3];
                    String genero = datos[4];
                    String fechaNacimiento = datos[5].replaceAll("\"", "");
                    String correo = datos[6];
                    long numero = Long.parseLong(datos[7]);
                    String region = datos[8];
                    String comuna = datos[9];

                    Personas persona = new Personas(nombre, primerApellido, segundoApellido, rut);
                    persona.agregarInformacionPersonal(genero, fechaNacimiento, correo, (int) numero);
                    persona.getInformacionPersonalList().get(0).setRegion(region);
                    persona.getInformacionPersonalList().get(0).setComuna(comuna);

                    mapaPersonas.put(rut, persona);
                } catch(NumberFormatException e){
                     System.out.println("Error al parsear número en la línea: " + linea);
                } catch (Exception e) {
                    System.out.println("Error al procesar línea: " + linea + ". " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }
    }


    // Método para guardar un solo usuario en un archivo de texto
    public static void guardarUsuario(String archivo, Personas persona) throws IOException {
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true))) {
        StringBuilder linea = new StringBuilder();

        // Añadir información personal común
        linea.append(obtenerInformacionPersonal(persona));

        // Guardar la ocupación actual y cómo se enteró (objeto Resto)
        if (persona.getResto() != null) {
            linea.append(";").append(persona.getResto().getOcupacionActual() != null ? persona.getResto().getOcupacionActual() : "")
                 .append(";").append(persona.getResto().getComoTeEnteraste() != null ? persona.getResto().getComoTeEnteraste() : "");
        } else {
            // Agregar campos vacíos si Resto no está definido
            linea.append(";;");
        }

        // Si es un Constructor, añadir el proyecto
      // Si es un Constructor, añadir el proyecto
        if (persona instanceof Constructor constructor) {
            linea.append(";").append(constructor.getProyecto() != null ? constructor.getProyecto() : "");
        }
        // Si es un Administrativo, añadir el área
        else if (persona instanceof Administrativo administrativo) {
            linea.append(";").append(administrativo.getArea() != null ? administrativo.getArea() : "");
        }

        // Escribir la línea al archivo
        bw.write(linea.toString());
        bw.newLine();

        System.out.println("Usuario guardado en el archivo.");
    } catch (IOException e) {
        System.out.println("Error al guardar el usuario en el archivo: " + e.getMessage());
        throw e; // Relanza la excepción para que pueda ser manejada en otro lugar si es necesario
    }
}

// Método auxiliar para obtener la información personal
private static String obtenerInformacionPersonal(Personas persona) {
    StringBuilder info = new StringBuilder();
    InformacionPersonal personalInfo = persona.getInformacionPersonalList().get(0);

    info.append(persona.getNombre() != null ? persona.getNombre() : "").append(";")
        .append(persona.getPrimerApellido() != null ? persona.getPrimerApellido() : "").append(";")
        .append(persona.getSegundoApellido() != null ? persona.getSegundoApellido() : "").append(";")
        .append(persona.getRut() != null ? persona.getRut() : "").append(";")
        .append(personalInfo.getGenero() != null ? personalInfo.getGenero() : "").append(";")
        .append(personalInfo.getFechaNacimiento() != null ? personalInfo.getFechaNacimiento() : "").append(";")
        .append(personalInfo.getCorreo() != null ? personalInfo.getCorreo() : "").append(";")
        .append(personalInfo.getNumero()).append(";")
        .append(personalInfo.getRegion() != null ? personalInfo.getRegion() : "").append(";")
        .append(personalInfo.getComuna() != null ? personalInfo.getComuna() : "");

    return info.toString();
    }
    
    public static String leerHistorial(String archivoCSV) throws IOException {
        StringBuilder historial = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(archivoCSV))) {
            String linea;
            boolean primeraLinea = true;

            while ((linea = br.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue; // Saltar la línea de encabezado
                }
                historial.append(linea).append("\n");
            }
        }
        return historial.toString();
    }

// Método para borrar el contenido del historial
    public static void borrarHistorial(String archivoCSV) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoCSV))) {
            // Solo escribimos los encabezados nuevamente, dejando el archivo "vacío"
            bw.write("Nombre;PrimerApellido;SegundoApellido;RUT;Genero;FechaNacimiento;Correo;Numero;Region;Comuna");
            bw.newLine();
        }
    }

}





