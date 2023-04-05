package com.example.proyectojavafx.controlador_vista;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class HomeController {
   @FXML
    private ImageView imgInformacion;
   @FXML
    private ImageView imgGuardar;
   @FXML
    private ImageView imgSalir;


   public void onExitButtonClick(MouseEvent mouseEvent) {
         System.exit(0);
   }

}