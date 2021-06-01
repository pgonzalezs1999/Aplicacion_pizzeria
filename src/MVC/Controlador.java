package MVC;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.util.Vector;

import noMVC.Base;
import noMVC.Ingrediente;
import noMVC.Orden;
import noMVC.Pizza;

public class Controlador
{
	Vector<Base> listaBases = new Vector<Base>();
	Vector<Ingrediente> listaIngr = new Vector<Ingrediente>();
	
	Orden orden = new Orden();
	Scanner sc = new Scanner(System.in); // Lo borraremos cuando ya no haga falta la consola
	
	// Atributos MVC	
	VentanaPrincipal ventanaPrincipal;
	VentanaPedido ventanaPedido;
	VentanaHistorial ventanaHistorial;
	
	// Constructor
	public Controlador(VentanaPrincipal ventanaPrincipal,
						VentanaPedido ventanaPedido,
						VentanaHistorial ventanaHistorial)
	{
		IniciarControlador();
		
		this.ventanaPrincipal = ventanaPrincipal;
		this.ventanaPedido = ventanaPedido;
		this.ventanaHistorial = ventanaHistorial;
		
		this.crearListenersVentanaPrincipal();
		this.crearListenersVentanaPedido();
		this.crearListenersVentanaHistorial();
		
		this.ventanaPrincipal.setVisible(true);
	}
	
	private void crearListenersVentanaPrincipal()
	{
		//---- Listeners VentanaPrincipal ----//
		this.ventanaPrincipal.getBtnNuevoPedido().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				AbrirPedidoDesdePrincipal();
			}
		});
		
		this.ventanaPrincipal.getBtnVerHistorial().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				AbrirHistorialDesdePrincipal();
			}
		});
	}
	private void crearListenersVentanaPedido()
	{
		//---- Listeners Ventana Pedidos ----//
		this.ventanaPedido.getBtnAniadirPizza().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				try
				{
					String opcion;
					Pizza nuevaPizza = new Pizza();
					
					if(orden.getPizzas().size() == 0)
					{
						nuevaPizza.setNombre("Pizza1");
					}
					else
					{
						nuevaPizza.setNombre("Pizza" + String.valueOf(orden.getPizzas().size()+1));							
					}
					
					System.out.println("¿Que tipo de base prefiere?");
					for(int i = 0; i < listaBases.size(); i++)
					{
						System.out.println("Pulse ("+ (i+1) +") para " + listaBases.get(i).getTipo()
								+ " -> " + listaBases.get(i).getPrecio() + "€");
					}
					opcion = sc.nextLine();
					nuevaPizza.ponerBase(listaBases.get(Integer.parseInt(opcion)-1));
					
					boolean puedeSalir2 = false;
					do
					{
						System.out.println("Que ingrediente desea agregar?");
						for(int i = 0; i < listaIngr.size(); i++)
						{
							boolean yaPuesto = false;
							for(int j = 0; j < nuevaPizza.getIngredientes().size(); j++)
							{
								if(listaIngr.get(i).getNombre().equals(nuevaPizza.getIngredientes().get(j).getNombre()))
								{
									yaPuesto = true;
								}
							}
							if(yaPuesto == false)
							{
								System.out.println("Pulse ("+ (i+1) +") para agregarr " + listaIngr.get(i).getNombre()
										+ " -> " + listaIngr.get(i).getPrecio() + "€");
							}
							else
							{
								System.out.println("--- " + listaIngr.get(i).getNombre() + " ya agregado --- ");
							}
						}
						System.out.println("Pulse (0) si no desea agregarr mas ingredientes");
						
						int opcion2 = sc.nextInt();
						sc.nextLine();
						if(opcion2 == 0)
						{
							puedeSalir2 = true;
						}
						else
						{
							nuevaPizza.ponerIngrediente(listaIngr.get(opcion2 - 1));								
						}
					}
					while(puedeSalir2 == false);
					
					orden.aniadirPizza(nuevaPizza);
					System.out.println("Pizza agregada correctamente. Regresando al menu...\n");
				}
				catch(Exception e)
				{
					System.out.println("Valor introducido incorrecto. Regresando al menu...\n");
					//e.printStackTrace();
				}
			}
		});
		
		this.ventanaPedido.getBtnEliminarPizza().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				orden.eliminarUltimaPizza();
			}
		});
		
		this.ventanaPedido.getBtnVerPedido().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if(orden.getPizzas().isEmpty() == false)
				{
					boolean paraCeliaco = true;
					for(int i = 0; i < orden.getPizzas().size(); i++)
					{
						System.out.println("\n+ " + orden.getPizzas().get(i).getNombre() + ":");
						System.out.println("    Base: " + orden.getPizzas().get(i).getBase().getTipo());
						System.out.println("    Ingredientes: ");
						for(int j = 0; j < orden.getPizzas().get(i).getIngredientes().size(); j++)
						{
							System.out.println("       - " + orden.getPizzas().get(i).getIngredientes().get(j).getNombre());
							if(orden.getPizzas().get(i).getIngredientes().get(j).getGluten() == true)
							{
								paraCeliaco = false;
							}
						}
						System.out.println("    Precio: " + Math.round(orden.getPizzas().get(i).calcularCostePizza()*100.0)/100.0 + "�");
					}
					System.out.println("\nPrecio total: " + Math.round(orden.calcularCostePedido()*100.0)/100.0 + "€");
					if(paraCeliaco == true)
					{
						System.out.println("Su pedido SI es apto para celiacos\n");			
					}
					else
					{
						System.out.println("Su pedido NO es apto para celiacos\n");
					}						
				}
				else
				{
					System.out.println("Ninguna pizza encargada. Regresando al menu...\n");
				}
			}
		});
		
		this.ventanaPedido.getBtnEnviarPedido().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if(orden.getPizzas().isEmpty() == false)
				{
					System.out.println("Enviando pedido... Llegara en unos 25 minutos");
					System.out.println("El coste de su pedido es: " + orden.calcularCostePedido() + "€");
				}
				else
				{
					System.out.println("No emitimos pedidos vacios. Regresando al menu...\n");
				}
			}
		});
		
		this.ventanaPedido.getBtnCancelarPedido().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				AbrirPrincipalDesdePedido();
			}
		});
	}
	private void crearListenersVentanaHistorial()
	{
		//---- Listeners Ventana Historial ----//
		this.ventanaHistorial.getBtnVolver().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				AbrirPrincipalDesdeHistorial();
			}
		});
	}
	
	private void AbrirHistorialDesdePrincipal()
	{
		this.ventanaHistorial.setVisible(true);
		this.ventanaPrincipal.setVisible(false);
	}
	private void AbrirPrincipalDesdeHistorial()
	{
		this.ventanaPrincipal.setVisible(true);
		this.ventanaHistorial.setVisible(false);
	}
	private void AbrirPedidoDesdePrincipal()
	{
		this.ventanaPedido.setVisible(true);
		this.ventanaPrincipal.setVisible(false);
	}
	private void AbrirPrincipalDesdePedido()
	{
		this.ventanaPrincipal.setVisible(true);
		this.ventanaPedido.setVisible(false);
	}
	private void IniciarControlador()
	{
		// --- Elementos del Main de consola, a�adidos para probar que funciona el controlador
		Base baseAux = new Base("Base pequena", true, 1.5, "Pequena");
		listaBases.add(baseAux);
		baseAux = new Base("Base grande", true, 3.75, "Grande");
		listaBases.add(baseAux);
		baseAux = new Base("Base pequena sin gluten", true, 2, "Pequena");
		listaBases.add(baseAux);
		baseAux = new Base("Base grande sin gluten", true, 4.5, "Grande");
		listaBases.add(baseAux);
		
		Ingrediente ingrAux = new Ingrediente("Queso", true, 0.6);
		listaIngr.add(ingrAux);
		ingrAux = new Ingrediente("Queso sin gluten", false, 0.9);
		listaIngr.add(ingrAux);
		ingrAux = new Ingrediente("Tomate", false, 0.65);
		listaIngr.add(ingrAux);
		ingrAux = new Ingrediente("Cebolla", false, 0.3);
		listaIngr.add(ingrAux);
		ingrAux = new Ingrediente("Bacon", false, 1.25);
		listaIngr.add(ingrAux);
		ingrAux = new Ingrediente("Jamon York", true, 0.75);
		listaIngr.add(ingrAux);
		ingrAux = new Ingrediente("Jamon Serrano", true, 1.2);
		listaIngr.add(ingrAux);
		ingrAux = new Ingrediente("Atun", false, 0.65);
		listaIngr.add(ingrAux);
		ingrAux = new Ingrediente("Anchoas", false, 0.75);
		listaIngr.add(ingrAux);
		ingrAux = new Ingrediente("Pepperoni", true, 0.85);
		listaIngr.add(ingrAux);		
	}
}