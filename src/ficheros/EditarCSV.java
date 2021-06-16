/**
 * Paquete que recoge todas las clases utilizadas para el manejo de datos en CSV
 * @author Pablo Gonzalez Sanchez <a href=mailto:pgonzalezs1999@gmail.com>pgonzalezs1999@gmail.com</a>
 */
package ficheros;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;
import modelo.Orden;
import modelo.Pizza;
/**
 * Clase que edita un CSV y muestra el contenido del mismo
 */
public class EditarCSV
{
	private String[][] datos; // En esta matriz se almacenara provisionalmente el contenido del CSV
	String ruta; // Donde se encuentra el archivo
	/**
	 * Metodo que define que archivo se va a manejar en una instancia de esta clase
	 */
	// Constructor
	public EditarCSV(String nuevaRuta)
	{
		this.ruta = nuevaRuta; // El archivo se encuentra en la ruta recibida por el parametro
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
			
			this.datos = matriz; // Se actualiza la matriz de datos CSV
			resultado = true; // Todo ha ido bien
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Ha habido un error leyendo la matriz de " + ruta); // No se muestra al usuario, pero nos ayuda para detectar errores
		}
		return resultado;
	}
	/**
	 * Metodo que sube la matriz datos[][] al fichero CSV
	 */
	public void guardarCSV()
	{
		FileWriter fichero;
		try
		{
			fichero = new FileWriter(ruta);
			for (int i = 0; i < this.datos.length; i++) // Recorre las filas
			{
				for (int j = 0; j < this.datos[0].length; j++) // Recorre las columnas
				{
					fichero.write("" + this.datos[i][j]);  // Sobreescribe el valor de datos[][] en ese punto del CSV
					
					if (j != (this.datos[0].length - 1)) // Hay que poner "," entre cada valor excepto en el último
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
	 * Metodo que indica un pedido como confirmado, es decir, que se ha entregado al domicilio
	 * @param numID ID del pedido a confirmar
	 */
	public void confirmar(int numID)
	{
		for(int i = 0; i < contarLineasFichero(); i++) // Recorre las filas de la matriz
		{
			if(datos[i][0].equals(String.valueOf(numID))) // Si la primera columna de esa fila es 0 (aparece como NO entregado)
			{
				datos[i][3] = "1"; // Lo cambia a 1 (aparece como entregado)
			}
		}
		guardarCSV(); // Para que tambien se suba el cambio al CSV
	}
	/**
	 * Metodo que devuelve las pizzas que pertenecen a un pedido concreto
	 * @param pedidoID ID del pedido que se quieren conocer sus pizzas
	 * @return lista de los nombres en las que se encuentran las pizzas del pedido indicado
	 */
	public Vector<String> buscarPizzasPorPedido(int pedidoID)
	{
		Vector<String> resultados = new Vector<String>(); // Recoge el nombre de cada pizza que cumple las condiciones
		
		for(int i = 0; i < datos.length; i++) // Recorre las filas
		{
			if(datos[i][0].equals(String.valueOf(pedidoID))) // Si el pedido de la pizza coincide con el pedido indicado
			{
				resultados.add(datos[i][1]); // Añade el nombre de esa pizza al vector 
			}
		}	
		return resultados;
	}
	/**
	 * Metodo que modifica un valor concreto del CSV
	 * @param fila fila en la que se encuentra el dato
	 * @param columna columna en la que se encuentra el dato
	 * @param nuevoValor Dato que desea sobreescribir en la posicion indicada
	 */
	public void modificarDato(int fila, int columna, String nuevoValor)
	{
		datos[fila][columna] = nuevoValor; // Reemplazar el dato en la matriz local
		guardarCSV(); // Actualizar el cambio en el CSV
	}
	/**
	 * Metodo que anade una pizza al CSV
	 * @param orden Orden a la que pertenece la pizza
	 * @param pizza Pizza que se desea anadir
	 */
	public void addPizza(Orden orden, Pizza pizza) // Introduce una fila con el ID de su orden
	{
		String[][] nuevosDatos = new String[this.datos.length+1][this.datos[0].length]; // Se reserva espacio para una matriz con una fila más		
		String[] listaIngr = new String[] {"queso", "queso sin gluten", "tomate", "champinones", "bacon", "aceitunas", "anchoas", "pimiento", "york", "serrano", "cebolla", "cebolla caramelizada", "pollo", "pepperoni", "maiz", "atun", "pina"};
		
		for (int i = 1; i < nuevosDatos.length; i++) // Se recorre esa matriz asignando los valores de la anterior
		{
			nuevosDatos[i] = this.datos[i-1]; // Se copia toda la fila, aplicando corrección
		}
		this.datos = nuevosDatos; // Graba los datos de la matriz auxiliar en la matriz original
		
		datos[0][0] = String.valueOf(orden.getID()); // Guarda el ID del pedido
		datos[0][1] = pizza.getNombre(); // Guarda el nombre de la pizza
		datos[0][2] = pizza.getBase().getTipo(); // Guarda la base utilizada para la pizza
		
		for(int j = 0; j < listaIngr.length; j++) // Recorre la lista de ingredientes
		{
			nuevosDatos[0][j+3] = "0"; // Para que ignore el ID del pedido, el nombre y el tipo de base
			for(int k = 0; k < pizza.getIngredientes().size(); k++) // Recorre la fila de la pizza en la matriz
			{				
				if(pizza.getIngredientes().get(k).getNombre().equals(listaIngr[j]) == true)
				{
					datos[0][j+3] = "1"; // Si coinciden, añadir ingrediente a la pizza
				}
			}
		}
		this.guardarCSV(); // Actualizar el cambio en el CSV
	}
	/**
	 * Busca el primer numero entero disponible y lo asigna como ID a un pedido
	 * @return el ID generado para el pedido
	 */
	public int generarID()
	{
		int resultado = 0; // Si no existe ningun pedido, se le asigna 0
		
		for(int i = 0; i < datos.length; i++) // Recorre las filas de la matriz
		{
			if(Integer.parseInt(datos[i][0]) > resultado) // Si el ID de la iteracion es el mas grande encontrado
			{
				resultado = Integer.parseInt(datos[i][0]); // El ID provisional sera el encontrado
			}
		}	
		return resultado + 1;
	}
	/**
	 * Elimina una fila especificada del fichero CSV
	 * @param orden El numero de la fila a eliminar
	 */
	public void delFila(int orden) // Elimina la fila en la posición orden
	{
		if (orden >= 0 && orden < (this.datos.length)) // Para que no se puedan introducir datos fuera de rango
		{
			String[][] nuevosDatos = new String[this.datos.length - 1][this.datos[0].length]; // Se reserva espacio para una matriz con una fila MENOS
			int correccionFila = 0; // Se usa para corregir la fila de lectura
			
			for (int i = 0; i < this.datos.length; i++) // Se recorre la matriz asignando los valores de la anterior
			{
				if (i == orden) // Se localiza si estamos en la fila deseada...
				{
					correccionFila = -1; // No se copian los datos y se corrije a la fila anterior
				}
				else // Se copia toda la fila, aplicando corrección
				{
					nuevosDatos[i + correccionFila] = this.datos[i];
				}
			}
			this.datos = nuevosDatos; // Se actualiza la matriz local con la matriz auxiliar
			this.guardarCSV(); // Se actualiza el fichero CSV con los datos de la matriz local
		}
		else // Puede darse el caso de quedar una matriz con 0 filas y algunas columnas...
		{
			System.out.println("ERROR(delFila) - Número para la fila a eliminar incorrecto."); // No se muestra al usuario, pero nos ayuda para detectar errores
		}
	}
	/**
	 * Elimina la ultima pizza anadida al pedido en curso
	 * @param ordenActual El pedido en curso
	 */
	public void eliminarUltimaPizza(Orden ordenActual)
	{
		boolean eliminada = false; // No se elimina la pizza una pizza a no ser que...
		
		for(int i = 0; i < datos.length; i++) // Recorre las filas de la matriz
		{
			if(datos[i][0].equals(String.valueOf(ordenActual.getID())) && eliminada == false) // Si la fila (pizza) tiene el mismo ID que ordenActual y aun no se ha eliminado
			{
				delFila(i); // Borra la pizza del vector pizzas de la orden en curso
				eliminada = true; // Checkeo para que no elimine varias pizzas una sola llamada a la funcion
			}
		}
	}
	/**
	 * Devuelve una lista de todos los pedidos aun no confirmados
	 * @return Una lista con los ID de todos los pedidos sin entregar
	 */
	public Vector<String> pedidosSinConfirmar() // Devuelve los ID necesarios para buscarlos en el otro CSV
	{
		Vector<String> pedidos = new Vector<String>(); // El vector que los almacenara
		
		for(int i = 0; i < datos.length; i++) // Recorre la matriz interna
		{
			if(datos[i][3].equals("0")) // Si el campo [3] esta a 0, ese pedido aun no esta confirmado
			{
				pedidos.add(datos[i][0]); // Añadirlo al vector de IDs
			}
		}		
		return pedidos;
	}
	/**
	 * Devuelve una lista de todos los pedidos, formateados para ser comprensibles por el usuario
	 * @return Una lista con la informacion clave de cada pedido
	 */
	public Vector<String> mostrarRegistro()
	{
		Vector<String> pedidos = new Vector<String>(); // El vector que los almacenara
		
		for(int i = 0; i < datos.length; i++) // Recorre la matriz interna
		{
			String entregado = ""; // Un diferenciador de cuales pedidos se han entregado y cuales no
			if(datos[i][3].equals("0")) // Si no se ha entregado
			{
				entregado = " no"; // Se escribira "entregado" o "no entregado", el diferenciador es esta string 
			}
			String nuevoTexto = "Pedido " + datos[i][0] + ": emitido el " + datos[i][1] + " a las " + datos[i][2] + ". Estado:" + entregado + " entregado";
			pedidos.add(nuevoTexto); // Añadir el texto al vector que se devuelve
		}
		
		return pedidos;
	}
	/**
	 * Dado que las pizzas se graban en el fichero antes de enviar el pedido, esta funcion elimina todas sus pizzas si se cancela el pedido
	 * @param nuevaOrden El pedido que se desea cancelar
	 */
	public void cancelarOrden(Orden nuevaOrden)
	{
		for(int i = 0; i < datos.length; i++) // Recorre la matriz interna
		{
			if(datos[i][0].equals(String.valueOf(nuevaOrden.getID())) == true) // Si la pizza iterada coincide en ID con nuevaOrden
			{
				delFila(i); // Elimina la pizza de la matriz interna
				i--; // Correccion para que no se salte la mitad de las pizzas
			}
		}
		guardarCSV(); // Grabar en el fichero CSV los datos de la matriz interna
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