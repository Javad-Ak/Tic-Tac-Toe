module org.aut.apworkshop12 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens org.aut.apworkshop12 to javafx.fxml;
    exports org.aut.apworkshop12;
    exports org.aut.apworkshop12.Controllers;
    opens org.aut.apworkshop12.Controllers to javafx.fxml;
    exports org.aut.apworkshop12.utils;
    opens org.aut.apworkshop12.utils to javafx.fxml;
}