package gui;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;


public class MainGame extends Pane {
    private final GridPane gridPane;


    public MainGame(String size) {

        gridPane = new GridPane();
        gridPane.setHgap(20);
        gridPane.setVgap(20);
        getChildren().add(gridPane);
        gridPane.setGridLinesVisible(true);
        Label label1 = new Label(size);
        gridPane.add(label1, 5, 5);
    }

}



