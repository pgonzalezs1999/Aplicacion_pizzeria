package mvc;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;

public class Main extends JFrame
{
	static VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
	static VentanaPedido ventanaPedido = new VentanaPedido();
	static VentanaPizza ventanaPizza = new VentanaPizza();
	static VentanaHistorial ventanaHistorial = new VentanaHistorial();
	private JTable table;
	
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