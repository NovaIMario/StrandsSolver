package com.example.strands.solver;

import java.util.*;

public class Solver {
    List<List<Character>> board;
    List<String> words;


    public Solver(List<List<Character>> board, List<String> words){
        this.board = board;
        this.words = words;
    }
    public List<String> solve(){
        Trie trie = new Trie();

        Set<String> wordsFound = new HashSet<>();
        for (String word: words){
            trie.add(word);
        }
        int m = board.size(), n = board.get(0).size();
        boolean[][] visited = new boolean[m][n];
        for(int r = 0; r<m; r++){
            for (int c = 0; c < n; c++) {
                dfs(r, c, trie, visited, new StringBuilder(), wordsFound);
            }
        }
        return new ArrayList<>(wordsFound);
    }

    public Map<String, List<int[]>> solveCoords(){
        Trie trie = new Trie();
        Map<String, List<int[]>> coordMap = new HashMap<>();
        for (String word: words){
            trie.add(word);
        }
        int m = board.size(), n = board.get(0).size();
        boolean[][] visited = new boolean[m][n];
        for(int r = 0; r<m; r++){
            for (int c = 0; c < n; c++) {
                dfs(r, c, trie, visited, new StringBuilder(), new ArrayList<>(), coordMap);
            }
        }
        return coordMap;
    }

    private void dfs(int r, int c, Trie root, boolean[][] visited, StringBuilder word, Set<String> wordsFound){
        int m = board.size(), n = board.get(0).size();
        if (r<0 || c<0 || r>=m || c>=n || !root.contains(board.get(r).get(c)) || visited[r][c]) return;
        char ch = board.get(r).get(c);
        visited[r][c] = true;
        word.append(ch);
        Trie child = root.children.get(ch);
        if (child.endOfWord){
            wordsFound.add(word.toString());
        }
        int[][] dirs = {{1, 0}, {1, 1}, {0, 1}, {-1, 0}, {-1, -1}, {0, -1}};
        for (int[] dir: dirs){
            int a = dir[0], b = dir[1];
            dfs(r+a, c+b, child, visited, word, wordsFound);
        }
        word.deleteCharAt(word.length()-1);
        visited[r][c] = false;
    }

    private void dfs(int r, int c, Trie root, boolean[][] visited, StringBuilder word, List<int[]> cur, Map<String, List<int[]>> coordMap){
        int m = board.size(), n = board.get(0).size();
        if (r<0 || c<0 || r>=m || c>=n || !root.contains(board.get(r).get(c)) || visited[r][c]) return;
        char ch = board.get(r).get(c);
        visited[r][c] = true;
        word.append(ch);
        cur.add(new int[]{r, c});
        Trie child = root.children.get(ch);
        if (child.endOfWord){
            String s = word.toString();
            coordMap.put(s, new ArrayList<>(cur));
        }
        int[][] dirs = {{1, 0}, {1, 1}, {0, 1}, {-1, 0}, {-1, -1}, {0, -1}};
        for (int[] dir: dirs){
            int a = dir[0], b = dir[1];
            dfs(r+a, c+b, child, visited, word, cur, coordMap);
        }
        word.deleteCharAt(word.length()-1);
        cur.remove(cur.size()-1);
        visited[r][c] = false;
    }
}
