package gui;

/**
 * Clase utilitaria para validación de entradas de datos.
 * 
 * Proporciona métodos estáticos para realizar validaciones de datos
 * y lanzar excepciones personalizadas cuando sea necesario.
 * 
 * @author Nicolás Rincón
 * @version 1.0
 * @since 2025-02-10
 */
public class Excepciones {
    /**
     * Valida que un dato de entrada no esté vacío o sea nulo.
     * 
     * @param dato Cadena de texto a validar
     * @throws DatosFaltantesException Si el dato es nulo o está vacío
     */
    public static void validarEntrada(String dato) throws DatosFaltantesException {
        if (dato == null || dato.trim().isEmpty()) {
            throw new DatosFaltantesException("es obligatorio llenar todos los espacios");
        }
    }
}
