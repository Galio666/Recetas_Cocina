package mx.edu.uacm.recetas_cocina;

public class Receta_Detalles {

    private String nombreReceta;
    private String tipoReceta;
    private String imagen;
    private String rating;


    public Receta_Detalles(String nombreReceta, String tipoReceta, String imagen, String rating) {
        this.nombreReceta = nombreReceta;
        this.tipoReceta = tipoReceta;
        this.imagen = imagen;
        this.rating = rating;
    }

    public String getNombreReceta() {
        return nombreReceta;
    }

    public void setNombreReceta(String nombreReceta) {
        this.nombreReceta = nombreReceta;
    }

    public String getTipoReceta() {
        return tipoReceta;
    }

    public void setTipoReceta(String tipoReceta) {
        this.tipoReceta = tipoReceta;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
