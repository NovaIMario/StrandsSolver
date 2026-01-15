module com.example.strands {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.strands to javafx.fxml;
    exports com.example.strands;
}