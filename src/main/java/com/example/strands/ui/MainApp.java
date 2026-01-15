package com.example.strands.ui;

import com.example.strands.solver.Solver;
import com.example.strands.solver.WordLoader;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MainApp {
    public static void main(String[] args) {

        List<List<Character>> board = List.of(
                List.of('c','r','e','m','a'),
                List.of('r','r','e','a','m'),
                List.of('a','r','e','a','m'),
                List.of('n','r','e','a','m'),
                List.of('e','r','e','a','m'));
        WordLoader wl = new WordLoader();
        List<String> words = wl.load("src/main/resources/words.txt");
        Solver solver = new Solver(board, words);
        Map<String, List<int[]>> wordCoords = solver.solveCoords();














        /*
        for (String k: wordCoords.keySet()){
            StringBuilder sb = new StringBuilder();
            for (int[] coords: wordCoords.get(k)){
                sb.append("("+coords[0]+","+coords[1]+") ");
            }
            sb.deleteCharAt(sb.length()-1);
            System.out.println(k+": "+sb.toString());
        }
         */
        //System.out.println(words.size());
    }
}
