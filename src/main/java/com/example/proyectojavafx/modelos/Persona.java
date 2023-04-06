package com.example.proyectojavafx.modelos;

import java.time.LocalDate;

public class Persona {

    private String dni;
    private String nombre;
    private String telefono;
    private boolean sexo;
    private LocalDate fechaNacimiento;
    private String ocupacion;
    private boolean tecnologia;
    private boolean diseno;
    private boolean consultoria;
    private boolean formacion;
    private String imagen;



    public Persona(String linea) {
        String[] datos = linea.split(";");
        this.dni = datos[0];
        this.nombre = datos[1];
        this.telefono = datos[2];
        this.sexo = datos[3].equals("true");
        this.fechaNacimiento = LocalDate.parse(datos[4]);
        this.ocupacion = datos[5];
        this.tecnologia = Boolean.parseBoolean(datos[6]);
        this.diseno = Boolean.parseBoolean(datos[7]);
        this.consultoria = Boolean.parseBoolean(datos[8]);
        this.formacion = Boolean.parseBoolean(datos[9]);
        this.imagen = datos[10];
    }

    public Persona() {

    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }



    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isSexo() {
        return sexo;
    }

    public void setSexo(boolean sexo) {
        this.sexo = sexo;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    public boolean isTecnologia() {
        return tecnologia;
    }

    public void setTecnologia(boolean tecnologia) {
        this.tecnologia = tecnologia;
    }

    public boolean isDiseno() {
        return diseno;
    }

    public void setDiseno(boolean diseno) {
        this.diseno = diseno;
    }

    public boolean isConsultoria() {
        return consultoria;
    }

    public void setConsultoria(boolean consultoria) {
        this.consultoria = consultoria;
    }

    public boolean isFormacion() {
        return formacion;
    }

    public void setFormacion(boolean formacion) {
        this.formacion = formacion;
    }

    public String modelo2Fichero(){
        return dni + ";" + nombre + ";" + telefono + ";" + sexo + ";" + fechaNacimiento.toString() + ";" + ocupacion + ";" + tecnologia + ";" + diseno + ";" + consultoria + ";" + formacion + ";" + imagen;
    }

    public void add(Persona persona) {

    }
}
