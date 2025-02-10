/**
 * La clase Login representa una ventana de inicio de sesión para un sistema de gestión de jugadores.
 * Esta ventana permite a los usuarios ingresar su nombre de usuario y contraseña para acceder al sistema.
 * Si las credenciales son válidas, el usuario es redirigido a la ventana principal del sistema.
 * Si las credenciales son incorrectas, se muestra un mensaje de error.
 * 
 * La ventana contiene campos de texto para ingresar el nombre de usuario y la contraseña,
 * así como botones para iniciar sesión y volver al menú principal.
 * 
 * @author Nicolás Rincón
 * @version 1.0
 * @since 27 de octubre de 2023
 */
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

public class Login extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane; // Panel principal que contiene todos los componentes de la ventana.
    private JTextField txtUserName; // Campo de texto para ingresar el nombre de usuario.
    private JTextField txtContraseña; // Campo de texto para ingresar la contraseña.

    /**
     * Constructor de la clase Login.
     * Inicializa la ventana de inicio de sesión con todos sus componentes.
     */
    public Login() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(242, 174, 37));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        // Cargar la imagen desde el paquete resources
        ImageIcon icono = new ImageIcon(getClass().getClassLoader().getResource("sudoku.png"));
        setIconImage(icono.getImage());
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Cargar la imagen desde el paquete resources
        ImageIcon sesion = new ImageIcon(getClass().getClassLoader().getResource("iniciosesion.png"));

        JLabel menuimg = new JLabel(sesion);
        menuimg.setBounds(313, 10, 64, 65);
        contentPane.add(menuimg);

        JLabel lblIniciarSesion = new JLabel("Inicia Sesión");
        lblIniciarSesion.setBounds(94, 18, 246, 43);
        lblIniciarSesion.setForeground(Color.WHITE);
        lblIniciarSesion.setFont(new Font("Arial Black", Font.PLAIN, 30));
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
        btnVolver.setFont(new Font("Arial Black", Font.PLAIN, 12));
        btnVolver.setBackground(new Color(255, 0, 0));
        btnVolver.setBounds(10, 218, 134, 35);
        contentPane.add(btnVolver);

        txtUserName = new JTextField();
        txtUserName.setFont(new Font("Arial", Font.PLAIN, 15));
        txtUserName.setBounds(243, 85, 134, 28);
        contentPane.add(txtUserName);
        txtUserName.setColumns(10);

        txtContraseña = new JTextField();
        txtContraseña.setFont(new Font("Arial", Font.PLAIN, 15));
        txtContraseña.setColumns(10);
        txtContraseña.setBounds(243, 138, 134, 28);
        contentPane.add(txtContraseña);

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
        btnIniciar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                List<Jugador> jugadores = new ArrayList<>();
                ModificarArchivos.LeerJugadores(jugadores);

                if(ModificarArchivos.ValidarNickName(jugadores, txtUserName.getText())) {
                    if(ModificarArchivos.EncontrarJugador(jugadores, txtUserName.getText()).getPassword().equals(txtContraseña.getText())) {
                        FormIniciar avanzar = new FormIniciar(ModificarArchivos.EncontrarJugador(jugadores, txtUserName.getText()));
                        avanzar.setVisible(true);
                        dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "Error el usuario no se encuentra registrado");
                }
            }
        });
        btnIniciar.setForeground(Color.WHITE);
        btnIniciar.setFont(new Font("Arial Black", Font.PLAIN, 12));
        btnIniciar.setBackground(new Color(0, 255, 0));
        btnIniciar.setBounds(277, 218, 134, 35);
        contentPane.add(btnIniciar);
    }
}
