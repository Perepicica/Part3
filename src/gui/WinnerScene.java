package gui;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class WinnerScene extends Pane{
    private Text whoWin = new Text();
    private VBox vBox = new VBox();

    public WinnerScene() {
        Text win = new Text("Победил:");
        vBox.getChildren().addAll(win,whoWin);
        getChildren().add(vBox);
    }
    public void setWinnerName(String name){
        whoWin.setText(name);
    }
}
