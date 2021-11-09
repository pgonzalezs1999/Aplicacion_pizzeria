/**
 * Paquete que recoge todas las clases utilizadas para las pruebas JUnit
 * @author Pablo Gonzalez Sanchez <a href=mailto:pgonzalezs1999@gmail.com>pgonzalezs1999@gmail.com</a>
 */
package pruebas;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import modelo.Base;
import modelo.Ingrediente;
import modelo.Orden;
import modelo.Pizza;
import ficheros.EditarCSV;
/**
 * Clase que genera las pruebas JUnit
 */
class PruebasJUnit
{
	Orden orden;
	Pizza pizza;
	Ingrediente ingr;
	Base base;
	EditarCSV pizzasCSV;
	EditarCSV pedidosCSV;
	
	public PruebasJUnit()
	{
		this.pizzasCSV = new EditarCSV("pizzas.csv");
		this.pedidosCSV = new EditarCSV("pedidos.csv");
		pizzasCSV.cargarCSV();
		pedidosCSV.cargarCSV();
		this.base = new Base("Pequena", true, 1.5, "Pequena");
		this.ingr = new Ingrediente("Tomate", false, 0.5);
		this.pizza = new Pizza();
		pizza.ponerBase(base);
		pizza.ponerIngrediente(ingr);
		this.orden = new Orden();
		orden.aniadirPizza(pizza);
	}
	
	@Test
	public void pruebaAniadirPizza()
	{
		orden.aniadirPizza(pizza);		
		assertEquals(2, orden.getPizzas().size()); // Espero que solo exista la pizza que acabo de añadir
	}
	@Test
	public void pruebaEliminarPizza()
	{
		orden.aniadirPizza(pizza);
		orden.eliminarUltimaPizza();
		assertEquals(1, orden.getPizzas().size()); // Sabiendo que aniadirPizza() funciona, espero que elimine la pizza y se quede vacío
	}
	@Test
	public void pruebaAnadirFilaPizza()
	{
		int original = pizzasCSV.contarLineasFichero();
		pizzasCSV.addPizza(orden, pizza);
		int resta = pizzasCSV.contarLineasFichero() - original;
		assertEquals(1, resta);
		pizzasCSV.cancelarOrden(orden);
	}
	@Test
	public void pruebaPrecioPizza()
	{
		assertEquals(5.5, pizza.calcularCostePizza());
	}
	@Test
	public void pruebaPrecioPedido()
	{
		assertEquals(5.5, orden.calcularCostePedido());
	}
}