/**
 * Module-info con todas las requires usadas:
 *
 * | requires javafx.controls; - Control de las acciones
 * | requires javafx.fxml; - Uso de las vistas
 * | requires com.dlsc.formsfx; - Formas de los componentes de las vistas
 * | requires javafx.media; - Reproducción de videos
 * | requires java.sql; - Uso de la base de datos
 */
module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires javafx.media;
    requires java.sql;

    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
}