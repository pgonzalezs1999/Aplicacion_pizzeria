import java.util.Date;
import java.util.Vector;

public class Orden
{
	private String nombre;
	private String direccion;
	private int telefono;
	private Date fecha_pedido;
	private Vector<Pizza> pizzas = new Vector<Pizza>();
	private boolean entregado;
	
	public void aniadirPizza(Pizza nuevaPizza)
	{
		pizzas.add(nuevaPizza);
	}
	
	public void eliminarUltimaPizza()
	{
		try
		{
			if(pizzas.isEmpty() == false)
			{
				pizzas.remove(pizzas.lastElement());
				System.out.println("Pizza eliminada correctamente. Regresando al menú...\n");
			}
			else
			{
				System.out.println("No hay ninguna pizza que eliminar\n");
			}
		}
		catch(Exception e)
		{
			System.out.println("No se ha podido eliminar la última pizza");
			e.printStackTrace();
		}
	}
	
	public double calcularCostePedido()
	{
		double coste = 0;
		
		for(int i = 0; i < pizzas.size(); i++)
		{
			coste += pizzas.get(i).calcularCostePizza();
		}
		return coste;
	}
	
	public void confirmarRecepcion()
	{
		this.entregado = true;
	}
	
	public void MostrarPizzas()
	{
		for(int i = 0; i < pizzas.size(); i++)
		{
			System.out.println(pizzas.get(i).getNombre() + ", " + pizzas.get(i).calcularCostePizza());
		}
	}
	
	public Vector<Pizza> getPizzas()
	{
		return this.pizzas;
	}
}