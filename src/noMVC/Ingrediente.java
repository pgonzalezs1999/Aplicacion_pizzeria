package noMVC;

public class Ingrediente
{
	private String nombre;
	private boolean gluten;
	private double precio;
	
	public Ingrediente(String nuevoNombre, boolean nuevoGluten, double nuevoPrecio)
	{
		this.nombre = nuevoNombre;
		this.gluten = nuevoGluten;
		this.precio = nuevoPrecio;
	}
	
	public String getNombre()
	{
		return this.nombre;
	}
	
	public boolean getGluten()
	{
		return this.gluten;
	}
	
	public double getPrecio()
	{
		return this.precio;
	}
	
	/*public void setNombre(String nuevoNombre)
	{
		this.nombre = nuevoNombre;
	}
	
	public void setGluten(boolean nuevoGluten)
	{
		this.gluten = nuevoGluten;
	}
	
	public void setPrecio(int nuevoPrecio)
	{
		this.precio = nuevoPrecio;
	}*/
}