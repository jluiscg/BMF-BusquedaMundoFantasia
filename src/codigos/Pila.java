package codigos;

import java.util.*;

public class Pila{
        private Vector datos=new Vector();
	void push(Object e){
		datos.add(e);
	}
	Object top(){
		int fin=datos.size()-1;
		if(fin>=0)
			return datos.get(fin);
		return null;
	}
	Object pop(){
		int fin=datos.size()-1;
		if(fin>=0)
			return datos.remove(fin);
		return null;
	}
        boolean contiene(Object e){
            return datos.contains(e);
        }
}