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

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
        //Copia de seguridad
        try {
            copiaSeguridad();
        } catch (IOException e) {
            e.printStackTrace();
            lanzaAlerta("Error", "Error al realizar la copia de seguridad");
        }
        System.exit(0);
    }

    private final String rutaCopiaSeguridad = "src/main/resources/com/example/proyectojavafx/backup/backup.bck";

    private void copiaSeguridad() throws IOException {
        File copia = new File(rutaCopiaSeguridad);
        FileWriter fileWriter = new FileWriter(copia, false);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        for (Persona persona : listaPersonas) {
            printWriter.println(persona.modelo2Fichero());
        }
        printWriter.close();
        fileWriter.close();
    }


    public void onImFotoClick(MouseEvent mouseEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar imagen");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

        imgFile = fileChooser.showOpenDialog(null);

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss");
            File destino = new File("src/main/resources/com/example/proyectojavafx/imagenes_user/" + sdf.format(new Date()) + "_" + imgFile.getName());
            Files.copy(imgFile.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
            imgFile = destino;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (imgFile != null) {
            Image image = new Image(imgFile.toURI().toString());
            imfoto.setImage(image);
        }
    }

    public void btnGuardarClicek(MouseEvent mouseEvent) {
        //Crear objeto persona
        Persona persona;
        persona = new Persona();
        //Asignar valores a los atributos
        persona.setDni(txtdni.getText());
        persona.setNombre(txtnombre.getText());
        persona.setTelefono(txttelefono.getText());
        persona.setImagen(imgFile.getPath());
        persona.setSexo(rbmujer.isSelected());
        persona.setFechaNacimiento(dtfechanacimiento.getValue());
        persona.setOcupacion(cbocupacion.getValue());
        persona.setConsultoria(chconsultoria.isSelected());
        persona.setDiseno(chdiseno.isSelected());
        persona.setFormacion(chformacion.isSelected());
        persona.setTecnologia(chtecnologia.isSelected());

        //Agregar a la lista
        listaPersonas.add(persona);
        lanzaAlerta("Persona insertada", "Se ha insertado la persona.\nSe limpian los campos");
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

        // Inicializar los datos del fichero
        try {
            cargarDatos();
        } catch (FileNotFoundException e) {
            lanzaAlerta("Error", "Error al cargar los datos");
            e.printStackTrace();
        } catch (IOException e) {
            lanzaAlerta("Error", "Error al cargar los datos");
            throw new RuntimeException(e);
        }
    }

    private void cargarDatos() throws IOException {
        File copia = new File(rutaCopiaSeguridad);
        FileReader fileReader = new FileReader(copia);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String linea;
        while ((linea = bufferedReader.readLine()) != null) {
            Persona persona = new Persona(linea);
            persona.add(persona);
        }
        bufferedReader.close();
        fileReader.close();
        lanzaAlerta("Datos cargados", "Se han cargado los datos del fichero");
        if (listaPersonas.size() > 0) {
            rellenaDatos(listaPersonas.get(0));
        }
    }

    private void rellenaDatos(Persona persona){
        txtdni.setText(persona.getDni());
        txtnombre.setText(persona.getNombre());
        txttelefono.setText(persona.getTelefono());
        if(persona.isSexo()){
            rbmujer.setSelected(true);
        }else{
            rbhombre.setSelected(true);
        }
        dtfechanacimiento.setValue(persona.getFechaNacimiento());
        cbocupacion.setValue(persona.getOcupacion());
        chconsultoria.setSelected(persona.isConsultoria());
        chdiseno.setSelected(persona.isDiseno());
        chformacion.setSelected(persona.isFormacion());
        chtecnologia.setSelected(persona.isTecnologia());
        imfoto.setImage(new Image(persona.getImagen()));
    }
}