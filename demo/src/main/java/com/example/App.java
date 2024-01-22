package com.example;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        Alunno a1 = new Alunno("Alessandro", "Skorzi", new Date(2005,11,12));
        Alunno a2 = new Alunno("alessio", "aldinucci", new Date(2004,1,3));
        Alunno a3 = new Alunno("sing", "sorang", new Date(2002,10,10));
        Alunno a4 = new Alunno("spugni", "meucci", new Date(2001, 4,5));

        ArrayList<Alunno> alunni = new ArrayList<>();
        alunni.add(a1);
        alunni.add(a2);
        alunni.add(a3);
        alunni.add(a4);

        Classe c = new Classe(1, "5DIA", "05TC", alunni);


        try {
            System.out.println("Server avviato");
            ServerSocket server = new ServerSocket(3000);
            while(true){
                Socket s = server.accept();
                System.out.println("in client si è connesso");

                NuovoT t1 = new NuovoT(s, "server", c);
                t1.start();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        // Deserializzazione
        //Alunno c = objectMapper.readValue(s2, Alunno.class); // da stringa
    }
}