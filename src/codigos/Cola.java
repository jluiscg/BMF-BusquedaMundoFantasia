package codigos;

import java.util.*;

public class Cola{
        private Vector datos=new Vector();
	Object primero(){
		if(datos.size()>0) return datos.get(0);
		return null;
	}
	Object ultimo(){
		int fin=datos.size()-1;
		if(fin>=0) return datos.get(fin);
		return null;
	}
	void mete(Object e){
		datos.add(e);
	}
	Object saca(){
		if(datos.size()>0) return datos.remove(0);
		return null;
	}
        boolean contiene(Object e){
            return datos.contains(e);
        }
}