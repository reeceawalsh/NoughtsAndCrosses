package application.noughtsandcrosses;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

public class NoughtsAndCrosses extends Application {

    private ArrayList<Button> buttons = new ArrayList<>();
    private String player = "X";

    public static void main(String[] args) {
        launch(NoughtsAndCrosses.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Layout
        BorderPane layout = new BorderPane();

        // Components
        Label textField = new Label("Turn: " + this.player);
        textField.setPadding(new Insets(20, 20, 20, 20));

        // Gameboard
        GridPane gameBoard = new GridPane();
        gameBoard.setPadding(new Insets(20, 20, 20, 20));
        gameBoard.setHgap(10);
        gameBoard.setVgap(10);

        // Layout
        layout.setTop(textField);
        layout.setCenter(gameBoard);

        // Buttons
        for (int i = 0; i < 9; i++){
            Button button = new Button();
            button.setMinSize(70, 70);
            button.setMaxSize(70, 70);
            button.setFont(Font.font("Monospaced", 25));

            button.setOnAction((event) -> {
                if (button.getText().equals("")) {
                    button.setText(this.player);
                    if (!gameFinished()) {
                        switchTurns();
                        textField.setText("Turn: " + this.player);
                    } else {
                        textField.setText("Game over! Player " + this.player + " wins!");
                    }
                }
            });

            buttons.add(button);
        }

        gameBoard.add(buttons.get(0), 0, 0);
        gameBoard.add(buttons.get(1), 0, 1);
        gameBoard.add(buttons.get(2), 0, 2);
        gameBoard.add(buttons.get(3), 1, 0);
        gameBoard.add(buttons.get(4), 1, 1);
        gameBoard.add(buttons.get(5), 1, 2);
        gameBoard.add(buttons.get(6), 2, 0);
        gameBoard.add(buttons.get(7), 2, 1);
        gameBoard.add(buttons.get(8), 2, 2);

        Scene scene = new Scene(layout);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void switchTurns() {
        if (player.equals("X")) {
            player = "O";
        } else if (player.equals("O")) {
            player = "X";
        }
    }

    public boolean gameFinished() {
        return false;
    }
}

