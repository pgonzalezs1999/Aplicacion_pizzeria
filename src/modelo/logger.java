/**
 * Paquete que recoge todas las clases utilizadas para la version de consola adaptadas a GUI
 * @author Pablo Gonzalez Sanchez <a href=mailto:pgonzalezs1999@gmail.com>pgonzalezs1999@gmail.com</a>
 */
package modelo;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 * Genera un registro temporal de los pedidos emitidos
 */
public class logger
{
	/**
	 * Imprime el log en formato con guiones por consola
	 * @param ruta Archivo al que se desea hacer log
	 */
    public static void consultar_log(String ruta) // Imprime el log en formato con guiones por consola
    {
        String linea = "";
        try
        {
            BufferedReader br = new BufferedReader (new FileReader(ruta));

            while ((linea = br.readLine()) != null)
            {
                System.out.println(linea); // No se muestra al usuario, pero nos ayuda para detectar errores
            }
            br.close();
            escribir_log("Se ha consultado el log", "log.txt");
        }
        catch (IOException e)
        {
            System.out.println("\nNo hay un log que leer"); // No se muestra al usuario, pero nos ayuda para detectar errores
        }
    }
    /**
	 * Escribe el log que se pida en el archivo especificado
	 * @param log Datos que se desean grabar
	 * @param ruta Archivo al que se desea hacer log
	 */
    public static void escribir_log(String log, String ruta) // Escribe el log que se pida en el archivo especificado
    { 
        try
        {
            FileWriter fichero = new FileWriter(ruta, true);
            BufferedWriter escritura = new BufferedWriter(fichero); // Crea un nuevo writer
            escritura.write("[" + fecha_y_hora() + "]\t" + log); // Escribir la fecha y la hora actuales
            escritura.newLine();
            escritura.close();
        }
        catch (IOException e)
        {
            System.out.print("No se ha podido registrar el log"); // No se muestra al usuario, pero nos ayuda para detectar errores
        }
    }
    /**
	 * Escribe el log de una pizza en el archivo especificado
	 * @param pizza Pizza que se desea grabar
	 * @param ruta Archivo al que se desea hacer log
	 */
    public static void escribir_nueva_pizza(String pizza, String ruta)
    {
        try
        {
            FileWriter fichero = new FileWriter(ruta, true);
            BufferedWriter escribir = new BufferedWriter(fichero); // Crea un nuevo writer
            escribir.close();   
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    /**
	 * Reconoce la fecha y la hora actuales
	 * @return Cadena de caracteres equivalente al formato fecha del fichero CSV
	 */
    private static String fecha_y_hora()
    {
        LocalDateTime FechaYHoraSinFormato = LocalDateTime.now();
        DateTimeFormatter FormatoDeFechaYHora = DateTimeFormatter.ofPattern("dd/MM/yyyy,HH:mm");
        String FechaYHora = FechaYHoraSinFormato.format(FormatoDeFechaYHora);
        return FechaYHora;
    }
}