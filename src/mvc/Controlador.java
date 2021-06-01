package mvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import serializar.Clase2;

public class Controlador {
	//Atributos
	Ventana1 ventana1;
	Ventana2 ventana2;
	Clase2 modelo;
	
	// Constructor
	public Controlador(Ventana1 ventana1, Ventana2 ventana2, Clase2 modelo) {
		this.ventana1 = ventana1;
		this.ventana2 = ventana2;
		this.modelo = modelo;
		
		crearListeners();
		
		this.ventana1.setVisible(true);
	}
	
	private void crearListeners() {
		//---- Listeners Ventana1 ----//
		this.ventana1.getBtnAbrirVentana().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ventana1AbrirVentana();
			}
		});
		
		//---- Listeners Ventana2 ----//
		this.ventana2.getBtnNuevo().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ventana2Nuevo();
			}
		});
		
		this.ventana2.getBtnVolver().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ventana2Volver();
			}
		});
	}
	
	
	private void ventana1AbrirVentana() {
		this.ventana2.setVisible(true);
		this.ventana1.setVisible(false);
	}
	private void ventana2Nuevo() {
		// Interactuar con modelo y refrescar ventanas...
		System.out.println("Se interactúa con el modelo y se refrescan las ventanas...");
	}
	private void ventana2Volver() {
		this.ventana1.setVisible(true);
		this.ventana2.setVisible(false);
	}
}
