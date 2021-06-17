/**
 * Paquete que recoge todas las clases utilizadas para la version de consola adaptadas a GUI
 * @author Pablo Gonzalez Sanchez <a href=mailto:pgonzalezs1999@gmail.com>pgonzalezs1999@gmail.com</a>
 */
package modelo; // Las clases utilizadas para la version de consola adaptadas a GUI se recogen en este paquete
/**
 * Proporciona a una pizza toda la informacion correspondiente a un ingrediente de la lista de ingredientes del controlador
 */
public class Ingrediente
{
	private String nombre; // Predefinido en el constructor del controlador
	private boolean gluten; // Si es false, es apto para celiacos; si es true, NO es apto para celiacos
	private double precio; // Predefinido en el constructor del controlador
	
	// Constructor
	/**
	 * Inicia el constructor. Almacena los valores proporcionados por el controlador
	 * @param nuevoNombre Nombre asignado al ingrediente
	 * @param nuevoGluten Si el ingrediente lleva o no gluten
	 * @param nuevoPrecio Precio asignado al ingrediente
	 */
	public Ingrediente(String nuevoNombre, boolean nuevoGluten, double nuevoPrecio)
	{
		// Almacenar en esta clase los datos proporcionados
		this.nombre = nuevoNombre;
		this.gluten = nuevoGluten;
		this.precio = nuevoPrecio;
	}	
	// Getters y setters
	/**
	 * Metodo de acceso al nombre del ingrediente
	 * @return Cadena de caracteres que identifica el nombre del ingrediente
	 */
	public String getNombre()
	{
		return this.nombre;
	}
	/**
	 * * Metodo de acceso a la variable que determina si el ingrediente es apto para celiacos (false) o no es apto para celiacos (true)
	 * @return Si contiene o no gluten en forma de boolean
	 */
	public boolean getGluten()
	{
		return this.gluten;
	}
	/**
	 * Metodo de acceso a la variable que determina el precio del ingrediente, el cual se sumara al precio total de la pizza
	 * @return El valor del precio del ingrediente
	 */
	public double getPrecio()
	{
		return this.precio;
	}
}