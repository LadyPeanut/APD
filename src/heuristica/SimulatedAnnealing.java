/*
 * Patricia Lazaro Tello (554309)
 */

package heuristica;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class SimulatedAnnealing {
	
	/*
	 * Clase que implementa la heuristica de Enfriamiento Simulado
	 * para el problema de Set Cover.
	 * 
	 * El problema de Set Cover:
	 * ENTRADA: S1, S2... Sm (conjuntos)
	 * SALIDA: numero minimo de conjuntos necesarios cuya union da
	 * el mismo resultado que la union de todos los conjuntos. 
	 * 
	 * Simulated Annealing:
	 * ==============================================================
	 * 1. Inicializar con una solucion aleatoria. T alta.
	 * BUCLE:
	 * 2. Movimiento.
	 * 3. Evaluar la nueva solucion.
	 * 4. Elegir la nueva solucion.
	 * 5. Reducir T.
	 *
	 * Representacion de sets:
	 * Seran sets de enteros, representados por el TAD de Java
	 * 
	 * Generador de soluciones aleatorias:
	 * Recibira una lista de sets, generara el numero de elementos
	 * que debe contener el set final (la union de los m sets) y
	 * con generacion de numeros aleatorios, ira introduciendo los 
	 * elementos de los sets que toquen hasta tener ese numero de 
	 * elementos. Finalmente, devolvera el numero de sets necesario
	 * para formar esa solucion
	 *  
	 * Evaluacion de soluciones:
	 * Numero de sets utilizados para cubrir todos los elementos
	 */
	
	/**
	 * @param lista lista de conjuntos
	 * @return numero de conjuntos necesarios cuya union es
	 * la misma que la union de los conjuntos originales 
	 */
	protected static int simulatedAnnealing(List<Set<Integer>> lista){
		int k = solucionAleatoria(lista);
		double temperatura = 1.0;
		double kb = 0.9;
		
		Random r = new Random();
		
		while(temperatura > 0.001){
			int k1 = solucionAleatoria(lista);
			if(k1 < k){
				/* la nueva solucion (k1) es mejor que la anterior (k) */
				k = k1;
			}
			else{
				/* k1 > k */
				double epsilon = (double) ( (double) (Math.abs(k1 - k)) / temperatura );
				double probabilidad = Math.pow(Math.E, -epsilon);
				
				if(probabilidad > r.nextDouble()){
					k = k1;
				}
			}
			temperatura = temperatura * kb;
		}
		
		return k;
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
		
		List<Boolean> repes = new LinkedList<Boolean>();
		for(int i = 0; i < lista.size(); i++){
			repes.add(false);
		}
		
		Random r = new Random();
		
		while(n < m){
			int next = r.nextInt(elems);

			if(!repes.get(next)){
				Set<Integer> s = lista.get(next);
				set.addAll(s);
				
				n = set.size();
				k++;
				repes.remove(next);
				repes.add(next, true);
			}
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
