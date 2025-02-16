package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import kernel.Jugador;
/**
 * Formulario de inicio para el juego de Sudoku.
 * 
 * Esta clase gestiona la pantalla inicial donde el jugador puede 
 * comenzar una nueva partida o volver al menú principal.
 * 
 * @author Nicolás Rincón
 * @version 1.0
 * @since 2025-02-10
 */
public class FormIniciar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


    /**
     * Constructor para crear el formulario de inicio.
     * 
     * @param jugador Objeto Jugador con la información del usuario actual
     */
	public FormIniciar(Jugador jugador) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(242, 174, 37));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
        // Cargar la imagen desde el paquete resources
        ImageIcon icono = new ImageIcon(getClass().getClassLoader().getResource("sudoku.png"));
        setIconImage(icono.getImage());
		
        // Cargar la imagen desde el paquete resources
        ImageIcon start = new ImageIcon(getClass().getClassLoader().getResource("start.png"));
		
		JLabel startimg = new JLabel(start);
		startimg.setBounds(268, 76, 181, 177);
		contentPane.add(startimg);
		
        // Cargar la imagen desde el paquete resources
        ImageIcon menu = new ImageIcon(getClass().getClassLoader().getResource("preparados.png"));
		
		JLabel menuimg = new JLabel(menu);
		menuimg.setBounds(26, 43, 126, 117);
		contentPane.add(menuimg);
		
		JLabel lblBienvenida = new JLabel("Bienvenido jugador " + jugador.getNickName());
		lblBienvenida.setBounds(43, 10, 393, 43);
		lblBienvenida.setForeground(Color.WHITE);
		lblBienvenida.setFont(new Font("Arial Black", Font.PLAIN, 22));
		contentPane.add(lblBienvenida);
		
		JButton btnSalir = new JButton("Volver");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal menu = new MenuPrincipal();
				menu.setVisible(true);
				dispose();
			}
		});
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setFont(new Font("Arial Black", Font.PLAIN, 12));
		btnSalir.setBackground(new Color(255, 0, 0));
		btnSalir.setBounds(157, 218, 134, 35);
		contentPane.add(btnSalir);
		
		JLabel lblqueDeseasHacer = new JLabel("¿Preparado?");
		lblqueDeseasHacer.setForeground(Color.WHITE);
		lblqueDeseasHacer.setFont(new Font("Arial Black", Font.PLAIN, 22));
		lblqueDeseasHacer.setBounds(153, 39, 161, 43);
		contentPane.add(lblqueDeseasHacer);
		
		JButton btnNueva = new JButton("Iniciar Partida");
		btnNueva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormSudoku nuevoSudoku = new FormSudoku(jugador);
				nuevoSudoku.setVisible(true);
				dispose();
			}
		});
		btnNueva.setForeground(Color.WHITE);
		btnNueva.setFont(new Font("Arial Black", Font.PLAIN, 12));
		btnNueva.setBackground(new Color(89, 43, 25));
		btnNueva.setBounds(157, 125, 134, 35);
		contentPane.add(btnNueva);
	}
}
