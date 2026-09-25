module com.csc311mod3lab2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.csc311mod3lab2 to javafx.fxml;
    exports com.csc311mod3lab2;
}