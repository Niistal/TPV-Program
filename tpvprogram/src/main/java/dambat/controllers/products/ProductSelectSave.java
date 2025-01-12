package dambat.controllers.products;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ProductSelectSave {

    /**
     * Guarda el contenido en un archivo.
     *
     * @param filename Ruta del archivo.
     * @param content  Contenido a guardar.
     */
    public static void saveResultToFile(String filename, String content) {
        File file = new File(filename);

        // Crear el directorio si no existe
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(content);
            System.out.println("Guardado correctamente en: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo: " + e.getMessage());
        }
    }

    /**
     * Carga el contenido desde un archivo.
     *
     * @param filename Ruta del archivo.
     * @return Contenido del archivo como String.
     */
    public static String loadResultFromFile(String filename) {
        StringBuilder content = new StringBuilder();
        File file = new File(filename);

        // Crear archivo vacío si no existe
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Error al crear el archivo: " + e.getMessage());
                return "";
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }

        return content.toString();
    }
}
