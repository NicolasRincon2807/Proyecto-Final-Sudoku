package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TableroNumeros extends JPanel{// Definición de variables que configuran el aspecto y comportamiento de los componentes
	private static final long serialVersionUID = 1L;
	private int txtAncho; // Ancho de cada campo de texto
	private int txtLargo; // Alto de cada campo de texto
	private int txtMargen; // Margen entre los campos de texto
	private int txtTamanioLetra; // Tamaño de la letra en los campos de texto
	private Color panelBackground; // Color de fondo del panel
	private Color txtBackground1; // Color de fondo inicial de los campos de texto
	private Color txtForeground1; // Color de texto inicial de los campos de texto
	private Color txtBackground2; // Color de fondo cuando el mouse entra al campo de texto
	private Color txtForeground2; // Color de texto cuando el mouse entra al campo de texto
	private TableroSudoku tableroSudoku; // Instancia del tablero Sudoku asociado

	// Constructor de la clase
	public TableroNumeros() {
	    // Inicializa los componentes del tablero
	    iniciarComponentes();
	    // Asocia el tablero Sudoku
	    tableroSudoku = FormSudoku.tableroSudoku;
	}

	// Método que configura los valores predeterminados para los componentes
	public void iniciarComponentes() {
	    txtAncho = 30; // Ancho del campo de texto
	    txtLargo = 30; // Largo del campo de texto
	    txtMargen = 4; // Margen entre los campos
	    txtTamanioLetra = 27; // Tamaño de la letra
	    panelBackground = Color.black; // Fondo del panel en color negro
	    txtBackground1 = Color.WHITE; // Fondo inicial de los campos de texto en blanco
	    txtForeground1 = Color.black; // Texto de los campos de texto en negro
	    txtBackground2 = Color.WHITE; // Fondo cuando el mouse pasa sobre el campo (blanco)
	    txtForeground2 = Color.black; // Color del texto cuando el mouse pasa sobre el campo (negro)
	}

	// Método que configura el panel con un layout nulo y tamaño adecuado
	public void crearTablero() {
	    // Establece el layout a null, para definir las posiciones de los componentes manualmente
	    this.setLayout(null);
	    // Define el tamaño del panel con base en el tamaño de los campos
	    this.setSize(txtAncho + (2 * txtMargen), txtLargo * 9 + (4 * txtMargen));
	    // Establece el color de fondo del panel
	    this.setBackground(panelBackground);
	    // Llama al método que crea los campos de texto
	    crearCamposTxt();
	}

	// Método que crea los 9 campos de texto que representarán los números
	public void crearCamposTxt() {
	    // Establece la posición inicial (margen superior izquierdo)
	    int x = txtMargen;
	    int y = txtMargen;

	    // Crea 9 campos de texto, uno para cada número
	    for (int i = 0; i < 9; i++) {
	        // Crea un nuevo JTextField (campo de texto)
	        JTextField txt = new JTextField();
	        // Agrega el campo de texto al panel
	        this.add(txt);
	        // Establece la posición y tamaño del campo de texto
	        txt.setBounds(x, y, txtAncho, txtLargo);
	        // Establece el color de fondo y texto del campo
	        txt.setBackground(panelBackground);
	        txt.setForeground(txtForeground1);
	        // Cambia el cursor a una mano al pasar sobre el campo
	        txt.setCursor(new Cursor(Cursor.HAND_CURSOR));
	        // Hace que el campo de texto no sea editable
	        txt.setEditable(false);
	        // Establece un borde delgado del color de fondo
	        txt.setBorder(BorderFactory.createLineBorder(panelBackground, 1));
	        // Establece la fuente del texto
	        txt.setFont(new Font("Arial Black", Font.BOLD, txtTamanioLetra));
	        // Asigna el número del campo de texto (i+1) como texto
	        txt.setText(String.valueOf(i + 1));

	        // Ajusta la posición vertical para el siguiente campo
	        y += txtLargo;
	        // Cada 3 campos, agrega un margen entre ellos
	        if ((i + 1) % 3 == 0) {
	            y += txtMargen;
	        }

	        // Llama al método para generar eventos para cada campo
	        generarEventosTxt(txt);
	    }
	}

	// Método que genera los eventos de ratón para los campos de texto
	public void generarEventosTxt(JTextField txt) {
	    // Crea un MouseListener para manejar los eventos del ratón
	    MouseListener evento = new MouseListener() {

	        @Override
	        public void mouseClicked(MouseEvent e) {
	            // No hace nada cuando el campo de texto es clickeado
	        }

	        @Override
	        public void mousePressed(MouseEvent e) {
	            // Si el texto ya fue generado, no hace nada
	            if (tableroSudoku.txtGenerado(tableroSudoku.txtSelect)) {
	                return;
	            }else if (tableroSudoku.estaVacio()) {
	            	return;
	            }
	            // Si no, asigna el texto del campo de texto al texto seleccionado
	            tableroSudoku.txtSelect.setText(txt.getText());
	        }

	        @Override
	        public void mouseReleased(MouseEvent e) {
	            // No hace nada cuando el mouse es liberado
	        }

	        @Override
	        public void mouseEntered(MouseEvent e) {
	            // Cambia el fondo y el color del texto al pasar el mouse sobre el campo
	            txt.setBackground(txtBackground2);
	            txt.setForeground(txtForeground2);
	        }

	        @Override
	        public void mouseExited(MouseEvent e) {
	            // Restaura el fondo y el color del texto cuando el mouse sale del campo
	            txt.setBackground(txtBackground1);
	            txt.setForeground(txtForeground1);
	        }

	    };
	    // Agrega el MouseListener al campo de texto
	    txt.addMouseListener(evento);
	}

	// Métodos getter y setter para los atributos privados de la clase
	public int getTxtAncho() {
	    return txtAncho;
	}
	public void setTxtAncho(int txtAncho) {
	    this.txtAncho = txtAncho;
	}
	public int getTxtLargo() {
	    return txtLargo;
	}
	public void setTxtLargo(int txtLargo) {
	    this.txtLargo = txtLargo;
	}
	public int getTxtMargen() {
	    return txtMargen;
	}
	public void setTxtMargen(int txtMargen) {
	    this.txtMargen = txtMargen;
	}
	public int getTxtTamanioLetra() {
	    return txtTamanioLetra;
	}
	public void setTxtTamanioLetra(int txtTamanioLetra) {
	    this.txtTamanioLetra = txtTamanioLetra;
	}
	public Color getPanelBackground() {
	    return panelBackground;
	}
	public void setPanelBackground(Color panelBackground) {
	    this.panelBackground = panelBackground;
	}
	public Color getTxtBackground1() {
	    return txtBackground1;
	}
	public void setTxtBackground1(Color txtBackground1) {
	    this.txtBackground1 = txtBackground1;
	}
	public Color getTxtForeground1() {
	    return txtForeground1;
	}
	public void setTxtForeground1(Color txtForeground1) {
	    this.txtForeground1 = txtForeground1;
	}
	public Color getTxtBackground2() {
	    return txtBackground2;
	}
	public void setTxtBackground2(Color txtBackground2) {
	    this.txtBackground2 = txtBackground2;
	}
	public Color getTxtForeground2() {
	    return txtForeground2;
	}
	public void setTxtForeground2(Color txtForeground2) {
	    this.txtForeground2 = txtForeground2;
	}}


