package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class App {
    public static void main(String[] args) {

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            Socket s = new Socket("localhost", 3000);

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());

            String r = in.readLine();

            Classe c = objectMapper.readValue(r, Classe.class);
            System.out.println(c.toString());
            s.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }
}
