package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

public class Cronometro extends javax.swing.JFrame {
	private static final long serialVersionUID = 1L;
	// Declaración de los componentes: un JLabel para mostrar el tiempo y el Timer para gestionar la actualización
	private JLabel label;
	private Timer mTimer;  // El timer se encarga de actualizar el tiempo en intervalos regulares
	private int mil = 0;   // Milisegundos
	private int seg = 0;   // Segundos
	private int min = 0;   // Minutos
	private int hor = 0;   // Horas
	
	// Constructor de la clase, recibe un JLabel donde se mostrará el tiempo
	public Cronometro(JLabel label) {
		this.label = label;
		// Inicializa el Timer que se ejecuta cada 10 ms (10 milisegundos)
		this.mTimer = new Timer(10, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ActualizaTiempo();  // Llama a la función que actualiza el tiempo
				ActualizaLabel();   // Llama a la función que actualiza la etiqueta del JLabel
			}
		});
	}
	
	// Método para iniciar el cronómetro
	public void iniciar() {
		mTimer.start();  // Inicia el Timer
	}
	
	// Método para parar el cronómetro
	public void parar() {
		mTimer.stop();  // Detiene el Timer
	}
	
	// Método para reiniciar el cronómetro
	public void reiniciar() {
		resetear();      // Resetea las variables del tiempo a 0
		mTimer.restart(); // Reinicia el Timer desde el inicio
	}
	public void limpiar() {
		mTimer.stop();
		resetear();
		ActualizaLabel();
	}
	
	// Actualiza el texto en el JLabel con el tiempo en formato HH:MM:SS:MS
	private void ActualizaLabel() {
		// Formatea el tiempo en una cadena con ceros a la izquierda donde sea necesario
		String cronometro = (hor <= 9 ? "0" : "") + hor + ":"
				+ (min <= 9 ? "0" : "") + min + ":"
				+ (seg <= 9 ? "0" : "") + seg + ":"
				+ (mil <= 9 ? "0" : "") + mil;
		label.setText(cronometro);  // Establece el texto del JLabel con el tiempo formateado
	}
	
	// Resetea las variables del tiempo a 0
	private void resetear() {
		mil = 0;
		seg = 0;
		min = 0;
		hor = 0;
	}
	
	// Añade 10 segundos al cronometro
	public void penalizar() {
		seg +=10;
	}
	
	// Actualiza el tiempo de acuerdo a los milisegundos, segundos, minutos y horas
	private void ActualizaTiempo() {
		mil++;  // Incrementa los milisegundos
		if (mil == 100) {  // Si milisegundos llegan a 100, resetea y aumenta los segundos
			mil = 0;
			seg++;
		}
		
		if (seg == 60) {  // Si los segundos llegan a 60, resetea y aumenta los minutos
			seg = 0;
			min++;
		}
		if (min == 60) {  // Si los minutos llegan a 60, resetea y aumenta las horas
			min = 0;
			hor++;
		}
	}

	public JLabel getLabel() {
		return label;
	}

	public void setLabel(JLabel label) {
		this.label = label;
	}

	public Timer getmTimer() {
		return mTimer;
	}

	public void setmTimer(Timer mTimer) {
		this.mTimer = mTimer;
	}

	public int getMil() {
		return mil;
	}

	public void setMil(int mil) {
		this.mil = mil;
	}

	public int getSeg() {
		return seg;
	}

	public void setSeg(int seg) {
		this.seg = seg;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getHor() {
		return hor;
	}

	public void setHor(int hor) {
		this.hor = hor;
	}
	
}

