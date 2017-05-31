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
    ClassForGame game;


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
            game = new ClassForGame(settingsScene.getBoardSize(), settingsScene.getFirstName(), settingsScene.getSecondName());
            setUpMainGame();
        });
    }

    private Parent setUp() {
        root = new Pane();
        root.setPrefSize(WIDTH, HEIGHT);

        generalScene = new GeneralScene(WIDTH, HEIGHT);
        settingsScene = new SettingsScene();


        root.getChildren().addAll(generalScene, settingsScene);

        generalScene.setVisible(true);
        settingsScene.setVisible(false);

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

    private void setUpMainGame() {
        mainGame = new MainGame(settingsScene.getBoardSize());
        mainGame.setVisible(true);
        root.getChildren().add(mainGame);
        mainGame.getBoard().setOnMouseClicked(event -> {
            double oneCell = mainGame.getBoard().getHeight() / settingsScene.getBoardSize();
            int column = (int) (event.getSceneX() / oneCell);
            int row = (int) (event.getSceneY() / oneCell);
            int x = 5;
            try {
                game.makeTurn(column, row);
                mainGame.change(column, row, game.getTurn());

            } catch (IllegalArgumentException e) {
            }
        });
    }
}
