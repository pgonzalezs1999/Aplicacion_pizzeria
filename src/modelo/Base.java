/**
 * Paquete que recoge todas las clases utilizadas para la version de consola adaptadas a GUI
 * @author Pablo Gonzalez Sanchez <a href=mailto:pgonzalezs1999@gmail.com>pgonzalezs1999@gmail.com</a>
 */
package modelo; // Las clases utilizadas para la version de consola adaptadas a GUI se recogen en este paquete
/**
 * Proporciona a una pizza toda la informacion correspondiente a una base de la lista de bases del controlador
 */
public class Base
{
	private String tipo; // Transforma la informacion de la base en una cadena de caracteres que se utiliza para nombrar las pizzas
	private boolean gluten; // Si es false, es apto para celiacos; si es true, NO es apto para celiacos
	private double precio; // Predefinido en el constructor del controlador
	private String tamanio; // Predefinido en el constructor del controlador. Solo puede ser "Grande" o "Pequena"
	
	// Constructor
	public Base(String nuevoTipo, boolean nuevoGluten, double nuevoPrecio, String nuevoTamanio)
	{
		// Almacenar en esta clase los datos proporcionados
		this.tipo = nuevoTipo;
		this.gluten = nuevoGluten;
		this.precio = nuevoPrecio;
		this.tamanio = nuevoTamanio;
	}	
	// Getters y setters
	/**
	 * Metodo de acceso al tipo de base en forma de cadena de caracteres explicativa. Por ejemplo, grande sin gluten o pequeña con gluten
	 * @return El valor de la cadena de caracteres que define el tipo de base
	 */
	public String getTipo()
	{
		return this.tipo;
	}	
	/**
	 * Metodo de acceso a la variable que determina si la base es apta para celiacos (false) o no es apta para celiacos (true)
	 * @return Si contiene o no gluten en forma de boolean
	 */
	public boolean getGluten()
	{
		return this.gluten;
	}
	/**
	 * Metodo de acceso a la variable que determina el precio de la base, el cual diferira del precio final de la pizza al anadirle ingredientes
	 * @return El valor del precio de la base
	 */
	public double getPrecio()
	{
		return this.precio;
	}
	/**
	 * Metodo de acceso a la variable que determina el tamano de la pizza
	 * @return El valor de la cadena de caracteres que define el tamano de base
	 */
	public String getTamanio()
	{
		return this.tamanio;
	}
}