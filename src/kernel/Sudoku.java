package kernel;

import java.util.Random;

/**
 * Clase Sudoku
 * 
 * Gestiona la lógica del juego de Sudoku, incluyendo la generación y validación de tableros.
 * 
 * Autor: Nicolas Rincon
 * Fecha: 2025-02-10
 */
public class Sudoku {// Declaración de una matriz 9x9 para almacenar el Sudoku
	private int sudoku[][];

    /**
     * Constructor de la clase Sudoku.
     * Inicializa un tablero de Sudoku vacío.
     */	public Sudoku(){
	    sudoku = new int[9][9]; // Inicializa la matriz de Sudoku con ceros
	}

	// Método recursivo para resolver el Sudoku
	public boolean resolverSudoku() {
	    // Itera sobre cada celda de la matriz Sudoku
	    for (int i = 0; i < sudoku.length; i++) {
	        for (int j = 0; j < sudoku[0].length; j++) {
	            // Si la celda está vacía (valor 0), intenta poner un número del 1 al 9
	            if (sudoku[i][j] == 0) {
	                for (int valor = 1; valor <= 9; valor++) {
	                    // Si el valor es válido (no se repite en la fila, columna ni cuadrante), lo coloca
	                    if (validarFila(i, valor) && validarColumna(j, valor) && validarCuadrante(i, j, valor)) {
	                        sudoku[i][j] = valor;
	                        // Llamada recursiva para intentar resolver el resto del Sudoku
	                        if (resolverSudoku()) return true;
	                        // Si no se puede resolver, se resetea la celda y sigue buscando
	                        sudoku[i][j] = 0;
	                    }
	                }
	                return false; // Si no se encuentra un valor válido, retorna false
	            }
	        }
	    }
	    return true; // Si todas las celdas están llenas correctamente, se retorna true
	}

	// Método para comprobar si el Sudoku es válido
	public boolean ComprobarSudoku() {
	    for (int i = 0; i < sudoku.length; i++) {
	        for (int j = 0; j < sudoku[0].length; j++) {
	            int aux = sudoku[i][j];
	            sudoku[i][j] = 0; // Temporalmente vacía la celda
	            // Si la celda no es válida, retorna false
	            if (!validarFila(i, aux) || !validarColumna(j, aux) || !validarCuadrante(i, j, aux)) {
	                sudoku[i][j] = aux; // Restaura el valor original
	                return false;
	            }
	            sudoku[i][j] = aux; // Restaura el valor original
	        }
	    }
	    return true; // Si todas las celdas son válidas, retorna true
	}

	// Valida si un valor es válido dentro del cuadrante 3x3 correspondiente
	public boolean validarCuadrante(int i, int j, int valor) {
	    int posI = subCuadranteActual(i); // Calcula la fila del cuadrante
	    int posJ = subCuadranteActual(j); // Calcula la columna del cuadrante
	    
	    // Verifica si el valor ya existe dentro del cuadrante 3x3
	    for (int k = posI - 3; k < posI; k++) {
	        for (int l = posJ - 3; l < posJ; l++) {
	            if (sudoku[k][l] == valor) {
	                return false; // El valor ya existe en el cuadrante
	            }
	        }
	    }
	    return true; // Si no se encuentra el valor en el cuadrante, es válido
	}

	// Limpia el Sudoku (pone todos los valores a cero)
	public void limpiarSudoku() {
	    for (int i = 0; i < sudoku.length; i++) {
	        for (int j = 0; j < sudoku[0].length; j++) {
	            sudoku[i][j] = 0; // Pone todos los valores de la matriz a cero
	        }
	    }
	}

	// Calcula la posición del subcuadrante (uno de los 9 subcuadrantes del Sudoku)
	public int subCuadranteActual(int pos) {
	    if (pos <= 2) return 3;
	    else if (pos <= 5) return 6;
	    else return 9;
	}

	// Valida si un valor es válido dentro de una fila
	public boolean validarFila(int i, int valor) {
	    // Recorre cada columna de la fila y verifica si el valor ya está presente
	    for (int j = 0; j < sudoku[i].length; j++) {
	        if (sudoku[i][j] == valor) {
	            return false; // El valor ya está en la fila
	        }
	    }
	    return true; // El valor no está en la fila, es válido
	}

	// Valida si un valor es válido dentro de una columna
	public boolean validarColumna(int j, int valor) {
	    // Recorre cada fila de la columna y verifica si el valor ya está presente
	    for (int i = 0; i < sudoku.length; i++) {
	        if (sudoku[i][j] == valor) {
	            return false; // El valor ya está en la columna
	        }
	    }
	    return true; // El valor no está en la columna, es válido
	}

	// Muestra el Sudoku en consola
	public void mostrarSudoku() {
	    resolverSudoku(); // Resuelve el Sudoku antes de mostrarlo
	    for (int i = 0; i < sudoku.length; i++) {
	        for (int j = 0; j < sudoku[0].length; j++) {
	            System.out.print(sudoku[i][j]);
	            if (!(j == sudoku[0].length - 1)) {
	                System.out.print(" - "); // Imprime el separador
	            }
	        }
	        System.out.println(); // Salto de línea después de cada fila
	    }
	}

	// Genera un Sudoku de acuerdo con el nivel de dificultad
	public void generarSudoku(int nivel) {
	    limpiarSudoku(); // Limpia el Sudoku antes de empezar
	    Random random = new Random();
	    
	    // Llena las primeras celdas de cada subcuadrante
	    for (int i = 0; i < 3; i++) {
	        for (int j = 0; j < 3; j++) {
	            int num = random.nextInt(9) + 1;
	            if (validarCuadrante(i, j, num)) {
	                sudoku[i][j] = num; // Coloca el número en la celda si es válido
	            } else {
	                j--; // Si no es válido, repite la iteración
	            }
	        }
	    }
	    
	    // Similar para los otros subcuadrantes (3x3)
	    for (int i = 3; i < 6; i++) {
	        for (int j = 3; j < 6; j++) {
	            int num = random.nextInt(9) + 1;
	            if (validarCuadrante(i, j, num)) {
	                sudoku[i][j] = num;
	            } else {
	                j--;
	            }
	        }
	    }
	    
	    for (int i = 6; i < 9; i++) {
	        for (int j = 6; j < 9; j++) {
	            int num = random.nextInt(9) + 1;
	            if (validarCuadrante(i, j, num)) {
	                sudoku[i][j] = num;
	            } else {
	                j--;
	            }
	        }
	    }

	    resolverSudoku(); // Resuelve el Sudoku parcialmente

	    // Borra algunas celdas de acuerdo al nivel de dificultad
	    for (int i = 0; i < sudoku.length; i++) {
	        for (int j = 0; j < sudoku[0].length; j++) {
	            int aux = j;
	            int rand = random.nextInt(nivel + 1);
	            j += rand; // Determina cuántas celdas vaciar
	            for (int k = aux; k < j && k < sudoku.length; k++) {
	                sudoku[i][k] = 0; // Vacía las celdas
	            }
	        }
	    }
	}

	// Métodos getters y setters para la matriz Sudoku
	public int[][] getSudoku() {
	    return sudoku;
	}

	public void setSudoku(int[][] sudoku) {
	    this.sudoku = sudoku;
	}}

