package gui;

/**
 * Excepción personalizada para manejar casos de datos faltantes en el sistema.
 * 
 * Esta excepción se lanza cuando se detecta que algún campo o dato requerido
 * no ha sido proporcionado, asegurando la integridad de la información.
 * 
 * @author Nicolás Rincón
 * @version 1.0
 * @since 2025-02-10
 */
class DatosFaltantesException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * Constructor que permite crear la excepción con un mensaje personalizado.
     * 
     * @param mensaje Descripción detallada del error de datos faltantes
     */
    public DatosFaltantesException(String mensaje) {
        super(mensaje);
    }
}