package ficheros;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class EditarCSVpedidos {
	private String[][] pedidos;
	String ruta;
	
	public EditarCSVpedidos (String nuevaRuta) {
		this.ruta = nuevaRuta;
	}
	
	public void guardarCSV()
	{
		FileWriter fichero;
		try
		{
			fichero = new FileWriter(ruta);
			for (int i = 0; i < this.pedidos.length; i++)
			{
				for (int j = 0; j < this.pedidos[0].length; j++)
				{
					fichero.write("" + this.pedidos[i][j]); 
					
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
			System.out.println("ERROR ESCRITURA FICHERO: " + e.getMessage());
			e.printStackTrace();
		}
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
			
			this.pedidos = matriz; // Se actualiza la matriz de datos CSV
			resultado = true; // Todo ha ido bien
		}
		catch (FileNotFoundException e)
		{
			System.out.println("Ha habido un error leyendo la matriz de " + ruta);
		}
		return resultado;
	}
	
	
	
	private static String fecha()
	{
        LocalDateTime FechaYHoraSinFormato = LocalDateTime.now();
        DateTimeFormatter FormatoDeFechaYHora = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String FechaYHora = FechaYHoraSinFormato.format(FormatoDeFechaYHora);
        return FechaYHora;
    }
	
	private static String hora()
	{
        LocalDateTime FechaYHoraSinFormato = LocalDateTime.now();
        DateTimeFormatter FormatoDeFechaYHora = DateTimeFormatter.ofPattern("HH:mm");
        String FechaYHora = FechaYHoraSinFormato.format(FormatoDeFechaYHora);

        return FechaYHora;
    }
	
	public void addPedido ()
	{
		String[][] nuevospedidos = new String[this.pedidos.length+1][this.pedidos[0].length];
		int entregado = 0;
		
		for (int i = 1; i<nuevospedidos.length;i++)
		{
			nuevospedidos[i] = this.pedidos[i-1];
		}
		
		this.pedidos = nuevospedidos;
		
		int suma = Integer.parseInt(pedidos[1][0]) + 1;
		
		pedidos[0][0] = String.valueOf(suma);
		pedidos[0][1] = fecha();
		pedidos[0][2] = hora();
		pedidos[0][3] = String.valueOf(entregado);
		
		this.guardarCSV();
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