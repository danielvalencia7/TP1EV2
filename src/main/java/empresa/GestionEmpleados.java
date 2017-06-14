package empresa;

import java.io.*;

public class GestionEmpleados {
	private GestionDepartamentos d=new GestionDepartamentos();
	private BufferedReader teclado=new BufferedReader(new InputStreamReader(System.in));
	private File fempleado=new File(".\\files\\empleados.txt");
	private File ftemporal=new File(".\\files\\temporal.txt");
	
	
	public GestionEmpleados(GestionDepartamentos d) {
		super();
		this.d = d;
	}
	public void menu() throws IOException{
		int opcion=0;
		do {
			 System.out.println("1. añadir empleado");
			 System.out.println("2. mostrar un empleado");
			 System.out.println("3. modificar empleado");
			 System.out.println("4. borrar empleado");
			 System.out.println("5. listar empleados");
			 System.out.println("0. salir");
			 try{
				 System.out.println("seleccione una opcion");
				 opcion=Integer.parseInt(teclado.readLine());
			 }catch (NumberFormatException e) {
				System.out.println("Debe introducir un numero de 0 a 5");
				opcion=-1;
			 }
			 
			 switch (opcion) {
			case 1:
				addEmpleado();
				break;
			case 2:
				mostrarEmpleado();
				break;

			case 3:
				setEmpleado();
				break;

			case 4:
				borrarEmpleado();
				break;
			case 5:
				listarEmpleados();
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
	private void mostrarEmpleado() throws IOException {
		boolean existe=false;
		boolean seguir=true;
		int empnum=-1;
		do{
			try{
				System.out.println("Dame un numero de empleado que desea buscar");
				empnum=Integer.parseInt(teclado.readLine());
				seguir=false;
			}catch(NumberFormatException e){
				System.out.println("Dame un numero.");
			}
		}while(seguir);
		Empleado e=buscarEmpleado(empnum);
		if (e!=null) {
			System.out.println(e);
		}else{
			System.out.println("No existe el empleado con el número "+empnum);
		}
	}
	private void addEmpleado() throws NumberFormatException, IOException {
		boolean seguir=true;
		int empnum=-1;
		do{
			try{
				System.out.println("Dame un numero de empleado");
				empnum=Integer.parseInt(teclado.readLine());
				seguir=false;
			}catch(NumberFormatException e){
				System.out.println("Dame un numero.");
			}
		}while(seguir);
		//buscar si existe
		if(buscarEmpleado(empnum)!=null){
			System.out.println("Ya existe un empleado con numero "+empnum);
			return;
		}
		
		//Pedir departamento
		int dptonum=-1;
		do{
			try{
				System.out.println("Dame el numero de departamento");
				dptonum=Integer.parseInt(teclado.readLine());
				seguir=false;
			}catch(NumberFormatException e){
				System.out.println("Dame un numero.");
			}
		}while(seguir);
		//buscar si existe el departamento
		if(!this.d.existeDepartamento(dptonum)){
			System.out.println("No existe el de`partamento "+dptonum);
			return;
		}
		Empleado e=new Empleado();
		e.pedirEmpleado(empnum,dptonum);
		BufferedReader br;
		try{
			br=new BufferedReader(new FileReader(fempleado));
		}catch(FileNotFoundException ex){
			BufferedWriter bwr=new BufferedWriter(new FileWriter(fempleado));
			bwr.close();
			br=new BufferedReader(new FileReader(fempleado));
		}
		BufferedWriter bw=new BufferedWriter(new FileWriter(ftemporal));
		String linea=br.readLine();
		while(linea!=null){
			bw.write(linea);
			bw.newLine();
			linea=br.readLine();
		}
		//añadir la nueva linea con el nuevo empleado
		linea=e.crearLinea();
		bw.write(linea);
		bw.flush();
		bw.close();
		br.close();
		fempleado.delete();
		ftemporal.renameTo(fempleado);
	}
	
	private void listarEmpleados() throws IOException  {
		try {
			Empleado e= new Empleado();
			BufferedReader br= new BufferedReader(new FileReader(fempleado));
			String linea;
			do{
				linea=br.readLine();
				if(!(linea!=null)){
					break;
				}
				//descomponemos la linea y la mostramos
				e.descomponerLinea(linea);
				System.out.println(e);
			}while(true);

			br.close();
		} catch (FileNotFoundException e1) {
			System.out.println("No existe ningún empleado");
		}
	}
	private Empleado buscarEmpleado(int empnum) throws IOException{
		try {
			Empleado e=new Empleado();
			BufferedReader br=new BufferedReader(new FileReader(fempleado));
			String linea=br.readLine();
			while (linea!=null) {
				e.descomponerLinea(linea);
				if (empnum==e.getEmpnum()) {
					br.close();
					return e;
				}
				linea=br.readLine();
			}
			br.close();
			return null;
		} catch (FileNotFoundException exc) {
			return null;
		}
	}
	private void setEmpleado() throws IOException {
		boolean existe=false;
		boolean seguir=true;
		int empnum=-1;
		do{
			try{
				System.out.println("Dame un numero de empleado");
				empnum=Integer.parseInt(teclado.readLine());
				seguir=false;
			}catch(NumberFormatException e){
				System.out.println("Dame un numero.");
			}
		}while(seguir);
		BufferedReader br;
		try {
			br=new BufferedReader(new FileReader(fempleado));
		} catch (FileNotFoundException exc) {
			System.out.println("No existe el empleado con número "+empnum);
			return;
		}
		BufferedWriter bw=new BufferedWriter(new FileWriter(ftemporal));
		Empleado e=new Empleado();
		String linea=br.readLine();
		while (linea!=null) {
			e.descomponerLinea(linea);
			if (empnum==e.getEmpnum()) {
				//
				//Pedimos el departamento y si no existe no hago nada 
				//
				e.pedirEmpleado(empnum);
				linea=e.crearLinea();
				existe=true;
			}
			bw.write(linea);
			bw.newLine();
			linea=br.readLine();
		}
		bw.flush();
		bw.close();
		br.close();
		fempleado.delete();
		ftemporal.renameTo(fempleado);
		if (existe) {
			System.out.println("Se ha modificado el empleado "+empnum+" correctamente.");
		}else{
			System.out.println("No se ha podido modificar el empleado "+empnum+" porque no existe.");
		}
	}
	private void borrarEmpleado() throws IOException {
		boolean existe=false;
		boolean seguir=true;
		int empnum=-1;
		do{
			try{
				System.out.println("Dame un numero de empleado");
				empnum=Integer.parseInt(teclado.readLine());
				seguir=false;
			}catch(NumberFormatException e){
				System.out.println("Dame un numero.");
			}
		}while(seguir);
		BufferedReader br;
		try {
			br= new BufferedReader(new FileReader(fempleado));
		} catch (Exception e) {
			System.out.println("No existe el empleado con el número "+empnum);
			return;
		}
		BufferedWriter bw=new BufferedWriter(new FileWriter(ftemporal));
		Empleado e=new Empleado();
		String linea=br.readLine();
		while (linea!=null) {
			e.descomponerLinea(linea);
			if (empnum!=e.getEmpnum()) {
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
		fempleado.delete();
		ftemporal.renameTo(fempleado);
		if (existe) {
			System.out.println("Se ha eliminado correctamente el empleado "+empnum);		
		}else{
			System.out.println("No se ha podido eliminar el empleado "+empnum+" porque no existe.");
		}
	}

	public boolean existenEmpleadosEnDepartamento(int dptonum) throws IOException{
		BufferedReader br;
		try{
			br=new BufferedReader(new FileReader(fempleado));
		}catch (FileNotFoundException e) {
			return false;
		}
		Empleado e=new Empleado();
		String linea=br.readLine();
		while(linea!=null){
			e.descomponerLinea(linea);
			if(e.getDptonum()==dptonum){
				return true;
			}
			linea=br.readLine();
		}
		
		br.close();
		return false;
	}
	
	public double totalSalarios(){
		double total=0;
		
		return total;
	}
	public double totalSalariosPorDepartamento(int dptonum){
		double total=0;
		
		return total;
	}
}
