package com.example.strands;
import com.example.strands.solver.Solver;
import com.example.strands.solver.WordLoader;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StrandsController {

    @FXML
    private List<List<Character>> board = new ArrayList<>();
    private List<Character> row = new ArrayList<>();
    private int n = -1;
    List<List<int[]>> highlightsCoord;
    List<String> highlightsString;
    private int i = 0;
    @FXML
    private GridPane boardGrid;
    @FXML
    private GridPane rowGrid;
    @FXML
    private TextField input;
    @FXML
    private Label theWord;
    @FXML
    private Button prevBtn;
    @FXML
    private Button nextBtn;
    @FXML
    private Button addChar;
    @FXML
    private Button removeChar;
    @FXML
    private Button doneBtn;
    @FXML
    private Button nextRow;

    private Label createCell(char ch, boolean highlighted) {
        Label cell = new Label(String.valueOf(Character.toUpperCase(ch)));

        // Let cell stretch
        cell.setMinSize(35, 35);
        cell.setPrefSize(35, 35);
        cell.setMaxSize(35, 35);

        cell.setAlignment(Pos.CENTER);
        cell.setStyle("-fx-border-color: black; -fx-alignment: center;");


        // Base style
        cell.setStyle(
                "-fx-border-color: black;" +
                        "-fx-border-width: 1;" +
                        "-fx-font-size: 18;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-color: white;"
        );

        // Highlight
        if (highlighted) {
            cell.setStyle(cell.getStyle() + "-fx-background-color: yellow;");
        }

        return cell;
    }

    public void showBoard() {
        boardGrid.getChildren().clear();

        for (int r = 0; r < board.size(); r++) {
            for (int c = 0; c < board.get(r).size(); c++) {

                boolean highlighted = false;
                if (highlightsCoord != null) {
                    for (int[] coord : highlightsCoord.get(i)) {
                        if (coord[0] == r && coord[1] == c) {
                            highlighted = true;
                            break;
                        }
                    }
                }

                Label cell = createCell(board.get(r).get(c), highlighted);
                boardGrid.add(cell, c, r);
            }
        }
    }

    public void showRow() {
        rowGrid.getChildren().clear();

        for (int i = 0; i< row.size(); i++){
            Label cell = createCell(row.get(i), false);
            rowGrid.add(cell, i, 0);
        }

    }

    public void addChar(ActionEvent e){
        if(n==-1 || row.size()<n){
            String in = input.getText();;
            if(in.length()==1 && Character.isLetter(in.charAt(0))){
                row.add(in.charAt(0));
                input.clear();
            }
            else {
                return;
            }
        };
        showRow();
    }

    public void removeChar(ActionEvent e){
        if (!row.isEmpty()){
            row.removeLast();
            showRow();
        }
    }

    public void addList(ActionEvent e){
        if (!row.isEmpty()){
            if(n==-1){
                n = row.size();
            }
            if (row.size()!=n)return;
            //System.out.println(row);
            board.add(new ArrayList<>(row));
            row.clear();
            showBoard();
            showRow();
        }
    }

    public void done(ActionEvent e) throws IOException {
        solve(e);
        prevBtn.setVisible(true);
        nextBtn.setVisible(true);
        prevBtn.setManaged(true);
        nextBtn.setManaged(true);
        addChar.setVisible(false);
        removeChar.setVisible(false);
        nextRow.setVisible(false);
        doneBtn.setVisible(false);
        input.setVisible(false);
        rowGrid.setVisible(false);
        theWord.setVisible(true);
        theWord.setManaged(true);
        rowGrid.setManaged(false);
        input.setManaged(false);
        addChar.setManaged(false);
        removeChar.setManaged(false);
        nextRow.setManaged(false);
        doneBtn.setManaged(false);
        theWord.setText(highlightsString.get(i));
        showBoard();
        theWord.setText(highlightsString.get(i).toUpperCase());
    }

    public void solve(ActionEvent e) throws IOException {
        WordLoader wl = new WordLoader();
        List<String> words = wl.load("src/main/resources/words.txt");
        Solver solver = new Solver(board, words);
        Map<String, List<int[]>> wordCoords = solver.solveCoords();
        highlightsCoord = new ArrayList<>();
        highlightsString= new ArrayList<>();
        for (String k: wordCoords.keySet()){
            highlightsString.add(k);
            List<int[]> coords = new ArrayList<>(wordCoords.get(k));
            highlightsCoord.add(coords);
        }
        /*
        System.out.println(board);
        for (String k: wordCoords.keySet()){
            StringBuilder sb = new StringBuilder();
            for (int[] coords: wordCoords.get(k)){
                sb.append("(").append(coords[0]).append(",").append(coords[1]).append(") ");
            }
            sb.deleteCharAt(sb.length()-1);
            System.out.println(k+": "+sb.toString());
        }
        System.out.println(words.size());*/

    }

    public void prev(ActionEvent e){
        i--;
        i%=(highlightsString.size()-1);
        showBoard();
        theWord.setText(highlightsString.get(i).toUpperCase());
    }

    public void next(ActionEvent e){
        i++;
        i%=(highlightsString.size()-1);
        showBoard();
        theWord.setText(highlightsString.get(i).toUpperCase());
    }

}
