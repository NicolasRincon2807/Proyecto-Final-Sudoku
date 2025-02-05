/**
 * KEVIN NICOLÁS RINCÓN HERNÁNDEZ
 * COD: 20241020082
 * FECHA: 13/01/2025
 * PROGRAMA QUE MANEJA DATOS DE UN GRUPO DE N ESTUDIANTES
 */

package gui;
//Se crea una excepción personalizada dado el caso de que falten datos en los espacios
class DatosFaltantesException extends Exception {
	public DatosFaltantesException(String mensaje) {
		super(mensaje);
	}
}
