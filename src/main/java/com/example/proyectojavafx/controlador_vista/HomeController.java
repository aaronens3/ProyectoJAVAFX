package com.example.proyectojavafx.controlador_vista;

import com.example.proyectojavafx.modelos.Persona;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
   @FXML
    private ImageView imgInformacion;
   @FXML
    private ImageView imgGuardar;
   @FXML
    private ImageView imgSalir;
    @FXML
    private TextField txtdni;
    @FXML
    private TextField txtnombre;
    @FXML
    private TextField txttelefono;
    @FXML
    private RadioButton rbmujer;
    @FXML
    private RadioButton rbhombre;
    @FXML
    private DatePicker dtfechanacimiento;
    @FXML
    private ComboBox<String> cbocupacion;
    @FXML
    private CheckBox chtecnologia;
    @FXML
    private CheckBox chdiseno;
    @FXML
    private CheckBox chconsultoria;
    @FXML
    private CheckBox chformacion;
    @FXML
    private ImageView imfoto;



   public void onExitButtonClick(MouseEvent mouseEvent) {
         System.exit(0);
   }

   public void onImFotoClick(MouseEvent mouseEvent) {
       FileChooser fileChooser = new FileChooser();
         fileChooser.setTitle("Seleccionar imagen");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                    new FileChooser.ExtensionFilter("PNG", "*.png")
            );
       imgFile = fileChooser.showOpenDialog(null);

         if (imgFile != null) {
             Image image = new Image(imgFile.toURI().toString());
                imfoto.setImage(image);
         }
   }

   public void btnGuardarClicek(MouseEvent mouseEvent) {
       //Crear objeto persona
       Persona persona = new Persona();
       //Asignar valores a los atributos
       persona.setDni(txtdni.getText());
       persona.setNombre(txtnombre.getText());
       persona.setTelefono(txttelefono.getText());
       persona.setImagen(imgFile.toURI().toString());
       persona.setSexo(rbmujer.isSelected());
       persona.setFechaNacimiento(dtfechanacimiento.getValue());
       persona.setOcupacion(cbocupacion.getValue());
       persona.setConsultoria(chconsultoria.isSelected());
       persona.setDiseno(chdiseno.isSelected());
       persona.setFormacion(chformacion.isSelected());
       persona.setTecnologia(chtecnologia.isSelected());

       //Agregar a la lista
       listaPersonas.add(persona);
       lanzaAlerta("Persona insertada",  "Se ha insertado la persona.\nSe limpian los campos");
       //limpiar campos
       limpiaCampos();
   }

    private void lanzaAlerta(String titulo, String mensaje) {
       Alert alert = new Alert(Alert.AlertType.INFORMATION);
       alert.setHeaderText(null);
       alert.setTitle(titulo);
       alert.setContentText(mensaje);
       alert.showAndWait();

    }

    private void limpiaCampos() {
        txtdni.clear();
        txtnombre.clear();
        txttelefono.clear();
        rbhombre.setSelected(false);
        rbmujer.setSelected(false);
        dtfechanacimiento.setValue(null);
        cbocupacion.setValue(null);
        chconsultoria.setSelected(false);
        chdiseno.setSelected(false);
        chformacion.setSelected(false);
        chtecnologia.setSelected(false);
        imfoto.setImage(null);
        File imgFile = new File("src/main/resources/com/example/proyectojavafx/imagenes/guarantee.png");
        imfoto.setImage(new Image(imgFile.toURI().toString()));

    }

    private File imgFile;

   private ArrayList<Persona> listaPersonas;

   ObservableList<String> contenidoComboOcupacion = FXCollections.observableArrayList("Estudiante", "Desempleado", "Autonomo", "Jubilado");
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbocupacion.setItems(contenidoComboOcupacion);

        ToggleGroup grupo = new ToggleGroup();
        rbhombre.setToggleGroup(grupo);
        rbmujer.setToggleGroup(grupo);

        listaPersonas = new ArrayList<>();


    }
}