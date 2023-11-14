package Model.FileManagers;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;

import Model.BasicObjects.Estandar;
import Model.BasicObjects.Premiun;
import Model.BasicObjects.User;

public class CsvManager implements IFileManager{
    
        final private String path = "src\\DB\\DB.csv";
    
        public CsvManager(){}

        @Override
        public void SaveUser(ArrayList<User> users) throws  ParserConfigurationException {
            try (FileWriter writer = new FileWriter(path, true)) {
                BufferedReader csvReader = new BufferedReader(new FileReader(this.path));
                String row;
                ArrayList<String> data = new ArrayList<String>();
                while ((row = csvReader.readLine()) != null) {
                    data.add(row);
                }
                csvReader.close();
                for (User user : users){
                    String line = "";
                    if (user instanceof Estandar){
                        System.out.println("Estandar guardado");
                        line = "Estandar," + user.getName() + "," + user.getPassword();
                    }else if (user instanceof Premiun){
                        System.out.println("Premiun guardado");
                        line = "Premiun," + user.getName() + "," + user.getPassword();
                    }
                    data.add(line);
                }
                for (String line : data){
                    writer.write(line + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
        @Override
        public ArrayList<User> LoadUsers() {
            ArrayList<User> users = new ArrayList<User>();
            try {
                BufferedReader csvReader = new BufferedReader(new FileReader(this.path));
                String row;
                while ((row = csvReader.readLine()) != null) {
                    String[] data = row.split(",");
                    if (data[0].equals("Estandar")){
                        users.add(new Estandar(data[1], data[2]));
                    }else if (data[0].equals("Premiun")){
                        users.add(new Premiun(data[1], data[2]));
                    }
                }
                csvReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return users;
        }

}