package com.example.strands;

import com.example.strands.solver.WordLoader;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        WordLoader wl = new WordLoader();
        List<String> words = wl.load("src/txt/words.txt");
        System.out.println(words.size());
        System.out.println(words.get(0));
    }
}
