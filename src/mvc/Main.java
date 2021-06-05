package mvc;

import javax.swing.JFrame;

public class Main extends JFrame
{
	static VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
	static VentanaPedido ventanaPedido = new VentanaPedido();
	static VentanaPizza ventanaPizza = new VentanaPizza();
	static VentanaHistorial ventanaHistorial = new VentanaHistorial();
	
	public Main()
	{

	}
	
	public static void main(String[] args)
	{
		Controlador controlador = new Controlador(ventanaPrincipal,
													ventanaPedido,
													ventanaPizza,
													ventanaHistorial);
	}
}