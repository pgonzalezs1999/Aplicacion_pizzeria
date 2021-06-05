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
		setBounds(250, 100, 700, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		labelIngredientes = new JLabel("Pulse el tama�o que desea para su pizza");
		labelIngredientes.setBounds(12, 30, 638, 25);
		contentPane.add(labelIngredientes);
		
		labelIngredientes = new JLabel("Pulse los ingredientes que desea a�adir");
		labelIngredientes.setBounds(12, 110, 638, 25);
		contentPane.add(labelIngredientes);
		
		btnEnviarPizza = new JButton("Enviar Pizza");
		btnEnviarPizza.setBounds(12, 510, 638, 25);
		btnEnviarPizza.setBackground(verdeSuave);
		contentPane.add(btnEnviarPizza);
		
		btnQueso = new JButton("Queso");
		btnQueso.setBounds(12, 150, 300, 25);
		contentPane.add(btnQueso);
		
		btnQuesoSinGluten = new JButton("Queso sin gl�ten");
		btnQuesoSinGluten.setBounds(12, 190, 300, 25);
		contentPane.add(btnQuesoSinGluten);
		
		btnTomate = new JButton("Tomate");
		btnTomate.setBounds(12, 230, 300, 25);
		contentPane.add(btnTomate);
		
		btnChampinones = new JButton("Champi�ones");
		btnChampinones.setBounds(12, 270, 300, 25);
		contentPane.add(btnChampinones);
		
		btnBacon = new JButton("Bacon");
		btnBacon.setBounds(12, 310, 300, 25);
		contentPane.add(btnBacon);
		
		btnAceitunas = new JButton("Aceitunas");
		btnAceitunas.setBounds(12, 350, 300, 25);
		contentPane.add(btnAceitunas);
		
		btnAnchoas = new JButton("Anchoas");
		btnAnchoas.setBounds(12, 390, 300, 25);
		contentPane.add(btnAnchoas);
		
		btnPimiento = new JButton("Pimiento");
		btnPimiento.setBounds(12, 430, 300, 25);
		contentPane.add(btnPimiento);
		
		btnYork = new JButton("York");
		btnYork.setBounds(12, 470, 300, 25);
		contentPane.add(btnYork);
		
		btnSerrano = new JButton("Serrano");
		btnSerrano.setBounds(350, 150, 300, 25);
		contentPane.add(btnSerrano);
		
		btnCebolla = new JButton("Cebolla");
		btnCebolla.setBounds(350, 190, 300, 25);
		contentPane.add(btnCebolla);
		
		btnCebollaCaram = new JButton("Cebolla caramelizada");
		btnCebollaCaram.setBounds(350, 230, 300, 25);
		contentPane.add(btnCebollaCaram);
		
		btnPollo = new JButton("Pollo");
		btnPollo.setBounds(350, 270, 300, 25);
		contentPane.add(btnPollo);
		
		btnPepperoni = new JButton("Pepperoni");
		btnPepperoni.setBounds(350, 310, 300, 25);
		contentPane.add(btnPepperoni);
		
		btnMaiz = new JButton("Maiz");
		btnMaiz.setBounds(350, 350, 300, 25);
		contentPane.add(btnMaiz);
		
		btnAtun = new JButton("At�n");
		btnAtun.setBounds(350, 390, 300, 25);
		contentPane.add(btnAtun);
		
		btnPina = new JButton("Pi�a");
		btnPina.setBounds(350, 430, 300, 25);
		contentPane.add(btnPina);
		
		btnMasaPequena = new JButton("Peque�a");
		btnMasaPequena.setBounds(12, 70, 150, 25);
		contentPane.add(btnMasaPequena);
		
		btnMasaGrande = new JButton("Grande");
		btnMasaGrande.setBounds(177, 70, 150, 25);
		contentPane.add(btnMasaGrande);
		
		btnMasaPequenaSG = new JButton("Peque�a sin gl�ten");
		btnMasaPequenaSG.setBounds(342, 70, 150, 25);
		contentPane.add(btnMasaPequenaSG);
		
		btnMasaGrandeSG = new JButton("Grande sin gl�ten");
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
	
	public void resetearVentana()
	{
		btnQueso.setBackground(null);
		btnQuesoSinGluten.setBackground(null);
		btnTomate.setBackground(null);
		btnChampinones.setBackground(null);
		btnBacon.setBackground(null);
		btnAceitunas.setBackground(null);
		btnAnchoas.setBackground(null);
		btnPimiento.setBackground(null);
		btnYork.setBackground(null);
		btnSerrano.setBackground(null);
		btnCebolla.setBackground(null);
		btnCebollaCaram.setBackground(null);
		btnPollo.setBackground(null);
		btnPepperoni.setBackground(null);
		btnMaiz.setBackground(null);
		btnAtun.setBackground(null);
		btnPina.setBackground(null);
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