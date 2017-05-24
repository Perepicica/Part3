package gui;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class GeneralScene extends Pane {
    private Button start;
    private Button exit;

    public Button getStartButton() {
        return start;
    }

    public Button getExitButton() {
        return exit;
    }

    public GeneralScene(int wid, int heig) {

        start = new Button("Начать");
        exit = new Button("Выход");
        getChildren().add(start);
        getChildren().add(exit);
        exit.setTranslateY(30);
    }
}
