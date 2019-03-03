import java.util.Vector;

public class AscensoColina {
	private static final int MOMBO=0;
	private static final int PIROLO=1;
	private static final int LUCAS=2;
	public static void BusquedaMasBarato(NodoGrafo base, NodoGrafo destino,int agente) {
		Vector<NodoGrafo> recorridos=new Vector<NodoGrafo>();
		float masBarato;
		int pos;
		while(base!=destino){
			recorridos.add(base);
			if(base.caminos[0]!=null){
				if(agente==MOMBO)masBarato=base.caminos[0].pM;
				else if(agente==PIROLO)masBarato=base.caminos[0].pP;
				else masBarato=base.caminos[0].pL;
				pos=0;
			}
			else if(base.caminos[1]!=null){
				if(agente==MOMBO)masBarato=base.caminos[1].pM;
				else if(agente==PIROLO)masBarato=base.caminos[1].pP;
				else masBarato=base.caminos[1].pL;
				pos=1;
			}
			else if(base.caminos[2]!=null){
				if(agente==MOMBO)masBarato=base.caminos[2].pM;
				else if(agente==PIROLO)masBarato=base.caminos[2].pP;
				else masBarato=base.caminos[2].pL;
				pos=2;
			}
			else if(base.caminos[3]!=null){
				if(agente==MOMBO)masBarato=base.caminos[3].pM;
				else if(agente==PIROLO)masBarato=base.caminos[3].pP;
				else masBarato=base.caminos[3].pL;
				pos=3;
			}
			else if(base.caminos[4]!=null){
				if(agente==MOMBO)masBarato=base.caminos[4].pM;
				else if(agente==PIROLO)masBarato=base.caminos[4].pP;
				else masBarato=base.caminos[4].pL;
				pos=4;
			}
			else if(base.caminos[5]!=null){
				if(agente==MOMBO)masBarato=base.caminos[5].pM;
				else if(agente==PIROLO)masBarato=base.caminos[5].pP;
				else masBarato=base.caminos[5].pL;
				pos=5;
			}
			else if(base.caminos[6]!=null){
				if(agente==MOMBO)masBarato=base.caminos[6].pM;
				else if(agente==PIROLO)masBarato=base.caminos[6].pP;
				else masBarato=base.caminos[6].pL;
				pos=6;
			}
			else if(base.caminos[7]!=null){
				if(agente==MOMBO)masBarato=base.caminos[7].pM;
				else if(agente==PIROLO)masBarato=base.caminos[7].pP;
				else masBarato=base.caminos[7].pL;
				pos=7;
			}
			else{
				return;
			}
			for(int i=0;i<7;i++){
				if(base.caminos[i]!=null){
					if(agente==MOMBO){
						if(base.caminos[i].pM<masBarato){
							masBarato=base.caminos[i].pM;
							pos=i;
						}
					}
					else if(agente==PIROLO){
						if(base.caminos[i].pP<masBarato){
							masBarato=base.caminos[i].pP;
							pos=i;
						}
					}
					else{
						if(base.caminos[i].pL<masBarato){
							masBarato=base.caminos[i].pL;
							pos=i;
						}
					}
				}
			}
			base=base.caminos[pos].destino;
		}
		if(agente==MOMBO)System.out.print("MOMBO");
		else if(agente==PIROLO)System.out.print("PIROLO");
		else System.out.print("LUCAS");
		for(int i=0;i<recorridos.size();i++){
			System.out.print("["+recorridos.get(i).fila+", "+recorridos.get(i).columna+"] -> ");
		}
		System.out.println("");
	}

}
