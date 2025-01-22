package gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TableroNumeros extends JPanel{
	private int txtAncho;
	private int txtLargo;
	private int txtMargen;
	private int txtTamanioLetra;
	private Color panelBackground;
	private Color txtBackground1;
	private Color txtForeground1;
	private Color txtBackground2;
	private Color txtForeground2;
	
	public TableroNumeros() {
		iniciarComponentes();
	}
	public void iniciarComponentes() {
		txtAncho = 30;
		txtLargo = 30;
		txtMargen = 4;
		txtTamanioLetra = 27;
		panelBackground = Color.black;
		txtBackground1 = Color.WHITE;
		txtForeground1 = Color.black;
		txtBackground2 = Color.WHITE;
		txtForeground2 = Color.black;
	}
	
	public void crearTablero() {
		this.setLayout(null);
		this.setSize(txtAncho+(2*txtMargen), txtLargo*9 + (4*txtMargen));
		this.setBackground(panelBackground);
		crearCamposTxt();
	}
	
	public void crearCamposTxt() {
		int x = txtMargen;
		int y = txtMargen;
		
		for(int i = 0; i < 9;i++) {
			JTextField txt = new JTextField();
			this.add(txt);
			txt.setBounds(x, y, txtAncho, txtLargo);
			txt.setBackground(panelBackground);
			txt.setForeground(txtForeground1);
			txt.setCursor(new Cursor(Cursor.HAND_CURSOR));
			txt.setEditable(false);
			txt.setBorder(BorderFactory.createLineBorder(panelBackground,1));
			txt.setFont(new Font("Arial Black",Font.BOLD,txtTamanioLetra));
			txt.setText(String.valueOf(i+1));
			
			y+=txtLargo;
			if((i+1)%3==0) {
				y+=txtMargen;
			}
			
			generarEventosTxt(txt);
		}
		
	}
	
	public void generarEventosTxt(JTextField txt) {
		MouseListener evento = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {				
			}

			@Override
			public void mousePressed(MouseEvent e) {				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
	
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				txt.setBackground(txtBackground2);
				txt.setForeground(txtForeground2);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				txt.setBackground(txtBackground1);
				txt.setForeground(txtForeground1);
			}
			
		};
		txt.addMouseListener(evento);
	}
	

	
	public int getTxtAncho() {
		return txtAncho;
	}
	public void setTxtAncho(int txtAncho) {
		this.txtAncho = txtAncho;
	}
	public int getTxtLargo() {
		return txtLargo;
	}
	public void setTxtLargo(int txtLargo) {
		this.txtLargo = txtLargo;
	}
	public int getTxtMargen() {
		return txtMargen;
	}
	public void setTxtMargen(int txtMargen) {
		this.txtMargen = txtMargen;
	}
	public int getTxtTamanioLetra() {
		return txtTamanioLetra;
	}
	public void setTxtTamanioLetra(int txtTamanioLetra) {
		this.txtTamanioLetra = txtTamanioLetra;
	}
	public Color getPanelBackground() {
		return panelBackground;
	}
	public void setPanelBackground(Color panelBackground) {
		this.panelBackground = panelBackground;
	}
	public Color getTxtBackground1() {
		return txtBackground1;
	}
	public void setTxtBackground1(Color txtBackground1) {
		this.txtBackground1 = txtBackground1;
	}
	public Color getTxtForeground1() {
		return txtForeground1;
	}
	public void setTxtForeground1(Color txtForeground1) {
		this.txtForeground1 = txtForeground1;
	}
	public Color getTxtBackground2() {
		return txtBackground2;
	}
	public void setTxtBackground2(Color txtBackground2) {
		this.txtBackground2 = txtBackground2;
	}
	public Color getTxtForeground2() {
		return txtForeground2;
	}
	public void setTxtForeground2(Color txtForeground2) {
		this.txtForeground2 = txtForeground2;
	}
	
	
}

