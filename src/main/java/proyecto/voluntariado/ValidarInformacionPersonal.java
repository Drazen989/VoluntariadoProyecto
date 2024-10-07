package proyecto.voluntariado;

import java.util.List;
public class ValidarInformacionPersonal {

    // Constructor que valida todas las variables
    public static void validarInformacion(String genero, String fechaNacimiento, String correo, int numero, String region,
                                          String comuna, String contactoEmergencia, int numeroEmergencia, List<Resto> restoList) throws InvalidDataException {

        validarGenero(genero);
        validarFechaNacimiento(fechaNacimiento);
        validarCorreo(correo);
        validarNumeroTelefono(numero);
        validarRegion(region);
        validarComuna(comuna);
        validarContactoEmergencia(contactoEmergencia);
        validarNumeroEmergencia(numeroEmergencia);
        validarRestoList(restoList);
    }

    // Valida el género (puede ser 'M' o 'F')
    private static void validarGenero(String genero) throws InvalidDataException {
        if (!genero.equalsIgnoreCase("M") && !genero.equalsIgnoreCase("F")) {
            throw new InvalidDataException("Género inválido. Debe ser 'M' o 'F'.");
        }
    }

    // Valida la fecha de nacimiento en formato YYYY-MM-DD
    private static void validarFechaNacimiento(String fechaNacimiento) throws InvalidDataException {
        if (!fechaNacimiento.matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new InvalidDataException("Fecha de nacimiento inválida. Debe estar en el formato YYYY-MM-DD.");
        }
    }

    // Valida el formato del correo electrónico
    private static void validarCorreo(String correo) throws InvalidDataException {
        if (!correo.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new InvalidDataException("Correo electrónico inválido.");
        }
    }

    // Valida el número de teléfono (debe ser positivo y tener al menos 7 dígitos)
    private static void validarNumeroTelefono(int numero) throws InvalidDataException {
        if (numero <= 0 || String.valueOf(numero).length() < 7) {
            throw new InvalidDataException("Número de teléfono inválido. Debe tener al menos 7 dígitos.");
        }
    }

    // Valida que la región no esté vacía
    private static void validarRegion(String region) throws InvalidDataException {
        if (region == null || region.isEmpty()) {
            throw new InvalidDataException("La región no puede estar vacía.");
        }
    }

    // Valida que la comuna no esté vacía
    private static void validarComuna(String comuna) throws InvalidDataException {
        if (comuna == null || comuna.isEmpty()) {
            throw new InvalidDataException("La comuna no puede estar vacía.");
        }
    }

    // Valida que el contacto de emergencia no esté vacío
    private static void validarContactoEmergencia(String contactoEmergencia) throws InvalidDataException {
        if (contactoEmergencia == null || contactoEmergencia.isEmpty()) {
            throw new InvalidDataException("El contacto de emergencia no puede estar vacío.");
        }
    }

    // Valida que el número de emergencia sea positivo y tenga al menos 7 dígitos
    private static void validarNumeroEmergencia(int numeroEmergencia) throws InvalidDataException {
        if (numeroEmergencia <= 0 || String.valueOf(numeroEmergencia).length() < 7) {
            throw new InvalidDataException("Número de emergencia inválido. Debe tener al menos 7 dígitos.");
        }
    }

    // Valida que la lista de 'Resto' no sea nula
    private static void validarRestoList(List<Resto> restoList) throws InvalidDataException {
        if (restoList == null) {
            throw new InvalidDataException("La lista 'Resto' no puede ser nula.");
        }
    }
}
