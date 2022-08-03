module application.noughtsandcrosses {
    requires javafx.controls;
    requires javafx.fxml;


    opens application.noughtsandcrosses to javafx.fxml;
    exports application.noughtsandcrosses;
}