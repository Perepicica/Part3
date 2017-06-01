package gui;

import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import logic.ClassForGame;

public class Cell extends VBox {
    private int x;
    private int y;


    private Text move = new Text();

    public int getPositionRow() {
        return x;
    }

    public int getPositionColumn() {
        return y;
    }

    public void setMove(ClassForGame.Move move) {
        switch (move) {
            case X:
                this.move.setText("X");
                break;
            case O:
                this.move.setText("O");
                break;
            default:
                break;
        }
    }

    public Cell(int h) {
        move.setText(String.valueOf(h));
        this.x = x;
        this.y = y;
        getChildren().addAll(move);
        setPrefSize(50, 50);
        setAlignment(Pos.CENTER);

    }
}


