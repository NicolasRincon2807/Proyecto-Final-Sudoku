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

public class FormNiveles extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private TableroSudoku tableroSudoku;

	public FormNiveles(TableroSudoku tableroSudoku) {
		
		this.tableroSudoku = tableroSudoku;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(242, 174, 37));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Sudoku");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Arial Black", Font.BOLD, 30));
		lblTitulo.setBounds(73, 11, 147, 43);
		contentPane.add(lblTitulo);
		
		JButton btnFacil = new JButton("Fácil");
		btnFacil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableroSudoku.generarSudoku(1);
				dispose();
				
			}
		});
		btnFacil.setForeground(Color.WHITE);
		btnFacil.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnFacil.setBackground(new Color(89, 43, 25));
		btnFacil.setBounds(73, 75, 134, 35);
		contentPane.add(btnFacil);
		
		JButton btnMedio = new JButton("Medio");
		btnMedio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableroSudoku.generarSudoku(2);
				dispose();
			}
		});
		btnMedio.setForeground(Color.WHITE);
		btnMedio.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnMedio.setBackground(new Color(89, 43, 25));
		btnMedio.setBounds(73, 121, 134, 35);
		contentPane.add(btnMedio);
		
		JButton btnDificil = new JButton("Dificil");
		btnDificil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableroSudoku.generarSudoku(3);
				dispose();
			}
		});
		btnDificil.setForeground(Color.WHITE);
		btnDificil.setFont(new Font("Arial Black", Font.BOLD, 12));
		btnDificil.setBackground(new Color(89, 43, 25));
		btnDificil.setBounds(73, 167, 134, 35);
		contentPane.add(btnDificil);
	}
}
