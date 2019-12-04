import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Archivo {
    
    //Declaramos nuestras variables para guardar el usuario y mensaje
    private String mensaje;
    private String usuario;

    Archivo(String mensaje, String usuario){
        //Asignamos a nuestras variables el contenido por parametro  a travez del contructor
        this.mensaje = mensaje;
        this.usuario = usuario;
    }

    public void crear() throws IOException {
        
        //Declaramos la ruta de nuestro archivo el nombre que tendra y donde estara ubicado
        String ruta = "/home/ubuntu/Palabras.log";
        File archivo = new File(ruta);

        if (archivo.exists()) {
            //Si el archivo existe simplemente añadira los mensaje
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
            //Si el archivo no existe, lo creara y añadira una leyenda para saber el formato que tiene e incertara el primer mensaje
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
