package mvc;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class VentanaConfirmar extends JFrame
{
	// Atributos
	private JPanel contentPane;
	
	private JButton btnVolver;
	private JButton btnEnviar;
	
	private JTextField scanner;
		
	public VentanaConfirmar()
	{
		setTitle("Ventana Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 100, 700, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnVolver = new JButton("Volver al menú");
		btnVolver.setBounds(12, 30, 408, 25);
		contentPane.add(btnVolver);
		
		btnEnviar = new JButton("Confirmar entrega");
		btnEnviar.setBounds(12, 70, 408, 25);
		contentPane.add(btnEnviar);
		
		scanner = new JTextField();
		scanner.setBounds(12, 150, 408, 25);
		contentPane.add(scanner);
	}
	
	public JButton getBtnVolver()
	{
		return this.btnVolver;
	}
	
	public JButton getBtnEnviar()
	{
		return this.btnEnviar;
	}
	public String getScannerText()
	{
		return this.scanner.getText();
	}
}