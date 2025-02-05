package kernel;

public class Jugador {
	private String nickName;
	private String password;
	private double puntaje;
	private double minutos;
	private double errores;
	private double dificultad;
	public Sudoku partida = new Sudoku();
	
	
	//se declara el constructor que recibe el nombre y contraseña del jugador
	public Jugador(String nickName, String password) {
		this.nickName = nickName;
		this.password = password;
	}
	//se declara otro metodo void 
	public void CalcularPuntaje(double minutos, double errores,double dificultad) {
		this.minutos = minutos;
		this.errores = errores;
		this.dificultad = dificultad;
		this.puntaje = (1000 * dificultad) - (minutos * 5) - (errores * 50);
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public double getPuntaje() {
		return puntaje;
	}
	public void setPuntaje(double puntaje) {
		this.puntaje = puntaje;
	}
	public double getMinutos() {
		return minutos;
	}
	public void setMinutos(double minutos) {
		this.minutos = minutos;
	}
	public double getErrores() {
		return errores;
	}
	public void setErrores(double errores) {
		this.errores = errores;
	}
	public double getDificultad() {
		return dificultad;
	}
	public void setDificultad(double dificultad) {
		this.dificultad = dificultad;
	}
	public Sudoku getPartida() {
		return partida;
	}
	public void setPartida(Sudoku partida) {
		this.partida = partida;
	}
	
	
}
