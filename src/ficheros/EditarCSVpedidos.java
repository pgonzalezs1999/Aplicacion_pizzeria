/**
 * Paquete que recoge todas las clases utilizadas para el manejo de datos en CSV
 * @author Pablo Gonzalez Sanchez <a href=mailto:pgonzalezs1999@gmail.com>pgonzalezs1999@gmail.com</a>
 */
package ficheros;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
/**
 * Clase que edita un CSV relacionado con algunas funciones especificas del CSV de pedidos
 */
public class EditarCSVpedidos
{
	private String[][] pedidos; // En esta matriz se almacenara provisionalmente el contenido del CSV
	String ruta; // Donde se encuentra el archivo
	
	//Constructor
	/**
	 * Metodo que define que archivo se va a manejar en una instancia de esta clase
	 * @param nuevaRuta Archivo que se va a gestionar
	 */
	public EditarCSVpedidos (String nuevaRuta)
	{
		this.ruta = nuevaRuta;
	}	
	/**
	 * Metodo que sube la matriz pedidos[][] al fichero CSV
	 */
	public void guardarCSV()
	{
		FileWriter fichero;
		try
		{
			fichero = new FileWriter(ruta);
			for (int i = 0; i < this.pedidos.length; i++) // Recorre las filas
			{
				for (int j = 0; j < this.pedidos[0].length; j++) // Recorre las columnas
				{
					fichero.write("" + this.pedidos[i][j]);  // Sobreescribe el valor de datos[][] en ese punto del CSV
					
					if (j != (this.pedidos[0].length - 1)) // Hay que poner "," entre cada valor excepto en el último
					{
						fichero.write(",");
					}
				}
				fichero.write("\n"); // Fin de la fila
			}
			fichero.close();
		}
		catch (IOException e)
		{
			System.out.println("ERROR ESCRITURA FICHERO: " + e.getMessage()); // No se muestra al usuario, pero nos ayuda para detectar errores
			e.printStackTrace();
		}
	}
	/**
	 * Metodo que trata de descargar a la matriz los datos del CSV a la matriz interna
	 * @return una comprobacion de si ha funcionado (true) o si ha habido algun fallo que ha impedido su carga (false)
	 */
	public boolean cargarCSV()
	{
		boolean resultado = false; // Se pondra true cuando se haya cargado la matriz completamente

		String[][] matriz; // matriz auxiliar que se trasladara a datos[][]
		String linea; // Linea por la que va recorriendo el CSV
		int fila = 0; // Empieza en 0
		int filas = contarLineasFichero(); // Numero de filas que tiene el CSV original
		int columnas = contarColumnasFichero(); // Numero de columnas que tiene el CSV original
		
		matriz = new String[filas][columnas]; // Reservamos espacio a la matriz
		
		for (int i = 0; i < matriz.length; i++) // Se ponen todos los elementos de matriz a ""
		{
			for (int j = 0; j < matriz[0].length; j++)
			{
				matriz[i][j] = "";
			}
		}
		
		try // Abrir el fichero para leerlo
		{
			Scanner fichero = new Scanner(new File(ruta)); // Lector de CSV
			while (fichero.hasNextLine())
			{
				linea = fichero.nextLine();	// Copia la linea actual			
				matriz[fila] = linea.split(","); // La divide por campos para poder almacenarla en la matriz
				fila++;
			}
			fichero.close();
			
			this.pedidos = matriz; // Se actualiza la matriz de datos CSV
			resultado = true; // Todo ha ido bien
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Ha habido un error leyendo la matriz de " + ruta); // No se muestra al usuario, pero nos ayuda para detectar errores
		}
		return resultado;
	}
	/**
	 * Metodo que averigua la fecha del momento en que se llama
	 * @return Fecha actual
	 */
	private static String fecha()
	{
        LocalDateTime FechaYHoraSinFormato = LocalDateTime.now(); // Averiguar la fecha en ese instante
        DateTimeFormatter FormatoDeFechaYHora = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Definir el formato
        String FechaYHora = FechaYHoraSinFormato.format(FormatoDeFechaYHora); // Recoger la fecha como cadena de caracteres
        return FechaYHora;
    }
	/**
	 * Metodo que averigua la hora del momento en que se llama
	 * @return Fecha actual
	 */
	private static String hora()
	{
        LocalDateTime FechaYHoraSinFormato = LocalDateTime.now(); // Averiguar la hora en ese instante
        DateTimeFormatter FormatoDeFechaYHora = DateTimeFormatter.ofPattern("HH:mm"); // Definir el formato
        String FechaYHora = FechaYHoraSinFormato.format(FormatoDeFechaYHora); // Recoger la hora como cadena de caracteres
        return FechaYHora;
    }
	/**
	 * Metodo que genera un nuevo pedido con sus correspondientes datos
	 */
	public void addPedido ()
	{
		String[][] nuevospedidos = new String[this.pedidos.length+1][this.pedidos[0].length]; // Crear una matriz auxiliar con una fila mas para el nuevo pedido
		int entregado = 0; // Al enviarse tiene que estar obligatoriamente no entregado
		
		for (int i = 1; i < nuevospedidos.length; i++) // Se salta la primera linea porque es donde ira el nuevo pedido
		{
			nuevospedidos[i] = this.pedidos[i-1]; // Copia el resto igual en su nueva posicion
		}		
		this.pedidos = nuevospedidos; // Pasa lo que lleva de matriz auxiliar a la matriz local
		
		int suma = Integer.parseInt(pedidos[1][0]) + 1; // El ID mayor del fichero sera el ultimo emitido, entonces sumar uno a este
		
		pedidos[0][0] = String.valueOf(suma); // Establecer ID
		pedidos[0][1] = fecha(); // Establecer fecha
		pedidos[0][2] = hora(); // Establecer hora
		pedidos[0][3] = String.valueOf(entregado); // Establecer como no entregado (no other way)
		
		this.guardarCSV(); // Grabar en el fichero CSV
	}
	/**
	 * Cuenta las filas del fichero CSV, funcion auxiliar para otras funciones
	 * @return El numero de filas
	 */
	public int contarLineasFichero()
	{
		int lineas = 0;		
		try // Usamos Scanner para contar las lineas
		{
			Scanner fichero = new Scanner(new File(this.ruta));
			while (fichero.hasNextLine())
			{
				lineas++;
				fichero.nextLine();
			}
			fichero.close();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Ha habido un error contando las lineas de " + ruta); // No se muestra al usuario, pero nos ayuda para detectar errores
		}
		return lineas;
	}
	/**
	 * Cuenta las columnas del fichero CSV, funcion auxiliar para otras funciones
	 * @return El numero de columnas
	 */
	public int contarColumnasFichero()
	{
		int columnas = 0;
		String primeraLinea = ""; // El contenido de toda la fila
		
		try // Usamos Scanner para contar las lineas
		{
			Scanner fichero = new Scanner(new File(this.ruta));
			if (fichero.hasNextLine())
			{
				primeraLinea = fichero.nextLine();
			}
			fichero.close();
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Ha habido un error contando las columnas de " + ruta); // No se muestra al usuario, pero nos ayuda para detectar errores
		}
		columnas = primeraLinea.split(",").length;

		if(columnas == 0) // Si no tiene columnas
		{
			columnas = primeraLinea.lastIndexOf(',')+2; // Posición del último "," 
		}
		return columnas;
	}
}