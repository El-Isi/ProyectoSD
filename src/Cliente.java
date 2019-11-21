import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.ref.Cleaner;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {
    private static boolean[] visitados = new boolean[5];

    Cliente(int id_server, String usuario) {
        visitados[id_server] = true;
        //Host del servidor
        final String[] HOST = {
                "127.0.0.1",
                "127.0.0.1",
                "127.0.0.1",
                "127.0.0.1",
                "127.0.0.1"
        }; // Aqui las ips de mis servidores de AWS

        //Puerto del servidor
        final int PUERTO = 5000;
        DataInputStream in;
        DataOutputStream out;

        try {

            //Pedimos al usuario una palabra y la guardamos en un string
            System.out.println("Introduzca una palabra:");
            Scanner scan = new Scanner(System.in);
            String palabra = scan.nextLine();

            //Creo el socket para conectarme con el cliente
            Socket sc = new Socket(HOST[id_server], PUERTO);

            //Definimos nuestras variables de entrada y salida del socket
            in = new DataInputStream(sc.getInputStream());
            out = new DataOutputStream(sc.getOutputStream());

            //Envio un mensaje al cliente y el usuario
            out.writeUTF(palabra);
            out.writeUTF(usuario);

            //Recibo el mensaje del servidor
            String mensaje = in.readUTF();

            System.out.println(mensaje);

            sc.close();

        } catch (IOException ex) {
            int fin = 0;
            for (boolean visitado : visitados) {
                if (visitado) fin++;
            }
            if (fin < 5) {
                int id;

                do {
                    Random random = new Random();
                    id = (int) Math.floor(random.nextDouble() * 4.0 + 1);

                } while (visitados[(id)]);

                System.out.println(id);
                System.out.println("Error al conectarnos al servidor, buscando otro servidor...");
                new Cliente(id, usuario);
            } else {
                System.out.println("No hay servidores disponibles, intente mas tarde");
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("Conectando...");
        //Definimos el usuario y lo obtenemos de windows
        String usuario = System.getProperty("user.name");

        new Cliente(0, usuario);

    }
}
