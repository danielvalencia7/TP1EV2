package empresa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Departamento {
	
	private int dptonum;
	private String dnombre;
	private String loc;
	private double totalsalarios;
	private BufferedReader teclado=new BufferedReader(new InputStreamReader(System.in));
	public Departamento() {
	}

	public Departamento(int dptonum, String dnombre, String loc) {
		super();
		this.dptonum = dptonum;
		this.dnombre = dnombre;
		this.loc = loc;
	}

	public int getDptonum() {
		return dptonum;
	}

	public void setDptonum(int dptonum) {
		this.dptonum = dptonum;
	}

	public String getDnombre() {
		return dnombre;
	}

	public void setDnombre(String dnombre) {
		this.dnombre = dnombre;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	@Override
	public String toString() {
		return "Departamento [dptonum=" + dptonum + ", dnombre=" + dnombre + ", loc=" + loc + "]";
	}

	

	public double getTotalsalarios() {
		return totalsalarios;
	}

	public void setTotalsalarios(double totalsalarios) {
		this.totalsalarios += totalsalarios;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Departamento other = (Departamento) obj;
		if (dnombre == null) {
			if (other.dnombre != null)
				return false;
		} else if (!dnombre.equals(other.dnombre))
			return false;
		if (dptonum != other.dptonum)
			return false;
		if (loc == null) {
			if (other.loc != null)
				return false;
		} else if (!loc.equals(other.loc))
			return false;
		return true;
	}
	
	public void pedirDepartamento(int dptonum) throws IOException{
		this.dptonum=dptonum;
		System.out.println("inserte en nombre del departamento");
		this.dnombre=teclado.readLine();
		System.out.println("inserte la localizacion del departamento");
		this.loc=teclado.readLine();
	}	
	
	private String addEspacios(String cadena,int longitud){
		String ret=cadena;
		for(int i=cadena.length();i<longitud;i++){
			ret+=" ";
		}
		return ret.substring(0, longitud);
	}
	public String crearLinea(){
		String linea="";
		linea=addEspacios(""+this.dptonum,4);
		linea+=addEspacios(this.dnombre, 40);
		linea+=addEspacios(this.loc, 40);
		return linea;
	}
	
	public void descomponerLinea(String linea){
		this.dptonum=Integer.parseInt(linea.substring(0,4).trim());
		this.dnombre=linea.substring(4,44).trim();
		this.loc=linea.substring(44,84).trim();
	}
}
