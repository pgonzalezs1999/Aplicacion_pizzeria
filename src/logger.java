import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class logger {
    public static void consultar_log(String ruta) { //Imprime el log en formato con guiones por consola
        String linea = "";

        try {
            System.out.println();
            BufferedReader br = new BufferedReader (new FileReader(ruta));

            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
            br.close();
            escribir_log("Se ha consultado el log", "log.txt");
        } catch (IOException e) {
            System.out.println("\nNo hay un log que leer");
        }
    }

    public static void escribir_log(String log, String ruta) { //Escribe el log que se pida en el archivo especificado
        try {
            FileWriter fichero = new FileWriter(ruta, true);
            BufferedWriter escritura = new BufferedWriter(fichero);

            escritura.write("[" + fecha_y_hora() + "]\t" + log);
            escritura.newLine();
            escritura.close();
        }	catch (IOException e) {
            System.out.print("No se ha podido registrar el log");
        }
    }

    public static void escribir_nueva_pizza (String pizza, String ruta) {
        try {
            FileWriter fichero = new FileWriter(ruta, true);
            BufferedWriter escribir = new BufferedWriter(fichero);
            escribir.close();   
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String fecha_y_hora() {
        LocalDateTime FechaYHoraSinFormato = LocalDateTime.now();
        DateTimeFormatter FormatoDeFechaYHora = DateTimeFormatter.ofPattern("dd/MM/yyyy,HH:mm");
        String FechaYHora = FechaYHoraSinFormato.format(FormatoDeFechaYHora);

        return FechaYHora;
    }
}