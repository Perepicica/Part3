package gui;

import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class SettingsScene extends Pane {
    private Button begin;
    private Button next;
    private boolean nameOffer;
    private int size;
    private String firstName;
    private String secondName;

    public int getBoardSize() {
        return size;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    private VBox vbox = new VBox();

    public Button getBeginButton() {
        return begin;
    }


    public SettingsScene() {
        getChildren().add(vbox);
        begin = new Button("Начать игру");
        firstName();


    }

    private void firstName() {
        TextField text11 = new TextField();
        next = new Button("Далее");
        if (!nameOffer) {
            Text text1 = new Text(10, 20, "Первый игрок, введите своё имя:");
            vbox.getChildren().add(text1);
            vbox.getChildren().add(text11);
            vbox.getChildren().add(next);
            nameOffer = true;
        }
        next.setOnAction(event -> {
            if (text11.getText().isEmpty()) emptyName(1);
            else {
                firstName = text11.getText();
                nameOffer = false;
                secondName();
            }
        });
    }

    private void secondName() {
        TextField text22 = new TextField();
        next = new Button("Далее");
        if (!nameOffer) {
            Text text2 = new Text(10, 20, "Второй игрок, введите своё имя:");
            vbox.getChildren().add(text2);
            vbox.getChildren().add(text22);
            vbox.getChildren().add(next);
            nameOffer = true;
        }
        next.setOnAction(event -> {
            if (text22.getText().isEmpty()) emptyName(2);
            else {
                secondName = text22.getText();
                nameOffer = false;
                fieldSize();
            }
        });
    }

    public void fieldSize() {
        ChoiceBox<Integer> choiceBox = new ChoiceBox();
        Text text3 = new Text(10, 20, "Выберите размер поля:");
        choiceBox.setItems(FXCollections.observableArrayList(3,4,5,6,7,8,9));
        vbox.getChildren().add(choiceBox);
        choiceBox.setValue(3);
        size = choiceBox.getValue();
        vbox.getChildren().add(begin);
        choiceBox.setOnAction(event -> {
            size = choiceBox.getValue();
        });
    }

    private void emptyName(int i) {
        Text error = new Text(10, 20, "Имя не может быть пустым,попробуйте еще раз:");
        vbox.getChildren().add(error);
        if (i == 1) firstName();
        else secondName();
    }
}


