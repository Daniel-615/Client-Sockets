package com.mycompany.proyectocliente;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.net.Socket;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Agent extends Thread {
    private UUID id = UUID.randomUUID();
    private Buffer buffer;
    private boolean status = true;
    private String description,name;
    private int agents_process,total_agents=0;
    public Agent(Buffer buffer,int total_agents,String description,String name) {
        this.buffer=buffer;
        this.description=description;
        this.name=name;
    }

    public void greetCustomer(String customerName) {
        System.out.println("Hola soy " + name + ". Bienvenido, " + customerName + ". ¿En qué puedo ayudarte?");
    }

    public void serverRequest() {
        try {
            Socket sc = new Socket("127.0.0.1", 5000);
            DataInputStream in = new DataInputStream(sc.getInputStream());
            DataOutputStream out = new DataOutputStream(sc.getOutputStream());

            // Enviar el nombre del agente al servidor
            out.writeUTF(name);
            out.writeUTF(description);
            // Leer el token del servidor
            String token = in.readUTF();
            System.out.println("Token recibido del servidor: " + token);

            // Cerrar la conexión
            sc.close();

            // Iniciar la comunicación con el cliente utilizando el token recibido
            communicateWithClient(token);

        } catch (IOException ex) {
            Logger.getLogger(Agent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void communicateWithClient(String token) {
        System.out.println("Comunicacion con el cliente iniciada utilizando el token: " + token);
    }
    @Override
    public void run() {
        while (status) {
            Cliente cliente = buffer.atenderCliente();
            if (cliente != null) {
                try {
                    sleep((int) (Math.random() * 6000));
                    //peticion al servidor
                    System.out.println(cliente.getNombre()+" ha salido del banco.");
                    serverRequest();
                    agents_process++;
                } catch (InterruptedException e) {
                    System.out.println("Error: " + e);
                }
            }
            if (agents_process == total_agents) {
                status = false;
            }
        }
    }
}