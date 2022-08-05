package application.noughtsandcrosses;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
        Button newGame = new Button("New Game");

        // Gameboard
        GridPane gameBoard = new GridPane();
        gameBoard.setPadding(new Insets(20, 20, 20, 20));
        gameBoard.setHgap(10);
        gameBoard.setVgap(10);

        // Layout
        layout.setTop(textField);
        layout.setCenter(gameBoard);
        layout.setBottom(newGame);
        BorderPane.setAlignment(textField, Pos.TOP_CENTER);
        BorderPane.setAlignment(gameBoard, Pos.CENTER);
        BorderPane.setAlignment(newGame, Pos.BOTTOM_CENTER);
        BorderPane.setMargin(newGame, new Insets(20, 20, 20, 20));

        // New game button
        newGame.setOnAction((event) -> {
            for (Button button : this.buttons) {
                button.setText("");
            }
            this.player = "X";
            textField.setText("Turn: " + this.player);
        });

        // Game board buttons
        for (int i = 0; i < 9; i++){
            Button button = new Button();
            button.setMinSize(70, 70);
            button.setMaxSize(70, 70);
            button.setFont(Font.font("Monospaced", 25));

            button.setOnAction((event) -> {
                if (!gameFinished() || gameDrawn()) {
                    if (button.getText().equals("")) {
                        button.setText(this.player);
                        if (!gameFinished()) {
                            switchTurns();
                            textField.setText("Turn: " + this.player);
                        } else {
                            textField.setText("Game over! Player " + this.player + " wins!");
                        }
                        if (gameDrawn()) {
                            textField.setText("Game drawn!");
                        }
                    }
                }
            });
            buttons.add(button);
        }

        // Add buttons to the gameBoard
        gameBoard.add(buttons.get(0), 0, 0);
        gameBoard.add(buttons.get(1), 1, 0);
        gameBoard.add(buttons.get(2), 2, 0);
        gameBoard.add(buttons.get(3), 0, 1);
        gameBoard.add(buttons.get(4), 1, 1);
        gameBoard.add(buttons.get(5), 2, 1);
        gameBoard.add(buttons.get(6), 0, 2);
        gameBoard.add(buttons.get(7), 1, 2);
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
        return winCheck(this.player);
    }

    public boolean winCheck(String player) {
        int[][] results = {
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 8},
                {0, 3, 6},
                {1, 4, 7},
                {2, 5, 8},
                {0, 4, 8},
                {2, 4, 6}
        };

        for (int i = 0; i < results.length; i++) {
            int count = 0;
            for (int j = 0; j < results[i].length; j++) {
                if (this.buttons.get(results[i][j]).getText().equals(player)) {
                    count++;
                }
                if (count == 3) {
                    return true;
                }
            }
        }
        return false;
    };

    public boolean gameDrawn() {
        if (winCheck(player)) {
            return false;
        }
        for (Button btn : this.buttons) {
            if (btn.getText().isEmpty()) {
                return false;
            }
        }
        return true;
    };
}

