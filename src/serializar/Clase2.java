package serializar;
import java.util.Date;
import java.util.Vector;

public class Clase2 {
	// Atributos
	private Vector<Clase1> coleccion;
	private Date atributo2;
	private String atributo3;

	// Constructor
	public Clase2(Vector<Clase1> coleccion, Date atributo2, String atributo3) {
		this.coleccion = coleccion;
		this.atributo2 = atributo2;
		this.atributo3 = atributo3;
	}
	
	// Getters/Setters
	public Vector<Clase1> getColeccion() {
		return coleccion;
	}
	public void setColeccion(Vector<Clase1> coleccion) {
		this.coleccion = coleccion;
	}
	public Date getAtributo2() {
		return atributo2;
	}
	public void setAtributo2(Date atributo2) {
		this.atributo2 = atributo2;
	}
	public String getAtributo3() {
		return atributo3;
	}
	public void setAtributo3(String atributo3) {
		this.atributo3 = atributo3;
	}
}
