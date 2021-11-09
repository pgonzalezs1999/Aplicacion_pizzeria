/**
 * Paquete de prueba
 * @author Pablo Gonzalez Sanchez <a href=mailto:pgonzalezs1999@gmail.com>pgonzalezs1999@gmail.com</a>
 */
package mvc;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JLabel;
import ficheros.EditarCSV;
import ficheros.EditarCSVpedidos;
import modelo.Base;
import modelo.Ingrediente;
import modelo.Orden;
import modelo.Pizza;
/**
 * Gestiona las variables no referentes a la vista
 * @author Pablo Gonzalez Sanchez
 */
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
	private EditarCSVpedidos newPedidosCSV = new EditarCSVpedidos("pedidos.csv");
	
	// Ventanas	
	VentanaPrincipal ventanaPrincipal;
	VentanaPedido ventanaPedido;
	VentanaPizza ventanaPizza;
	VentanaHistorial ventanaHistorial;
	VentanaConfirmar ventanaConfirmar;
	
	// Constructor
	/**
	 * Inicia el constructor. Crea los tipos de bases e ingredientes y almacena las ventanas y los editores de CSV
	 * @param ventanaPrincipal Primera ventana que abre el programa, recibida del main
	 * @param ventanaPedido Ventana que gestiona toda la informacion sobre un pedido concreto, recibida del main
	 * @param ventanaPizza Ventana que gestiona toda la informacion sobre una pizza concreta, recibida del main
	 * @param ventanaHistorial Ventana que muestra un listado de todos los pedidos emitidos, recibida del main
	 * @param ventanaConfirmar Ventana que permite marcar los pedidos no entregados como entregados, recibida del main
	 */
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
		newPedidosCSV.cargarCSV();
		
		// Iniciar ambos constructores
		nuevaPizza = new Pizza();
		nuevaOrden = new Orden();
	}
	/**
	 * Recopila todas los listeners de la ventana principal
	 */
	private void crearListenersVentanaPrincipal() //---- Listeners de VentanaPrincipal ----//
	{		
		this.ventanaPrincipal.getBtnNuevoPedido().addActionListener(new ActionListener() // Cuando se pulse el boton nuevo pedido de la ventana principal
		{
			public void actionPerformed(ActionEvent arg0)
			{
				AbrirPedidoDesdePrincipal(); // Cambia a la pantalla indicada
				
				if(ventanaPedido.getLabeles().isEmpty() == false) // Si ya se han creado textos auxiliares antes
				{
					for(int i = 0; i < ventanaPedido.getLabeles().size(); i++) // Recorre el vector que los almacena
					{
						ventanaPedido.getLabeles().get(i).setText(""); // Les quita el texto que contenian para que no se vean
					}
					ventanaPedido.getLabeles().clear(); // Elimina todos los labels auxiliares
				}			
				ventanaPedido.setLabelPrecioText("El coste actual de su pedido es: " + nuevaOrden.calcularCostePedido() + "€"); // Actualiza el texto que muestra el precio del pedido
				ventanaPedido.setLabelID("Pedido número: " + nuevaOrden.getID()); // Actualiza el texto que muestra el ID del pedido
			}
		});
		this.ventanaPrincipal.getBtnVerHistorial().addActionListener(new ActionListener() // Cuando se pulse el boton historial de la ventana principal
		{
			public void actionPerformed(ActionEvent arg0)
			{
				AbrirHistorialDesdePrincipal(); // Cambia a la pantalla indicada
			}
		});
		this.ventanaPrincipal.getBtnConfirmarPedido().addActionListener(new ActionListener() // Cuando se pulse el boton confirmar pedido de la ventana principal
		{
			public void actionPerformed(ActionEvent arg0)
			{
				resetearVentanaConfirmar(); // Recupera los valores por defecto de la ventana
				AbrirConfirmarDesdePrincipal();  // Cambia a la pantalla indicada
			}
		});
	}
	/**
	 * Recopila todas los listeners de la ventana para generar un nuevo pedido
	 */
	private void crearListenersVentanaPedido() //---- Listeners de Ventana Pedidos ----//
	{
		this.ventanaPedido.getBtnAniadirPizza().addActionListener(new ActionListener() // Cuando se pulse el boton anadir pizza de la ventana pedido
		{
			public void actionPerformed(ActionEvent arg0)
			{				
				nuevaPizza = new Pizza();
				resetearVentanaPizza(); // Recupera los valores por defecto de la ventana
				AbrirPizzaDesdePedido(); // Cambia a la pantalla indicada
			}
		});
		this.ventanaPedido.getBtnEliminarPizza().addActionListener(new ActionListener() // Cuando se pulse el boton eliminar pizza de la ventana pedido
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if(nuevaOrden.getPizzas().isEmpty() == true) // Si el pedido aun no contiene ninguna pizza
				{					
					ventanaPedido.getBtnEliminarPizza().setText("No hay ninguna pizza que eliminar"); // Explicar al usuario que no se puede explicar una pizza que no existe DUH
				}
				else
				{
					pizzasCSV.eliminarUltimaPizza(nuevaOrden); // Comprueba que la pizza cancelada se elimine del fichero CSV
					nuevaOrden.eliminarUltimaPizza(); // Elimina la ultima pizza del vector de pizzas del pedido en curso
					ventanaPedido.getLabeles().lastElement().setText(""); // Oculta el texto de la pizza eliminada
				}
			}
		});
		this.ventanaPedido.getBtnVerPedido().addActionListener(new ActionListener() // Cuando se pulse el boton ver pedido de la ventana pedido
		{
			public void actionPerformed(ActionEvent arg0)
			{
				resetearVentanaPedido(); // Recupera los valores por defecto de la ventana
				
				if(nuevaOrden.getPizzas().isEmpty() == false) // Si el pedido tiene al menos una pizza
				{
					int posY = 300; // Posicion Y en la que se colocara el primer texto
					for(int i = 0; i < nuevaOrden.getPizzas().size(); i++) // Recorre el vector de pizzas del pedido en curso
					{
						posY += 25; // Por cada texto, baja la posicion Y para hacer el salto de linea
						ventanaPedido.crearLabel(12, posY, 408, 25, "- " + nuevaOrden.getPizzas().get(i).getNombre()); // Crea el label en la posicion fijada con el texto
					}
				}
				else // Si el pedido aun no contiene ninguna pizza
				{
					ventanaPedido.getBtnVerPedido().setText("Ninguna pizza encargada"); // Solo muestra un label de error
				}
				ventanaPedido.repaint(); // Refresca la ventana
			}
		});
		this.ventanaPedido.getBtnEnviarPedido().addActionListener(new ActionListener() // Cuando se pulse el boton enviar pedido de la ventana pedido
		{
			public void actionPerformed(ActionEvent arg0)
			{
				resetearVentanaPedido(); // Recupera los valores por defecto de la ventana

				if(nuevaOrden.getPizzas().isEmpty() == false) // Si el pedido tiene al menos una pizza 
				{
					AbrirPrincipalDesdePedido(); // Cambia a la pantalla indicada
					ventanaPrincipal.getLabelPedidoEnviado().setVisible(true); // Muestra un mensaje para confirmarle al usuario que se ha emitido el pedido
					newPedidosCSV.addPedido(); // Se añade el pedido al historial
					
				}
				else // Si el pedido aun no contiene ninguna pizza
				{
					ventanaPedido.getBtnEnviarPedido().setText("No emitimos pedidos vacíos"); // Solo muestra un label de error
				}
				resetearVentanaHistorial();
				resetearVentanaConfirmar();
			}
		});	
		this.ventanaPedido.getBtnCancelarPedido().addActionListener(new ActionListener() // Cuando se pulse el boton cancelar pedido de la ventana pedido
		{
			public void actionPerformed(ActionEvent arg0)
			{
				resetearVentanaPedido(); // Recupera los valores por defecto de la ventana
				
				pizzasCSV.cancelarOrden(nuevaOrden); // Se eliminan las pizzas del fichero CSV y se vacia la orden en curso
				
				AbrirPrincipalDesdePedido(); // Cambia a la pantalla indicada
			}
		});
	}
	/**
	 * Recopila todas los listeners de la ventana para generar una nueva pizza
	 */
	private void crearListenersVentanaPizza() //---- Listeners de Ventana Historial ----//
	{	
		this.ventanaPizza.getBtnEnviarPizza().addActionListener(new ActionListener() // Cuando se pulse el boton enviar pizza de la ventana pizza
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if(ventanaPedido.getLabeles().isEmpty() == false) // Si hay al menos un label que eliminar
				{
					for(int i = 0; i < ventanaPedido.getLabeles().size(); i++) // Recorre el vector de labels
					{
						ventanaPedido.getLabeles().get(i).setText(""); // Elimina el texto de cada uno para que no se vean
					}
					ventanaPedido.getLabeles().clear(); // Los elimina del vector
				}
				
				if(nuevaPizza.getBase() == null) // Si aun no se ha elegido la base
				{
					ventanaPizza.getBtnEnviarPizza().setText("No puede enviar una pizza sin elegir tamaño"); // Se muestra un mensaje de error y no deja emitir la pizza
				}
				else if(nuevaPizza.getIngredientes().size() == 0) // Si aun no se han elegido ingredientes
				{
					ventanaPizza.getBtnEnviarPizza().setText("No puede enviar una pizza sin ingredientes"); // Se muestra un mensaje de error y no deja emitir la pizza
				}
				else // Si esta todo correcto
				{
					String nuevoNombre = "Pizza "; // La parte comun del nombre de cualquier pizza
					nuevoNombre = nuevoNombre + nuevaPizza.getBase().getTamanio() + " con "; // Se le suma la base al nombre
					for(int i = 0; i < nuevaPizza.getIngredientes().size(); i++) // Recorre los ingredientes de la pizza
					{
						nuevoNombre = nuevoNombre + nuevaPizza.getIngredientes().get(i).getNombre() + " "; // Se le suman los ingredientes al nombre
					}
					nuevaPizza.setNombre(nuevoNombre); // Se le asigna el nombre a la pizza
					
					pizzasCSV.addPizza(nuevaOrden, nuevaPizza); // Añadir la pizza al CSV
					nuevaOrden.aniadirPizza(nuevaPizza); // Añade la pizza a la orden en curso
					ventanaPedido.setLabelPrecioText("El coste actual de su pedido es: " + nuevaOrden.calcularCostePedido() + "€"); // Actualiza el texto del precio actual del pedido en curso
					
					AbrirPedidoDesdePizza(); // Cambia a la pantalla indicada
				}
			}
		});
		this.ventanaPizza.getBtnQueso().addActionListener(new ActionListener() // Cuando se pulse el boton queso de la ventana pizza
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.getIngredientes().add(listaIngr.get(0)); // Recoge el objeto ingrediente correspondiente a la posicion del vector local de ingredientes
				ventanaPizza.hacerIngredienteAzul(ventanaPizza.getBtnQueso()); // Hace el boton azul para dar feedback al usuario
			}
		});
		this.ventanaPizza.getBtnQuesoSinGluten().addActionListener(new ActionListener() // Cuando se pulse el boton queso sin gluten de la ventana pizza
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.getIngredientes().add(listaIngr.get(1)); // Recoge el objeto ingrediente correspondiente a la posicion del vector local de ingredientes
				ventanaPizza.hacerIngredienteAzul(ventanaPizza.getBtnQuesoSinGluten()); // Hace el boton azul para dar feedback al usuario
			}
		});
		this.ventanaPizza.getBtnTomate().addActionListener(new ActionListener() // Cuando se pulse el boton tomate de la ventana pizza
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.getIngredientes().add(listaIngr.get(2)); // Recoge el objeto ingrediente correspondiente a la posicion del vector local de ingredientes
				ventanaPizza.hacerIngredienteAzul(ventanaPizza.getBtnTomate()); // Hace el boton azul para dar feedback al usuario
			}
		});
		this.ventanaPizza.getBtnChampinones().addActionListener(new ActionListener() // Cuando se pulse el boton champiñones de la ventana pizza
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.getIngredientes().add(listaIngr.get(3)); // Recoge el objeto ingrediente correspondiente a la posicion del vector local de ingredientes
				ventanaPizza.hacerIngredienteAzul(ventanaPizza.getBtnChampinones()); // Hace el boton azul para dar feedback al usuario
			}
		});
		this.ventanaPizza.getBtnBacon().addActionListener(new ActionListener() // Cuando se pulse el boton bacon de la ventana pizza
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.getIngredientes().add(listaIngr.get(4)); // Recoge el objeto ingrediente correspondiente a la posicion del vector local de ingredientes
				ventanaPizza.hacerIngredienteAzul(ventanaPizza.getBtnBacon()); // Hace el boton azul para dar feedback al usuario
			}
		});
		this.ventanaPizza.getBtnAceitunas().addActionListener(new ActionListener() // Cuando se pulse el boton aceitunas de la ventana pizza
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.getIngredientes().add(listaIngr.get(5)); // Recoge el objeto ingrediente correspondiente a la posicion del vector local de ingredientes
				ventanaPizza.hacerIngredienteAzul(ventanaPizza.getBtnAceitunas()); // Hace el boton azul para dar feedback al usuario
			}
		});
		this.ventanaPizza.getBtnAnchoas().addActionListener(new ActionListener() // Cuando se pulse el boton anchoas de la ventana pizza
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.getIngredientes().add(listaIngr.get(6)); // Recoge el objeto ingrediente correspondiente a la posicion del vector local de ingredientes
				ventanaPizza.hacerIngredienteAzul(ventanaPizza.getBtnAnchoas()); // Hace el boton azul para dar feedback al usuario
			}
		});
		this.ventanaPizza.getBtnPimiento().addActionListener(new ActionListener() // Cuando se pulse el boton pimiento de la ventana pizza
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.getIngredientes().add(listaIngr.get(7)); // Recoge el objeto ingrediente correspondiente a la posicion del vector local de ingredientes
				ventanaPizza.hacerIngredienteAzul(ventanaPizza.getBtnPimiento()); // Hace el boton azul para dar feedback al usuario
			}
		});
		this.ventanaPizza.getBtnYork().addActionListener(new ActionListener() // Cuando se pulse el boton york de la ventana pizza
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.getIngredientes().add(listaIngr.get(8)); // Recoge el objeto ingrediente correspondiente a la posicion del vector local de ingredientes
				ventanaPizza.hacerIngredienteAzul(ventanaPizza.getBtnYork()); // Hace el boton azul para dar feedback al usuario
			}
		});
		this.ventanaPizza.getBtnSerrano().addActionListener(new ActionListener() // Cuando se pulse el boton serrano de la ventana pizza
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.getIngredientes().add(listaIngr.get(9)); // Recoge el objeto ingrediente correspondiente a la posicion del vector local de ingredientes
				ventanaPizza.hacerIngredienteAzul(ventanaPizza.getBtnSerrano()); // Hace el boton azul para dar feedback al usuario
			}
		});
		this.ventanaPizza.getBtnCebolla().addActionListener(new ActionListener() // Cuando se pulse el boton cebolla de la ventana pizza
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.getIngredientes().add(listaIngr.get(10)); // Recoge el objeto ingrediente correspondiente a la posicion del vector local de ingredientes
				ventanaPizza.hacerIngredienteAzul(ventanaPizza.getBtnCebolla()); // Hace el boton azul para dar feedback al usuario
			}
		});
		this.ventanaPizza.getBtnCebollaCaram().addActionListener(new ActionListener() // Cuando se pulse el boton cebolla caramelizada de la ventana pizza
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.getIngredientes().add(listaIngr.get(11)); // Recoge el objeto ingrediente correspondiente a la posicion del vector local de ingredientes
				ventanaPizza.hacerIngredienteAzul(ventanaPizza.getBtnCebollaCaram()); // Hace el boton azul para dar feedback al usuario
			}
		});
		this.ventanaPizza.getBtnPollo().addActionListener(new ActionListener() // Cuando se pulse el boton pollo de la ventana pizza
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.getIngredientes().add(listaIngr.get(12)); // Recoge el objeto ingrediente correspondiente a la posicion del vector local de ingredientes
				ventanaPizza.hacerIngredienteAzul(ventanaPizza.getBtnPollo()); // Hace el boton azul para dar feedback al usuario
			}
		});
		this.ventanaPizza.getBtnPepperoni().addActionListener(new ActionListener() // Cuando se pulse el boton pepperoni de la ventana pizza
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.getIngredientes().add(listaIngr.get(13)); // Recoge el objeto ingrediente correspondiente a la posicion del vector local de ingredientes
				ventanaPizza.hacerIngredienteAzul(ventanaPizza.getBtnPepperoni()); // Hace el boton azul para dar feedback al usuario
			}
		});
		this.ventanaPizza.getBtnMaiz().addActionListener(new ActionListener() // Cuando se pulse el boton maiz de la ventana pizza
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.getIngredientes().add(listaIngr.get(14)); // Recoge el objeto ingrediente correspondiente a la posicion del vector local de ingredientes
				ventanaPizza.hacerIngredienteAzul(ventanaPizza.getBtnMaiz()); // Hace el boton azul para dar feedback al usuario
			}
		});
		this.ventanaPizza.getBtnAtun().addActionListener(new ActionListener() // Cuando se pulse el boton atun de la ventana pizza
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.getIngredientes().add(listaIngr.get(15)); // Recoge el objeto ingrediente correspondiente a la posicion del vector local de ingredientes
				ventanaPizza.hacerIngredienteAzul(ventanaPizza.getBtnAtun()); // Hace el boton azul para dar feedback al usuario
			}
		});
		this.ventanaPizza.getBtnPina().addActionListener(new ActionListener() // Cuando se pulse el boton piña de la ventana pizza
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.getIngredientes().add(listaIngr.get(16)); // Recoge el objeto ingrediente correspondiente a la posicion del vector local de ingredientes
				ventanaPizza.hacerIngredienteAzul(ventanaPizza.getBtnPina()); // Hace el boton azul para dar feedback al usuario
			}
		});
		this.ventanaPizza.getBtnMasaPequena().addActionListener(new ActionListener() // Cuando se pulse el boton masa pequeña de la ventana pizza
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.ponerBase(listaBases.get(0)); // Recoge el objeto base correspondiente a la posicion del vector local de bases
				ventanaPizza.hacerBaseAzul(ventanaPizza.getBtnMasaPequena()); // Hace el boton azul para dar feedback al usuario
			}
		});
		this.ventanaPizza.getBtnMasaGrande().addActionListener(new ActionListener() // Cuando se pulse el boton masa grande de la ventana pizza
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.ponerBase(listaBases.get(1)); // Recoge el objeto base correspondiente a la posicion del vector local de bases
				ventanaPizza.hacerBaseAzul(ventanaPizza.getBtnMasaGrande()); // Hace el boton azul para dar feedback al usuario
			}
		});
		this.ventanaPizza.getBtnMasaPequenaSG().addActionListener(new ActionListener() // Cuando se pulse el boton masa pequeña sin gluten de la ventana pizza
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.ponerBase(listaBases.get(2)); // Recoge el objeto base correspondiente a la posicion del vector local de bases
				ventanaPizza.hacerBaseAzul(ventanaPizza.getBtnMasaPequenaSG()); // Hace el boton azul para dar feedback al usuario
			}
		});
		this.ventanaPizza.getBtnMasaGrandeSG().addActionListener(new ActionListener() // Cuando se pulse el boton masa grande sin gluten de la ventana pizza
		{
			public void actionPerformed(ActionEvent arg0)
			{
				nuevaPizza.ponerBase(listaBases.get(3)); // Recoge el objeto base correspondiente a la posicion del vector local de bases
				ventanaPizza.hacerBaseAzul(ventanaPizza.getBtnMasaGrandeSG()); // Hace el boton azul para dar feedback al usuario
			}
		});
	}
	/**
	 * Recopila todas los listeners de la ventana para consultar el historial de pedidos
	 */
	private void crearListenersVentanaHistorial() //---- Listeners Ventana Historial ----//
	{		
		this.ventanaHistorial.getBtnVolver().addActionListener(new ActionListener() // Cuando se pulse el boton volver de la ventana historial
		{
			public void actionPerformed(ActionEvent arg0)
			{
				pedidosCSV.cargarCSV(); // Recupera el CSV por si se han editado los datos en otra ventana
				AbrirPrincipalDesdeHistorial(); // Cambia a la pantalla indicada
			}
		});
	}
	/**
	 * Recopila todas los listeners de la ventana para confirmar la entrega de un pedido
	 */
	private void crearListenersVentanaConfirmar() //---- Listeners Ventana Confirmar ----//
	{		
		this.ventanaConfirmar.getBtnVolver().addActionListener(new ActionListener() // Cuando se pulse el boton volver de la ventana confirmar
		{
			public void actionPerformed(ActionEvent arg0)
			{
				pedidosCSV.cargarCSV(); // Recupera el CSV por si se han editado los datos en otra ventana
				AbrirPrincipalDesdeConfirmar(); // Cambia a la pantalla indicada
			}
		});
		this.ventanaConfirmar.getBtnConfirmar().addActionListener(new ActionListener() // Cuando se pulse el boton confirmar de la ventana confirmar
		{
			public void actionPerformed(ActionEvent arg0)
			{
				try
				{
					for(int i = 0; i < pedidosCSV.contarLineasFichero(); i++) // Recorre el fichero CSV de pedidos
					{
						pedidosCSV.confirmar(Integer.parseInt(ventanaConfirmar.getScannerText())); // Marca como confirmado el pedido que coincide con el ID indicado por el usuario
					}
				}				
				catch(Exception e)
				{
					ventanaConfirmar.setInstruccionesText("Introduzca un número válido:"); // Si introduce un valor incorrecto
				}
				ventanaConfirmar.setScannerText(""); // Vacia el ultimo valor introducido en el lector, para que se vea una interfaz mas limpia
				resetearVentanaConfirmar(); // Recupera los valores por defecto de la ventana
			}
		});
	}
	/**
	 * Instrucciones para pasar de la ventana principal a la ventana historial
	 */
	private void AbrirHistorialDesdePrincipal()
	{
		this.ventanaHistorial.setVisible(true); // Muestra la pantalla
		resetearVentanaHistorial(); // Recupera los valores por defecto de la ventana
		this.ventanaPrincipal.setVisible(false); // Oculta la pantalla
	}
	/**
	 * Instrucciones para pasar de la ventana principal a la ventana de historial
	 */
	private void AbrirPrincipalDesdeHistorial()
	{
		this.ventanaPrincipal.setVisible(true); // Muestra la pantalla
		this.ventanaHistorial.setVisible(false); // Oculta la pantalla
	}
	/**
	 * Instrucciones para pasar de la ventana principal a la ventana de pedidos
	 */
	private void AbrirPedidoDesdePrincipal()
	{
		this.ventanaPedido.setVisible(true); // Muestra la pantalla
		this.ventanaPrincipal.setVisible(false); // Oculta la pantalla
		resetearVentanaPedido(); // Recupera los valores por defecto de la ventana
		nuevaOrden = new Orden();
	}
	/**
	 * Instrucciones para pasar de la ventana de pedidos a la ventana principal
	 */
	private void AbrirPrincipalDesdePedido()
	{
		this.ventanaPrincipal.setVisible(true); // Muestra la pantalla
		this.ventanaPedido.setVisible(false); // Oculta la pantalla
	}
	/**
	 * Instrucciones para pasar de la ventana de pedidos a la ventana de pizzas
	 */
	private void AbrirPizzaDesdePedido()
	{
		this.ventanaPizza.setVisible(true); // Muestra la pantalla
		this.ventanaPedido.setVisible(false); // Oculta la pantalla
	}
	/**
	 * Instrucciones para pasar de la ventana de pizzas a la ventana de pedidos
	 */
	private void AbrirPedidoDesdePizza()
	{
		this.ventanaPedido.setVisible(true); // Muestra la pantalla
		this.ventanaPizza.setVisible(false); // Oculta la pantalla
		resetearVentanaPedido(); // Recupera los valores por defecto de la ventana
	}
	/**
	 * Instrucciones para pasar de la ventana para confirmar a la ventana principal
	 */
	private void AbrirConfirmarDesdePrincipal()
	{
		ventanaConfirmar.setScannerText(""); // Vacia el ultimo valor introducido en el lector, para que se vea una interfaz mas limpia
		this.ventanaConfirmar.setVisible(true); // Muestra la pantalla
		this.ventanaPrincipal.setVisible(false); // Oculta la pantalla
		nuevaOrden = new Orden();
	}
	/**
	 * Instrucciones para pasar de la ventana para confirmar a la ventana principal
	 */
	private void AbrirPrincipalDesdeConfirmar()
	{
		this.ventanaPrincipal.setVisible(true); // Muestra la pantalla
		this.ventanaConfirmar.setVisible(false); // Oculta la pantalla
		nuevaOrden = new Orden();
	}
	/**
	 * Instrucciones restablecer los cambios de la ventana para generar pedidos a sus valores por defecto
	 */
	public void resetearVentanaPedido()
	{		
		if(nuevaOrden.getPizzas().isEmpty() == false) // Si el pedido tiene al menos una pizza
		{
			boolean aptaCeliacos = true; // El pedido tiene presuncion de celiaciencia: es apto para celiacos hasta que se demuestre lo contrario
			for(int i = 0; i < nuevaOrden.getPizzas().size(); i++) // Recorre las pizzas del pedido
			{
				if(nuevaOrden.getPizzas().get(i).aptaCeliacos() == false) // Consulta si la pizza es apta para celiacos
				{
					aptaCeliacos = false; // Si no lo es, el pedido tampoco
				}
			}
			if(aptaCeliacos == true) // Si todas las pizzas son aptas para celiacos, el pedido tambien lo es
			{
				ventanaPedido.setLabelCeliaco("Su pedido SÍ es apto para celiacos"); // Muestra la informacion al usuario
			}
			else
			{
				ventanaPedido.setLabelCeliaco("Su pedido NO es apto para celiacos"); // Muestra la informacion al usuario
			}
		}
		ventanaPedido.setLabelPrecioText("El coste actual de su pedido es: " + nuevaOrden.calcularCostePedido() + "€"); // Actualizar el texto que muestra el precio actual del pedido
		ventanaPedido.getBtnEliminarPizza().setText("Eliminar última pizza"); // Resetea el texto al valor por defecto
		ventanaPedido.getBtnVerPedido().setText("Ver pedido"); // Resetea el texto al valor por defecto
		ventanaPedido.getBtnEnviarPedido().setText("EnviarPedido"); // Resetea el texto al valor por defecto
	}
	/**
	 * Instrucciones restablecer los cambios de la ventana para anadir pizzas a sus valores por defecto
	 */
	public void resetearVentanaPizza()
	{
		// Quita el color azul para que el usuario entienda que aun no ha elegido nada
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
		ventanaPizza.getBtnEnviarPizza().setText("Enviar pizza"); // Resetea el texto al valor por defecto
	}
	/**
	 * Instrucciones restablecer los cambios de la ventana para confirmar entregas a sus valores por defecto
	 */
	public void resetearVentanaConfirmar()
	{
		eliminarComentariosConfirmar(); // Elimina los textos auxiliares para generar unos nuevos
		
		Vector<String> pedidos = pedidosCSV.pedidosSinConfirmar(); // Recupera todos los pedidos que tienen el campo de entregado como "0"
		int posXPedido = 12; // Pocion X en la que se crearan los textos de pedidos
		int posXPizza = 35;// Pocion X en la que se crearan los textos de pizzas
		int posYactual = 100; // Posicion Y en la que se creara el primer texto
		String nuevoTexto;
		if(pedidos.isEmpty() == true) // Si no hay pedidos sin entregar
		{
			nuevoTexto = "¡Estamos al día! Todos nuestros pedidos han sido ya entregados";
			ventanaConfirmar.crearLabel(posXPedido, posYactual + 35, 408, 25, nuevoTexto); // Solo devuelve el mensaje explicando que pasa
		}
		else // Si hay al menos un pedido sin confirmar
		{		
			for(int i = 0; i < pedidos.size(); i++) // Recorre el vector obtenido
			{
				posYactual += 35; // Margen entre textos
				nuevoTexto = "Pedido con ID: " + pedidos.get(i); // Texto que se mostrara
				ventanaConfirmar.crearLabel(posXPedido, posYactual, 408, 25, nuevoTexto); // Crea el label con posicion y texto indicados
				
				Vector<String> titulos = pizzasCSV.buscarPizzasPorPedido(Integer.parseInt(pedidos.get(i))); // Busca las pizzas que contiene el pedido
				
				for(int j = 0; j < titulos.size(); j++) // 
				{
					posYactual += 20; // Margen entre textos
					nuevoTexto = "- " + titulos.get(j); // Texto que se mostrara
					ventanaConfirmar.crearLabel(posXPizza, posYactual, 408, 25, nuevoTexto); // Crea el label con posicion y texto indicados
				}
			}
		}
		ventanaConfirmar.setInstruccionesText("Introduzca el ID del pedido que desea confirmar"); // Texto con las instrucciones para el usuario
		ventanaConfirmar.repaint(); // Refresca la ventana
	}
	/**
	 * Instrucciones restablecer los cambios de la ventana de historial de pedidos a sus valores por defecto
	 */
	public void resetearVentanaHistorial()
	{
		eliminarComentariosHistorial(); // Elimina los textos auxiliares para generar unos nuevos
		
		Vector<String> pedidos = pedidosCSV.mostrarRegistro(); // Recupera todos los pedidos almacenados en el CSV
		int posXPedido = 30; // Pocion X en la que se crearan los textos de pedidos
		int posYactual = 25; // Posicion Y en la que se creara el primer texto
		String nuevoTexto;
		if(pedidos.isEmpty() == true) // Si no se ha creado ningun pedido
		{
			nuevoTexto = "Aún no se ha emitido ningún pedido";
			ventanaHistorial.crearLabel(posXPedido, posYactual + 35, 408, 25, nuevoTexto); // Solo muestra un label explicando que pasa
		}
		else // Si se ha creado al menos un pedido
		{		
			for(int i = 0; i < pedidos.size(); i++) // Recorre el vector de pedidos
			{
				posYactual += 30; // Margen entre textos
				nuevoTexto = pedidos.get(i);
				ventanaHistorial.crearLabel(posXPedido, posYactual, 408, 25, nuevoTexto); // Crea el label con posicion y texto indicados
			}
		}
		ventanaHistorial.repaint(); // Refresca la ventana
	}
	/**
	 * Metodo para eliminar los textos que informan de los pedidos que faltan por confirmar, para poder generar unos nuevos con informacion actualizada
	 */
	public void eliminarComentariosConfirmar()
	{
		for(int i = 0; i < ventanaConfirmar.getLabelsBorrar().size(); i++) // Recorre el vector a borrar
		{
			ventanaConfirmar.getLabelsBorrar().get(i).setText(""); // Elimina el texto para que no se vea
			ventanaConfirmar.getLabelsBorrar().remove(i); // Lo elimina del vector
			i--;
		}
	}
	/**
	 * Metodo para eliminar los textos que muestran cada pedido, para poder generar unos nuevos con informacion actualizada
	 */
	public void eliminarComentariosHistorial()
	{
		for(int i = 0; i < ventanaHistorial.getLabelsBorrar().size(); i++) // Recorre el vector a eliminar
		{
			ventanaHistorial.getLabelsBorrar().get(i).setText(""); // Elimina el texto para que no se vea
			ventanaHistorial.getLabelsBorrar().remove(i); // Elimina el label del vector
			i--; // Corrige el incremento para que no se salte la mitad
		}
	}
	/**
	 * Metodo que simplifica la tarea de unir dos cadenas de caracteres
	 * @param original Cadena a la que se le quiere anadir otra
	 * @param extra Cadena que quiere ser anadida a la original
	 */
	public void concatenar(String original, String extra)
	{
		original = original + "" + extra; // Une ambas cadenas de caracteres
	}
	/**
	 * Metodo para configurar la informacion fija de cada base y cada ingrediente y tenerla almacenada en memoria
	 */
	private void IniciarBasesIngredientes() // Funcion con la que generamos a mano cada base e ingrediente
	{
		Base baseAux = new Base("Pequena", true, 1.5, "pequena"); // Se asignan los datos propios de la base
		listaBases.add(baseAux); // Se añade al vector local
		baseAux = new Base("Grande", true, 3.75, "grande"); // Se asignan los datos propios de la base
		listaBases.add(baseAux); // Se añade al vector local
		baseAux = new Base("Pequena sin gluten", false, 2, "pequena"); // Se asignan los datos propios de la base
		listaBases.add(baseAux); // Se añade al vector local
		baseAux = new Base("Grande sin gluten", false, 4.5, "grande"); // Se asignan los datos propios de la base
		listaBases.add(baseAux); // Se añade al vector local
		
		Ingrediente ingrAux = new Ingrediente("queso", true, 0.6); // Se asignan los datos propios del ingrediente
		listaIngr.add(ingrAux); // Se añade al vector local
		ingrAux = new Ingrediente("queso sin gluten", false, 0.9); // Se asignan los datos propios del ingrediente
		listaIngr.add(ingrAux); // Se añade al vector local
		ingrAux = new Ingrediente("tomate", false, 0.65); // Se asignan los datos propios del ingrediente
		listaIngr.add(ingrAux); // Se añade al vector local
		ingrAux = new Ingrediente("champinones", true, 0.75); // Se asignan los datos propios del ingrediente
		listaIngr.add(ingrAux); // Se añade al vector local
		ingrAux = new Ingrediente("bacon", true, 1.25); // Se asignan los datos propios del ingrediente
		listaIngr.add(ingrAux); // Se añade al vector local
		ingrAux = new Ingrediente("aceitunas", true, 0.55); // Se asignan los datos propios del ingrediente
		listaIngr.add(ingrAux); // Se añade al vector local
		ingrAux = new Ingrediente("anchoas", false, 0.5); // Se asignan los datos propios del ingrediente
		listaIngr.add(ingrAux); // Se añade al vector local
		ingrAux = new Ingrediente("pimiento", false, 0.6); // Se asignan los datos propios del ingrediente
		listaIngr.add(ingrAux); // Se añade al vector local
		ingrAux = new Ingrediente("york", true, 0.75); // Se asignan los datos propios del ingrediente
		listaIngr.add(ingrAux); // Se añade al vector local
		ingrAux = new Ingrediente("serrano", true, 1.2); // Se asignan los datos propios del ingrediente
		listaIngr.add(ingrAux); // Se añade al vector local
		ingrAux = new Ingrediente("cebolla", false, 0.3); // Se asignan los datos propios del ingrediente
		listaIngr.add(ingrAux); // Se añade al vector local
		ingrAux = new Ingrediente("cebolla caramelizada", false, 0.6); // Se asignan los datos propios del ingrediente
		listaIngr.add(ingrAux); // Se añade al vector local
		ingrAux = new Ingrediente("pollo", false, 0.45); // Se asignan los datos propios del ingrediente
		listaIngr.add(ingrAux); // Se añade al vector local
		ingrAux = new Ingrediente("pepperoni", true, 0.85); // Se asignan los datos propios del ingrediente
		listaIngr.add(ingrAux); // Se añade al vector local
		ingrAux = new Ingrediente("maiz", true, 0.3); // Se asignan los datos propios del ingrediente
		listaIngr.add(ingrAux); // Se añade al vector local
		ingrAux = new Ingrediente("atun", false, 0.65); // Se asignan los datos propios del ingrediente
		listaIngr.add(ingrAux); // Se añade al vector local
		ingrAux = new Ingrediente("pina", false, 0.8); // Se asignan los datos propios del ingrediente
		listaIngr.add(ingrAux); // Se asignan los datos propios del ingrediente
	}
}