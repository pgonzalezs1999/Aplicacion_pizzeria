package mvc;

import java.awt.Color;
import java.util.Vector;

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
	
	private JLabel labelPrecio;
	private JLabel labelCeliaco;
	private JLabel labelID;
	
	private JTable tablaPizzas = new JTable();

	private Color rojoSuave = new Color(255, 200, 200);
	private Color verdeSuave = new Color(200, 255, 200);
	
	Vector<JLabel> labeles = new Vector<JLabel>();

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
		
		btnEliminarPizza = new JButton("Eliminar última pizza");
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
		
		labelID = new JLabel();
		labelID.setText("Pedido número: ");
		labelID.setBounds(12, 270, 408, 25);
		contentPane.add(labelID);
	}
	
	public void crearLabel(int posX, int posY, int ancho, int alto, String texto)
	{
		JLabel labelAux = new JLabel();
		labelAux.setBounds(posX, posY, ancho, alto);
		labelAux.setText(texto);
		contentPane.add(labelAux);
		labeles.add(labelAux);
	}
	
	// Getters/Setters
	public void setLabelCeliaco(String nuevoTexto)
	{
		labelCeliaco.setText(nuevoTexto);
	}	
	public void setLabelPrecioText(String nuevoTexto)
	{
		labelPrecio.setText(nuevoTexto);
	}
	public void setLabelID(String nuevoTexto)
	{
		labelID.setText(nuevoTexto);
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
	public Vector<JLabel> getLabeles()
	{
		return labeles;
	}
}