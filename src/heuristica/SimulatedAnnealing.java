package heuristica;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class SimulatedAnnealing {
	
	/**
	 * @param lista lista de conjuntos
	 * @return numero de conjuntos necesarios cuya union es
	 * la misma que la union de los conjuntos originales 
	 */
	protected static int simulatedAnnealing(List<Set<Integer>> lista){
		int k = solucionAleatoria(lista);
		double T = 
	}
	
	/**
	 * Recibira una lista de sets, generara el numero de elementos
	 * que debe contener el set final (la union de los m sets) y
	 * con generacion de numeros aleatorios, ira introduciendo los 
	 * elementos de los sets que toquen hasta tener ese numero de 
	 * elementos. Finalmente, devolvera el numero de sets necesario
	 * para formar esa solucion 
	 *
	 * @param lista lista de conjuntos
	 * @return numero de conjuntos necesarios para una solucion 
	 * aleatoria
	 */
	private static int solucionAleatoria(List<Set<Integer>> lista){
		int m = elemsUnion(lista);	// numero de elementos en la union
		int k = 0;	// numero de conjuntos usados
		int n = 0;	// elementos en el set resultante
		
		int elems = lista.size();	// numero de sets
		
		Set<Integer> set = new HashSet<Integer>();
		Random r = new Random();
		
		while(n < m){
			int next = r.nextInt(elems);
			
			Set<Integer> s = lista.get(next);
			set.addAll(s);
			
			n = set.size();
			k++;
		}
		
		return k;
	}
	
	/**
	 * @param lista lista de conjuntos
	 * @return numero de elementos en la union
	 */
	private static int elemsUnion(List<Set<Integer>> lista){
		Set<Integer> set = new HashSet<Integer>();
		for (int i = 0; i < lista.size(); i++) {
			set.addAll(lista.get(i));
		}
		return set.size();
	}

}
