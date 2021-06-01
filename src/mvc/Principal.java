package mvc;

import java.util.Date;
import java.util.Vector;

import serializar.Clase1;
import serializar.Clase2;

public class Principal {

	public static void main(String[] args) {

		Clase2 modelo = new Clase2(new Vector<Clase1>(), new Date(), "Mi gestor");
		
				
		// Vistas
		Ventana1 ventana1 = new Ventana1();
		Ventana2 ventana2 = new Ventana2();
		
		Controlador controlador = new Controlador(ventana1, ventana2, modelo);
	}

}
