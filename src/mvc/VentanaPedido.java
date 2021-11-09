/**
 * Paquete que gestiona el modelo vista-controlador
 * @author Pablo Gonzalez Sanchez <a href=mailto:pgonzalezs1999@gmail.com>pgonzalezs1999@gmail.com</a>
 */
package mvc;

import java.awt.Color;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
/**
 * Gestiona la vista de la ventana para crear un nuevo pedido
 */
public class VentanaPedido extends JFrame
{
	private JPanel contentPane; // Panel que almacenara todo el contenido to display de la ventana
	
	// Botones de la ventana
	private JButton btnAniadirPizza;
	private JButton btnEliminarPizza;
	private JButton btnVerPedido;
	private JButton btnEnviarPedido;
	private JButton btnCancelarPedido;
	
	// Labeles de la ventana
	private JLabel labelPrecio;
	private JLabel labelCeliaco;
	private JLabel labelID;

	private Color rojoSuave = new Color(255, 200, 200); // Color hexadecimal exacto para los botones rojos
	private Color verdeSuave = new Color(200, 255, 200); // Color hexadecimal exacto para los botones verdes
	
	Vector<JLabel> labelsBorrar = new Vector<JLabel>(); // Almacena los textos temporales que describen cada pedido sin confirmar
	
	// Constructor
	/**
	 * Inicia el constructor. Crea el frame de la ventana
	 */
	public VentanaPedido()
	{
		setTitle("Ventana Pedido"); // Nombre de encabezado de la ventana
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar ventana al salir
		setBounds(100, 70, 450, 600); // Indicar posicion y dimensiones
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // Margenes de la ventana
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnAniadirPizza = new JButton("Añadir pizza"); // Texto que muestra
		btnAniadirPizza.setBounds(12, 30, 408, 25); // Indicar posicion y dimensiones
		btnAniadirPizza.setAlignmentX(CENTER_ALIGNMENT);
		contentPane.add(btnAniadirPizza); // Añadir al panel
		
		btnEliminarPizza = new JButton("Eliminar última pizza"); // Texto que muestra
		btnEliminarPizza.setBounds(12, 70, 408, 25); // Indicar posicion y dimensiones
		btnEliminarPizza.setAlignmentX(CENTER_ALIGNMENT);
		contentPane.add(btnEliminarPizza); // Añadir al panel
		
		btnVerPedido = new JButton("Ver pedido"); // Texto que muestra
		btnVerPedido.setBounds(12, 110, 408, 25); // Indicar posicion y dimensiones
		btnVerPedido.setAlignmentX(CENTER_ALIGNMENT);
		contentPane.add(btnVerPedido); // Añadir al panel
		
		btnEnviarPedido = new JButton("Enviar Pedido"); // Texto que muestra
		btnEnviarPedido.setBounds(12, 150, 408, 25); // Indicar posicion y dimensiones
		btnEnviarPedido.setAlignmentX(CENTER_ALIGNMENT);
		btnEnviarPedido.setBackground(verdeSuave); // Darle el tono de verde elejido
		contentPane.add(btnEnviarPedido); // Añadir al panel
		
		btnCancelarPedido = new JButton("Cancelar pedido"); // Texto que muestra
		btnCancelarPedido.setBounds(12, 190, 408, 25); // Indicar posicion y dimensiones
		btnCancelarPedido.setAlignmentX(CENTER_ALIGNMENT);
		btnCancelarPedido.setBackground(rojoSuave); // Darle el tono de rojo elejido
		contentPane.add(btnCancelarPedido); // Añadir al panel
				
		labelPrecio = new JLabel();
		labelPrecio.setBounds(12, 230, 408, 25); // Indicar posicion y dimensiones
		contentPane.add(labelPrecio); // Añadir al panel
		
		labelCeliaco = new JLabel();
		labelCeliaco.setText("Su pedido SÍ es apto para celiacos"); // Texto que muestra
		labelCeliaco.setBounds(12, 250, 408, 25); // Indicar posicion y dimensiones
		contentPane.add(labelCeliaco); // Añadir al panel
		
		labelID = new JLabel();
		labelID.setText("Pedido número: "); // Texto que muestra
		labelID.setBounds(12, 270, 408, 25); // Indicar posicion y dimensiones
		contentPane.add(labelID); // Añadir al panel
	}
	/**
	 * Crea un label con las caracteristicas proporcionadas
	 * @param posX Poscion X del label
	 * @param posY Posicion Y del label
	 * @param ancho Dimension horizontal del label
	 * @param alto Dimension vertical del label
	 * @param texto Texto que mostrara el label
	 */
	public void crearLabel(int posX, int posY, int ancho, int alto, String texto)
	{
		JLabel labelAux = new JLabel();
		labelAux.setBounds(posX, posY, ancho, alto); // Indicar posicion y dimensiones
		labelAux.setText(texto); // Texto que muestra
		contentPane.add(labelAux); // Añadir al panel
		labelsBorrar.add(labelAux); // Añadir al vector para saber cuales hay que eliminar al refrescar
	}
	
	// Getters/Setters
	/**
	 * Metodo que devuelve el label que explica si el pedido en curso es para celiacos o no
	 * @param nuevoTexto Texto a asignar al label
	 */
	public void setLabelCeliaco(String nuevoTexto)
	{
		labelCeliaco.setText(nuevoTexto);
	}
	/**
	 * Metodo que devuelve el label que explica el precio actual del pedido en curso
	 * @param nuevoTexto Texto a asignar al label
	 */
	public void setLabelPrecioText(String nuevoTexto)
	{
		labelPrecio.setText(nuevoTexto);
	}
	/**
	 * Metodo que devuelve el label que explica el ID del pedido en curso
	 * @param nuevoTexto Texto a asignar al label
	 */
	public void setLabelID(String nuevoTexto)
	{
		labelID.setText(nuevoTexto);
	}
	/**
	 * Metodo que devuelve el boton para anadir una pizza al pedido
	 * @return Boton para anadir una pizza al pedido
	 */
	public JButton getBtnAniadirPizza()
	{
		return btnAniadirPizza;
	}
	/**
	 * Metodo que devuelve el boton para eliminar una pizza del pedido
	 * @return Boton para eliminar una pizza del pedido
	 */
	public JButton getBtnEliminarPizza()
	{
		return btnEliminarPizza;
	}
	/**
	 * Metodo que devuelve el boton para ver el estado actual del pedido
	 * @return Boton para ver el estado actual del pedido
	 */
	public JButton getBtnVerPedido()
	{
		return btnVerPedido;
	}
	/**
	 * Metodo que devuelve el boton para terminar y enviar el pedido actual
	 * @return Boton para terminar y enviar el pedido actual
	 */
	public JButton getBtnEnviarPedido()
	{
		return btnEnviarPedido;
	}
	/**
	 * Metodo que devuelve el boton para cancelar el pedido actual
	 * @return Boton para cancelar el pedido actual
	 */
	public JButton getBtnCancelarPedido()
	{
		return btnCancelarPedido;
	}
	/**
	 * Metodo para modificar el label que muestra el precio actual del pedido en curso
	 * @return Label que muestra el precio actual del pedido en curso
	 */
	public String getLabelPrecioText()
	{
		return labelPrecio.getText();
	}
	/**
	 * Metodo que devuelve el vector de labels a eliminar cuando se refresque la pantalla
	 * @return Label que muestra el precio actual del pedido en curso
	 */
	public Vector<JLabel> getLabeles()
	{
		return labelsBorrar;
	}
}