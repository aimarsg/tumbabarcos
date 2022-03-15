package packModelo;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Teclado {
	private static Teclado miTeclado=null; 
	private Scanner sc;
	
	private Teclado() {
		sc= new Scanner(System.in);
	}
	public static Teclado getMiTeclado() {
		if (Teclado.miTeclado==null) {
			Teclado.miTeclado= new Teclado();
		}
		
		return (Teclado.miTeclado);
	}
	
	public int leerEntero () {
		int r=0;
		boolean todoOK=true;
		do{todoOK=true;
			try{
		
			r = sc.nextInt();
		}
			catch(InputMismatchException e) {
				System.out.println("El numero introducido no es valido por no ser un numero.");
				System.out.println("Escribe otro numero:");
				todoOK=false;
			}
		}while(todoOK==false);
		sc.nextLine();
		return r;
	}
	
	public int leerEntero (int pDesde, int pHasta) {
		int r=0;
		boolean todoOk=true;
		
		do{try {
			r = sc.nextInt();
			
			todoOk=true;
			
			if (r>=pDesde && r<=pHasta) {
				
			}
			else {
				throw new NumeroFueraDeRangoException();
			}
			}
		
		catch(NumeroFueraDeRangoException e){
			todoOk=false;
			System.out.println("El numero introducido no esta entre " + pDesde +" y "+ pHasta + ".");
			System.out.println("Escribe otro numero:");
			
			
		}
		catch(InputMismatchException e) {
			todoOk=false;
			System.out.println("El numero introducido no es valido por no ser un numero.");
			System.out.println("Escribe otro numero:");
			sc.nextLine();
		}
		
		}
		while(!todoOk);
		
		
		sc.nextLine();
		return r;
	}
	
	public String leerString () {
		String r = sc.nextLine();
		return r;
	}
}
