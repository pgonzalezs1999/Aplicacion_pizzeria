package mvc;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;

public class VentanaPizza extends JFrame
{
	private JPanel contentPane;	
	
	private JLabel labelIngredientes;
	private JLabel labelBases;
	
	private JButton btnEnviarPizza;

	private JButton btnQueso; // Orden en que se guardan en la matriz
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
	
	private Color verdeSuave = new Color(200, 255, 200);
	private Color azulSuave = new Color(200, 200, 255);
	
	/**
	 * Create the frame.
	 */
	public VentanaPizza()
	{
		setTitle("Ventana Pizza");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 100, 685, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		labelIngredientes = new JLabel("Pulse el tamaño que desea para su pizza");
		labelIngredientes.setBounds(12, 30, 638, 25);
		contentPane.add(labelIngredientes);
		
		labelIngredientes = new JLabel("Pulse los ingredientes que desea añadir");
		labelIngredientes.setBounds(12, 110, 638, 25);
		contentPane.add(labelIngredientes);
		
		btnEnviarPizza = new JButton("Enviar Pizza");
		btnEnviarPizza.setBounds(12, 510, 638, 25);
		btnEnviarPizza.setBackground(verdeSuave);
		contentPane.add(btnEnviarPizza);
		
		btnQueso = new JButton("Queso (0.60€)");
		btnQueso.setBounds(12, 150, 300, 25);
		contentPane.add(btnQueso);
		
		btnQuesoSinGluten = new JButton("Queso sin glúten (0.90€)");
		btnQuesoSinGluten.setBounds(12, 190, 300, 25);
		contentPane.add(btnQuesoSinGluten);
		
		btnTomate = new JButton("Tomate (0.65€)");
		btnTomate.setBounds(12, 230, 300, 25);
		contentPane.add(btnTomate);
		
		btnChampinones = new JButton("Champiñones (0.75€)");
		btnChampinones.setBounds(12, 270, 300, 25);
		contentPane.add(btnChampinones);
		
		btnBacon = new JButton("Bacon (1.25€)");
		btnBacon.setBounds(12, 310, 300, 25);
		contentPane.add(btnBacon);
		
		btnAceitunas = new JButton("Aceitunas (0.55€)");
		btnAceitunas.setBounds(12, 350, 300, 25);
		contentPane.add(btnAceitunas);
		
		btnAnchoas = new JButton("Anchoas (0.50€)");
		btnAnchoas.setBounds(12, 390, 300, 25);
		contentPane.add(btnAnchoas);
		
		btnPimiento = new JButton("Pimiento (0.60€)");
		btnPimiento.setBounds(12, 430, 300, 25);
		contentPane.add(btnPimiento);
		
		btnYork = new JButton("York (0.75€)");
		btnYork.setBounds(12, 470, 300, 25);
		contentPane.add(btnYork);
		
		btnSerrano = new JButton("Serrano (1.20€)");
		btnSerrano.setBounds(350, 150, 300, 25);
		contentPane.add(btnSerrano);
		
		btnCebolla = new JButton("Cebolla (0.30€)");
		btnCebolla.setBounds(350, 190, 300, 25);
		contentPane.add(btnCebolla);
		
		btnCebollaCaram = new JButton("Cebolla caramelizada (0.60€)");
		btnCebollaCaram.setBounds(350, 230, 300, 25);
		contentPane.add(btnCebollaCaram);
		
		btnPollo = new JButton("Pollo (0.45€)");
		btnPollo.setBounds(350, 270, 300, 25);
		contentPane.add(btnPollo);
		
		btnPepperoni = new JButton("Pepperoni (0.85€)");
		btnPepperoni.setBounds(350, 310, 300, 25);
		contentPane.add(btnPepperoni);
		
		btnMaiz = new JButton("Maiz (0.30€)");
		btnMaiz.setBounds(350, 350, 300, 25);
		contentPane.add(btnMaiz);
		
		btnAtun = new JButton("Atún (0.65€)");
		btnAtun.setBounds(350, 390, 300, 25);
		contentPane.add(btnAtun);
		
		btnPina = new JButton("Piña (0.80€)");
		btnPina.setBounds(350, 430, 300, 25);
		contentPane.add(btnPina);
		
		btnMasaPequena = new JButton("Pequeña");
		btnMasaPequena.setBounds(12, 70, 150, 25);
		contentPane.add(btnMasaPequena);
		
		btnMasaGrande = new JButton("Grande");
		btnMasaGrande.setBounds(177, 70, 150, 25);
		contentPane.add(btnMasaGrande);
		
		btnMasaPequenaSG = new JButton("Pequeña sin glúten");
		btnMasaPequenaSG.setBounds(342, 70, 150, 25);
		contentPane.add(btnMasaPequenaSG);
		
		btnMasaGrandeSG = new JButton("Grande sin glúten");
		btnMasaGrandeSG.setBounds(507, 70, 150, 25);
		contentPane.add(btnMasaGrandeSG);
	}
	
	public void hacerBaseAzul(JButton botonElegido)
	{
		btnMasaPequena.setBackground(null);
		btnMasaGrande.setBackground(null);
		btnMasaPequenaSG.setBackground(null);
		btnMasaGrandeSG.setBackground(null);
		botonElegido.setBackground(azulSuave);
	}

	public void hacerIngredienteAzul(JButton botonElegido)
	{
		botonElegido.setBackground(azulSuave);
	}
	
	// Getters/Setters
	public JButton getBtnQueso()
	{
		return btnQueso;
	}
	
	public JButton getBtnQuesoSinGluten()
	{
		return btnQuesoSinGluten;
	}
	
	public JButton getBtnTomate()
	{
		return btnTomate;
	}
	
	public JButton getBtnChampinones()
	{
		return btnChampinones;
	}
	
	public JButton getBtnBacon()
	{
		return btnBacon;
	}
	
	public JButton getBtnAceitunas()
	{
		return btnAceitunas;
	}
	
	public JButton getBtnAnchoas()
	{
		return btnAnchoas;
	}
	
	public JButton getBtnPimiento()
	{
		return btnPimiento;
	}
	
	public JButton getBtnYork()
	{
		return btnYork;
	}
	
	public JButton getBtnSerrano()
	{
		return btnSerrano;
	}
	
	public JButton getBtnCebolla()
	{
		return btnCebolla;
	}
	
	public JButton getBtnCebollaCaram()
	{
		return btnCebollaCaram;
	}
	
	public JButton getBtnPollo()
	{
		return btnPollo;
	}
	
	public JButton getBtnPepperoni()
	{
		return btnPepperoni;
	}
	
	public JButton getBtnMaiz()
	{
		return btnMaiz;
	}
	
	public JButton getBtnAtun()
	{
		return btnAtun;
	}
	
	public JButton getBtnPina()
	{
		return btnPina;
	}
	
	public JButton getBtnMasaPequena()
	{
		return btnMasaPequena;
	}
	
	public JButton getBtnMasaGrande()
	{
		return btnMasaGrande;
	}
	
	public JButton getBtnMasaPequenaSG()
	{
		return btnMasaPequenaSG;
	}
	
	public JButton getBtnMasaGrandeSG()
	{
		return btnMasaGrandeSG;
	}
	
	public JButton getBtnEnviarPizza()
	{
		return btnEnviarPizza;
	}
	
	public void setLabelInstrucciones(String texto)
	{
		labelIngredientes.setText(texto);
	}
}