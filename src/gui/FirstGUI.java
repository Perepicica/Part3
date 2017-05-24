package gui;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logic.ClassForGame;

public class FirstGUI extends Application {

    static int WIDTH = 600;
    static int HEIGHT = 300;
    private Pane root;
    private GeneralScene generalScene;
    private SettingsScene settingsScene;
    private MainGame mainGame;
    ClassForGame game ;


    public static void main(String[] args) {
        launch(args);
    }

    private void setUpButtons() {

        generalScene.getStartButton().setOnAction(event -> {
            generalScene.setVisible(false);
            settingsScene.setVisible(true);
        });

        generalScene.getExitButton().setOnAction(event -> {
            System.exit(0);
        });

        settingsScene.getBeginButton().setOnAction(event -> {
            settingsScene.setVisible(false);
            mainGame.setVisible(true);

        });
    }

    private Parent setUp() {
        root = new Pane();
        root.setPrefSize(WIDTH, HEIGHT);

        generalScene = new GeneralScene(WIDTH, HEIGHT);
        settingsScene = new SettingsScene();
        String size = settingsScene.size;
        mainGame  = new MainGame(size);

        root.getChildren().addAll(generalScene, settingsScene,mainGame);

        generalScene.setVisible(true);
        settingsScene.setVisible(false);
        mainGame.setVisible(false);

        setUpButtons();
        return root;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(setUp());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Игра крестики нолики");
        primaryStage.show();
    }
}
