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
	
	/*
	 * Clase para la interaccion con el usuario
	 * y presentacion de resultados por pantalla
	 */
	
	/**
	 * @param args 
	 * 		SimAnnealingSetCover -path <path> -m <numero de pruebas>
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
			System.out.println("Prueba no " + (i+1) + ": " 
					+ SimulatedAnnealing.simulatedAnnealing(lista_conj));
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
