/**
 * Paquete que gestiona el modelo vista-controlador
 * @author Pablo Gonzalez Sanchez <a href=mailto:pgonzalezs1999@gmail.com>pgonzalezs1999@gmail.com</a>
 */
package mvc;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
/**
 * Gestiona la vista de la ventana para crear una nueva pizza
 */
public class VentanaPizza extends JFrame
{
	private JPanel contentPane;	// Panel que almacenara todo el contenido to display de la ventana
	
	// Labels de la ventana
	private JLabel labelIngredientes;
	private JLabel labelBases;
	
	// Botones de la ventana
	private JButton btnEnviarPizza;
	// Los ingredientes estan declarados en el orden que se almacenan en el fichero CSV
	private JButton btnQueso;
	private JButton btnQuesoSinGluten;
	private JButton btnTomate;
	private JButton btnChampinones;
	private JButton btnBacon;
	private JButton btnAceitunas;
	private JButton btnAnchoas;
	private JButton btnPimiento;
	private JButton btnYork;
	private JButton btnSerrano;
	private JButton btnCebolla;
	private JButton btnCebollaCaram;
	private JButton btnPollo;
	private JButton btnPepperoni;
	private JButton btnMaiz;
	private JButton btnAtun;
	private JButton btnPina;
	private JButton btnMasaPequena;
	private JButton btnMasaGrande;
	private JButton btnMasaPequenaSG;
	private JButton btnMasaGrandeSG;
	
	private Color verdeSuave = new Color(200, 255, 200); // Color hexadecimal exacto para los botones verdes
	private Color azulSuave = new Color(200, 200, 255);  // Color hexadecimal exacto para los botones azules

	// Constructor
	/**
	 * Inicia el constructor. Crea el frame de la ventana
	 */
	public VentanaPizza()
	{
		setTitle("Ventana Pizza"); // Nombre de encabezado de la ventana
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cerrar ventana al salir
		setBounds(350, 100, 685, 600); // Indicar posicion y dimensiones
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5)); // Margenes de la ventana
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		labelIngredientes = new JLabel("Pulse el tamaño que desea para su pizza"); // Texto que muestra
		labelIngredientes.setBounds(12, 30, 638, 25); // Indicar posicion y dimensiones
		contentPane.add(labelIngredientes); // Añadir al panel
		
		labelIngredientes = new JLabel("Pulse los ingredientes que desea añadir"); // Texto que muestra
		labelIngredientes.setBounds(12, 110, 638, 25); // Indicar posicion y dimensiones
		contentPane.add(labelIngredientes); // Añadir al panel
		
		btnEnviarPizza = new JButton("Enviar Pizza"); // Texto que muestra
		btnEnviarPizza.setBounds(12, 510, 638, 25); // Indicar posicion y dimensiones
		btnEnviarPizza.setBackground(verdeSuave); // Darle el tono de verde elejido
		contentPane.add(btnEnviarPizza); // Añadir al panel
		
		btnQueso = new JButton("Queso (0.60€)"); // Texto que muestra
		btnQueso.setBounds(12, 150, 300, 25); // Indicar posicion y dimensiones
		contentPane.add(btnQueso); // Añadir al panel
		
		btnQuesoSinGluten = new JButton("Queso sin glúten (0.90€)"); // Texto que muestra
		btnQuesoSinGluten.setBounds(12, 190, 300, 25); // Indicar posicion y dimensiones
		contentPane.add(btnQuesoSinGluten); // Añadir al panel
		
		btnTomate = new JButton("Tomate (0.65€)"); // Texto que muestra
		btnTomate.setBounds(12, 230, 300, 25); // Indicar posicion y dimensiones
		contentPane.add(btnTomate); // Añadir al panel
		
		btnChampinones = new JButton("Champiñones (0.75€)"); // Texto que muestra
		btnChampinones.setBounds(12, 270, 300, 25); // Indicar posicion y dimensiones
		contentPane.add(btnChampinones); // Añadir al panel
		
		btnBacon = new JButton("Bacon (1.25€)"); // Texto que muestra
		btnBacon.setBounds(12, 310, 300, 25); // Indicar posicion y dimensiones
		contentPane.add(btnBacon); // Añadir al panel
		
		btnAceitunas = new JButton("Aceitunas (0.55€)"); // Texto que muestra
		btnAceitunas.setBounds(12, 350, 300, 25); // Indicar posicion y dimensiones
		contentPane.add(btnAceitunas); // Añadir al panel
		
		btnAnchoas = new JButton("Anchoas (0.50€)"); // Texto que muestra
		btnAnchoas.setBounds(12, 390, 300, 25); // Indicar posicion y dimensiones
		contentPane.add(btnAnchoas); // Añadir al panel
		
		btnPimiento = new JButton("Pimiento (0.60€)"); // Texto que muestra
		btnPimiento.setBounds(12, 430, 300, 25); // Indicar posicion y dimensiones
		contentPane.add(btnPimiento); // Añadir al panel
		
		btnYork = new JButton("York (0.75€)"); // Texto que muestra
		btnYork.setBounds(12, 470, 300, 25); // Indicar posicion y dimensiones
		contentPane.add(btnYork); // Añadir al panel
		
		btnSerrano = new JButton("Serrano (1.20€)"); // Texto que muestra
		btnSerrano.setBounds(350, 150, 300, 25); // Indicar posicion y dimensiones
		contentPane.add(btnSerrano); // Añadir al panel
		
		btnCebolla = new JButton("Cebolla (0.30€)"); // Texto que muestra
		btnCebolla.setBounds(350, 190, 300, 25); // Indicar posicion y dimensiones
		contentPane.add(btnCebolla); // Añadir al panel
		
		btnCebollaCaram = new JButton("Cebolla caramelizada (0.60€)"); // Texto que muestra
		btnCebollaCaram.setBounds(350, 230, 300, 25); // Indicar posicion y dimensiones
		contentPane.add(btnCebollaCaram); // Añadir al panel
		
		btnPollo = new JButton("Pollo (0.45€)"); // Texto que muestra
		btnPollo.setBounds(350, 270, 300, 25); // Indicar posicion y dimensiones
		contentPane.add(btnPollo); // Añadir al panel
		
		btnPepperoni = new JButton("Pepperoni (0.85€)"); // Texto que muestra
		btnPepperoni.setBounds(350, 310, 300, 25); // Indicar posicion y dimensiones
		contentPane.add(btnPepperoni); // Añadir al panel
		
		btnMaiz = new JButton("Maiz (0.30€)"); // Texto que muestra
		btnMaiz.setBounds(350, 350, 300, 25); // Indicar posicion y dimensiones
		contentPane.add(btnMaiz); // Añadir al panel
		
		btnAtun = new JButton("Atún (0.65€)"); // Texto que muestra
		btnAtun.setBounds(350, 390, 300, 25); // Indicar posicion y dimensiones
		contentPane.add(btnAtun); // Añadir al panel
		
		btnPina = new JButton("Piña (0.80€)"); // Texto que muestra
		btnPina.setBounds(350, 430, 300, 25); // Indicar posicion y dimensiones
		contentPane.add(btnPina); // Añadir al panel
		
		btnMasaPequena = new JButton("Pequeña"); // Texto que muestra
		btnMasaPequena.setBounds(12, 70, 150, 25); // Indicar posicion y dimensiones
		contentPane.add(btnMasaPequena); // Añadir al panel
		
		btnMasaGrande = new JButton("Grande"); // Texto que muestra
		btnMasaGrande.setBounds(177, 70, 150, 25); // Indicar posicion y dimensiones
		contentPane.add(btnMasaGrande); // Añadir al panel
		
		btnMasaPequenaSG = new JButton("Pequeña sin glúten"); // Texto que muestra
		btnMasaPequenaSG.setBounds(342, 70, 150, 25); // Indicar posicion y dimensiones
		contentPane.add(btnMasaPequenaSG); // Añadir al panel
		
		btnMasaGrandeSG = new JButton("Grande sin glúten"); // Texto que muestra
		btnMasaGrandeSG.setBounds(507, 70, 150, 25); // Indicar posicion y dimensiones
		contentPane.add(btnMasaGrandeSG); // Añadir al panel
	}
	/**
	 * Metodo que hace azules los botones pulsados de las bases para ayudar al usuario a recordar su eleccion
	 * @param botonElegido Boton que se pondra azul cuando corresponda
	 */
	public void hacerBaseAzul(JButton botonElegido)
	{
		// Asegurarse que solo haya una masa azul, since solo puede elegirse una masa
		btnMasaPequena.setBackground(null);
		btnMasaGrande.setBackground(null);
		btnMasaPequenaSG.setBackground(null);
		btnMasaGrandeSG.setBackground(null);
		botonElegido.setBackground(azulSuave);
	}
	/**
	 * Metodo que hace azules los botones pulsados de los ingredientes para ayudar al usuario a recordar su eleccion
	 * @param botonElegido Boton que se pondra azul cuando corresponda
	 */
	public void hacerIngredienteAzul(JButton botonElegido)
	{
		// Aqui no hay que descolorear los otros ingredientes porque se pueden seleccionar varios
		botonElegido.setBackground(azulSuave);
	}
	
	// Getters/Setters
	/**
	 * Metodo que devuelve el boton Queso
	 * @return Boton de queso
	 */
	public JButton getBtnQueso()
	{
		return btnQueso;
	}
	/**
	 * Metodo que devuelve el boton Queso sin gluten
	 * @return Boton de queso sin gluten
	 */
	public JButton getBtnQuesoSinGluten()
	{
		return btnQuesoSinGluten;
	}
	/**
	 * Metodo que devuelve el boton Tomate
	 * @return Boton de tomate
	 */
	public JButton getBtnTomate()
	{
		return btnTomate;
	}
	/**
	 * Metodo que devuelve el boton Champinones
	 * @return Boton de champinones
	 */
	public JButton getBtnChampinones()
	{
		return btnChampinones;
	}
	/**
	 * Metodo que devuelve el boton Bacon
	 * @return Boton de bacon
	 */
	public JButton getBtnBacon()
	{
		return btnBacon;
	}
	/**
	 * Metodo que devuelve el boton Aceitunas
	 * @return Boton de aceitunas
	 */
	public JButton getBtnAceitunas()
	{
		return btnAceitunas;
	}
	/**
	 * Metodo que devuelve el boton Anchoas
	 * @return Boton de anchoas
	 */
	public JButton getBtnAnchoas()
	{
		return btnAnchoas;
	}
	/**
	 * Metodo que devuelve el boton Pimiento
	 * @return Boton de pimiento
	 */
	public JButton getBtnPimiento()
	{
		return btnPimiento;
	}
	/**
	 * Metodo que devuelve el boton York
	 * @return Boton de york
	 */
	public JButton getBtnYork()
	{
		return btnYork;
	}
	/**
	 * Metodo que devuelve el boton Serrano
	 * @return Boton de serrano
	 */
	public JButton getBtnSerrano()
	{
		return btnSerrano;
	}
	/**
	 * Metodo que devuelve el boton Cebolla
	 * @return Boton de cebolla
	 */
	public JButton getBtnCebolla()
	{
		return btnCebolla;
	}
	/**
	 * Metodo que devuelve el boton Cebolla caramelizada
	 * @return Boton de cebolla caramelizada
	 */
	public JButton getBtnCebollaCaram()
	{
		return btnCebollaCaram;
	}
	/**
	 * Metodo que devuelve el boton Pollo
	 * @return Boton de pollo
	 */
	public JButton getBtnPollo()
	{
		return btnPollo;
	}
	/**
	 * Metodo que devuelve el boton Pepperoni
	 * @return Boton de pepperoni
	 */
	public JButton getBtnPepperoni()
	{
		return btnPepperoni;
	}
	/**
	 * Metodo que devuelve el boton Maiz
	 * @return Boton de maiz
	 */
	public JButton getBtnMaiz()
	{
		return btnMaiz;
	}
	/**
	 * Metodo que devuelve el boton Atun
	 * @return Boton de atun
	 */
	public JButton getBtnAtun()
	{
		return btnAtun;
	}
	/**
	 * Metodo que devuelve el boton Pina
	 * @return Boton de pina
	 */
	public JButton getBtnPina()
	{
		return btnPina;
	}
	/**
	 * Metodo que devuelve el boton Masa Pequena
	 * @return Boton de masa pequena
	 */
	public JButton getBtnMasaPequena()
	{
		return btnMasaPequena;
	}
	/**
	 * Metodo que devuelve el boton Masa grande
	 * @return Boton de masa grande
	 */
	public JButton getBtnMasaGrande()
	{
		return btnMasaGrande;
	}
	/**
	 * Metodo que devuelve el boton Masa pequena sin gluten
	 * @return Boton de masa pequena sin gluten
	 */
	public JButton getBtnMasaPequenaSG()
	{
		return btnMasaPequenaSG;
	}
	/**
	 * Metodo que devuelve el boton Masa grande sin gluten
	 * @return Boton de masa grande sin gluten
	 */
	public JButton getBtnMasaGrandeSG()
	{
		return btnMasaGrandeSG;
	}
	/**
	 * Metodo que devuelve el boton para confirmar la pizza actual
	 * @return Boton de confirmar la pizza actual
	 */
	public JButton getBtnEnviarPizza()
	{
		return btnEnviarPizza;
	}
	/**
	 * Metodo que actualiza las instrucciones de la ventana
	 * @param texto Texto que quiere imprimirse como instrucciones
	 */
	public void setLabelInstrucciones(String texto)
	{
		labelIngredientes.setText(texto);
	}
}