package mvc;

import java.awt.Component;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class VentanaConfirmar extends JFrame
{
	// Atributos
	private JPanel contentPane;
	
	private JButton btnVolver;
	private JButton btnConfirmar;
	
	private JTextField scanner;
	
	private JLabel labelInstrucciones;
	
	Vector<JLabel> labelsBorrar = new Vector<JLabel>();
		
	public VentanaConfirmar()
	{
		setTitle("Ventana Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 100, 500, 600);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnVolver = new JButton("Volver al menú");
		btnVolver.setBounds(12, 100, 408, 25);
		contentPane.add(btnVolver);
		
		btnConfirmar = new JButton("Confirmar entrega");
		btnConfirmar.setBounds(260, 60, 160, 25);
		contentPane.add(btnConfirmar);
		
		scanner = new JTextField();
		scanner.setBounds(12, 60, 230, 25);
		contentPane.add(scanner);
		
		labelInstrucciones = new JLabel();
		labelInstrucciones.setText("Introduce el ID del pedido que desea confirmar:");
		labelInstrucciones.setBounds(12, 30, 408, 25);
		contentPane.add(labelInstrucciones);		
	}
	
	public void crearLabel(int posX, int posY, int ancho, int alto, String texto)
	{
		JLabel labelAux = new JLabel();
		labelAux.setBounds(posX, posY, ancho, alto);
		labelAux.setText(texto);
		contentPane.add(labelAux);
		labelsBorrar.add(labelAux);
	}
	
	public JButton getBtnVolver()
	{
		return this.btnVolver;
	}	
	public JButton getBtnConfirmar()
	{
		return this.btnConfirmar;
	}
	public String getScannerText()
	{
		return this.scanner.getText();
	}
	public void setInstruccionesText(String nuevoTexto)
	{
		this.labelInstrucciones.setText(nuevoTexto);
	}
	public Vector<JLabel> getLabelsBorrar()
	{
		return this.labelsBorrar;
	}
}