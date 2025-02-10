package gui;


import javax.swing.JFrame;
import javax.swing.JPanel;
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
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
/**
 * Clase Register
 * 
 * Permite a los usuarios registrarse en el sistema.
 * Se validan las credenciales y se almacenan en un archivo.
 * 
 * Autor: Nicolas Rincon
 * Fecha: 2025-02-10
 */
public class Register extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUserName;
	private JTextField txtContraseña;
	private JTextField txtConfirmarContraseña;

    /**
     * Constructor de la clase Register.
     * Configura la interfaz gráfica para el registro de usuario.
     */
	public Register() {
		ModificarArchivos.validarYCrearArchivo("archivo6.txt");
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

		JLabel lblCrearCuenta = new JLabel("Crear Cuenta");
		lblCrearCuenta.setBounds(105, 20, 246, 43);
		lblCrearCuenta.setForeground(Color.WHITE);
		lblCrearCuenta.setFont(new Font("Arial Black", Font.PLAIN, 30));
		contentPane.add(lblCrearCuenta);
		
        // Cargar la imagen desde el paquete resources
        ImageIcon menu = new ImageIcon(getClass().getClassLoader().getResource("registrar.png"));
		
		JLabel menuimg = new JLabel(menu);
		menuimg.setBounds(-19, -48, 181, 177);
		contentPane.add(menuimg);

		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal menu = new MenuPrincipal();
				menu.setVisible(true);
				dispose();
			}
		});
		btnVolver.setForeground(Color.WHITE);
		btnVolver.setFont(new Font("Arial Black", Font.PLAIN, 12));
		btnVolver.setBackground(new Color(255, 0, 0));
		btnVolver.setBounds(10, 218, 134, 35);
		contentPane.add(btnVolver);

		txtUserName = new JTextField();
		txtUserName.setFont(new Font("Arial", Font.PLAIN, 15));
		txtUserName.setBounds(275, 96, 134, 28);
		contentPane.add(txtUserName);
		txtUserName.setColumns(10);

		txtContraseña = new JTextField();
		txtContraseña.setFont(new Font("Arial", Font.PLAIN, 15));
		txtContraseña.setColumns(10);
		txtContraseña.setBounds(275, 134, 134, 28);
		contentPane.add(txtContraseña);

		JLabel lblUserName = new JLabel("Nombre:");
		lblUserName.setForeground(Color.WHITE);
		lblUserName.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblUserName.setBounds(10, 86, 93, 43);
		contentPane.add(lblUserName);

		JLabel lblContraseña = new JLabel("Contraseña:");
		lblContraseña.setForeground(Color.WHITE);
		lblContraseña.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblContraseña.setBounds(10, 124, 134, 43);
		contentPane.add(lblContraseña);

		JLabel lblConfirmarContraseña = new JLabel("Confirmar contraseña:");
		lblConfirmarContraseña.setForeground(Color.WHITE);
		lblConfirmarContraseña.setFont(new Font("Arial Black", Font.PLAIN, 20));
		lblConfirmarContraseña.setBounds(13, 165, 252, 43);
		contentPane.add(lblConfirmarContraseña);

		txtConfirmarContraseña = new JTextField();
		txtConfirmarContraseña.setFont(new Font("Arial", Font.PLAIN, 15));
		txtConfirmarContraseña.setColumns(10);
		txtConfirmarContraseña.setBounds(275, 175, 134, 28);
		contentPane.add(txtConfirmarContraseña);

		JButton btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//se valida que no este vacío ninguno de los espacios
					Excepciones.validarEntrada(txtUserName.getText());
					Excepciones.validarEntrada(txtContraseña.getText());
					Excepciones.validarEntrada(txtConfirmarContraseña.getText());
					
					//si la contraseña ingresada y su confirmación son la misma se crea el jugador
					if(txtContraseña.getText().equals(txtConfirmarContraseña.getText())) {
						List<Jugador> jugadores = new ArrayList<>();

						ModificarArchivos.LeerJugadores(jugadores);
						
						if(!ModificarArchivos.ValidarNickName(jugadores, txtUserName.getText())) {
						Jugador nuevo = new Jugador(txtUserName.getText(),txtContraseña.getText());
						
						ModificarArchivos.GuardarJugador(nuevo);
						
						JOptionPane.showMessageDialog(null, "El jugador ha sido creado con éxito");
						//se envía al usuario al menú para que cargue o inicie una partida nueva
						
						FormIniciar avanzar = new FormIniciar(nuevo);
						avanzar.setVisible(true);
						dispose();
						
						}else {
							JOptionPane.showMessageDialog(null, "Error el usuario se encuentra registrado");
						}
					}else {
						//en caso contrario no se crea
						JOptionPane.showMessageDialog(null, "Error: la contraseña no coincide");
					}
					//se capturan todas las posibles excepciones
				} catch (NullPointerException f) {
					JOptionPane.showMessageDialog(null, "Error: Llena todos los espacios");
				} catch (DatosFaltantesException e1) {
					JOptionPane.showMessageDialog(null, "Error: Llena todos los espacios");
				}
			}
		});
		btnCrear.setForeground(Color.WHITE);
		btnCrear.setFont(new Font("Arial Black", Font.PLAIN, 12));
		btnCrear.setBackground(new Color(0, 255, 0));
		btnCrear.setBounds(275, 218, 134, 35);
		contentPane.add(btnCrear);
	}
}
