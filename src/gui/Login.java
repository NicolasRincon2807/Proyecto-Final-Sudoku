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
import javax.swing.JTextField;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUserName;
	private JTextField textField;

	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(242, 174, 37));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIniciarSesion = new JLabel("Inicia Sesión");
		lblIniciarSesion.setBounds(106, 10, 246, 43);
		lblIniciarSesion.setForeground(Color.WHITE);
		lblIniciarSesion.setFont(new Font("Arial Black", Font.BOLD, 30));
		contentPane.add(lblIniciarSesion);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPrincipal menu = new MenuPrincipal();
				menu.setVisible(true);
				dispose();
			}
		});
		btnVolver.setForeground(Color.WHITE);
		btnVolver.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnVolver.setBackground(new Color(255, 0, 0));
		btnVolver.setBounds(10, 218, 134, 35);
		contentPane.add(btnVolver);
		
		txtUserName = new JTextField();
		txtUserName.setFont(new Font("Arial", Font.PLAIN, 15));
		txtUserName.setBounds(243, 85, 134, 28);
		contentPane.add(txtUserName);
		txtUserName.setColumns(10);
		
		textField = new JTextField();
		textField.setFont(new Font("Arial", Font.PLAIN, 15));
		textField.setColumns(10);
		textField.setBounds(243, 138, 134, 28);
		contentPane.add(textField);
		
		JLabel lblUserName = new JLabel("Nombre:");
		lblUserName.setForeground(Color.WHITE);
		lblUserName.setFont(new Font("Arial Black", Font.PLAIN, 25));
		lblUserName.setBounds(106, 71, 121, 43);
		contentPane.add(lblUserName);
		
		JLabel lblContraseña = new JLabel("Contraseña:");
		lblContraseña.setForeground(Color.WHITE);
		lblContraseña.setFont(new Font("Arial Black", Font.PLAIN, 25));
		lblContraseña.setBounds(48, 124, 214, 43);
		contentPane.add(lblContraseña);
		
		JButton btnIniciar = new JButton("Iniciar");
		btnIniciar.setForeground(Color.WHITE);
		btnIniciar.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnIniciar.setBackground(new Color(0, 255, 0));
		btnIniciar.setBounds(277, 218, 134, 35);
		contentPane.add(btnIniciar);
	}
}
