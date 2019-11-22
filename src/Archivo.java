import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Archivo {
    private String mensaje;
    private String usuario;

    Archivo(String mensaje, String usuario){
        this.mensaje = mensaje;
        this.usuario = usuario;
    }

    public void crear() throws IOException {
        String ruta = "C:\\Users\\el_re\\Desktop\\Palabras.log";
        File archivo = new File(ruta);

        if (archivo.exists()) {
            try {
                FileWriter fstream = new FileWriter(ruta, true);
                BufferedWriter outp = new BufferedWriter(fstream);
                Date fecha = new Date();
                outp.append("\r\n");
                outp.write(fecha + "\t   " + mensaje + "\t   " + usuario);
                outp.append("\r\n");
                outp.close();
            } catch (IOException ex) {
                System.out.println("Error: " + ex.getMessage());
            }

        } else {
            archivo.createNewFile();
            try {
                FileWriter fstream = new FileWriter(ruta, true);
                BufferedWriter outp = new BufferedWriter(fstream);
                Date fecha = new Date();
                outp.write("Formato: Fecha - Palabra Recibida - Usuario");
                outp.append("\r\n");
                outp.write(fecha + "\t   " + mensaje + "\t   " + usuario);
                outp.append("\r\n");
                outp.close();
            } catch (IOException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }
}