module labs.lab6 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens labs.lab6 to javafx.fxml;
    exports labs.lab6;
}