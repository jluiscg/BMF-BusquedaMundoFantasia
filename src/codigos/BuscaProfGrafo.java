package codigos;

import java.util.*;

public class BuscaProfGrafo{
	GrafoCaotico gc;
	Pila p=new Pila();
	Vector ruta,visitados;
	BuscaProfGrafo(GrafoCaotico gc){
	 this.gc=gc;
	 ruta=new Vector();
	 visitados=new Vector();
	}
	Vector busca(int o,int d){
	 int na=o,nant=-1;
	 Integer oi=null, doi=null;
	 oi=new Integer(na);
         boolean pruebaSendero;
         System.out.println("* Buscando en profundidad camino posible: "+o+" >>> "+d);
	 do{
	 	if(!visitados.contains(oi)){
	 		visitados.add(oi);
			System.out.println("\t* No visitado antes: "+na+
                                           "\n\tSe extiende frontera por: "+na);
                        ruta.add(oi);
                        System.out.println("\tRuta: "+ruta+ " NAct: "+na+" NAnt: "+nant);
	 		if (nant!=-1){
                          System.out.println("\t* Comprobando conexión: "+nant+" -> "+na);
                          if(!gc.ligas[nant][na]){
                            System.out.println("\t* Removiendo desconexión: "+nant+" -> "+na);
                            ruta.remove(ruta.size()-1);
                            na=((Integer)ruta.get(ruta.size()-1)).intValue();
                            nant=(ruta.size()>1)?((Integer)ruta.get(ruta.size()-2)).intValue():-1;
                            System.out.println("\tRuta: "+ruta+ " NAct: "+na+" NAnt: "+nant);
                          }else System.out.println("\t* Conexión existente: "+nant+" -> "+na);
                        }
                        pruebaSendero=false;
                        if(gc.gradoSalida(na)>0){	 			
				//System.out.println("Tiene gs>0: "+na);
	 			if(na==d){
                                    System.out.println("** Camino posible en BP concluido");
                                    return ruta;
                                }
	 			for(int i=0;i<gc.n;i++){
                                 doi=new Integer(i);
	 			 if(gc.ligas[na][i])
                                  if (!visitados.contains(doi) && !p.contiene(doi)){
                                        pruebaSendero=true;
					p.push(new Integer(i));
					System.out.println("\tApilando: "+i);
				  }else System.out.println("\t* Ruta alterna considerada antes: "+doi);
                                }
                                if (!pruebaSendero){
                                    System.out.println("\tContracción frontera por: "+na);
                                    ruta.remove(oi);
                                    visitados.remove(oi);
                                    na=((Integer)ruta.get(ruta.size()-1)).intValue();
                                    nant=(ruta.size()>1)?((Integer)ruta.get(ruta.size()-2)).intValue():-1;
                                    System.out.println("\tRuta: "+ruta+ " NAct: "+na+" NAnt: "+nant);
                                }
	 		}
                        nant=na;
	 	}
                else System.out.println("\t* Visitado antes: "+oi);
		oi=(Integer)p.pop();
	 	if(oi!=null){
		 na=oi.intValue();
		 System.out.println("\tDesapilando: "+na);
		}
	 }while(oi!=null); 
         System.out.println("** Imposible construir camino posible en BP");
	 return null;
	}	
}
