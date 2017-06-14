package empresa;

import java.io.*;

public class Empresa {
	public static void main(String[] args) {
		BufferedReader teclado=new BufferedReader(new InputStreamReader(System.in));
		try{
			int opcion = 0;
			GestionDepartamentos d = new GestionDepartamentos();
			GestionEmpleados e = new GestionEmpleados(d);
			d.setEmpleados(e);
			
			
			do{
				System.out.println("MENÚ: ");
				System.out.println("1. Gestión de departamentos: ");
				System.out.println("2. Gestión de empleados: ");
				System.out.println("0. Salir. ");
				
				try{
					System.out.println("Elija una opción: ");
					opcion=Integer.parseInt(teclado.readLine());
				}catch (NumberFormatException ex) {
					System.out.println("Debe introducir un numero");
					opcion=-1;
				}
				switch (opcion) {
				case 1:
					d.menu();
					break;
				case 2:
					e.menu();
					break;
				case 0:
					System.out.println("Adiós!!!!");
					break;
		
				default:
					System.out.println("Por favor, elija una opción entre 0 y 2.");
					break;
				}
			}while(opcion!=0);
		}catch (IOException e) {
			System.out.println("Error de I/O");
			e.printStackTrace();
		}
	}
}
