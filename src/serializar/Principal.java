package serializar;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.Vector;

public class Principal {
	public static void main(String[] args) {

		// Se crean 3 datos de la Clase1
		Clase1 dato1 = new Clase1(1, "Dato1", "Detalles1", 1.56);
		Clase1 dato2 = new Clase1(2, "Dato2", "Detalles2", 2.56);
		Clase1 dato3 = new Clase1(3, "Dato3", "Detalles3", 3.56);
		
		// Se crea un Vector y se guardan los 3 datos de Clase1
		Vector<Clase1> vector = new Vector<Clase1>();
		vector.add(dato1);
		vector.add(dato2);
		vector.add(dato3);
		
		// Se crea 1 gestor de la Clase2
		Clase2 gestor = new Clase2(null, new Date(), "Mi gestor de datos");
		
		// Escribir un objeto en un fichero
		try {
			FileOutputStream fos = new FileOutputStream("matriz.dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(gestor);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Leer de un fichero un objeto
		try {
			FileInputStream fis = new FileInputStream("matriz.dat"); 
			ObjectInputStream ois = new ObjectInputStream(fis); 
			Clase2 ges = (Clase2) ois.readObject();

			System.out.println("Pizzas Cargadas:" + ges.getColeccion().size());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
