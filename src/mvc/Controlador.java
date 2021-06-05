package mvc;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import ficheros.EditarCSV;
import modelo.Base;
import modelo.Ingrediente;
import modelo.Orden;
import modelo.Pizza;

public class Controlador
{
	Vector<Base> listaBases = new Vector<Base>();
	Vector<Ingrediente> listaIngr = new Vector<Ingrediente>();
	
	private Orden nuevaOrden;
	private Pizza nuevaPizza;
	private EditarCSV pizzasCSV = new EditarCSV("pizzas.csv");
	private EditarCSV pedidosCSV = new EditarCSV("pedidos.csv");
	
	// Atributos MVC	
	VentanaPrincipal ventanaPrincipal;
	VentanaPedido ventanaPedido;
	VentanaPizza ventanaPizza;
	VentanaHistorial ventanaHistorial;
	
	// Constructor
	public Controlador(VentanaPrincipal ventanaPrincipal,
						VentanaPedido ventanaPedido,
						VentanaPizza ventanaPizza,
						VentanaHistorial ventanaHistorial)
	{
		IniciarBasesIngredientes();
		
		this.ventanaPrincipal = ventanaPrincipal;
		this.ventanaPedido = ventanaPedido;
		this.ventanaPizza = ventanaPizza;
		this.ventanaHistorial = ventanaHistorial;
		
		this.crearListenersVentanaPrincipal();
		this.crearListenersVentanaPedido();
		this.crearListenersVentanaPizza();
		this.crearListenersVentanaHistorial();
		
		this.ventanaPrincipal.setVisible(true);
		
		pizzasCSV.cargarCSV();
		pedidosCSV.cargarCSV();
		
		nuevaPizza = new Pizza();
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
				nuevaPizza = new Pizza();
				nuevaPizza.ponerBase(listaBases.get(3)); // Para que funcione provisionalmente, luego quitar esto
				AbrirPizzaDesdePedido();
			}
		});
		
		this.ventanaPedido.getBtnEliminarPizza().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaOrden.eliminarUltimaPizza();
			}
		});
		
		this.ventanaPedido.getBtnVerPedido().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if(nuevaOrden.getPizzas().isEmpty() == false)
				{
					String[][] matrizAux = new String[nuevaOrden.getPizzas().size()][5];
					
					boolean paraCeliaco = true;
					for(int i = 0; i < nuevaOrden.getPizzas().size(); i++)
					{
						matrizAux[i][0] = nuevaOrden.getPizzas().get(i).getNombre();
						matrizAux[i][1] = nuevaOrden.getPizzas().get(i).getBase().getTipo();
						
						matrizAux[i][2] = nuevaOrden.getPizzas().get(i).getIngredientes().get(0).getNombre() + ", ";
						for(int j = 1; j < nuevaOrden.getPizzas().get(i).getIngredientes().size(); j++)
						{
							matrizAux[i][2] = matrizAux[i][2] + nuevaOrden.getPizzas().get(i).getIngredientes().get(j).getNombre() + ", ";
							if(nuevaOrden.getPizzas().get(i).getIngredientes().get(j).getGluten() == true)
							{
								paraCeliaco = false;
							}
						}
						matrizAux[i][3] = Math.round(nuevaOrden.getPizzas().get(i).calcularCostePizza()*100.0)/100.0 + " euros";
						matrizAux[i][4] = Math.round(nuevaOrden.calcularCostePedido()*100.0)/100.0 + " euros";
					}
					if(paraCeliaco == true)
					{
						concatenar(ventanaPedido.getLabelDesgloseText(), ("Su pedido SI es apto para celiacos\n"));			
					}
					else
					{
						concatenar(ventanaPedido.getLabelDesgloseText(), ("Su pedido NO es apto para celiacos\n"));
					}
					ventanaPedido.CrearTablaPizzas(matrizAux);
				}
				else
				{
					ventanaPedido.setLabelDesgloseText("Ninguna pizza encargada");
				}
			}
		});
		
		this.ventanaPedido.getBtnEnviarPedido().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if(nuevaOrden.getPizzas().isEmpty() == false)
				{
					System.out.println("Enviando pedido... Llegara en unos 25 minutos");
					System.out.println("El coste de su pedido es: " + nuevaOrden.calcularCostePedido() + "â‚¬");
				}
				else
				{
					System.out.println("No emitimos pedidos vacios");
				}
			}
		});
		
		this.ventanaPedido.getBtnCancelarPedido().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaOrden.aniadirPizza(nuevaPizza);
				
				AbrirPrincipalDesdePedido();
			}
		});
	}
	private void crearListenersVentanaPizza()
	{
		//---- Listeners Ventana Historial ----//
		this.ventanaPizza.getBtnEnviarPizza().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if(nuevaPizza.getIngredientes().size() == 0)
				{
					System.out.println("No puede enviar una pizza sin ingredientes");
				}
				else
				{
					String nuevoNombre = "Pizza con ";
					for(int i = 0; i < nuevaPizza.getIngredientes().size(); i++)
					{
						nuevoNombre = nuevoNombre + nuevaPizza.getIngredientes().get(i).getNombre() + " ";
					}
					nuevaPizza.setNombre(nuevoNombre);
					
					pizzasCSV.addPizza(nuevaOrden, nuevaPizza);
					AbrirPedidoDesdePizza();
				}
			}
		});
		
		this.ventanaPizza.getBtnQueso().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.getIngredientes().add(listaIngr.get(0));
			}
		});
		this.ventanaPizza.getBtnQuesoSinGluten().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.getIngredientes().add(listaIngr.get(1));
			}
		});
		this.ventanaPizza.getBtnTomate().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.getIngredientes().add(listaIngr.get(2));
			}
		});
		this.ventanaPizza.getBtnChampinones().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.getIngredientes().add(listaIngr.get(3));
			}
		});
		this.ventanaPizza.getBtnBacon().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.getIngredientes().add(listaIngr.get(4));
			}
		});
		this.ventanaPizza.getBtnAceitunas().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.getIngredientes().add(listaIngr.get(5));
			}
		});
		this.ventanaPizza.getBtnAnchoas().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.getIngredientes().add(listaIngr.get(6));
			}
		});
		this.ventanaPizza.getBtnPimiento().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.getIngredientes().add(listaIngr.get(7));
			}
		});
		this.ventanaPizza.getBtnYork().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.getIngredientes().add(listaIngr.get(8));
			}
		});
		this.ventanaPizza.getBtnSerrano().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.getIngredientes().add(listaIngr.get(9));
			}
		});
		this.ventanaPizza.getBtnCebolla().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.getIngredientes().add(listaIngr.get(10));
			}
		});
		this.ventanaPizza.getBtnCebollaCaram().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.getIngredientes().add(listaIngr.get(11));
			}
		});
		this.ventanaPizza.getBtnPollo().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.getIngredientes().add(listaIngr.get(12));
			}
		});
		this.ventanaPizza.getBtnPepperoni().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.getIngredientes().add(listaIngr.get(13));
			}
		});
		this.ventanaPizza.getBtnMaiz().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.getIngredientes().add(listaIngr.get(14));
			}
		});
		this.ventanaPizza.getBtnAtun().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.getIngredientes().add(listaIngr.get(15));
			}
		});
		this.ventanaPizza.getBtnPina().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.getIngredientes().add(listaIngr.get(16));
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
		nuevaOrden = new Orden();
	}
	private void AbrirPrincipalDesdePedido()
	{
		this.ventanaPrincipal.setVisible(true);
		this.ventanaPedido.setVisible(false);
	}
	private void AbrirPizzaDesdePedido()
	{
		this.ventanaPizza.setVisible(true);
		this.ventanaPedido.setVisible(false);
	}
	private void AbrirPedidoDesdePizza()
	{
		this.ventanaPedido.setVisible(true);
		this.ventanaPizza.setVisible(false);
	}
	public void concatenar(String original, String extra)
	{
		original = original + "" + extra; 
	}
	private void IniciarBasesIngredientes()
	{
		// --- Elementos del Main de consola, añadidos para probar que funciona el controlador
		Base baseAux = new Base("Pequena", true, 1.5, "Pequena");
		listaBases.add(baseAux);
		baseAux = new Base("Grande", true, 3.75, "Grande");
		listaBases.add(baseAux);
		baseAux = new Base("Pequena sin gluten", true, 2, "Pequena");
		listaBases.add(baseAux);
		baseAux = new Base("Grande sin gluten", true, 4.5, "Grande");
		listaBases.add(baseAux);
		
		Ingrediente ingrAux = new Ingrediente("Queso", true, 0.6);
		listaIngr.add(ingrAux);
		ingrAux = new Ingrediente("Queso sin gluten", false, 0.9);
		listaIngr.add(ingrAux);
		ingrAux = new Ingrediente("Tomate", false, 0.65);
		listaIngr.add(ingrAux);
		ingrAux = new Ingrediente("Champinones", true, 0.75);
		listaIngr.add(ingrAux);
		ingrAux = new Ingrediente("Bacon", true, 1.25);
		listaIngr.add(ingrAux);
		ingrAux = new Ingrediente("Aceitunas", true, 1.25);
		listaIngr.add(ingrAux);
		ingrAux = new Ingrediente("Anchoas", false, 0.5);
		listaIngr.add(ingrAux);
		ingrAux = new Ingrediente("Pimiento", false, 0.6);
		listaIngr.add(ingrAux);
		ingrAux = new Ingrediente("York", true, 0.75);
		listaIngr.add(ingrAux);
		ingrAux = new Ingrediente("Serrano", true, 1.2);
		listaIngr.add(ingrAux);
		ingrAux = new Ingrediente("Cebolla", false, 0.3);
		listaIngr.add(ingrAux);
		ingrAux = new Ingrediente("Cebolla caramelizada", false, 0.6);
		listaIngr.add(ingrAux);
		ingrAux = new Ingrediente("Pollo", false, 0.45);
		listaIngr.add(ingrAux);
		ingrAux = new Ingrediente("Pepperoni", true, 0.85);
		listaIngr.add(ingrAux);
		ingrAux = new Ingrediente("Maiz", true, 0.3);
		listaIngr.add(ingrAux);
		ingrAux = new Ingrediente("Atun", false, 0.65);
		listaIngr.add(ingrAux);
		ingrAux = new Ingrediente("Pina", false, 0.8);
		listaIngr.add(ingrAux);
	}
}