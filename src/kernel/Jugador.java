package kernel;

import javax.swing.JTextField;

/**
 * Clase Jugador
 * 
 * Representa a un jugador en el sistema, almacenando su información personal y su estado en el juego.
 * 
 * Autor: Nicolas Rincon
 * Fecha: 2025-02-10
 */
public class Jugador implements Comparable<Jugador>{
    private String nickName;
    private String password;
    private double puntaje;
    private double errores;
    private double dificultad;
    private int[][] sudokuActual;         // Para almacenar el estado actual del sudoku
    private int[][] sudokuGenerado;       // Para almacenar las posiciones generadas inicialmente
    private int tiempoHoras;              // Para almacenar las horas
    private int tiempoMinutos;            // Para almacenar los minutos
    private int tiempoSegundos;           // Para almacenar los segundos
    private boolean partidaEnCurso;       // Para saber si tiene una partida guardada
    
    
    @Override
    public int compareTo(Jugador otroJugador) {
        // Orden descendente (mayor a menor)
        return Double.compare(otroJugador.getPuntaje(), this.puntaje);
    }
    /**
     * Constructor de la clase Jugador.
     * Inicializa un nuevo jugador con un nombre y contraseña.
     */
    public Jugador(String nickName, String password) {
        this.nickName = nickName;
        this.password = password;
        this.puntaje = 0;
        this.errores = 0;
        this.dificultad = 0;
        this.sudokuActual = new int[9][9];
        this.sudokuGenerado = new int[9][9];
        this.tiempoHoras = 0;
        this.tiempoMinutos = 0;
        this.tiempoSegundos = 0;
        this.partidaEnCurso = false;
    }
    public void resetearJugador() {
        this.errores = 0;
        this.sudokuActual = new int[9][9];
        this.sudokuGenerado = new int[9][9];
        this.tiempoHoras = 0;
        this.tiempoMinutos = 0;
        this.tiempoSegundos = 0;
        this.partidaEnCurso = false;
    }
    
    public boolean ValidarMatrizGuardada() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
            	if(sudokuActual[i][j] != 0) {
            		return true;
            		}
            	}
            }
        return false;
    }
    // Método para guardar el estado actual de una partida
    public void guardarEstadoPartida(int[][] sudokuActual, int[][] sudokuGenerado, 
                                   int horas, int minutos, int segundos, double dificultad) {
        this.sudokuActual = copiarMatriz(sudokuActual);
        this.sudokuGenerado = copiarMatriz(sudokuGenerado);
        this.tiempoHoras = horas;
        this.tiempoMinutos = minutos;
        this.tiempoSegundos = segundos;
        this.dificultad = dificultad;
        this.partidaEnCurso = true;
    }

    // Método auxiliar para copiar matrices
    private int[][] copiarMatriz(int[][] matriz) {
        int[][] copia = new int[matriz.length][matriz[0].length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                copia[i][j] = matriz[i][j];
            }
        }
        return copia;
    }

    // Método para convertir el tablero de JTextField a matriz de enteros
    public int[][] convertirTableroAMatriz(JTextField[][] tablero) {
        int[][] matriz = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String valor = tablero[i][j].getText();
                matriz[i][j] = valor.isEmpty() ? 0 : Integer.parseInt(valor);
            }
        }
        return matriz;
    }

    public void CalcularPuntaje(double errores, double dificultad) {
        this.errores = errores;
        this.dificultad = dificultad;
        this.puntaje = (1000 * dificultad) - ((tiempoMinutos + (tiempoHoras * 60)) * 5) - (errores * 50);
    }
	
	

    // Getters y setters para los nuevos campos
    public int[][] getSudokuActual() {
        return sudokuActual;
    }

    public int[][] getSudokuGenerado() {
        return sudokuGenerado;
    }

    public int getTiempoHoras() {
        return tiempoHoras;
    }

    public int getTiempoMinutos() {
        return tiempoMinutos;
    }

    public int getTiempoSegundos() {
        return tiempoSegundos;
    }

    public boolean tienePartidaGuardada() {
        return partidaEnCurso;
    }
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public double getPuntaje() {
		return puntaje;
	}
	public void setPuntaje(double puntaje) {
		this.puntaje = puntaje;
	}

	public double getErrores() {
		return errores;
	}
	public void setErrores(double errores) {
		this.errores = errores;
	}
	public double getDificultad() {
		return dificultad;
	}
	public void setDificultad(double dificultad) {
		this.dificultad = dificultad;
	}

	public boolean isPartidaEnCurso() {
		return partidaEnCurso;
	}

	public void setPartidaEnCurso(boolean partidaEnCurso) {
		this.partidaEnCurso = partidaEnCurso;
	}

	public void setSudokuActual(int[][] sudokuActual) {
		this.sudokuActual = sudokuActual;
	}

	public void setSudokuGenerado(int[][] sudokuGenerado) {
		this.sudokuGenerado = sudokuGenerado;
	}

	public void setTiempoHoras(int tiempoHoras) {
		this.tiempoHoras = tiempoHoras;
	}

	public void setTiempoMinutos(int tiempoMinutos) {
		this.tiempoMinutos = tiempoMinutos;
	}

	public void setTiempoSegundos(int tiempoSegundos) {
		this.tiempoSegundos = tiempoSegundos;
	}

	
	
}
