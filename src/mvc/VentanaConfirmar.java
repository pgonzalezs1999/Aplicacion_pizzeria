/**
 * Paquete que gestiona el modelo vista-controlador
 * @author Pablo Gonzalez Sanchez <a href=mailto:pgonzalezs1999@gmail.com>pgonzalezs1999@gmail.com</a>
 */
package mvc;

import java.awt.Component;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
/**
 * Gestiona la vista de la ventana para confirmar entrega de pedidos en proceso
 */
public class VentanaConfirmar extends JFrame
{
	private JPanel contentPane; // Panel que almacenara todo el contenido to display de la ventana
	// Botones de la ventana
	private JButton btnVolver;
	private JButton btnConfirmar;
	
	private JTextField scanner; // Espacio disponible para escribir que pedido se quiere confirmar
	private JLabel labelInstrucciones; // Titulo de la ventana
	Vector<JLabel> labelsBorrar = new Vector<JLabel>(); // Almacena los textos temporales que describen cada pedido sin confirmar
	// Constructor
	/**
	 * Inicia el constructor. Crea el frame de la ventana
	 */
	public VentanaConfirmar()
	{
		setTitle("Ventana Principal"); // Nombre de encabezado de la ventana
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar ventana al salir
		setBounds(100, 70, 500, 600); // Indicar posicion y dimensiones
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // Margenes de la ventana
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnVolver = new JButton("Volver al menú"); // Texto que muestra
		btnVolver.setBounds(12, 100, 408, 25); // Indicar posicion y dimensiones 
		contentPane.add(btnVolver); // Añadir al panel
		
		btnConfirmar = new JButton("Confirmar entrega"); // Texto que muestra
		btnConfirmar.setBounds(260, 60, 160, 25); // Indicar posicion y dimensiones
		contentPane.add(btnConfirmar); // Añadir al panel
		
		scanner = new JTextField();
		scanner.setBounds(12, 60, 230, 25); // Indicar posicion y dimensiones
		contentPane.add(scanner); // Añadir al panel
		
		labelInstrucciones = new JLabel();
		labelInstrucciones.setText("Introduce el ID del pedido que desea confirmar:"); // Texto que muestra
		labelInstrucciones.setBounds(12, 30, 408, 25); // Indicar posicion y dimensiones
		contentPane.add(labelInstrucciones); // Añadir al panel		
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
	// Getters y setters
	/**
	 * Metodo que devuelve el boton para volver a la ventana principal
	 * @return Boton para volver a la ventana principal
	 */
	public JButton getBtnVolver()
	{
		return this.btnVolver;
	}	
	/**
	 * Metodo que devuelve el boton para confirmar una entrega indicada
	 * @return Boton para confirmar una entrega indicada
	 */
	public JButton getBtnConfirmar()
	{
		return this.btnConfirmar;
	}
	/**
	 * Metodo que devuelve el TextField para indicar el ID del pedido a confirmar
	 * @return TextField para indicar el ID del pedido a confirmar
	 */
	public String getScannerText()
	{
		return this.scanner.getText();
	}
	/**
	 * Metodo que actualiza el texto contenido en el lector de IDs
	 * @param nuevoTexto Texto que mostrara el lector
	 */
	public void setScannerText(String nuevoTexto)
	{
		this.scanner.setText(nuevoTexto);
	}
	/**
	 * Metodo que devuelve el label con las indicaciones de la ventana
	 * @param nuevoTexto Texto que mostrara el label
	 */
	public void setInstruccionesText(String nuevoTexto)
	{
		this.labelInstrucciones.setText(nuevoTexto);
	}
	/**
	 * Metodo que devuelve el vector de labels a borrar al refrescar
	 * @return Vector de labels a borrar al refrescar
	 */
	public Vector<JLabel> getLabelsBorrar()
	{
		return this.labelsBorrar;
	}
}