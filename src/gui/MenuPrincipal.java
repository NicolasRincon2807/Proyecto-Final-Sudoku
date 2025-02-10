package gui;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import kernel.Jugador;
import persistencia.ModificarArchivos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.awt.event.ActionEvent;

/**
 * Clase MenuPrincipal
 * 
 * Esta clase representa la ventana principal del juego de Sudoku.
 * Permite a los jugadores iniciar sesión, registrarse, ver las reglas, consultar puntajes y salir del juego.
 * 
 * Autor: Nicolas Rincon
 * Fecha: 2025-02-10
 */
public class MenuPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

    /**
     * Constructor de la clase MenuPrincipal.
     * Configura la interfaz gráfica y los botones de navegación.
     */
	public MenuPrincipal() {
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
        
		JLabel lblMenPrincipakl = new JLabel("Menú");
		lblMenPrincipakl.setBounds(176, 10, 95, 43);
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
		btnIniciarSesion.setBounds(10, 63, 134, 35);
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
		btnCrearUsuario.setBounds(10, 163, 134, 35);
		contentPane.add(btnCrearUsuario);
		
		JButton btnReglas = new JButton("Reglas");
		btnReglas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReglasSudoku reglas = new ReglasSudoku();
				reglas.setVisible(true);
				dispose();
			}
		});
		btnReglas.setForeground(Color.WHITE);
		btnReglas.setFont(new Font("Arial Black", Font.PLAIN, 12));
		btnReglas.setBackground(new Color(89, 43, 25));
		btnReglas.setBounds(292, 63, 134, 35);
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
		btnSalir.setBounds(156, 218, 134, 35);
		contentPane.add(btnSalir);
		
		JButton btnTabla = new JButton("Puntajes");
		btnTabla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MostrarDatos();
			}
		});
		btnTabla.setForeground(Color.WHITE);
		btnTabla.setFont(new Font("Arial Black", Font.PLAIN, 12));
		btnTabla.setBackground(new Color(89, 43, 25));
		btnTabla.setBounds(292, 163, 134, 35);
		contentPane.add(btnTabla);
		
        // Cargar la imagen desde el paquete resources
        ImageIcon menu = new ImageIcon(getClass().getClassLoader().getResource("menu1.png"));
		
		JLabel menuimg = new JLabel(menu);
		menuimg.setBounds(133, 63, 181, 177);
		contentPane.add(menuimg);
		

	}
	
    /**
     * Organiza la lista de jugadores según su puntaje en orden descendente.
     * @return Lista de jugadores ordenados.
     */
	public List<Jugador> OrganizarJugadores() {
		List<Jugador> jugadores = new ArrayList<>();
		List<Jugador> jugadoresOrganizados = new ArrayList<>();

		ModificarArchivos.LeerJugadores(jugadores);
		for(Jugador jugador : jugadores) {
			if (jugador.getPuntaje()>0) {
				jugadoresOrganizados.add(jugador);
			}
		}
		Collections.sort(jugadoresOrganizados);
		return jugadoresOrganizados;
	}
    /**
     * Muestra los datos de los jugadores en una tabla ordenada.
     */
	public void MostrarDatos() {
	    List<Jugador> jugadores = OrganizarJugadores();
	    String[] columnNames = {"Usuario", "Puntaje"};
	    Object[][] data = new Object[jugadores.size()][2];

	    for (int i = 0; i < jugadores.size(); i++) {
	        Jugador jugador = jugadores.get(i);
	        data[i][0] = jugador.getNickName();
	        data[i][1] = jugador.getPuntaje();
	    }

	    // Crear la tabla y personalizar su apariencia
	    @SuppressWarnings("serial")
		JTable table = new JTable(data, columnNames) {
	        @Override
	        public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
	            Component comp = super.prepareRenderer(renderer, row, column);
	            // Alternar colores de las filas
	            if (!isRowSelected(row)) {
	                comp.setBackground(row % 2 == 0 ? new Color(255, 243, 224) : new Color(255, 236, 209));
	            }
	            return comp;
	        }
	    };
	    

	    // Personalizar el encabezado de la tabla
	    JTableHeader header = table.getTableHeader();
	    header.setBackground(new Color(242, 174, 37));
	    header.setForeground(Color.WHITE);
	    header.setFont(new Font("Cooper Black", Font.PLAIN, 14));

	    // Personalizar la fuente de las celdas
	    table.setFont(new Font("Arial", Font.PLAIN, 12));
	    table.setRowHeight(25);
	    
	    // Deshabilitar la reordenación de columnas
	    table.getTableHeader().setReorderingAllowed(false);
	    
	    // Centrar el contenido de las celdas
	    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	    centerRenderer.setHorizontalAlignment(JLabel.CENTER);
	    table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
	    table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

	    JButton boton = new JButton("Volver");
	    boton.setBackground(new Color(242, 174, 37));
	    boton.setForeground(Color.WHITE);
	    boton.setFont(new Font("Cooper Black", Font.PLAIN, 16));
	    boton.setFocusPainted(false);
	    boton.setBorder(BorderFactory.createRaisedBevelBorder());

	    // Crear un panel con BorderLayout
	    JPanel panel = new JPanel(new BorderLayout(10, 10));
	    panel.setBackground(new Color(255, 248, 225));
	    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	    
        
	    // Agregar el JTable dentro de un JScrollPane
	    JScrollPane scrollPane = new JScrollPane(table);
	    scrollPane.getViewport().setBackground(new Color(255, 248, 225));
	    scrollPane.setBorder(BorderFactory.createLineBorder(new Color(242, 174, 37), 1));
	    panel.add(scrollPane, BorderLayout.CENTER);

	    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	    buttonPanel.setBackground(new Color(255, 248, 225));
	    buttonPanel.add(boton);
	    panel.add(buttonPanel, BorderLayout.SOUTH);
	    
        // Cargar la imagen desde el paquete resources
        ImageIcon icono = new ImageIcon(getClass().getClassLoader().getResource("sudoku.png"));
        setIconImage(icono.getImage());
	    JFrame frame = new JFrame("Lista de Puntajes");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(600, 400);
	    frame.getContentPane().add(panel);

	    boton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            frame.dispose();
	        }
	    });

	    // Centrar la ventana en la pantalla
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	}
}
