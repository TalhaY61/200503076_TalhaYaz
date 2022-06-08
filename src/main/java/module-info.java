module com.sample.fahrschuleapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;


    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires mysql.connector.java;

    opens com.sample.fahrschuleapp to javafx.fxml;
    exports com.sample.fahrschuleapp;
}