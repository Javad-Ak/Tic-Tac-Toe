module org.aut.apworkshop12 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens org.aut.apworkshop12 to javafx.fxml;
    exports org.aut.apworkshop12;
}