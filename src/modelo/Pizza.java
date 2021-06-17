/**
 * Paquete que recoge todas las clases utilizadas para la version de consola adaptadas a GUI
 * @author Pablo Gonzalez Sanchez <a href=mailto:pgonzalezs1999@gmail.com>pgonzalezs1999@gmail.com</a>
 */
package modelo; // Las clases utilizadas para la version de consola adaptadas a GUI se recogen en este paquete

import java.util.Vector;
/**
 * Agrupa ingredientes y base seleccionada para crear una pizza que anadir a la orden
 */
public class Pizza
{
	private String nombre_pizza; // Identificador de pizzas para cuando haya ques seleccionar alguna pizza o pedido en concreto
	private Base base; // La asignada por el usuario
	private Vector<Ingrediente> ingredientes = new Vector<Ingrediente>(); // Los asignados por el usuario
	/**
	 * Metodo para anadir el ingrediente seleccionado a la lista de ingredientes de una pizza
	 * @param i Ingrediente que se desea anadir
	 */
	public void ponerIngrediente(Ingrediente i)
	{
		ingredientes.add(i); // Añade el ingrediente seleccionado a la lista de ingredientes de una pizza
	}
	/**
	 * Metodo para anadir una base seleccionada a una pizza
	 * @param b Base que se desea anadir
	 */
	public void ponerBase(Base b)
	{
		this.base = b; // Añade una base seleccionada a una pizza
	}
	/**
	 * Metodo para calcular el coste final de una pizza
	 * @return Precio de la pizza
	 */
	public double calcularCostePizza()
	{
		double coste = 3.5; // Coste inicial, para asegurarse que siempre sale rentable enviar una pizza, por simple que sea
		
		for(int i = 0; i < ingredientes.size(); i++) // Recorre la lista de ingredientes de la pizza
		{
			coste += ingredientes.get(i).getPrecio(); // En cada iteracion suma el precio del ingrediente al acumulado
		}	
		coste += base.getPrecio(); // Suma el precio de la base al acumulado
		
		return coste;
	}
	/**
	 * Metodo para determinar si una pizza es apta para celiacos (true) o no lo es (false)
	 * @return si la pizza es o no apta para celiacos
	 */
	public boolean aptaCeliacos()
	{
		boolean esApta = true; // La pizza tiene presuncion de glutenecia: es apta para celiacos hasta que se demuestre lo contrario
		
		if (base.getGluten() == true) // Comprueba si la base es apta para celiacos
		{
			esApta = false; // Si es apta no pasa nada, pero si no lo es, la pizza automaticamente tampoco lo es
		}
		else
		{
			for(int i = 0; i < ingredientes.size(); i++) // Recorre el vector de ingredientes
			{
				if(ingredientes.get(i).getGluten() == true) // Comprueba si el ingrediente es apta para celiacos
				{
					esApta = false; // Si es apto no pasa nada, pero si no lo es, la pizza automaticamente tampoco lo es
				}
			}
		}
		return esApta;
	}
	// Getters y setters
	/**
	 * Metodo que devuelve el nombre de la pizza.
	 * Se usara tanto para diferenciar la pizza de sus similares en el pedido, como para definirla cuando se muestre en pantalla
	 * @return el nombre de una pizza
	 */
	public String getNombre()
	{
		return this.nombre_pizza;
	}
	/**
	 * Metodo que devuelve la base utilizada para esa pizza
	 * @return la base usada para una pizza
	 */
	public Base getBase()
	{
		return this.base;
	}
	/**
	 * Metodo que devuelve el vector que almacena los ingredientes elegidos para la pizza
	 * @return todos los ingredientes de una pizza almacenados en un vector
	 */
	public Vector<Ingrediente> getIngredientes()
	{
		return this.ingredientes;
	}
	/**
	 * Metodo que designa un nuevo nombre a una pizza
	 * @param nuevoNombre Nombre que se desea asignar
	 */
	public void setNombre(String nuevoNombre)
	{
		this.nombre_pizza = nuevoNombre;
	}
}