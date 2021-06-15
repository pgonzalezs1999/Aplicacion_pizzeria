package pruebas;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import modelo.Orden;
import modelo.Pizza;

import org.junit.Before;

class PruebasJUnit
{
	Orden orden;
	Pizza pizza;
	
	@Before
	public void iniciarOrden()
	{
		this.orden = new Orden();
	}
	public void iniciarPizza()
	{
		this.pizza = new Pizza();
	}
	
	@Test
	public void pruebaAniadirPizza()
	{
		iniciarOrden();
		orden.aniadirPizza(pizza);		
		assertEquals(1, orden.getPizzas().size()); // Espero que solo exista la pizza que acabo de añadir
	}
	@Test
	public void pruebaEliminarPizza()
	{
		iniciarOrden();
		orden.aniadirPizza(pizza);
		orden.eliminarUltimaPizza();
		assertEquals(0, orden.getPizzas().size()); // Sabiendo que aniadirPizza() funciona, espero que elimine la pizza y se quede vacío
	}
	/*@Test
	public void pruebaResta() {
		int resta = orden.resta(2,3);
		assertEquals(-1, resta);
	}
	@Test
	public void pruebaMultiplicacion() {
		int multiplicacion = orden.multiplicacion(2,3);
		assertEquals(6, multiplicacion);
	}
	@Test
	public void pruebaDivision() {
		double division = orden.division(5,2);
		assertEquals(2.5, division, 0.02);
	}*/
}