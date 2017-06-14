package empresa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Empleado {
	
	private int empnum;
	private String apellido;
	private String oficio;
	private double salario;
	private double comision;
	private int dir;
	private int dptonum;
	private String fechaalta;
	private double totalsalario;
	private BufferedReader teclado=new BufferedReader(new InputStreamReader(System.in));
	public Empleado() {

	}

	public Empleado(int empnum, String apellido, String oficio, double salario, double comision, int dir, int dptonum,
			String fechaalta) {
		this.empnum = empnum;
		this.apellido = apellido;
		this.oficio = oficio;
		this.salario = salario;
		this.comision = comision;
		this.dir = dir;
		this.dptonum = dptonum;
		this.fechaalta = fechaalta;
	}

	public int getEmpnum() {
		return empnum;
	}

	public void setEmpnum(int empnum) {
		this.empnum = empnum;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getOficio() {
		return oficio;
	}

	public void setOficio(String oficio) {
		this.oficio = oficio;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public double getComision() {
		return comision;
	}

	public void setComision(double comision) {
		this.comision = comision;
	}

	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}

	public int getDptonum() {
		return dptonum;
	}

	public void setDptonum(int dptonum) {
		this.dptonum = dptonum;
	}

	public String getFechaalta() {
		return fechaalta;
	}

	public void setFechaalta(String fechaalta) {
		this.fechaalta = fechaalta;
	}

	public double getTotalsalario() {
		return totalsalario;
	}

	public void setTotalsalario(double totalsalario) {
		this.totalsalario = totalsalario;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empleado other = (Empleado) obj;
		if (apellido == null) {
			if (other.apellido != null)
				return false;
		} else if (!apellido.equals(other.apellido))
			return false;
		if (Double.doubleToLongBits(comision) != Double.doubleToLongBits(other.comision))
			return false;
		if (dir != other.dir)
			return false;
		if (dptonum != other.dptonum)
			return false;
		if (empnum != other.empnum)
			return false;
		if (fechaalta == null) {
			if (other.fechaalta != null)
				return false;
		} else if (!fechaalta.equals(other.fechaalta))
			return false;
		if (oficio == null) {
			if (other.oficio != null)
				return false;
		} else if (!oficio.equals(other.oficio))
			return false;
		if (Double.doubleToLongBits(salario) != Double.doubleToLongBits(other.salario))
			return false;
		return true;
	}

	public String toString() {
		return "Empleado [empnum=" + empnum + ", apellido=" + apellido + ", oficio=" + oficio + ", salario=" + salario
				+ ", comision=" + comision + ", dir=" + dir + ", dptonum=" + dptonum + ", fechaalta=" + fechaalta + "]";
	}
	
	public void pedirEmpleado(int empnum) throws IOException{
		this.empnum=empnum;
		System.out.println("Introduzca el apellido del empleado");
		this.apellido=teclado.readLine();
		
		System.out.println("Introduzca el oficio del empleado.");
		this.oficio=teclado.readLine();
		
		boolean seguir=true;
		do{
			try{
				System.out.println("Introduzca el salario del empleado.");
				this.salario=Double.parseDouble(teclado.readLine());
				seguir=false;
			}catch(NumberFormatException e){
				System.out.println("Dame un numero decimal");
			}
		}while(seguir);
		
		seguir=true;
		do{
			try{
				System.out.println("Introduzca la comisión del empleado.");
				this.comision=Double.parseDouble(teclado.readLine());
				seguir=false;
			}catch(NumberFormatException e){
				System.out.println("Dame un numero decimal.");
			}
		}while(seguir);
		
		System.out.println("Introduzca el director del empleado.");
		this.dir=Integer.parseInt(teclado.readLine());
		
		System.out.println("Introduzca el número del departamento.");
		this.dptonum=Integer.parseInt(teclado.readLine());
		
		System.out.println("Introduzca la fecha de alta.");
		this.fechaalta=teclado.readLine();
		
		this.totalsalario=this.salario+this.comision;
	}
	public void pedirEmpleado(int empnum,int dptonum) throws IOException{
		this.empnum=empnum;
		this.dptonum=dptonum;
		System.out.println("Introduzca el apellido del empleado");
		this.apellido=teclado.readLine();
		
		System.out.println("Introduzca el oficio del empleado.");
		this.oficio=teclado.readLine();
		
		boolean seguir=true;
		do{
			try{
				System.out.println("Introduzca el salario del empleado.");
				this.salario=Double.parseDouble(teclado.readLine());
				seguir=false;
			}catch(NumberFormatException e){
				System.out.println("Dame un numero decimal");
			}
		}while(seguir);
		
		seguir=true;
		do{
			try{
				System.out.println("Introduzca la comisión del empleado.");
				this.comision=Double.parseDouble(teclado.readLine());
				seguir=false;
			}catch(NumberFormatException e){
				System.out.println("Dame un numero decimal.");
			}
		}while(seguir);
		
		System.out.println("Introduzca el director del empleado.");
		this.dir=Integer.parseInt(teclado.readLine());
		
		System.out.println("Introduzca la fecha de alta.");
		this.fechaalta=teclado.readLine();
		
		this.totalsalario=this.salario+this.comision;
	}
	private String addEspacios(String cadena, int longitud){
		String ret=cadena;
		for(int i=cadena.length();i<longitud;i++){
			ret+=" ";
		}
		
		return ret.substring(0, longitud);
	}
	public String crearLinea(){
		String linea="";
		linea+=addEspacios(""+this.empnum,4);
		linea+=addEspacios(this.apellido,20);
		linea+=addEspacios(this.oficio,20);
		linea+=addEspacios(""+this.salario,7);
		linea+=addEspacios(""+this.comision,7);
		linea+=addEspacios(""+this.dir,4);
		linea+=addEspacios(""+this.dptonum,4);
		linea+=addEspacios(this.fechaalta,10);
		return linea;
	}
	public void descomponerLinea(String linea){
		this.empnum=Integer.parseInt(linea.substring(0,4).trim());
		this.apellido=linea.substring(4, 24).trim();
		this.oficio=linea.substring(24,44).trim();
		this.salario=Double.parseDouble(linea.substring(44,51).trim());
		this.comision=Double.parseDouble(linea.substring(51,58).trim());
		this.dir=Integer.parseInt(linea.substring(58,62).trim());
		this.dptonum=Integer.parseInt(linea.substring(62,66).trim());
		this.fechaalta=linea.substring(66,76).trim();
		
	}
}
