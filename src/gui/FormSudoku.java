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
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormSudoku extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel PanelFondo;
	private TableroSudoku tableroSudoku;
	private TableroNumeros tableroNumeros;
	private FormNiveles formniveles;
	
	public FormSudoku() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 590, 430);
		PanelFondo = new JPanel();
		PanelFondo.setBackground(new Color(242, 174, 37));
		PanelFondo.setBorder(new EmptyBorder(5, 5, 400, 240));
		setContentPane(PanelFondo);
		PanelFondo.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Sudoku");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Arial Black", Font.BOLD, 30));
		lblTitulo.setBounds(10, 0, 147, 43);
		PanelFondo.add(lblTitulo);
		iniciarComponentes();
	}
	
	public void iniciarComponentes(){
		tableroSudoku = new TableroSudoku(); 
		tableroSudoku.setTxtLargo (36);
		tableroSudoku.setTxtAncho(36);
		tableroSudoku.setTxtMargen(4);
		tableroSudoku.setTxtTamanioLetra (27);
		tableroSudoku.setPanelBackground(new Color(89, 43, 25));
		tableroSudoku.setTxtBackground1(Color.WHITE);
		tableroSudoku.setTxtForeground1(Color.BLACK);
		tableroSudoku.setTxtBackground2(new Color(252, 250, 199));
		tableroSudoku.setTxtForeground2(Color.BLACK);
		tableroSudoku.setTxtBackground3 (new Color (242, 174, 37)); 
		tableroSudoku.setTxtForeground3 (Color.WHITE);
		PanelFondo.add(tableroSudoku);
		tableroSudoku.setLocation(70,45);
		tableroSudoku.setVisible(true);
		tableroSudoku.crearSudoku();
		
		tableroNumeros = new TableroNumeros();
		tableroNumeros.setTxtLargo (36);
		tableroNumeros.setTxtAncho(36);
		tableroNumeros.setTxtMargen(4);
		tableroNumeros.setTxtTamanioLetra (37);
		tableroNumeros.setPanelBackground(new Color(89, 43, 25));
		tableroNumeros.setTxtBackground1(Color.WHITE);
		tableroNumeros.setTxtForeground1(Color.BLACK);
		tableroNumeros.setTxtBackground2(new Color(252, 250, 199));
		tableroNumeros.setTxtForeground2(Color.BLACK);
		PanelFondo.add(tableroNumeros);
		tableroNumeros.crearTablero();
		tableroNumeros.setLocation(20,45);
		
		JButton btnNuevaPartida = new JButton("Nueva Partida");
		btnNuevaPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (formniveles != null) {
					formniveles.setVisible(true);
				}else {
					formniveles = new FormNiveles(tableroSudoku);
					formniveles.setVisible(true);
				}
				
				
			}
		});
		btnNuevaPartida.setBackground(new Color(89, 43, 25));//
		btnNuevaPartida.setForeground(Color.WHITE);
		btnNuevaPartida.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnNuevaPartida.setBounds(417, 45, 134, 35);
		PanelFondo.add(btnNuevaPartida);
		
		JButton btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBackground(new Color(89, 43, 25));
		btnLimpiar.setForeground(Color.WHITE);
		btnLimpiar.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnLimpiar.setBounds(417, 91, 134, 35);
		PanelFondo.add(btnLimpiar);
		
		JButton btnComprobar = new JButton("Comprobar");
		btnComprobar.setBackground(new Color(89, 43, 25));
		btnComprobar.setForeground(Color.WHITE);
		btnComprobar.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnComprobar.setBounds(417, 137, 134, 35);
		PanelFondo.add(btnComprobar);
		
		JButton btnVolver = new JButton("Ir al menú");
		btnVolver.setBackground(new Color(89, 43, 25));
		btnVolver.setForeground(Color.WHITE);
		btnVolver.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnVolver.setBounds(417, 323, 134, 43);
		PanelFondo.add(btnVolver);
		tableroNumeros.setVisible(true);
		}
}
