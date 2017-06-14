package empresa;

import java.awt.BufferCapabilities;
import java.io.*;

public class GestionDepartamentos {
	private BufferedReader teclado=new BufferedReader(new InputStreamReader(System.in));
	private File fdepart=new File(".\\files\\departamentos.txt");
	private File ftemporal=new File(".\\files\\temporal.txt");
	private GestionEmpleados e;
	public void setEmpleados(GestionEmpleados e){
		this.e=e;
	}
	
	public void menu() throws IOException{
		int opcion=0;
		do {
			 System.out.println("1. añadir departamento");
			 System.out.println("2. listar departamento");
			 System.out.println("3. modificar departamento");
			 System.out.println("4. borrar departamento");
			 System.out.println("5. Mostrar total salario de un departamento.");
			 System.out.println("6. Mostrar total salarios de los departamentos.");
			 System.out.println("0. salir");
			 try{
				 System.out.println("seleccione una opcion");
				 opcion=Integer.parseInt(teclado.readLine());
			 }catch (NumberFormatException e) {
				System.out.println("Debe introducir un numero de 0 a 6");
				opcion=-1;
			 }
			 
			 switch (opcion) {
			case 1:
				addDepartamento();
				break;
			case 2:
				listarDepartamento();
				break;

			case 3:
				setDepartamento();
				break;

			case 4:
				borrarDepartamento();
				break;
			case 5:
				mostrarSalarioDepartamento();
				break;
			case 6:
				mostrarSalarios();
				break;
			case 0:
				System.out.println("has salido del menu");
				break;
			default:
				System.out.println("debe introducir un numero entre 0 y 4");
				break;
			}
		} while (opcion!=0);	

	}
	public boolean existeDepartamento(int dptonum) throws IOException{
		try {
			Departamento d=new Departamento();
			BufferedReader br=new BufferedReader(new FileReader(fdepart));
			String linea=br.readLine();
			while(linea!=null){
				d.descomponerLinea(linea);
				if(dptonum==d.getDptonum()){
					br.close();
					return true;
				}
				linea=br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			return false;
		}
		return false;
	}
	private void addDepartamento() throws IOException {
		boolean seguir=true;
		int dptonum=-1;
		do{
			try{
				System.out.println("Introduzca el numero de departamento");
				dptonum=Integer.parseInt(teclado.readLine());
				seguir=false;
			}catch (NumberFormatException e) {
				System.out.println("Debe introducir un numero");
			}
		}while(seguir);
		
		//buscar departamento
		if(existeDepartamento(dptonum)){
			System.out.println("Ya existe un departamento con nº "+dptonum+ " o no existen departamentos");
			return;
		}
		
		Departamento d=new Departamento();
		d.pedirDepartamento(dptonum);
		BufferedReader br;
		try{
			br=new BufferedReader(new FileReader(fdepart));
		}catch (FileNotFoundException e) {
			BufferedWriter brw=new BufferedWriter(new FileWriter(fdepart));
			brw.close();
			br=new BufferedReader(new FileReader(fdepart));
		}
		BufferedWriter bw=new BufferedWriter(new FileWriter(ftemporal));
		
		String linea=br.readLine();
		while(linea!=null){
			bw.write(linea);
			bw.newLine();
			linea=br.readLine();
		}
		
		//Montamos la linea
		linea=d.crearLinea();
		bw.write(linea);
		bw.flush();
		
		br.close();
		bw.close();
		
		fdepart.delete();
		ftemporal.renameTo(fdepart);
	}

	private void listarDepartamento() throws IOException {
		
		try {
			Departamento d=new Departamento();
			BufferedReader br=new BufferedReader(new FileReader(fdepart));
			String linea=br.readLine();
			while(linea!=null){
				d.descomponerLinea(linea);
				System.out.println(d);
				linea=br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("No existen departamentos");
		}
		
	}

	private void setDepartamento() throws IOException {
		boolean existe=false;
		boolean seguir=true;
		int dptonum=-1;
		do{
			try{
				System.out.println("Introduzca el numero de departamento a modificar");
				dptonum=Integer.parseInt(teclado.readLine());
				seguir=false;
			}catch (NumberFormatException e) {
				System.out.println("Debe introducir un numero");
			}
		}while(seguir);
		
		BufferedReader br;
		try{
			br=new BufferedReader(new FileReader(fdepart));
		}catch (FileNotFoundException e) {
			System.out.println("El departamento "+dptonum+" no existe");
			return;
		}
		BufferedWriter bw=new BufferedWriter(new FileWriter(ftemporal));
		Departamento d=new Departamento();
		String linea=br.readLine();
		while(linea!=null){
			d.descomponerLinea(linea);
			if(dptonum==d.getDptonum()){
				d.pedirDepartamento(dptonum);
				linea=d.crearLinea();
				existe=true;
			}
			bw.write(linea);
			bw.newLine();;
			linea=br.readLine();
		}
		
		bw.flush();
		bw.close();
		br.close();
		fdepart.delete();
		ftemporal.renameTo(fdepart);
		if(existe){
			System.out.println("Departamento "+dptonum+" modificado correctamente");
		}else{
			System.out.println("No se ha modificado el Departamento "+dptonum+" porque no existe");
		}
		
	}

	private void borrarDepartamento() throws IOException {
		boolean existe=false;
		boolean seguir=true;
		int dptonum=-1;
		do{
			try{
				System.out.println("Introduzca el numero de departamento a eliminar");
				dptonum=Integer.parseInt(teclado.readLine());
				seguir=false;
			}catch (NumberFormatException e) {
				System.out.println("Debe introducir un numero");
			}
		}while(seguir);
		
		//Comprobar que no existe ningun empleado en ese departamento
		if(e.existenEmpleadosEnDepartamento(dptonum)){
			System.out.println("Existen empleados en el departamento "+dptonum+". No puede borrarlo");
			return;
		}
		BufferedReader br;
		try{
			br=new BufferedReader(new FileReader(fdepart));
		}catch (FileNotFoundException e) {
			System.out.println("No existe el departamento "+dptonum);
			return;
		}
		
		BufferedWriter bw=new BufferedWriter(new FileWriter(ftemporal));
		String linea=br.readLine();
		Departamento d=new Departamento();
		while(linea!=null){
			d.descomponerLinea(linea);
			if(dptonum!=d.getDptonum()){
				bw.write(linea);
				bw.newLine();
			}else{
				existe=true;
			}
			linea=br.readLine();
		}
		bw.flush();
		bw.close();
		br.close();
		fdepart.delete();
		ftemporal.renameTo(fdepart);
		if(existe){
			System.out.println("Departamento "+dptonum+" borrado correctamente");
		}else{
			System.out.println("No se ha borrado el Departamento "+dptonum+" porque no existe");
		}
		
	}

	private void mostrarSalarioDepartamento() {
		// TODO Apéndice de método generado automáticamente
		
	}

	private void mostrarSalarios() {
		// TODO Apéndice de método generado automáticamente
		
	}
}
