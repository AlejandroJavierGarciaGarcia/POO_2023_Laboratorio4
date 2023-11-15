package com.biblioteca.controller;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import com.biblioteca.inter.ArchiveInterface;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVController implements ArchiveInterface {

    private final String usersCSVFile = "UsersInfo.csv";
    private final String loansCSVFile = "loans.csv";

    @Override
    public List<String[]> read(String fileName) throws IOException {
        List<String[]> data = new ArrayList<>();
    
        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                data.add(nextLine);
            }
        }
    
        return data;
    }
    

    @Override
    public void export(List<String[]> data, String fileName) {
        try {
            // Implementación para exportar a CSV (ya proporcionada en la respuesta anterior)
            CSVWriter writer = new CSVWriter(new FileWriter(fileName));
            writer.writeAll(data);
            System.out.println("Exportación exitosa a " + fileName);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String[]> readCSV(String fileName) throws IOException {
        List<String[]> data = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(fileName))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                data.add(nextLine);
            }
        }

        return data;
    }

    public void exportUsers(List<String[]> userData) {
        export(userData, usersCSVFile);
    }

    public void exportLoans(List<String[]> loanData) {
        export(loanData, loansCSVFile);
    }
}
