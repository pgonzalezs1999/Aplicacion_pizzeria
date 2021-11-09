/**
 * Paquete que gestiona el modelo vista-controlador
 * @author Pablo Gonzalez Sanchez <a href=mailto:pgonzalezs1999@gmail.com>pgonzalezs1999@gmail.com</a>
 */
package mvc;
//import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.util.Vector;
import javax.swing.JButton;
/**
 * Gestiona la vista de la ventana para ver el historial de pedidos
 */
public class VentanaHistorial extends JFrame
{
	private JPanel contentPane; // Panel que almacenara todo el contenido to display de la ventana
	private JButton btnVolver; // Unico boton de la ventana
	
	Vector<JLabel> labelsBorrar = new Vector<JLabel>(); // Almacena los textos temporales que describen cada pedido sin confirmar
	
	// Constructor
	/**
	 * Inicia el constructor. Crea el frame de la ventana
	 */
	public VentanaHistorial()
	{
		setTitle("Ventana Historial"); // Nombre de encabezado de la ventana
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar ventana al salir
		setBounds(100, 70, 450, 600); // Indicar posicion y dimensiones
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // Margenes de la ventana
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnVolver = new JButton("Volver a Ventana Principal"); // Texto que muestra
		btnVolver.setBounds(12, 20, 408, 25); // Indicar posicion y dimensiones 
		contentPane.add(btnVolver); // Añadir al panel
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
	 * Metodo que devuelve el boton para volver a la ventana principal
	 * @return Boton para volver a la ventana principal
	 */
	public JButton getBtnVolver()
	{
		return this.btnVolver;
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