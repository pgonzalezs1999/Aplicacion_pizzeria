package mvc;

//import java.awt.BorderLayout;
//import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.util.Vector;

import javax.swing.JButton;
//import java.awt.event.ActionListener;
//import java.awt.event.ActionEvent;

public class VentanaHistorial extends JFrame
{
	// Atributos
	private JPanel contentPane;
	private JButton btnVolver;
	
	Vector<JLabel> labelsBorrar = new Vector<JLabel>();
	
	/**
	 * Create the frame.
	 */
	public VentanaHistorial()
	{
		setTitle("Ventana Historial");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 100, 450, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnVolver = new JButton("Volver a Ventana Principal");
		btnVolver.setBounds(12, 20, 408, 25);
		contentPane.add(btnVolver);
	}
	
	public void crearLabel(int posX, int posY, int ancho, int alto, String texto)
	{
		JLabel labelAux = new JLabel();
		labelAux.setBounds(posX, posY, ancho, alto);
		labelAux.setText(texto);
		contentPane.add(labelAux);
		labelsBorrar.add(labelAux);
	}
	
	// Getters/Setters
	public JButton getBtnVolver()
	{
		return btnVolver;
	}
	public Vector<JLabel> getLabelsBorrar()
	{
		return this.labelsBorrar;
	}
}