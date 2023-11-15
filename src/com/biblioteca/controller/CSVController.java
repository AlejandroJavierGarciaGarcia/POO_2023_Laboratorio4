package com.biblioteca.controller;

import com.biblioteca.inter.ArchiveInterface;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringJoiner;

public class CSVController implements ArchiveInterface {

    private final String filePath;
    private List<HashMap<String, String>> records;
    private String title;

    public CSVController(String filePath, List<HashMap<String, String>> records) {
        this.filePath = filePath;
        this.records = records;
    }

    public CSVController(String filePath, String title) {
        this.filePath = filePath;
        this.title = title;
    }

    public CSVController(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void export() {
        // exportData();
    }

    @Override
    public void read() {
        // importData();
    }

    public void exportData() {
        try (FileWriter fw = new FileWriter(filePath);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {
            out.println(title);
            for (HashMap<String, String> record : records) {
                StringJoiner joiner = new StringJoiner(",");
                for (String key : record.keySet()) {
                    joiner.add(record.get(key));
                }
                out.println(joiner.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<HashMap<String, String>> importData() {
        records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();
            if (line == null) {
                throw new IOException("El archivo CSV está vacío o no es accesible.");
            }
            String[] headers = line.split(",");

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                HashMap<String, String> record = new HashMap<>();
                for (int i = 0; i < headers.length; i++) {
                    record.put(headers[i], values[i]);
                }
                records.add(record);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

    public void appendData(HashMap<String, String> newData) {
        try (FileWriter fw = new FileWriter(filePath, true); // El segundo parámetro "true" habilita el modo de apéndice
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter out = new PrintWriter(bw)) {

            if (title != null) {
                // Si hay un título, escribirlo solo la primera vez que se realiza el apéndice
                out.println(title);
                title = null; // Después de escribir el título, establecerlo en null para evitar que se repita
            }

            StringJoiner joiner = new StringJoiner(",");
            for (String key : newData.keySet()) {
                joiner.add(newData.get(key));
            }
            out.println(joiner.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
