package mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

public class VentanaPedido extends JFrame
{
	// Atributos
	private JButton btnAniadirPizza;
	private JButton btnEliminarPizza;
	private JButton btnVerPedido;
	private JButton btnEnviarPedido;
	private JButton btnCancelarPedido;
	
	private JPanel contentPane;
	
	private JLabel labelDesglose;
	
	JTable tablaPizzas = new JTable();

	Color rojoSuave = new Color(255, 200, 200);

	public VentanaPedido()
	{
		setTitle("Ventana Pedido");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnAniadirPizza = new JButton("Añadir pizza");
		btnAniadirPizza.setBounds(12, 30, 408, 25);
		contentPane.add(btnAniadirPizza);
		
		btnEliminarPizza = new JButton("Eliminar pizza");
		btnEliminarPizza.setBounds(12, 70, 408, 25);
		contentPane.add(btnEliminarPizza);
		
		btnVerPedido = new JButton("Ver pedido");
		btnVerPedido.setBounds(12, 110, 408, 25);
		contentPane.add(btnVerPedido);
		
		btnEnviarPedido = new JButton("Enviar Pedido");
		btnEnviarPedido.setBounds(12, 150, 408, 25);
		contentPane.add(btnEnviarPedido);
		
		btnCancelarPedido = new JButton("Cancelar pedido");
		btnCancelarPedido.setBounds(12, 190, 408, 25);	
		btnCancelarPedido.setBackground(rojoSuave);
		contentPane.add(btnCancelarPedido);
				
		labelDesglose = new JLabel();
		labelDesglose.setBounds(12, 230, 408, 100);
		contentPane.add(labelDesglose);
		
		contentPane.add(tablaPizzas);
	}
	
	public void ActivarBuilder()
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
	}
	
	public void CrearTablaPizzas(String[][] datos)
	{
		String[] nombreColumnas = {"Nombre", "Base", "Ingredientes", "Precio", "Precio Acumulado"};
		tablaPizzas = new JTable(datos, nombreColumnas);
		tablaPizzas.setBounds(12, 270, 408, 100);
		tablaPizzas.setAutoResizeMode(5);
	}
	
	// Getters/Setters
	public void setLabelDesgloseText(String nuevoTexto)
	{
		labelDesglose.setText(nuevoTexto);
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
	public String getLabelDesgloseText()
	{
		return labelDesglose.getText();
	}
}