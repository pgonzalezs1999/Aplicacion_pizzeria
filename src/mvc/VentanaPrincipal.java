package mvc;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
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
	
	private JLabel labelAreaClientes;
	private JLabel labelAreaTrabajadores;
	private JLabel labelPedidoEnviado;
	
	/**
	 * Create the frame.
	 */
	public VentanaPrincipal()
	{
		setTitle("Ventana Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 100, 450, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnNuevoPedido = new JButton("Nuevo pedido");
		btnNuevoPedido.setBounds(12, 60, 408, 25);
		contentPane.add(btnNuevoPedido);
		
		btnConfirmarPedido = new JButton("Confirmar entrega");
		btnConfirmarPedido.setBounds(12, 210, 408, 25);
		contentPane.add(btnConfirmarPedido);
		
		btnVerHistorial = new JButton("Ver historial de pedidos");
		btnVerHistorial.setBounds(12, 170, 408, 25);
		contentPane.add(btnVerHistorial);
		
		labelPedidoEnviado = new JLabel();
		labelPedidoEnviado.setText("¡Su pedido se ha enviado correctamente! Llegará en unos 25 minutos");
		labelPedidoEnviado.setVisible(false);
		labelPedidoEnviado.setBounds(12, 90, 408, 25);
		contentPane.add(labelPedidoEnviado);
		
		labelAreaClientes = new JLabel();
		labelAreaClientes.setText("Área clientes:");
		labelAreaClientes.setBounds(12, 30, 408, 25);
		contentPane.add(labelAreaClientes);
		
		labelAreaTrabajadores = new JLabel();
		labelAreaTrabajadores.setText("Área trabajadores:");
		labelAreaTrabajadores.setBounds(12, 140, 408, 25);
		contentPane.add(labelAreaTrabajadores);
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
	public JLabel getLabelPedidoEnviado()
	{
		return labelPedidoEnviado;
	}
}