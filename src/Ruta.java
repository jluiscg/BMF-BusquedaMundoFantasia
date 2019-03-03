
public class Ruta {
	float eM;
	float eP;
	float eL;
	int pM;
	int pP;
	int pL;
	NodoGrafo destino;
	Ruta(float eM, float eP, float eL){
		destino=null;
		this.eM=eM;
		this.eP=eP;
		this.eL=eL;
		pM=pP=pL=(Integer.MAX_VALUE/2);
	}
}
