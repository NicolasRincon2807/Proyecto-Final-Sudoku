
package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import kernel.Sudoku;

/**
 * Clase TableroSudoku
 * 
 * Representa el tablero de Sudoku donde los jugadores completan los números.
 * Controla la validación y la lógica del juego.
 * 
 * Autor: Nicolas Rincon
 * Fecha: 2025-02-10
 */
public class TableroSudoku extends JPanel{

	private static final long serialVersionUID = 1L;
	// Definición de las variables que representan las celdas del Sudoku y sus propiedades visuales
    private JTextField[][] listaTxt;
    private int txtAncho;
    private int txtLargo;
    private int txtMargen;
    private int txtTamanioLetra;
    private Color panelBackground;
    private Color txtBackground1;
    private Color txtForeground1;
    private Color txtBackground2;
    private Color txtForeground2;
    private Color txtBackground3;
    private Color txtForeground3;
    private Color txtBackground4;
    private Color txtForeground4;
    private Sudoku sudoku;
    private ArrayList<JTextField> listaTxtAux;
    ArrayList<JTextField> listaTxtGenerados;
    public JTextField txtSelect;

    /**
     * Constructor de la clase TableroSudoku.
     * Inicializa el tablero del Sudoku y configura su interfaz.
     */
    TableroSudoku(){
        iniciarComponentes();
    }

    // Método para inicializar los componentes del tablero
    public void iniciarComponentes() {
        listaTxt = new JTextField[9][9]; // Matriz de JTextFields para las celdas del Sudoku
        txtAncho = 35;
        txtLargo = 36;
        txtMargen = 4;
        txtTamanioLetra = 27;
        panelBackground = Color.black;
        txtBackground1 = Color.WHITE;
        txtForeground1 = Color.black;
        txtBackground2 = Color.WHITE;
        txtForeground2 = Color.black;
        txtBackground3 = Color.WHITE;
        txtForeground3 = Color.black;
        sudoku = new Sudoku(); // Se instancia el objeto Sudoku
        listaTxtAux = new ArrayList<>(); // Lista auxiliar para celdas seleccionadas
        listaTxtGenerados = new ArrayList<>(); // Lista de celdas generadas
        txtSelect = new JTextField(); // Campo de texto para selección actual
    }

    // Método para configurar y crear el Sudoku en el panel
    public void crearSudoku() {
        this.setLayout(null);
        this.setSize(txtAncho*9 + (txtMargen * 4), txtAncho*9 + (txtMargen * 4));
        this.setBackground(panelBackground);
        crearCamposTxt(); // Crea los campos de texto (celdas)
    }

    // Método que genera los campos de texto (celdas) del Sudoku
    public void crearCamposTxt() {
        int x = txtMargen;
        int y = txtMargen;
        
        // Itera sobre la matriz para crear las celdas y agregarlas al panel
        for (int i = 0; i < listaTxt.length; i++) {
            for (int j = 0; j < listaTxt[0].length; j++) {
                JTextField txt = new JTextField();
                this.add(txt); // Agrega la celda al panel
                txt.setBounds(x, y, txtAncho, txtLargo); // Establece el tamaño y posición
                txt.setBackground(txtBackground1);
                txt.setForeground(txtForeground1);
                txt.setFont(new Font("Arial Black", Font.BOLD, txtTamanioLetra));
                txt.setEditable(false); // Las celdas no son editables directamente
                txt.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambia el cursor al hacer hover
                txt.setBorder(BorderFactory.createLineBorder(panelBackground, 1));
                txt.setVisible(true);
                x += txtAncho; // Avanza en la dirección X
                if((j+1)%3 == 0) {
                    x += txtMargen; // Ajusta el espaciado después de cada bloque 3x3
                }
                listaTxt[i][j] = txt; // Asigna la celda a la matriz
                generarEventos(txt); // Asocia eventos a cada celda
            }
            x = txtMargen; // Reinicia la posición X
            y += txtLargo; // Avanza en la dirección Y
            if((i+1)%3 == 0) {
                y += txtMargen; // Ajusta el espaciado después de cada bloque 3x3
            }
        }
    }

    // Verifica si un JTextField ya ha sido generado (para evitar duplicados)
    public boolean txtGenerado(JTextField txt) {
        for (JTextField jTxt: listaTxtGenerados) {
            if(txt == jTxt) {
                return true;
            }
        }
        return false;
    }

    // Método para generar los eventos de interacción con las celdas
    public void generarEventos(JTextField txt) {
        MouseListener evento = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) { }

            @Override
            public void mousePressed(MouseEvent e) {
                pressed(txt); // Marca la celda como seleccionada
                txtSelect = txt; // Actualiza la celda seleccionada
            }

            @Override
            public void mouseReleased(MouseEvent e) { }

            @Override
            public void mouseEntered(MouseEvent e) { }

            @Override
            public void mouseExited(MouseEvent e) { }
        };

        // Evento para capturar teclas presionadas en la celda
        KeyListener eventoTecla = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { }

            @Override
            public void keyPressed(KeyEvent e) {
            	
            	if(estaVacio()) {//si el sudoku esta sin iniciar, es decir totalmente vacío no se puede ingresar datos
            		return;
            	}
                if(txtGenerado(txt)) {
                    return; // No hace nada si la celda ya está generada
                } else {
                    // Si la tecla presionada es Backspace, borra el texto
                    if(e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                        txt.setText("");
                    }
                    // Si la tecla presionada es un número (1-9), lo coloca en la celda
                    if (e.getKeyChar() >= '1' && e.getKeyChar() <= '9') {
                        txt.setText(String.valueOf(e.getKeyChar()));
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) { }
        };

        // Asocia los eventos al JTextField
        txt.addMouseListener(evento);
        txt.addKeyListener(eventoTecla);
    }

    // Método para manejar la lógica de selección de una celda (marca las celdas relacionadas)
    public void pressed(JTextField txt) {
        // Restaura los colores de las celdas previamente seleccionadas
        for(JTextField jTxt : listaTxtAux) {
            jTxt.setBackground(txtBackground1);
            jTxt.setForeground(txtForeground1);
            jTxt.setBorder(BorderFactory.createLineBorder(panelBackground, 1));
        }
        listaTxtAux.clear();
        
        // Marca las celdas generadas con un color específico
        for(JTextField jTxt : listaTxtGenerados) {
            jTxt.setBackground(txtBackground4);
            jTxt.setForeground(txtForeground4);
        }
        
        // Itera sobre las celdas para marcar las celdas en la misma fila, columna y subcuadrante
        for (int i = 0; i < listaTxt.length; i++) {
            for (int j = 0; j < listaTxt[0].length; j++) {
                if (listaTxt[i][j] == txt) {
                    for (int k = 0; k < listaTxt.length; k++) {
                        listaTxt[k][j].setBackground(txtBackground2);
                        listaTxtAux.add(listaTxt[k][j]);
                    }
                    for (int k = 0; k < listaTxt[0].length; k++) {
                        listaTxt[i][k].setBackground(txtBackground2);
                        listaTxtAux.add(listaTxt[i][k]);
                    }
                    // Marca las celdas del subcuadrante correspondiente
                    int posI = sudoku.subCuadranteActual(i);
                    int posJ = sudoku.subCuadranteActual(j);
                    for (int k = posI - 3; k < posI; k++) {
                        for (int l = posJ - 3; l < posJ; l++) {
                            listaTxt[k][l].setBackground(txtBackground2);
                            listaTxtAux.add(listaTxt[k][l]);
                        }
                    }

                    listaTxt[i][j].setBackground(txtBackground3); // Marca la celda seleccionada
                    listaTxt[i][j].setForeground(txtForeground3);
                    listaTxt[i][j].setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
                    return;
                }
            }
        }
    }

    // Método para generar un Sudoku con un nivel de dificultad específico
    public void generarSudoku(int nivel) {
        listaTxtGenerados.clear();
        limpiarTxt(); // Limpia los valores previos
        sudoku.generarSudoku(nivel); // Genera un nuevo Sudoku
        int[][] sudokuGenerado = sudoku.getSudoku();
        
        // Asigna los valores generados al Sudoku en las celdas correspondientes
        for(int i = 0; i < listaTxt.length; i++) {
            for(int j = 0; j < listaTxt[0].length; j++) {
                if (sudokuGenerado[i][j] != 0) {
                    listaTxt[i][j].setText(String.valueOf(sudokuGenerado[i][j]));
                    listaTxt[i][j].setBackground(txtBackground4);
                    listaTxt[i][j].setForeground(txtForeground4);
                    listaTxtGenerados.add(listaTxt[i][j]);
                }
            }
        }
    }

    // Método para limpiar todas las celdas del Sudoku
    public void limpiarTxt() {
        for(int i = 0; i < listaTxt.length; i++) {
            for(int j = 0; j < listaTxt[0].length; j++) {
                listaTxt[i][j].setText(""); // Borra el texto
                listaTxt[i][j].setBackground(txtBackground1); // Restaura el fondo
                listaTxt[i][j].setForeground(txtForeground1); // Restaura el color de texto
            }
        }
    }
	
	public void limpiar() {
		for(int i = 0;i< listaTxt.length; i++) {
			for(int j = 0;j< listaTxt[0].length; j++) {
				boolean b = false;
				for(JTextField txt: listaTxtGenerados) {
					if(listaTxt[i][j]==txt) {
					b = true;
					break;
						}
					}
				if(!b) {
					listaTxt[i][j].setText("");
				}
			}
		}
	}
	
	public void vaciar() {
		for(int i = 0;i< listaTxt.length; i++) {
			for(int j = 0;j< listaTxt[0].length; j++) {
				listaTxt[i][j].setText("");
				}	
			}
	}
	
	public boolean comprobar() {
		int sudo[][] = new int [9][9];
		for(int i = 0;i< listaTxt.length; i++) {
			for(int j = 0;j< listaTxt[0].length; j++) {
					if(listaTxt[i][j].getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Sudoku incompleto (+10 Seg)","Error",JOptionPane.ERROR_MESSAGE);
						return false;
					}
					sudo[i][j]=Integer.parseInt(listaTxt[i][j].getText());
				}	
			}
		sudoku.setSudoku(sudo);
		if(sudoku.ComprobarSudoku()) {
			JOptionPane.showMessageDialog(null, "Sudoku correcto!","Sudoku",JOptionPane.INFORMATION_MESSAGE);
			return true;
		}
		else {
			JOptionPane.showMessageDialog(null, "Sudoku incorrecto! (+10 Seg)","Sudoku",JOptionPane.INFORMATION_MESSAGE);
			return false;
		}
	}
	
	public boolean estaVacio() {
	    if (listaTxt == null) {
	        return true;  // El arreglo no ha sido inicializado.
	    }

	    for (int i = 0; i < listaTxt.length; i++) {
	        for (int j = 0; j < listaTxt[i].length; j++) {
	            // Verificamos si alguna celda tiene un valor distinto de vacío
	            if (!listaTxt[i][j].getText().isEmpty()) {
	                return false;  // Si alguna celda tiene texto, el Sudoku no está vacío
	            }
	        }
	    }

	    return true;  // Si todas las celdas están vacías
	}
	
	public Color getTxtBackground4() {
		return txtBackground4;
	}

	public void setTxtBackground4(Color txtBackground4) {
		this.txtBackground4 = txtBackground4;
	}

	public Color getTxtForeground4() {
		return txtForeground4;
	}

	public void setTxtForeground4(Color txtForeground4) {
		this.txtForeground4 = txtForeground4;
	}

	public JTextField[][] getListaTxt() {
		return listaTxt;
	}

	public void setListaTxt(JTextField[][] listaTxt) {
		this.listaTxt = listaTxt;
	}

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
	}

	public Color getTxtBackground3() {
		return txtBackground3;
	}

	public void setTxtBackground3(Color txtBackground3) {
		this.txtBackground3 = txtBackground3;
	}

	public Color getTxtForeground3() {
		return txtForeground3;
	}

	public void setTxtForeground3(Color txtForeground3) {
		this.txtForeground3 = txtForeground3;
	}
	
}
