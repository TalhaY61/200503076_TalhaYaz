module com.sample.fahrschuleapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    //requires mysql.connector.java;


    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;

    opens com.sample.fahrschuleapp to javafx.fxml;
    exports com.sample.fahrschuleapp;
}