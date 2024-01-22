package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class NuovoT extends Thread {
    String n;
    Socket s;
    Classe c;

    public NuovoT(Socket s, String n, Classe c) {
        this.s = s;
        this.n = n;
        this.c = c;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            ObjectMapper objectMapper = new ObjectMapper();
            
            String sjson = objectMapper.writeValueAsString(c);
            //System.out.println(sjson);
            out.writeBytes(sjson + "\n");
            System.out.println("json creato e inviato");

            s.close();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}