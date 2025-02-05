package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormSudoku extends JFrame {

	// Definición de variables
	private static final long serialVersionUID = 1L;
	private JPanel PanelFondo; // Panel principal donde se agregan los demás componentes
	public static TableroSudoku tableroSudoku; // Componente para el tablero de Sudoku
	private TableroNumeros tableroNumeros; // Componente para los botones numéricos del Sudoku
	private FormNiveles formniveles; // Componente para la selección de niveles
	private static JLabel lblTimer = new JLabel("00:00:00:00"); // Etiqueta para mostrar el tiempo transcurrido
	Cronometro cronometro = new Cronometro(lblTimer); // Instancia del cronómetro

	// Constructor de la clase FormSudoku, inicializa los componentes
	public FormSudoku() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Establece la acción al cerrar la ventana
		setBounds(100, 100, 590, 430); // Configura el tamaño y posición de la ventana
		PanelFondo = new JPanel(); // Crea el panel de fondo
		PanelFondo.setBackground(new Color(242, 174, 37)); // Color de fondo del panel
		PanelFondo.setBorder(new EmptyBorder(5, 5, 400, 240)); // Margen del panel
		setContentPane(PanelFondo); // Asigna el panel de fondo al JFrame
		PanelFondo.setLayout(null); // Establece un diseño nulo para colocar los componentes manualmente

		// Configuración de la etiqueta para mostrar el cronómetro
		lblTimer.setForeground(Color.WHITE);
		lblTimer.setFont(new Font("Arial Black", Font.BOLD, 18)); // Fuente del cronómetro
		lblTimer.setBounds(417, 47, 127, 43); // Posición y tamaño de la etiqueta
		PanelFondo.add(lblTimer); // Añade la etiqueta al panel

		// Llamada al método para inicializar otros componentes
		iniciarComponentes();
	}

	// Método para inicializar los componentes del formulario
	public void iniciarComponentes() {
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
					formniveles = new FormNiveles(tableroSudoku, cronometro); // Crea la ventana de niveles
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
					}else{
						cronometro.parar();
						JOptionPane.showMessageDialog(null, "Tu tiempo fue de" + lblTimer.getText(),"Sudoku",JOptionPane.INFORMATION_MESSAGE);
						
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
	
}
