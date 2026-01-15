package com.example.strands.solver;

import java.util.HashMap;
import java.util.Map;

public class Trie {
    Map<Character, Trie> children;
    boolean endOfWord;
    public Trie(){
        this.children=new HashMap<>();
        this.endOfWord = false;
    }
    public void add(String word){
        Trie cur = this;
        for(char c: word.toCharArray()){
            if (!cur.children.containsKey(c))cur.children.put(c, new Trie());
            cur = cur.children.get(c);
        }
        cur.endOfWord=true;
    }
    public boolean contains(char c){
        return this.children.containsKey(c);
    }
}
