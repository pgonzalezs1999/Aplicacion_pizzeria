package ficheros;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import modelo.Orden;
import modelo.Pizza;

public class EditarCSV
{
	private String[][] datos;
	String ruta;
	
	public EditarCSV(String nuevaRuta)
	{
		this.ruta = nuevaRuta;
	}
	
	public boolean cargarCSV()
	{
		boolean resultado = false;

		String[][] matriz;
		String linea;
		int fila = 0;
		int filas = contarLineasFichero();
		int columnas = contarColumnasFichero();
		
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
			Scanner fichero = new Scanner(new File(ruta));
			while (fichero.hasNextLine())
			{
				linea = fichero.nextLine();				
				matriz[fila] = linea.split(","); // fila tiene los elementos en tipo String
				fila++;
			}
			fichero.close();
			
			this.datos = matriz; // Se actualiza la matriz de datos CSV
			resultado = true; // Todo ha ido bien
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Ha habido un error leyendo la matriz de " + ruta);
		}
		return resultado;
	}

	public void guardarCSV()
	{
		FileWriter fichero;
		try
		{
			fichero = new FileWriter(ruta);
			for (int i = 0; i < this.datos.length; i++)
			{
				for (int j = 0; j < this.datos[0].length; j++)
				{
					fichero.write("" + this.datos[i][j]); 
					
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
			System.out.println("ERROR ESCRITURA FICHERO: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void modificarDato(int fila, int columna, String nuevoValor)
	{
		datos[fila][columna] = nuevoValor;
		guardarCSV();
	}

	public void addPizza(Orden orden, Pizza pizza) // Introduce una fila en la posición orden
	{
		String[][] nuevosDatos = new String[this.datos.length+1][this.datos[0].length]; // Se reserva espacio para una matriz con una fila más		
		String[] listaIngr = new String[] {"queso", "queso sin gluten", "tomate", "champinones", "bacon", "aceitunas", "anchoas", "pimiento", "york", "serrano", "cebolla", "cebolla caramelizada", "pollo", "pepperoni", "maiz", "atun", "pina"};
		
		for (int i = 1; i < nuevosDatos.length; i++) // Se recorre esa matriz asignando los valores de la anterior
		{
			nuevosDatos[i] = this.datos[i-1]; // Se copia toda la fila, aplicando corrección
		}
		this.datos = nuevosDatos;
		
		datos[0][0] = String.valueOf(orden.getID());
		datos[0][1] = pizza.getNombre();
		datos[0][2] = pizza.getBase().getTipo();
		
		for(int j = 0; j < listaIngr.length; j++)
		{
			nuevosDatos[0][j+3] = "0";
			for(int k = 0; k < pizza.getIngredientes().size(); k++)
			{				
				if(pizza.getIngredientes().get(k).getNombre().equals(listaIngr[j]) == true)
				{
					datos[0][j+3] = "1";
				}				
			}
		}
		this.guardarCSV();
	}
	
	public int generarID()
	{
		int resultado = 0;
		
		for(int i = 0; i < datos.length; i++)
		{
			if(Integer.parseInt(datos[i][0]) > resultado)
			{
				resultado = Integer.parseInt(datos[i][0]);
			}
		}
		
		return resultado + 1;
	}

	public void delFila(int orden) // Elimina la fila en la posición orden
	{
		if (orden >= 0 && orden < (this.datos.length))
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
			this.datos = nuevosDatos;
			this.guardarCSV();
		}
		else // Puede darse el caso de quedar una matriz con 0 filas y algunas columnas...
		{
			System.out.println("ERROR(delFila) - Número para la fila a eliminar incorrecto.");
		}
	}

	public void eliminarUltimaPizza(Orden ordenActual)
	{
		boolean eliminada = false;
		
		for(int i = 0; i < datos.length; i++)
		{
			if(datos[i][0].equals(String.valueOf(ordenActual.getID())) && eliminada == false)
			{
				delFila(i);
				eliminada = true;
			}
		}
	}
	
	public Vector<String> pedidosSinConfirmar() // Devuelve los ID necesarios para buscarlos en el otro CSV
	{
		Vector<String> pedidos = new Vector<String>();
		
		for(int i = 0; i < datos.length; i++)
		{
			if(datos[i][3].equals("0"))
			{
				pedidos.add(datos[i][0]);
			}
		}
		
		return pedidos;
	}
	
	public void cancelarOrden(Orden nuevaOrden)
	{
		for(int i = 0; i < datos.length; i++)
		{
			if(datos[i][0].equals(String.valueOf(nuevaOrden.getID())) == true)
			{
				delFila(i);
				i--;
			}
		}
	}
	
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
			System.out.println("Ha habido un error contando las lineas de " + ruta);
		}
		return lineas;
	}

	public int contarColumnasFichero()
	{
		int columnas = 0;
		String primeraLinea = "";
		
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
			System.out.println("Ha habido un error contando las columnas de " + ruta);
		}
		columnas = primeraLinea.split(",").length;

		if(columnas == 0)
		{
			columnas = primeraLinea.lastIndexOf(',')+2; // Posición del último "," 
		}
		return columnas;
	}
}