package MVC;

//import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class VentanaPrincipal extends JFrame
{
	// Atributos
	private JPanel contentPane;
	private JButton btnNuevoPedido;
	private JButton btnConfirmarPedido;
	private JButton btnVerHistorial;
	
	/**
	 * Create the frame.
	 */
	public VentanaPrincipal()
	{
		setTitle("Ventana Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 200, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnNuevoPedido = new JButton("Nuevo pedido");
		btnNuevoPedido.setBounds(12, 30, 408, 25);
		contentPane.add(btnNuevoPedido);
		
		btnConfirmarPedido = new JButton("Confirmar pedido");
		btnConfirmarPedido.setBounds(12, 70, 408, 25);
		contentPane.add(btnConfirmarPedido);
		
		btnVerHistorial = new JButton("Ver historial de pedidos");
		btnVerHistorial.setBounds(12, 110, 408, 25);
		contentPane.add(btnVerHistorial);
	}

	// Getters/Setters
	public JButton getBtnNuevoPedido()
	{
		return btnNuevoPedido;
	}	
	public JButton getBtnConfirmarPedido()
	{
		return btnConfirmarPedido;
	}	
	public JButton getBtnVerHistorial()
	{
		return btnVerHistorial;
	}
}