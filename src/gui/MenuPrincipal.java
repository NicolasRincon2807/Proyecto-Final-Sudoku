package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(242, 174, 37));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMenPrincipakl = new JLabel("Menú");
		lblMenPrincipakl.setBounds(167, 10, 103, 43);
		lblMenPrincipakl.setForeground(Color.WHITE);
		lblMenPrincipakl.setFont(new Font("Arial Black", Font.PLAIN, 30));
		contentPane.add(lblMenPrincipakl);
		
		JButton btnIniciarSesion = new JButton("Iniciar Sesión");
		btnIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login iniciar = new Login();
				iniciar.setVisible(true);
				dispose();
			}
		});
		btnIniciarSesion.setForeground(Color.WHITE);
		btnIniciarSesion.setFont(new Font("Arial Black", Font.PLAIN, 12));
		btnIniciarSesion.setBackground(new Color(89, 43, 25));
		btnIniciarSesion.setBounds(150, 63, 134, 35);
		contentPane.add(btnIniciarSesion);
		
		JButton btnCrearUsuario = new JButton("Crear usuario");
		btnCrearUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register registrar = new Register();
				registrar.setVisible(true);
				dispose();
			}
		});
		btnCrearUsuario.setForeground(Color.WHITE);
		btnCrearUsuario.setFont(new Font("Arial Black", Font.PLAIN, 12));
		btnCrearUsuario.setBackground(new Color(89, 43, 25));
		btnCrearUsuario.setBounds(150, 108, 134, 35);
		contentPane.add(btnCrearUsuario);
		
		JButton btnReglas = new JButton("Reglas");
		btnReglas.setForeground(Color.WHITE);
		btnReglas.setFont(new Font("Arial Black", Font.PLAIN, 12));
		btnReglas.setBackground(new Color(89, 43, 25));
		btnReglas.setBounds(150, 153, 134, 35);
		contentPane.add(btnReglas);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnSalir.setForeground(Color.WHITE);
		btnSalir.setFont(new Font("Arial Black", Font.PLAIN, 12));
		btnSalir.setBackground(new Color(255, 0, 0));
		btnSalir.setBounds(10, 218, 134, 35);
		contentPane.add(btnSalir);
	}
}
