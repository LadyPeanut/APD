/*
 * Patricia Lazaro Tello (554309)
 */

package heuristica;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class SimAnnealingSetCover {
	
	/**
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
	 */
	
	/* 
	 * TODO Representacion de sets
	 * Seran sets de enteros, representados por el TAD de Java
	 */
	
	/* 
	 * TODO Generador de soluciones aleatorias.
	 * Recibira una lista de sets, generara el numero de elementos
	 * que debe contener el set final (la union de los m sets) y
	 * con generacion de numeros aleatorios, ira introduciendo los 
	 * elementos de los sets que toquen hasta tener ese numero de 
	 * elementos. Finalmente, devolvera el numero de sets necesario
	 * para formar esa solucion 
	 */
	
	// TODO Evaluacion de soluciones
	
	/**
	 * Invocacion:
	 * SimAnnealingSetCover -path <path> -m <numero de pruebas> 
	 */
	public static void main(String[] args) {
		int m = -1;
		String path = null;
		
		if(args.length > 0){
			for (int i = 0; i < args.length; i++) {
				String s = args[i];
				
				if(s.equals("-m")){	// numero de pruebas
					i++;
					s = args[i];
					m = Integer.parseInt(s);
				}

				if(s.equals("-path")){	// path
					i++;
					path = args[i];
				}
			}
		}
		
		/* comprobacion de errores */
		if(m < 1){
			System.err.println("El numero de pruebas ha de ser positivo");
			System.exit(1);
		}
		if(path == null){
			System.err.println("Falta path");
			System.exit(1);
		}
		
		try {
			pruebas(m, path);
		} catch (FileNotFoundException e) {
			System.err.println("Error en el fichero");
			System.exit(1);
		}
	}
	
	/**
	 * @param m numero de pruebas
	 * @param path path del fichero
	 * @throws FileNotFoundException 
	 */
	private static void pruebas(int m, String path) throws FileNotFoundException{
		List<Set<Integer>> lista_conj = generarInput(path);
		for(int i = 0; i < m; i++){
			System.out.println(SimulatedAnnealing.simulatedAnnealing(lista_conj));
		}
	}
	
	/**
	 * @param path path del fichero
	 * @return lista con los sets contenidos en el fichero
	 * @throws FileNotFoundException
	 */
	private static List<Set<Integer>> generarInput(String path) throws FileNotFoundException{
		File f = new File(path);
		Scanner s = new Scanner(f);
		
		List<Set<Integer>> lista = new LinkedList<Set<Integer>>();
		while(s.hasNextLine()){
			Set<Integer> set = new HashSet<Integer>();
			String in = s.nextLine();
			String[] subs = in.split(" ");
			for (int i = 0; i < subs.length; i++) {
				String number = subs[i];
				set.add(Integer.parseInt(number));
			}
			lista.add(set);
		}
		
		s.close();
		
		return lista;
	}
}
