import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {

    public static void main(String[] args) {

        ServerSocket servidor = null; //Declaramos el socket del servidor
        Socket sc = null; //Declaramos el socket del cliente que se conectara a nuestro servidor
        DataInputStream in; //Declaramos nuestra entrada
        DataOutputStream out; //Declaramos nuestra salida

        //puerto de nuestro servidor
        final int PUERTO = 5000;

        try {
            //Creamos el socket del servidor
            servidor = new ServerSocket(PUERTO);
            System.out.println("Servidor iniciado");

            //Siempre estara escuchando peticiones
            while (true) {

                //Espero a que un cliente se conecte
                sc = servidor.accept();
                System.out.println("Cliente conectado");
                
                //Inicializamos nuestra variable de entrada y salida tomando como referencia el socket del cliente
                in = new DataInputStream(sc.getInputStream());
                out = new DataOutputStream(sc.getOutputStream());

                //Leo el mensaje que me envia
                String mensaje = in.readUTF();
                String usuario = in.readUTF();


                //Mando el mensaje a mi archivo Palabras.log
                Archivo escribir_palabra = new Archivo(mensaje, usuario);
                escribir_palabra.crear();

                //Le envio un mensaje
                out.writeUTF("Bienvenido " + usuario);

                //Cierro el socket
                sc.close();
                System.out.println("Cliente desconectado");
            }

        } catch (IOException ex) {
            //siemple mensaje de error en caso de que algo salga mal nos informe
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
