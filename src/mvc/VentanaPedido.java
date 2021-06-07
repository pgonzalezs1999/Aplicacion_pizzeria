package mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Orden;

public class VentanaPedido extends JFrame
{
	// Atributos
	private JButton btnAniadirPizza;
	private JButton btnEliminarPizza;
	private JButton btnVerPedido;
	private JButton btnEnviarPedido;
	private JButton btnCancelarPedido;
	
	private JPanel contentPane;
	
	private JLabel labelPrecio;
	private JLabel labelCeliaco;
	
	private JTable tablaPizzas = new JTable();

	private Color rojoSuave = new Color(255, 200, 200);
	private Color verdeSuave = new Color(200, 255, 200);

	public VentanaPedido()
	{
		setTitle("Ventana Pedido");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 100, 700, 600);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnAniadirPizza = new JButton("Añadir pizza");
		btnAniadirPizza.setBounds(12, 30, 408, 25);
		btnAniadirPizza.setAlignmentX(CENTER_ALIGNMENT);
		contentPane.add(btnAniadirPizza);
		
		btnEliminarPizza = new JButton("Eliminar pizza");
		btnEliminarPizza.setBounds(12, 70, 408, 25);
		btnEliminarPizza.setAlignmentX(CENTER_ALIGNMENT);
		contentPane.add(btnEliminarPizza);
		
		btnVerPedido = new JButton("Ver pedido");
		btnVerPedido.setBounds(12, 110, 408, 25);
		btnVerPedido.setAlignmentX(CENTER_ALIGNMENT);
		contentPane.add(btnVerPedido);
		
		btnEnviarPedido = new JButton("Enviar Pedido");
		btnEnviarPedido.setBounds(12, 150, 408, 25);
		btnEnviarPedido.setAlignmentX(CENTER_ALIGNMENT);
		btnEnviarPedido.setBackground(verdeSuave);
		contentPane.add(btnEnviarPedido);
		
		btnCancelarPedido = new JButton("Cancelar pedido");
		btnCancelarPedido.setBounds(12, 190, 408, 25);
		btnCancelarPedido.setAlignmentX(CENTER_ALIGNMENT);
		btnCancelarPedido.setBackground(rojoSuave);
		contentPane.add(btnCancelarPedido);
				
		labelPrecio = new JLabel();
		labelPrecio.setBounds(12, 230, 408, 25);
		contentPane.add(labelPrecio);
		
		labelCeliaco = new JLabel();
		labelCeliaco.setText("Su pedido SÍ es apto para celiacos");
		labelCeliaco.setBounds(12, 250, 408, 25);
		contentPane.add(labelCeliaco);
		
		/*String[] nombreColumnas = {"Descripción", "Precio"};
		String[][] datos = {{null, null}};
		tablaPizzas.setBackground(null);
		tablaPizzas = new JTable(datos, nombreColumnas);
		tablaPizzas.setBounds(12, 290, 408, 100);
		contentPane.add(tablaPizzas);*/
	}
	
	/*public void ActivarBuilder()
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					VentanaPedido frame = new VentanaPedido();
					frame.setVisible(true);
				}
				catch (Exception e) { e.printStackTrace(); }
			}
		});
	}*/
	
	public void CrearTablaPizzas(String[][] datos)
	{
		String[] nombreColumnas = {"Descripción", "Precio"};				
		tablaPizzas = new JTable(datos, nombreColumnas);		
		contentPane.add(tablaPizzas);
		tablaPizzas.setBackground(null);
		tablaPizzas.setBounds(12, 290, 408, 100);
		tablaPizzas.getColumn(0).setMinWidth(100);
		tablaPizzas.getColumn(1).setMinWidth(100);
	}
	
	public void resetear()
	{
		btnEliminarPizza.setText("EliminarPizza");
		btnVerPedido.setText("Ver pedido");
	}
	
	// Getters/Setters
	public void setLabelPrecioText(String nuevoTexto)
	{
		labelPrecio.setText(nuevoTexto);
	}
	public JButton getBtnAniadirPizza()
	{
		return btnAniadirPizza;
	}
	public JButton getBtnEliminarPizza()
	{
		return btnEliminarPizza;
	}
	public JButton getBtnVerPedido()
	{
		return btnVerPedido;
	}
	public JButton getBtnEnviarPedido()
	{
		return btnEnviarPedido;
	}
	public JButton getBtnCancelarPedido()
	{
		return btnCancelarPedido;
	}
	public String getLabelPrecioText()
	{
		return labelPrecio.getText();
	}
}