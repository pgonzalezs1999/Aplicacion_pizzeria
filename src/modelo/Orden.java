/**
 * Paquete que recoge todas las clases utilizadas para la version de consola adaptadas a GUI
 * @author Pablo Gonzalez Sanchez <a href=mailto:pgonzalezs1999@gmail.com>pgonzalezs1999@gmail.com</a>
 */
package modelo; // Las clases utilizadas para la version de consola adaptadas a GUI se recogen en este paquete

import java.util.Date;
import java.util.Vector;
import ficheros.EditarCSV;
/**
 * Lista todas las pizzas seleccionadas para el pedido en curso y proporciona informacion del conjunto
 */
public class Orden
{
	private int ID; // Identificador necesario para poder diferenciarlos a nivel de fichero y a nivel de otras features del programa
	private Date fecha_pedido; // Cuando se emitio (se pulso confirmar) el pedido
	private Vector<Pizza> pizzas = new Vector<Pizza>(); // Vector que almacena y manipula las pizzas mientras se gestiona un pedido
	private boolean entregado; // Cuando la pizza ha llegado al domicilio, se marca true
	
	// Constructor
	public Orden()
	{
		EditarCSV leerPedidos = new EditarCSV("pedidos.csv"); // Cada vez que haya que anadir o eliminar algo del fichero se hara desde esta clase
		leerPedidos.cargarCSV(); // Descarga los datos del CSV
		this.ID = leerPedidos.generarID(); // Genera un ID valido para el pedido (que no sea repetido y no dejarse numeros por medio)
	}
	/**
	 * Metodo para anadir una pizza al vector de la orden en curso, una vez que dicha pizza ha sido terminada y comprobado que no tiene errores
	 */
	public void aniadirPizza(Pizza nuevaPizza)
	{
		pizzas.add(nuevaPizza); // Una vez la pizza se ha creado y terminado correctamente, se envia al vector de la orden en curso
	}
	/**
	 * Metodo para eliminar del pedido en curso la ultima pizza anadida al vector
	 */
	public void eliminarUltimaPizza()
	{
		try
		{
			if(pizzas.isEmpty() == false) // Si intenta eliminar una pizza cuando esta vacia, el size del vector se pondria en -1 y saltaria error
			{
				pizzas.remove(pizzas.lastElement()); // Si hay al menos una pizza, que elimine del vector la ultima
			}
		}
		catch(Exception e)
		{
			System.out.println("No se ha podido eliminar la ultima pizza"); // No lo muestra al usuario, pero nos sirve para detectar el problema
		}
	}
	/**
	 * Metodo para calcular el coste acumulado de un pedido, equivalente a la suma de los precios de todas sus pizzas
	 * @return El coste total del pedido
	 */
	public double calcularCostePedido()
	{
		double coste = 0; // Coste del pedido hasta que se anada al menos una pizza
		
		for(int i = 0; i < pizzas.size(); i++) // Recorre el vector pizzas
		{
			coste += pizzas.get(i).calcularCostePizza(); // En cada iteracion suma el precio de la pizza y lo suma al acumulado
		}
		return coste;
	}
	/**
	 * Metodo para que el repartidor indique que un pedido ha sido entregado al cliente exitosamente
	 */
	public void confirmarRecepcion()
	{
		this.entregado = true; // Se finaliza el trato del pedido una vez se cumple esto. True = entregado, false = cocinando o en camino
	}
	// Getters y setters
	/**
	 * Metodo que devuelve el vector que almacena las pizzas encargadas en un pedido
	 * @return El vector que contiene las pizzas
	 */
	public Vector<Pizza> getPizzas()
	{
		return this.pizzas;
	}
	/**
	 * Metodo que devuelve si el pedido ha sido confirmado o aun esta pendiente de ser entregado
	 * @return El estado del pedido
	 */
	public boolean getEntregado()
	{
		return this.entregado; // true = entregado, false = cocinando o en camino
	}
	/**
	 * Metodo que devuelve el ID de un pedido
	 * @return El ID del pedido
	 */
	public int getID()
	{
		return this.ID;
	}
}