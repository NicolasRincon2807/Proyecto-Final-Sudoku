package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import kernel.Jugador;

public class FormIniciar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;


	public FormIniciar(Jugador jugador) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(242, 174, 37));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		
		JLabel lblqueDeseasHacer = new JLabel("¿que deseas hacer?");
		lblqueDeseasHacer.setForeground(Color.WHITE);
		lblqueDeseasHacer.setFont(new Font("Arial Black", Font.PLAIN, 22));
		lblqueDeseasHacer.setBounds(104, 38, 254, 43);
		contentPane.add(lblqueDeseasHacer);
		
		JButton btnCargar = new JButton("Cargar partida");
		btnCargar.setForeground(Color.WHITE);
		btnCargar.setFont(new Font("Arial Black", Font.PLAIN, 12));
		btnCargar.setBackground(new Color(89, 43, 25));
		btnCargar.setBounds(157, 106, 134, 35);
		contentPane.add(btnCargar);
		
		JButton btnNueva = new JButton("Nueva partida");
		btnNueva.setForeground(Color.WHITE);
		btnNueva.setFont(new Font("Arial Black", Font.PLAIN, 12));
		btnNueva.setBackground(new Color(89, 43, 25));
		btnNueva.setBounds(157, 151, 134, 35);
		contentPane.add(btnNueva);
	}

}
