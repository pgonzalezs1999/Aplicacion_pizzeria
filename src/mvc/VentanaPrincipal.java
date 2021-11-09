/**
 * Paquete que gestiona el modelo vista-controlador
 * @author Pablo Gonzalez Sanchez <a href=mailto:pgonzalezs1999@gmail.com>pgonzalezs1999@gmail.com</a>
 */
package mvc;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
/**
 * Gestiona la vista de la ventana principal
 */
public class VentanaPrincipal extends JFrame
{
	private JPanel contentPane; // Panel que almacenara todo el contenido to display de la ventana
	
	// Botones de la ventana
	private JButton btnNuevoPedido;
	private JButton btnConfirmarPedido;
	private JButton btnVerHistorial;
	
	// Labeles de la ventana
	private JLabel labelAreaClientes;
	private JLabel labelAreaTrabajadores;
	private JLabel labelPedidoEnviado;
	
	// Constructor
	/**
	 * Inicia el constructor. Crea el frame de la ventana
	 */
	public VentanaPrincipal()
	{
		setTitle("Ventana Principal"); // Nombre de encabezado de la ventana
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar ventana al salir
		setBounds(100, 70, 450, 600); // Indicar posicion y dimensiones
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // Margenes de la ventana
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnNuevoPedido = new JButton("Nuevo pedido"); // Texto que muestra
		btnNuevoPedido.setBounds(12, 60, 408, 25); // Indicar posicion y dimensiones
		contentPane.add(btnNuevoPedido); // Añadir al panel
		
		btnConfirmarPedido = new JButton("Confirmar entrega"); // Texto que muestra
		btnConfirmarPedido.setBounds(12, 210, 408, 25); // Indicar posicion y dimensiones
		contentPane.add(btnConfirmarPedido); // Añadir al panel
		
		btnVerHistorial = new JButton("Ver historial de pedidos"); // Texto que muestra
		btnVerHistorial.setBounds(12, 170, 408, 25); // Indicar posicion y dimensiones
		contentPane.add(btnVerHistorial); // Añadir al panel
		
		labelPedidoEnviado = new JLabel();
		labelPedidoEnviado.setText("¡Su pedido se ha enviado correctamente! Llegará en unos 25 minutos"); // Texto que muestra
		labelPedidoEnviado.setVisible(false);
		labelPedidoEnviado.setBounds(12, 90, 408, 25); // Indicar posicion y dimensiones
		contentPane.add(labelPedidoEnviado); // Añadir al panel
		
		labelAreaClientes = new JLabel();
		labelAreaClientes.setText("Área clientes:"); // Texto que muestra
		labelAreaClientes.setBounds(12, 30, 408, 25); // Indicar posicion y dimensiones
		contentPane.add(labelAreaClientes); // Añadir al panel
		
		labelAreaTrabajadores = new JLabel();
		labelAreaTrabajadores.setText("Área trabajadores:"); // Texto que muestra
		labelAreaTrabajadores.setBounds(12, 140, 408, 25); // Indicar posicion y dimensiones
		contentPane.add(labelAreaTrabajadores); // Añadir al panel
	}

	// Getters/Setters
	/**
	 * Metodo que devuelve el boton para crear un nuevo pedido
	 * @return Boton para crear un nuevo pedido
	 */
	public JButton getBtnNuevoPedido()
	{
		return btnNuevoPedido;
	}
	/**
	 * Metodo que devuelve el boton para confirmar pedidos en tramite
	 * @return Boton para confirmar pedidos en tramite
	 */
	public JButton getBtnConfirmarPedido()
	{
		return btnConfirmarPedido;
	}	
	/**
	 * Metodo que devuelve el boton para ver el historial de pedidos
	 * @return Boton para ver el historial de pedidos
	 */
	public JButton getBtnVerHistorial()
	{
		return btnVerHistorial;
	}
	/**
	 * Metodo que devuelve el label que explica que el pedido ha sido enviado
	 * @return Label que explica que el pedido ha sido enviado
	 */
	public JLabel getLabelPedidoEnviado()
	{
		return labelPedidoEnviado;
	}
}