module com.example.proyectojavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.proyectojavafx to javafx.fxml;
    exports com.example.proyectojavafx;
    exports com.example.proyectojavafx.controlador_vista;
    opens com.example.proyectojavafx.controlador_vista to javafx.fxml;
}