package mvc;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class Ventana1 extends JFrame {
	// Atributos
	private JPanel contentPane;
	private JButton btnAbrirVentana;
	
	/**
	 * Create the frame.
	 */
	public Ventana1() {
		setTitle("Ventana 1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnAbrirVentana = new JButton("Abrir Ventana 2");
		btnAbrirVentana.setBounds(12, 13, 408, 25);
		contentPane.add(btnAbrirVentana);
	}

	// Getters/Setters
	public JButton getBtnAbrirVentana() {
		return btnAbrirVentana;
	}
	
	
}
