package com.example.strands.solver;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordLoader {
    public WordLoader(){}
    public List<String> load(String file) {
        List<String> words = new ArrayList<>();
        try(Scanner scanner = new Scanner(new File(file))){
            while(scanner.hasNextLine()){
                words.add(scanner.nextLine().trim());
            }
        }
        catch (Exception e){
            return words;
        }

        return words;
    }
}
