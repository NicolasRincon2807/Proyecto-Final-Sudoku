package persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import kernel.Jugador;

public class ModificarArchivos {
    public static void validarYCrearArchivo(String nombreArchivo) {
        File archivo = new File(nombreArchivo);
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
            } catch (IOException e) {
                // No se hace nada en caso de error
            }
        }
    }
    
    public static void GuardarJugadores(List<Jugador> jugadores) {
        try {
            BufferedWriter escritor = new BufferedWriter(new FileWriter("archivo6.txt"));
            for (Jugador jugador : jugadores) {
                escritor.write(jugador.getNickName() + "\t" + 
                              jugador.getPassword() + "\t" + 
                              String.valueOf(jugador.getPuntaje()) + "\t" +
                              String.valueOf(jugador.getErrores()) + "\t" +
                              String.valueOf(jugador.getDificultad()) + "\t" +
                              String.valueOf(jugador.getTiempoHoras()) + "\t" +
                              String.valueOf(jugador.getTiempoMinutos()) + "\t" +
                              String.valueOf(jugador.getTiempoSegundos()) + "\t" +
                              convertirMatrizAString(jugador.getSudokuActual()) + "\t" +
                              convertirMatrizAString(jugador.getSudokuGenerado()));
                escritor.newLine();
            }
            escritor.close();
        } catch (IOException e) {
            System.out.println("Error al guardar jugadores");
        }
    }

    public static void GuardarJugador(Jugador jugador) {
        try {
            BufferedWriter escritor = new BufferedWriter(new FileWriter("archivo6.txt", true));
            escritor.write(jugador.getNickName() + "\t" + 
                          jugador.getPassword() + "\t" + 
                          String.valueOf(jugador.getPuntaje()) + "\t" +
                          String.valueOf(jugador.getErrores()) + "\t" +
                          String.valueOf(jugador.getDificultad()) + "\t" +
                          String.valueOf(jugador.getTiempoHoras()) + "\t" +
                          String.valueOf(jugador.getTiempoMinutos()) + "\t" +
                          String.valueOf(jugador.getTiempoSegundos()) + "\t" +
                          convertirMatrizAString(jugador.getSudokuActual()) + "\t" +
                          convertirMatrizAString(jugador.getSudokuGenerado()));
            escritor.newLine();
            escritor.close();
        } catch (IOException e) {
            System.out.println("Error al guardar jugador");
        }
    }
    
    // Método auxiliar para convertir matriz a string
    private static String convertirMatrizAString(int[][] matriz) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                sb.append(matriz[i][j]);
                if (j < matriz[0].length - 1) sb.append(",");
            }
            if (i < matriz.length - 1) sb.append(";");
        }
        return sb.toString();
    }

    // Método auxiliar para convertir string a matriz
    private static int[][] convertirStringAMatriz(String str) {
        int[][] matriz = new int[9][9];
        String[] filas = str.split(";");
        for (int i = 0; i < filas.length; i++) {
            String[] valores = filas[i].split(",");
            for (int j = 0; j < valores.length; j++) {
                matriz[i][j] = Integer.parseInt(valores[j]);
            }
        }
        return matriz;
    }

    public static void LeerJugadores(List<Jugador> lista) {
        try {
            BufferedReader lector = new BufferedReader(new FileReader("archivo6.txt"));
            String linea = "";
            while ((linea = lector.readLine()) != null) {
                String[] bloques = linea.split("\t");
                if (bloques.length >= 10) {
                    String nickName = bloques[0];
                    String password = bloques[1];
                    double puntaje = Double.parseDouble(bloques[2]);
                    double errores = Double.parseDouble(bloques[3]);
                    double dificultad = Double.parseDouble(bloques[4]);
                    int horas = Integer.parseInt(bloques[5]);
                    int minutos = Integer.parseInt(bloques[6]);
                    int segundos = Integer.parseInt(bloques[7]);
                    int[][] sudokuActual = convertirStringAMatriz(bloques[8]);
                    int[][] sudokuGenerado = convertirStringAMatriz(bloques[9]);
                    
                    Jugador jugador = new Jugador(nickName, password);
                    jugador.setPuntaje(puntaje);
                    jugador.setErrores(errores);
                    jugador.setDificultad(dificultad);
                    jugador.guardarEstadoPartida(sudokuActual, sudokuGenerado, 
                                               horas, minutos, segundos, dificultad);
                    
                    lista.add(jugador);
                }
            }
            lector.close();
        } catch (IOException e) {
            System.out.println("Error al leer jugadores");
        }
    }

    public static boolean ValidarNickName(List<Jugador> lista, String nickName) {
        int pos = -1;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNickName().equals(nickName)) {
                pos = i;
            }
        }
        return pos != -1;
    }

    public static Jugador EncontrarJugador(List<Jugador> lista, String nickName) {
        int pos = -1;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNickName().equals(nickName)) {
                pos = i;
            }
        }
        return lista.get(pos);
    }

    public static int EncontrarPosicion(List<Jugador> lista, String password) {
        int pos = -1;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getPassword().equals(password)) {
                pos = i;
            }
        }
        return pos;
    }
    

    public static void BorrarJugadores() {
        try {
            PrintWriter escritor = new PrintWriter("archivo6.txt");
            escritor.print("");
            escritor.close();
        } catch (IOException er) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al vaciar el archivo");
            er.printStackTrace();
        }
    }
    
    public static void ActualizarJugador(Jugador jugadorActualizado) {
        List<Jugador> jugadores = new ArrayList<>();
        LeerJugadores(jugadores); // Lee todos los jugadores actuales
        
        // Encuentra y actualiza el jugador en la lista
        for (int i = 0; i < jugadores.size(); i++) {
            if (jugadores.get(i).getNickName().equals(jugadorActualizado.getNickName())) {
                jugadores.set(i, jugadorActualizado);
                break;
            }
        }
        
        // Borra el archivo y guarda la lista actualizada
        BorrarJugadores();
        GuardarJugadores(jugadores);
    }
}
