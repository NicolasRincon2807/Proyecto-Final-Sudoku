package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReglasSudoku extends JFrame {

	private static final long serialVersionUID = 1L;

	public ReglasSudoku() {
        // Cargar la imagen desde el paquete resources
        ImageIcon icono = new ImageIcon(getClass().getClassLoader().getResource("sudoku.png"));
        setIconImage(icono.getImage());
        setTitle("Reglas del Sudoku");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 273); // Aumenté el tamaño del JFrame
        setLocationRelativeTo(null);

        // Crear el panel principal con BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(255, 248, 225));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Crear un JTextArea para mostrar las reglas
        JTextArea reglasTextArea = new JTextArea();
        reglasTextArea.setEditable(false);
        reglasTextArea.setFont(new Font("Arial", Font.BOLD, 14));
        reglasTextArea.setBackground(new Color(255, 248, 225));
        reglasTextArea.setForeground(new Color(50, 50, 50)); // Color de texto oscuro
        reglasTextArea.setLineWrap(true); // Ajustar texto automáticamente
        reglasTextArea.setWrapStyleWord(true); // Ajustar por palabras
        reglasTextArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margen interno

        // Texto de las reglas
        String reglas = """
            1. La cuadrícula del Sudoku tiene 9x9 espacios.
            
            2. Solo puedes usar los números del 1 al 9.
            
            3. Cada bloque de 3x3 solo puede contener números del 1 al 9.
            
            4. Cada columna vertical solo puede contener números del 1 al 9.
            
            5. Cada fila horizontal solo puede contener números del 1 al 9.
            
            6. Cada número de un bloque de 3x3, de una columna vertical o de una fila horizontal solo puede usarse una vez.
            
            7. La partida acaba cuando se completa toda la cuadrícula del Sudoku con los números correctos
            
            8.Dependiendo de la dificultad se te darán puntajes iniciales
            
        			a. Fácil = 1000 pts
        			b. Medio = 2000 pts
        			c. Difícil = 3000 pts
            
            9. Por cada error al comprobar la partida se te restarán 50 puntos y sumarán 10 segundos
            
            10. por cada minuto que pase se te restarán 5 puntos
            """;
        reglasTextArea.setText(reglas);

        // Agregar el JTextArea dentro de un JScrollPane
        JScrollPane scrollPane = new JScrollPane(reglasTextArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(242, 174, 37), 2));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Crear el botón "Volver"
        JButton volverButton = new JButton("Volver");
        volverButton.setBackground(new Color(242, 174, 37));
        volverButton.setForeground(Color.WHITE);
        volverButton.setFont(new Font("Cooper Black", Font.PLAIN, 16));
        volverButton.setFocusPainted(false);
        volverButton.setBorder(BorderFactory.createRaisedBevelBorder());

        // Panel para el botón
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(255, 248, 225));
        buttonPanel.add(volverButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Acción del botón "Volver"
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes agregar la lógica para volver al menú principal
                MenuPrincipal menu = new MenuPrincipal();
                menu.setVisible(true);
                dispose(); // Cierra la ventana actual
            }
        });

        // Agregar el panel principal al JFrame
        getContentPane().add(mainPanel);
    }}
