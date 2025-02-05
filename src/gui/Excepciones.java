package gui;

public class Excepciones {
	// Se crean métodos encargados de validar las posibles fallas.
    public static void validarEntrada(String dato) throws DatosFaltantesException {
        if (dato == null || dato.trim().isEmpty()) {
            throw new DatosFaltantesException("es obligatorio llenar todos los espacios");
        }
    }
    
}
