package noMVC;

public class Base
{
	private String tipo;
	private boolean gluten;
	private double precio;
	private String tamanio;
	
	public Base(String nuevoTipo, boolean nuevoGluten, double nuevoPrecio, String nuevoTamanio)
	{
		this.tipo = nuevoTipo;
		this.gluten = nuevoGluten;
		this.precio = nuevoPrecio;
		this.tamanio = nuevoTamanio;
	}
	
	public String getTipo()
	{
		return this.tipo;
	}
	
	public boolean getGluten()
	{
		return this.gluten;
	}
	
	public double getPrecio()
	{
		return this.precio;
	}
	
	public String getTamanio()
	{
		return this.tamanio;
	}
}