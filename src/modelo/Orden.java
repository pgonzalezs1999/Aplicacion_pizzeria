package modelo;

import java.util.Date;
//import java.util.Date;
import java.util.Vector;

import ficheros.EditarCSV;

public class Orden
{
	private int ID;
	//private String nombre;
	//private String direccion;
	//private int telefono;
	private Date fecha_pedido;
	private Vector<Pizza> pizzas = new Vector<Pizza>();
	private boolean entregado;
	
	public Orden()
	{
		EditarCSV leerPedidos = new EditarCSV("pedidos.csv");
		leerPedidos.cargarCSV();
		this.ID = leerPedidos.generarID();
	}
	
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
			}
		}
		catch(Exception e)
		{
			System.out.println("No se ha podido eliminar la ultima pizza");
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
	
	public boolean getEntregado()
	{
		return this.entregado;
	}
	
	public int getID()
	{
		return this.ID;
	}
}