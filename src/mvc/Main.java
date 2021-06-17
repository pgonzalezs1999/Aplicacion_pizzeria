/**
 * Paquete que gestiona el modelo vista-controlador
 * @author Pablo Gonzalez Sanchez <a href=mailto:pgonzalezs1999@gmail.com>pgonzalezs1999@gmail.com</a>
 */
package mvc;

import javax.swing.JFrame;
/**
 * Simplemente indica el comienzo del codigo y prepara el controlador
 */
public class Main extends JFrame // Comentario
{
	// Crea las ventanas que en algun punto will be displayed en el programa
	static VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
	static VentanaPedido ventanaPedido = new VentanaPedido();
	static VentanaPizza ventanaPizza = new VentanaPizza();
	static VentanaHistorial ventanaHistorial = new VentanaHistorial();
	static VentanaConfirmar ventanaConfirmar = new VentanaConfirmar();
	
	/**
	 * Comienzo del codigo. Simplemente genera el controlador
	 * @param args Parametro generico para el main de Java
	 */
	public static void main(String[] args)
	{
		Controlador controlador = new Controlador(ventanaPrincipal,
													ventanaPedido,
													ventanaPizza,
													ventanaHistorial,
													ventanaConfirmar);
	}
}