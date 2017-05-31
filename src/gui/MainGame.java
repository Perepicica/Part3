package gui;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import logic.ClassForGame;

import java.util.ArrayList;
import java.util.List;


public class MainGame extends Pane {

    private final GridPane gridPane;
    private List<Cell> cells;
    private int size;

    public GridPane getBoard() {
        return gridPane;
    }

    public MainGame(int size) {
        this.size = size;
        gridPane = new GridPane();
        gridPane.setGridLinesVisible(true);
        gridPane.setHgap(0);
        gridPane.setVgap(0);

        getChildren().add(gridPane);
        cells = new ArrayList<>();

        for (int i = 0; i < size * size; i++) {
            cells.add(new Cell(i));
        }

        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                gridPane.add(cells.get(row * size + column), column, row);
            }
        }
    }

    public void change(int column, int row, ClassForGame.Move move) {
        cells.get(row * size + column).setMove(move.opposite());
    }
}



