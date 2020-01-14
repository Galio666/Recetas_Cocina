package mx.edu.uacm.recetas_cocina;

import android.view.View;

public class Receta_Detalles {

    private String Nombre;
    private String Categoria;
    private String Foto;
    private int Calificacion;
    private  String Ingredientes;
    private  String Preparacion;

    public Receta_Detalles() {
    }

    public Receta_Detalles(String Nombre, String Categoria, String Foto, int Calificacion) {
        this.Nombre = Nombre;
        this.Categoria = Categoria;
        this.Foto = Foto;
        this.Calificacion = Calificacion;
    }



    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        this.Categoria = categoria;
    }

    public String getFoto() {
        return Foto;
    }

    public void setFoto(String foto) {
        this.Foto = foto;
    }

    public int getCalificacion() {
        return Calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.Calificacion = calificacion;
    }


    public String getIngredientes() {
        return Ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        Ingredientes = ingredientes;
    }

    public String getPreparacion() {
        return Preparacion;
    }

    public void setPreparacion(String preparacion) {
        Preparacion = preparacion;
    }
}
