package gui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import kernel.Jugador;
import persistencia.ModificarArchivos;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;

/**
 * Formulario principal del juego de Sudoku.
 * 
 * Gestiona la interfaz de juego, incluyendo el tablero, 
 * cronómetro, botones de control y lógica de juego.
 * 
 * @author Nicolás Rincón
 * @version 1.0
 * @since 2025-02-10
 */
public class FormSudoku extends JFrame {

	// Definición de variables
	private static final long serialVersionUID = 1L;
	private JPanel PanelFondo; // Panel principal donde se agregan los demás componentes
	public static TableroSudoku tableroSudoku; // Componente para el tablero de Sudoku
	private TableroNumeros tableroNumeros; // Componente para los botones numéricos del Sudoku
	private FormNiveles formniveles; // Componente para la selección de niveles
	private static JLabel lblTimer = new JLabel("00:00:00:00"); // Etiqueta para mostrar el tiempo transcurrido
	Cronometro cronometro = new Cronometro(lblTimer); // Instancia del cronómetro
	
    /**
     * Constructor para crear el formulario de Sudoku.
     * 
     * Inicializa los componentes y gestiona la carga de partidas guardadas.
     * 
     * @param player Objeto Jugador con la información del usuario
     */
	public FormSudoku(Jugador player) {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // No cerrar automáticamente

        // Agregar un WindowListener
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	GuardarAntesDeCerrar(player);
                // Finalmente, cerrar la ventana
                dispose(); // Cierra la ventana
                // System.exit(0); // Si deseas terminar la aplicación
            }
        });
        setBounds(100, 100, 590, 430); // Configura el tamaño y posición de la ventana
		PanelFondo = new JPanel(); // Crea el panel de fondo
		PanelFondo.setBackground(new Color(242, 174, 37)); // Color de fondo del panel
		PanelFondo.setBorder(new EmptyBorder(5, 5, 400, 240)); // Margen del panel
		setContentPane(PanelFondo); // Asigna el panel de fondo al JFrame
		PanelFondo.setLayout(null); // Establece un diseño nulo para colocar los componentes manualmente
		
        // Cargar la imagen desde el paquete resources
        ImageIcon icono = new ImageIcon(getClass().getClassLoader().getResource("sudoku.png"));
        setIconImage(icono.getImage());
		// Configuración de la etiqueta para mostrar el cronómetro
		lblTimer.setForeground(Color.WHITE);
		lblTimer.setFont(new Font("Arial Black", Font.BOLD, 18)); // Fuente del cronómetro
		lblTimer.setBounds(417, 47, 127, 43); // Posición y tamaño de la etiqueta
		PanelFondo.add(lblTimer); // Añade la etiqueta al panel		
	    // Verificar si hay partida guardada
	    if (player.tienePartidaGuardada() && player.ValidarMatrizGuardada() && player.getDificultad()!=0) {
	        int option = JOptionPane.showConfirmDialog(null, 
	            "Tienes una partida sin terminar. ¿Deseas continuarla?",
	            "Partida guardada", 
	            JOptionPane.YES_NO_OPTION);
	            
	        if (option == JOptionPane.YES_OPTION) {
	            iniciarComponentes(player);  // Primero iniciamos los componentes
	            
	            // Restaurar el tablero generado inicial (números fijos)
	            for(int i = 0; i < 9; i++) {
	                for(int j = 0; j < 9; j++) {
	                    if (player.getSudokuGenerado()[i][j] != 0) {
	                        tableroSudoku.getListaTxt()[i][j].setText(
	                            String.valueOf(player.getSudokuGenerado()[i][j]));
	                        tableroSudoku.getListaTxt()[i][j].setBackground(tableroSudoku.getTxtBackground4());
	                        tableroSudoku.getListaTxt()[i][j].setForeground(tableroSudoku.getTxtForeground4());
	                        tableroSudoku.listaTxtGenerados.add(tableroSudoku.getListaTxt()[i][j]);
	                    }
	                }
	            }
	            
	            // Restaurar los números ingresados por el jugador
	            for(int i = 0; i < 9; i++) {
	                for(int j = 0; j < 9; j++) {
	                    if (player.getSudokuActual()[i][j] != 0 && 
	                        player.getSudokuGenerado()[i][j] == 0) {  // Solo si no es un número generado
	                        tableroSudoku.getListaTxt()[i][j].setText(
	                            String.valueOf(player.getSudokuActual()[i][j]));
	                    }
	                }
	            }
	            
	            // Restaurar el tiempo
	            cronometro.setHor(player.getTiempoHoras());
	            cronometro.setMin(player.getTiempoMinutos());
	            cronometro.setSeg(player.getTiempoSegundos());
	            cronometro.iniciar();
	            return;  // Importante: no llamar a iniciarComponentes de nuevo
	        }
	    }	
		
		// Llamada al método para inicializar otros componentes
	    player.resetearJugador();
		iniciarComponentes(player);
	}

    /**
     * Inicializa los componentes del formulario de Sudoku.
     * 
     * Configura el tablero, botones, cronómetro y otros elementos de la interfaz.
     * 
     * @param player Objeto Jugador con la información del usuario
     */
	public void iniciarComponentes(Jugador player) {
		// Inicialización del tablero de Sudoku
		tableroSudoku = new TableroSudoku();
		tableroSudoku.setTxtLargo(36); // Configura el tamaño de las celdas
		tableroSudoku.setTxtAncho(36);
		tableroSudoku.setTxtMargen(4); // Configura el margen entre celdas
		tableroSudoku.setTxtTamanioLetra(27); // Tamaño de letra para las celdas
		tableroSudoku.setPanelBackground(new Color(89, 43, 25)); // Color de fondo del tablero
		tableroSudoku.setTxtBackground1(Color.WHITE); // Colores de las celdas según el tipo
		tableroSudoku.setTxtForeground1(new Color(255, 128, 0));
		tableroSudoku.setTxtBackground2(new Color(252, 250, 199));
		tableroSudoku.setTxtForeground2(Color.BLACK);
		tableroSudoku.setTxtBackground3(new Color(242, 174, 37));
		tableroSudoku.setTxtForeground3(Color.WHITE);
		tableroSudoku.setTxtBackground4(new Color(255, 237, 81));
		tableroSudoku.setTxtForeground4(Color.BLACK);

		// Añade el tablero de Sudoku al panel y lo hace visible
		PanelFondo.add(tableroSudoku);
		tableroSudoku.setLocation(70, 45); // Posición del tablero en la ventana
		tableroSudoku.setVisible(true); // Hace visible el tablero
		tableroSudoku.crearSudoku(); // Crea el Sudoku inicial

		// Inicialización del tablero de números (botones)
		tableroNumeros = new TableroNumeros();
		tableroNumeros.setTxtLargo(36);
		tableroNumeros.setTxtAncho(36);
		tableroNumeros.setTxtMargen(4);
		tableroNumeros.setTxtTamanioLetra(37);
		tableroNumeros.setPanelBackground(new Color(89, 43, 25));
		tableroNumeros.setTxtBackground1(Color.WHITE);
		tableroNumeros.setTxtForeground1(Color.BLACK);
		tableroNumeros.setTxtBackground2(new Color(252, 250, 199));
		tableroNumeros.setTxtForeground2(Color.BLACK);
		PanelFondo.add(tableroNumeros); // Añade el tablero de números al panel
		tableroNumeros.crearTablero(); // Crea los botones numéricos
		tableroNumeros.setLocation(20, 45); // Posición del tablero de números

		// Configuración del botón "Nueva Partida"
		JButton btnNuevaPartida = new JButton("Nueva Partida");
		btnNuevaPartida.addActionListener(new ActionListener() { // Acción al presionar el botón
			public void actionPerformed(ActionEvent e) {
				if (formniveles != null) {
					formniveles.setVisible(true); // Si ya existe la ventana de niveles, la muestra
				} else {
					formniveles = new FormNiveles(tableroSudoku, cronometro, player); // Crea la ventana de niveles
					formniveles.setVisible(true); // Muestra la ventana de niveles
				}
			}
		});
		// Configura el aspecto visual del botón
		btnNuevaPartida.setBackground(new Color(89, 43, 25));
		btnNuevaPartida.setForeground(Color.WHITE);
		btnNuevaPartida.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnNuevaPartida.setBounds(417, 89, 134, 35);
		PanelFondo.add(btnNuevaPartida);

		// Configuración del botón "Limpiar"
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableroSudoku.limpiar(); // Limpia el tablero de Sudoku
			}
		});
		btnLimpiar.setBackground(new Color(89, 43, 25));
		btnLimpiar.setForeground(Color.WHITE);
		btnLimpiar.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnLimpiar.setBounds(417, 134, 134, 35);
		PanelFondo.add(btnLimpiar);

		// Configuración del botón "Comprobar"
		JButton btnComprobar = new JButton("Comprobar");
		btnComprobar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Si el cronometro no ha sido iniciado se deshabilita el botón
				if (lblTimer.getText().equals("00:00:00:00")) {
					return;
				}else {
					//si el sudoku no es correcto se penaliza con 10 segundos
				if(!tableroSudoku.comprobar()) {
					cronometro.penalizar();
					player.setErrores(player.getErrores() + 1);
					}else{
						cronometro.parar();
		                // Calcular puntaje
						player.setTiempoMinutos(cronometro.getMin());
						player.setTiempoHoras(cronometro.getHor());
		                player.CalcularPuntaje(player.getErrores(), player.getDificultad());
		                player.setDificultad(0);
		                // Guardar puntaje
		                ModificarArchivos.ActualizarJugador(player);
		                
		                JOptionPane.showMessageDialog(null, 
		                    "¡Sudoku completado!\n" +
		                    "Tiempo: " + lblTimer.getText() + "\n" +
		                    "Puntaje: " + player.getPuntaje() + "\n" +
		                    "Errores: " + player.getErrores(),
		                    "Sudoku", JOptionPane.INFORMATION_MESSAGE);						
						tableroSudoku.vaciar();
						cronometro.limpiar();
						tableroSudoku.limpiarTxt();
					}
						
				}
			}
		});
		btnComprobar.setBackground(new Color(89, 43, 25));
		btnComprobar.setForeground(Color.WHITE);
		btnComprobar.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnComprobar.setBounds(417, 179, 134, 35);
		PanelFondo.add(btnComprobar);
		
		// Configuración del botón "Ir al menú"
		JButton btnVolver = new JButton("Ir al menú");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuardarAntesDeCerrar(player);
				//cuando el usuario vuelva al menú se deben almacenar cronometro y y sudoku
				dispose();
			}
		});
		btnVolver.setBackground(new Color(89, 43, 25));
		btnVolver.setForeground(Color.WHITE);
		btnVolver.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnVolver.setBounds(417, 323, 134, 43);
		PanelFondo.add(btnVolver);

		// Configuración de la etiqueta de título
		JLabel lblTitulo = new JLabel("Sudoku");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Arial Black", Font.BOLD, 30));
		lblTitulo.setBounds(51, 0, 147, 43);
		PanelFondo.add(lblTitulo);

		// Hace visible el tablero de números
		tableroNumeros.setVisible(true);
	}

	// Métodos getters y setters
	public Cronometro getCronometro() {
		return cronometro;
	}

	public void setCronometro(Cronometro cronometro) {
		this.cronometro = cronometro;
	}

	public static JLabel getLblTimer() {
		return lblTimer;
	}

	public static void setLblTimer(JLabel lblTimer) {
		FormSudoku.lblTimer = lblTimer;
	}
	public void GuardarAntesDeCerrar(Jugador player) {
        // Guardar estado actual antes de volver al menú
        JTextField[][] tableroActual = tableroSudoku.getListaTxt();
        int[][] matrizActual = new int[9][9];
        
        // Convertir el tablero actual a matriz
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                String valor = tableroActual[i][j].getText();
                matrizActual[i][j] = valor.isEmpty() ? 0 : Integer.parseInt(valor);
            }
        }
        
        // Guardar el estado en el objeto jugador
        player.guardarEstadoPartida(
            matrizActual, 
            player.getSudokuGenerado(),
            cronometro.getHor(),
            cronometro.getMin(),
            cronometro.getSeg(),
            player.getDificultad()
        );
		// al igual que antes se cambian los datos y renueva la base de datos
        // Guardar en archivo
        ModificarArchivos.ActualizarJugador(player);
        
        
        MenuPrincipal volver = new MenuPrincipal();
        volver.setVisible(true);
        cronometro.parar();
        dispose();
		
		
		cronometro.limpiar();
		cronometro.parar();
	}
}
