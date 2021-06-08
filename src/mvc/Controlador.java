package mvc;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JLabel;

import ficheros.EditarCSV;
import modelo.Base;
import modelo.Ingrediente;
import modelo.Orden;
import modelo.Pizza;

public class Controlador
{
	// Declarar los tipos de base e ingrediente que ofrecemos y sus propiedades
	Vector<Base> listaBases = new Vector<Base>();
	Vector<Ingrediente> listaIngr = new Vector<Ingrediente>();
	
	// La orden y la pizza que van a almacenar los datos hasta grabarlos en el CSV
	private Orden nuevaOrden;
	private Pizza nuevaPizza;
	
	// Instancias del editor que trabajarán sobre el CSV de pizzas y de pedidos
	private EditarCSV pizzasCSV = new EditarCSV("pizzas.csv");
	private EditarCSV pedidosCSV = new EditarCSV("pedidos.csv");
	
	// Ventanas	
	VentanaPrincipal ventanaPrincipal;
	VentanaPedido ventanaPedido;
	VentanaPizza ventanaPizza;
	VentanaHistorial ventanaHistorial;
	VentanaConfirmar ventanaConfirmar;
	
	// Constructor
	public Controlador(VentanaPrincipal ventanaPrincipal,
						VentanaPedido ventanaPedido,
						VentanaPizza ventanaPizza,
						VentanaHistorial ventanaHistorial,
						VentanaConfirmar ventanaConfirmar)
	{
		IniciarBasesIngredientes(); // Funcion con la que generamos a mano cada base e ingrediente
		
		// Guardar en esta clase las ventanas del main
		this.ventanaPrincipal = ventanaPrincipal;
		this.ventanaPedido = ventanaPedido;
		this.ventanaPizza = ventanaPizza;
		this.ventanaHistorial = ventanaHistorial;
		this.ventanaConfirmar = ventanaConfirmar;
		
		// Cada ventana tiene su propia funcion de listeners tener el codigo algo mas ordenado
		this.crearListenersVentanaPrincipal();
		this.crearListenersVentanaPedido();
		this.crearListenersVentanaPizza();
		this.crearListenersVentanaHistorial();
		this.crearListenersVentanaConfirmar();
		
		this.ventanaPrincipal.setVisible(true); // La que abrirá por defecto el programa
		
		// Funciones para recibir datos de los CSV y dejarlos listos para trabajar con ellos
		pizzasCSV.cargarCSV();
		pedidosCSV.cargarCSV();
		
		nuevaPizza = new Pizza();
		nuevaOrden = new Orden();
	}
	
	private void crearListenersVentanaPrincipal() //---- Listeners de VentanaPrincipal ----//
	{		
		this.ventanaPrincipal.getBtnNuevoPedido().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				AbrirPedidoDesdePrincipal();
				ventanaPedido.setLabelPrecioText("El coste actual de su pedido es: " + nuevaOrden.calcularCostePedido() + "€");
				ventanaPedido.setLabelID("Pedido número: " + nuevaOrden.getID());
			}
		});
		
		this.ventanaPrincipal.getBtnVerHistorial().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				AbrirHistorialDesdePrincipal();
			}
		});
		
		this.ventanaPrincipal.getBtnConfirmarPedido().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				resetearVentanaConfirmar();
				AbrirConfirmarDesdePrincipal();			
			}
		});
	}
	private void crearListenersVentanaPedido() //---- Listeners de Ventana Pedidos ----//
	{
		this.ventanaPedido.getBtnAniadirPizza().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				System.out.println(nuevaOrden.getID());
				resetearVentanaPizza();
				nuevaPizza = new Pizza();
				AbrirPizzaDesdePedido();
			}
		});
		
		this.ventanaPedido.getBtnEliminarPizza().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				resetearVentanaPedido();

				if(nuevaOrden.getPizzas().isEmpty() == true)
				{
					ventanaPedido.getBtnEliminarPizza().setText("No hay ninguna pizza que eliminar");
				}
				else
				{
					pizzasCSV.eliminarUltimaPizza(nuevaOrden);
					nuevaOrden.eliminarUltimaPizza();
					resetearVentanaPedido();				
				}
			}
		});
		
		this.ventanaPedido.getBtnVerPedido().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				resetearVentanaPedido();

				if(nuevaOrden.getPizzas().isEmpty() == false)
				{
					String[][] matrizAux = new String[nuevaOrden.getPizzas().size()][5];
					
					boolean paraCeliaco = true;
					for(int i = 0; i < nuevaOrden.getPizzas().size(); i++)
					{
						matrizAux[i][0] = nuevaOrden.getPizzas().get(i).getNombre();
						matrizAux[i][1] = nuevaOrden.getPizzas().get(i).calcularCostePizza() + " euros";
					}
					if(paraCeliaco == true)
					{
						concatenar(ventanaPedido.getLabelPrecioText(), ("Su pedido SI es apto para celiacos\n"));			
					}
					else
					{
						concatenar(ventanaPedido.getLabelPrecioText(), ("Su pedido NO es apto para celiacos\n"));
					}
					ventanaPedido.CrearTablaPizzas(matrizAux);
				}
				else
				{
					ventanaPedido.getBtnVerPedido().setText("Ninguna pizza encargada");
				}
			}
		});
		
		this.ventanaPedido.getBtnEnviarPedido().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				resetearVentanaPedido();

				if(nuevaOrden.getPizzas().isEmpty() == false)
				{
					AbrirPrincipalDesdePedido();
					ventanaPrincipal.getLabelPedidoEnviado().setVisible(true);
				}
				else
				{
					ventanaPedido.getBtnEnviarPedido().setText("No emitimos pedidos vacíos");
				}
			}
		});
		
		this.ventanaPedido.getBtnCancelarPedido().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				pizzasCSV.cancelarOrden(nuevaOrden);
				
				AbrirPrincipalDesdePedido();
			}
		});
	}
	private void crearListenersVentanaPizza() //---- Listeners de Ventana Historial ----//
	{	
		this.ventanaPizza.getBtnEnviarPizza().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if(nuevaPizza.getBase() == null)
				{
					ventanaPizza.getBtnEnviarPizza().setText("No puede enviar una pizza sin elegir tamaño");
				}
				else if(nuevaPizza.getIngredientes().size() == 0)
				{
					ventanaPizza.getBtnEnviarPizza().setText("No puede enviar una pizza sin ingredientes");
				}
				else
				{
					String nuevoNombre = "Pizza ";
					nuevoNombre = nuevoNombre + nuevaPizza.getBase().getTamanio() + " con ";
					for(int i = 0; i < nuevaPizza.getIngredientes().size(); i++)
					{
						nuevoNombre = nuevoNombre + nuevaPizza.getIngredientes().get(i).getNombre() + " ";
					}
					nuevaPizza.setNombre(nuevoNombre);
					
					pizzasCSV.addPizza(nuevaOrden, nuevaPizza);
					nuevaOrden.aniadirPizza(nuevaPizza);
					ventanaPedido.setLabelPrecioText("El coste actual de su pedido es: " + nuevaOrden.calcularCostePedido() + "€");
					AbrirPedidoDesdePizza();
				}
			}
		});
		
		this.ventanaPizza.getBtnQueso().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.getIngredientes().add(listaIngr.get(0));
				ventanaPizza.hacerIngredienteAzul(ventanaPizza.getBtnQueso());
			}
		});
		this.ventanaPizza.getBtnQuesoSinGluten().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.getIngredientes().add(listaIngr.get(1));
				ventanaPizza.hacerIngredienteAzul(ventanaPizza.getBtnQuesoSinGluten());
			}
		});
		this.ventanaPizza.getBtnTomate().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.getIngredientes().add(listaIngr.get(2));
				ventanaPizza.hacerIngredienteAzul(ventanaPizza.getBtnTomate());
			}
		});
		this.ventanaPizza.getBtnChampinones().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.getIngredientes().add(listaIngr.get(3));
				ventanaPizza.hacerIngredienteAzul(ventanaPizza.getBtnChampinones());
			}
		});
		this.ventanaPizza.getBtnBacon().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.getIngredientes().add(listaIngr.get(4));
				ventanaPizza.hacerIngredienteAzul(ventanaPizza.getBtnBacon());
			}
		});
		this.ventanaPizza.getBtnAceitunas().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.getIngredientes().add(listaIngr.get(5));
				ventanaPizza.hacerIngredienteAzul(ventanaPizza.getBtnAceitunas());
			}
		});
		this.ventanaPizza.getBtnAnchoas().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.getIngredientes().add(listaIngr.get(6));
				ventanaPizza.hacerIngredienteAzul(ventanaPizza.getBtnAnchoas());
			}
		});
		this.ventanaPizza.getBtnPimiento().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.getIngredientes().add(listaIngr.get(7));
				ventanaPizza.hacerIngredienteAzul(ventanaPizza.getBtnPimiento());
			}
		});
		this.ventanaPizza.getBtnYork().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.getIngredientes().add(listaIngr.get(8));
				ventanaPizza.hacerIngredienteAzul(ventanaPizza.getBtnYork());
			}
		});
		this.ventanaPizza.getBtnSerrano().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.getIngredientes().add(listaIngr.get(9));
				ventanaPizza.hacerIngredienteAzul(ventanaPizza.getBtnSerrano());
			}
		});
		this.ventanaPizza.getBtnCebolla().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.getIngredientes().add(listaIngr.get(10));
				ventanaPizza.hacerIngredienteAzul(ventanaPizza.getBtnCebolla());
			}
		});
		this.ventanaPizza.getBtnCebollaCaram().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.getIngredientes().add(listaIngr.get(11));
				ventanaPizza.hacerIngredienteAzul(ventanaPizza.getBtnCebollaCaram());
			}
		});
		this.ventanaPizza.getBtnPollo().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.getIngredientes().add(listaIngr.get(12));
				ventanaPizza.hacerIngredienteAzul(ventanaPizza.getBtnPollo());
			}
		});
		this.ventanaPizza.getBtnPepperoni().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.getIngredientes().add(listaIngr.get(13));
				ventanaPizza.hacerIngredienteAzul(ventanaPizza.getBtnPepperoni());
			}
		});
		this.ventanaPizza.getBtnMaiz().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.getIngredientes().add(listaIngr.get(14));
				ventanaPizza.hacerIngredienteAzul(ventanaPizza.getBtnMaiz());
			}
		});
		this.ventanaPizza.getBtnAtun().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.getIngredientes().add(listaIngr.get(15));
				ventanaPizza.hacerIngredienteAzul(ventanaPizza.getBtnAtun());
			}
		});
		this.ventanaPizza.getBtnPina().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.getIngredientes().add(listaIngr.get(16));
				ventanaPizza.hacerIngredienteAzul(ventanaPizza.getBtnPina());
			}
		});
		this.ventanaPizza.getBtnMasaPequena().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.ponerBase(listaBases.get(0));
				ventanaPizza.hacerBaseAzul(ventanaPizza.getBtnMasaPequena());
			}
		});
		this.ventanaPizza.getBtnMasaGrande().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.ponerBase(listaBases.get(1));
				ventanaPizza.hacerBaseAzul(ventanaPizza.getBtnMasaGrande());
			}
		});
		this.ventanaPizza.getBtnMasaPequenaSG().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.ponerBase(listaBases.get(2));
				ventanaPizza.hacerBaseAzul(ventanaPizza.getBtnMasaPequenaSG());
			}
		});
		this.ventanaPizza.getBtnMasaGrandeSG().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.ponerBase(listaBases.get(3));
				ventanaPizza.hacerBaseAzul(ventanaPizza.getBtnMasaGrandeSG());
			}
		});
	}
	private void crearListenersVentanaHistorial() //---- Listeners Ventana Historial ----//
	{		
		this.ventanaHistorial.getBtnVolver().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				AbrirPrincipalDesdeHistorial();
			}
		});
	}
	private void crearListenersVentanaConfirmar() //---- Listeners Ventana Confirmar ----//
	{		
		this.ventanaConfirmar.getBtnVolver().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				AbrirPrincipalDesdeConfirmar();
			}
		});
		this.ventanaConfirmar.getBtnConfirmar().addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				try
				{
					int idConfirmar = Integer.parseInt(ventanaConfirmar.getScannerText()) - 1;
					if(idConfirmar > 0 && idConfirmar < pedidosCSV.contarLineasFichero())
					{
						pedidosCSV.modificarDato(idConfirmar, 3, "1");						
					}
				}
				catch(Exception e)
				{
					ventanaConfirmar.setInstruccionesText("Introduzca un número válido:");
				}
				resetearVentanaConfirmar();
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
		resetearVentanaPedido();
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
		resetearVentanaPedido();
	}
	private void AbrirConfirmarDesdePrincipal()
	{
		this.ventanaConfirmar.setVisible(true);
		this.ventanaPrincipal.setVisible(false);
		nuevaOrden = new Orden();
	}
	private void AbrirPrincipalDesdeConfirmar()
	{
		this.ventanaPrincipal.setVisible(true);
		this.ventanaConfirmar.setVisible(false);
		nuevaOrden = new Orden();
	}
	
	public void resetearVentanaPrincipal()
	{
		
	}
	public void resetearVentanaPedido()
	{
		if(nuevaOrden.getPizzas().size() != 0)
		{
			boolean aptaCeliacos = true;
			for(int i = 0; i < nuevaOrden.getPizzas().size(); i++)
			{
				if(nuevaOrden.getPizzas().get(i).aptaCeliacos() == false)
				{
					aptaCeliacos = false;
				}
			}
			if(aptaCeliacos == true)
			{
				ventanaPedido.setLabelCeliaco("Su pedido SÍ es apto para celiacos");
			}
			else
			{
				ventanaPedido.setLabelCeliaco("Su pedido NO es apto para celiacos");
			}
		}
		ventanaPedido.setLabelPrecioText("El coste actual de su pedido es: " + nuevaOrden.calcularCostePedido() + "€");
		ventanaPedido.getBtnEliminarPizza().setText("Eliminar última pizza");
		ventanaPedido.getBtnVerPedido().setText("Ver pedido");
		ventanaPedido.getBtnEnviarPedido().setText("EnviarPedido");
		
	}
	public void resetearVentanaPizza()
	{
		ventanaPizza.getBtnMasaPequena().setBackground(null);
		ventanaPizza.getBtnMasaGrande().setBackground(null);
		ventanaPizza.getBtnMasaPequenaSG().setBackground(null);
		ventanaPizza.getBtnMasaGrandeSG().setBackground(null);
		ventanaPizza.getBtnQueso().setBackground(null);
		ventanaPizza.getBtnQuesoSinGluten().setBackground(null);
		ventanaPizza.getBtnTomate().setBackground(null);
		ventanaPizza.getBtnChampinones().setBackground(null);
		ventanaPizza.getBtnBacon().setBackground(null);
		ventanaPizza.getBtnAceitunas().setBackground(null);
		ventanaPizza.getBtnAnchoas().setBackground(null);
		ventanaPizza.getBtnPimiento().setBackground(null);
		ventanaPizza.getBtnYork().setBackground(null);
		ventanaPizza.getBtnSerrano().setBackground(null);
		ventanaPizza.getBtnCebolla().setBackground(null);
		ventanaPizza.getBtnCebollaCaram().setBackground(null);
		ventanaPizza.getBtnPollo().setBackground(null);
		ventanaPizza.getBtnPepperoni().setBackground(null);
		ventanaPizza.getBtnMaiz().setBackground(null);
		ventanaPizza.getBtnAtun().setBackground(null);
		ventanaPizza.getBtnPina().setBackground(null);
		ventanaPizza.getBtnEnviarPizza().setText("Enviar pizza");
	}
	public void resetearVentanaHistorial()
	{

	}
	public void resetearVentanaConfirmar()
	{
		Vector<String> pedidos = pedidosCSV.pedidosSinConfirmar();
		if(pedidos.isEmpty() == false)
		{
			int posXPedido = 12;
			int posXPizza = 20;
			int posYactual = 130;
			int separacion = 30;
			String nuevoTexto;
			
			for(int i = 0; i < pedidos.size(); i++)
			{
				nuevoTexto = "ID del pedido: " + pedidos.get(i) + ". Encargado el " + "";
				ventanaConfirmar.crearLabel(posXPedido, posYactual, 408, 25, nuevoTexto);
				posYactual += 30;
			}
		}
		ventanaConfirmar.setInstruccionesText("Introduzca el ID del pedido que desea confirmar");
	}
	
	public void concatenar(String original, String extra)
	{
		original = original + "" + extra; 
	}
	private void IniciarBasesIngredientes() // Funcion con la que generamos a mano cada base e ingrediente
	{
		Base baseAux = new Base("Pequena", true, 1.5, "pequena");
		listaBases.add(baseAux);
		baseAux = new Base("Grande", true, 3.75, "grande");
		listaBases.add(baseAux);
		baseAux = new Base("Pequena sin gluten", false, 2, "pequena");
		listaBases.add(baseAux);
		baseAux = new Base("Grande sin gluten", false, 4.5, "grande");
		listaBases.add(baseAux);
		
		Ingrediente ingrAux = new Ingrediente("queso", true, 0.6);
		listaIngr.add(ingrAux);
		ingrAux = new Ingrediente("queso sin gluten", false, 0.9);
		listaIngr.add(ingrAux);
		ingrAux = new Ingrediente("tomate", false, 0.65);
		listaIngr.add(ingrAux);
		ingrAux = new Ingrediente("champinones", true, 0.75);
		listaIngr.add(ingrAux);
		ingrAux = new Ingrediente("bacon", true, 1.25);
		listaIngr.add(ingrAux);
		ingrAux = new Ingrediente("aceitunas", true, 0.55);
		listaIngr.add(ingrAux);
		ingrAux = new Ingrediente("anchoas", false, 0.5);
		listaIngr.add(ingrAux);
		ingrAux = new Ingrediente("pimiento", false, 0.6);
		listaIngr.add(ingrAux);
		ingrAux = new Ingrediente("york", true, 0.75);
		listaIngr.add(ingrAux);
		ingrAux = new Ingrediente("serrano", true, 1.2);
		listaIngr.add(ingrAux);
		ingrAux = new Ingrediente("cebolla", false, 0.3);
		listaIngr.add(ingrAux);
		ingrAux = new Ingrediente("cebolla caramelizada", false, 0.6);
		listaIngr.add(ingrAux);
		ingrAux = new Ingrediente("pollo", false, 0.45);
		listaIngr.add(ingrAux);
		ingrAux = new Ingrediente("pepperoni", true, 0.85);
		listaIngr.add(ingrAux);
		ingrAux = new Ingrediente("maiz", true, 0.3);
		listaIngr.add(ingrAux);
		ingrAux = new Ingrediente("atun", false, 0.65);
		listaIngr.add(ingrAux);
		ingrAux = new Ingrediente("pina", false, 0.8);
		listaIngr.add(ingrAux);
	}
}