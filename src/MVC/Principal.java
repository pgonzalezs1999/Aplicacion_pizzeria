package MVC;

//import java.util.Date;
//import java.util.Vector;

public class Principal
{
	public static void main(String[] args)
	{				
		// Vistas
		VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
		VentanaPedido ventanaPedido = new VentanaPedido();
		VentanaPizza ventanaPizza = new VentanaPizza();
		VentanaHistorial ventanaHistorial = new VentanaHistorial();
		
		Controlador controlador = new Controlador(ventanaPrincipal,
													ventanaPedido,
													ventanaPizza,
													ventanaHistorial);
	}
}