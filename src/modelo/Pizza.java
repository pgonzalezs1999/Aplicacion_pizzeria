package modelo;

import java.util.Vector;

public class Pizza
{
	private String nombre_pizza;
	private Base base;
	private Vector<Ingrediente> ingredientes = new Vector<Ingrediente>();
	
	public void ponerIngrediente(Ingrediente i)
	{
		ingredientes.add(i);
	}
	
	public void ponerBase(Base b)
	{
		this.base = b;
	}
	
	public double calcularCostePizza()
	{
		double coste = 3.5;
		
		for(int i = 0; i < ingredientes.size(); i++)
		{
			coste += ingredientes.get(i).getPrecio();
		}
		
		coste += base.getPrecio();
		
		return coste;
	}
	
	public boolean aptaCeliacos()
	{
		boolean esApta = true;
		
		if (base.getGluten() == true)
		{
			esApta = false;
		}
		else
		{
			for(int i = 0; i < ingredientes.size(); i++)
			{
				if(ingredientes.get(i).getGluten() == true)
				{
					esApta = false;
				}
			}
		}
		return esApta;
	}
	
	public String getNombre()
	{
		return this.nombre_pizza;
	}
	
	public Base getBase()
	{
		return this.base;
	}
	
	public Vector<Ingrediente> getIngredientes()
	{
		return this.ingredientes;
	}
	
	public void setNombre(String nuevoNombre)
	{
		this.nombre_pizza = nuevoNombre;
	}
}