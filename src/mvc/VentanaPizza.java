package mvc;

//import java.awt.BorderLayout;
//import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
//import java.awt.event.ActionListener;
//import java.awt.event.ActionEvent;

public class VentanaPizza extends JFrame
{
	// Atributos
	private JPanel contentPane;
	private JButton btnVolver;
	
	/**
	 * Create the frame.
	 */
	public VentanaPizza()
	{
		setTitle("Ventana Pedido");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(250, 100, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnVolver = new JButton("Volver a Ventana Principal");
		btnVolver.setBounds(12, 215, 408, 25);
		contentPane.add(btnVolver);
	}
	
	// Getters/Setters
	public JButton getBtnVolver()
	{
		return btnVolver;
	}
}