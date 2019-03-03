

public class Casilla {
	public  static final int CASA=3;
	public  static final int PLANO=4;
	public  static final int MONTANIA=5;
	public  static final int BARRANCO=6;
	public  static final int AGUA=7;
	public  static final int MURO=8;
	
	int  tipoCasilla;
	NodoGrafo nodo;
	public Casilla(int fila, int columna){
		tipoCasilla=PLANO;
		nodo=new NodoGrafo(fila,columna);
	}
	
}
