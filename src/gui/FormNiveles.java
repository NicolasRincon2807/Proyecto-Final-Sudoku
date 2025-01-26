package gui;

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

    // Constructor que recibe el tablero de Sudoku y el cronómetro
    public FormNiveles(TableroSudoku tableroSudoku, Cronometro timer) {
        
        this.tableroSudoku = tableroSudoku; // Inicializa el tablero de Sudoku recibido
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Configura el comportamiento al cerrar la ventana
        setBounds(100, 100, 300, 300); // Establece el tamaño y la posición inicial de la ventana
        contentPane = new JPanel(); // Crea el panel de contenido
        contentPane.setBackground(new Color(242, 174, 37)); // Establece el color de fondo del panel
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // Configura un borde vacío alrededor del panel
        setContentPane(contentPane); // Establece el panel como el contenido de la ventana
        contentPane.setLayout(null); // Establece el layout del panel como null (posicionamiento absoluto)

        JLabel lblTitulo = new JLabel("Sudoku"); // Título del formulario
        lblTitulo.setForeground(Color.WHITE); // Establece el color del texto
        lblTitulo.setFont(new Font("Arial Black", Font.BOLD, 30)); // Configura la fuente
        lblTitulo.setBounds(73, 11, 147, 43); // Establece la posición y tamaño de la etiqueta
        contentPane.add(lblTitulo); // Añade la etiqueta al panel

        // Botón para seleccionar el nivel fácil
        JButton btnFacil = new JButton("Fácil");
        btnFacil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tableroSudoku.generarSudoku(1); // Genera un Sudoku fácil (nivel 1)
                timer.reiniciar(); // Reinicia el cronómetro
                dispose(); // Cierra la ventana de selección de niveles
            }
        });
        btnFacil.setForeground(Color.WHITE);
        btnFacil.setFont(new Font("Arial Black", Font.BOLD, 12));
        btnFacil.setBackground(new Color(89, 43, 25));
        btnFacil.setBounds(73, 75, 134, 35); // Establece la posición y tamaño del botón
        contentPane.add(btnFacil); // Añade el botón al panel

        // Botón para seleccionar el nivel medio
        JButton btnMedio = new JButton("Medio");
        btnMedio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tableroSudoku.generarSudoku(2); // Genera un Sudoku de dificultad media (nivel 2)
                timer.reiniciar(); // Reinicia el cronómetro
                dispose(); // Cierra la ventana de selección de niveles
            }
        });
        btnMedio.setForeground(Color.WHITE);
        btnMedio.setFont(new Font("Arial Black", Font.BOLD, 12));
        btnMedio.setBackground(new Color(89, 43, 25));
        btnMedio.setBounds(73, 121, 134, 35); // Establece la posición y tamaño del botón
        contentPane.add(btnMedio); // Añade el botón al panel

        // Botón para seleccionar el nivel difícil
        JButton btnDificil = new JButton("Dificil");
        btnDificil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tableroSudoku.generarSudoku(3); // Genera un Sudoku difícil (nivel 3)
                timer.reiniciar(); // Reinicia el cronómetro
                dispose(); // Cierra la ventana de selección de niveles
            }
        });
        btnDificil.setForeground(Color.WHITE);
        btnDificil.setFont(new Font("Arial Black", Font.BOLD, 12));
        btnDificil.setBackground(new Color(89, 43, 25));
        btnDificil.setBounds(73, 167, 134, 35); // Establece la posición y tamaño del botón
        contentPane.add(btnDificil); // Añade el botón al panel
    }
}
