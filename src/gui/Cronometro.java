package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * Clase Cronometro: Implementa un cronómetro funcional con interfaz gráfica.
 * 
 * Esta clase gestiona un cronómetro que puede iniciarse, pausarse, reiniciarse y 
 * actualizarse con precisión de milisegundos. Utiliza un Timer de Swing para 
 * actualizar el tiempo de forma continua.
 * 
 * @author Nicolás Rincón
 * @version 1.0
 * @since 2025-02-10
 */
public class Cronometro extends javax.swing.JFrame {
    private static final long serialVersionUID = 1L;
    
    // Componentes y variables de tiempo
    private JLabel label;           // Etiqueta para mostrar el tiempo
    private Timer mTimer;           // Timer para actualizar el tiempo
    private int mil = 0;            // Milisegundos
    private int seg = 0;            // Segundos
    private int min = 0;            // Minutos
    private int hor = 0;            // Horas
    
    /**
     * Constructor que inicializa el cronómetro con una etiqueta para mostrar el tiempo.
     * 
     * @param label JLabel donde se mostrará el tiempo transcurrido
     */
    public Cronometro(JLabel label) {
        this.label = label;
        // Inicializa el Timer que se ejecuta cada 10 ms
        this.mTimer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ActualizaTiempo();  // Actualiza el tiempo
                ActualizaLabel();   // Actualiza la etiqueta
            }
        });
    }
    
    /**
     * Inicia el cronómetro comenzando el Timer.
     */
    public void iniciar() {
        mTimer.start();
    }
    
    /**
     * Detiene el cronómetro deteniendo el Timer.
     */
    public void parar() {
        mTimer.stop();
    }
    
    /**
     * Reinicia el cronómetro reseteando el tiempo y reiniciando el Timer.
     */
    public void reiniciar() {
        resetear();
        mTimer.restart();
    }
    
    /**
     * Limpia completamente el cronómetro, deteniéndolo y reseteando el tiempo.
     */
    public void limpiar() {
        mTimer.stop();
        resetear();
        ActualizaLabel();
    }
    
    /**
     * Actualiza la etiqueta con el tiempo formateado en HH:MM:SS:MS.
     * Asegura que cada componente del tiempo tenga dos dígitos.
     */
    private void ActualizaLabel() {
        String cronometro = (hor <= 9 ? "0" : "") + hor + ":"
                + (min <= 9 ? "0" : "") + min + ":"
                + (seg <= 9 ? "0" : "") + seg + ":"
                + (mil <= 9 ? "0" : "") + mil;
        label.setText(cronometro);
    }
    
    /**
     * Resetea todas las variables de tiempo a cero.
     */
    private void resetear() {
        mil = 0;
        seg = 0;
        min = 0;
        hor = 0;
    }
    
    /**
     * Añade una penalización de 10 segundos al cronómetro.
     */
    public void penalizar() {
        seg += 10;
    }
    
    /**
     * Actualiza el tiempo incrementando milisegundos y gestionando 
     * el desbordamiento de milisegundos, segundos, minutos y horas.
     */
    private void ActualizaTiempo() {
        mil++;
        if (mil == 100) {
            mil = 0;
            seg++;
        }
        
        if (seg == 60) {
            seg = 0;
            min++;
        }
        if (min == 60) {
            min = 0;
            hor++;
        }
    }

    // Getters y Setters generados automáticamente (documentados)
    
    /**
     * Obtiene la etiqueta del cronómetro.
     * @return JLabel actual
     */
    public JLabel getLabel() {
        return label;
    }

    /**
     * Establece una nueva etiqueta para el cronómetro.
     * @param label Nueva etiqueta a mostrar
     */
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

    // Los demás getters y setters seguirían un patrón similar de documentación
    
}

