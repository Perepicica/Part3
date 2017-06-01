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
    private WinnerScene winnerScene;
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
        winnerScene = new WinnerScene();


        root.getChildren().addAll(generalScene, settingsScene,winnerScene);

        generalScene.setVisible(true);
        settingsScene.setVisible(false);
        winnerScene.setVisible(false);

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
            try {
                game.makeTurn(column, row);
                mainGame.change(column, row, game.getTurn());
                String winner = game.getWinner();
                if (winner!=null){
                    mainGame.setVisible(false);
                    winnerScene.setWinnerName(winner);
                    winnerScene.setVisible(true);

                }
            } catch (IllegalArgumentException e) {
            }
        });
    }
}
