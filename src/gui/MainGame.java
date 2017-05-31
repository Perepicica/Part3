package gui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import logic.ClassForGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MainGame extends Pane {
    private final GridPane gridPane;
    private List<Cell> cells;
    private int size;
    public GridPane getBoard(){
        return gridPane;
    }

    public MainGame(int size) {
        this.size = size;
        gridPane = new GridPane();
        gridPane.setHgap(0);
        gridPane.setVgap(0);

        getChildren().add(gridPane);
        cells = new ArrayList<>();

        for (int i = 0; i < size*size; i++) {
            cells.add(new Cell());
        }
        int num = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                num++;
                gridPane.add(cells.get(i*size+j), j, i);
            }
        }
    }
     public void change(int x, int y, ClassForGame.Move move){
         cells.get(x*size+y).setMove(move.opposite());
     }
}



