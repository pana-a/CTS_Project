package utils;

import models.sauces.FermentableSauce;
import models.sauces.Sauce;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SauceSerializer {
    public static void write(List<Sauce> sauces, String filepath){
        try{
            FileWriter fileWriter = new FileWriter(filepath);
            for(Sauce sauce: sauces){
                StringBuilder sb = new StringBuilder();

                String type = sauce instanceof FermentableSauce ? "fermentable" : "non-fermentable";
                sb.append(type).append(";");
                sb.append(sauce.getName() + ";");

                if(sauce instanceof FermentableSauce){
                    sb.append(((FermentableSauce)sauce).getExpirationHours() + ";");
                }
                sb.append("\n");
                fileWriter.write(sb.toString());
            }
            fileWriter.close();
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public static List<Sauce> read(String filepath){
        List<Sauce> sauces = new ArrayList<>();
        try{
            File file = new File(filepath);
            Scanner fileReader = new Scanner(file);
            while(fileReader.hasNextLine()){
                String data = fileReader.nextLine();
                String[] splitData = data.split(";");

                String type = splitData[0];
                String name = splitData[1];

                if(type.equals("non-fermentable")){
                    Sauce sauce = new Sauce(name);
                    sauces.add(sauce);
                }
                else{
                    Integer expiryHours = Integer.parseInt(splitData[2]);
                    Sauce sauce = new FermentableSauce(name, expiryHours);
                    sauces.add(sauce);
                }
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return sauces;
    }
}
