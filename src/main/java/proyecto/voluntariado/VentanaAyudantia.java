package proyecto.voluntariado;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
//import java.io.IOException;


public class VentanaAyudantia extends JFrame {
    // Nuevo final abajo
    private final HashMap<String, Personas> mapaPersonas;
    private JTextArea areaTexto;

    public VentanaAyudantia(HashMap<String, Personas> mapaPersonas) {
        this.mapaPersonas = mapaPersonas;
        initUI();
    }

    private void initUI() {
        setTitle("Sistema de Ayudantía");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //--- NUEVO ---
        //setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Evitar cierre automático

        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        add(new JScrollPane(areaTexto), BorderLayout.CENTER);
        
        // Barra de menú
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Opciones");
        JMenuItem salirItem = new JMenuItem("Salir");
        salirItem.addActionListener(e -> dispose()); // Cerrar la ventana directamente
        menu.add(salirItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);
        
        // Panel con los botones
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(5, 1)); // 5 filas para 5 botones

        // Botones para las diferentes opciones
        JButton botonInsertar = new JButton("Insertar Persona");
        JButton botonBuscarRUT = new JButton("Buscar por RUT");
        JButton botonBuscarRegion = new JButton("Buscar por Región");
        JButton botonMostrarTodos = new JButton("Mostrar todas las Personas");
        JButton botonEliminar = new JButton("Eliminar Persona por RUT");
        JButton botonBuscarTipo = new JButton("Buscar por Tipo");
        JButton botonMostrarHistorial = new JButton("Mostrar Historial");
        JButton botonBorrarHistorial = new JButton("Borrar Historial");

        // Añadir los botones al panel
        panelBotones.add(botonInsertar);
        panelBotones.add(botonBuscarRUT);
        panelBotones.add(botonBuscarRegion);
        panelBotones.add(botonMostrarTodos);
        panelBotones.add(botonEliminar);
        panelBotones.add(botonBuscarTipo);
        panelBotones.add(botonMostrarHistorial);
        panelBotones.add(botonBorrarHistorial);

        // Añadir el panel de botones al marco
        add(panelBotones, BorderLayout.EAST);

        // Acciones para los botones
        botonInsertar.addActionListener(e -> insertarPersona());
        botonBuscarRUT.addActionListener(e -> buscarPersonaPorRUT());
        botonBuscarRegion.addActionListener(e -> buscarPersonasPorRegion());
        botonMostrarTodos.addActionListener(e -> mostrarTodasLasPersonas());
        botonEliminar.addActionListener(e -> eliminarPersonaPorRUT());
        botonBuscarTipo.addActionListener(e -> buscarPorTipo());
        botonMostrarHistorial.addActionListener(e -> mostrarHistorial());
        botonBorrarHistorial.addActionListener(e -> borrarHistorial());
    
    }
  
    // Método para insertar una nueva persona
    private void insertarPersona() {   
        try {
            String nombre = obtenerEntradaValidaConLetras("Nombre:", "Ingrese solo letras.");
            if (nombre == null) return;

            String primerApellido = obtenerEntradaValidaConLetras("Primer Apellido:", "Ingrese solo letras.");
            if (primerApellido == null) return;

            String segundoApellido = obtenerEntradaValidaConLetras("Segundo Apellido:", "Ingrese solo letras.");
            if (segundoApellido == null) return;

            String rut = obtenerEntradaValidaRUT("RUT (formato NNNNNNNN-D):", "Ingrese un RUT válido con el formato NNNNNNNN-D.");
            if (rut == null) return;

            String genero = obtenerEntradaValidaConLetras("Género:", "Ingrese solo letras.");
            if (genero == null) return;

            String fechaNacimiento = obtenerEntradaValidaFecha("Fecha de Nacimiento (DD-MM-AAAA):", "Ingrese la fecha en formato DD-MM-YYYY.");
            if (fechaNacimiento == null) return;

            String correo = JOptionPane.showInputDialog(this, "Correo:");
            if (correo == null) return;

            String numeroTelefono = obtenerEntradaValidaNumeros("Número de Teléfono:", "Ingrese solo números.");
            if (numeroTelefono == null) return;
            int numero = Integer.parseInt(numeroTelefono);

            String region = obtenerEntradaValidaConEspacios("Región:", "Ingrese solo letras y puede incluir espacios.");
            if (region == null) return;

            String comuna = obtenerEntradaValidaConEspacios("Comuna:", "Ingrese solo letras y puede incluir espacios.");
            if (comuna == null) return;

            String tipoPersona = obtenerEntradaValidaTipoPersona("¿Es Constructor o Administrativo? (C/A):", "Ingrese 'C' o 'A'.");
            if (tipoPersona == null) return;
            
            Personas persona;

                                    

            if (tipoPersona.equalsIgnoreCase("C")) {
                String proyecto = obtenerEntradaValidaConLetras("¿En que proyecto te gustaria participar?:", "Ingrese solo letras sin espacios.");
                persona = new Constructor(nombre, primerApellido, segundoApellido, rut, proyecto);
            } else if (tipoPersona.equalsIgnoreCase("A")) {
                String area = obtenerEntradaValidaConLetras("Área:", "Ingrese solo letras sin espacios.");
                persona = new Administrativo(nombre, primerApellido, segundoApellido, rut, area);
            } else {
                persona = new Personas(nombre, primerApellido, segundoApellido, rut);
            }

            persona.agregarInformacionPersonal(genero, fechaNacimiento, correo, numero);
            persona.getInformacionPersonalList().get(0).setRegion(region);
            persona.getInformacionPersonalList().get(0).setComuna(comuna);

            // Solicitar ocupación actual y cómo se enteró
            String ocupacionActual = obtenerEntradaValidaConEspacios("Ocupación actual:", "Ingrese solo letras y espacios.");
            if (ocupacionActual == null) return;

            String comoTeEnteraste = obtenerEntradaValidaConEspacios("¿Cómo te enteraste?", "Ingrese solo letras y espacios.");
            if (comoTeEnteraste == null) return;

            // Crear el objeto Resto y añadirlo a la persona
            Resto resto = new Resto(ocupacionActual, comoTeEnteraste);
            persona.setResto(resto);
          
          
            mapaPersonas.put(rut, persona);
            areaTexto.setText("Persona añadida exitosamente.");

            LeerCSV.guardarUsuario("src/proyecto/voluntariado/resources/historial.csv", persona);
            System.out.println("Persona añadida al historial.");

        } catch (Exception e) {
          JOptionPane.showMessageDialog(this, e.getMessage(), "Error de Entrada", JOptionPane.ERROR_MESSAGE);
          insertarPersona(); // Llamar de nuevo al método para reingresar los datos
        }
    }


    // Método para buscar una persona por RUT
    private void buscarPersonaPorRUT() {
        try {
            //String rut = obtenerEntradaValidaRUT("Ingrese el RUT de la persona a buscar:", "Ingrese solo letras y números sin espacios.");
            String rut = JOptionPane.showInputDialog(this, "RUT:");
            if (rut == null) return; // Si el uriario presiona "Cancelar", salir del metodo
          
            
            if (mapaPersonas.containsKey(rut)) {
                Personas persona = mapaPersonas.get(rut);
                String resultado = "Nombre: " + persona.getNombre() + " " + persona.getPrimerApellido() + " " + persona.getSegundoApellido() +
                                   "\nRUT: " + persona.getRut() + 
                                   "\nGénero: " + persona.getInformacionPersonalList().get(0).getGenero() +
                                   "\nFecha de Nacimiento: " + persona.getInformacionPersonalList().get(0).getFechaNacimiento() +
                                   "\nCorreo: " + persona.getInformacionPersonalList().get(0).getCorreo() +
                                   "\nNúmero: " + persona.getInformacionPersonalList().get(0).getNumero() +
                                   "\nRegión: " + persona.getInformacionPersonalList().get(0).getRegion() +
                                   "\nComuna: " + persona.getInformacionPersonalList().get(0).getComuna();
        
                areaTexto.setText(resultado);
            } else {
                areaTexto.setText("Persona no encontrada con RUT " + rut + ".");
            }
        } catch (HeadlessException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error de Entrada", JOptionPane.ERROR_MESSAGE);
            buscarPersonaPorRUT(); // Llamar de nuevo al método para reingresar los datos
        }
    }

    // Método para buscar personas por región
    private void buscarPersonasPorRegion() {
        try {
            String region = obtenerEntradaValidaConLetras("Ingrese la Región:", "Ingrese solo letras sin espacios.");
            if (region == null) return;
            
            StringBuilder resultados = new StringBuilder();
            
            for (Personas persona : mapaPersonas.values()) {
                if (persona.getInformacionPersonalList().get(0).getRegion().equalsIgnoreCase(region)) {
                    resultados.append("Nombre: ").append(persona.getNombre()).append(" ").append(persona.getPrimerApellido()).append(" ").append(persona.getSegundoApellido())
                              .append("\nRUT: ").append(persona.getRut()).append("\n-------------------------\n");
                }
            }
    
            if (resultados.length() > 0) {
                areaTexto.setText(resultados.toString());
            } else {
                areaTexto.setText("No se encontraron personas en la región especificada.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error de Entrada", JOptionPane.ERROR_MESSAGE);
            buscarPersonasPorRegion(); // Llamar de nuevo al método para reingresar los datos
        }
    }

    // Método para mostrar todas las personas
    private void mostrarTodasLasPersonas() {
        StringBuilder resultados = new StringBuilder();

        for (Personas persona : mapaPersonas.values()) {
              resultados.append("Nombre: ").append(persona.getNombre()).append(" ").append(persona.getPrimerApellido()).append(" ").append(persona.getSegundoApellido())
                      .append("\nRUT: ").append(persona.getRut()).append("\n-------------------------\n");
        }

        if (resultados.length() > 0) {
            areaTexto.setText(resultados.toString());
        } else {
            areaTexto.setText("No hay personas registradas.");
        }

    }

    // Método para eliminar una persona por RUT
    private void eliminarPersonaPorRUT() {
        try {
        //String rut = obtenerEntradaValidaRUT("Ingrese el RUT de la persona a eliminar:", "Ingrese solo letras y números sin espacios.");
        String rut = JOptionPane.showInputDialog(this, "RUT:");
        if (rut == null) return;
        if (mapaPersonas.containsKey(rut)) {
            mapaPersonas.remove(rut);
            areaTexto.setText("Persona con RUT " + rut + " eliminada exitosamente.");
        } else {
            areaTexto.setText("Persona no encontrada.");
        }
    } catch (HeadlessException e) {
        JOptionPane.showMessageDialog(this, e.getMessage(), "Error de Entrada", JOptionPane.ERROR_MESSAGE);
        eliminarPersonaPorRUT(); // Llamar de nuevo al método para reingresar los datos
    }
    }
    
    // Metodo buscar Constructor o Administrativo
    private void buscarPorTipo() {
        try {
            String tipo = JOptionPane.showInputDialog(this, "Ingrese el tipo de persona (Constructor/Administrativo):");
    
            // Validar entrada
            if (tipo == null || tipo.trim().isEmpty() || tipo.matches(".*\\d.*")) {
                throw new IllegalArgumentException("Entrada no válida. No se permiten números ni espacios en blanco.");
            }
    
            StringBuilder resultados = new StringBuilder();
            boolean encontrado = false;
    
            if (tipo.equalsIgnoreCase("Constructor")) {
                for (Personas persona : mapaPersonas.values()) {
                    if (persona instanceof Constructor) {
                        encontrado = true;
                        resultados.append("Nombre: ").append(persona.getNombre())
                                  .append(" ").append(persona.getPrimerApellido())
                                  .append(" ").append(persona.getSegundoApellido())
                                  .append("\nRUT: ").append(persona.getRut())
                                  .append("\n-------------------------\n");
                    }
                }
            } else if (tipo.equalsIgnoreCase("Administrativo")) {
                for (Personas persona : mapaPersonas.values()) {
                    if (persona instanceof Administrativo) {
                        encontrado = true;
                        resultados.append("Nombre: ").append(persona.getNombre())
                                  .append(" ").append(persona.getPrimerApellido())
                                  .append(" ").append(persona.getSegundoApellido())
                                  .append("\nRUT: ").append(persona.getRut())
                                  .append("\n-------------------------\n");
                    }
                }
            } else {
                throw new IllegalArgumentException("Tipo no válido. Ingrese 'Constructor' o 'Administrativo'.");
            }
    
            if (encontrado) {
                areaTexto.setText(resultados.toString());
            } else {
                areaTexto.setText("No se encontraron personas de tipo " + tipo + ".");
            }
    
        } catch (IllegalArgumentException e) {
            // Mostrar mensaje de error al usuario
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error de Entrada", JOptionPane.ERROR_MESSAGE);
        } catch (HeadlessException e) {
            // Captura cualquier otra excepción que pueda ocurrir
            JOptionPane.showMessageDialog(this, "Ocurrió un error inesperado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private String obtenerEntradaValidaConLetras(String mensaje, String mensajeError) throws Exception {
        String entrada;
        do {
            entrada = JOptionPane.showInputDialog(this, mensaje);
            if (entrada == null) return null; // El usuario presionó "cancelar"
            // Permitir letras y "ñ" (mayúscula y minúscula)
            if (!entrada.matches("[a-zA-ZñÑ]+")) {
                JOptionPane.showMessageDialog(this, mensajeError, "Error de Entrada", JOptionPane.ERROR_MESSAGE);
            }
        } while (!entrada.matches("[a-zA-ZñÑ]+"));
        return entrada;
    }
    
    // Método para validar solo números (para RUT y número de teléfono)
    private String obtenerEntradaValidaNumeros(String mensaje, String mensajeError) {
        String entrada;
        do {
            entrada = JOptionPane.showInputDialog(this, mensaje);
            if (entrada == null) return null; // El usuario presionó "Cancelar"
            if (!entrada.matches("\\d+")) { // Solo acepta números
                JOptionPane.showMessageDialog(this, mensajeError, "Error de Entrada", JOptionPane.ERROR_MESSAGE);
            }
        } while (!entrada.matches("\\d+"));
        return entrada;
    }

    // Método para validar la fecha en formato DD-MM-YYYY
    private String obtenerEntradaValidaFecha(String mensaje, String mensajeError) {
        String entrada;
        do {
            entrada = JOptionPane.showInputDialog(this, mensaje);
            if (entrada == null) return null; // El usuario presionó "Cancelar"
            if (!entrada.matches("\\d{2}-\\d{2}-\\d{4}")) { // Asegura formato DD-MM-YYYY
                JOptionPane.showMessageDialog(this, mensajeError, "Error de Entrada", JOptionPane.ERROR_MESSAGE);
            }
        } while (!entrada.matches("\\d{2}-\\d{2}-\\d{4}"));
        return entrada;
    }

    // Método para validar el RUT en formato NNNNNNNN-D
    private String obtenerEntradaValidaRUT(String mensaje, String mensajeError) {
        String entrada;
        do {
            entrada = JOptionPane.showInputDialog(this, mensaje);
            if (entrada == null) return null; // El usuario presionó "Cancelar"
            if (!entrada.matches("\\d{7,8}-[\\dKk]")) { // Formato RUT chileno
                JOptionPane.showMessageDialog(this, mensajeError, "Error de Entrada", JOptionPane.ERROR_MESSAGE);
            }
        } while (!entrada.matches("\\d{7,8}-[\\dKk]"));
        return entrada;
    }
    
    private String obtenerEntradaValidaConEspacios(String mensaje, String mensajeError) {
        String entrada;
        do {
            entrada = JOptionPane.showInputDialog(this, mensaje);
            if (entrada == null || entrada.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, mensajeError, "Error de Entrada", JOptionPane.ERROR_MESSAGE);
                continue;
            }
            // Permitir letras, espacios y "ñ" (mayúscula y minúscula)
            if (!entrada.matches("[a-zA-ZñÑ\\s]+")) {
                JOptionPane.showMessageDialog(this, mensajeError, "Error de Entrada", JOptionPane.ERROR_MESSAGE);
                entrada = null; // Aseguramos que la entrada no sea válida para repetir el ciclo
            }
        } while (entrada == null || entrada.trim().isEmpty());

        return entrada.trim();
    }
    
    private void mostrarHistorial() {
        String archivoHistorial = "src/proyecto/voluntariado/resources/historial.csv";
        try {
            String historial = LeerCSV.leerHistorial(archivoHistorial);
            if (historial.isEmpty()) {
                areaTexto.setText("No hay historial registrado.");
            } else {
                StringBuilder historialFormateado = new StringBuilder();
                String[] lineas = historial.split("\n");

                for (String linea : lineas) {
                    // Separar los campos por ";"
                    String[] datos = linea.split(";");

                    // Formatear la salida
                    historialFormateado.append("Nombre: ").append(datos[0]).append("\n")
                                       .append("Primer Apellido: ").append(datos[1]).append("\n")
                                       .append("Segundo Apellido: ").append(datos[2]).append("\n")
                                       .append("RUT: ").append(datos[3]).append("\n")
                                       .append("Género: ").append(datos[4]).append("\n")
                                       .append("Fecha de Nacimiento: ").append(datos[5]).append("\n")
                                       .append("Correo: ").append(datos[6]).append("\n")
                                       .append("Número: ").append(datos[7]).append("\n")
                                       .append("Región: ").append(datos[8]).append("\n")
                                       .append("Comuna: ").append(datos[9]).append("\n");

                    // Verificar si hay más campos (proyecto o área) para agregar
                    if (datos.length > 10) {
                        historialFormateado.append("Proyecto/Área: ").append(datos[10]).append("\n");
                    }

                    // Añadir la línea separadora entre usuarios
                    historialFormateado.append("-------------------------\n");
                }

                areaTexto.setText(historialFormateado.toString());
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al leer el historial: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void borrarHistorial() {
        String archivoHistorial = "src/proyecto/voluntariado/resources/historial.csv";
        int confirm = JOptionPane.showConfirmDialog(this, "¿Estás seguro de que deseas borrar el historial?", "Confirmar", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                LeerCSV.borrarHistorial(archivoHistorial);
                areaTexto.setText("Historial borrado exitosamente.");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error al borrar el historial: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private String obtenerEntradaValidaTipoPersona(String mensaje, String mensajeError) {
        String entrada;
        do {
            entrada = JOptionPane.showInputDialog(this, mensaje);
            if (entrada == null) return null; // El usuario presionó "cancelar"

            // Validar que la entrada sea 'C' o 'A', ignorando mayúsculas y minúsculas
            if (!entrada.equalsIgnoreCase("C") && !entrada.equalsIgnoreCase("A")) {
                JOptionPane.showMessageDialog(this, mensajeError, "Error de Entrada", JOptionPane.ERROR_MESSAGE);
                entrada = null; // Forzar la repetición del ciclo
            }
        } while (entrada == null);

        return entrada.toUpperCase(); // Retornar la entrada en mayúscula
    }
   

}
