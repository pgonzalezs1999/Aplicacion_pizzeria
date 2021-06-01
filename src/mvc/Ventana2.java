package mvc;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ventana2 extends JFrame {
	// Atributos
	private JPanel contentPane;
	private JButton btnNuevo;
	private JButton btnVolver;
	
	/**
	 * Create the frame.
	 */
	public Ventana2() {
		setTitle("Ventana 2");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnNuevo = new JButton("Nuevo");
		btnNuevo.setBounds(12, 13, 408, 25);
		contentPane.add(btnNuevo);
		
		btnVolver = new JButton("Volver a Ventana 1");
		btnVolver.setBounds(12, 215, 408, 25);
		contentPane.add(btnVolver);
	}
	
	// Getters/Setters
	public JButton getBtnNuevo() {
		return btnNuevo;
	}
	public JButton getBtnVolver() {
		return btnVolver;
	}
}
