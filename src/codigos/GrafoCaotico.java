package codigos;

import java.util.*;

public class GrafoCaotico{
	boolean [][] ligas;
	int n;

	GrafoCaotico(int n){
		this.n=n;
		ligas=new boolean [n][n];
		llenaAzar();
	}
	
	GrafoCaotico(boolean [][] m){
	 	ligas=m;
	 	n=m.length;
	 }
	
	void llenaAzar(){
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++)
				ligas[i][j]=((int)(Math.random()*2)==1);
	}
	
	boolean [][] generaTrans(){
	        boolean [][] ret=new boolean [n][n];
		for(int i=0;i<n;i++)
			for(int j=0;j<n;j++)
			        ret[i][j]=ligas[j][i];
	    	return ret;
	}
	
	void muestra(){
                System.out.println("* Matriz de Adyacencia ("+n+"x"+n+") del Grafo Aleatorio creado:");
		for(int i=0;i<n;i++){
			for(int j=0;j<n;j++)
				System.out.print(" "+(ligas[i][j]?1:0));
			System.out.println();}
		System.out.println();	
	}
	
	//GradoSalida
	int gradoSalida(int na){
		int cont=0;
		if(na>=0&&na<n)
			for(int i=0;i<n;i++) if(ligas[na][i])cont++;
		return cont;
	}	
	
	//GradoEntrada
	int gradoEntrada(int na){
		int cont=0;
		if(na>=0&&na<n)
			for(int i=0;i<n;i++) if(ligas[i][na])cont++;
		return cont;
	}	

	public static void main(String args[]){
		int tam=Integer.parseInt(args[0]);
		int o=0,d=0;
		Vector ruta=null;
		GrafoCaotico gc= new GrafoCaotico(tam);
		BuscaProfGrafo bp=new BuscaProfGrafo(gc);
		BuscaAmpGrafo ba=new BuscaAmpGrafo(gc);
                System.out.println("Búsquedas de Ruta Posible en Amplitud y Profundidad sobre un Grafo Caótico\n");
		gc.muestra();
		o=(int)(Math.random()*tam);
		do{
                 d=(int)(Math.random()*tam);
                }while(o==d);
                System.out.println("Buscando Ruta Posible: "+o+" >>> "+d);  
                ruta=bp.busca(o,d);
                if(ruta!=null) System.out.println("BP: "+ruta);  
                System.out.println(); 
                ruta=ba.busca(o,d);
                if(ruta!=null) System.out.println("BA: "+ruta);  
        }
}
