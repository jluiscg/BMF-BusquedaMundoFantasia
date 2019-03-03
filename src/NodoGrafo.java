
public class NodoGrafo {
	Ruta[] caminos;
	int fila;
	int columna;
	NodoGrafo(int fila, int columna){
		this.fila=fila;
		this.columna=columna;
		caminos = new Ruta[8];
		for(int i=0;i<8;i++)
			caminos[i]=null;
	}
}
