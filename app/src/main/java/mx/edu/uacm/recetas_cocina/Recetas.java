package mx.edu.uacm.recetas_cocina;

public class Recetas {

    public String nombre;
    public String calificacion;
    public String dificultad;
    public String ingredientes;
    public String categoria;
    public String favorito;
    public String preparacion;

    public Recetas() {

    }

    public Recetas(String nombre, String calificacion, String dificultad, String ingredientes, String categoria, String favorito, String preparacion) {
        this.nombre = nombre;
        this.calificacion = calificacion;
        this.dificultad = dificultad;
        this.ingredientes = ingredientes;
        this.categoria = categoria;
        this.favorito = favorito;
        this.preparacion = preparacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getFavorito() {
        return favorito;
    }

    public void setFavorito(String favorito) {
        this.favorito = favorito;
    }

    public String getPreparacion() {
        return preparacion;
    }

    public void setPreparacion(String preparacion) {
        this.preparacion = preparacion;
    }

    @Override
    public String toString() {
        return "Recetas{" +
                "nombre='" + nombre + '\'' +
                ", calificacion='" + calificacion + '\'' +
                ", dificultad='" + dificultad + '\'' +
                ", ingredientes='" + ingredientes + '\'' +
                ", categoria='" + categoria + '\'' +
                ", favorito='" + favorito + '\'' +
                ", preparacion='" + preparacion + '\'' +
                '}';
    }
}
